/**
 * Main application class to demonstrate the {@link Polynomial} class.
 * Creates {@link Polynomial} instances using all constructors and exercises all their operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Polynomial
 * @see Monomial
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Empty constructor
        Polynomial empty = new Polynomial(5);
        System.out.println("--- Empty constructor ---");
        System.out.println("maxSize (expected 5): " + empty.maxSize);
        System.out.println("numMonomials (expected 0): " + empty.numMonomials);
        System.out.println();

        // 2. Coefficient-array constructor: P(x) = 1 + 0x + 3x^2
        double[] coeffs = {1.0, 0.0, 3.0};
        Polynomial fromCoeffs = new Polynomial(coeffs);
        System.out.println("--- Coefficient-array constructor: {1.0, 0.0, 3.0} ---");
        System.out.println("getMonomial(0).coefficient (expected 1.0): " + fromCoeffs.getMonomial(0).coefficient);
        System.out.println("getMonomial(2).coefficient (expected 3.0): " + fromCoeffs.getMonomial(2).coefficient);
        System.out.println(fromCoeffs);
        System.out.println();

        // 3. Monomial-array constructor
        Monomial[] monomials = {new Monomial(2.0, 0), new Monomial(5.0, 1)};
        Polynomial fromMonomials = new Polynomial(monomials);
        System.out.println("--- Monomial-array constructor: [2X^0, 5X^1] ---");
        System.out.println("getMonomial(0).coefficient (expected 2.0): " + fromMonomials.getMonomial(0).coefficient);
        System.out.println("getMonomial(1).coefficient (expected 5.0): " + fromMonomials.getMonomial(1).coefficient);
        System.out.println(fromMonomials);
        System.out.println();

        // 4. addMonomial()
        empty.addMonomial(new Monomial(3.0, 2));
        System.out.println("--- addMonomial(Monomial(3.0, 2)) ---");
        System.out.println("numMonomials (expected 1): " + empty.numMonomials);
        System.out.println("getMonomial(2).coefficient (expected 3.0): " + empty.getMonomial(2).coefficient);
        System.out.println();

        // 6. add(): P1(x) = 1 + 2x^2, P2(x) = 3 + 4x^2 -> P1+P2 = 4 + 6x^2
        Polynomial p1 = new Polynomial(new double[]{1.0, 0.0, 2.0});
        Polynomial p2 = new Polynomial(new double[]{3.0, 0.0, 4.0});
        Polynomial sumP = p1.add(p2);
        System.out.println("--- add() ---");
        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
        System.out.println("P1.add(P2): " + sumP);
        System.out.println("result.getMonomial(0).coefficient (expected 4.0): " + sumP.getMonomial(0).coefficient);
        System.out.println("result.getMonomial(2).coefficient (expected 6.0): " + sumP.getMonomial(2).coefficient);
        System.out.println();

        // 7. scalarProduct(): P(x) = 2 + 3x, scalar = 2 -> 4 + 6x
        Polynomial pScalar = new Polynomial(new double[]{2.0, 3.0});
        Polynomial scaledP = pScalar.scalarProduct(2.0);
        System.out.println("--- scalarProduct(2.0) ---");
        System.out.println("P: " + pScalar);
        System.out.println("P.scalarProduct(2.0): " + scaledP);
        System.out.println("result.getMonomial(0).coefficient (expected 4.0): " + scaledP.getMonomial(0).coefficient);
        System.out.println("result.getMonomial(1).coefficient (expected 6.0): " + scaledP.getMonomial(1).coefficient);
        System.out.println();

        // 8. product(): P1(x) = 1 + x, P2(x) = 1 + x -> 1 + 2x + x^2
        Polynomial pa = new Polynomial(new double[]{1.0, 1.0});
        Polynomial pb = new Polynomial(new double[]{1.0, 1.0});
        Polynomial prodP = pa.product(pb);
        System.out.println("--- product() ---");
        System.out.println("P1: " + pa + "  P2: " + pb);
        System.out.println("P1.product(P2): " + prodP);
        System.out.println("coeff at exp 0 (expected 1.0): " + prodP.getMonomial(0).coefficient);
        System.out.println("coeff at exp 1 (expected 2.0): " + prodP.getMonomial(1).coefficient);
        System.out.println("coeff at exp 2 (expected 1.0): " + prodP.getMonomial(2).coefficient);
        System.out.println();

        // 9. solve(): P(x) = 2 + 3x, x = 2 -> 8
        double solveResult = pScalar.solve(2.0);
        System.out.println("--- solve() ---");
        System.out.println("P(x) = 2 + 3x");
        System.out.println("P.solve(2.0) (expected 8.0): " + solveResult);
        System.out.println();

        // 10. equals()
        Polynomial pe1 = new Polynomial(new double[]{1.0, 2.0, 3.0});
        Polynomial pe2 = new Polynomial(new double[]{1.0, 2.0, 3.0});
        Polynomial pe3 = new Polynomial(new double[]{1.0, 2.0, 9.0});
        System.out.println("--- equals() ---");
        System.out.println("pe1.equals(pe2) (expected true):  " + pe1.equals(pe2));
        System.out.println("pe1.equals(pe3) (expected false): " + pe1.equals(pe3));
        System.out.println();
    }
}
