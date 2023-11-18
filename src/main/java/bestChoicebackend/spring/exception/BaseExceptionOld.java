package bestChoicebackend.spring.exception;

public class BaseExceptionOld extends RuntimeException {

    private BaseResponseStatusOld status;

    public BaseExceptionOld(BaseResponseStatusOld status) {
        super(status.getMessage());
        this.printStackTrace();
        this.status = status;
    }

    public BaseResponseStatusOld getStatus() {
        return this.status;
    }

    public void setStatus(final BaseResponseStatusOld status) {
        this.status = status;
    }
}