/**
 * Represents a professional player in the system.
 * This version uses {@code static} to manage class-level resources and
 * {@code this} for instance-level clarity.
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
     * A class-level counter shared by all instances to track the total
     * number of {@link Player} objects created.
     */
    public static int playerCount = 0;

    /**
     * Constructs a new {@link Player}, initializing its fields and
     * incrementing the shared {@link #playerCount}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     */
    public Player(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;

        // Incrementing the static counter every time a constructor is called
        Player.playerCount++;
    }

    /**
     * Static method that reports the current class-level registry status.
     * Note that this method cannot access {@link #name} because it is not
     * tied to a specific instance.
     */
    public static void displayGlobalCount() {
        System.out.println("System registry: " + Player.playerCount + " players currently active.");

        // System.out.println("Current player: " + name); // Compilation error
        // Reason: Cannot make a static reference to the non-static field name.
    }

    /**
     * Instance method that combines object data with class-level data.
     * This demonstrates that instance methods can access {@code static} members.
     */
    public void displayRegistryInfo() {
        System.out.println("Player: " + this.name + " | Total players in system: " + Player.playerCount);
    }

    /**
     * Performs a celebration.
     */
    public void celebrate() {
        System.out.println(this.name + " performs a signature jump!");
    }

    /**
     * Scores a goal and triggers a celebration.
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
        System.out.println(this.name + " trains " + exercise + " for " + minutes + " minutes.");
    }

    /**
     * Calculates years to reach a target age.
     * The formula used is $targetAge - this.age$.
     *
     * @param targetAge The age to compare against.
     * @return Years remaining.
     */
    public int calculateYearsToAge(int targetAge) {
        return targetAge - this.age;
    }
}
