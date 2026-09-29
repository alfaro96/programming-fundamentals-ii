package playerRoles;

/**
 * Represents a generic soccer player.
 * <p>
 * This abstract structure can serve as a base for more specific player roles such as {@link Forward} or {@link Midfielder}.
 * It typically includes shared attributes like name, age, and nationality.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public abstract class Player {

    /** The name of the player. */
    private String name;

    /** The age of the player. */
    private int age;

    /** The nationality of the player. */
    private String nationality;

    /**
     * Constructs a player with the given attributes.
     *
     * @param name The name of the player.
     * @param age The age of the player.
     * @param nationality The nationality of the player.
     */
    public Player(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the player's nationality.
     *
     * @return The player's nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Simulates the training process for the player.
     * This method is protected and can only be accessed within the same package or by subclasses.
     */
    protected void train() {
        System.out.println(name + " is training generally.");
    }
}
