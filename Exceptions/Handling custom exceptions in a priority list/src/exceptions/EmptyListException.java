package exceptions;

/**
 * Thrown when an attempt is made to remove an element from an empty list.
 * <p>
 * This is a checked exception and must be either caught or declared.
 * Commonly used to guard against invalid operations on data structures.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class EmptyListException extends Exception {

    /**
     * Returns a string representation of the exception.
     *
     * @return A message indicating that the list is empty
     */
    @Override
    public String toString() {
        return super.toString() + " Error: The list is empty.";
    }
}
