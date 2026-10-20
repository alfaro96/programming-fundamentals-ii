package housing;

import exceptions.InvalidPriceException;

/**
 * Represents an apartment, which may have a terrace.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see InvalidPriceException
 */
public class Apartment extends Housing {

    /** Indicates whether the apartment has a terrace. */
    private boolean terrace;

    /**
     * Constructs a new {@link Apartment}.
     *
     * @param price the base price of the apartment, in euros.
     * @param terrace {@code true} if it has a terrace, {@code false} otherwise.
     * @throws InvalidPriceException if {@code price} is negative.
     */
    public Apartment(double price, boolean terrace) throws InvalidPriceException {
        super(price);
        this.terrace = terrace;
    }

    /**
     * Calculates the tax for the apartment, its base price multiplied by 1.5.
     *
     * @return the calculated tax amount in euros.
     */
    @Override
    public double tax() {
        return this.getPrice() * 1.5;
    }

    /**
     * Returns a string representation of the apartment.
     *
     * @return a string containing its price, whether it has a terrace, and its tax.
     */
    @Override
    public String toString() {
        return "Apartment [Price = " + this.getPrice() + " euros, Terrace = " + this.terrace
                + ", Tax = " + this.tax() + " euros]";
    }
}
