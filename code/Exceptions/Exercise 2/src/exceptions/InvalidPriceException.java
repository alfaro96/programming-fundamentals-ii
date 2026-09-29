package exceptions;

/**
 * Checked exception thrown when an antique's price is negative.
 *
 * <p>This exception must be explicitly declared in the {@code throws} clause of any
 * constructor or method that validates a price, and must be caught or propagated
 * by all callers.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Exception
 */
public class InvalidPriceException extends Exception {

    /**
     * Constructs a new {@link InvalidPriceException} with a descriptive message
     * that includes the invalid value that triggered the exception.
     *
     * @param price The negative price value that caused the exception.
     */
    public InvalidPriceException(double price) {
        super("Invalid price: " + price + " euros. Price must be non-negative.");
    }
}