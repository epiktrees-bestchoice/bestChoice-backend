package bestChoicebackend.spring.common.exceptions;

import bestChoicebackend.spring.common.status.BaseResponseStatus;
import bestChoicebackend.spring.common.status.ProductResponseStatus;


public class ProductException extends RuntimeException implements BaseException{

    private BaseResponseStatus status;


    public ProductException(ProductResponseStatus status) {
        super(status.getMessage());
//        this.printStackTrace();
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

//    public ProductResponseStatus getStatus() {
//        return this.status;
//    }
//
//    public void setStatus(final ProductResponseStatus status) {
//        this.status = status;
//    }
}
