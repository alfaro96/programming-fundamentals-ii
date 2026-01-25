/**
 * Main application class to perform for testing for both
 * {@link Point} and {@link Vector} classes.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Entry point for the geometric testing suite.
     * Executes functional tests for point coordinates and vector arithmetic.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Point testing
        System.out.println("=== PART 1: POINT TESTING ===");

        // Define four points and display them
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(6, 8);
        Point p4 = new Point(1, 1);

        System.out.println("Point 1: " + p1.toString());
        System.out.println("Point 2: " + p2.toString());
        System.out.println("Point 3: " + p3.toString());
        System.out.println("Point 4: " + p4.toString());

        // Compute and show distances
        System.out.println("\nDistance between P1 and P2: " + p1.distance(p2));

        // Verify colinearity of three points
        System.out.println("Are P1, P2, and P3 colinear? " + p1.isColinearTo(p2, p3));
        System.out.println("Are P1, P2, and P4 colinear? " + p1.isColinearTo(p2, p4));

        // Compute middle point between pairs
        System.out.println("Middle point between P1 and P2: " + p1.middlePoint(p2));

        // Verify if specific pairs of points are equal
        Point p1Clone = new Point(0, 0);
        System.out.println("Is P1 equal to P1Clone? " + p1.equals(p1Clone));
        System.out.println("Is P1 equal to P2? " + p1.equals(p2));

        // 2. Vector testing
        System.out.println("\n\n=== PART 2: VECTOR TESTING ===");

        // Define five vectors and display them
        Vector v1 = new Vector(p1, p2); // (0, 0) -> (3, 4)
        Vector v2 = new Vector(p1, p4); // (0, 0) -> (1, 1)
        Vector v3 = new Vector(p2, p3); // (3, 4) -> (6, 8)
        Vector v4 = new Vector(p4, p2); // (1, 1) -> (3, 4)
        Vector v5 = new Vector(p1, p2); // (0, 0) -> (3, 4)

        System.out.println("Vector 1: " + v1.toString());
        System.out.println("Vector 2: " + v2.toString());
        System.out.println("Vector 3: " + v3.toString());
        System.out.println("Vector 4: " + v4.toString());
        System.out.println("Vector 5: " + v5.toString());

        // Compute addition, subtraction, and product
        System.out.println("\nAddition (V1 + V2): " + v1.add(v2).toString());
        System.out.println("Subtraction (V1 - V2): " + v1.subtract(v2).toString());
        System.out.println("Scalar Product (V1 * V2): " + v1.scalarProduct(v2).toString());

        // Determine the centroid of groups of vectors
        // The centroid is calculated based on the end points of V1, V2, and V3
        Point centroid = v1.centroid(v2, v3);
        System.out.println("\nCentroid of V1, V2, and V3: " + centroid);

        // Verify vector equality
        System.out.println("Is V1 equal to V5? " + v1.equals(v5));
        System.out.println("Is V1 equal to V2? " + v1.equals(v2));

        System.out.println("\nVerification completed successfully.");
    }
}
