package bestChoicebackend.spring.common.handler;

import bestChoicebackend.spring.common.exceptions.BaseException;
import bestChoicebackend.spring.common.exceptions.CommonException;
import bestChoicebackend.spring.common.exceptions.ProductException;
import bestChoicebackend.spring.common.exceptions.UserException;
import bestChoicebackend.spring.common.responses.BaseResponse;
import bestChoicebackend.spring.common.status.CommonResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Controller나 RestController가 적용된 빈에서 발생하는 예외를 잡아 처리
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(value = {ProductException.class, UserException.class, CommonException.class})
    public BaseResponse baseHandlerException(BaseException baseException){
        log.error("Exception invoke, {} ", baseException.getStackTrace()[0]);
        return new BaseResponse(baseException.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseResponse methodArgumentNotValidHandlerException(MethodArgumentNotValidException m){
        // 특정한 예외 같은 것은 직접 주입할 수 있다.
        return new BaseResponse(CommonResponseStatus.POST_INVAILD_ARGUMENT);
    }

    // RuntimeException가 발생하는 예외가 발생하면 처리하는 메서드
    // 예측할 수 없는 최후의 예외가 발생한 경우,
    @ExceptionHandler(value = {RuntimeException.class})
    public BaseResponse handlerException(RuntimeException e, HttpServletRequest request){
        log.error("Advice 내 handleException 호출, {} {} {}", request.getRequestURL(), e.getMessage(), e.getStackTrace()[0]);
        return new BaseResponse(CommonResponseStatus.Null_POINT_EXCEPTION);
    }

    // 예외가 발생하면 status를 받아서 공통 형식으로 출력
//    @ExceptionHandler(value = {CommonException.class})
//    public BaseResponse commonHandlerException(CommonException commonException){
//        log.error("Common Exception, {} ", commonException.getStackTrace()[0]);
//        return new BaseResponse(commonException.getStatus());
//    }

//    @ExceptionHandler(value = {UserException.class})
//    public BaseResponse userHandlerException(UserException userException){
//        log.error("User Exception, {} ", userException.getStackTrace()[0]);
//        return new BaseResponse(userException.getStatus());
//    }
}
