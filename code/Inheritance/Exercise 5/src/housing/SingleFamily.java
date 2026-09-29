package housing;

/**
 * Represents a single-family home.
 * Extends {@link Housing} and allows comparison based on taxes and class name.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class SingleFamily extends Housing implements Comparable<SingleFamily> {

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
     * Compares this single-family home to another based on their tax amount.
     * If the taxes are equal, it resolves the tie by comparing the class names alphabetically.
     *
     * @param other The other {@link SingleFamily} to compare against.
     * @return A negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(SingleFamily other) {
        int taxComparison = Double.compare(this.tax(), other.tax());

        if (taxComparison != 0) {
            return taxComparison;
        }

        // Tie-breaker: Compare by class name (e.g., "Chalet" vs "SingleFamily")
        return this.getClass().getSimpleName().compareTo(other.getClass().getSimpleName());
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
