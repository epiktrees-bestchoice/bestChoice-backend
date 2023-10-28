package bestChoicebackend.spring.controllerv2;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @GetMapping(value = "/search/{type}")
    public Page<Accommodation> getReq(@PathVariable("type") String type,
                                      SearchReqDto searchReqDto,
                                      @PageableDefault(size=10, direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.GetProductWithCondition(type, searchReqDto, pageable);
    }
}
