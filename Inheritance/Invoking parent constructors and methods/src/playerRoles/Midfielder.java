package playerRoles;

/**
 * Represents a midfielder player in the system.
 * This class demonstrates the implicit call to the parent's default constructor.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Midfielder extends Player {

    /** The number of assists provided by the midfielder. */
    private int assists;

    /**
     * Constructs a new {@link Midfielder}.
     * Because there is no explicit call to super(), Java will implicitly call the parent's default constructor.
     *
     * @param assists The number of assists provided.
     */
    public Midfielder(int assists) {
        // Implicit call: We omit super(), so Java automatically injects super() here.
        this.assists = assists;
        System.out.println("[Midfielder] Implicit parent constructor called.");
    }
}
