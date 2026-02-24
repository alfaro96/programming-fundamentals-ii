package playerRoles;

/**
 * Represents a professional player in the system.
 * Demonstrates strict encapsulation by keeping all fields {@code private} and providing
 * controlled access through getters and setters.
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
     * Default constructor for the {@link Player} class.
     */
    public Player() {
        this.name = "Unknown";
        this.age = 0;
        this.nationality = "Unknown";
    }

    /**
     * Parameterized constructor for the {@link Player} class.
     * Initializes the private attributes directly.
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
    public String getName() {
        return this.name;
    }

    /**
     * Sets the player's name.
     *
     * @param name The new name to assign.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player's age.
     *
     * @return The current age in years.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the player's age.
     * Demonstrates data validation: prevents negative ages.
     *
     * @param age The new age to assign.
     */
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Error: Age cannot be negative. Value not updated.");
        }
    }

    /**
     * Gets the player's nationality.
     *
     * @return The current nationality.
     */
    public String getNationality() {
        return this.nationality;
    }

    /**
     * Sets the player's nationality.
     *
     * @param nationality The new nationality to assign.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}