package playerRoles;

/**
 * Represents a soccer player who plays in the forward position.
 * A forward is responsible for scoring goals.
 * <p>
 * This class includes player-specific attributes and a method to simulate scoring a goal.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Forward extends Player {

    /** The number of goals scored by the forward. */
    private int goals;

    /**
     * Constructs a forward player with the specified attributes.
     *
     * @param name The name of the forward.
     * @param age The age of the forward.
     * @param nationality The nationality of the forward.
     * @param goals The number of goals scored by the forward.
     */
    public Forward(String name, int age, String nationality, int goals) {
        super(name, age, nationality);
        this.goals = goals;
    }

    /**
     * Overrides the train method to provide specific training for a forward.
     * Focuses on shooting and finishing skills.
     */
    @Override
    protected void train() {
        System.out.println(getName() + " is practicing shooting and finishing!");
    }

    /**
     * Method that simulates the forward scoring a goal.
     * Increments the goal count and outputs a message.
     */
    public void score() {
        goals++;  // Increment the goals count
        System.out.println(getName() + " scores a goal! Total goals: " + goals);
    }

    /**
     * Returns a string representation of the forward's details.
     *
     * @return A string containing player details along with goals scored.
     */
    @Override
    public String toString() {
        return "Forward: " + getName() + " (" + getNationality() + "), Goals: " + goals;
    }
}
