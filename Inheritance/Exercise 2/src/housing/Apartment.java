package housing;

/**
 * Represents an apartment.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Apartment extends Housing {

    /** Indicates whether the apartment has a terrace. */
    private boolean terrace;

    /**
     * Constructs a new {@link Apartment}.
     *
     * @param price The base price of the apartment.
     * @param terrace {@code true} if it has a terrace, {@code false} otherwise.
     */
    public Apartment(double price, boolean terrace) {
        super(price);
        this.terrace = terrace;
    }

    /**
     * Calculates the tax for the apartment.
     * The tax is computed as the base price multiplied by 1.5.
     *
     * @return The calculated tax amount.
     */
    @Override
    public double tax() {
        return this.getPrice() * 1.5;
    }

    /**
     * Returns a string representation of the apartment.
     *
     * @return A descriptive string of the apartment.
     */
    @Override
    public String toString() {
        return "Apartment [Price = " + this.getPrice() + " euros, Terrace = " + this.terrace +
                ", Tax = " + this.tax() + " euros]";
    }
}
