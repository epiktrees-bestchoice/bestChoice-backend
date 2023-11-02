package bestChoicebackend.spring.dto.accommodationDto;

import bestChoicebackend.spring.domain.AccommodationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccommodationResDto{
    Long id;
    String accommodationName;
    AccommodationType type;
    Long price;
    String imgUrl;
    String region;
    String introduce;
}