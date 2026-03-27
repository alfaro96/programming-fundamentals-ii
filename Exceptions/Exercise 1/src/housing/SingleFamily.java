package housing;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;

/**
 * Represents a single-family home.
 *
 * <p>Extends {@link Housing} and allows comparison based on taxes and class name.
 * Validates both price (delegated to {@link Housing}) and basement size at
 * construction time via checked exceptions.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Housing
 * @see exceptions.InvalidPriceException
 * @see exceptions.InvalidSquareMetersException
 */
public class SingleFamily extends Housing implements Comparable<SingleFamily> {

    /** The number of floors in the house. Must be positive. */
    private int numberOfFloors;

    /** The size of the basement in square meters. Must be non-negative. */
    private double basementSize;

    /**
     * Constructs a new {@link SingleFamily} home.
     *
     * @param price The base price of the home in euros.
     * @param numberOfFloors The number of floors.
     * @param basementSize The size of the basement in square meters.
     * @throws InvalidPriceException If {@code price} is strictly negative.
     * @throws InvalidSquareMetersException If {@code basementSize} is strictly negative.
     */
    public SingleFamily(double price, int numberOfFloors, double basementSize)
            throws InvalidPriceException, InvalidSquareMetersException {
        super(price);
        if (basementSize < 0) {
            throw new InvalidSquareMetersException("basementSize", basementSize);
        }
        this.numberOfFloors = numberOfFloors;
        this.basementSize = basementSize;
    }

    /**
     * Calculates the tax for a single-family home.
     * The tax is fixed at 20% of the property's base price.
     *
     * @return The calculated tax in euros.
     */
    @Override
    public double tax() {
        return this.getPrice() * 0.20;
    }

    /**
     * Compares this single-family home to another based on their tax amount.
     * If the taxes are equal, it resolves the tie by comparing class names alphabetically.
     *
     * @param other The other {@link SingleFamily} to compare against.
     * @return A negative integer, zero, or a positive integer as this object is less than,
     *         equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(SingleFamily other) {
        int taxComparison = Double.compare(this.tax(), other.tax());
        if (taxComparison != 0) {
            return taxComparison;
        }
        // Tie-breaker: compare by class name
        return this.getClass().getSimpleName().compareTo(other.getClass().getSimpleName());
    }

    /**
     * Returns a string representation of the single-family home.
     *
     * @return A descriptive string containing price, floors, basement size, and tax.
     */
    @Override
    public String toString() {
        return "SingleFamily [Price = " + this.getPrice() + " euros, Floors = " + this.numberOfFloors
                + ", Basement = " + this.basementSize + " m², Tax = " + this.tax() + " euros]";
    }
}