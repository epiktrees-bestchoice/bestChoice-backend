package bestChoicebackend.spring.common.exceptions;

import bestChoicebackend.spring.common.status.BaseResponseStatus;

public interface BaseException {


    BaseResponseStatus getStatus();
    void setStatus(final BaseResponseStatus status);
    StackTraceElement[] getStackTrace();


}
