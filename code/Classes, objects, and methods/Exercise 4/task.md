# Exercise 4

The goal of this exercise is to define two classes that work together: a `Point`, representing coordinates in two-dimensional cartesian space, and a `Vector`, representing a directed line between two points. Functionality in the `Vector` class will depend on methods previously implemented in the `Point` class.

### `Point`

Implement a `Point` class with the following requirements:

* **Attributes**:
  * `double x`: The $ X $ coordinate of the point.
  * `double y`: The $ Y $ coordinate of the point.

* **Methods**:
  * **Constructors**:
    * A default constructor that initializes the point at $ (0, 0) $.
    * A standard constructor that takes $ X $ and $ Y $ coordinates as arguments.
  * `distance()`: A method that, given another point, returns the Euclidean distance between them.
  * `isColinearTo()`: A method that, given two additional points, returns whether the three points belong to a single line.
  * `middlePoint()`: A method that, given another point, returns a **new** `Point` object placed exactly between the two.
  * `toString()`: A method that returns the point in a readable format, such as `"(x, y)"`.
  * `equals()`: A method that checks whether two points are equivalent.

### `Vector`

Implement a `Vector` class with the following requirements:

* **Attributes**:
  * `Point originPoint`: The point of origin of the vector.
  * `Point endPoint`: The end point of the vector.

* **Methods**:
  * **Constructor**: A constructor that takes the origin and end points as arguments. No default constructor should be provided.
  * `add()`: A method that, given another vector, returns a **new** `Vector` representing the addition of the two.
  * `subtract()`: A method that, given another vector, returns a **new** `Vector` representing the subtraction of the second from the first.
  * `scalarProduct()`: A method that, given another vector, returns a **new** `Vector` containing the scalar product.
  * `centroid()`: A method that, given two other vectors, returns a `Point` representing the centroid or barycenter of the three.
  * `toString()`: A method that returns the vector in a readable format, such as `"(x, y)` $ \rightarrow $ `(m, n)"`.
  * `equals()`: A method that checks whether two vectors are equivalent.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation and testing

To successfully complete the exercise, implement a `Main` class with a `main` method to perform the following tests:

1. **`Point` testing**:
   * Define four points and display them.
   * Compute and show the distances, colinearity of three points, and the middle point between pairs.
   * Verify if specific pairs of points are equal.

2. **`Vector` testing**:
   * Define five vectors and display them.
   * Compute and show the addition, subtraction, and product of vector pairs.
   * Determine the centroid of groups of vectors and verify vector equality.

3. **Clean code and documentation**:
   * Ensure the code is clean, legible, and includes explanatory comments where necessary.
