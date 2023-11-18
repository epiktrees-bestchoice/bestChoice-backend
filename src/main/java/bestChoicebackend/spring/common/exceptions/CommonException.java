package bestChoicebackend.spring.common.exceptions;

import bestChoicebackend.spring.common.status.BaseResponseStatus;
import bestChoicebackend.spring.common.status.CommonResponseStatus;

// interface나 추상 클래스 만들자!
public class CommonException extends RuntimeException implements BaseException{

    private BaseResponseStatus status;


    public CommonException(CommonResponseStatus status) {
        super(status.getMessage());
        this.printStackTrace(); // 예외 상황 로깅하기 에서 제거할 예정
        this.status = status;
    }

    @Override
    public BaseResponseStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(BaseResponseStatus status) {
        this.status = status;
    }

//    public CommonResponseStatus getStatus() {
//        return this.status;
//    }
//
//    public void setStatus(final CommonResponseStatus status) {
//        this.status = status;
//    }
}
