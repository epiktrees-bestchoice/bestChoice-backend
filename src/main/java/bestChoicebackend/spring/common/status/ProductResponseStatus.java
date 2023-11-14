package bestChoicebackend.spring.common.status;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductResponseStatus {
    // httpstatus는 code 대신 HttpsStatus 열거형 쓰는게 더 표준적

    DATA_NOT_FOUND(false,HttpStatus.INTERNAL_SERVER_ERROR,"존재하지 않는 상품입니다."),
    TYPE_NOT_FOUND(false,HttpStatus.NOT_FOUND,"존재하지 않는 타입입니다."),
    ACCOMMODATION_NOT_FOUND(false,HttpStatus.NOT_FOUND,"존재하지 않는 숙소입니다.");

    private final boolean isSuccess;
    private final HttpStatus code;
    private final String message;

    private ProductResponseStatus(boolean isSuccess, HttpStatus code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
