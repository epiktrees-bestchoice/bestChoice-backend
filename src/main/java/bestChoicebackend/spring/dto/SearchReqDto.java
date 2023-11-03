package bestChoicebackend.spring.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
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
    private List<Integer> keywords;
    public SearchReqDto(){ // 기본 생성자
        sel_date = LocalDate.now(); // 오늘 날짜 입력
        sel_date2 = LocalDate.now().plusDays(1); // 내일 날짜 입력
        min_price = 0;
        max_price = 10000000;
    }
}
