/**
 * Laboratory assignment 0: first program in Java.
 * Computes the sum of the integers from 0 to <i>n</i> in two ways and checks that both agree.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Sum {

    /**
     * Computes the sum of the integers from 0 to {@code n} with a {@code for} loop.
     *
     * @param n the last integer to add.
     * @return the sum 0 + 1 + &hellip; + <i>n</i>.
     */
    public static int sum1(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }

    /**
     * Computes the sum of the integers from 0 to {@code n} with the formula
     * <i>n</i>(<i>n</i> + 1) / 2.
     *
     * @param n the last integer to add.
     * @return the sum 0 + 1 + &hellip; + <i>n</i>.
     */
    public static int sum2(int n) {
        return n * (n + 1) / 2;
    }

    /**
     * Tests {@code sum1} and {@code sum2}, first with a single value and then with a
     * list of values, checking that both methods return the same result.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.printf("sum1(11) = %d\n", sum1(11));
        System.out.printf("sum2(11) = %d\n", sum2(11));

        int[] values = {3, 4, 13, 21, 67, 102, 155, 365, 1007};
        for (int n : values) {
            System.out.printf("n = %d: sum1 = %d, sum2 = %d, equal: %b\n", n, sum1(n), sum2(n), sum1(n) == sum2(n));
        }
    }
}
