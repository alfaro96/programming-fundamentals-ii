/**
 * Represents a professional player in the system.
 * This version uses a constructor to ensure all attributes are initialized at creation.
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
     * Constructs a new {@link Player} with the specified details.
     *
     * @param initialName The full name of the player.
     * @param initialAge The current age in years.
     * @param initialNationality The country of origin.
     */
    public Player(String initialName, int initialAge, String initialNationality) {
        name = initialName;
        age = initialAge;
        nationality = initialNationality;
    }

    /**
     * Performs a signature celebration.
     */
    public void celebrate() {
        System.out.println(name + " performs a signature jump to celebrate!");
    }

    /**
     * Simulates a training session.
     *
     * @param exercise The name of the specific drill.
     * @param minutes The duration of the training session.
     */
    public void train(String exercise, int minutes) {
        System.out.println(name + " is training " + exercise + " for " + minutes + " minutes.");
    }

    /**
     * Calculates the time remaining until a specific age is reached.
     *
     * @param targetAge The age to compare against.
     * @return The number of years until the target age is reached.
     */
    public int calculateYearsToAge(int targetAge) {
        return targetAge - age;
    }
}
