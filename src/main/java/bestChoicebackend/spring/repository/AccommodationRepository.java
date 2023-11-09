package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.dto.accommodationDto.QAccommodationResDto;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import static bestChoicebackend.spring.domain.QAccommodation.*;
import static bestChoicebackend.spring.domain.QAccommodationKeyword.accommodationKeyword;
import static bestChoicebackend.spring.domain.QKeyword.keyword;
import static bestChoicebackend.spring.domain.QReserve.reserve;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.sql.Date;
interface AccommodationCustom { // QueryDSL은 빈에 자동 등록!!
    Page<AccommodationResDto> findByConditions(String type, SearchReqDto searchReqDto, Pageable pageable);
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
        if(keywords != null && !keywords.isEmpty()){
            List<Long> longs = keywords.stream().map(Long::valueOf).toList();
            return accommodationKeyword.keywordId.keywordId.in(longs);
        } else{return null;}// null 인 경우 항상 true로 설정
    }
    private BooleanExpression priceBetween(Integer minPrice, Integer maxPrice){
        return accommodation.price.between(minPrice, maxPrice);
    }
    private BooleanExpression reserveUnable(LocalDate selDate, LocalDate selDate2){
       Date hopeStart = Date.valueOf(selDate); // 예약
        Date hopeEnd = Date.valueOf(selDate2);
       return reserve.reserveDate.loe(hopeEnd).and(reserve.endDate.goe(hopeStart));
    }
    @Override
    public Page<AccommodationResDto> findByConditions(String type, SearchReqDto searchReqDto, Pageable pageable) {
        List<Accommodation> contents =  jpaQueryFactory.select(accommodation)
                .from(accommodation)
                .where(typeEq(type),
                        regionEq(searchReqDto.getRegion()),
                        priceBetween(searchReqDto.getMin_price(), searchReqDto.getMax_price()),
                        accommodation.accommodationId.in( // keyword를 모두 포함하는 숙소만 표기
                                JPAExpressions
                                        .select(accommodation.accommodationId)
                                        .from(accommodation)
                                        .join(accommodation.accommodationKeywords, accommodationKeyword)
                                        .join(accommodationKeyword.keywordId, keyword)
                                        .where(keywordContain(searchReqDto.getKeywords()))
                                        .groupBy(accommodation.accommodationId)
                                        .having(accommodation.accommodationId.count().goe(searchReqDto.getKeywords().size()))),
                        accommodation.accommodationId.notIn( // 예약 불가인 숙소는 표기 X
                                JPAExpressions
                                        .select(reserve.accommodationId.accommodationId)
                                        .from(reserve)
                                        .where(reserveUnable(searchReqDto.getSel_date(), searchReqDto.getSel_date2()))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = jpaQueryFactory.select(accommodation.count())
                .from(accommodation)
                .where(typeEq(type),
                        regionEq(searchReqDto.getRegion()),
                        priceBetween(searchReqDto.getMin_price(), searchReqDto.getMax_price()),
                        accommodation.accommodationId.in( // keyword를 모두 포함하는 숙소만 표기
                                JPAExpressions
                                        .select(accommodation.accommodationId)
                                        .from(accommodation)
                                        .join(accommodation.accommodationKeywords, accommodationKeyword)
                                        .join(accommodationKeyword.keywordId, keyword)
                                        .where(keywordContain(searchReqDto.getKeywords()))
                                        .groupBy(accommodation.accommodationId)
                                        .having(accommodation.accommodationId.count().goe(searchReqDto.getKeywords().size()))),
                        accommodation.accommodationId.notIn( // 예약 불가인 숙소는 표기 X
                                JPAExpressions
                                        .select(reserve.accommodationId.accommodationId)
                                        .from(reserve)
                                        .where(reserveUnable(searchReqDto.getSel_date(), searchReqDto.getSel_date2()))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();

        List<AccommodationResDto> accommodationResDtos = contents.stream()
                                                        .map(AccommodationResDto::fromEntity)
                                                        .toList();
        if(totalCount != null){
            return new PageImpl<>(accommodationResDtos, pageable, totalCount);
        }
        else{
            return null;
        }
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
