In mathematics, a monomial is the product of a variable, raised to a power, by a constant. Usually, it has the structure:

$$
m \cdot x^{p}
$$

where:

* `m` (**coefficient**) refers to a constant value.
* `x` (**variable**) refers to a variable value.
* `p` (**exponent**) refers to the power the variable is raised to.

Examples of monomials include expressions like $ 2x^{5} $ or $ x^{-3} $. The goal of this exercise is to implement a class `Monomial` in Java to represent this data type and perform usual operations.

### Implementation

Implement a `Monomial` class with the following requirements:

* **Attributes**:
  * `double coefficient`: Represents the coefficient of the monomial.
  * `int exponent`: Represents the power the variable is raised to.
  * For this exercise, assume the variable is always `X` and is implicit.

* **Methods**:
  * **Constructor**: A constructor that allows the user to create a monomial given a coefficient and an exponent.
  * `toString()`: A method that returns a `String` containing the written representation of the monomial (e.g., `"2X^3"`).
  * `addition()`: A method that, given another `Monomial` as an argument, **returns a new monomial** representing the addition of the two.
  * `scalarProduct()`: A method that, given a scalar (numerical) value, **returns a new monomial** representing the multiplication of the monomial by that value.
  * `product()`: A method that, given another `Monomial` as an argument, **returns a new monomial** representing the multiplication of the two.
  * `evaluate()`: A method that, given a scalar value as an argument, returns the numerical result of solving the monomial using that value as `X`.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation

To successfully complete the exercise, the following points must be addressed:

1. **Class functionality**:
   * Ensure that the constructors work properly.
   * Verify the output format of the `toString()` method.

2. **Monomial operations**:
   * Test additions and products to ensure results are correct and that the coefficient and exponents remain consistent.

3. **Clean code and documentation**:
   * Ensure that the code is clean, legible, and includes explanatory comments where necessary.