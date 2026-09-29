package housing;

import exceptions.InvalidPriceException;

/**
 * Represents an apartment.
 *
 * <p>An apartment is a type of {@link Housing} and may optionally include a terrace.
 * Its tax is calculated as 1.5 times the base price. Price validation is
 * delegated to the parent constructor.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Housing
 * @see exceptions.InvalidPriceException
 */
public class Apartment extends Housing {

    /** Indicates whether the apartment has a terrace. */
    private boolean terrace;

    /**
     * Constructs a new {@link Apartment}.
     *
     * @param price The base price of the apartment in euros.
     * @param terrace {@code true} if it has a terrace, {@code false} otherwise.
     * @throws InvalidPriceException If {@code price} is strictly negative.
     */
    public Apartment(double price, boolean terrace) throws InvalidPriceException {
        super(price);
        this.terrace = terrace;
    }

    /**
     * Calculates the tax for the apartment.
     * The tax is computed as the base price multiplied by 1.5.
     *
     * @return The calculated tax amount in euros.
     */
    @Override
    public double tax() {
        return this.getPrice() * 1.5;
    }

    /**
     * Returns a string representation of the apartment.
     *
     * @return A descriptive string containing price, terrace availability, and tax.
     */
    @Override
    public String toString() {
        return "Apartment [Price = " + this.getPrice() + " euros, Terrace = " + this.terrace
                + ", Tax = " + this.tax() + " euros]";
    }
}
