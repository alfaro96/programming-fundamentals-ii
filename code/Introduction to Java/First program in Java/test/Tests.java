import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Sum} class: both summation methods must return the sum of the
 * integers from 0 to <i>n</i>.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Sum
 */
public class Tests {

    /**
     * The values of <i>n</i> used in the assignment.
     */
    private static final int[] VALUES = {3, 4, 13, 21, 67, 102, 155, 365, 1007};

    /**
     * The sum of the integers from 0 to <i>n</i> for each value in {@link #VALUES}.
     */
    private static final int[] EXPECTED = {6, 10, 91, 231, 2278, 5253, 12090, 66795, 507528};

    /**
     * Checks {@link Sum#sum1} with the value of the individual test: {@code sum1(11)} is 66.
     */
    @Test
    public void testSum1Eleven() {
        assertEquals("sum1(11) should be 66", 66, Sum.sum1(11));
    }

    /**
     * Checks {@link Sum#sum2} with the value of the individual test: {@code sum2(11)} is 66.
     */
    @Test
    public void testSum2Eleven() {
        assertEquals("sum2(11) should be 66", 66, Sum.sum2(11));
    }

    /**
     * Checks both methods with the smallest values: <i>n</i> = 0 gives 0 and <i>n</i> = 1 gives 1.
     */
    @Test
    public void testSmallestValues() {
        assertEquals("sum1(0) should be 0", 0, Sum.sum1(0));
        assertEquals("sum2(0) should be 0", 0, Sum.sum2(0));
        assertEquals("sum1(1) should be 1", 1, Sum.sum1(1));
        assertEquals("sum2(1) should be 1", 1, Sum.sum2(1));
    }

    /**
     * Checks both methods against the expected result for every value in the
     * assignment's list, so that two methods wrong in the same way do not pass.
     */
    @Test
    public void testValues() {
        for (int i = 0; i < VALUES.length; i++) {
            int n = VALUES[i];
            assertEquals("sum1(" + n + ") should be " + EXPECTED[i], EXPECTED[i], Sum.sum1(n));
            assertEquals("sum2(" + n + ") should be " + EXPECTED[i], EXPECTED[i], Sum.sum2(n));
        }
    }
}
