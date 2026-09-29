package playerRoles;

/**
 * Represents the base blueprint for all players in the soccer team.
 * Abstract classes are declared using the {@code abstract} keyword to serve as base templates.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public abstract class Player {

    /** The full name of the player. */
    private String name;

    /**
     * Constructs a new {@link Player}.
     * Note: While abstract methods cannot have constructors, abstract classes themselves
     * can have constructors to initialize their attributes via subclasses.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name.
     * @return The current name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Demonstrates mixed content: These classes can contain both abstract methods
     * and fully implemented methods.
     */
    public void train() {
        System.out.println(this.name + " completes a standard fitness session.");
    }

    /**
     * Calculates the financial bonus based on field performance.
     * How do we calculate this? Goals? Saves? We don't know at this generic level!
     * Therefore, it is declared without a body (no implementation) to define a required behavior for subclasses.
     *
     * @return The calculated financial bonus.
     */
    public abstract double calculateBonus();
}
