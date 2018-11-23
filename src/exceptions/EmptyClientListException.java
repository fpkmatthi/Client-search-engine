package exceptions;

public class EmptyClientListException extends Exception {
    public EmptyClientListException() {
    }

    public EmptyClientListException(String message) {
        super(message);
    }

    public EmptyClientListException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyClientListException(Throwable cause) {
        super(cause);
    }
}
