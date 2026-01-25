/**
 * Represents a mathematical monomial.
 * A monomial consists of a constant coefficient and a variable X raised to an exponent.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Monomial {

    /** The constant value multiplying the variable. */
    public double coefficient;

    /** The power to which the variable X is raised. */
    public int exponent;

    /**
     * Constructs a new {@link Monomial} with the specified values.
     *
     * @param coefficient The numerical constant.
     * @param exponent The power of the variable.
     */
    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * Returns a string representation of the monomial.
     *
     * @return A formatted {@code String}.
     */
    @Override
    public String toString() {
        return this.coefficient + "X^" + this.exponent;
    }

    /**
     * Adds another monomial to the current one.
     * This operation is only performed if the exponents are identical.
     *
     * @param other The {@link Monomial} to be added.
     * @return A new {@link Monomial} representing the sum, or {@code null} if exponents differ.
     */
    public Monomial addition(Monomial other) {
        // Validation: In mathematics, addition is only valid if exponents are equal
        if (this.exponent != other.exponent) {
            return null;
        }

        return new Monomial(this.coefficient + other.coefficient, this.exponent);
    }

    /**
     * Multiplies the monomial by a scalar value.
     *
     * @param scalar The numerical value to multiply by.
     * @return A new {@link Monomial} with the updated coefficient.
     */
    public Monomial scalarProduct(double scalar) {
        return new Monomial(this.coefficient * scalar, this.exponent);
    }

    /**
     * Multiplies the current monomial by another one.
     *
     * @param other The {@link Monomial} to multiply with.
     * @return A new {@link Monomial} representing the product.
     */
    public Monomial product(Monomial other) {
        double newCoefficient = this.coefficient * other.coefficient;
        int newExponent = this.exponent + other.exponent;
        return new Monomial(newCoefficient, newExponent);
    }

    /**
     * Solves the monomial for a specific value of X.
     *
     * @param xValue The value to substitute for X.
     * @return The numerical result.
     */
    public double evaluate(double xValue) {
        return this.coefficient * Math.pow(xValue, this.exponent);
    }

    /**
     * Entry point of the test application.
     * <p>
     * This method executes an incremental test suite to verify:
     * <ul>
     * <li>Proper object initialization and field consistency.</li>
     * <li>Formatting of the {@link Monomial#toString()} method.</li>
     * <li>Correctness of addition (including exponent validation) and products.</li>
     * <li>Numerical precision of the evaluation logic.</li>
     * </ul>
     * </p>
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Creation and String representation
        Monomial m1 = new Monomial(2.0, 3); // 2X^3
        System.out.println("Monomial 1: " + m1); // Expected: 2.0X^3

        // 2. Addition with same exponent
        Monomial m2 = new Monomial(4.5, 3); // 4.5X^3
        Monomial validSum = m1.addition(m2); // Expected: 6.5X^3
        System.out.println("Valid addition (2.0X^3 + 4.5X^3): " + validSum);

        // 3. Addition with different exponents (validation test)
        Monomial m3 = new Monomial(1.0, 5); // 1.0X^5
        Monomial invalidSum = m1.addition(m3); // Expected: null
        if (invalidSum == null) {
            System.out.println("Invalid addition Test: Correctly returned null for mismatched exponents.");
        }

        // 4. Scalar product
        Monomial scaled = m1.scalarProduct(3.0); // (2.0X^3) * 3 = 6.0X^3
        System.out.println("Scalar Product (2.0X^3 * 3.0): " + scaled);

        // 5. Monomial product
        Monomial product = m1.product(m3); // (2.0X^3) * (1.0X^5) = 2.0X^8
        System.out.println("Monomial Product (2.0X^3 * 1.0X^5): " + product);

        // 6. Evaluation
        // Calculation: 2.0 * (2.0^3) = 2.0 * 8.0 = 16.0
        double evaluationResult = m1.evaluate(2.0);
        System.out.println("Evaluation of m1 for X=2.0: " + evaluationResult);
    }
}
