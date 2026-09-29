package antiques;

/**
 * Represents a painting, which is a specific type of antique.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Painting extends Antique {

    /** The technique used in the painting. */
    private String technique;

    /**
     * Constructs a new {@link Painting}.
     *
     * @param id The unique identifier.
     * @param year The year it was painted.
     * @param technique The painting technique.
     */
    public Painting(String id, int year, String technique) {
        super(id, year);
        this.technique = technique;
    }

    /**
     * Returns a string representation of the painting.
     *
     * @return A string containing all the attributes of the painting.
     */
    @Override
    public String toString() {
        return "Painting[" + this.id + " | Year: " + this.year + " | Technique: " + this.technique + "]";
    }
}
