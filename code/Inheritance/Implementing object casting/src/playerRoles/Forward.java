package playerRoles;

/**
 * Represents a forward player in the system.
 * Contains specific methods that become inaccessible if referenced by a generic {@link Player} type,
 * requiring downcasting to be used.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends Player {

    /** The number of goals scored by the forward. */
    private int goals;

    /**
     * Constructs a new {@link Forward}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param goals The number of goals scored.
     */
    public Forward(String name, int age, String nationality, int goals) {
        super(name, age, nationality);
        this.goals = goals;
    }

    /**
     * Gets the number of goals.
     *
     * @return The current goals.
     */
    public int getGoals() { return this.goals; }

    /**
     * Sets the number of goals.
     *
     * @param goals The new amount of goals.
     */
    public void setGoals(int goals) { this.goals = goals; }

    /**
     * Method that simulates the forward scoring a goal.
     * Increments the goal count and outputs a message.
     */
    public void scoreGoal() {
        this.goals++;
        System.out.println(this.getName() + " scores a goal! Total goals: " + this.goals);
    }
}
