package exceptions;

/**
 * Checked exception thrown when no furniture dated in a specific year is found
 * in the collection during a deletion operation.
 *
 * <p>This exception is thrown by {@code Collection.deleteFurniture()} when the
 * collection contains no {@code Furniture} objects matching the requested year.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Exception
 */
public class FurnitureNotFoundException extends Exception {

    /**
     * Constructs a new {@link FurnitureNotFoundException} with a descriptive message
     * that includes the year for which no matching furniture was found.
     *
     * @param year The year for which no furniture was found in the collection.
     */
    public FurnitureNotFoundException(int year) {
        super("No furniture dated " + year + " was found in the collection.");
    }
}
