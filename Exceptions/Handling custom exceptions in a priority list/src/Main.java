import exceptions.*;
import prioritylist.*;

/**
 * Demonstrates the usage of the {@link PriorityList} class and custom exception handling in Java.
 * <p>
 * This example shows how to:
 * </p>
 * <ul>
 *     <li>Add elements to a priority-based list and handle the {@link ListFullException} if the list is full</li>
 *     <li>Remove a specific record, which may trigger an {@link ElementNotFoundException}</li>
 *     <li>Remove elements from the list until empty, handling {@link EmptyListException}</li>
 * </ul>
 * <p>
 * It highlights the distinction between checked exceptions, which must be handled or declared;
 * and unchecked exceptions, which may be ignored by the compiler, but still cause runtime errors.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * Entry point of the program. Demonstrates how custom exceptions work
     * by adding and removing records from a {@link PriorityList}.
     * <ul>
     *      <li>Catches {@link ListFullException} when the list is at capacity.</li>
     *      <li> Catches {@link EmptyListException} when trying to remove from an empty list.</li>
     *      <li> Does not catch {@link ElementNotFoundException} since it is unchecked.</li>
     * </p>
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        PriorityList list = new PriorityList(10);

        // Add records and handle possible checked exceptions
        try {
            list.add(new Record(3, "R1"));
            list.add(new Record(3, "R2"));
            list.add(new Record(5, "R3"));
            list.add(new Record(2, "R4"));
            list.add(new Record(4, "R5"));
        } catch (ListFullException e) {
            System.out.println(e);
        }

        // Remove a specific record, which is an unchecked exception, so not required to catch
        list.remove(new Record(7, "R2"));

        // Remove elements and handle possible checked exceptions
        try {
            list.remove();
            list.remove();
            list.remove();
            list.remove();
            list.remove();
            list.remove();
        } catch (EmptyListException e) {
            System.out.println(e);
        }
    }
}
