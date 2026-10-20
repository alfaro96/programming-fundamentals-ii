package exceptions;

/**
 * Signals that a size in square meters, such as a basement, a garden or a plot, is negative.
 *
 * <p>It is a checked exception: every constructor that may throw it has to declare it, and
 * every caller has to catch it or declare it in turn.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class InvalidSquareMetersException extends Exception {

    /**
     * Constructs a new {@link InvalidSquareMetersException} whose message includes the name of
     * the attribute and its invalid value.
     *
     * @param attribute the name of the attribute that was given a negative size.
     * @param squareMeters the negative size, in square meters.
     */
    public InvalidSquareMetersException(String attribute, double squareMeters) {
        super("Invalid " + attribute + ": " + squareMeters + " square meters. It cannot be negative.");
    }
}
