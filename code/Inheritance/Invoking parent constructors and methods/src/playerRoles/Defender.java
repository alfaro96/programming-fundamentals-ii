package playerRoles;

/**
 * Represents a defender player in the system.
 * This class demonstrates an explicit call to the parent's default constructor.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Defender extends Player {

    /** The number of successful tackles made by the defender. */
    public int tackles;

    /**
     * Constructs a new {@link Defender}.
     *
     * @param tackles The number of successful tackles.
     */
    public Defender(int tackles) {
        // Explicit call: Calling the parent's parameterless constructor manually.
        super();
        this.tackles = tackles;
        System.out.println("[Defender] Explicit parent default constructor called.");
    }
}
