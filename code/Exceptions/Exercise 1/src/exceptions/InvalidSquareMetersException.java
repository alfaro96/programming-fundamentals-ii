package exceptions;

/**
 * Signals that a size in square meters, such as a basement, a garden or a plot, is negative.
 *
 * <p>It is an unchecked exception, since a negative size is a mistake in the code that calls
 * the constructor, which should check the value first. It extends {@link RuntimeException}, so
 * the constructors that throw it do not have to declare it, although a caller can still catch
 * it.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class InvalidSquareMetersException extends RuntimeException {

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
