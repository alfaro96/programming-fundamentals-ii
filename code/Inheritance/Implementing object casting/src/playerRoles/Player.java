package playerRoles;

/**
 * Represents a professional player in the system.
 * Acts as the general superclass. It is a safe target for upcasting, as any subclass
 * is always a valid instance of {@link Player}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The full name of the player. */
    private String name;

    /** The current age of the player in years. */
    private int age;

    /** The country of origin or nationality of the player. */
    private String nationality;

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
     * Gets the player's name.
     *
     * @return The current name of the player.
     */
    public String getName() { return this.name; }

    /**
     * Sets the player's name.
     * @param name The new name to assign.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the player's age.
     *
     * @return The current age in years.
     */
    public int getAge() { return this.age; }

    /**
     * Sets the player's age.
     *
     * @param age The new age to assign.
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Gets the player's nationality.
     *
     * @return The current nationality.
     */
    public String getNationality() { return this.nationality; }

    /**
     * Sets the player's nationality.
     *
     * @param nationality The new nationality to assign.
     */
    public void setNationality(String nationality) { this.nationality = nationality; }
}