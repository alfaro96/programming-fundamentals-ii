package antiques;

import exceptions.InvalidPriceException;
import exceptions.YearOutOfRangeException;

/**
 * Represents a generic antique in the collection.
 *
 * <p>Serves as an abstract base class for specific types of antiques such as
 * {@link Furniture} and {@link Painting}. Input validation is enforced at
 * construction time: the year must lie within
 * [{@link exceptions.YearOutOfRangeException#MIN_YEAR},
 * {@link exceptions.YearOutOfRangeException#MAX_YEAR}],
 * and the price must be non-negative.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see exceptions.InvalidPriceException
 * @see exceptions.YearOutOfRangeException
 */
public abstract class Antique {

    /** The unique identifier of the antique. */
    protected String id;

    /**
     * The creation year of the antique.
     * Must be in [{@link YearOutOfRangeException#MIN_YEAR},
     * {@link YearOutOfRangeException#MAX_YEAR}].
     */
    protected int year;

    /** The estimated market price of the antique in euros. Must be non-negative. */
    protected double price;

    /**
     * Constructs a new {@link Antique}.
     *
     * @param id The unique identifier.
     * @param year The creation year of the antique.
     * @param price The estimated market price of the antique in euros.
     * @throws YearOutOfRangeException If {@code year} is outside the accepted range. This is an unchecked exception.
     * @throws InvalidPriceException If {@code price} is strictly negative.
     */
    protected Antique(String id, int year, double price) throws InvalidPriceException {
        if (year < YearOutOfRangeException.MIN_YEAR || year > YearOutOfRangeException.MAX_YEAR) {
            throw new YearOutOfRangeException(year);
        }
        if (price < 0) {
            throw new InvalidPriceException(price);
        }
        this.id = id;
        this.year = year;
        this.price = price;
    }

    /**
     * Gets the creation year of the antique.
     * Essential for calculating the corresponding century row in the matrix.
     *
     * @return The creation year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets the estimated market price of the antique.
     *
     * @return The price in euros.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Abstract method forcing subclasses to provide their own string representation.
     *
     * @return A descriptive string of the antique.
     */
    @Override
    public abstract String toString();
}