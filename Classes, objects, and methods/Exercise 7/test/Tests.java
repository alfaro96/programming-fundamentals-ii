import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Matrix} class to verify 
 * the correctness of multi-dimensional array management, algebraic operations 
 * (addition, products, trace), and transposition.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Matrix
 */
public class Tests {

    /** * Delta value for floating-point comparisons ({@code double}). 
     * Essential for validating matrix cell values.
     */
    private static final double DELTA = 1e-9;

    /**
     * Verifies the variety of {@link Matrix} constructors.
     * <p>
     * <b>Requirement:</b> Support for square, rectangular, and value-initialized matrices.
     * </p>
     */
    @Test
    public void testConstructors() {
        // 1. Square matrix constructor
        Matrix square = new Matrix(3);
        assertEquals("Should have 3 rows", 3, square.nRows);
        assertEquals("Should have 3 columns", 3, square.nCols);

        // 2. Rectangular with initial value
        Matrix rectVal = new Matrix(2, 4, 7.5);
        assertEquals("Value at (1,3) should be 7.5", 7.5, rectVal.values[1][3], DELTA);

        // 3. Copy constructor
        Matrix original = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix copy = new Matrix(original);
        assertNotSame("Copy should be a different object reference", original, copy);
        assertEquals("Copy value should match original", 4.0, copy.values[1][1], DELTA);
    }

    /**
     * Verifies the {@link Matrix#add} operation.
     * <p>
     * <b>Requirement:</b> Performs addition and returns a <b>new</b> matrix.
     * </p>
     * <p><b>Validation:</b> Mismatched dimensions must return {@code null}.</p>
     */
    @Test
    public void testAddition() {
        Matrix m1 = new Matrix(2, 2, 5.0);
        Matrix m2 = new Matrix(2, 2, 3.0);
        Matrix diffSize = new Matrix(3, 3);

        Matrix result = m1.add(m2);

        assertNotNull("Addition of same size should succeed", result);
        assertEquals("Result at (0, 0) should be 8.0", 8.0, result.values[0][0], DELTA);
        assertNull("Addition of different sizes should return null", m1.add(diffSize));
    }

    /**
     * Verifies the {@link Matrix#scalarProduct} operation.
     * <p>
     * <b>Math Logic:</b> Each element $ a_{ij} $ is multiplied by the scalar $ k $.
     * </p>
     */
    @Test
    public void testScalarProduct() {
        Matrix m = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix result = m.scalarProduct(2.0);

        assertEquals("Value at (0, 1) should be 2 * 2 = 4", 4.0, result.values[0][1], DELTA);
        assertEquals("Value at (1, 0) should be 3 * 2 = 6", 6.0, result.values[1][0], DELTA);
    }

    /**
     * Verifies the {@link Matrix#product} operation.
     * <p>
     * <b>Math Logic:</b> $ C_{ij} = \sum_{k = 1}^{n} A_{ik} \cdot B_{kj} $.
     * </p>
     * <p><b>Validation:</b> Multiplying a $ 2 \times 3 $ by a $ 3 \times 2 $ matrix results in a $ 2 \times 2 $.</p>
     */
    @Test
    public void testMatrixProduct() {
        // A (2 x 3)
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });
        // B (3 x 2)
        Matrix b = new Matrix(new double[][]{
                {7, 8},
                {9, 10},
                {11, 12}
        });

        Matrix result = a.product(b);

        assertNotNull("Product should be valid as A.nCols == B.nRows", result);
        assertEquals("Result should have 2 rows", 2, result.nRows);
        assertEquals("Result should have 2 columns", 2, result.nCols);

        // Calculation: (1 * 7 + 2 * 9 + 3 * 11) = 7 + 18 + 33 = 58
        assertEquals("Result at (0, 0) should be 58.0", 58.0, result.values[0][0], DELTA);
    }

    /**
     * Verifies the {@link Matrix#trace} calculation.
     * <p>
     * <b>Requirement:</b> Sum of values on the main diagonal. Only for square matrices.
     * </p>
     */
    @Test
    public void testTrace() {
        Matrix square = new Matrix(new double[][]{
                {1, 0, 0},
                {0, 5, 0},
                {0, 0, 9}
        });
        Matrix rect = new Matrix(2, 3, 1.0);

        assertEquals("Trace of square matrix should be 15.0", 15.0, square.trace(), DELTA);
        assertEquals("Trace of non-square matrix should be 0.0", 0.0, rect.trace(), DELTA);
    }

    /**
     * Verifies the {@link Matrix#transpose} logic.
     * <p>
     * <b>Requirement:</b> $ n \times m $ matrix becomes $ m \times n $, and $ (i, j) $ moves to $ (j, i) $.
     * </p>
     */
    @Test
    public void testTranspose() {
        // 2 rows, 3 columns
        Matrix original = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        Matrix transposed = original.transpose();

        assertEquals("Transposed should have 3 rows", 3, transposed.nRows);
        assertEquals("Transposed should have 2 columns", 2, transposed.nCols);
        assertEquals("Value at (2, 1) should be original (1, 2) -> 6", 6.0, transposed.values[2][1], DELTA);
    }
}