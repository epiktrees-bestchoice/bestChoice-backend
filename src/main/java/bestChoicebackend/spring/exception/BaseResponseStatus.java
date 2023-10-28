package bestChoicebackend.spring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {
    // httpstatus는 code 대신 HttpsStatus 열거형 쓰는게 더 표준적

    SUCCESS(true, HttpStatus.OK, "요청에 성공하였습니다."),
    POST_USERS_EMPTY_EMAIL(false, HttpStatus.BAD_REQUEST, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, HttpStatus.BAD_REQUEST, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false, HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND, "값을 불러오는데 실패하였습니다."),
    DUPLICATED_EMAIL(false, HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false, HttpStatus.NOT_FOUND, "없는 아이디거나 비밀번호가 틀렸습니다."),
    DATA_NOT_FOUND(false,HttpStatus.INTERNAL_SERVER_ERROR,"존재하지 않는 상품입니다."),
    NULLPOINTER_EXCEPTION(false,HttpStatus.INTERNAL_SERVER_ERROR,"왜 기계에게 도전하려는가 휴~먼"),
    INSERT_EXCEPTION(false,HttpStatus.INTERNAL_SERVER_ERROR,"감히 기계를 가르치려하다니 무례하다 휴~먼"),
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "서버와의 연결에 실패하였습니다."),
    MODIFY_FAIL_USERNAME(false, HttpStatus.INTERNAL_SERVER_ERROR, "유저네임 수정 실패"),
    DELETE_FAIL_USERNAME(false, HttpStatus.INTERNAL_SERVER_ERROR, "유저 삭제 실패"),
    DELETE_FAIL_OPTION(false, HttpStatus.INTERNAL_SERVER_ERROR, "옵션 삭제 실패"),
    DELETE_FAIL_PRODUCT(false, HttpStatus.INTERNAL_SERVER_ERROR, "상품 삭제 실패"),
    DELETE_FAIL_IMAGE(false, HttpStatus.INTERNAL_SERVER_ERROR, "이미지 삭제 실패");

    private final boolean isSuccess;
    private final HttpStatus code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, HttpStatus code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}