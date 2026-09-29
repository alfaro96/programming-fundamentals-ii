package soccerRoles;

/**
 * Represents a team coach.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Coach implements Interviewable {

    /** The name of the coach. */
    private String name;

    /** The total years of coaching experience. */
    private int yearsOfExperience;

    /**
     * Constructs a new {@link Coach}.
     *
     * @param name The name of the coach.
     * @param yearsOfExperience The years of experience.
     */
    public Coach(String name, int yearsOfExperience) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Simulates the coach giving a post-match press conference, providing
     * tactical feedback to the default media channel.
     */
    @Override
    public void giveInterview() {
        System.out.println("Coach " + this.name + " speaks to " + DEFAULT_MEDIA_CHANNEL + ": 'The team played well tactically.'");
    }
}