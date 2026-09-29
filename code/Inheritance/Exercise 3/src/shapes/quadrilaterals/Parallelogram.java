package shapes.quadrilaterals;

import shapes.GeometricFigure;

/**
 * Represents a parallelogram, a quadrilateral with two pairs of parallel sides.
 * This class inherits the basic properties from {@link GeometricFigure}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Parallelogram extends GeometricFigure {

    /** The length of the first pair of parallel sides. */
    private double side1;

    /** The length of the second pair of parallel sides. */
    private double side2;

    /** The first internal angle in radians. */
    private double angle1;

    /** The second internal angle in radians (complementary to angle1). */
    private double angle2;

    /**
     * Constructs a full {@link Parallelogram} specifying all sides and angles explicitly.
     *
     * @param fillColor The fill color.
     * @param borderColor The border color.
     * @param side1 The length of the first side pair.
     * @param side2 The length of the second side pair.
     * @param angle1 The first internal angle (in radians).
     * @param angle2 The second internal angle (in radians).
     */
    public Parallelogram(String fillColor, String borderColor, double side1, double side2, double angle1, double angle2) {
        super(fillColor, borderColor);
        this.side1 = side1;
        this.side2 = side2;
        this.angle1 = angle1;
        this.angle2 = angle2;
    }

    /**
     * Constructs a symmetric {@link Parallelogram} (like a rhombus) using a single side and angle.
     * The second angle is calculated automatically assuming supplementary angles.
     *
     * @param side The uniform length for all sides.
     * @param angle The primary internal angle (in radians).
     * @param fillColor The fill color.
     * @param borderColor The border color.
     */
    public Parallelogram(double side, double angle, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.side1 = side;
        this.side2 = side;
        this.angle1 = angle;
        // In a parallelogram, consecutive angles add up to 180 degrees (Math.PI radians)
        this.angle2 = Math.PI - angle;
    }

    /**
     * Calculates the perimeter.
     *
     * @return The perimeter of the parallelogram.
     */
    @Override
    public double calculatePerimeter() {
        return 2 * (this.side1 + this.side2);
    }

    /**
     * Calculates the area.
     *
     * @return The calculated area.
     */
    @Override
    public double calculateArea() {
        return this.side1 * this.side2 * Math.sin(this.angle1);
    }

    // Getters and setters for Parallelogram attributes can be added here if needed
}