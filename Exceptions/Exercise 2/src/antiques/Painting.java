package antiques;

import exceptions.InvalidPriceException;

/**
 * Represents a painting, a specific type of {@link Antique}.
 *
 * <p>In addition to the common antique attributes, a painting records the technique
 * used by the artist. Year and price validation is delegated to the
 * {@link Antique} constructor.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Antique
 * @see exceptions.InvalidPriceException
 * @see exceptions.YearOutOfRangeException
 */
public class Painting extends Antique {

    /** The technique used in the painting (e.g., "Oil on canvas", "Watercolour"). */
    private String technique;

    /**
     * Constructs a new {@link Painting}.
     *
     * @param id The unique identifier.
     * @param year The year the painting was created.
     * @param price The estimated market price in euros.
     * @param technique The painting technique.
     * @throws exceptions.YearOutOfRangeException If {@code year} is outside the accepted range (unchecked).
     * @throws InvalidPriceException If {@code price} is strictly negative.
     */
    public Painting(String id, int year, double price, String technique) throws InvalidPriceException {
        super(id, year, price);
        this.technique = technique;
    }

    /**
     * Returns a string representation of the painting.
     *
     * @return A string containing the identifier, year, price, and technique.
     */
    @Override
    public String toString() {
        return "Painting[" + this.id + " | Year: " + this.year
                + " | Price: " + this.price + " euros | Technique: " + this.technique + "]";
    }
}