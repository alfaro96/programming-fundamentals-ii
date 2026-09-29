package soccerRoles;

/**
 * Defines a contract for any entity that can give press interviews.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public interface Interviewable {

    /**
     * The default television network or media outlet where the
     * press conferences and interviews take place.
     */
    String DEFAULT_MEDIA_CHANNEL = "Global Sports TV";

    /**
     * Performs the action of speaking to the media or participating
     * in a public press conference.
     */
    void giveInterview();
}
