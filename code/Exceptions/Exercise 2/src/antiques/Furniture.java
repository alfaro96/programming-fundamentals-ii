package antiques;

import exceptions.InvalidPriceException;

/**
 * Represents a piece of furniture, a specific type of {@link Antique}.
 *
 * <p>In addition to the common antique attributes, a furniture piece records the
 * material it is made of. Year and price validation is delegated to the
 * {@link Antique} constructor.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Antique
 * @see exceptions.InvalidPriceException
 * @see exceptions.YearOutOfRangeException
 */
public class Furniture extends Antique {

    /** The material the furniture is made of (e.g., "Oak", "Mahogany"). */
    private String material;

    /**
     * Constructs a new {@link Furniture}.
     *
     * @param id The unique identifier.
     * @param year The year the furniture was built.
     * @param price The estimated market price in euros.
     * @param material The material used in the furniture.
     * @throws exceptions.YearOutOfRangeException If {@code year} is outside the accepted range (unchecked).
     * @throws InvalidPriceException If {@code price} is strictly negative.
     */
    public Furniture(String id, int year, double price, String material) throws InvalidPriceException {
        super(id, year, price);
        this.material = material;
    }

    /**
     * Returns a string representation of the furniture.
     *
     * @return A string containing the identifier, year, price, and material.
     */
    @Override
    public String toString() {
        return "Furniture[" + this.id + " | Year: " + this.year
                + " | Price: " + this.price + " euros | Material: " + this.material + "]";
    }
}