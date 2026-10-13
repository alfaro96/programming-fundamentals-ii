# Exercise 4

The goal of this exercise is to write two classes that work together: `Point`, which represents a point of the plane with its Cartesian coordinates, and `Vector`, which represents a directed segment from one point to another. The `Vector` class uses the `Point` class and its methods.

The coordinates are `double` values, which carry rounding errors: `0.1 + 0.2` is not exactly `0.3`. That is why two coordinates are considered equal when they differ by less than a very small amount, such as `1e-9`. Keep it in a constant of the `Point` class, declared `static final`, and compare with `Math.abs(a - b) < EPSILON` instead of `a == b`.

### `Point`

Write a `Point` class with the following requirements:

* **Attributes**:
  * `double x`: the $X$ coordinate of the point.
  * `double y`: the $Y$ coordinate of the point.

* **Methods**:
  * **Constructors**:
    * A default constructor, which creates the point $(0, 0)$.
    * A constructor that receives the $X$ and $Y$ coordinates.
  * `distance()`: given another point, returns the Euclidean distance between both: $\sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}$. `Math.sqrt` computes a square root.
  * `isCollinearTo()`: given two more points, returns whether the three lie on the same line. They do when the triangle they form has no area, that is, when $(x_2 - x_1)(y_3 - y_1) - (y_2 - y_1)(x_3 - x_1)$ is $0$.
  * `middlePoint()`: given another point, returns a **new** `Point` halfway between both.
  * `describe()`: returns the point as a `String` in the form `"(x, y)"`, such as `"(1.0, 2.5)"`.
  * `isEqualTo()`: given another point, checks whether both are the same point, that is, whether both coordinates are equal.

### `Vector`

Write a `Vector` class with the following requirements:

* **Attributes**:
  * `Point originPoint`: the point where the vector starts.
  * `Point endPoint`: the point where the vector ends.

* **Methods**:
  * **Constructor**: receives the origin point and the end point. There is no default constructor.
  * `add()`: given another vector, returns a **new** `Vector` whose origin is the sum of both origins, coordinate by coordinate, and whose end is the sum of both ends.
  * `subtract()`: given another vector, returns a **new** `Vector` in the same way, subtracting the origin and the end of the second vector from those of the first.
  * `scalarProduct()`: given a number, returns a **new** `Vector` whose points have every coordinate multiplied by that number.
  * `centroid()`: given two other vectors, returns a **new** `Point` at the centroid, or barycenter, of the triangle formed by the origin points of the three vectors. Its coordinates are the averages of theirs.
  * `describe()`: returns the vector as a `String` in the form `"(x, y) -> (m, n)"`, such as `"(0.0, 0.0) -> (1.0, 2.0)"`.
  * `isEqualTo()`: given another vector, checks whether both are the same vector, that is, whether they have the same origin point and the same end point.

None of the operations changes the points or vectors it works with.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.

### Evaluation and testing

To complete the exercise, write a `Main` class with a `main` method that performs the following tests:

1. **`Point` testing**:
   * Create four points and print them.
   * Compute and print distances, whether three points are collinear and the middle point of some pairs.
   * Check whether some pairs of points are equal.

2. **`Vector` testing**:
   * Create five vectors and print them.
   * Compute and print the addition and subtraction of some pairs, and the product of a vector by a number.
   * Compute the centroid of a group of three vectors and check whether some pairs of vectors are equal.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
