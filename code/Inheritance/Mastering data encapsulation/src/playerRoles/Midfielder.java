package playerRoles;

/**
 * Represents a midfielder player in the system.
 * Inherits common attributes and methods from {@link Player}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Midfielder extends Player {

    /** The number of assists provided by the midfielder. */
    public int assists;

    /**
     * Constructs a new {@link Midfielder}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param medicalCondition The confidential medical status.
     * @param assists The number of assists provided.
     */
    public Midfielder(String name, int age, String nationality, String medicalCondition, int assists) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        // this.medicalCondition = medicalCondition;
        this.assists = assists;
    }
}
