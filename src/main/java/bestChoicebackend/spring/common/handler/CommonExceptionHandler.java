package bestChoicebackend.spring.common.handler;

import bestChoicebackend.spring.common.exceptions.CommonException;
import bestChoicebackend.spring.common.exceptions.ProductException;
import bestChoicebackend.spring.common.exceptions.UserException;
import bestChoicebackend.spring.common.responses.BaseResponse;
import bestChoicebackend.spring.common.status.CommonResponseStatus;
import bestChoicebackend.spring.common.status.UserResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Controller나 RestController가 적용된 빈에서 발생하는 예외를 잡아 처리
@Slf4j
public class CommonExceptionHandler {

    // 예외가 발생하면 status를 받아서 공통 형식으로 출력
    @ExceptionHandler(value = {CommonException.class})
    public BaseResponse baseHandlerException(CommonException commonException){
        log.error("Common Exception, {} ", commonException.getStackTrace()[0]);
        return new BaseResponse(commonException.getStatus());
    }

    @ExceptionHandler(value = {ProductException.class})
    public BaseResponse productHandlerException(ProductException productException){
        log.error("Product Exception, {} ", productException.getStackTrace()[0]);
        return new BaseResponse(productException.getStatus());
    }

    @ExceptionHandler(value = {UserException.class})
    public BaseResponse userHandlerException(UserException userException){
        log.error("User Exception, {} ", userException.getStackTrace()[0]);
        return new BaseResponse(userException.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseResponse methodArgumentNotValidHandlerException(MethodArgumentNotValidException m){
        // 특정한 예외 같은 것은 직접 주입할 수 있다.
        return new BaseResponse(CommonResponseStatus.POST_INVAILD_ARGUMENT);
    }

    @ExceptionHandler(value = {RuntimeException.class}) //RuntimeException나 NullPointerException가 발생하는 예외가 발생하면 처리하는 메서드
    public BaseResponse handlerException(RuntimeException e, HttpServletRequest request){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        log.error("Advice 내 handleException 호출, {} {} {}", request.getRequestURL(), e.getMessage(), e.getStackTrace()[0]);
//        // 클라이언트에게 보낼 응답 메세지 만들기
//        Map<String, String> map = new HashMap<>();
//        map.put("error type", httpStatus.getReasonPhrase());
//        map.put("code","400");
//        map.put("message", e.getMessage());
        //return new ResponseEntity<>(map, httpHeaders,httpStatus);
        return new BaseResponse(CommonResponseStatus.Null_POINT_EXCEPTION);
    }
}
