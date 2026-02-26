/**
 * Main application class to demonstrate the {@link Matrix} class.
 * Creates {@link Matrix} instances using all constructors and exercises all their operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Matrix
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Matrix(int n) - square matrix with default values (0.0)
        Matrix m1 = new Matrix(3);
        System.out.println("--- Matrix(3) - square, defaults to 0.0 ---");
        System.out.println("nRows (expected 3): " + m1.nRows);
        System.out.println("nCols (expected 3): " + m1.nCols);
        System.out.println("values[0][0] (expected 0.0): " + m1.values[0][0]);
        m1.display();
        System.out.println();

        // 2. Matrix(int n, double val) - square matrix with constant value
        Matrix m2 = new Matrix(2, 5.0);
        System.out.println("--- Matrix(2, 5.0) - square, all 5.0 ---");
        System.out.println("nRows (expected 2): " + m2.nRows);
        System.out.println("nCols (expected 2): " + m2.nCols);
        System.out.println("values[0][0] (expected 5.0): " + m2.values[0][0]);
        m2.display();
        System.out.println();

        // 3. Matrix(int n, int m) - rectangular matrix with default values
        Matrix m3 = new Matrix(2, 3);
        System.out.println("--- Matrix(2, 3) - rectangular, defaults to 0.0 ---");
        System.out.println("nRows (expected 2): " + m3.nRows);
        System.out.println("nCols (expected 3): " + m3.nCols);
        m3.display();
        System.out.println();

        // 4. Matrix(int n, int m, double val) - rectangular with constant value
        Matrix m4 = new Matrix(2, 3, 7.0);
        System.out.println("--- Matrix(2, 3, 7.0) - rectangular, all 7.0 ---");
        System.out.println("values[1][2] (expected 7.0): " + m4.values[1][2]);
        m4.display();
        System.out.println();

        // 5. Matrix(double[][] values) - from a 2D array
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Matrix m5 = new Matrix(data);
        System.out.println("--- Matrix({{1,2},{3,4}}) ---");
        System.out.println("nRows (expected 2): " + m5.nRows + " nCols (expected 2): " + m5.nCols);
        m5.display();
        System.out.println();

        // 6. Matrix(Matrix other) - copy constructor (deep copy)
        Matrix m5copy = new Matrix(m5);
        m5copy.values[0][0] = 99.0;
        System.out.println("--- Matrix(Matrix other) - copy constructor ---");
        System.out.println("original.values[0][0] (expected 1.0):  " + m5.values[0][0]);
        System.out.println("copy.values[0][0] (expected 99.0): " + m5copy.values[0][0]);
        System.out.println();

        // 7. add() - element-wise addition
        // | 1 2 |   | 5 6 |   |  6  8 |
        // | 3 4 | + | 7 8 | = | 10 12 |
        Matrix ma = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix mb = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}});
        Matrix addResult = ma.add(mb);
        System.out.println("--- add() ---");
        System.out.println("MA:");
        ma.display();
        System.out.println("MB:");
        mb.display();
        System.out.println("MA + MB (expected [[6,8],[10,12]]):");
        addResult.display();
        System.out.println();

        // 8. scalarProduct() - 2 * MA
        Matrix scalarResult = ma.scalarProduct(2.0);
        System.out.println("--- scalarProduct(2.0) ---");
        System.out.println("2 * MA (expected [[2,4],[6,8]]):");
        scalarResult.display();
        System.out.println();

        // 9. product() - standard matrix multiplication
        // | 1 2 |   | 5 6 |   | 19 22 |
        // | 3 4 | x | 7 8 | = | 43 50 |
        Matrix productResult = ma.product(mb);
        System.out.println("--- product() ---");
        System.out.println("MA x MB (expected [[19,22],[43,50]]):");
        productResult.display();
        System.out.println("result[0][0] (expected 19.0): " + productResult.values[0][0]);
        System.out.println("result[1][1] (expected 50.0): " + productResult.values[1][1]);
        System.out.println();

        // 10. trace() - sum of main diagonal of a square matrix
        double trace = ma.trace();
        System.out.println("--- trace() ---");
        System.out.println("MA:");
        ma.display();
        System.out.println("trace(MA) (expected 5.0 = 1+4): " + trace);
        System.out.println();

        // 11. transpose() - swap rows and columns
        // | 1 2 3 |T   | 1 4 |
        // | 4 5 6 |  = | 2 5 |
        //               | 3 6 |
        Matrix rect = new Matrix(new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}});
        Matrix transposed = rect.transpose();
        System.out.println("--- transpose() ---");
        System.out.println("Original (2x3):");
        rect.display();
        System.out.println("Transposed (expected 3x2):");
        transposed.display();
        System.out.println("nRows (expected 3): " + transposed.nRows);
        System.out.println("nCols (expected 2): " + transposed.nCols);
        System.out.println("transposed[1][0] (expected 2.0): " + transposed.values[1][0]);
        System.out.println("transposed[0][1] (expected 4.0): " + transposed.values[0][1]);
        System.out.println();
    }
}
