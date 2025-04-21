package exceptions;

/**
 * Thrown when a specified element is not found in a list or collection.
 * <p>
 * This is an unchecked exception that extends {@link RuntimeException}.
 * It is typically used when an operation expects an element to exist, but it doesn't.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class ElementNotFoundException extends RuntimeException {

    /**
     * Returns a string representation of the exception.
     *
     * @return A message indicating that the element does not exist
     */
    @Override
    public String toString() {
        return super.toString() + " Error: The element does not exist.";
    }
}
