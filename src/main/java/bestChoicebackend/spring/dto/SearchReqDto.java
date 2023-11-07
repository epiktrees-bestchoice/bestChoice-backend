package bestChoicebackend.spring.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class SearchReqDto {
    private String region;
    private String sort;
//    @FormattedLocalDate
//    private LocalDate sel_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sel_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sel_date2;
    private Integer min_price; // 기본값을 0L로 설정
    private Integer max_price;
    private List<Integer> keywords = new ArrayList<>();
}
