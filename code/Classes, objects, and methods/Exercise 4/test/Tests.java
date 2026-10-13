import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Point} and {@link Vector} classes: their constructors, the geometric
 * operations on points, the operations on vectors, their written forms and equality.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Point
 * @see Vector
 */
public class Tests {

    /**
     * The largest difference allowed when comparing two {@code double} values.
     */
    private static final double DELTA = 1e-9;

    /**
     * Checks that a point has the expected coordinates.
     *
     * @param name the name of the point, for the failure messages.
     * @param x the expected <i>x</i> coordinate.
     * @param y the expected <i>y</i> coordinate.
     * @param p the point to check.
     */
    private static void assertPoint(String name, double x, double y, Point p) {
        assertNotNull(name + " should not be null", p);
        assertEquals(name + ".x should be " + x, x, p.x, DELTA);
        assertEquals(name + ".y should be " + y, y, p.y, DELTA);
    }

    /**
     * Checks that the constructor without arguments creates the origin, and the other one
     * stores the given coordinates.
     */
    @Test
    public void testPointConstructors() {
        assertPoint("new Point()", 0.0, 0.0, new Point());
        assertPoint("new Point(3.0, -4.0)", 3.0, -4.0, new Point(3.0, -4.0));
    }

    /**
     * Checks {@link Point#distance}: from (0, 0) to (3, 4) is 5, from (1, 1) to (-2, 5) is
     * 5 too, and from a point to itself is 0.
     */
    @Test
    public void testPointDistance() {
        assertEquals("From (0, 0) to (3, 4) should be 5.0", 5.0, new Point(0, 0).distance(new Point(3, 4)), DELTA);
        assertEquals("From (1, 1) to (-2, 5) should be 5.0", 5.0, new Point(1, 1).distance(new Point(-2, 5)), DELTA);
        assertEquals("From a point to itself should be 0.0", 0.0, new Point(2, 7).distance(new Point(2, 7)), DELTA);
    }

    /**
     * Checks {@link Point#isCollinearTo} with three points on one line, on a vertical line,
     * and three that are not on one line.
     */
    @Test
    public void testPointIsCollinearTo() {
        assertTrue("(0, 0), (1, 1) and (2, 2) should be collinear",
                new Point(0, 0).isCollinearTo(new Point(1, 1), new Point(2, 2)));
        assertTrue("(1, 0), (1, 5) and (1, -3) should be collinear",
                new Point(1, 0).isCollinearTo(new Point(1, 5), new Point(1, -3)));
        assertFalse("(0, 0), (1, 0) and (0, 1) should not be collinear",
                new Point(0, 0).isCollinearTo(new Point(1, 0), new Point(0, 1)));
    }

    /**
     * Checks {@link Point#middlePoint}: halfway between (0, 0) and (4, 6) is (2, 3), and
     * both points stay unchanged.
     */
    @Test
    public void testPointMiddlePoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 6);

        assertPoint("The middle point", 2.0, 3.0, p1.middlePoint(p2));
        assertPoint("p1", 0.0, 0.0, p1);
        assertPoint("p2", 4.0, 6.0, p2);
    }

    /**
     * Checks {@link Point#describe}: (1.0, 2.5) is written {@code "(1.0, 2.5)"}.
     */
    @Test
    public void testPointToString() {
        assertEquals("(1.0, 2.5) should be written (1.0, 2.5)", "(1.0, 2.5)", new Point(1.0, 2.5).describe());
    }

    /**
     * Checks {@link Point#isEqualTo}: two points with the same coordinates are equal, even with a
     * rounding error, and two with different coordinates are not.
     */
    @Test
    public void testPointEquals() {
        Point a = new Point(0.3, 2.0);

        assertTrue("Points with the same coordinates should be equal", a.isEqualTo(new Point(0.3, 2.0)));
        assertTrue("0.1 + 0.2 and 0.3 should be equal", a.isEqualTo(new Point(0.1 + 0.2, 2.0)));
        assertFalse("Points with a different x should not be equal", a.isEqualTo(new Point(0.4, 2.0)));
        assertFalse("Points with a different y should not be equal", a.isEqualTo(new Point(0.3, 2.1)));
    }

    /**
     * Checks that the constructor of {@link Vector} stores both points.
     */
    @Test
    public void testVectorConstructor() {
        Point origin = new Point(0, 0);
        Point end = new Point(3, 4);
        Vector v = new Vector(origin, end);

        assertSame("originPoint should be the point given", origin, v.originPoint);
        assertSame("endPoint should be the point given", end, v.endPoint);
    }

    /**
     * Checks {@link Vector#add}: ((1, 2) &rarr; (3, 4)) + ((0, 0) &rarr; (1, 1)) is
     * (1, 2) &rarr; (4, 5), and both vectors stay unchanged.
     */
    @Test
    public void testVectorAdd() {
        Vector v1 = new Vector(new Point(1, 2), new Point(3, 4));
        Vector v2 = new Vector(new Point(0, 0), new Point(1, 1));

        Vector result = v1.add(v2);

        assertPoint("The origin of the sum", 1.0, 2.0, result.originPoint);
        assertPoint("The end of the sum", 4.0, 5.0, result.endPoint);
        assertPoint("The end of v1", 3.0, 4.0, v1.endPoint);
        assertPoint("The end of v2", 1.0, 1.0, v2.endPoint);
    }

    /**
     * Checks {@link Vector#subtract}: ((3, 4) &rarr; (5, 6)) &minus; ((1, 1) &rarr; (2, 2)) is
     * (2, 3) &rarr; (3, 4), and the first vector stays unchanged.
     */
    @Test
    public void testVectorSubtract() {
        Vector v1 = new Vector(new Point(3, 4), new Point(5, 6));
        Vector v2 = new Vector(new Point(1, 1), new Point(2, 2));

        Vector result = v1.subtract(v2);

        assertPoint("The origin of the difference", 2.0, 3.0, result.originPoint);
        assertPoint("The end of the difference", 3.0, 4.0, result.endPoint);
        assertPoint("The origin of v1", 3.0, 4.0, v1.originPoint);
    }

    /**
     * Checks {@link Vector#scalarProduct}: ((1, 2) &rarr; (3, 4)) &middot; 2 is
     * (2, 4) &rarr; (6, 8), and the vector stays unchanged.
     */
    @Test
    public void testVectorScalarProduct() {
        Vector v = new Vector(new Point(1, 2), new Point(3, 4));

        Vector result = v.scalarProduct(2.0);

        assertPoint("The origin of the product", 2.0, 4.0, result.originPoint);
        assertPoint("The end of the product", 6.0, 8.0, result.endPoint);
        assertPoint("The end of v", 3.0, 4.0, v.endPoint);
    }

    /**
     * Checks {@link Vector#centroid}: the average of the three origin points, (0, 0), (3, 0)
     * and (0, 6), is (1, 2).
     */
    @Test
    public void testVectorCentroid() {
        Vector v1 = new Vector(new Point(0, 0), new Point(1, 0));
        Vector v2 = new Vector(new Point(3, 0), new Point(4, 5));
        Vector v3 = new Vector(new Point(0, 6), new Point(1, 3));

        assertPoint("The centroid", 1.0, 2.0, v1.centroid(v2, v3));
    }

    /**
     * Checks {@link Vector#describe}: (1, 2) &rarr; (3, 4) is written
     * {@code "(1.0, 2.0) -> (3.0, 4.0)"}.
     */
    @Test
    public void testVectorToString() {
        Vector v = new Vector(new Point(1, 2), new Point(3, 4));

        assertEquals("It should be written (1.0, 2.0) -> (3.0, 4.0)", "(1.0, 2.0) -> (3.0, 4.0)", v.describe());
    }

    /**
     * Checks {@link Vector#isEqualTo}: vectors with the same points are equal, even if they are
     * different objects, and vectors that differ in either point are not.
     */
    @Test
    public void testVectorEquals() {
        Vector v1 = new Vector(new Point(0, 0), new Point(1, 1));

        assertTrue("Vectors with the same points should be equal",
                v1.isEqualTo(new Vector(new Point(0, 0), new Point(1, 1))));
        assertFalse("Vectors with a different end should not be equal",
                v1.isEqualTo(new Vector(new Point(0, 0), new Point(2, 2))));
        assertFalse("Vectors with a different origin should not be equal",
                v1.isEqualTo(new Vector(new Point(1, 0), new Point(1, 1))));
    }
}
