package exceptions;

/**
 * Thrown when an attempt is made to add an element to a list that is already full.
 * <p>
 * This is a checked exception and must be declared or handled.
 * Typically used in fixed-size data structures like {@link prioritylist.PriorityList}.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class ListFullException extends Exception {

    /**
     * Returns a string representation of the exception.
     *
     * @return A message indicating that the list is full
     */
    @Override
    public String toString() {
        return super.toString() + " Error: The list is full.";
    }
}
