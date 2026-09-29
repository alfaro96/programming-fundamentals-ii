package playerRoles;

/**
 * Represents a goalkeeper.
 * Provides a different specific implementation for the same inherited method.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Goalkeeper extends Player {

    /**
     * Constructs a new {@link Goalkeeper}.
     *
     * @param name The name of the player.
     */
    public Goalkeeper(String name) {
        super(name);
    }

    /**
     * Specifically implements the match behavior for a goalkeeper.
     * Demonstrates dynamic binding: same call, different result.
     */
    @Override
    public void playMatch() {
        System.out.println(this.getName() + " is guarding the net and making saves!");
    }
}
