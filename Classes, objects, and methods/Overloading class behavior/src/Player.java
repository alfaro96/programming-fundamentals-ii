/**
 * Represents a professional player in the system.
 * This version uses overloading to provide flexibility in initialization and behavior.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The unique identifier for the player. */
    public String id;

    /** The full name of the player. */
    public String name;

    /** The current age of the player in years. */
    public int age;

    /** The country of origin or nationality of the player. */
    public String nationality;

    /**
     * Constructs a new {@link Player} with a specific identifier.
     *
     * @param id The unique identifier for the player.
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     */
    public Player(String id, String name, int age, String nationality) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    /**
     * Constructs a new {@link Player} without a specific identifier.
     * In this case, the identifier remains unassigned or {@code null}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     */
    public Player(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    /**
     * Performs a celebration.
     */
    public void celebrate() {
        System.out.println(this.name + " performs a signature jump!");
    }

    /**
     * Scores a goal and then trigger {@link #celebrate()}.
     */
    public void scoreGoal() {
        System.out.println(this.name + " has scored!");
        this.celebrate();
    }

    /**
     * Simulates training.
     *
     * @param exercise The training drill name.
     * @param minutes The duration in minutes.
     */
    public void train(String exercise, int minutes) {
        System.out.println(this.name + " trains " + exercise + " for " + minutes + " min.");
    }

    /**
     * Simulates a quick training session without a specific duration.
     *
     * @param exercise The training drill name.
     */
    public void train(String exercise) {
        System.out.println(this.name + " is doing a quick session of " + exercise + ".");
    }

    /**
     * Calculates the time remaining until a specific age is reached.
     *
     * @param targetAge The age to compare against.
     * @return The number of years until the target age is reached.
     */
    public int calculateYearsToAge(int targetAge) {
        return targetAge - this.age;
    }
}
