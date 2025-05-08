package playerRoles;

/**
 * Represents a soccer player who plays in the midfield position.
 * A midfielder is responsible for assisting in both offense and defense.
 * <p>
 * This class includes player-specific attributes and a method to simulate assisting a goal.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Midfielder extends Player {

    /** The number of assists made by the midfielder. */
    private int assists;

    /**
     * Constructs a midfielder player with the specified attributes.
     *
     * @param name The name of the midfielder.
     * @param age The age of the midfielder.
     * @param nationality The nationality of the midfielder.
     * @param assists The number of assists made by the midfielder.
     */
    public Midfielder(String name, int age, String nationality, int assists) {
        super(name, age, nationality);
        this.assists = assists;
    }

    /**
     * Overrides the train method to provide specific training for a midfielder.
     * Focuses on passing and ball control.
     */
    @Override
    protected void train() {
        System.out.println(getName() + " is working on passing and ball control!");
    }

    /**
     * Method that simulates the midfielder assisting a goal.
     * Increments the assist count and outputs a message.
     */
    public void assist() {
        assists++;
        System.out.println(getName() + " makes an assist! Total assists: " + assists);
    }

    /**
     * Returns a string representation of the midfielder's details.
     *
     * @return A string containing player details along with assists.
     */
    @Override
    public String toString() {
        return "Midfielder: " + getName() + " (" + getNationality() + "), Assists: " + assists;
    }
}
