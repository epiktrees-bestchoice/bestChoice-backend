package bestChoicebackend.spring.common;

import com.example.solcoupang.common.exceptions.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Controller나 RestController가 적용된 빈에서 발생하는 예외를 잡아 처리
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class, NullPointerException.class}) //RuntimeException나 NullPointerException가 발생하는 예외가 발생하면 처리하는 메서드
    public ResponseEntity<Map<String, String>> handlerException(RuntimeException e, HttpServletRequest request){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        log.error("Advice 내 handleException 호출, {} {}", request.getRequestURL(), e.getMessage());
        // 클라이언트에게 보낼 응답 메세지 만들기
        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, httpHeaders,httpStatus);
    }

    // 예외가 발생하면 status를 받아서 공통 형식으로 출력
    @ExceptionHandler(value = {BaseException.class})
    public BaseResponse baseHandlerException(BaseException baseException){
        return new BaseResponse(baseException.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseResponse methodArgumentNotValidHandlerException(MethodArgumentNotValidException m){
        // 특정한 예외 같은 것은 직접 주입할 수 있다.
        return new BaseResponse(BaseResponseStatus.POST_INVAILD_ARGUMENT);
    }
}
