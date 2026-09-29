package playerRoles;

/**
 * Represents a goalkeeper in the system.
 * This class demonstrates how to access a superclass method using the super keyword.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Goalkeeper extends Player {

    /** The number of successful saves made by the goalkeeper. */
    public int saves;

    /**
     * Constructs a new {@link Goalkeeper}.
     * Uses super(arguments) to initialize inherited private attributes.
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
     * Simulates saving a penalty.
     * After the save, it triggers the parent's celebration behavior by using the {@code super} keyword.
     */
    public void savePenalty() {
        System.out.println("An incredible penalty save has been made!");

        // Calls a parent method directly.
        super.celebrate();
    }
}
