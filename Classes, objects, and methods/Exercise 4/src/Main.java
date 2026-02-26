/**
 * Main application class to demonstrate the {@link Point} and {@link Vector} classes.
 * Creates {@link Point} and {@link Vector} instances and exercises all their operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Point
 * @see Vector
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Point tests

        // 1. Create four points and display them
        Point p1 = new Point(); // default (0, 0)
        Point p2 = new Point(3.0, 4.0);
        Point p3 = new Point(1.0, 1.0);
        Point p4 = new Point(2.0, 2.0);
        System.out.println("--- Points ---");
        System.out.println("p1 (default, expected (0.0, 0.0)): " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);
        System.out.println("p4: " + p4);
        System.out.println();

        // 2. Distance between pairs
        System.out.println("--- distance() ---");
        System.out.println("p1.distance(p2) (expected 5.0): " + p1.distance(p2));
        System.out.println("p3.distance(p4): " + p3.distance(p4));
        System.out.println();

        // 3. Collinearity - true case: p1(0, 0), p3(1, 1), p4(2, 2) are on y = x
        System.out.println("--- isColinearTo() ---");
        System.out.println("p1, p3, p4 collinear (expected true):  " + p1.isColinearTo(p3, p4));
        // False case: p1(0, 0), p2(3, 4), p3(1, 1) do not share a line
        System.out.println("p1, p2, p3 collinear (expected false): " + p1.isColinearTo(p2, p3));
        System.out.println();

        // 4. Middle point
        Point mid12 = p1.middlePoint(p2);
        System.out.println("--- middlePoint() ---");
        System.out.println("middlePoint(p1, p2) (expected (1.5, 2.0)): " + mid12);
        Point mid34 = p3.middlePoint(p4);
        System.out.println("middlePoint(p3, p4) (expected (1.5, 1.5)): " + mid34);
        System.out.println();

        // 5. equals()
        Point p5 = new Point(3.0, 4.0);
        System.out.println("--- equals() ---");
        System.out.println("p2.equals(p5) (expected true):  " + p2.equals(p5));
        System.out.println("p2.equals(p3) (expected false): " + p2.equals(p3));
        System.out.println();

        // Vector tests

        // 6. Create five vectors and display them
        Vector v1 = new Vector(new Point(0.0, 0.0), new Point(1.0, 0.0));
        Vector v2 = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        Vector v3 = new Vector(new Point(3.0, 0.0), new Point(4.0, 0.0));
        Vector v4 = new Vector(new Point(0.0, 3.0), new Point(1.0, 3.0));
        Vector v5 = new Vector(new Point(1.0, 1.0), new Point(2.0, 2.0));
        System.out.println("--- Vectors ---");
        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);
        System.out.println("v3: " + v3);
        System.out.println("v4: " + v4);
        System.out.println("v5: " + v5);
        System.out.println();

        // 7. add()
        Vector addV = v2.add(v5);
        System.out.println("--- add() ---");
        System.out.println("v2.add(v5): " + addV);
        System.out.println("Expected origin (2.0, 3.0), end (5.0, 6.0)");
        System.out.println();

        // 8. subtract()
        Vector subV = v2.subtract(v5);
        System.out.println("--- subtract() ---");
        System.out.println("v2.subtract(v5): " + subV);
        System.out.println("Expected origin (0.0, 1.0), end (1.0, 2.0)");
        System.out.println();

        // 9. scalarProduct()
        Vector scalV = v2.scalarProduct(2.0);
        System.out.println("--- scalarProduct() ---");
        System.out.println("v2.scalarProduct(2.0): " + scalV);
        System.out.println("Expected origin (2.0, 4.0), end (6.0, 8.0)");
        System.out.println();

        // 10. centroid() of v1, v3, v4
        Point centroid = v1.centroid(v3, v4);
        System.out.println("--- centroid() ---");
        System.out.println("centroid of v1, v3, v4 (expected (1.0, 1.0)): " + centroid);
        System.out.println();

        // 11. equals()
        Vector v2copy = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        System.out.println("--- equals() ---");
        System.out.println("v2.equals(v2copy) (expected true):  " + v2.equals(v2copy));
        System.out.println("v2.equals(v5) (expected false): " + v2.equals(v5));
        System.out.println();
    }
}
