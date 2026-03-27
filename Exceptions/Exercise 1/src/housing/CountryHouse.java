package housing;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;

/**
 * Represents a country house.
 *
 * <p>A country house is built on a large plot of land and contains a specific
 * {@link Chalet} within it, demonstrating composition. Validates both price
 * (delegated to {@link Housing}) and plot size at construction time.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Housing
 * @see Chalet
 * @see exceptions.InvalidPriceException
 * @see exceptions.InvalidSquareMetersException
 */
public class CountryHouse extends Housing {

    /** The total size of the plot of land in square meters. Must be non-negative. */
    private double plotSize;

    /** The chalet located inside this country house. */
    private Chalet chalet;

    /**
     * Constructs a new {@link CountryHouse}.
     *
     * @param price The base price of the country house in euros.
     * @param plotSize The size of the land plot in square meters.
     * @param chalet The specific chalet built on this plot.
     * @throws InvalidPriceException If {@code price} is strictly negative.
     * @throws InvalidSquareMetersException If {@code plotSize} is strictly negative.
     */
    public CountryHouse(double price, double plotSize, Chalet chalet)
            throws InvalidPriceException, InvalidSquareMetersException {
        super(price);
        if (plotSize < 0) {
            throw new InvalidSquareMetersException("plotSize", plotSize);
        }
        this.plotSize = plotSize;
        this.chalet = chalet;
    }

    /**
     * Calculates the tax for the country house.
     * The tax is the sum of the internal chalet's tax plus 0.5 euros per square meter of the plot.
     *
     * @return The calculated tax amount in euros.
     */
    @Override
    public double tax() {
        return this.chalet.tax() + (this.plotSize * 0.5);
    }

    /**
     * Returns a string representation of the country house.
     *
     * @return A descriptive string containing price, plot size, tax, and the embedded chalet.
     */
    @Override
    public String toString() {
        return "CountryHouse [Price = " + this.getPrice() + " euros, Plot = " + this.plotSize
                + " m², Tax = " + this.tax() + " euros] containing: " + this.chalet.toString();
    }
}
