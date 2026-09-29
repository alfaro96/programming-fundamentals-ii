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
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param medicalCondition The confidential medical status.
     * @param saves The number of goals scored.
     */
    public Goalkeeper(String name, int age, String nationality, String medicalCondition, int saves) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        // this.medicalCondition = medicalCondition;
        this.saves = saves;
    }
}
