/**
 * Represents a monomial <i>m</i> &middot; <i>X</i><sup><i>p</i></sup>: a constant coefficient
 * multiplied by the variable <i>X</i> raised to an exponent.
 * <p>
 * The variable is always <i>X</i>, so it is not stored.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
class Monomial {

    /**
     * The constant that multiplies the variable.
     */
    double coefficient;

    /**
     * The power the variable is raised to.
     */
    int exponent;

    /**
     * Creates a monomial with the given coefficient and exponent.
     *
     * @param coefficient the constant that multiplies the variable.
     * @param exponent the power the variable is raised to.
     */
    Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * Returns the written form of the monomial, such as {@code "2.0X^3"}.
     *
     * @return the coefficient, followed by {@code X^} and the exponent.
     */
    String describe() {
        return this.coefficient + "X^" + this.exponent;
    }

    /**
     * Adds another monomial to this one.
     * <p>
     * Two monomials only add up to a single monomial when they have the same exponent:
     * the coefficients are added and the exponent stays the same.
     *
     * @param other the monomial to add.
     * @return a new monomial with the sum, or {@code null} if the exponents differ.
     */
    Monomial addition(Monomial other) {
        if (this.exponent != other.exponent) {
            return null;
        }
        return new Monomial(this.coefficient + other.coefficient, this.exponent);
    }

    /**
     * Multiplies this monomial by a number: the coefficient is multiplied and the
     * exponent stays the same.
     *
     * @param scalar the number to multiply by.
     * @return a new monomial with the product.
     */
    Monomial scalarProduct(double scalar) {
        return new Monomial(this.coefficient * scalar, this.exponent);
    }

    /**
     * Multiplies this monomial by another one: the coefficients are multiplied and the
     * exponents are added.
     *
     * @param other the monomial to multiply by.
     * @return a new monomial with the product.
     */
    Monomial product(Monomial other) {
        return new Monomial(this.coefficient * other.coefficient, this.exponent + other.exponent);
    }

    /**
     * Evaluates the monomial, replacing <i>X</i> with the given value.
     *
     * @param x the value of <i>X</i>.
     * @return the coefficient multiplied by {@code x} raised to the exponent.
     */
    double evaluate(double x) {
        return this.coefficient * Math.pow(x, this.exponent);
    }
}
