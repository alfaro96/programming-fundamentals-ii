package playerRoles;

/**
 * Represents a professional player.
 * This class demonstrates static polymorphism through method overloading.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The name of the player. */
    private String name;

    /**
     * Constructs a new {@link Player}.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name.
     * @return The current name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Performs a generic training session.
     * This is the base version of the overloaded method.
     */
    public void train() {
        System.out.println(this.name + " is doing a general fitness session.");
    }

    /**
     * Performs a training session for a specific duration.
     * Overloaded version using an integer parameter.
     *
     * @param minutes The duration of the session in minutes.
     */
    public void train(int minutes) {
        System.out.println(this.name + " trains for " + minutes + " minutes.");
    }

    /**
     * Performs a specific training exercise.
     * Overloaded version using a {@code String} parameter.
     *
     * @param exercise The name of the specific exercise.
     */
    public void train(String exercise) {
        System.out.println(this.name + " practices " + exercise + " drills.");
    }
}