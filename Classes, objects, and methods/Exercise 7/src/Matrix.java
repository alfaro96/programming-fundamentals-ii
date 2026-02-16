/**
 * Represents an algebraic two-dimensional matrix of numerical values.
 * This class demonstrates the use of arrays of arrays and complex nested logic.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Matrix {

   /** Number of rows in the matrix. */
   public int nRows;

   /** Number of columns in the matrix. */
   public int nCols;

   /** Internal 2D array storing the matrix values. */
   public double[][] values;

   /**
    * Creates a square-shaped matrix of size n x n with default values.
    *
    * @param n The dimension for both rows and columns.
    */
   public Matrix(int n) {
      this(n, n);
   }

   /**
    * Creates a square-shaped matrix of size n x n initialized to a specific value.
    *
    * @param n The dimension for both rows and columns.
    * @param val The initial value for all positions.
    */
   public Matrix(int n, double val) {
      this(n, n, val);
   }

   /**
    * Creates a rectangular matrix of size n x m with default values.
    *
    * @param n Number of rows.
    * @param m Number of columns.
    */
   public Matrix(int n, int m) {
      this.nRows = n;
      this.nCols = m;
      this.values = new double[n][m];
   }

   /**
    * Creates a rectangular matrix of size n x m initialized to a specific value.
    *
    * @param n Number of rows.
    * @param m Number of columns.
    * @param val The initial value for all positions.
    */
   public Matrix(int n, int m, double val) {
      this(n, m);
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            this.values[i][j] = val;
         }
      }
   }


   /**
    * Creates a matrix from a provided two-dimensional array.
    *
    * @param values The two-dimensional array of doubles.
    */
   public Matrix(double[][] values) {
      this.nRows = values.length;
      this.nCols = values[0].length;
      this.values = new double[this.nRows][this.nCols];
      for (int i = 0; i < this.nRows; i++) {
         for (int j = 0; j < this.nCols; j++) {
            this.values[i][j] = values[i][j];
         }
      }
   }

   /**
    * Copy constructor. Creates a new matrix based on an existing one.
    *
    * @param other The {@link Matrix} object to copy.
    */
   public Matrix(Matrix other) {
      this(other.values);
   }

   /**
    * Performs an addition with another matrix.
    *
    * @param other The matrix to add.
    * @return A new {@link Matrix} with the result, or {@code null} if dimensions mismatch.
    */
   public Matrix add(Matrix other) {
      if (this.nRows != other.nRows || this.nCols != other.nCols) return null;
      Matrix result = new Matrix(this.nRows, this.nCols);
      for (int i = 0; i < this.nRows; i++) {
         for (int j = 0; j < this.nCols; j++) {
            result.values[i][j] = this.values[i][j] + other.values[i][j];
         }
      }
      return result;
   }

   /**
    * Multiplies the matrix by a scalar value.
    *
    * @param val The numerical value to multiply by.
    * @return A new {@link Matrix} with the scaled values.
    */
   public Matrix scalarProduct(double val) {
      Matrix result = new Matrix(this.nRows, this.nCols);
      for (int i = 0; i < this.nRows; i++) {
         for (int j = 0; j < this.nCols; j++) {
            result.values[i][j] = this.values[i][j] * val;
         }
      }
      return result;
   }

   /**
    * Performs matrix multiplication between this and another matrix.
    *
    * @param other The matrix to multiply with.
    * @return A new {@link Matrix} with the result, or {@code null} if dimensions are incompatible.
    */
   public Matrix product(Matrix other) {
      if (this.nCols != other.nRows) return null;
      Matrix result = new Matrix(this.nRows, other.nCols);
      for (int i = 0; i < this.nRows; i++) {
         for (int j = 0; j < other.nCols; j++) {
            for (int k = 0; k < this.nCols; k++) {
               result.values[i][j] += this.values[i][k] * other.values[k][j];
            }
         }
      }
      return result;
   }

   /**
    * Calculates the sum of values on the main diagonal.
    *
    * @return The trace value, or 0.0 if the matrix is not square.
    */
   public double trace() {
      if (this.nRows != this.nCols) return 0.0;
      double sum = 0;
      for (int i = 0; i < this.nRows; i++) {
         sum += this.values[i][i];
      }
      return sum;
   }

   /**
    * Creates a new matrix representing the transposition.
    *
    * @return A new m x n {@link Matrix}.
    */
   public Matrix transpose() {
      Matrix result = new Matrix(this.nCols, this.nRows);
      for (int i = 0; i < this.nRows; i++) {
         for (int j = 0; j < this.nCols; j++) {
            result.values[j][i] = this.values[i][j];
         }
      }
      return result;
   }

   /**
    * Directly prints the matrix content to the console.
    */
   public void display() {
      for (int i = 0; i < this.nRows; i++) {
         for (int j = 0; j < this.nCols; j++) {
            System.out.printf("%.2f\t", this.values[i][j]);
         }
         System.out.println();
      }
   }
}
