/**
 * Represents a directed line between two Points in a two-dimensioanl plane.
 * This class depends on the {@link Point} class for its geometric logic.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Vector {

    /** The point where the vector begins.  */
    public Point originPoint;

    /** The point where the vector ends.  */
    public Point endPoint;

    /**
     * Constructor that takes the origin and end points.
     *
     * @param originPoint The starting point.
     * @param endPoint The ending point.
     */
    public Vector(Point originPoint, Point endPoint) {
        this.originPoint = originPoint;
        this.endPoint = endPoint;
    }

    /**
     * Adds another vector to this one.
     *
     * @param other Vector to be added.
     * @return A new {@link Vector} representing the addition.
     */
    public Vector add(Vector other) {
        Point newOrigin = new Point(this.originPoint.x + other.originPoint.x, this.originPoint.y + other.originPoint.y);
        Point newEnd = new Point(this.endPoint.x + other.endPoint.x, this.endPoint.y + other.endPoint.y);
        return new Vector(newOrigin, newEnd);
    }

    /**
     * Subtracts another vector from this one.
     *
     * @param other Vector to subtract.
     * @return A new {@link Vector} representing the subtraction.
     */
    public Vector subtract(Vector other) {
        Point newOrigin = new Point(this.originPoint.x - other.originPoint.x, this.originPoint.y - other.originPoint.y);
        Point newEnd = new Point(this.endPoint.x - other.endPoint.x, this.endPoint.y - other.endPoint.y);
        return new Vector(newOrigin, newEnd);
    }

    /**
     * Performs a scalar multiplication, multiplying each component of this
     * {@link Vector} by the given scalar value.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new {@link Vector} whose components are each multiplied by {@code scalar}.
     */
    public Vector scalarProduct(double scalar) {
        Point newOrigin = new Point(this.originPoint.x * scalar, this.originPoint.y * scalar);
        Point newEnd = new Point(this.endPoint.x * scalar, this.endPoint.y * scalar);
        return new Vector(newOrigin, newEnd);
    }

    /**
     * Calculates the centroid or barycenter of three vectors.
     *
     * @param v2 Second vector.
     * @param v3 Third vector.
     * @return A {@link Point} representing the centroid.
     */
    public Point centroid(Vector v2, Vector v3) {
        double avgX = (this.originPoint.x + v2.originPoint.x + v3.originPoint.x) / 3.0;
        double avgY = (this.endPoint.y + v2.endPoint.y + v3.endPoint.y) / 3.0;
        return new Point(avgX, avgY);
    }

    /**
     * Returns the vector in a readable format.
     *
     * @return String formatted.
     */
    @Override
    public String toString() {
        return this.originPoint.toString() + " -> " + this.endPoint.toString();
    }

    /**
     * Checks whether two vectors are equivalent.
     *
     * @param o The object to compare.
     * @return {@code true} if both origin and end points are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return this.originPoint.equals(vector.originPoint) && this.endPoint.equals(vector.endPoint);
    }
}
