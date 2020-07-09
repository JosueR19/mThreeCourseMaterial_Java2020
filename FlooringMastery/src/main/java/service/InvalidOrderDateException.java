package service;

public class InvalidOrderDateException extends Exception{
    public InvalidOrderDateException(String message) {
        super(message);
    }

    public InvalidOrderDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
