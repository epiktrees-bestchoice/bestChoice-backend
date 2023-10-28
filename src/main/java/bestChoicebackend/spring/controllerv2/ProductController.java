package bestChoicebackend.spring.controllerv2;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.dto.SearchReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v2/product")
@Slf4j
public class ProductController {

    @GetMapping(value = "/search/{type}")
    public Page<Accommodation> getReq(@PathVariable("type") String type, SearchReqDto searchReqDto) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date selDate = format.parse(searchReqDto.getSel_date());
        Date selDate2 = format.parse(searchReqDto.getSel_date2());

        log.info("sel_date : "+selDate+" sel_date2 : "+selDate2);

        // java.sql.Date로 변환
        java.sql.Date sqlSelDate = new java.sql.Date(selDate.getTime());
        java.sql.Date sqlSelDate2 = new java.sql.Date(selDate2.getTime());

        log.info("type: "+type+" sel_date : "+sqlSelDate+" sel_date2 : "+sqlSelDate2);
        //return "type : "+type+" "+searchReqDto.toString();
        return null;
    }
}
