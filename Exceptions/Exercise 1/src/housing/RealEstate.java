package housing;

/**
 * Manages a collection of housing properties.
 *
 * <p>Provides functionality to store, count, and analyze different types of homes.
 * The internal array has a fixed capacity set at construction time.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Housing
 */
public class RealEstate {

    /** Array storing the housing objects. */
    private Housing[] housings;

    /** Tracks the current number of inserted housings. */
    private int counter;

    /**
     * Constructs a new {@link RealEstate} manager with a specified capacity.
     *
     * @param capacity The maximum number of housing properties this real estate can hold.
     */
    public RealEstate(int capacity) {
        this.housings = new Housing[capacity];
        this.counter = 0;
    }

    /**
     * Adds a new housing object to the real estate catalogue.
     * If the catalogue is already at full capacity, a warning is printed and the
     * property is not added.
     *
     * @param housing The housing property to add.
     */
    public void addHousing(Housing housing) {
        if (this.counter < this.housings.length) {
            this.housings[this.counter] = housing;
            this.counter++;
        } else {
            System.out.println("Error: Real estate catalogue is at full capacity.");
        }
    }

    /**
     * Counts how many {@link SingleFamily} homes are present that are not chalets.
     * Uses the {@code instanceof} operator to distinguish the exact runtime type.
     *
     * @return The count of standard {@link SingleFamily} homes (excluding {@link Chalet} instances).
     */
    public int countNonChaletSingleFamily() {
        int count = 0;
        for (int i = 0; i < this.counter; i++) {
            if (this.housings[i] instanceof SingleFamily && !(this.housings[i] instanceof Chalet)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Determines which housing property in the catalogue has the highest tax.
     *
     * <p>Demonstrates polymorphism: {@link Housing#tax()} is called on generic
     * {@link Housing} references and dispatches to the correct override at runtime.</p>
     *
     * @return The {@link Housing} object with the highest calculated tax,
     *         or {@code null} if the catalogue is empty.
     */
    public Housing getHighestTaxHousing() {
        if (this.counter == 0) {
            return null;
        }
        Housing maxHousing = this.housings[0];
        for (int i = 1; i < this.counter; i++) {
            if (this.housings[i].tax() > maxHousing.tax()) {
                maxHousing = this.housings[i];
            }
        }
        return maxHousing;
    }
}
