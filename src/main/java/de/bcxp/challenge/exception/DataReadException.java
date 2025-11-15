package de.bcxp.challenge.exception;

/**
 * Custom exception used to indicate problems while reading or parsing data files.
 *
 * <p>This exception helps separate data-loading errors from other types of
 * runtime failures, improving clarity and robustness in the application's
 * error-handling strategy.</p>
 */
public class DataReadException extends RuntimeException {

    /**
     * Creates a new exception with the given message.
     *
     * @param message description of the error
     */
    public DataReadException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the given message and underlying cause.
     *
     * @param message description of the error
     * @param cause the original exception that triggered this error
     */
    public DataReadException(String message, Throwable cause) {
        super(message, cause);
    }
}

