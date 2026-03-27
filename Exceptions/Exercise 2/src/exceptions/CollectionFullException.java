package exceptions;

/**
 * Checked exception thrown when there is no space available in a given century's
 * row to add a new antique to the collection.
 *
 * <p>This exception is thrown by {@code Collection.addAntique()} when the row
 * corresponding to the antique's century is already at maximum capacity.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Exception
 */
public class CollectionFullException extends Exception {

    /**
     * Constructs a new {@link CollectionFullException} with a descriptive message
     * indicating which century is full.
     *
     * @param century The 1-based century number whose row has no remaining slots.
     */
    public CollectionFullException(int century) {
        super("The collection for century " + century + " is full. No more antiques can be added.");
    }
}
