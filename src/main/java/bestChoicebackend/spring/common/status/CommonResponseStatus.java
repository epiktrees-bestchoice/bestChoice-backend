package bestChoicebackend.spring.common.status;

import org.springframework.http.HttpStatus;

public enum CommonResponseStatus implements BaseResponseStatus {
    // 유효성 검사와 성공 응답 코드 관리
    SUCCESS(true, HttpStatus.OK, "요청에 성공하였습니다."),
    NO_CONTENT(true, HttpStatus.NO_CONTENT, "요청에 성공했지만, 컨텐츠는 없습니다."),
    INVALID_REQUEST_BODY(false, HttpStatus.BAD_REQUEST, "입력 형식을 확인해주세요."),
    POST_INVAILD_ARGUMENT(false, HttpStatus.BAD_REQUEST, "올바른 입력 형식이 아닙니다."),
    Null_POINT_EXCEPTION(false, HttpStatus.INTERNAL_SERVER_ERROR, "Null 값을 조회했습니다.");

    private final boolean isSuccess;
    private final HttpStatus code;
    private final String message;

    private CommonResponseStatus(boolean isSuccess, HttpStatus code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean getIsSuccess() {
        return isSuccess;
    }

    @Override
    public HttpStatus getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
