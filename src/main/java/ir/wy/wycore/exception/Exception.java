package ir.wy.wycore.exception;

import lombok.Getter;
import lombok.Setter;

public class Exception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private static boolean errorSavedAutomatically = true;

    public Exception(Throwable t) {
        super(t);
    }

    public Exception(String message, Throwable t) {
        this(t, message);
    }

    public Exception(Throwable t, String message) {
        super(message, t);
    }

    @Override
    public String getMessage() {
        return "LOG " + super.getMessage();
    }
}
