package bestChoicebackend.spring.controllerv2;

import bestChoicebackend.spring.dto.accommodationDto.AccommodationReqDto;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.service.ProductService;
import bestChoicebackend.spring.service.SubImgService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final SubImgService subImgService;

    @GetMapping(value = "/search/{type}")
    public Page<AccommodationResDto> getReq(@PathVariable("type") String type,
                                            @Valid  @RequestBody SearchReqDto searchReqDto, // Controller에서 받는 json을 DTO로 변환, NotNull 판단,.
                                            @PageableDefault(size=10, direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("type : "+type+" region : "+searchReqDto.getRegion()+" SelDate : "+searchReqDto.getSel_date()+" maxPrice : " + searchReqDto.getMax_price());
        return productService.getProductWithCondition(type, searchReqDto, pageable);
    }

    @GetMapping("/search/Default")
    public List<AccommodationResDto> getAccomms(){
        return productService.getJDBCProduct();
    }

    @PostMapping("/subimage/init")
    public int saveAll(){
        return subImgService.saveAll();
    }

}
