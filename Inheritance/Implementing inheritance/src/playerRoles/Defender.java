package playerRoles;

/**
 * Represents a defender player in the system.
 * Inherits common attributes and methods from {@link Player}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Defender extends Player {

    /** The number of successful tackles made by the defender. */
    public int tackles;

    /**
     * Constructs a new {@link Defender}.
     * Initializes both inherited attributes and its own specific attributes directly.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param tackles The number of successful tackles.
     */
    public Defender(String name, int age, String nationality, int tackles) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.tackles = tackles;
    }
}
