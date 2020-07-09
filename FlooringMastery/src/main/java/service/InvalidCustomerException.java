package service;

public class InvalidCustomerException extends Exception {
    public InvalidCustomerException(String message) {
        super(message);
    }

    public InvalidCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
