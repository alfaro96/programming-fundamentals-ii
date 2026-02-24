package housing;

/**
 * Represents a chalet, which is a specific type of single-family home
 * that includes a garden.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Chalet extends SingleFamily {

    /** The size of the garden in square meters. */
    private double gardenSize;

    /**
     * Constructs a new {@link Chalet}.
     *
     * @param price The base price of the chalet.
     * @param numberOfFloors The number of floors.
     * @param basementSize The size of the basement in square meters.
     * @param gardenSize The size of the garden in square meters.
     */
    public Chalet(double price, int numberOfFloors, double basementSize, double gardenSize) {
        super(price, numberOfFloors, basementSize);
        this.gardenSize = gardenSize;
    }

    /**
     * Returns a string representation of the chalet.
     *
     * @return A descriptive string detailing the chalet properties.
     */
    @Override
    public String toString() {
        return "Chalet [Price = " + this.getPrice() + " euros, Garden = " + this.gardenSize +
                " square meters, Tax = " + this.tax() + " euros]";
    }
}