import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link Point} and {@link Vector} test suite.
 * <p>
 * This class performs unit testing on both the {@link Point} and {@link Vector} classes
 * to verify the correctness of geometric operations, distance calculations, and
 * vector arithmetic.
 * </p>
 * <p><b>Point – Covered scenarios:</b></p>
 * <ul>
 * <li>Default constructor – initializes the point at the origin ({@code 0}, {@code 0}).</li>
 * <li>Standard constructor – stores the given {@code x} and {@code y} coordinates.</li>
 * <li>{@link Point#distance(Point)} – Euclidean distance between two points.</li>
 * <li>{@link Point#isColinearTo(Point, Point)} – three collinear points and three non-collinear points.</li>
 * <li>{@link Point#middlePoint(Point)} – midpoint between two points.</li>
 * <li>{@link Point#equals(Object)} – equal and unequal point pairs.</li>
 * <li>{@link Point#toString()} – {@code "(x, y)"} format.</li>
 * </ul>
 * <p><b>Vector – Covered scenarios:</b></p>
 * <ul>
 * <li>Constructor – stores {@code originPoint} and {@code endPoint}.</li>
 * <li>{@link Vector#add(Vector)} – vector addition.</li>
 * <li>{@link Vector#subtract(Vector)} – vector subtraction.</li>
 * <li>{@link Vector#scalarProduct(double)} – scalar multiplication.</li>
 * <li>{@link Vector#centroid(Vector, Vector)} – centroid of three vectors.</li>
 * <li>{@link Vector#equals(Object)} – equal and unequal vector pairs.</li>
 * <li>{@link Vector#toString()} – {@code "(x, y) -> (m, n)"} format.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Point
 * @see Vector
 */
public class Tests {

    /** Delta value for floating-point comparisons. */
    private static final double DELTA = 1e-9;

    /**
     * Verifies the default constructor places the point at the origin ({@code 0.0}, {@code 0.0}).
     */
    @Test
    public void testPointDefaultConstructor() {
        Point p = new Point();

        assertEquals("Default x must be 0.0", 0.0, p.x, DELTA);
        assertEquals("Default y must be 0.0", 0.0, p.y, DELTA);
    }

    /**
     * Verifies the standard constructor stores the given coordinates correctly.
     * <p>
     * <b>Scenario:</b> {@code new Point(3.0, 4.0)} → {@code x = 3.0}, {@code y = 4.0}.
     * </p>
     */
    @Test
    public void testPointStandardConstructor() {
        Point p = new Point(3.0, 4.0);

        assertEquals("x must be 3.0", 3.0, p.x, DELTA);
        assertEquals("y must be 4.0", 4.0, p.y, DELTA);
    }

    /**
     * Verifies the Euclidean distance between two known points.
     * <p>
     * <b>Math Logic:</b> {@code distance((0,0), (3,4)) = sqrt(9 + 16) = 5.0}.
     * </p>
     */
    @Test
    public void testPointDistance() {
        Point origin = new Point(0.0, 0.0);
        Point other = new Point(3.0, 4.0);

        double dist = origin.distance(other);

        assertEquals("Distance between (0, 0) and (3, 4) must be 5.0", 5.0, dist, DELTA);
    }

    /**
     * Verifies {@link Point#isColinearTo(Point, Point)} returns {@code true} for three collinear points.
     * <p>
     * <b>Scenario:</b> Points {@code (0,0)}, {@code (1,1)}, and {@code (2,2)} all lie on the line {@code y = x}.
     * </p>
     */
    @Test
    public void testPointIsColinearTrue() {
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(1.0, 1.0);
        Point p3 = new Point(2.0, 2.0);

        assertTrue("(0, 0), (1, 1), (2,2 ) must be collinear", p1.isColinearTo(p2, p3));
    }

    /**
     * Verifies {@link Point#isColinearTo(Point, Point)} returns {@code false} for three non-collinear points.
     * <p>
     * <b>Scenario:</b> Points {@code (0, 0)}, {@code (1, 0)}, and {@code (0, 1)} form a right
     * triangle and are not collinear.
     * </p>
     */
    @Test
    public void testPointIsColinearFalse() {
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(1.0, 0.0);
        Point p3 = new Point(0.0, 1.0);

        assertFalse("(0, 0), (1, 0), (0, 1) must not be collinear", p1.isColinearTo(p2, p3));
    }

    /**
     * Verifies {@link Point#middlePoint(Point)} returns the exact midpoint between two points.
     * <p>
     * <b>Math Logic:</b> midpoint of {@code (0,0)} and {@code (4,6)} = {@code (2,3)}.
     * </p>
     */
    @Test
    public void testPointMiddlePoint() {
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(4.0, 6.0);
        Point mid = p1.middlePoint(p2);

        assertNotNull("middlePoint() must not return null", mid);
        assertEquals("Middle x must be 2.0", 2.0, mid.x, DELTA);
        assertEquals("Middle y must be 3.0", 3.0, mid.y, DELTA);
    }

    /**
     * Verifies {@link Point#equals(Object)} returns {@code true} for two points with identical
     * coordinates and {@code false} for two points with different coordinates.
     */
    @Test
    public void testPointEquals() {
        Point a = new Point(1.0, 2.0);
        Point b = new Point(1.0, 2.0);
        Point c = new Point(3.0, 4.0);

        assertTrue("Identical coordinates must be equal", a.equals(b));
        assertFalse("Different coordinates must not be equal", a.equals(c));
    }

    /**
     * Verifies that {@link Point#toString()} produces the expected {@code "(x, y)"} format.
     * <p>
     * <b>Scenario:</b> {@code new Point(1.0, 2.0).toString()} must contain {@code "1"} and {@code "2"}.
     * </p>
     */
    @Test
    public void testPointToString() {
        Point p = new Point(1.0, 2.0);
        String str = p.toString();

        assertNotNull("toString() must not return null", str);
        assertTrue("toString() must contain the x value", str.contains("1"));
        assertTrue("toString() must contain the y value", str.contains("2"));
    }

    /**
     * Verifies the constructor stores {@code originPoint} and {@code endPoint} correctly.
     */
    @Test
    public void testVectorConstructor() {
        Point origin = new Point(0.0, 0.0);
        Point end = new Point(3.0, 4.0);
        Vector v = new Vector(origin, end);

        assertEquals("originPoint.x must be 0.0", 0.0, v.originPoint.x, DELTA);
        assertEquals("originPoint.y must be 0.0", 0.0, v.originPoint.y, DELTA);
        assertEquals("endPoint.x must be 3.0", 3.0, v.endPoint.x, DELTA);
        assertEquals("endPoint.y must be 4.0", 4.0, v.endPoint.y, DELTA);
    }

    /**
     * Verifies {@link Vector#add(Vector)} returns the correct vector sum.
     * <p>
     * <b>Math Logic:</b> {@code v1 = (1,2)→(3,4)}, {@code v2 = (0,0)→(1,1)}.
     * Component-wise addition: origin {@code (1,2)}, end {@code (4,5)}.
     * </p>
     */
    @Test
    public void testVectorAdd() {
        Vector v1 = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        Vector v2 = new Vector(new Point(0.0, 0.0), new Point(1.0, 1.0));

        Vector result = v1.add(v2);

        assertNotNull("add() result must not be null", result);
        assertEquals("result.originPoint.x = 1 + 0 = 1", 1.0, result.originPoint.x, DELTA);
        assertEquals("result.originPoint.y = 2 + 0 = 2", 2.0, result.originPoint.y, DELTA);
        assertEquals("result.endPoint.x = 3 + 1 = 4", 4.0, result.endPoint.x, DELTA);
        assertEquals("result.endPoint.y = 4 + 1 = 5", 5.0, result.endPoint.y, DELTA);
    }

    /**
     * Verifies {@link Vector#subtract(Vector)} returns the correct vector difference.
     * <p>
     * <b>Math Logic:</b> {@code v1 = (3,4)→(5,6)}, {@code v2 = (1,1)→(2,2)}.
     * Component-wise subtraction: origin {@code (2,3)}, end {@code (3,4)}.
     * </p>
     */
    @Test
    public void testVectorSubtract() {
        Vector v1 = new Vector(new Point(3.0, 4.0), new Point(5.0, 6.0));
        Vector v2 = new Vector(new Point(1.0, 1.0), new Point(2.0, 2.0));

        Vector result = v1.subtract(v2);

        assertNotNull("subtract() result must not be null", result);
        assertEquals("result.originPoint.x = 3 - 1 = 2", 2.0, result.originPoint.x, DELTA);
        assertEquals("result.originPoint.y = 4 - 1 = 3", 3.0, result.originPoint.y, DELTA);
        assertEquals("result.endPoint.x = 5 - 2 = 3", 3.0, result.endPoint.x, DELTA);
        assertEquals("result.endPoint.y = 6 - 2 = 4", 4.0, result.endPoint.y, DELTA);
    }

    /**
     * Verifies {@link Vector#scalarProduct(double)} scales the vector's coordinates.
     * <p>
     * <b>Math Logic:</b> {@code v = (1,2)→(3,4)}, scalar {@code 2}.
     * Each coordinate is multiplied by {@code 2}: origin {@code (2,4)}, end {@code (6,8)}.
     * </p>
     */
    @Test
    public void testVectorScalarProduct() {
        Vector v = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        Vector result = v.scalarProduct(2.0);

        assertNotNull("scalarProduct() result must not be null", result);
        assertEquals("result.originPoint.x = 1 * 2 = 2", 2.0, result.originPoint.x, DELTA);
        assertEquals("result.originPoint.y = 2 * 2 = 4", 4.0, result.originPoint.y, DELTA);
        assertEquals("result.endPoint.x = 3 * 2 = 6", 6.0, result.endPoint.x, DELTA);
        assertEquals("result.endPoint.y = 4 * 2 = 8", 8.0, result.endPoint.y, DELTA);
    }

    /**
     * Verifies {@link Vector#centroid(Vector, Vector)} returns the barycenter of three vectors.
     * <p>
     * <b>Math Logic:</b> The centroid is computed from the average of the origin points.
     * Origins: {@code v1=(0,0)}, {@code v2=(3,0)}, {@code v3=(0,3)} →
     * centroid {@code x = 1}, {@code y = 1}.
     * </p>
     */
    @Test
    public void testVectorCentroid() {
        Vector v1 = new Vector(new Point(0.0, 0.0), new Point(1.0, 0.0));
        Vector v2 = new Vector(new Point(3.0, 0.0), new Point(4.0, 0.0));
        Vector v3 = new Vector(new Point(0.0, 3.0), new Point(1.0, 3.0));

        Point centroid = v1.centroid(v2, v3);

        assertNotNull("centroid() must not return null", centroid);
        assertEquals("centroid x = (0 + 3 + 0) / 3 = 1.0", 1.0, centroid.x, DELTA);
        assertEquals("centroid y = (0 + 0 + 3) / 3 = 1.0", 1.0, centroid.y, DELTA);
    }

    /**
     * Verifies {@link Vector#equals(Object)} returns {@code true} for identical vectors and
     * {@code false} for different ones.
     */
    @Test
    public void testVectorEquals() {
        Vector v1 = new Vector(new Point(0.0, 0.0), new Point(1.0, 1.0));
        Vector v2 = new Vector(new Point(0.0, 0.0), new Point(1.0, 1.0));
        Vector v3 = new Vector(new Point(0.0, 0.0), new Point(2.0, 2.0));

        assertTrue("Identical vectors must be equal", v1.equals(v2));
        assertFalse("Different end points must not be equal", v1.equals(v3));
    }

    /**
     * Verifies that {@link Vector#toString()} produces the expected
     * {@code "(x, y) -> (m, n)"} format.
     */
    @Test
    public void testVectorToString() {
        Vector v = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        String str = v.toString();

        assertNotNull("toString() must not return null", str);
        assertTrue("toString() must contain origin x value", str.contains("1"));
        assertTrue("toString() must contain origin y value", str.contains("2"));
        assertTrue("toString() must contain end x value", str.contains("3"));
        assertTrue("toString() must contain end y value", str.contains("4"));
        assertTrue("toString() must contain '->'", str.contains("->"));
    }
}