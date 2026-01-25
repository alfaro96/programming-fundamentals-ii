# Exercise 7

In this task, you will work with **arrays of arrays**. A `Matrix` is a two-dimensional grid where data is organized into rows and columns, allowing for complex algebraic operations and nested iteration logic.

---

## Objective

The goal of this exercise is to define a `Matrix` class in Java that represents an algebraic two-dimensional matrix containing numerical values and supports standard matrix arithmetic.

---

## Requirements

### Attributes

* **`int nRows`**: The number of rows in the matrix.
* **`int nCols`**: The number of columns in the matrix.
* **`double[][] values`**: A two-dimensional array representing the internal numerical data.

### Constructors

You must implement the following constructors to provide maximum flexibility:

1.  **Square (`n`)**: Creates an $ n \times n $ matrix with default values.
2.  **Square with initial value (`n`, `val`)**: Creates an $ n \times n $ matrix with all positions set to `val`.
3.  **Rectangular (`n`, `m`)**: Creates an $ n \times m $ matrix with default values.
4.  **Rectangular with initial value (`n`, `m`, `val`)**: Creates an $ n \times m $ matrix with all positions set to `val`.
5.  **From two-dimensional array**: Creates a matrix using a provided `double[][]` array, copying values manually.
6.  **Copy constructor**: Creates a new matrix based on an existing one by copying its values.

### Methods

* **`add(Matrix other)`**: Performs matrix addition and returns a **new** matrix.
* **`scalarProduct(double val)`**: Multiplies the matrix by a scalar and returns a **new** matrix.
* **`product(Matrix other)`**: Performs matrix multiplication and returns a **new** matrix.
* **`trace()`**: Returns the sum of the main diagonal (only for square matrices).
* **`transpose()`**: Returns a **new** matrix representing the transposition ($ n \times m $ becomes $ m \times n $).
* **`display()`**: Outputs the matrix content directly to the console.

---

## Evaluation criteria

To successfully complete the exercise, your internal `main` method must:

1.  **Instantiate**: Create several matrices using different dimensions and constructors.
2.  **Operate**: Perform and display the results of addition, products, and transpositions.
3.  **Verify**: Ensure square-only operations (like `trace`) handle non-square matrices appropriately.
4.  **Clean code**: Maintain legibility and include professional Javadoc comments.

---

## View the files

* [Matrix.java](file://src/Matrix.java)
