/**
 * Represents a point (<i>x</i>, <i>y</i>) of the plane.
 * <p>
 * Coordinates are {@code double} values, which carry rounding errors, so two of them are
 * considered equal when they differ by less than {@link #EPSILON}.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Vector
 */
class Point {

    /**
     * The largest difference between two values that are still considered equal.
     */
    static final double EPSILON = 1e-9;

    /**
     * The <i>x</i> coordinate of the point.
     */
    double x;

    /**
     * The <i>y</i> coordinate of the point.
     */
    double y;

    /**
     * Creates the point (0, 0), the origin.
     */
    Point() {
        this(0, 0);
    }

    /**
     * Creates the point with the given coordinates.
     *
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Computes the Euclidean distance between this point and another one:
     * &radic;((<i>x</i><sub>2</sub> &minus; <i>x</i><sub>1</sub>)<sup>2</sup> +
     * (<i>y</i><sub>2</sub> &minus; <i>y</i><sub>1</sub>)<sup>2</sup>).
     *
     * @param other the other point.
     * @return the distance between both points.
     */
    double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks whether this point and two others lie on the same line.
     * <p>
     * They do when the triangle they form has no area, that is, when
     * (<i>x</i><sub>2</sub> &minus; <i>x</i><sub>1</sub>)(<i>y</i><sub>3</sub> &minus; <i>y</i><sub>1</sub>) &minus;
     * (<i>y</i><sub>2</sub> &minus; <i>y</i><sub>1</sub>)(<i>x</i><sub>3</sub> &minus; <i>x</i><sub>1</sub>) is 0.
     *
     * @param second the second point.
     * @param third the third point.
     * @return {@code true} if the three points lie on the same line, {@code false} otherwise.
     */
    boolean isCollinearTo(Point second, Point third) {
        double area = (second.x - this.x) * (third.y - this.y) - (second.y - this.y) * (third.x - this.x);
        return Math.abs(area) < EPSILON;
    }

    /**
     * Computes the point halfway between this point and another one.
     *
     * @param other the other point.
     * @return a new point, whose coordinates are the averages of those of both points.
     */
    Point middlePoint(Point other) {
        return new Point((this.x + other.x) / 2, (this.y + other.y) / 2);
    }

    /**
     * Returns the point in the form {@code "(x, y)"}, such as {@code "(1.0, 2.5)"}.
     *
     * @return both coordinates, in parentheses and separated by a comma.
     */
    String describe() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Checks whether this point and another one are the same point: both coordinates differ
     * by less than {@link #EPSILON}.
     *
     * @param other the point to compare with.
     * @return {@code true} if both points are the same, {@code false} otherwise.
     */
    boolean isEqualTo(Point other) {
        return Math.abs(this.x - other.x) < EPSILON && Math.abs(this.y - other.y) < EPSILON;
    }
}
