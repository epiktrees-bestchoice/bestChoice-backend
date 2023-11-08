package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.dto.accommodationDto.QAccommodationResDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import static bestChoicebackend.spring.domain.QAccommodation.*;
import static bestChoicebackend.spring.domain.QAccommodationKeyword.accommodationKeyword;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface AccommodationCustom { // QueryDSL은 빈에 자동 등록!!

    List<AccommodationResDto> findByConditions(String type, SearchReqDto searchReqDto, Pageable pageable);
}

@RequiredArgsConstructor
class AccommodationCustomImpl implements AccommodationCustom{
    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression typeEq(String type){
        return type != null ? accommodation.type.eq(AccommodationType.valueOf(type)) : null;
    }

    private BooleanExpression regionEq(String region){
        return region != null ? accommodation.region.eq(region) : null;
    }

    private BooleanExpression keywordContain(List<Integer> keywords){
        return keywords != null ?  accommodationKeyword.keywordId.keywordId
                .in(keywords.stream().map(Long::valueOf).collect(Collectors.toList()))
                : null;
    }

    @Override
    public List<AccommodationResDto> findByConditions(String type, SearchReqDto searchReqDto, Pageable pageable) {

        QAccommodationResDto accommodationResDto = new QAccommodationResDto(accommodation.accommodationId,
                accommodation.accommodationName,
                accommodation.type,
                accommodation.price,
                accommodation.imgUrl,
                accommodation.region,
                accommodation.introduce);

        List<AccommodationResDto> content =  jpaQueryFactory.select(accommodationResDto)
                .from(accommodation)
                .join(accommodation.accommodationKeywords, accommodationKeyword).fetchJoin()
                .where(typeEq(type)
                        .and(regionEq(searchReqDto.getRegion()))
                        .and(keywordContain(searchReqDto.getKeywords())))

//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetch();

        return content;
    }
}

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, AccommodationCustom {
    Page<Accommodation> findByAccommodationNameContaining(String accommodationName, Pageable pageable);

    Optional<Accommodation> findByAccommodationId(Long accommodationId);

    @Query("select m from Accommodation m")
    List<Accommodation> findAllAccommodationId();
    List<Accommodation> findAllByType(AccommodationType type);
    List<Accommodation> findAllByTypeAndRegion(AccommodationType type, String region);
}
