package es.uclm.esii.math.complex;

/**
 * Represents a complex number consisting of a real and an imaginary part.
 * This class provides methods for mathematical operations and comparisons.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class ComplexNumber {

    /** The real part of the complex number. */
    private double real;

    /** The imaginary part of the complex number. */
    private double imaginary;

    /** Epsilon value for comparing double values to handle rounding errors. */
    private static final double EPSILON = 1e-9;

    /**
     * Default constructor: Initializes both parts to 0.
     */
    public ComplexNumber() {
        this.real = 0;
        this.imaginary = 0;
    }

    /**
     * Integer constructor: Takes two integer values.
     *
     * @param real The real part as an integer.
     * @param imaginary The imaginary part as an integer.
     */
    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Double constructor: Takes two double values.
     *
     * @param real The real part as a double.
     * @param imaginary The imaginary part as a double.
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Copy constructor: Copies values from an existing {@link ComplexNumber} object.
     *
     * @param other The {@link ComplexNumber} object to copy.
     */
    public ComplexNumber(ComplexNumber other) {
        this.real = other.real;
        this.imaginary = other.imaginary;
    }

    /**
     * Adds a complex number to the current one.
     *
     * @param other The complex number to add.
     * @return A new {@link ComplexNumber} representing the result.
     */
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    /**
     * Subtracts a complex number from the current one.
     *
     * @param other The complex number to subtract.
     * @return A new {@link ComplexNumber} representing the result.
     */
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    /**
     * Multiplies the current complex number by a scalar value.
     *
     * @param scalar The double value to multiply by.
     * @return A new {@link ComplexNumber} representing the result.
     */
    public ComplexNumber multiply(double scalar) {
        return new ComplexNumber(this.real * scalar, this.imaginary * scalar);
    }

    /**
     * Multiplies the current complex number by another complex number.
     *
     * @param other The complex number to multiply by.
     * @return A new {@link ComplexNumber} representing the result.
     */
    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = (this.real * other.real) - (this.imaginary * other.imaginary);
        double newImaginary = (this.real * other.imaginary) + (this.imaginary * other.real);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Represents the complex number in a legible format.
     *
     * @return A legible text representation of the number.
     */
    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", this.real, this.imaginary);
    }

    /**
     * Compares the current complex number to another using epsilon comparison.
     * @param other The complex number to compare against.
     *
     * @return {@code true} if the differences are within epsilon, {@code false} otherwise.
     */
    public boolean equals(ComplexNumber other) {
        boolean realEqual = Math.abs(this.real - other.real) <= EPSILON;
        boolean imaginaryEqual = Math.abs(this.imaginary - other.imaginary) <= EPSILON;
        return realEqual && imaginaryEqual;
    }
}
