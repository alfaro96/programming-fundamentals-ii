/**
 * Exercise 7: tries every constructor and method of the {@link Matrix} class.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Matrix
 */
class Main {

    /**
     * Creates matrices with every constructor, operates with them and prints each result,
     * with the values it should have written above it.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("Matrix(3), all 0:");
        new Matrix(3).display();
        System.out.println("Matrix(2, 5.0), all 5:");
        new Matrix(2, 5.0).display();
        System.out.println("Matrix(2, 3), 2 rows and 3 columns, all 0:");
        new Matrix(2, 3).display();
        System.out.println("Matrix(2, 3, 7.0), 2 rows and 3 columns, all 7:");
        new Matrix(2, 3, 7.0).display();

        double[][] dataA = {{1, 2}, {3, 4}};
        double[][] dataB = {{5, 6}, {7, 8}};
        Matrix a = new Matrix(dataA);
        Matrix b = new Matrix(dataB);
        System.out.println("A:");
        a.display();
        System.out.println("B:");
        b.display();

        // The copy has its own values
        Matrix copy = new Matrix(a);
        copy.values[0][0] = 99;
        System.out.println("A after changing its copy (should not change):");
        a.display();

        System.out.println("A + B (should be 6 8 / 10 12):");
        a.add(b).display();
        System.out.println("2 * A (should be 2 4 / 6 8):");
        a.scalarProduct(2).display();
        System.out.println("A * B (should be 19 22 / 43 50):");
        a.product(b).display();
        System.out.println("Trace of A: " + a.trace() + " (should be 5.0)");

        double[][] dataC = {{1, 2, 3}, {4, 5, 6}};
        Matrix c = new Matrix(dataC);
        System.out.println("C, 2 x 3:");
        c.display();
        System.out.println("Transpose of C, 3 x 2 (should be 1 4 / 2 5 / 3 6):");
        c.transpose().display();
        System.out.println("A * C, 2 x 3 (should be 9 12 15 / 19 26 33):");
        a.product(c).display();
        System.out.println("C * A: " + c.product(a) + " (should be null, the sizes do not fit)");
    }
}
