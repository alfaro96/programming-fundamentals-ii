package playerRoles;

/**
 * Represents a generic player in our football simulation.
 * <p>
 * This is the base class where the core logic (and potential errors) originate.
 */
public class Player {

    /**
     * Calculates the base fitness score of the player.
     * <p>
     * This method performs a division. It uses {@code throws} to warn 
     * that a division by zero could occur if the player hasn't played any matches.
     *
     * @param totalMinutes the total minutes played in the season
     * @param matchesPlayed the number of matches played (must not be zero)
     * @return the calculated fitness score
     * @throws ArithmeticException if {@code matchesPlayed} is zero.
     */
    public int calculateFitness(int totalMinutes, int matchesPlayed) throws ArithmeticException {

        // The exception physically originates here if matchesPlayed is 0
        return totalMinutes / matchesPlayed;
    }
}