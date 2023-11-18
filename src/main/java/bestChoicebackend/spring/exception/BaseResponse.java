package bestChoicebackend.spring.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@Getter
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    private final HttpStatus code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public BaseResponse(T result) {
        this.isSuccess = BaseResponseStatusOld.SUCCESS.isSuccess();
        this.message = BaseResponseStatusOld.SUCCESS.getMessage();
        this.code = BaseResponseStatusOld.SUCCESS.getCode();
        this.result = result;
    }

    public BaseResponse(BaseResponseStatusOld status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }
    public BaseResponse(final Boolean isSuccess, final String message, final HttpStatus code, final T result) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
        this.result = result;
    }
}