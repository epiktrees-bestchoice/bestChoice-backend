package bestChoicebackend.spring.dto.SubImg;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class SubImgResDto {
    Long subImgId;
    Long accommodationId;
    String subImgUrl;
}
