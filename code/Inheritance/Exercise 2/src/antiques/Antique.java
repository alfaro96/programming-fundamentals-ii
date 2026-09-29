package antiques;

/**
 * Represents a generic antique in the collection.
 * Serves as an abstract base class for specific types of antiques.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public abstract class Antique {

    /** The identifier of the antique. */
    protected String id;

    /** * The creation year of the antique. */
    protected int year;

    /**
     * Constructs a new {@link Antique}.
     *
     * @param id The unique identifier.
     * @param year The year it was created.
     */
    protected Antique(String id, int year) {
        this.id = id;
        this.year = year;
    }

    /**
     * Gets the year of the antique.
     * Essential for calculating the corresponding century in the matrix.
     *
     * @return The creation year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Abstract method to force subclasses to provide their own string representation.
     *
     * @return A descriptive string of the antique.
     */
    @Override
    public abstract String toString();
}
