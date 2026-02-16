import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Sum} class to verify
 * the correctness of iterative and formulaic summation implementations.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Sum
 */
public class Tests {

    /**
     * Verifies the iterative implementation {@link Sum#sum1} with the specific
     * test value requested in the assignment logic.
     *
     * <p>Test Logic:</p>
     * <ul>
     * <li>Input: 11</li>
     * <li>Expected output: 66</li>
     * </ul>
     */
    @Test
    public void testIterativeSumSpecificValue() {
        int input = 11;
        int expected = 66;

        int result = Sum.sum1(input);

        assertEquals("The sum of the first 11 numbers should be 66", expected, result);
    }

    /**
     * Verifies the formulaic implementation {@link Sum#sum2} with the specific
     * test value requested in the assignment logic.
     *
     * <p>Test Logic:</p>
     * <ul>
     * <li>Input: 11</li>
     * <li>Expected output: 66</li>
     * </ul>
     */
    @Test
    public void testFormulaicSumSpecificValue() {
        int input = 11;
        int expected = 66;

        int result = Sum.sum2(input);

        assertEquals("The formula result for 11 should be 66", expected, result);
    }

    /**
     * Verifies the boundary condition where the input is 0.
     * Both methods must handle the lower limit correctly.
     *
     * <p>Test Logic:</p>
     * <ul>
     * <li>Input: 0</li>
     * <li>Expected output: 0</li>
     * </ul>
     */
    @Test
    public void testZeroBoundary() {
        int input = 0;
        int expected = 0;

        assertEquals("Iterative sum of 0 should be 0", expected, Sum.sum1(input));
        assertEquals("Formulaic sum of 0 should be 0", expected, Sum.sum2(input));
    }

    /**
     * Automates the verification process by comparing both implementation techniques
     * (iterative vs formulaic) against the dataset provided in the assignment tasks.
     *
     * <p>This test ensures that {@code sum1(n) == sum2(n)} for every n in the array:</p>
     * <p>{@code {3, 4, 13, 21, 67, 102, 155, 365, 1007}}</p>
     */
    @Test
    public void testBatchComparisonAndConsistency() {
        // The specific array provided in the "verification and testing" task
        int[] data = {3, 4, 13, 21, 67, 102, 155, 365, 1007};

        for (int n : data) {
            int iterativeResult = Sum.sum1(n);
            int formulaResult = Sum.sum2(n);

            // Verification: results must be identical regardless of the method used
            assertEquals("Results for input " + n + " must match between methods",
                    iterativeResult, formulaResult);
        }
    }
}
