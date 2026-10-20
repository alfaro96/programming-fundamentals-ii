package exceptions;

/**
 * Signals that an antique has been given a negative price.
 *
 * <p>It is a checked exception: every constructor that may throw it has to declare it, and
 * every caller has to catch it or declare it in turn.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class InvalidPriceException extends Exception {

    /**
     * Constructs a new {@link InvalidPriceException} whose message includes the invalid price.
     *
     * @param price the negative price that was given.
     */
    public InvalidPriceException(double price) {
        super("Invalid price: " + price + " euros. It cannot be negative.");
    }
}
