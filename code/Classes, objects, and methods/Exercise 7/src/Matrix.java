/**
 * Represents a matrix of real numbers with {@link #nRows} rows and {@link #nCols} columns.
 * <p>
 * The values are kept in an array of arrays: {@code values[i]} is row <i>i</i>, and
 * {@code values[i][j]} the value in row <i>i</i> and column <i>j</i>, both counted from 0.
 * <p>
 * The operations return new matrices and leave the ones they receive unchanged.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
class Matrix {

    /**
     * The number of rows.
     */
    int nRows;

    /**
     * The number of columns.
     */
    int nCols;

    /**
     * The values of the matrix, one array per row.
     */
    double[][] values;

    /**
     * Creates a square matrix of size <i>n</i> &times; <i>n</i> with every value at 0.
     *
     * @param n the number of rows and of columns.
     */
    Matrix(int n) {
        this(n, n);
    }

    /**
     * Creates a square matrix of size <i>n</i> &times; <i>n</i> with every value at {@code val}.
     *
     * @param n the number of rows and of columns.
     * @param val the value of every position.
     */
    Matrix(int n, double val) {
        this(n, n, val);
    }

    /**
     * Creates a matrix of size <i>n</i> &times; <i>m</i> with every value at 0.
     *
     * @param n the number of rows.
     * @param m the number of columns.
     */
    Matrix(int n, int m) {
        this.nRows = n;
        this.nCols = m;
        this.values = new double[n][m];
    }

    /**
     * Creates a matrix of size <i>n</i> &times; <i>m</i> with every value at {@code val}.
     *
     * @param n the number of rows.
     * @param m the number of columns.
     * @param val the value of every position.
     */
    Matrix(int n, int m, double val) {
        this(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.values[i][j] = val;
            }
        }
    }

    /**
     * Creates a matrix with the given values, which are copied: changing the array
     * afterwards does not change the matrix. Every row must have the same length.
     *
     * @param values the values, one array per row.
     */
    Matrix(double[][] values) {
        this(values.length, values[0].length);
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                this.values[i][j] = values[i][j];
            }
        }
    }

    /**
     * Creates a copy of another matrix, with its own values: changing one of them does not
     * change the other.
     *
     * @param other the matrix to copy.
     */
    Matrix(Matrix other) {
        this(other.values);
    }

    /**
     * Adds another matrix of the same size to this one, position by position.
     *
     * @param other the matrix to add.
     * @return a new matrix with the sum, or {@code null} if the sizes differ.
     */
    Matrix add(Matrix other) {
        if (this.nRows != other.nRows || this.nCols != other.nCols) {
            return null;
        }
        Matrix result = new Matrix(this.nRows, this.nCols);
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                result.values[i][j] = this.values[i][j] + other.values[i][j];
            }
        }
        return result;
    }

    /**
     * Multiplies every value of this matrix by a number.
     *
     * @param scalar the number to multiply by.
     * @return a new matrix with the product.
     */
    Matrix scalarProduct(double scalar) {
        Matrix result = new Matrix(this.nRows, this.nCols);
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                result.values[i][j] = this.values[i][j] * scalar;
            }
        }
        return result;
    }

    /**
     * Multiplies this matrix by another one, whose number of rows must match the number of
     * columns of this one.
     * <p>
     * The value in row <i>i</i> and column <i>j</i> of the result is the sum of the products
     * of row <i>i</i> of this matrix by column <i>j</i> of the other, element by element.
     *
     * @param other the matrix to multiply by, on the right.
     * @return a new matrix with {@link #nRows} rows and as many columns as {@code other}, or
     *         {@code null} if the sizes do not fit.
     */
    Matrix product(Matrix other) {
        if (this.nCols != other.nRows) {
            return null;
        }
        Matrix result = new Matrix(this.nRows, other.nCols);
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < other.nCols; j++) {
                for (int k = 0; k < this.nCols; k++) {
                    result.values[i][j] = result.values[i][j] + this.values[i][k] * other.values[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Computes the trace: the sum of the values on the main diagonal, from the top left to the
     * bottom right. Only a square matrix has one.
     *
     * @return the trace, or 0 if the matrix is not square.
     */
    double trace() {
        if (this.nRows != this.nCols) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < this.nRows; i++) {
            sum = sum + this.values[i][i];
        }
        return sum;
    }

    /**
     * Computes the transpose: the rows become columns, so the value in row <i>i</i> and
     * column <i>j</i> goes to row <i>j</i> and column <i>i</i>.
     *
     * @return a new matrix with {@link #nCols} rows and {@link #nRows} columns.
     */
    Matrix transpose() {
        Matrix result = new Matrix(this.nCols, this.nRows);
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                result.values[j][i] = this.values[i][j];
            }
        }
        return result;
    }

    /**
     * Prints the matrix, one row per line, with every value to two decimal places.
     */
    void display() {
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                System.out.printf("%8.2f", this.values[i][j]);
            }
            System.out.println();
        }
    }
}
