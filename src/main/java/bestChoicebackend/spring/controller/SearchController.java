package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Region;
import bestChoicebackend.spring.service.AccommodationService;
import bestChoicebackend.spring.service.SearchService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /**
     * 숙소 검색
     * 검색어가 포함된 숙소 리스트 Page 객체로 10개씩 반환
     */
    @GetMapping("/api/v1/search/result/{text}")
    public Page<Accommodation> findByAccommodationName(@PathVariable("text") String text,
                                                       @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Accommodation> accommodations = searchService.findByAccommodationName(text, pageable);
        return accommodations;
    }

    /**
     * 모텔 검색
     * 여러가지 조건에 따라 모텔 리스트 Page객체로 10개씩 반환
     */
    @GetMapping("/api/v1/search/key/MOTEL/{region}")
    public Page<Accommodation> findMotelByCondition(@PathVariable(name = "region") String region,
                                                    @RequestParam String sortVal,
                                                    @RequestParam(required = false) String sel_date, @RequestParam(required = false) String sel_date2,
                                                    @RequestParam Long min_price, @RequestParam Long max_price,
                                                    @RequestParam Long reserve,
                                                    @RequestParam String keyword,
                                                    @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Accommodation> accommodations = searchService.findMotelByCondition(Region.from(region), sortVal, LocalDate.parse(sel_date), LocalDate.parse(sel_date2), min_price, max_price, reserve, keyword, pageable);
        return accommodations;
    }

    @GetMapping("/api/v1/search/key/HOTEL/{region}")
    public Page<Accommodation> findHotelByCondition(
            @PathVariable(name = "region") String region,
            @RequestParam String sortVal,
            @RequestParam(required = false) String sel_date, @RequestParam(required = false) String sel_date2,
            @RequestParam Long min_price, @RequestParam Long max_price,
            @RequestParam Long reserve,
            @RequestParam String keyword,
            @PageableDefault(size = 10) Pageable pageable
    ){
        Page<Accommodation> accommodations = searchService.findHotelByCondition(Region.from(region), sortVal, LocalDate.parse(sel_date), LocalDate.parse(sel_date2), min_price, max_price, reserve, keyword, pageable);
        return accommodations;
    }
}
