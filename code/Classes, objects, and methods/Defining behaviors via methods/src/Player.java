/**
 * Represents a professional player in the system.
 * This class defines the attributes and various types of behaviors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The full name of the player. */
    public String name;

    /** The current age of the player in years. */
    public int age;

    /** The country of origin or nationality of the player. */
    public String nationality;

    /**
     * Performs a signature celebration.
     * This method uses internal state and does not require arguments.
     */
    public void celebrate() {
        System.out.println(name + " performs a signature jump to celebrate!");
    }

    /**
     * Simulates a training session.
     * This method requires external data to customize the behavior.
     *
     * @param exercise The name of the specific drill.
     * @param minutes The duration of the training session.
     */
    public void train(String exercise, int minutes) {
        System.out.println(name + " is training " + exercise + " for " + minutes + " minutes.");
    }

    /**
     * Calculates the time remaining until a specific age is reached.
     * This method processes data and returns a result to the caller.
     *
     * @param targetAge The age to compare against.
     * @return The number of years until the target age is reached.
     */
    public int calculateYearsToAge(int targetAge) {
        return targetAge - age;
    }
}
