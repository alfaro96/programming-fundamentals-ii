package antiques;

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
     * @param id The unique identifier.
     * @param year The year it was built.
     * @param material The material used.
     */
    public Furniture(String id, int year, String material) {
        super(id, year);
        this.material = material;
    }

    /**
     * Returns a string representation of the furniture.
     *
     * @return A string containing all the attributes of the furniture.
     */
    @Override
    public String toString() {
        return "Furniture[" + this.id + " | Year: " + this.year + " | Material: " + this.material + "]";
    }
}
