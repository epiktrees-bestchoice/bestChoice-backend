package bestChoicebackend.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class SearchReqDto {
    private String region;
    private String sort;
//    @FormattedLocalDate
//    private LocalDate sel_date;
    private String sel_date;
    private String sel_date2;
    @Builder.Default
    private Long min_price = 0L; // 기본값을 0L로 설정
    @Builder.Default
    private Long max_price = 2147483647L;
    private List<Long> keywords;
}
