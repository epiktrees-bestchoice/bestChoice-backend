package bestChoicebackend.spring.dto.SubImg;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.SubImg;
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
    Accommodation accommodation;
    String subImgUrl;

    public SubImg toEntity(){
        return SubImg.builder()
                .accommodation(accommodation)
                .subImgUrl(subImgUrl)
                .build();
    }
}
