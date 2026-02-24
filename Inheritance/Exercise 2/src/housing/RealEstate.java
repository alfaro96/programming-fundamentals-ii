package housing;

/**
 * Manages a collection of housing properties.
 * Provides functionality to store, count, and analyze different types of homes.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class RealEstate {

    /** Array storing the housing objects. */
    private Housing[] housings;

    /** Tracks the current number of inserted housings. */
    private int counter;

    /**
     * Constructs a new {@link RealEstate} manager with a specified capacity.
     *
     * @param n The maximum number of housing properties this real estate can hold.
     */
    public RealEstate(int n) {
        this.housings = new Housing[n];
        this.counter = 0;
    }

    /**
     * Adds a new housing object to the real estate catalog.
     *
     * @param housing The housing property to add.
     */
    public void addHousing(Housing housing) {
        if (this.counter < this.housings.length) {
            this.housings[this.counter] = housing;
            this.counter++;
        } else {
            System.out.println("Error: Real Estate capacity is full.");
        }
    }

    /**
     * Counts how many {@link SingleFamily} homes are present that are not chalets.
     * Uses the {@code instanceof} operator to verify specific object types.
     *
     * @return The count of standard {@link SingleFamily} homes.
     */
    public int countNonChaletSingleFamily() {
        int count = 0;
        for (int i = 0; i < this.counter; i++) {
            // Must be a SingleFamily, but specifically not a Chalet subclass
            if (this.housings[i] instanceof SingleFamily && !(this.housings[i] instanceof Chalet)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Determines which housing object in the catalog has the highest tax.
     * Demonstrates polymorphism by calling the overridden {@link Housing#tax} method on generic Housing references.
     *
     * @return The {@link Housing} object with the highest calculated tax, or null if empty.
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
