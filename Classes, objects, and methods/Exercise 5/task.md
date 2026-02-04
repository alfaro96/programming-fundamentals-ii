# Exercise 5

This exercise focuses on the interaction between classes. A `Polynomial` is a mathematical entity composed of several `Monomial` objects. In this implementation, the structure relies on an array where the position of each element defines its mathematical role.

---

## Objective

The goal is to define the `Polynomial` class in Java to represent equations that follow the format:

$$ P(x) = c_{0} + c_{1} \cdot x + c_{2} \cdot x^{2} + \dots + c_{n} \cdot x^{n} $$.

---

## Requirements

### Attributes

* **`monomials (Monomial[])`**: An array where each monomial's exponent corresponds to its position in the array. For example, position 0 contains the monomial with exponent 0.
* **`maxSize (int)`**: The maximum capacity of the internal array.
* **`numMonomials (int)`**: The current count of monomials stored in the array.

### Methods

* **Constructors**:
  * Creates an empty polynomial with a specified maximum capacity.
  * Takes an array of coefficients (`double`s) where the index represents the exponent.
  * Takes an array of `Monomial` objects.

* **Array management**:
  * `getMonomial()`: Returns the monomial associated with a given exponent.
  * `addMonomial()`: Adds a monomial at the correct position based on its exponent.
  * `replaceMonomial()`: Replaces an existing monomial with a new one based on its exponent.

* **Arithmetic operations**:
  * `add()`: Adds another polynomial and returns a **new** `Polynomial`.
  * `scalarProduct()`: Multiplies the polynomial by a scalar and returns a **new** `Polynomial`.
  * `product()`: Multiplies the current polynomial by another and returns a **new** `Polynomial`.

* **Logic**:
  * `solve()`: Calculates the numerical result for a given value of $ X $.
  * `toString()`: Returns a human-legible representation of the polynomial.
  * `equals()`: Checks if two polynomials are equal by verifying all their respective monomials.

---

## Evaluation criteria

To successfully complete the exercise, you must implement a `main` method to perform:

1. **Object creation**: Instantiate polynomials using all three defined constructors.
2. **Operational verification**: Perform calculations and ensure results match mathematical expectations.
3. **Accuracy**: Solve the equations for specific values of $ X $.
4. **Equality check**: Verify the equality of different instances.
5. **Clean code**: Ensure legible implementation and professional Javadoc documentation.

---

## View the files

* [Polynomial.java](src/Polynomial.java)
