package bestChoicebackend.spring.dto.accommodationDto;

import bestChoicebackend.spring.domain.AccommodationType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccommodationResDto{
    Long id;
    String accommodationName;
    AccommodationType type;
    Long price;
    String imgUrl;
    List<String> subImgUrls;
    String region;
    String introduce;

    @QueryProjection
    public AccommodationResDto(Long id, String accommodationName,
                               AccommodationType type, Long price, String imgUrl,
                               String region, String introduce){
        this.id = id;
        this.accommodationName = accommodationName;
        this.type = type;
        this.price = price;
        this.imgUrl = imgUrl;
        this.region = region;
        this.introduce = introduce;
    }

}
