package bestChoicebackend.spring.controllerv2;

import bestChoicebackend.spring.dto.accommodationDto.AccommodationReqDto;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @GetMapping(value = "/search/{type}")
    public Page<AccommodationResDto> getReq(@PathVariable("type") String type,
                                            @RequestBody SearchReqDto searchReqDto, // Controller에서 받는 json을 DTO로 변환
                                            @PageableDefault(size=10, direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.GetProductWithCondition(type, searchReqDto, pageable);
    }
}
