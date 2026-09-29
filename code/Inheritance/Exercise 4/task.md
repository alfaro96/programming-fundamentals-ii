# Exercise 4

In this exercise, you will implement a class hierarchy that represents various types of numbers, such as rational, irrational, integer, and natural numbers. The hierarchy will be designed using inheritance, where each class extends a base class `Real`. You will also implement various mathematical operations that can be performed on these numbers.

1. **Create a package named `numbers`**

First, create a package named `numbers` where all the classes related to numbers will be stored. This helps to organize your code properly.

2. **Implement the `Real` abstract class**

The `Real` class will represent the base class for all types of numbers in the system. This class will define the common attributes and methods that all numbers share.

* **Attributes:**
  * `double value`: This attribute stores the decimal value of the real number. It will be used by subclasses to hold the value for their respective number types.

* **Methods:**
  * `add(Real other)`: Adds another `Real` object to the current number and returns the result.
  * `subtract(Real other)`: Subtracts another `Real` object from the current number and returns the result.
  * `multiply(Real other)`: Multiplies the current number by another `Real` object and returns the result.
  * `divide(Real other)`: Divides the current number by another `Real` object and returns the result.

These methods should be implemented so that they can be used by any type of number in the system. However, since `Real` is an abstract class, the subclasses are expected to implement specific behaviors where needed.

* **Constructor:**
  * `Real(double value)`: This constructor initializes the `value` attribute to a specific value passed as an argument.

**Ensure that neither the constructor nor the attributes are public**. Use the appropriate visibility modifiers to restrict access but allow inheritance.

3. **Create subclasses of `Real`**

Now, create subclasses that represent more specific types of numbers. These classes will inherit from `Real` and implement the necessary methods for each type of number.

* **`Rational` class**

The `Rational` class represents rational numbers, which can be expressed as a fraction.

* **Attributes:**
  * `int numerator`: The numerator of the rational number.
  * `int denominator`: The denominator of the rational number.

* **Constructor:**
  * `Rational(int numerator, int denominator)`: This constructor initializes both the `numerator` and `denominator` attributes. The decimal `value` is calculated internally by dividing the numerator by the denominator, and this result is passed to the parent class `Real` using `super()`.

* **Methods:** Override the methods `add`, `subtract`, `multiply`, and `divide` to handle operations specifically using fractions if the other number is also a `Rational`, otherwise perform a standard decimal operation.

* **`Integer` class**

The `Integer` class represents integer numbers, which are a specific type of rational numbers with a denominator of 1. It should inherit directly from `Rational`.

* **Constructor:**
  * `Integer(int value)`: This constructor initializes the `numerator` with the given integer value and explicitly sets the `denominator` to 1 by calling the parent constructor.

* **Methods:** Override the mathematical operation methods if necessary to ensure the results maintain integer-specific behaviors when operating with other integers.

* **`Natural` class**

The `Natural` class represents natural numbers, which are strictly positive integers used for counting. It should inherit directly from `Integer`.

* **Constructor:**
  * `Natural(int value)`: This constructor must ensure that the `value` is always strictly a positive integer (greater than zero). If not, handle it appropriately.

* **Methods:** Inherit mathematical operations from `Integer`, but ensure that operations resulting in negative numbers or zero are handled gracefully to maintain strict natural number rules.

* **`Irrational` class**

The `Irrational` class represents irrational numbers, such as π or the square root of 2, which cannot be expressed as exact fractions. It inherits directly from `Real`.

* **Constructor:**
  * `Irrational(double value)`: Initializes the `value` attribute by passing it to the parent class constructor.

* **Methods:** The mathematical operation methods will work as expected using the inherited floating-point arithmetic, returning approximations since irrational numbers cannot be perfectly represented in a finite decimal form.

4. **Main program**

Create a `Main` class that will test all the implemented classes and their functionality.

* Instantiate objects of `Integer`, `Natural`, `Rational`, and `Irrational`.
* Perform arithmetic operations like addition, subtraction, multiplication, and division on these objects and print the results to demonstrate polymorphism in action.
