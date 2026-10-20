package housing;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;

/**
 * Represents a chalet, a specific type of single-family home that includes a garden.
 *
 * <p>Its constructor rejects a negative garden size, on top of the checks of
 * {@link SingleFamily} and {@link Housing}.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see InvalidSquareMetersException
 */
public class Chalet extends SingleFamily {

    /** The size of the garden in square meters. */
    private double gardenSize;

    /**
     * Constructs a new {@link Chalet}.
     *
     * @param price the base price of the chalet, in euros.
     * @param numberOfFloors the number of floors.
     * @param basementSize the size of the basement in square meters.
     * @param gardenSize the size of the garden in square meters.
     * @throws InvalidPriceException if {@code price} is negative.
     * @throws InvalidSquareMetersException if {@code basementSize} or {@code gardenSize} is
     *         negative.
     */
    public Chalet(double price, int numberOfFloors, double basementSize, double gardenSize)
            throws InvalidPriceException, InvalidSquareMetersException {
        super(price, numberOfFloors, basementSize);
        if (gardenSize < 0) {
            throw new InvalidSquareMetersException("gardenSize", gardenSize);
        }
        this.gardenSize = gardenSize;
    }

    /**
     * Returns a string representation of the chalet.
     *
     * @return a string containing its price, garden size and tax.
     */
    @Override
    public String toString() {
        return "Chalet [Price = " + this.getPrice() + " euros, Garden = " + this.gardenSize
                + " square meters, Tax = " + this.tax() + " euros]";
    }
}
