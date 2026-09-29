package shapes.polygons;

import shapes.GeometricFigure;

/**
 * Represents a regular polygon, a specific type of {@link GeometricFigure}.
 * A regular polygon has all sides of equal length and all internal angles equal.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class RegularPolygon extends GeometricFigure {

    /** The total number of edges the polygon has. */
    private int numberOfSides;

    /** The length of each individual side. */
    private double sideLength;

    /** The internal angle of the polygon, expressed in radians. */
    private double angle;

    /**
     * Constructs a new {@link RegularPolygon}.
     *
     * @param numberOfSides The total amount of sides.
     * @param sideLength The specific length of a single side.
     * @param angle The internal angle in radians (required for {@link Math#tan}).
     * @param fillColor The fill color inherited from {@link GeometricFigure}.
     * @param borderColor The border color inherited from {@link GeometricFigure}.
     */
    public RegularPolygon(int numberOfSides, double sideLength, double angle, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.numberOfSides = numberOfSides;
        this.sideLength = sideLength;
        this.angle = angle;
    }

    /**
     * Calculates the perimeter.
     *
     * @return The calculated perimeter.
     */
    @Override
    public double calculatePerimeter() {
        return this.numberOfSides * this.sideLength;
    }

    /**
     * Calculates the area.
     *
     * @return The calculated area.
     */
    @Override
    public double calculateArea() {
        double perimeter = this.calculatePerimeter();
        double apothem = this.sideLength / (2 * Math.tan(this.angle / 2));

        return (perimeter * apothem) / 2;
    }
}
