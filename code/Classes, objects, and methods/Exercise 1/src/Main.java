/**
 * Exercise 1: tries every operation of the {@link Monomial} class.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Monomial
 */
class Main {

    /**
     * Creates several monomials, prints them and prints the result of each operation,
     * next to the value it should have.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        Monomial m1 = new Monomial(2.0, 3);
        Monomial m2 = new Monomial(3.0, 3);
        Monomial m3 = new Monomial(3.0, 2);

        System.out.println("m1 = " + m1.describe() + " (should be 2.0X^3)");
        System.out.println("m2 = " + m2.describe() + " (should be 3.0X^3)");
        System.out.println("m3 = " + m3.describe() + " (should be 3.0X^2)");

        // Same exponent: the result is a new monomial
        System.out.println("m1 + m2 = " + m1.addition(m2).describe() + " (should be 5.0X^3)");

        // Different exponents: there is no single monomial for the result
        System.out.println("m1 + m3 = " + m1.addition(m3) + " (should be null)");

        System.out.println("m1 * 4 = " + m1.scalarProduct(4.0).describe() + " (should be 8.0X^3)");
        System.out.println("m1 * m3 = " + m1.product(m3).describe() + " (should be 6.0X^5)");
        System.out.println("m1 at X = 2: " + m1.evaluate(2.0) + " (should be 16.0)");

        // The operations return new monomials: m1 is still the same
        System.out.println("m1 = " + m1.describe() + " (should still be 2.0X^3)");
    }
}
