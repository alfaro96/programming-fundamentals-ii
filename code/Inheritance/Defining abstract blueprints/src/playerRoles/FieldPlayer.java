package playerRoles;

/**
 * Represents a generic field player.
 * Demonstrates the exception to the implementation contract.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public abstract class FieldPlayer extends Player {

    /**
     * Constructs a new {@link FieldPlayer}.
     *
     * @param name The name of the player.
     */
    public FieldPlayer(String name) {
        super(name);
    }

    /**
     * Another abstract method adding further requirements to the contract.
     */
    public abstract void run();
}
