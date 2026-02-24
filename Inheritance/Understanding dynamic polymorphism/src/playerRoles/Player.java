package playerRoles;

/**
 * Represents a base player.
 * Defines the contract for dynamic polymorphism through method overriding.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The name of the player. */
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
     * Executes a generic match action.
     * This method is the target for dynamic binding.
     */
    public void playMatch() {
        System.out.println(this.name + " is participating in the match.");
    }

    /**
     * Gets the player's name.
     *
     * @return The current name.
     */
    public String getName() {
        return this.name;
    }
}