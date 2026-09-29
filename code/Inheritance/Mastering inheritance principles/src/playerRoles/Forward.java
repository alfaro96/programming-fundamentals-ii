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
     * Default constructor required for further subclassing.
     */
    public Forward() {

    }

    /**
     * Constructs a new {@link Forward}.
     * Initializes both inherited attributes and its own specific attributes directly.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param goals The number of goals scored.
     */
    public Forward(String name, int age, String nationality, int goals) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.goals = goals;
    }
}
