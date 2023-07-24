package eshop.product.exception;

public class NoSuchOrderExistsException extends RuntimeException {



    private String message;

    public NoSuchOrderExistsException() {}

    public NoSuchOrderExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }

}
