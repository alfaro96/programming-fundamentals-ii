package antiques;

import exceptions.InvalidPriceException;
import exceptions.YearOutOfRangeException;

/**
 * Represents a generic antique in the collection.
 *
 * <p>Serves as the superclass of the specific types of antiques. Its constructor rejects a
 * year out of {@link #MIN_YEAR} to {@link #MAX_YEAR} and a negative price.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see YearOutOfRangeException
 * @see InvalidPriceException
 */
public class Antique {

    /** The first year an antique can be dated in. */
    public static final int MIN_YEAR = 1;

    /** The last year an antique can be dated in. */
    public static final int MAX_YEAR = 1900;

    /** The identifier of the antique. */
    protected String id;

    /** The creation year of the antique. */
    protected int year;

    /** The price of the antique in euros. */
    protected double price;

    /**
     * Constructs a new {@link Antique}.
     *
     * @param id the unique identifier.
     * @param year the year it was created.
     * @param price its price, in euros.
     * @throws YearOutOfRangeException if {@code year} is not between {@link #MIN_YEAR} and
     *         {@link #MAX_YEAR}.
     * @throws InvalidPriceException if {@code price} is negative.
     */
    protected Antique(String id, int year, double price) throws InvalidPriceException {
        if (year < MIN_YEAR || year > MAX_YEAR) {
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
     * Returns the year of the antique, which gives its century in the matrix.
     *
     * @return the creation year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Returns the price of the antique.
     *
     * @return the price in euros.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns a string representation of the antique.
     *
     * @return a string containing the identifier, the year and the price of the antique.
     */
    @Override
    public String toString() {
        return "Antique[" + this.id + " | Year: " + this.year + " | Price: " + this.price + " euros]";
    }
}
