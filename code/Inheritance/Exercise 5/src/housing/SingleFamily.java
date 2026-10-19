package housing;

/**
 * Represents a single-family home.
 * Extends {@link Housing} and can tell whether it pays more tax than another one.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class SingleFamily extends Housing {

    /** The number of floors in the house. */
    private int numberOfFloors;

    /** The size of the basement in square meters. */
    private double basementSize;

    /**
     * Constructs a new {@link SingleFamily} home.
     *
     * @param price The base price of the home.
     * @param numberOfFloors The number of floors.
     * @param basementSize The size of the basement in square meters.
     */
    public SingleFamily(double price, int numberOfFloors, double basementSize) {
        super(price);
        this.numberOfFloors = numberOfFloors;
        this.basementSize = basementSize;
    }

    /**
     * Calculates the tax for a single-family home.
     * The tax is fixed at 20% of the property's base price.
     *
     * @return The calculated tax.
     */
    @Override
    public double tax() {
        return this.getPrice() * 0.20;
    }

    /**
     * Checks whether this single-family home pays more tax than another one.
     *
     * @param other the other {@link SingleFamily} to compare against.
     * @return {@code true} if the tax of this home is greater than the tax of {@code other}.
     */
    public boolean paysMoreTaxThan(SingleFamily other) {
        return this.tax() > other.tax();
    }

    /**
     * Returns a string representation of the single-family home.
     *
     * @return A descriptive string containing price, floors, and basement size.
     */
    @Override
    public String toString() {
        return "SingleFamily [Price = " + this.getPrice() + " euros, Floors = " + this.numberOfFloors +
                ", Basement = " + this.basementSize + " square meters, Tax = " + this.tax() + " euros]";
    }
}
