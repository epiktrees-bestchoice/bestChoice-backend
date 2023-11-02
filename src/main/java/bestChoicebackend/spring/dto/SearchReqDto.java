package bestChoicebackend.spring.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class SearchReqDto {
    private String region;
    private String sort;
//    @FormattedLocalDate
//    private LocalDate sel_date;
    private String sel_date; // Date 포멧을 확인하려면 커스텀 어노테이션이 필요
    private String sel_date2;
    private Integer min_price; // 기본값을 추가하려면 커스텀 어노테이션이 필요
    private Integer max_price;
    @NotNull
    private List<Long> keywords;
}
