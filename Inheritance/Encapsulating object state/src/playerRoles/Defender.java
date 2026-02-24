package playerRoles;

/**
 * Represents a defender player in the system.
 * Implements getters and setters for its specific {@code private} attributes.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Defender extends Player {

    /** The number of successful tackles made by the defender. */
    private int tackles;

    /**
     * Constructs a new {@link Defender}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param tackles The number of successful tackles.
     */
    public Defender(String name, int age, String nationality, int tackles) {
        super(name, age, nationality);
        this.tackles = tackles;
    }

    /**
     * Gets the number of tackles.
     *
     * @return The current tackles.
     */
    public int getTackles() {
        return this.tackles;
    }

    /**
     * Sets the number of tackles.
     *
     * @param tackles The new amount of tackles.
     */
    public void setTackles(int tackles) {
        if (tackles >= 0) {
            this.tackles = tackles;
        }
    }
}
