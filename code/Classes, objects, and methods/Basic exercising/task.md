# Basic exercising

## Goals

In this laboratory assignment you'll move from a mathematical concept to a Java class that represents it. The main focus is on **defining the structure of an object** and **implementing its behavior** with methods.

By the end of this session, you will be able to:

* Turn a real-world concept into a Java class with the right attributes and methods.
* Write several constructors for the same class, overloaded with different parameters.
* Write methods that work on the attributes of an object and return new objects.
* Overload a method so that it accepts different types of arguments.
* Document a class with Javadoc comments and generate its documentation.

## Tasks

Write a program that works with **complex numbers**, made of a real part and an imaginary part: $real + imaginary \cdot i$.

### Environment setup

1. **Create the project**: select `New Project`, choose `Java` and name it `Laboratory assignment 1`.
2. **Create the classes**: inside the source (`src`) folder, create two classes, `ComplexNumber` and `Main`, each in its own file with its name: `ComplexNumber.java` and `Main.java`.

### Implementation of the `ComplexNumber` class

1. **Attributes**: two attributes of type `double`, `real` and `imaginary`.

2. **Constructors**: write the following **four constructors**:
   * **Default constructor**: sets both parts to $0$.
   * **Integer constructor**: receives the two parts as `int` values.
   * **Double constructor**: receives the two parts as `double` values.
   * **Copy constructor**: receives another `ComplexNumber` and creates a new object with the same parts.

3. **Methods**: none of the operations changes the complex numbers it works with: each one creates a new complex number with `new` and returns it.
   * `add()`: adds another complex number to this one and returns a **new** complex number: $(a + bi) + (c + di) = (a + c) + (b + d)i$.
   * `subtract()`: subtracts another complex number from this one and returns a **new** complex number: $(a + bi) - (c + di) = (a - c) + (b - d)i$.
   * `multiply()`: **overload** this method so that it accepts either a real number or another complex number, and returns a **new** complex number in both cases:
     * By a real number $k$: $(a + bi) \cdot k = ak + bki$.
     * By a complex number: $(a + bi)(c + di) = (ac - bd) + (ad + bc)i$, since $i^2 = -1$.
   * `describe()`: returns a `String` with the complex number in the usual mathematical form, $real + imaginary \cdot i$.
     * **Logic**: join with `+` the real part, `" + "`, the imaginary part and `"i"`.
     * **Example**: for a complex number with `real = 3.5` and `imaginary = 2.0`, the method must return exactly the `String` `"3.5 + 2.0i"`.
   * `isEqualTo()`: given another complex number, checks whether both are equal. The parts are `double` values, which carry rounding errors (`0.1 + 0.2` is not exactly `0.3`), so compare them with a tolerance: two parts are equal when they differ by less than a constant `EPSILON = 1e-9`, declared `static final` in the class. `Math.abs` gives the absolute value of a difference.

### Testing and documentation

1. **Incremental testing**: test each constructor and method right after writing it, and make sure it works before moving on to the next one, instead of trying to debug the whole program at the end.

2. **Verification**: write the `main` method of the `Main` class to test the `ComplexNumber` class.
   * Check that the four constructors set the attributes correctly, and that changing a copy does not change the original.
   * Test the operations (`add`, `subtract` and both versions of `multiply`) and compare the results with the ones you work out by hand.
   * Check that `describe()` and `isEqualTo()` give the expected results, also for numbers that differ only by a rounding error, such as `0.1 + 0.2` and `0.3`.

3. **Style**: write `this.` before every attribute, such as `this.real`, and before every call to another method of the same object, even where no parameter has the same name, as in the exercises.

4. **Documentation comments**: document every class, attribute, constructor and method with **Javadoc comments** (`/** ... */`), following the same rules as in the previous assignment.

5. **Documentation generation**: open the `Tools` menu, select `Generate JavaDoc...` and generate the documentation in the `doc` folder of your project. Since neither class is declared `public`, choose `package` as the visibility level so that they appear in it. Then open its `index.html` file in a web browser and check that every class and method is documented.

### Extra: `ComplexNumberArray` class

Write a class that represents an array of complex numbers:

* **Attributes**: an array of `ComplexNumber` objects.
* **Constructor**: receives a length and creates an array of that length, filled with complex numbers with random parts. `Math.random()` returns a random `double` between $0$ and $1$.
* **Methods**: `sumArray()`, which returns a **new** complex number with the sum of all of them, and `describe()`, which returns a `String` with every complex number of the array. Since it joins one piece of text per complex number, a `StringBuilder` is a better fit than `+`.
