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
}
