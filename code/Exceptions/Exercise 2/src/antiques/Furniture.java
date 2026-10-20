package antiques;

import exceptions.InvalidPriceException;

/**
 * Represents a piece of furniture, a specific type of antique.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Furniture extends Antique {

    /** The material the furniture is made of. */
    private String material;

    /**
     * Constructs a new {@link Furniture}.
     *
     * @param id the unique identifier.
     * @param year the year it was built.
     * @param price its price, in euros.
     * @param material the material used.
     * @throws exceptions.YearOutOfRangeException if {@code year} is out of range.
     * @throws InvalidPriceException if {@code price} is negative.
     */
    public Furniture(String id, int year, double price, String material) throws InvalidPriceException {
        super(id, year, price);
        this.material = material;
    }

    /**
     * Returns a string representation of the furniture.
     *
     * @return a string containing all the attributes of the furniture.
     */
    @Override
    public String toString() {
        return "Furniture[" + this.id + " | Year: " + this.year + " | Price: " + this.price
                + " euros | Material: " + this.material + "]";
    }
}
