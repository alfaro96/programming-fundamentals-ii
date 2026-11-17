package exceptions;

/**
 * Signals that a housing property has been given a negative price.
 *
 * <p>It is an unchecked exception, since a negative price is a mistake in the code that calls
 * the constructor, which should check the value first. It extends {@link RuntimeException}, so
 * the constructors that throw it do not have to declare it, although a caller can still catch
 * it.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class InvalidPriceException extends RuntimeException {

    /**
     * Constructs a new {@link InvalidPriceException} whose message includes the invalid price.
     *
     * @param price the negative price that was given.
     */
    public InvalidPriceException(double price) {
        super("Invalid price: " + price + " euros. It cannot be negative.");
    }
}
