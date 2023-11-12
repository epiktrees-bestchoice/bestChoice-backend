package bestChoicebackend.spring.common.exceptions;

import bestChoicebackend.spring.common.status.ProductResponseStatus;


public class ProductException extends RuntimeException{

    private ProductResponseStatus status;


    public ProductException(ProductResponseStatus status) {
        super(status.getMessage());
        this.printStackTrace();
        this.status = status;
    }

    public ProductResponseStatus getStatus() {
        return this.status;
    }

    public void setStatus(final ProductResponseStatus status) {
        this.status = status;
    }
}
