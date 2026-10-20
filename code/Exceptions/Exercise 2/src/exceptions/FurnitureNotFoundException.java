package exceptions;

/**
 * Signals that there is no furniture dated in the year that was going to be deleted.
 *
 * <p>It is a checked exception: every method that may throw it has to declare it, and every
 * caller has to catch it or declare it in turn.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class FurnitureNotFoundException extends Exception {

    /**
     * Constructs a new {@link FurnitureNotFoundException} whose message includes the year.
     *
     * @param year the year with no furniture in the collection.
     */
    public FurnitureNotFoundException(int year) {
        super("No furniture dated in " + year + ".");
    }
}
