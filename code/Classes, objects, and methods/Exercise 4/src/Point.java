/**
 * Represents a point in two-dimensional Cartesian space.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Point {

    /** The X coordinate of the point. */
    public double x;

    /** The Y coordinate of the point. */
    public double y;

    /**
     * Default constructor. Initializes the point at origin (0,0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Standard constructor with specific coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance to another point.
     *
     * @param other The target point.
     * @return The distance value.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    /**
     * Checks if this point and two others are on the same line.
     * Calculated using the area of the triangle formed by the points.
     *
     * @param p1 Second point.
     * @param p2 Third point.
     * @return {@code true} if they are colinear.
     */
    public boolean isColinearTo(Point p1, Point p2) {
        double area = this.x * (p1.y - p2.y) + p1.x * (p2.y - this.y) + p2.x * (this.y - p1.y);
        return area == 0;
    }

    /**
     * Calculates the point exactly in the middle of this and another point.
     *
     * @param other The second point.
     * @return A new {@link Point} object representing the middle.
     */
    public Point middlePoint(Point other) {
        return new Point((this.x + other.x) / 2, (this.y + other.y) / 2);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }
}
