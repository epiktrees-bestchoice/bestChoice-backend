package bestChoicebackend.spring.common.status;

import org.springframework.http.HttpStatus;


public enum UserResponseStatus implements BaseResponseStatus {

    POST_INVAILD_ARGUMENT(false, HttpStatus.BAD_REQUEST, "올바른 입력 형식이 아닙니다."),
    POST_USERS_EMPTY_EMAIL(false, HttpStatus.BAD_REQUEST, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, HttpStatus.BAD_REQUEST, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false, HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND, "값을 불러오는데 실패하였습니다."),
    FAILED_TO_LOGIN(false, HttpStatus.NOT_FOUND, "없는 아이디거나 비밀번호가 틀렸습니다."),
    USER_NOT_FOUND(false,HttpStatus.UNAUTHORIZED,"회원이 아닌 유저입니다."),
    USER_FORBIDDEN(false,HttpStatus.FORBIDDEN,"요청에 권한이 없는 유저입니다."),
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "서버와의 연결에 실패하였습니다."),
    MODIFY_FAIL_USERNAME(false, HttpStatus.INTERNAL_SERVER_ERROR, "유저네임 수정 실패"),
    DELETE_FAIL_USERNAME(false, HttpStatus.INTERNAL_SERVER_ERROR, "유저 삭제 실패");

    private final boolean isSuccess;
    private final HttpStatus code;
    private final String message;

    private UserResponseStatus(boolean isSuccess, HttpStatus code, String message) {
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
