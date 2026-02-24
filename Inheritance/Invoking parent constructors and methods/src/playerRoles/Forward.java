package playerRoles;

/**
 * Represents a forward player in the system.
 * This class demonstrates the use of the super keyword to invoke a specific parent constructor.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends Player {

    /** The number of goals scored by the forward. */
    private int goals;

    /**
     * Constructs a new {@link Forward}.
     * Demonstrates the placement rule: super(arguments) must be the very first statement inside the subclass constructor.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param goals The number of goals scored.
     */
    public Forward(String name, int age, String nationality, int goals) {
        // Placement rule: super(arguments) must be the exact first statement.
        super(name, age, nationality);

        this.goals = goals;
        System.out.println("[Forward] Specific attributes initialized.");
    }
}
