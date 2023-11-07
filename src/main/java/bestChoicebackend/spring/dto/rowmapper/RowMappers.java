package bestChoicebackend.spring.dto.rowmapper;

import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RowMappers {

    public RowMapper<Integer> itemRowMapper() {
        return ((rs, rowNum) -> {
//            log.info(String.valueOf(rs.getInt(1)));
            return rs.getInt(1);
        });
    }

    public RowMapper<AccommodationResDto> accommodationRowMapper() {
        return (rs, rowNum) -> {
//            AccommodationType type = AccommodationType.from(rs.getInt(7)); // Convert the integer to Enum
//            Accommodation accommodation = new AccommodationDto();
//            accommodation.setAccommodationId(rs.getLong(1));
//            accommodation.setAccommodationName(rs.getString(2));
//            accommodation.setImgUrl(rs.getString(3));
//            accommodation.setIntroduce(rs.getString(4));
//            accommodation.setPrice(rs.getLong(5));
//            accommodation.setRegion(rs.getString(6));
//            accommodation.setType(type);

            AccommodationType type = AccommodationType.from(rs.getInt(7));
            return AccommodationResDto.builder()
                    .id(rs.getLong(1))
                    .accommodationName(rs.getString(2))
                    .imgUrl(rs.getString(3))
                    .introduce(rs.getString(4))
                    .price(rs.getLong(5))
                    .region(rs.getString(6))
                    .type(type).build();
        };
    }
}
