package playerRoles;

/**
 * Represents a goalkeeper in the system.
 * Inherits common attributes and methods from {@link Player}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Goalkeeper extends Player {

    /** The number of successful saves made by the goalkeeper. */
    public int saves;

    /**
     * Constructs a new {@link Goalkeeper}.
     * Initializes both inherited attributes and its own specific attributes directly.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param saves The number of successful saves.
     */
    public Goalkeeper(String name, int age, String nationality, int saves) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.saves = saves;
    }
}
