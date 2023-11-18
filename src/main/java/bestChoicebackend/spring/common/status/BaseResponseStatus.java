package bestChoicebackend.spring.common.status;

import org.springframework.http.HttpStatus;

public interface BaseResponseStatus {

    boolean getIsSuccess();
    HttpStatus getCode();
    String getMessage();
}
