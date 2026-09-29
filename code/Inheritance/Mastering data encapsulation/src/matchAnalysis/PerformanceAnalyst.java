package matchAnalysis;

import playerRoles.Player;

/**
 * Represents an analyst evaluating a player's performance.
 * Demonstrates access restrictions for a non-subclass in a DIFFERENT package.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class PerformanceAnalyst {

    /** The name of the analyst. */
    public String analystName;

    /**
     * Constructs a new {@link PerformanceAnalyst}.
     *
     * @param analystName The name of the analyst.
     */
    public PerformanceAnalyst(String analystName) {
        this.analystName = analystName;
    }

    /**
     * Attempts to evaluate the data of a given player, demonstrating visibility rules.
     *
     * @param target The player to analyze.
     */
    public void evaluatePlayer(Player target) {
        System.out.println("--- Analyst report by " + this.analystName + " ---");

        // Public: Accessible everywhere.
        System.out.println("Evaluating player: " + target.name);

        // Protected: Not accessible because Analyst is not a subclass and is in a different package.
        // System.out.println("Age: " + target.age);

        // Default: Not accessible outside the "playerRoles" package.
        // System.out.println("Nationality: " + target.nationality);

        // Private: Not accessible outside the Player class itself.
        // System.out.println("Medical: " + target.medicalCondition);
    }
}
