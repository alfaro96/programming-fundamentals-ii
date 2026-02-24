package playerRoles;

/**
 * Represents a goalkeeper in the system.
 * Demonstrates method overriding.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Goalkeeper extends Player {

    /** The number of successful saves made by the goalkeeper. */
    private int saves;

    /**
     * Constructs a new {@link Goalkeeper}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param saves The number of successful saves.
     */
    public Goalkeeper(String name, int age, String nationality, int saves) {
        super(name, age, nationality);
        this.saves = saves;
    }

    /**
     * Gets the number of saves.
     *
     * @return The current saves.
     */
    public int getSaves() { return this.saves; }

    /**
     * Sets the number of saves.
     *
     * @param saves The new amount of saves.
     */
    public void setSaves(int saves) { this.saves = saves; }

    /**
     * Provides a specific implementation for playing a match.
     * Demonstrates method overriding to tailor inherited behaviors.
     */
    @Override
    public void playMatch() {
        System.out.println(this.getName() + " dives to the corner to make a spectacular save!");
    }
}
