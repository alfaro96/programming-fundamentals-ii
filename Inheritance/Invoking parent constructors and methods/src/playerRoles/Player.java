package playerRoles;

/**
 * Represents a professional player in the system.
 * This class acts as the superclass and defines the common properties and behaviors.
 * All attributes are kept private to enforce encapsulation, meaning subclasses
 * must use the {@code super} keyword to initialize them.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The full name of the player. Kept private to enforce encapsulation. */
    private String name;

    /** The current age of the player in years. Kept private to enforce encapsulation. */
    private int age;

    /** The country of origin or nationality of the player. Kept private to enforce encapsulation. */
    private String nationality;

    /**
     * Default constructor for the {@link Player} class.
     * If a subclass omits the {@code super()} call, Java automatically calls this default constructor.
     */
    public Player() {
        System.out.println("[Player] Default constructor executed.");
        this.name = "Unknown";
        this.age = 0;
        this.nationality = "Unknown";
    }

    /**
     * Parameterized constructor for the {@link Player} class.
     * Subclasses can invoke this specific parent constructor using super(arguments).
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     */
    public Player(String name, int age, String nationality) {
        System.out.println("[Player] Parameterized constructor executed for: " + name);
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    /**
     * Performs a standard celebration.
     * This method can be overridden by subclasses, but its original logic can still
     * be accessed using super.celebrate().
     */
    public void celebrate() {
        System.out.println(this.name + " performs a standard signature jump!");
    }
}