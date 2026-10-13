/**
 * Represents a polynomial <i>P</i>(<i>X</i>) = <i>c</i><sub>0</sub> + <i>c</i><sub>1</sub><i>X</i> +
 * <i>c</i><sub>2</sub><i>X</i><sup>2</sup> + &hellip; + <i>c</i><sub><i>n</i></sub><i>X</i><sup><i>n</i></sup>
 * as an array of {@link Monomial} objects.
 * <p>
 * Each monomial is stored at the position of its exponent, so the array only has room for
 * exponents from 0 to {@link #maxSize} &minus; 1. A position without a monomial holds
 * {@code null}, which stands for a coefficient of 0: no position ever holds a monomial whose
 * coefficient is 0, so {@link #numMonomials} counts only the ones that matter.
 * <p>
 * The monomials are never changed once stored, only replaced, so the operations can share
 * them and always return new polynomials.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Monomial
 */
class Polynomial {

    /**
     * The largest difference between two coefficients that are still considered equal.
     */
    static final double EPSILON = 1e-9;

    /**
     * The monomials of the polynomial, each one at the position of its exponent.
     */
    Monomial[] monomials;

    /**
     * The length of {@link #monomials}: the highest exponent it has room for, plus one.
     */
    int maxSize;

    /**
     * The number of positions of {@link #monomials} that hold a monomial.
     */
    int numMonomials;

    /**
     * Creates a polynomial with no monomials, with room for exponents from 0 to
     * {@code maxSize} &minus; 1.
     *
     * @param maxSize the length of the array of monomials.
     */
    Polynomial(int maxSize) {
        this.maxSize = maxSize;
        this.monomials = new Monomial[maxSize];
        this.numMonomials = 0;
    }

    /**
     * Creates a polynomial from its coefficients: the one at position <i>i</i> is that of
     * <i>X</i><sup><i>i</i></sup>. A coefficient of 0 gives no monomial.
     *
     * @param coefficients the coefficients, from that of <i>X</i><sup>0</sup> upward.
     */
    Polynomial(double[] coefficients) {
        this(coefficients.length);
        for (int i = 0; i < coefficients.length; i++) {
            this.addMonomial(new Monomial(coefficients[i], i));
        }
    }

    /**
     * Creates a polynomial from an array of monomials, in any order and possibly with
     * {@code null} positions. Each monomial goes to the position of its exponent, and two
     * with the same exponent are added.
     * <p>
     * The array of the polynomial has room for the highest exponent among the monomials.
     * Monomials with a negative exponent are left out, since a polynomial has none.
     *
     * @param monomials the monomials of the polynomial.
     */
    Polynomial(Monomial[] monomials) {
        int highest = -1;
        for (Monomial m : monomials) {
            if (m != null && m.exponent > highest) {
                highest = m.exponent;
            }
        }
        this.maxSize = highest + 1;
        this.monomials = new Monomial[this.maxSize];
        this.numMonomials = 0;
        for (Monomial m : monomials) {
            if (m != null) {
                this.addMonomial(m);
            }
        }
    }

    /**
     * Returns the monomial with the given exponent.
     *
     * @param exponent the exponent of the monomial.
     * @return the monomial, or {@code null} if the polynomial has none with that exponent.
     */
    Monomial getMonomial(int exponent) {
        if (exponent < 0 || exponent >= this.maxSize) {
            return null;
        }
        return this.monomials[exponent];
    }

    /**
     * Adds a monomial to the polynomial, at the position of its exponent. If there is already
     * a monomial there, it is replaced by the sum of both.
     * <p>
     * If the coefficient of the result is 0, the position is left empty. If the array has no
     * room for the exponent, the polynomial does not change.
     *
     * @param m the monomial to add.
     */
    void addMonomial(Monomial m) {
        if (m.exponent < 0 || m.exponent >= this.maxSize) {
            return;
        }
        Monomial sum = m;
        if (this.monomials[m.exponent] != null) {
            // The old monomial leaves its position, and the sum takes it unless it is 0
            sum = this.monomials[m.exponent].addition(m);
            this.monomials[m.exponent] = null;
            this.numMonomials--;
        }
        if (Math.abs(sum.coefficient) >= EPSILON) {
            this.monomials[m.exponent] = sum;
            this.numMonomials++;
        }
    }

    /**
     * Replaces the monomial with the same exponent as the given one, or removes it if the
     * coefficient of the given one is 0. If the polynomial has no monomial with that exponent,
     * it does not change.
     *
     * @param m the new monomial.
     */
    void replaceMonomial(Monomial m) {
        if (this.getMonomial(m.exponent) == null) {
            return;
        }
        if (Math.abs(m.coefficient) < EPSILON) {
            this.monomials[m.exponent] = null;
            this.numMonomials--;
        } else {
            this.monomials[m.exponent] = m;
        }
    }

    /**
     * Adds another polynomial to this one: the monomials with the same exponent are added.
     *
     * @param other the polynomial to add.
     * @return a new polynomial with the sum.
     */
    Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial(Math.max(this.maxSize, other.maxSize));
        for (Monomial m : this.monomials) {
            if (m != null) {
                result.addMonomial(m);
            }
        }
        for (Monomial m : other.monomials) {
            if (m != null) {
                result.addMonomial(m);
            }
        }
        return result;
    }

    /**
     * Multiplies this polynomial by a number: every monomial is multiplied by it.
     *
     * @param scalar the number to multiply by.
     * @return a new polynomial with the product.
     */
    Polynomial scalarProduct(double scalar) {
        Polynomial result = new Polynomial(this.maxSize);
        for (Monomial m : this.monomials) {
            if (m != null) {
                result.addMonomial(m.scalarProduct(scalar));
            }
        }
        return result;
    }

    /**
     * Multiplies this polynomial by another one: every monomial of this polynomial is
     * multiplied by every monomial of the other, and the products are added.
     *
     * @param other the polynomial to multiply by.
     * @return a new polynomial with the product.
     */
    Polynomial product(Polynomial other) {
        Polynomial result = new Polynomial(this.maxSize + other.maxSize);
        for (Monomial m1 : this.monomials) {
            if (m1 != null) {
                for (Monomial m2 : other.monomials) {
                    if (m2 != null) {
                        result.addMonomial(m1.product(m2));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Evaluates the polynomial, replacing <i>X</i> with the given value.
     *
     * @param x the value of <i>X</i>.
     * @return the sum of the values of every monomial at {@code x}.
     */
    double solve(double x) {
        double result = 0;
        for (Monomial m : this.monomials) {
            if (m != null) {
                result = result + m.evaluate(x);
            }
        }
        return result;
    }

    /**
     * Returns the monomials of the polynomial from the lowest exponent up, joined by
     * {@code " + "}, such as {@code "1.0X^0 + 3.0X^2"}, or {@code "0"} if it has none.
     * <p>
     * The text is built in a {@link StringBuilder}, which grows instead of being copied for
     * each monomial.
     *
     * @return the written form of the polynomial.
     */
    String describe() {
        if (this.numMonomials == 0) {
            return "0";
        }
        StringBuilder text = new StringBuilder();
        for (Monomial m : this.monomials) {
            if (m != null) {
                if (text.length() > 0) {
                    text.append(" + ");
                }
                text.append(m.describe());
            }
        }
        return text.toString();
    }

    /**
     * Checks whether this polynomial and another one are equal: for every exponent, their
     * coefficients differ by less than {@link #EPSILON}. A missing monomial counts as a
     * coefficient of 0, so the length of the arrays does not matter.
     *
     * @param other the polynomial to compare with.
     * @return {@code true} if both polynomials are equal, {@code false} otherwise.
     */
    boolean isEqualTo(Polynomial other) {
        int size = Math.max(this.maxSize, other.maxSize);
        for (int i = 0; i < size; i++) {
            double c1 = 0;
            double c2 = 0;
            if (this.getMonomial(i) != null) {
                c1 = this.getMonomial(i).coefficient;
            }
            if (other.getMonomial(i) != null) {
                c2 = other.getMonomial(i).coefficient;
            }
            if (Math.abs(c1 - c2) >= EPSILON) {
                return false;
            }
        }
        return true;
    }
}
