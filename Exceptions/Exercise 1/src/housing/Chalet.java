package housing;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;

/**
 * Represents a chalet, which is a specific type of single-family home
 * that includes a garden.
 *
 * <p>Validates the garden size at construction time. Inherits price and
 * basement validation from {@link SingleFamily} and {@link Housing} respectively.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see SingleFamily
 * @see exceptions.InvalidPriceException
 * @see exceptions.InvalidSquareMetersException
 */
public class Chalet extends SingleFamily {

    /** The size of the garden in square meters. Must be non-negative. */
    private double gardenSize;

    /**
     * Constructs a new {@link Chalet}.
     *
     * @param price The base price of the chalet in euros.
     * @param numberOfFloors The number of floors.
     * @param basementSize The size of the basement in square meters.
     * @param gardenSize The size of the garden in square meters.
     * @throws InvalidPriceException If {@code price} is strictly negative.
     * @throws InvalidSquareMetersException If {@code basementSize} or {@code gardenSize} is strictly negative.
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
     * @return A descriptive string detailing the chalet's price, garden size, and tax.
     */
    @Override
    public String toString() {
        return "Chalet [Price = " + this.getPrice() + " euros, Garden = " + this.gardenSize
                + " m², Tax = " + this.tax() + " euros]";
    }
}
