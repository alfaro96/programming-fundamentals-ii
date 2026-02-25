package soccerRoles;

/**
 * Represents the base blueprint for all players in the soccer team.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public abstract class Player {

    /** * The full name of the player. */
    private String name;

    /**
     * Constructs a new {@link Player}.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name.
     *
     * @return The current name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Calculates the financial bonus based on field performance.
     *
     * @return The calculated financial bonus.
     */
    public abstract int calculateBonus();
}
