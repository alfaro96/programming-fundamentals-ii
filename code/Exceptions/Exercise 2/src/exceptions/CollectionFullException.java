package exceptions;

/**
 * Signals that the row of a century has no room for another antique.
 *
 * <p>It is a checked exception: every method that may throw it has to declare it, and every
 * caller has to catch it or declare it in turn.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class CollectionFullException extends Exception {

    /**
     * Constructs a new {@link CollectionFullException} whose message includes the full century.
     *
     * @param century the century, counted from 1, whose row is full.
     */
    public CollectionFullException(int century) {
        super("No room left for century " + century + ".");
    }
}
