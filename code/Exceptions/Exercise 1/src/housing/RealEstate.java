package housing;

/**
 * Manages a collection of housing properties.
 *
 * <p>Stores, counts and analyzes different types of homes in an array of fixed size.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class RealEstate {

    /** Array storing the housing objects. */
    private Housing[] housings;

    /** The number of housing objects inserted so far, and the position of the next one. */
    private int counter;

    /**
     * Constructs a new {@link RealEstate} with room for {@code n} housing properties.
     *
     * @param n the maximum number of housing properties it can hold.
     */
    public RealEstate(int n) {
        this.housings = new Housing[n];
        this.counter = 0;
    }

    /**
     * Adds a new housing property to the catalog, if there is room for it.
     *
     * @param housing the housing property to add.
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
     * Counts how many {@link SingleFamily} homes are not chalets.
     *
     * @return the number of {@link SingleFamily} homes that are not {@link Chalet} objects.
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
     * Determines which housing property in the catalog has the highest tax.
     *
     * @return the {@link Housing} with the highest tax, or {@code null} if there is none.
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
