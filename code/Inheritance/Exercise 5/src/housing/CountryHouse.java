package housing;

/**
 * Represents a country house.
 * A country house is built on a large plot of land and contains a specific chalet within it,
 * demonstrating composition.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class CountryHouse extends Housing {

    /** The total size of the plot of land in square meters. */
    private double plotSize;

    /** The chalet located inside this country house. */
    private Chalet chalet;

    /**
     * Constructs a new {@link CountryHouse}.
     *
     * @param price The base price of the country house.
     * @param plotSize The size of the land plot in square meters.
     * @param chalet The specific chalet built on this plot.
     */
    public CountryHouse(double price, double plotSize, Chalet chalet) {
        super(price);
        this.plotSize = plotSize;
        this.chalet = chalet;
    }

    /**
     * Calculates the tax for the country house.
     * The tax is the sum of the internal chalet's tax plus 0.5 euros per square meter of the plot.
     *
     * @return The calculated tax amount.
     */
    @Override
    public double tax() {
        return this.chalet.tax() + (this.plotSize * 0.5);
    }

    /**
     * Returns a string representation of the country house.
     *
     * @return A descriptive string containing the country house and its internal chalet.
     */
    @Override
    public String toString() {
        return "CountryHouse [Price = " + this.getPrice() + " euros, Plot = " + this.plotSize +
                " square meters, Tax=" + this.tax() + " euros] containing: " + this.chalet.toString();
    }
}
