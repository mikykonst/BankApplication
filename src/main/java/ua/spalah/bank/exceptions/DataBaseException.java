package ua.spalah.bank.exceptions;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
