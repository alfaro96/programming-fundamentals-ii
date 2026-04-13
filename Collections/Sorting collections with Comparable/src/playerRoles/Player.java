package playerRoles;
import java.util.Collections;

/**
 * Represents a professional football player in the simulation system.
 * <p>
 * This class implements the {@link Comparable} interface to establish a
 * <b>natural ordering</b> based on the performance score.
 * This allows collections of {@link Player} objects to be sorted
 * automatically by utilities such as {@link Collections.sort}.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Player implements Comparable<Player> {

    /**
     * The full name of the professional football player.
     */
    private String name;

    /**
     * The current skill level or performance score of the player.
     * Generally evaluated on a scale ranging from 0 to 100.
     */
    private int performanceScore;

    /**
     * Constructs a new {@link Player} instance with the specified data.
     *
     * @param name The full name of the player.
     * @param performanceScore The initial performance score (0-100).
     */
    public Player(String name, int performanceScore) {
        this.name = name;
        this.performanceScore = performanceScore;
    }

    /**
     * Compares this player with another based on their performance score.
     * <p>
     * The implementation follows an ascending order: a player with a higher score
     * is considered "greater" than one with a lower score.
     * </p>
     *
     * @param other The other player object to be compared with.
     * @return A negative integer, zero, or a positive integer as this player
     * is less than, equal to, or greater than the specified player.
     * @throws NullPointerException if the compared object is null.
     */
    @Override
    public int compareTo(Player other) {
        // Natural order: ascending by score
        return Integer.compare(this.performanceScore, other.performanceScore);
    }

    /**
     * Returns a string representation of the player.
     *
     * @return A formatted string that includes the name and the score.
     */
    @Override
    public String toString() {
        return String.format("%-15s | Score: %d", name, performanceScore);
    }

    /**
     * Gets the name of the player.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the performance score of the player.
     *
     * @return The current performance score.
     */
    public int getPerformanceScore() {
        return performanceScore;
    }
}