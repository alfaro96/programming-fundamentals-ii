package playerRoles;

/**
 * Represents a forward player in our football simulation.
 * <p>
 * This class includes actions and statistics specific to an attacking player,
 * such as calculating their performance on the field.
 */
public class Forward {

    /**
     * Calculates the shooting accuracy of the forward based on goals and misses.
     * <p>
     * This method performs a mathematical division. To warn anyone using this
     * method that a division by zero could occur, we use the {@code throws}
     * keyword in the method signature
     *
     * @param goals the number of goals scored
     * @param missedShots the number of missed shots (must not be zero)
     * @return the calculated accuracy ratio
     * @throws ArithmeticException if {@code missedShots} is zero.
     */
    public int calculateAccuracy(int goals, int missedShots) throws ArithmeticException {
        return goals / missedShots;
    }
}
