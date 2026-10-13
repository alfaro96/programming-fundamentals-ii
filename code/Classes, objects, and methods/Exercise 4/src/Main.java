/**
 * Exercise 4: tries every method of the {@link Point} and {@link Vector} classes.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Point
 * @see Vector
 */
class Main {

    /**
     * Creates four points and five vectors, prints them and prints the result of each
     * operation, next to the value it should have.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point(3.0, 4.0);
        Point p3 = new Point(1.0, 1.0);
        Point p4 = new Point(2.0, 2.0);
        System.out.println("p1 = " + p1.describe() + " (should be the origin)");
        System.out.println("p2 = " + p2.describe());
        System.out.println("p3 = " + p3.describe());
        System.out.println("p4 = " + p4.describe());

        System.out.println("Distance p1-p2: " + p1.distance(p2) + " (should be 5.0)");
        System.out.println("Distance p3-p4: " + p3.distance(p4) + " (should be 1.414...)");

        // p1, p3 and p4 lie on the line y = x; p2 does not
        System.out.println("p1, p3, p4 collinear: " + p1.isCollinearTo(p3, p4) + " (should be true)");
        System.out.println("p1, p2, p3 collinear: " + p1.isCollinearTo(p2, p3) + " (should be false)");

        System.out.println("Middle of p1 and p2: " + p1.middlePoint(p2).describe() + " (should be (1.5, 2.0))");
        System.out.println("Middle of p3 and p4: " + p3.middlePoint(p4).describe() + " (should be (1.5, 1.5))");

        Point p5 = new Point(3.0, 4.0);
        System.out.println("p2 equals p5: " + p2.isEqualTo(p5) + " (should be true)");
        System.out.println("p2 equals p3: " + p2.isEqualTo(p3) + " (should be false)");

        Vector v1 = new Vector(new Point(0.0, 0.0), new Point(1.0, 0.0));
        Vector v2 = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        Vector v3 = new Vector(new Point(3.0, 0.0), new Point(4.0, 0.0));
        Vector v4 = new Vector(new Point(0.0, 3.0), new Point(1.0, 3.0));
        Vector v5 = new Vector(new Point(1.0, 1.0), new Point(2.0, 2.0));
        System.out.println("v1 = " + v1.describe());
        System.out.println("v2 = " + v2.describe());
        System.out.println("v3 = " + v3.describe());
        System.out.println("v4 = " + v4.describe());
        System.out.println("v5 = " + v5.describe());

        System.out.println("v2 + v5 = " + v2.add(v5).describe() + " (should be (2.0, 3.0) -> (5.0, 6.0))");
        System.out.println("v2 - v5 = " + v2.subtract(v5).describe() + " (should be (0.0, 1.0) -> (1.0, 2.0))");
        System.out.println("v2 * 2 = " + v2.scalarProduct(2.0).describe() + " (should be (2.0, 4.0) -> (6.0, 8.0))");

        System.out.println("Centroid of v1, v3, v4: " + v1.centroid(v3, v4).describe() + " (should be (1.0, 1.0))");

        Vector v6 = new Vector(new Point(1.0, 2.0), new Point(3.0, 4.0));
        System.out.println("v2 equals v6: " + v2.isEqualTo(v6) + " (should be true)");
        System.out.println("v2 equals v5: " + v2.isEqualTo(v5) + " (should be false)");
    }
}
