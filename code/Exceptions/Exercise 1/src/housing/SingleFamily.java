package housing;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;

/**
 * Represents a single-family home.
 *
 * <p>Extends {@link Housing} and can tell whether it pays more tax than another one. Its
 * constructor rejects a negative basement size.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see InvalidSquareMetersException
 */
public class SingleFamily extends Housing {

    /** The number of floors in the house. */
    private int numberOfFloors;

    /** The size of the basement in square meters. */
    private double basementSize;

    /**
     * Constructs a new {@link SingleFamily} home.
     *
     * @param price the base price of the home, in euros.
     * @param numberOfFloors the number of floors.
     * @param basementSize the size of the basement in square meters.
     * @throws InvalidPriceException if {@code price} is negative.
     * @throws InvalidSquareMetersException if {@code basementSize} is negative.
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
     * Calculates the tax for a single-family home, 20% of its base price.
     *
     * @return the calculated tax in euros.
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
     * @return a string containing its price, floors, basement size and tax.
     */
    @Override
    public String toString() {
        return "SingleFamily [Price = " + this.getPrice() + " euros, Floors = " + this.numberOfFloors
                + ", Basement = " + this.basementSize + " square meters, Tax = " + this.tax() + " euros]";
    }
}
