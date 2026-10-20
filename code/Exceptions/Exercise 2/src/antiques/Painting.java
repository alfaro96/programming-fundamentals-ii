package antiques;

import exceptions.InvalidPriceException;

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
     * @param id the unique identifier.
     * @param year the year it was painted.
     * @param price its price, in euros.
     * @param technique the painting technique.
     * @throws exceptions.YearOutOfRangeException if {@code year} is out of range.
     * @throws InvalidPriceException if {@code price} is negative.
     */
    public Painting(String id, int year, double price, String technique) throws InvalidPriceException {
        super(id, year, price);
        this.technique = technique;
    }

    /**
     * Returns a string representation of the painting.
     *
     * @return a string containing all the attributes of the painting.
     */
    @Override
    public String toString() {
        return "Painting[" + this.id + " | Year: " + this.year + " | Price: " + this.price
                + " euros | Technique: " + this.technique + "]";
    }
}
