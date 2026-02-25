package soccerRoles;

/**
 * Represents a forward player on the soccer team.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends Player implements Interviewable, Sponsorable {

    /** * The number of goals scored by the forward during the season.
     */
    private int goals;

    /**
     * Initializes a new forward with their name and total goals.
     *
     * @param name The name of the player.
     * @param goals The number of goals scored.
     */
    public Forward(String name, int goals) {
        super(name);
        this.goals = goals;
    }

    /**
     * Calculates the player's financial bonus, granting 1000 euros per goal scored.
     *
     * @return The calculated financial bonus in euros.
     */
    @Override
    public int calculateBonus() {
        return this.goals * 1000;
    }

    /**
     * Performs the action of speaking to the media, expressing satisfaction
     * about the goals scored.
     */
    @Override
    public void giveInterview() {
        System.out.println("Forward " + getName() + " speaks to " + DEFAULT_MEDIA_CHANNEL + ": 'I am happy with the goals I scored today.'");
    }

    /**
     * Calculates the monetary value of the player's brand sponsorship,
     * granting 5000 euros per goal scored.
     *
     * @return The sponsorship amount in euros.
     */
    @Override
    public int calculateSponsorship() {
        return this.goals * 5000;
    }
}