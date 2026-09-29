package playerRoles;

/**
 * Represents a midfielder player in the system.
 * Implements getters and setters for its specific {@code private} attributes.
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
    public int getAssists() {
        return this.assists;
    }

    /**
     * Sets the number of assists.
     *
     * @param assists The new amount of assists.
     */
    public void setAssists(int assists) {
        if (assists >= 0) {
            this.assists = assists;
        }
    }
}
