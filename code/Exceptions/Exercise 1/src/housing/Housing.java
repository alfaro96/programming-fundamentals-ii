package housing;

import exceptions.InvalidPriceException;

/**
 * Represents a generic housing property in the real estate system.
 *
 * <p>It is an abstract class that defines the attributes and behaviors that every specific
 * type of housing must implement. Its constructor rejects a negative price.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see InvalidPriceException
 */
public abstract class Housing {

    /** The base price of the housing property in euros. */
    private double price;

    /**
     * Constructs a new {@link Housing} instance.
     *
     * @param price the base price of the housing property, in euros.
     * @throws InvalidPriceException if {@code price} is negative.
     */
    public Housing(double price) throws InvalidPriceException {
        if (price < 0) {
            throw new InvalidPriceException(price);
        }
        this.price = price;
    }

    /**
     * Returns the base price of the housing property.
     *
     * @return the price in euros.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Calculates the tax associated with this housing property.
     *
     * @return the calculated tax amount in euros.
     */
    public abstract double tax();

    /**
     * Returns a string representation of the housing property.
     *
     * @return a string detailing the properties of the housing.
     */
    @Override
    public abstract String toString();
}
