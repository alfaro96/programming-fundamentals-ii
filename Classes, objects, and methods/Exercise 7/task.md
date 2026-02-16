# Exercise 7

The goal of this exercise is to define the `Matrix` class, which represents an algebraic two-dimensional matrix containing numerical values. This exercise is particularly useful for understanding the concept of arrays of arrays, where lists contain other lists within themselves.

### Implementation

Implement a `Matrix` class with the following requirements:

* **Attributes**:
  * `int nRows`: Number of rows in the matrix.
  * `int nCols`: Number of columns in the matrix.
  * `double[][] values`: A matrix of double values representing the internal data of the algebraic matrix.

* **Constructors**:
  * `Matrix(int n)`: Creates a square-shaped matrix of size $n \times n$ with default values for each position.
  * `Matrix(int n, double val)`: Creates a square-shaped matrix of size $n \times n$, initializing all positions to the value `val`.
  * `Matrix(int n, int m)`: Creates a matrix with shape $n \times m$ with default values.
  * `Matrix(int n, int m, double val)`: Creates a matrix with shape $n \times m$, initializing all values to `val`.
  * `Matrix(double[][] values)`: Creates a matrix using a provided two-dimensional array of initial values.
  * `Matrix(Matrix other)`: A copy constructor that creates a new matrix based on an existing one by copying its values.

* **Methods**:
  * `add()`: Performs an addition between two matrices and returns a **new** matrix containing the result.
  * `scalarProduct()`: Multiplies the matrix by a scalar numerical value and returns a **new** matrix with the result.
  * `product()`: Performs a matrix multiplication between the current matrix and another, returning a **new** matrix as the result.
  * `trace()`: Calculates and returns the sum of the values on the main diagonal of the matrix. This operation is only applicable to square matrices.
  * `transpose()`: Returns a **new** matrix representing the transposition of the current one. If the original shape is $n \times m$, the result is $m \times n$, and elements at position $(i, j)$ move to $(j, i)$.
  * `display()`: Prints the contents of the matrix directly to the screen. Note that this is not a `toString` method; it must output the data directly.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation and testing

To successfully complete the exercise, implement a `main` method to perform the following tests:

1. **Class functionality**:
   * Create the `Matrix` class fulfilling all specified attributes, constructors, and methods.

2. **Testing in `main` method**:
   * Create several matrices using different values and dimensions.
   * Perform algebraic operations (addition, products, etc.) and display the results to verify correctness.

3. **Clean code and documentation**:
   * Ensure that the code is clean, legible, and includes explanatory comments where necessary.
