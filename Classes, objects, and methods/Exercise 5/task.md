# Exercise 5
The goal of this exercise is to define the `Polynomial` class, representing an equation composed of several monomials. The polynomial follows the format:

$$
P(x) = c_0 + c_1 \cdot x + c_2 \cdot x^2 + \dots + c_n \cdot x^n
$$

This implementation requires the `Monomial` class.

### Implementation

Implement a `Polynomial` class with the following requirements:

* **Attributes**:
  * `Monomial[] monomials`: An array where each monomial's exponent corresponds to its position in the array. For instance, position 0 contains the monomial with exponent 0.
  * If the polynomial lacks a monomial for a specific exponent, that array position remains empty.
  * `int maxSize`: The maximum size of the array.
  * `int numMonomials`: The current count of monomials in the array.

* **Methods**:
  * **Constructors**:
    * A constructor that creates an empty polynomial with a specified maximum capacity.
    * A constructor that takes an array of real numbers as coefficients, where the array index represents the exponent.
    * A constructor that takes an array of `Monomial` objects.
  * `getMonomial()`: Returns the monomial associated with a given exponent.
  * `addMonomial()`: Adds a monomial to the polynomial at the correct position based on its exponent.
  * `replaceMonomial()`: Replaces an existing monomial with a new one based on its exponent.
  * `add()`: Adds another polynomial to the current one and returns a **new** `Polynomial`.
  * `scalarProduct()`: Multiplies the polynomial by a scalar value and returns a **new** `Polynomial`.
  * `product()`: Multiplies the current polynomial by another and returns a **new** `Polynomial`.
  * `solve()`: Given a value for $X$, calculates and returns the numerical result of the polynomial.
  * `toString()`: Returns a human-legible `String` representation of the polynomial.
  * `equals()`: Checks if two polynomials are equal by verifying if all their respective monomials are equal.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation and testing

To successfully complete the exercise, implement a `main` method to perform the following tests:

1. **Object creation**:
   * Create polynomials using all three defined constructors.

2. **Operations and verification**:
   * Perform operations between polynomials and ensure the results match expected values.
   * Solve the polynomials for specific $X$ values to verify numerical accuracy.
   * Check the equality of different polynomial instances.

3. **Clean code and documentation**:
   * Ensure the code is clean, legible, and includes explanatory comments where necessary.
