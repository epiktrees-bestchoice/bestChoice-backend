package bestChoicebackend.spring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SearchReqDto {
    private String region;
    private String sort;
    private String sel_date;
    private String sel_date2;
    private Long min_price;
    private Long max_price;
    private List<Long> keywords;
}
