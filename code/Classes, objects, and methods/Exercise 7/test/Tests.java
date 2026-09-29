import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link Matrix} test suite.
 * <p>
 * This class performs unit testing on the {@link Matrix} class to verify the
 * correctness of constructors, algebraic operations, and structural transformations
 * on two-dimensional numerical matrices.
 * </p>
 * <p><b>Covered scenarios:</b></p>
 * <ul>
 * <li>{@code Matrix(int n)} – square matrix with default values ({@code 0.0}).</li>
 * <li>{@code Matrix(int n, double val)} – square matrix initialized to a constant.</li>
 * <li>{@code Matrix(int n, int m)} – rectangular matrix with default values.</li>
 * <li>{@code Matrix(int n, int m, double val)} – rectangular matrix with a constant.</li>
 * <li>{@code Matrix(double[][] values)} – matrix from a 2-D array.</li>
 * <li>{@code Matrix(Matrix other)} – copy constructor; changes to the copy must not affect the original.</li>
 * <li>{@link Matrix#add(Matrix)} – element-wise addition of two matrices.</li>
 * <li>{@link Matrix#scalarProduct(double)} – multiplication of every element by a scalar.</li>
 * <li>{@link Matrix#product(Matrix)} – standard matrix multiplication.</li>
 * <li>{@link Matrix#trace()} – sum of the main diagonal of a square matrix.</li>
 * <li>{@link Matrix#transpose()} – transposition swaps rows and columns.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Matrix
 */
public class Tests {

    /** Delta value for floating-point comparisons. */
    private static final double DELTA = 1e-9;

    /**
     * Verifies {@code Matrix(int n)} creates an {@code n×n} matrix whose every element
     * is the default value ({@code 0.0}).
     * <p>
     * <b>Scenario:</b> {@code new Matrix(3)} → {@code 3} rows, {@code 3} columns, all zeros.
     * </p>
     */
    @Test
    public void testSquareDefaultConstructor() {
        Matrix m = new Matrix(3);

        assertEquals("nRows must be 3", 3, m.nRows);
        assertEquals("nCols must be 3", 3, m.nCols);
        assertEquals("All elements must default to 0.0", 0.0, m.values[0][0], DELTA);
        assertEquals("All elements must default to 0.0", 0.0, m.values[2][2], DELTA);
    }

    /**
     * Verifies {@code Matrix(int n, double val)} creates an {@code n × n} matrix where every
     * element is initialized to {@code val}.
     * <p>
     * <b>Scenario:</b> {@code new Matrix(2, 5.0)} → {@code 2×2}, all elements {@code 5.0}.
     * </p>
     */
    @Test
    public void testSquareConstantConstructor() {
        Matrix m = new Matrix(2, 5.0);

        assertEquals("nRows must be 2", 2, m.nRows);
        assertEquals("nCols must be 2", 2, m.nCols);
        assertEquals("values[0][0] must be 5.0", 5.0, m.values[0][0], DELTA);
        assertEquals("values[1][1] must be 5.0", 5.0, m.values[1][1], DELTA);
        assertEquals("values[0][1] must be 5.0", 5.0, m.values[0][1], DELTA);
    }

    /**
     * Verifies {@code Matrix(int n, int m)} creates an {@code n×m} rectangular matrix
     * with all elements at the default value ({@code 0.0}).
     * <p>
     * <b>Scenario:</b> {@code new Matrix(2, 3)} → {@code 2} rows, {@code 3} columns, all zeros.
     * </p>
     */
    @Test
    public void testRectangularDefaultConstructor() {
        Matrix m = new Matrix(2, 3);

        assertEquals("nRows must be 2", 2, m.nRows);
        assertEquals("nCols must be 3", 3, m.nCols);
        assertEquals("All elements must default to 0.0", 0.0, m.values[1][2], DELTA);
    }

    /**
     * Verifies {@code Matrix(int n, int m, double val)} creates an {@code n×m} rectangular
     * matrix where every element is initialized to {@code val}.
     * <p>
     * <b>Scenario:</b> {@code new Matrix(2, 3, 7.0)} → {@code 2×3}, all elements {@code 7.0}.
     * </p>
     */
    @Test
    public void testRectangularConstantConstructor() {
        Matrix m = new Matrix(2, 3, 7.0);

        assertEquals("nRows must be 2", 2, m.nRows);
        assertEquals("nCols must be 3", 3, m.nCols);
        assertEquals("values[0][0] must be 7.0", 7.0, m.values[0][0], DELTA);
        assertEquals("values[1][2] must be 7.0", 7.0, m.values[1][2], DELTA);
    }

    /**
     * Verifies {@code Matrix(double[][] values)} stores the provided 2-D array
     * correctly and infers {@code nRows} and {@code nCols} from it.
     * <p>
     * <b>Scenario:</b>
     * <pre>
     *   | 1  2 |
     *   | 3  4 |
     * </pre>
     * </p>
     */
    @Test
    public void testArrayConstructor() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Matrix m = new Matrix(data);

        assertEquals("nRows must be 2", 2, m.nRows);
        assertEquals("nCols must be 2", 2, m.nCols);
        assertEquals("values[0][0] must be 1.0", 1.0, m.values[0][0], DELTA);
        assertEquals("values[0][1] must be 2.0", 2.0, m.values[0][1], DELTA);
        assertEquals("values[1][0] must be 3.0", 3.0, m.values[1][0], DELTA);
        assertEquals("values[1][1] must be 4.0", 4.0, m.values[1][1], DELTA);
    }

    /**
     * Verifies the copy constructor creates a deep copy so that modifications to the
     * copy do not affect the original.
     * <p>
     * <b>Scenario:</b> Copy a {@code 2×2} matrix, then change an element in the copy.
     * The corresponding element of the original must remain unchanged.
     * </p>
     */
    @Test
    public void testCopyConstructor() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Matrix original = new Matrix(data);
        Matrix copy = new Matrix(original);

        copy.values[0][0] = 99.0;

        assertEquals("Original must be unchanged after modifying the copy", 1.0, original.values[0][0], DELTA);
        assertEquals("Copy must reflect the modification", 99.0, copy.values[0][0], DELTA);
    }

    /**
     * Verifies {@link Matrix#add(Matrix)} (element-wise addition).
     * <p>
     * <b>Math Logic:</b>
     * <pre>
     *   | 1 2 |   | 5 6 |   |  6  8 |
     *   | 3 4 | + | 7 8 | = | 10 12 |
     * </pre>
     * </p>
     */
    @Test
    public void testAdd() {
        Matrix m1 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix m2 = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}});

        Matrix result = m1.add(m2);

        assertNotNull("add() result must not be null", result);
        assertEquals("result[0][0] = 1+5 = 6", 6.0, result.values[0][0], DELTA);
        assertEquals("result[0][1] = 2+6 = 8", 8.0, result.values[0][1], DELTA);
        assertEquals("result[1][0] = 3+7 = 10", 10.0, result.values[1][0], DELTA);
        assertEquals("result[1][1] = 4+8 = 12", 12.0, result.values[1][1], DELTA);
    }

    /**
     * Verifies {@link Matrix#scalarProduct(double)}.
     * <p>
     * <b>Math Logic:</b>
     * <pre>
     *       | 1 2 |   | 2 4 |
     *   2 × | 3 4 | = | 6 8 |
     * </pre>
     * </p>
     */
    @Test
    public void testScalarProduct() {
        Matrix m = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix result = m.scalarProduct(2.0);

        assertNotNull("scalarProduct() result must not be null", result);
        assertEquals("result[0][0] = 1*2 = 2", 2.0, result.values[0][0], DELTA);
        assertEquals("result[0][1] = 2*2 = 4", 4.0, result.values[0][1], DELTA);
        assertEquals("result[1][0] = 3*2 = 6", 6.0, result.values[1][0], DELTA);
        assertEquals("result[1][1] = 4*2 = 8", 8.0, result.values[1][1], DELTA);
    }

    /**
     * Verifies {@link Matrix#product(Matrix)} (standard matrix multiplication).
     * <p>
     * <b>Math Logic:</b>
     * <pre>
     *   | 1 2 |   | 5 6 |   | 1*5+2*7  1*6+2*8 |   | 19 22 |
     *   | 3 4 | × | 7 8 | = | 3*5+4*7  3*6+4*8 | = | 43 50 |
     * </pre>
     * </p>
     */
    @Test
    public void testProduct() {
        Matrix m1 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix m2 = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}});
        Matrix result = m1.product(m2);

        assertNotNull("product() result must not be null", result);
        assertEquals("result[0][0] = 1*5+2*7 = 19", 19.0, result.values[0][0], DELTA);
        assertEquals("result[0][1] = 1*6+2*8 = 22", 22.0, result.values[0][1], DELTA);
        assertEquals("result[1][0] = 3*5+4*7 = 43", 43.0, result.values[1][0], DELTA);
        assertEquals("result[1][1] = 3*6+4*8 = 50", 50.0, result.values[1][1], DELTA);
    }

    /**
     * Verifies {@link Matrix#trace()} (sum of the main diagonal).
     * <p>
     * <b>Math Logic:</b>
     * <pre>
     *   | 1 2 |  →  trace = 1 + 4 = 5
     *   | 3 4 |
     * </pre>
     * </p>
     */
    @Test
    public void testTrace() {
        Matrix m = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        double trace = m.trace();

        assertEquals("trace = values[0][0] + values[1][1] = 1 + 4 = 5", 5.0, trace, DELTA);
    }

    /**
     * Verifies {@link Matrix#transpose()}: element at position {@code (i, j)} moves to
     * {@code (j, i)} and the resulting shape is swapped.
     * <p>
     * <b>Math Logic:</b>
     * <pre>
     *   | 1 2 3 |ᵀ  =  | 1 4 |
     *   | 4 5 6 |       | 2 5 |
     *                    | 3 6 |
     * </pre>
     * </p>
     */
    @Test
    public void testTranspose() {
        Matrix m = new Matrix(new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}});
        Matrix transposed = m.transpose();

        assertNotNull("transpose() result must not be null", transposed);
        assertEquals("Transposed nRows must be original nCols = 3", 3, transposed.nRows);
        assertEquals("Transposed nCols must be original nRows = 2", 2, transposed.nCols);
        assertEquals("transposed[0][0] = original[0][0] = 1", 1.0, transposed.values[0][0], DELTA);
        assertEquals("transposed[1][0] = original[0][1] = 2", 2.0, transposed.values[1][0], DELTA);
        assertEquals("transposed[2][0] = original[0][2] = 3", 3.0, transposed.values[2][0], DELTA);
        assertEquals("transposed[0][1] = original[1][0] = 4", 4.0, transposed.values[0][1], DELTA);
        assertEquals("transposed[2][1] = original[1][2] = 6", 6.0, transposed.values[2][1], DELTA);
    }
}
