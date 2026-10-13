/**
 * Represents a complex number <i>a</i> + <i>b</i><i>i</i>, with a real part <i>a</i> and an
 * imaginary part <i>b</i>.
 * <p>
 * The operations return new complex numbers and leave the ones they receive unchanged.
 * Both parts are {@code double} values, which carry rounding errors, so two complex numbers
 * are considered equal when their parts differ by less than {@link #EPSILON}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
class ComplexNumber {

    /**
     * The largest difference between two parts that are still considered equal.
     */
    static final double EPSILON = 1e-9;

    /**
     * The real part.
     */
    double real;

    /**
     * The imaginary part.
     */
    double imaginary;

    /**
     * Creates the complex number 0 + 0<i>i</i>.
     */
    ComplexNumber() {
        this(0.0, 0.0);
    }

    /**
     * Creates a complex number from two integers.
     *
     * @param real the real part.
     * @param imaginary the imaginary part.
     */
    ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Creates a complex number from two real numbers.
     *
     * @param real the real part.
     * @param imaginary the imaginary part.
     */
    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Creates a copy of another complex number: a new object with the same parts.
     *
     * @param other the complex number to copy.
     */
    ComplexNumber(ComplexNumber other) {
        this(other.real, other.imaginary);
    }

    /**
     * Adds another complex number to this one: (<i>a</i> + <i>b</i><i>i</i>) +
     * (<i>c</i> + <i>d</i><i>i</i>) = (<i>a</i> + <i>c</i>) + (<i>b</i> + <i>d</i>)<i>i</i>.
     *
     * @param other the complex number to add.
     * @return a new complex number with the sum.
     */
    ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    /**
     * Subtracts another complex number from this one: (<i>a</i> + <i>b</i><i>i</i>) &minus;
     * (<i>c</i> + <i>d</i><i>i</i>) = (<i>a</i> &minus; <i>c</i>) + (<i>b</i> &minus; <i>d</i>)<i>i</i>.
     *
     * @param other the complex number to subtract.
     * @return a new complex number with the difference.
     */
    ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    /**
     * Multiplies this complex number by a real number: both parts are multiplied by it.
     *
     * @param scalar the real number to multiply by.
     * @return a new complex number with the product.
     */
    ComplexNumber multiply(double scalar) {
        return new ComplexNumber(this.real * scalar, this.imaginary * scalar);
    }

    /**
     * Multiplies this complex number by another one: (<i>a</i> + <i>b</i><i>i</i>)(<i>c</i> +
     * <i>d</i><i>i</i>) = (<i>ac</i> &minus; <i>bd</i>) + (<i>ad</i> + <i>bc</i>)<i>i</i>, since
     * <i>i</i><sup>2</sup> = &minus;1.
     *
     * @param other the complex number to multiply by.
     * @return a new complex number with the product.
     */
    ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Returns the complex number in the form {@code "real + imaginaryi"}, such as
     * {@code "3.5 + 2.0i"}.
     *
     * @return the real part, {@code " + "}, the imaginary part and {@code i}.
     */
    String describe() {
        return this.real + " + " + this.imaginary + "i";
    }

    /**
     * Checks whether this complex number and another one are equal: both parts differ by less
     * than {@link #EPSILON}.
     *
     * @param other the complex number to compare with.
     * @return {@code true} if both complex numbers are equal, {@code false} otherwise.
     */
    boolean isEqualTo(ComplexNumber other) {
        return Math.abs(this.real - other.real) < EPSILON && Math.abs(this.imaginary - other.imaginary) < EPSILON;
    }
}
