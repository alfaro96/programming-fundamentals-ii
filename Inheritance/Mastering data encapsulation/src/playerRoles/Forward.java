package playerRoles;

/**
 * Represents a forward player in the system.
 * Inherits common attributes and methods from {@link Player}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends Player {

    /** The number of goals scored by the forward. */
    public int goals;

    /**
     * Constructs a new {@link Forward}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param medicalCondition The confidential medical status.
     * @param goals The number of goals scored.
     */
    public Forward(String name, int age, String nationality, String medicalCondition, int goals) {
        this.name = name; // Public
        this.age = age; // Protected
        this.nationality = nationality; // Default
        // this.medicalCondition = medicalCondition; // Private
        this.goals = goals;
    }
}
