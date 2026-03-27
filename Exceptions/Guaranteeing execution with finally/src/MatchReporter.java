/**
 * Simulates a system that saves match statistics to an external database.
 * <p>
 * This class demonstrates a resource that needs to be carefully managed:
 * it must be opened before use, and strictly closed after use to free up memory.
 */
public class MatchReporter {

    /**
     * Simulates opening a connection to the database.
     */
    public void openConnection() {
        System.out.println("Reporter: Opening secure connection to the database.");
    }

    /**
     * Attempts to save the match statistics to the database.
     *
     * @param goalsScored the number of goals to record
     * @throws IllegalArgumentException if the goals are negative
     */
    public void saveStats(int goalsScored) {
        if (goalsScored < 0) {
            throw new IllegalArgumentException("Database error: Cannot save negative goals!");
        }

        System.out.println("Reporter: Match statistics saved successfully.");
    }

    /**
     * Simulates closing the database connection and releasing resources.
     * This method MUST be called to prevent memory leaks.
     */
    public void closeConnection() {
        System.out.println("Reporter: Closing database connection and releasing resources.");
    }
}