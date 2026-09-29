package exceptions;

/**
 * A custom checked exception representing a failed transfer negotiation.
 * Extends {@link Exception} because this is a formal process and the compiler
 * must force the caller to handle potential rejections.
 */
public class TransferDeclinedException extends Exception {

    /**
     * Constructor that passes our custom message to the parent class.
     *
     * @param message the detailed error message
     */
    public TransferDeclinedException(String message) {
        // We pass the string to the parent constructor using super()
        super(message);
    }
}
