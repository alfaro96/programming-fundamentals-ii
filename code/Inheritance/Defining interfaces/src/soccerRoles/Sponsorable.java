package soccerRoles;

/**
 * Defines a contract for entities that can receive brand sponsorships.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public interface Sponsorable {

    /**
     * Calculates the monetary value of the sponsorship.
     *
     * @return The sponsorship amount in euros.
     */
    int calculateSponsorship();
}
