package bestChoicebackend.spring.common.responses;

import bestChoicebackend.spring.common.status.ProductResponseStatus;
import bestChoicebackend.spring.common.status.CommonResponseStatus;
import bestChoicebackend.spring.common.status.UserResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@Getter
public class BaseResponse<T> {
    // Http Response의 일관성을 높이자

    @JsonProperty("isSuccess") // json 객체 내의 Key 값 설정
    private final Boolean isSuccess;
    private final String message;
    private final HttpStatus code;
    @JsonInclude(JsonInclude.Include.NON_NULL) // json을 만들 때 null인 객체는 제외한다.
    private T result;

    public BaseResponse(T result) {
        // 응답에 성공하고 컨텐츠가 있는 경우, create, update, read 경우
        this.isSuccess = CommonResponseStatus.SUCCESS.isSuccess();
        this.message = CommonResponseStatus.SUCCESS.getMessage();
        this.code = CommonResponseStatus.SUCCESS.getCode();
        this.result = result;
    }

    public BaseResponse() {
        // 응답에 성공하고 컨텐츠가 없는 경우, delete 같은 경우
        this.isSuccess = CommonResponseStatus.NO_CONTENT.isSuccess();
        this.message = CommonResponseStatus.NO_CONTENT.getMessage();
        this.code = CommonResponseStatus.NO_CONTENT.getCode();
    }

    public BaseResponse(CommonResponseStatus status) {
        // 예외 발생한 경우
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    public BaseResponse(ProductResponseStatus status) {
        // 예외 발생한 경우
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    public BaseResponse(UserResponseStatus status) {
        // 예외 발생한 경우
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
