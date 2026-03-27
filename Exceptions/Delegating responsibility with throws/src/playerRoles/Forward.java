package playerRoles;

/**
 * Represents a forward player, inheriting from {@link Player}.
 */
public class Forward extends Player {

    /**
     * Calculates the total attacking score of the forward.
     * <p>
     * This method relies on {@link Player#calculateFitness(int, int)}. 
     * Instead of using a try-catch block here, it delegates the responsibility 
     * to whoever calls this method by adding {@code throws} to its signature.
     *
     * @param goals the number of goals scored
     * @param totalMinutes the total minutes played
     * @param matchesPlayed the number of matches played
     * @return the total attacking score
     * @throws ArithmeticException if {@code matchesPlayed} is zero (passed from {@link Player}).
     */
    public int getAttackingScore(int goals, int totalMinutes, int matchesPlayed) throws ArithmeticException {
        // We call the dangerous method. We don't catch the error here; 
        // we just pass the warning up the chain.
        int baseFitness = calculateFitness(totalMinutes, matchesPlayed);

        return baseFitness + (goals * 10);
    }
}
