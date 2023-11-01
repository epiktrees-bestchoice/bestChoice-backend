package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.exception.BaseException;
import bestChoicebackend.spring.exception.BaseResponseStatus;
import bestChoicebackend.spring.repository.ProductDao;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    private static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public static boolean isDateFormatValid(String date) {
        // Use a regular expression to check if the date matches the format "YYYY-MM-DD"
        return Pattern.matches(DATE_FORMAT_REGEX, date);
    }

    public Page<Accommodation> GetProductWithCondition(String type, SearchReqDto searchReqDto, Pageable pageable){
        // enum null 처리
        AccommodationType accommoType = AccommodationType.from(type);
        // Date 포멧팅을 어노테이션으로 domain에 적용할 수 있다.
        if(searchReqDto.getSel_date() == null              // 기준 날짜가 빈 경우
                || searchReqDto.getSel_date().isBlank()){  // 기준 날짜가 ""인 경우
            Date today = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String result = df.format(today);
            searchReqDto.setSel_date(result);

            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.add(Calendar.DATE, 1);
            searchReqDto.setSel_date2(df.format(cal.getTime()));
        }
        else if(!(isDateFormatValid(searchReqDto.getSel_date())  // 기준 날짜 형태가 맞지 않은 경우
                && isDateFormatValid(searchReqDto.getSel_date2()))){
            throw new BaseException(BaseResponseStatus.DATE_FORMAT_EXCEPTION);
        }

        List<Accommodation> accommodations = productDao.checkProduct(accommoType.getValue() ,searchReqDto);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), accommodations.size());
        return new PageImpl<>(accommodations.subList(start, end), pageable, accommodations.size());
    }
}
