/**
 * Represents a vector of the plane: a directed segment from an origin point to an end point.
 * <p>
 * The operations work on both points, coordinate by coordinate, and always return new
 * objects: the vectors and points they receive do not change.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Point
 */
class Vector {

    /**
     * The point where the vector starts.
     */
    Point originPoint;

    /**
     * The point where the vector ends.
     */
    Point endPoint;

    /**
     * Creates the vector that goes from one point to another.
     *
     * @param originPoint the point where the vector starts.
     * @param endPoint the point where the vector ends.
     */
    Vector(Point originPoint, Point endPoint) {
        this.originPoint = originPoint;
        this.endPoint = endPoint;
    }

    /**
     * Adds another vector to this one: the origins are added coordinate by coordinate, and
     * so are the ends.
     *
     * @param other the vector to add.
     * @return a new vector with the sum.
     */
    Vector add(Vector other) {
        Point origin = new Point(this.originPoint.x + other.originPoint.x, this.originPoint.y + other.originPoint.y);
        Point end = new Point(this.endPoint.x + other.endPoint.x, this.endPoint.y + other.endPoint.y);
        return new Vector(origin, end);
    }

    /**
     * Subtracts another vector from this one: the origins are subtracted coordinate by
     * coordinate, and so are the ends.
     *
     * @param other the vector to subtract.
     * @return a new vector with the difference.
     */
    Vector subtract(Vector other) {
        Point origin = new Point(this.originPoint.x - other.originPoint.x, this.originPoint.y - other.originPoint.y);
        Point end = new Point(this.endPoint.x - other.endPoint.x, this.endPoint.y - other.endPoint.y);
        return new Vector(origin, end);
    }

    /**
     * Multiplies this vector by a number: every coordinate of both points is multiplied by it.
     *
     * @param scalar the number to multiply by.
     * @return a new vector with the product.
     */
    Vector scalarProduct(double scalar) {
        Point origin = new Point(this.originPoint.x * scalar, this.originPoint.y * scalar);
        Point end = new Point(this.endPoint.x * scalar, this.endPoint.y * scalar);
        return new Vector(origin, end);
    }

    /**
     * Computes the centroid of this vector and two others: the barycenter of the triangle
     * formed by their three origin points, whose coordinates are the averages of theirs.
     *
     * @param second the second vector.
     * @param third the third vector.
     * @return a new point at the centroid.
     */
    Point centroid(Vector second, Vector third) {
        double x = (this.originPoint.x + second.originPoint.x + third.originPoint.x) / 3;
        double y = (this.originPoint.y + second.originPoint.y + third.originPoint.y) / 3;
        return new Point(x, y);
    }

    /**
     * Returns the vector in the form {@code "(x, y) -> (m, n)"}, such as
     * {@code "(0.0, 0.0) -> (1.0, 2.0)"}.
     *
     * @return the origin point, an arrow and the end point.
     */
    String describe() {
        return this.originPoint.describe() + " -> " + this.endPoint.describe();
    }

    /**
     * Checks whether this vector and another one are the same vector: they have the same
     * origin point and the same end point.
     *
     * @param other the vector to compare with.
     * @return {@code true} if both vectors are the same, {@code false} otherwise.
     */
    boolean isEqualTo(Vector other) {
        return this.originPoint.isEqualTo(other.originPoint) && this.endPoint.isEqualTo(other.endPoint);
    }
}
