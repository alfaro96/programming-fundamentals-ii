import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Point} and {@link Vector} classes to verify
 * geometric calculations, vector operations, and object relationships.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Point
 * @see Vector
 */
public class Tests {

    /**
     * Delta value for floating-point comparisons ({@code double}).
     * Necessary for validating distance and coordinate calculations.
     */
    private static final double DELTA = 0.001;

    /**
     * Verifies {@link Point} constructors and {@link Point#toString} format.
     * <p>
     * <b>Requirement:</b> Default constructor (0, 0) and standard constructor.
     * <b>Format:</b> {@code "(x.x, y.y)"}.
     * </p>
     */
    @Test
    public void testPointConstructionAndToString() {
        Point pDefault = new Point();
        Point pStandard = new Point(1.5, 2.5);

        assertEquals("Default point should be (0.0, 0.0)", "(0.0, 0.0)", pDefault.toString());
        assertEquals("Standard point should be (1.5, 2.5)", "(1.5, 2.5)", pStandard.toString());
    }

    /**
     * Verifies {@link Point#distance} logic.
     * <p>
     * <b>Math logic:</b> Euclidean distance $ \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2} $.
     * </p>
     * Example: Distance between (0, 0) and (3, 4) should be 5.
     */
    @Test
    public void testPointDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        double distance = p1.distance(p2);

        assertEquals("Distance between (0, 0) and (3, 4) should be 5.0", 5.0, distance, DELTA);
    }

    /**
     * Verifies {@link Point#isColinearTo} logic.
     * <p>
     * <b>Requirement:</b> Returns {@code true} if three points lie on the same line.
     * </p>
     */
    @Test
    public void testPointColinearity() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2); // Colinear
        Point p4 = new Point(2, 3); // Not colinear

        assertTrue("Points (0, 0), (1, 1), (2, 2) should be colinear", p1.isColinearTo(p2, p3));
        assertFalse("Points (0, 0), (1, 1), (2, 3) should NOT be colinear", p1.isColinearTo(p2, p4));
    }

    /**
     * Verifies {@link Point#middlePoint} logic.
     * <p>
     * <b>Math logic:</b> Midpoint $ M = (\frac{x_1 + x_2}{2}, \frac{y_1 + y_2}{2}) $.
     * </p>
     */
    @Test
    public void testMiddlePoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 2);

        Point mid = p1.middlePoint(p2);
        Point expected = new Point(2, 1);

        assertEquals("Middle point should be (2, 1)", expected, mid);
    }

    /**
     * Verifies {@link Vector} constructor and {@link Vector#toString} format.
     * <p>
     * <b>Format:</b> {@code "Origin -> End"}.
     * </p>
     */
    @Test
    public void testVectorConstructionAndToString() {
        Point origin = new Point(0, 0);
        Point end = new Point(1, 1);
        Vector v = new Vector(origin, end);

        String expected = "(0.0, 0.0) -> (1.0, 1.0)";
        assertEquals("Vector toString format incorrect", expected, v.toString());
    }

    /**
     * Verifies {@link Vector#add(Vector)} logic.
     * <p>
     * <b>Implementation note:</b> The provided implementation sums origins and ends independently.
     * New origin = $ O_1 + O_2 $, New End = $ E_1 + E_2 $.
     * </p>
     */
    @Test
    public void testVectorAddition() {
        Vector v1 = new Vector(new Point(1, 1), new Point(2, 2));
        Vector v2 = new Vector(new Point(3, 3), new Point(4, 4));

        Vector result = v1.add(v2);

        // Origin: (1 + 3, 1 + 3) = (4, 4)
        // End: (2 + 4, 2 + 4) = (6, 6)
        Point expectedOrigin = new Point(4, 4);
        Point expectedEnd = new Point(6, 6);

        assertEquals("Origin point summation incorrect", expectedOrigin, result.originPoint);
        assertEquals("End point summation incorrect", expectedEnd, result.endPoint);
    }

    /**
     * Verifies {@link Vector#subtract} logic.
     * <p>
     * <b>Implementation note:</b> Subtracts components of origin and end points respectively.
     * </p>
     */
    @Test
    public void testVectorSubtraction() {
        Vector v1 = new Vector(new Point(5, 5), new Point(10, 10));
        Vector v2 = new Vector(new Point(1, 1), new Point(2, 2));

        Vector result = v1.subtract(v2);

        // Origin: (5 - 1, 5 - 1) = (4, 4)
        // End: (10 - 2, 10 - 2) = (8, 8)
        assertEquals("Origin point subtraction incorrect", new Point(4, 4), result.originPoint);
        assertEquals("End point subtraction incorrect", new Point(8, 8), result.endPoint);
    }

    /**
     * Verifies {@link Vector#scalarProduct} logic.
     * <p>
     * <b>Implementation note:</b> Performs component-wise multiplication of points.
     * </p>
     */
    @Test
    public void testVectorScalarProduct() {
        Vector v1 = new Vector(new Point(2, 2), new Point(3, 3));
        Vector v2 = new Vector(new Point(4, 4), new Point(5, 5));

        Vector result = v1.scalarProduct(v2);

        // Origin: (2 * 4, 2 * 4) = (8, 8)
        // End: (3 * 5, 3 * 5) = (15, 15)
        assertEquals("Origin product incorrect", new Point(8, 8), result.originPoint);
        assertEquals("End product incorrect", new Point(15, 15), result.endPoint);
    }

    /**
     * Verifies {@link Vector#centroid} logic.
     * <p>
     * <b>Implementation note:</b> Calculates the average of the <b>end points</b> of the three vectors.
     * Logic: $ \frac{E_1 + E_2 + E_3}{3} $.
     * </p>
     */
    @Test
    public void testVectorCentroid() {
        // We focus on end Points: (0, 0), (3, 0), (0, 4)
        // Centroid should be ((0 + 3 + 0) / 3, (0 + 0 + 4) / 3) = (1.0, 1.333...)
        Vector v1 = new Vector(new Point(0,0), new Point(0, 0));
        Vector v2 = new Vector(new Point(0,0), new Point(3, 0));
        Vector v3 = new Vector(new Point(0,0), new Point(0, 4));

        Point centroid = v1.centroid(v2, v3);

        assertEquals("Centroid X incorrect", 1.0, centroid.x, DELTA);
        assertEquals("Centroid Y incorrect", 4.0 / 3.0, centroid.y, DELTA);
    }

    /**
     * Verifies {@link Vector#equals} logic.
     * <p>
     * <b>Requirement:</b> Vectors are equal if both origin and end points are equal.
     * </p>
     */
    @Test
    public void testVectorEquals() {
        Vector v1 = new Vector(new Point(1, 1), new Point(2, 2));
        Vector v2 = new Vector(new Point(1, 1), new Point(2, 2));
        Vector v3 = new Vector(new Point(1, 1), new Point(2, 3)); // Diff end

        assertTrue("Identical vectors should be equal", v1.equals(v2));
        assertFalse("Different vectors should not be equal", v1.equals(v3));
    }
}