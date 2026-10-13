# Exercise 7

The goal of this exercise is to write a `Matrix` class that represents an algebraic matrix of real numbers, with rows and columns. It is a good way to get used to arrays of arrays: `double[][] values` is an array whose elements are arrays, one per row, so `values[i][j]` is the value in row `i` and column `j`, both counted from 0. It is created as `new double[n][m]`, with `n` rows and `m` columns, and `values.length` gives the number of rows.

### Implementation

Write a `Matrix` class with the following requirements:

* **Attributes**:
  * `int nRows`: the number of rows.
  * `int nCols`: the number of columns.
  * `double[][] values`: the values of the matrix, one array per row.

* **Constructors**:
  * `Matrix(int n)`: creates a square matrix of size $n \times n$ with every value at 0.
  * `Matrix(int n, double val)`: creates a square matrix of size $n \times n$ with every value at `val`.
  * `Matrix(int n, int m)`: creates a matrix of size $n \times m$ with every value at 0.
  * `Matrix(int n, int m, double val)`: creates a matrix of size $n \times m$ with every value at `val`.
  * `Matrix(double[][] values)`: creates a matrix with the size and values of the given array. The values are copied, so changing the array afterwards does not change the matrix.
  * `Matrix(Matrix other)`: creates a copy of another matrix, with its own values, so changing one of them does not change the other.

* **Methods**:
  * `add()`: adds another matrix of the same size to this one, position by position, and returns a **new** matrix with the result, or `null` if the sizes differ.
  * `scalarProduct()`: multiplies every value of the matrix by a number and returns a **new** matrix with the result.
  * `product()`: multiplies this matrix by another one and returns a **new** matrix with the result. The number of columns of the first matrix must match the number of rows of the second; if it does not, the method returns `null`. The value in row $i$ and column $j$ of the result is the sum of the products of row $i$ of the first matrix by column $j$ of the second, element by element.
  * `trace()`: returns the sum of the values on the main diagonal, from the top left to the bottom right. Only a square matrix has a trace; for any other matrix, the method returns 0.
  * `transpose()`: returns a **new** matrix with the rows of this one as columns: if this matrix is $n \times m$, the result is $m \times n$, and the value at position $(i, j)$ moves to $(j, i)$.
  * `display()`: prints the matrix on the screen, one row per line. Unlike `describe()` in the previous exercises, it does not return a `String`: it prints the values itself.

None of the operations changes the matrices it works with.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.
* **Reuse**: a constructor can call another one with `this(...)`, so most of them can be written in a single line.

### Evaluation and testing

To complete the exercise, write a `Main` class with a `main` method that performs the following tests:

1. **Class functionality**:
   * Write the `Matrix` class with all the attributes, constructors and methods described.

2. **Testing in the `main` method**:
   * Create several matrices with different values and sizes, using every constructor.
   * Operate with them (addition, products, trace and transpose) and display the results to check that they are correct.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
