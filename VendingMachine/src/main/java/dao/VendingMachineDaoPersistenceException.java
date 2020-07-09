package dao;

public class VendingMachineDaoPersistenceException extends Exception {
    public VendingMachineDaoPersistenceException(String message) {
        super(message);
    }

    public VendingMachineDaoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
