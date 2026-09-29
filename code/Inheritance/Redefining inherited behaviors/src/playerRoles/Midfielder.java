package playerRoles;

/**
 * Represents a midfielder player in the system.
 * Demonstrates method overriding.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Midfielder extends Player {

    /** The number of assists provided by the midfielder. */
    private int assists;

    /**
     * Constructs a new {@link Midfielder}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param assists The number of assists provided.
     */
    public Midfielder(String name, int age, String nationality, int assists) {
        super(name, age, nationality);
        this.assists = assists;
    }

    /**
     * Gets the number of assists.
     *
     * @return The current assists.
     */
    public int getAssists() { return this.assists; }

    /**
     * Sets the number of assists.
     *
     * @param assists The new amount of assists.
     */
    public void setAssists(int assists) { this.assists = assists; }

    /**
     * Provides a specific implementation for playing a match.
     * Demonstrates method overriding to tailor inherited behaviors.
     */
    @Override
    public void playMatch() {
        System.out.println(this.getName() + " controls the midfield and distributes long passes.");
    }
}
