package playerRoles;

/**
 * Represents a forward player.
 * Provides a specific implementation of the {@link Player#playMatch} method.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends Player {

    /**
     * Constructs a new {@link Forward}.
     *
     * @param name The name of the player.
     */
    public Forward(String name) {
        super(name);
    }

    /**
     * Specifically implements the match behavior for a forward.
     * Overrides the base method to provide adaptability.
     */
    @Override
    public void playMatch() {
        System.out.println(this.getName() + " is attacking and looking for a goal!");
    }
}
