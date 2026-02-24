package playerRoles;

/**
 * Represents a specialized striker in the system.
 * This class demonstrates a class hierarchy that can be further extended.
 * It shows "full lineage" by inheriting fields and methods from all its superclasses ({@link Forward} and {@link Player}).
 * It is marked as {@code final}, meaning this class cannot be extended.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public final class Striker extends Forward {

    /** The specific shot power attribute for a striker. */
    public int shotPower;

    /**
     * Constructs a new {@link Striker}.
     * Initializes attributes inherited from Player, attributes inherited from Forward, 
     * and its own specific attributes.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param goals The number of goals scored.
     * @param shotPower The shot power specific to the striker.
     */
    public Striker(String name, int age, String nationality, int goals, int shotPower) {
        // Inherited from Player (level 0)
        this.name = name;
        this.age = age;
        this.nationality = nationality;

        // Inherited from Forward (level 1)
        this.goals = goals;

        // Specific to Striker (level 2)
        this.shotPower = shotPower;
    }
}
