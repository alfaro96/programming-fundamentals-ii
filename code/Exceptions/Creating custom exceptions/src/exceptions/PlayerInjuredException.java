package exceptions;

/**
 * A custom unchecked exception representing a physical injury.
 * Extends {@link RuntimeException} because injuries happen unexpectedly during runtime.
 */
public class PlayerInjuredException extends RuntimeException {

    /**
     * Constructor that passes our custom message to the parent class.
     *
     * @param message the detailed error message
     */
    public PlayerInjuredException(String message) {
        // We pass the string to the parent constructor using super()
        super(message);
    }
}
