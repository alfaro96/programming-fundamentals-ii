package exceptions;

/**
 * Checked exception thrown when a square meter measurement is negative.
 *
 * <p>This exception is used to validate surface-area fields such as basement size,
 * garden size, or plot size. It must be explicitly declared and handled by all
 * constructors and methods that accept square-meter values.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Exception
 */
public class InvalidSquareMetersException extends Exception {

    /**
     * Constructs a new {@link InvalidSquareMetersException} with a descriptive message
     * that includes the invalid value and the name of the field that triggered the exception.
     *
     * @param field The name of the field whose value was invalid (e.g., {@code "basementSize"}).
     * @param squareMeters The negative square-meter value that caused the exception.
     */
    public InvalidSquareMetersException(String field, double squareMeters) {
        super("Invalid value for '" + field + "': " + squareMeters
                + " m². Square meters must be non-negative.");
    }
}
