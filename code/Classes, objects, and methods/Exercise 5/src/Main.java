/**
 * Exercise 5: tries every constructor and method of the {@link Polynomial} class.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Polynomial
 * @see Monomial
 */
class Main {

    /**
     * Creates polynomials with the three constructors, operates with them and prints each
     * result next to the value it should have.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        // An empty polynomial, filled monomial by monomial
        Polynomial p1 = new Polynomial(3);
        System.out.println("p1 = " + p1.describe() + " (should be 0)");
        p1.addMonomial(new Monomial(2.0, 2));
        p1.addMonomial(new Monomial(1.0, 0));
        System.out.println("p1 = " + p1.describe() + " (should be 1.0X^0 + 2.0X^2)");

        // From its coefficients: 1 + 0X + 3X^2
        double[] coefficients = {1.0, 0.0, 3.0};
        Polynomial p2 = new Polynomial(coefficients);
        System.out.println("p2 = " + p2.describe() + " (should be 1.0X^0 + 3.0X^2)");

        // From its monomials, in any order
        Monomial[] monomials = {new Monomial(5.0, 1), new Monomial(2.0, 0)};
        Polynomial p3 = new Polynomial(monomials);
        System.out.println("p3 = " + p3.describe() + " (should be 2.0X^0 + 5.0X^1)");

        System.out.println("Monomial of p2 with exponent 2: " + p2.getMonomial(2).describe() + " (should be 3.0X^2)");
        System.out.println("Monomial of p2 with exponent 1: " + p2.getMonomial(1) + " (should be null)");

        p2.replaceMonomial(new Monomial(4.0, 2));
        System.out.println("p2 after the replacement = " + p2.describe() + " (should be 1.0X^0 + 4.0X^2)");

        System.out.println("p1 + p2 = " + p1.add(p2).describe() + " (should be 2.0X^0 + 6.0X^2)");
        // Coefficients that cancel out leave no monomials
        System.out.println("p3 + (-1) * p3 = " + p3.add(p3.scalarProduct(-1.0)).describe() + " (should be 0)");
        System.out.println("p3 * 2 = " + p3.scalarProduct(2.0).describe() + " (should be 4.0X^0 + 10.0X^1)");
        System.out.println("p3 * p3 = " + p3.product(p3).describe() + " (should be 4.0X^0 + 20.0X^1 + 25.0X^2)");

        System.out.println("p3 at X = 2: " + p3.solve(2.0) + " (should be 12.0)");
        System.out.println("p2 at X = -1: " + p2.solve(-1.0) + " (should be 5.0)");

        // The same polynomial, built in two different ways
        double[] sameCoefficients = {2.0, 5.0};
        Polynomial p4 = new Polynomial(sameCoefficients);
        System.out.println("p3 equals p4: " + p3.isEqualTo(p4) + " (should be true)");
        System.out.println("p1 equals p2: " + p1.isEqualTo(p2) + " (should be false)");
    }
}
