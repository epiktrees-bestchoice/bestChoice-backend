package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.exception.BaseException;
import bestChoicebackend.spring.exception.BaseResponseStatus;
import bestChoicebackend.spring.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    private static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public static boolean isDateFormatValid(String date) {
        // Use a regular expression to check if the date matches the format "YYYY-MM-DD"
        return Pattern.matches(DATE_FORMAT_REGEX, date);
    }

    public List<AccommodationResDto> getJDBCProduct(){
        return productDao.checkJDBCProduct();
    }

    public Page<AccommodationResDto> getProductWithCondition(String type, SearchReqDto searchReqDto, Pageable pageable){
        // enum null 처리
        AccommodationType accommoType = AccommodationType.from(type);
        List<AccommodationResDto> accommodations = productDao.checkProduct(accommoType.getType() ,searchReqDto);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), accommodations.size());
        return new PageImpl<>(accommodations.subList(start, end), pageable, accommodations.size());
    }
}
