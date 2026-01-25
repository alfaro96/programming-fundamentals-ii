# Exercise 1

In mathematics, a monomial is the product of a variable, raised to a power, by a constant. It usually follows the structure $ m \cdot x^{p} $, where $ m $ is the coefficient, $ x $ is the variable, and $ p $ is the exponent.

---

## Objective

Implement a Java class named `Monomial` to represent this data type and perform standard operations. For this task, the variable $ X $ is implicit.

---

## Requirements

### Attributes

The class must contain:

* `double coefficient`: The constant value.
* `int exponent`: The power the variable is raised to.

### Methods

Implement the following behaviors:

* **Constructor**: Initializes the monomial with a coefficient and an exponent.
* **`toString()`**: Returns the written representation (e.g., `"2.0X^3"`).
* **`addition(Monomial other)`**: Returns a **new** `Monomial` representing the sum.
* **`scalarProduct(double scalar)`**: Returns a **new** `Monomial` multiplied by a constant.
* **`product(Monomial other)`**: Returns a **new** `Monomial` representing the product of the two.
* **`evaluate(double xValue)`**: Returns the numerical result of $ m \cdot x^{p} $.

---

## Evaluation criteria

* **Class functionality**: Constructors and `toString()` must work properly.
* **Monomial operations**: Additions and products must maintain consistent coefficients and exponents.
* **Clean code**: Ensure the code is legible and includes explanatory Javadoc.

---

## View the files

* [Monomial.java](file://src/Monomial.java)
