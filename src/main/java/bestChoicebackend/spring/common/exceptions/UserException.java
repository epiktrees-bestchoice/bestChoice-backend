package bestChoicebackend.spring.common.exceptions;

import bestChoicebackend.spring.common.status.UserResponseStatus;


public class UserException extends RuntimeException{

    private UserResponseStatus status;


    public UserException(UserResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public UserResponseStatus getStatus() {
        return this.status;
    }

    public void setStatus(final UserResponseStatus status) {
        this.status = status;
    }
}
