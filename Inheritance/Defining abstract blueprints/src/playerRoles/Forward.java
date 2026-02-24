package playerRoles;

/**
 * Represents a concrete forward player.
 * Enforces the inheritance contract by implementing all abstract methods.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends FieldPlayer {

    /** The number of goals scored by the forward. */
    private int goals;

    /**
     * Constructs a new {@link Forward}.
     *
     * @param name The name of the player.
     * @param goals The number of goals scored.
     */
    public Forward(String name, int goals) {
        super(name);
        this.goals = goals;
    }

    /**
     * The contract: Subclasses must override and implement all inherited abstract methods.
     * Calculates the bonus specifically for a forward based on goals scored.
     *
     * @return The calculated financial bonus.
     */
    @Override
    public double calculateBonus() {
        return this.goals * 1000;
    }

    /**
     * Fulfilling the contract inherited from the intermediate {@link FieldPlayer} class.
     */
    @Override
    public void run() {
        System.out.println(getName() + " sprints towards the penalty box.");
    }
}
