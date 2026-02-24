package externalClub;

import playerRoles.Player;

/**
 * Represents a player playing for another club on loan.
 * Demonstrates access restrictions for a subclass in a DIFFERENT package.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class LoanedPlayer extends Player {

    /** The original club that owns the player's rights. */
    public String parentClub;

    /** Indicates if the player is forbidden from playing against their parent club. */
    public boolean hasFearClause;

    /**
     * Constructs a new {@link LoanedPlayer}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param medicalCondition The confidential medical status.
     * @param parentClub The original club.
     * @param hasFearClause Whether the fear clause is active.
     */
    public LoanedPlayer(String name, int age, String nationality, String medicalCondition, String parentClub, boolean hasFearClause) {
        this.name = name; // Public
        this.age = age;   // Protected
        // this.nationality = nationality; // Default
        // this.medicalCondition = medicalCondition; // Private
        this.parentClub = parentClub;
        this.hasFearClause = hasFearClause;
    }

    /**
     * Prints the loan details, actively demonstrating which inherited fields
     * are accessible across package boundaries.
     */
    public void printLoanDetails() {
        System.out.println("--- Loan details for " + this.name + " ---");

        // Public: Always accessible.
        System.out.println("Name: " + this.name);

        // Protected: Accessible because LoanedPlayer is a subclass.
        System.out.println("Age: " + this.age);

        // Default: Not accessible from a different package.
        // System.out.println("Nationality: " + this.nationality);

        // Private: Not accessible outside the Player class.
        // System.out.println("Medical Condition: " + this.medicalCondition);

        System.out.println("Parent club: " + this.parentClub);
        System.out.println("Has fear clause: " + this.hasFearClause);
    }
}