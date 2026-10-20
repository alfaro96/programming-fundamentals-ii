package housing;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;

/**
 * Represents a country house, built on a large plot of land with a {@link Chalet} in it.
 *
 * <p>Its constructor rejects a negative plot size, on top of the price check of
 * {@link Housing}.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see InvalidSquareMetersException
 */
public class CountryHouse extends Housing {

    /** The total size of the plot of land in square meters. */
    private double plotSize;

    /** The chalet located inside this country house. */
    private Chalet chalet;

    /**
     * Constructs a new {@link CountryHouse}.
     *
     * @param price the base price of the country house, in euros.
     * @param plotSize the size of the plot in square meters.
     * @param chalet the chalet built on this plot.
     * @throws InvalidPriceException if {@code price} is negative.
     * @throws InvalidSquareMetersException if {@code plotSize} is negative.
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
     * Calculates the tax for the country house: the tax of its chalet plus 0.5 euros per square
     * meter of the plot.
     *
     * @return the calculated tax amount in euros.
     */
    @Override
    public double tax() {
        return this.chalet.tax() + this.plotSize * 0.5;
    }

    /**
     * Returns a string representation of the country house.
     *
     * @return a string containing its price, plot size, tax and chalet.
     */
    @Override
    public String toString() {
        return "CountryHouse [Price = " + this.getPrice() + " euros, Plot = " + this.plotSize
                + " square meters, Tax = " + this.tax() + " euros] containing: " + this.chalet;
    }
}
