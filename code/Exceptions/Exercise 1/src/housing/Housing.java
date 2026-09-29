package housing;

import exceptions.InvalidPriceException;

/**
 * Represents a generic housing property in the real estate system.
 *
 * <p>This is an abstract class that defines the core attributes and behaviors
 * that all specific types of housing must implement. Price validation is
 * enforced at construction time via a checked exception.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see exceptions.InvalidPriceException
 */
public abstract class Housing {

    /** The base price of the housing property in euros. Must be non-negative. */
    private double price;

    /**
     * Constructs a new {@link Housing} instance.
     *
     * @param price The base price of the housing property in euros.
     * @throws InvalidPriceException If {@code price} is strictly negative.
     */
    public Housing(double price) throws InvalidPriceException {
        if (price < 0) {
            throw new InvalidPriceException(price);
        }
        this.price = price;
    }

    /**
     * Retrieves the base price of the housing property.
     *
     * @return The price in euros.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Calculates the tax associated with this housing property.
     * This method must be implemented by all subclasses according to their specific rules.
     *
     * @return The calculated tax amount in euros.
     */
    public abstract double tax();

    /**
     * Returns a string representation of the housing property.
     * Forces all subclasses to provide their own descriptive text.
     *
     * @return A string detailing the housing's properties.
     */
    @Override
    public abstract String toString();
}
