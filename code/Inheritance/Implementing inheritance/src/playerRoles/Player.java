package playerRoles;

/**
 * Represents a professional player in the system.
 * This class acts as the superclass, containing common attributes and behaviors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The full name of the player. */
    public String name;

    /** The current age of the player in years. */
    public int age;

    /** The country of origin or nationality of the player. */
    public String nationality;

    /**
     * Default constructor.
     * Required to allow subclasses to initialize inherited attributes directly.
     */
    public Player() {

    }

    /**
     * Constructs a new {@link Player}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     */
    public Player(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    /**
     * Performs a celebration.
     */
    public void celebrate() {
        System.out.println(this.name + " performs a signature jump!");
    }
}