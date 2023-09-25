package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
                                          @PageableDefault(size=10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<Accommodation> accommodations = searchService.findByAccommodationName(text, pageable);
        return accommodations;
    }


}
