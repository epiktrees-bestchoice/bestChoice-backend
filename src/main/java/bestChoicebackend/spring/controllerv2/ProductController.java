package bestChoicebackend.spring.controllerv2;

import bestChoicebackend.spring.dto.accommodationDto.AccommodationReqDto;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.service.ProductService;
import bestChoicebackend.spring.service.SubImgService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
                                            @RequestParam String region, @RequestParam LocalDate sel_date, @RequestParam LocalDate sel_date2,
                                            @RequestParam Integer min_price, @RequestParam Integer max_price, @RequestParam List<Integer> keywords,
                                            @PageableDefault(size=10, direction = Sort.Direction.DESC) Pageable pageable) {
        SearchReqDto.SearchReqDtoBuilder searchReqDtoBuilder = SearchReqDto.builder();
        if(region == null) searchReqDtoBuilder.region("서울");
        else searchReqDtoBuilder.region(region);

        if(sel_date == null) searchReqDtoBuilder.sel_date(LocalDate.now());
        else searchReqDtoBuilder.sel_date(sel_date);

        if(sel_date2 == null) searchReqDtoBuilder.sel_date(LocalDate.now());
        else searchReqDtoBuilder.sel_date2(sel_date2);

        if(min_price == null) searchReqDtoBuilder.min_price(0);
        else searchReqDtoBuilder.min_price(min_price);

        if(max_price == null) searchReqDtoBuilder.max_price(10000000);
        else searchReqDtoBuilder.max_price(max_price);

        if(keywords == null) searchReqDtoBuilder.keywords(new ArrayList<Integer>());
        else searchReqDtoBuilder.keywords(keywords);
        SearchReqDto searchReqDto = searchReqDtoBuilder.build();

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
