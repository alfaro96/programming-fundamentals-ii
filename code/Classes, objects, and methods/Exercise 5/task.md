# Exercise 5

The goal of this exercise is to write a `Polynomial` class that represents a polynomial as a set of monomials:

$$
P(X) = c_0 + c_1 \cdot X + c_2 \cdot X^2 + \dots + c_n \cdot X^n
$$

It uses the `Monomial` class of Exercise 1, which is provided.

### Implementation

Write a `Polynomial` class with the following requirements:

* **Attributes**:
  * `Monomial[] monomials`: an array where each monomial is stored at the position of its exponent: position 0 holds the monomial with exponent 0, position 1 the one with exponent 1, and so on. If the polynomial has no monomial with a given exponent, that position holds `null`, which stands for a coefficient of 0. That is why no position may ever hold a monomial whose coefficient is 0.
  * `int maxSize`: the length of the array, so the polynomial has room for exponents from 0 to `maxSize - 1`.
  * `int numMonomials`: the number of positions of the array that hold a monomial.

* **Methods**:
  * **Constructors**:
    * A constructor that creates a polynomial with no monomials, given the length of the array.
    * A constructor that receives an array of coefficients, where the position of each coefficient is its exponent. A coefficient of 0 gives no monomial.
    * A constructor that receives an array of `Monomial` objects, in any order. The array of the polynomial must have room for the highest exponent among them.
  * `getMonomial()`: returns the monomial with a given exponent, or `null` if there is none or the exponent is outside the array.
  * `addMonomial()`: adds a monomial to the polynomial, at the position of its exponent. If there is already a monomial there, it is replaced by the sum of both. If the coefficient of the result is 0, or within the tolerance of `isEqualTo()` of it, such as when $3X^2$ and $-3X^2$ are added, the position is left empty, with `null`. If the array has no room for that exponent, the polynomial does not change.
  * `replaceMonomial()`: replaces the monomial that has the same exponent as a given one, or removes it if the coefficient of the given one is 0. If the polynomial has no monomial with that exponent, it does not change.
  * `add()`: adds another polynomial to this one and returns a **new** `Polynomial`.
  * `scalarProduct()`: multiplies the polynomial by a number and returns a **new** `Polynomial`.
  * `product()`: multiplies this polynomial by another one and returns a **new** `Polynomial`: every monomial of the first is multiplied by every monomial of the second, and the products are added.
  * `solve()`: given a value for $X$, returns the value of the polynomial.
  * `describe()`: returns a `String` with the polynomial, such as `"1.0X^0 + 3.0X^2"`, or `"0"` if it has no monomials. `numMonomials` tells you whether that is the case. The `describe()` method of `Monomial` gives the text of each monomial.
  * `isEqualTo()`: given another polynomial, checks whether both are equal, that is, whether they have the same coefficient for every exponent. A missing monomial counts as a coefficient of 0, so two equal polynomials may have arrays of different lengths. As in Exercise 4, compare the coefficients with a small tolerance.

None of the operations changes the polynomials it works with.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.
* **Reuse**: `addMonomial()` already adds a monomial to the one in its position and leaves out the ones that cancel out, so the constructors, `add()`, `scalarProduct()` and `product()` become simpler if they use it. Call it on the polynomial being built, as `result.addMonomial(...)`, or on the object itself, as `this.addMonomial(...)`.
* **Building the text**: `describe()` joins one piece of text per monomial, so a `StringBuilder` is a better fit than `+`.
* **Keeping `numMonomials` right**: every time a position goes from `null` to a monomial, or back, update it.

### Evaluation and testing

To complete the exercise, write a `Main` class with a `main` method that performs the following tests:

1. **Object creation**:
   * Create polynomials with each of the three constructors.

2. **Operations and verification**:
   * Operate with polynomials and check that the results are the expected ones, including a sum whose coefficients cancel out.
   * Evaluate polynomials for some values of $X$ to check the results.
   * Check whether different polynomials are equal, including the same polynomial built in two different ways.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
