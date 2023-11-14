package bestChoicebackend.spring.common.exceptions;

import bestChoicebackend.spring.common.status.CommonResponseStatus;


public class CommonException extends RuntimeException{

    private CommonResponseStatus status;


    public CommonException(CommonResponseStatus status) {
        super(status.getMessage());
//        this.printStackTrace();
        this.status = status;
    }

    public CommonResponseStatus getStatus() {
        return this.status;
    }

    public void setStatus(final CommonResponseStatus status) {
        this.status = status;
    }
}
