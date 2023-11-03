package bestChoicebackend.spring.dto.accommodationDto;

import bestChoicebackend.spring.domain.AccommodationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

// Controller에서 받는 json을 DTO로 변환
@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class AccommodationReqDto {

    String accommodationName;
    AccommodationType type;
    Long price;
    String imgUrl;
    String region;
    String introduce;
}
