# Exercise 1

In mathematics, a monomial is a constant multiplied by a variable raised to a power:

$$
m \cdot X^{p}
$$

where:

* $m$ (**coefficient**) is a constant value.
* $X$ (**variable**) is a variable value.
* $p$ (**exponent**) is the power the variable is raised to.

Examples of monomials are $2X^{5}$ and $X^{-3}$. The goal of this exercise is to write a `Monomial` class in Java that represents this kind of data and performs the usual operations with it.

### Implementation

Write a `Monomial` class with the following requirements:

* **Attributes**:
  * `double coefficient`: the coefficient of the monomial.
  * `int exponent`: the power the variable is raised to.
  * The variable is always $X$, so it is not stored.

* **Methods**:
  * **Constructor**: creates a monomial from a coefficient and an exponent.
  * `describe()`: returns a `String` with the written form of the monomial, made by joining the coefficient, `X^` and the exponent with `+`. For example, the monomial with coefficient `2` and exponent `3` is described as `"2.0X^3"` (a `double` is always written with a decimal point).
  * `addition()`: given another `Monomial`, **returns a new monomial** with the sum of both. Two monomials only add up to a single monomial when they have the same exponent: the coefficients are added and the exponent stays the same. If the exponents differ, the method returns `null`.
  * `scalarProduct()`: given a number, **returns a new monomial** with the product of the monomial by that number.
  * `product()`: given another `Monomial`, **returns a new monomial** with the product of both: the coefficients are multiplied and the exponents are added.
  * `evaluate()`: given a number, returns the value of the monomial when $X$ takes that value. `Math.pow(a, b)` computes $a^{b}$.

None of the operations changes the monomials it works with: each one creates a new monomial with `new` and returns it.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.
* **`this`**: write `this.` before every attribute, such as `this.coefficient`, even where no parameter has the same name, as in the slides. It makes clear which values belong to the monomial itself and which to `other`.

### Evaluation

The `Main` class provided creates several monomials and prints the result of each operation next to the value it should have. To complete the exercise:

1. **Class functionality**:
   * Check that the constructor stores both values.
   * Check the text returned by `describe()`.

2. **Monomial operations**:
   * Check that additions and products give the right coefficient and exponent, and that the original monomials do not change.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
