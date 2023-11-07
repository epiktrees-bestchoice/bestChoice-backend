package bestChoicebackend.spring.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
//@Setter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchReqDto {
    private String region="서울";
    private String sort = "DESC";
//    @FormattedLocalDate
//    private LocalDate sel_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sel_date = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sel_date2 = LocalDate.now();
    private Integer min_price = 0; // 기본값을 0L로 설정
    private Integer max_price = 10000000;
    private List<Integer> keywords = new ArrayList<>();

    private SearchReqDto (String region, String sort, LocalDate sel_date, LocalDate sel_date2, Integer min_price, Integer max_price, List<Integer> keywords){
        if (region != null) {this.region = region;}
        if (sort != null) {this.sort = sort;}
        if (sel_date != null) {this.sel_date = sel_date;}
        if (sel_date2 != null) {this.sel_date2 = sel_date2;}
        if (min_price != null) {this.min_price = min_price;}
        if (max_price != null) {this.max_price = max_price;}
        if (keywords != null) {this.keywords = keywords;}
    }
}
