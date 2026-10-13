import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Matrix} class: its six constructors, the algebraic operations, which must
 * return new matrices, the trace and the transpose.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Matrix
 */
public class Tests {

    /**
     * The largest difference allowed when comparing two {@code double} values.
     */
    private static final double DELTA = 1e-9;

    /**
     * The 2 &times; 2 matrix with rows (1, 2) and (3, 4).
     */
    private static final double[][] A = {{1, 2}, {3, 4}};

    /**
     * The 2 &times; 2 matrix with rows (5, 6) and (7, 8).
     */
    private static final double[][] B = {{5, 6}, {7, 8}};

    /**
     * The 2 &times; 3 matrix with rows (1, 2, 3) and (4, 5, 6).
     */
    private static final double[][] C = {{1, 2, 3}, {4, 5, 6}};

    /**
     * Checks that a matrix has the expected size and values.
     *
     * @param expected the expected values, one array per row.
     * @param m the matrix to check.
     */
    private static void assertMatrix(double[][] expected, Matrix m) {
        assertNotNull("The matrix should not be null", m);
        assertEquals("nRows should be " + expected.length, expected.length, m.nRows);
        assertEquals("nCols should be " + expected[0].length, expected[0].length, m.nCols);
        assertEquals("values should have " + expected.length + " rows", expected.length, m.values.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals("Row " + i + " should have " + expected[i].length + " values", expected[i].length, m.values[i].length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals("values[" + i + "][" + j + "] should be " + expected[i][j],
                        expected[i][j], m.values[i][j], DELTA);
            }
        }
    }

    /**
     * Checks {@code Matrix(int n)}: a 3 &times; 3 matrix with every value at 0.
     */
    @Test
    public void testSquareConstructor() {
        assertMatrix(new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new Matrix(3));
    }

    /**
     * Checks {@code Matrix(int n, double val)}: a 2 &times; 2 matrix with every value at 5.
     */
    @Test
    public void testSquareValueConstructor() {
        assertMatrix(new double[][]{{5, 5}, {5, 5}}, new Matrix(2, 5.0));
    }

    /**
     * Checks {@code Matrix(int n, int m)}: a matrix with 2 rows and 3 columns, every value at 0.
     */
    @Test
    public void testRectangularConstructor() {
        assertMatrix(new double[][]{{0, 0, 0}, {0, 0, 0}}, new Matrix(2, 3));
    }

    /**
     * Checks {@code Matrix(int n, int m, double val)}: a matrix with 2 rows and 3 columns,
     * every value at 7.
     */
    @Test
    public void testRectangularValueConstructor() {
        assertMatrix(new double[][]{{7, 7, 7}, {7, 7, 7}}, new Matrix(2, 3, 7.0));
    }

    /**
     * Checks {@code Matrix(double[][] values)}: the matrix takes the size and values of the
     * array, copied, so changing the array afterwards does not change the matrix.
     */
    @Test
    public void testArrayConstructor() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}};
        Matrix m = new Matrix(data);
        data[0][0] = 99;

        assertMatrix(C, m);
    }

    /**
     * Checks {@code Matrix(Matrix other)}: the copy has the same values as the original, and
     * changing one does not change the other.
     */
    @Test
    public void testCopyConstructor() {
        Matrix original = new Matrix(A);
        Matrix copy = new Matrix(original);

        assertMatrix(A, copy);
        copy.values[0][0] = 99;
        original.values[1][1] = -1;
        assertMatrix(new double[][]{{1, 2}, {3, -1}}, original);
        assertMatrix(new double[][]{{99, 2}, {3, 4}}, copy);
    }

    /**
     * Checks {@link Matrix#add}: A + B has rows (6, 8) and (10, 12), both operands stay
     * unchanged, and matrices of different sizes give {@code null}.
     */
    @Test
    public void testAdd() {
        Matrix a = new Matrix(A);
        Matrix b = new Matrix(B);

        assertMatrix(new double[][]{{6, 8}, {10, 12}}, a.add(b));
        assertMatrix(A, a);
        assertMatrix(B, b);
        assertNull("Matrices of different sizes should not be added", a.add(new Matrix(C)));
    }

    /**
     * Checks {@link Matrix#scalarProduct}: 2 &middot; C has rows (2, 4, 6) and (8, 10, 12), and C
     * stays unchanged.
     */
    @Test
    public void testScalarProduct() {
        Matrix c = new Matrix(C);

        assertMatrix(new double[][]{{2, 4, 6}, {8, 10, 12}}, c.scalarProduct(2.0));
        assertMatrix(C, c);
    }

    /**
     * Checks {@link Matrix#product} between square matrices: A &middot; B has rows (19, 22) and
     * (43, 50), B &middot; A has rows (23, 34) and (31, 46), and both operands stay unchanged.
     */
    @Test
    public void testProductSquare() {
        Matrix a = new Matrix(A);
        Matrix b = new Matrix(B);

        assertMatrix(new double[][]{{19, 22}, {43, 50}}, a.product(b));
        assertMatrix(new double[][]{{23, 34}, {31, 46}}, b.product(a));
        assertMatrix(A, a);
        assertMatrix(B, b);
    }

    /**
     * Checks {@link Matrix#product} between matrices of different sizes: A &middot; C, 2 &times; 2
     * by 2 &times; 3, is 2 &times; 3 with rows (9, 12, 15) and (19, 26, 33), and C &middot; A, whose
     * sizes do not fit, gives {@code null}.
     */
    @Test
    public void testProductRectangular() {
        Matrix a = new Matrix(A);
        Matrix c = new Matrix(C);

        assertMatrix(new double[][]{{9, 12, 15}, {19, 26, 33}}, a.product(c));
        assertNull("A 2 x 3 matrix by a 2 x 2 one should give null", c.product(a));
    }

    /**
     * Checks {@link Matrix#trace}: 1 + 4 = 5 for A, and 15 for the 3 &times; 3 matrix with every
     * value at 5.
     */
    @Test
    public void testTrace() {
        assertEquals("The trace of A should be 5.0", 5.0, new Matrix(A).trace(), DELTA);
        assertEquals("The trace of Matrix(3, 5.0) should be 15.0", 15.0, new Matrix(3, 5.0).trace(), DELTA);
    }

    /**
     * Checks {@link Matrix#transpose}: the transpose of C, 2 &times; 3, is 3 &times; 2 with rows
     * (1, 4), (2, 5) and (3, 6), and C stays unchanged.
     */
    @Test
    public void testTranspose() {
        Matrix c = new Matrix(C);

        assertMatrix(new double[][]{{1, 4}, {2, 5}, {3, 6}}, c.transpose());
        assertMatrix(C, c);
    }
}
