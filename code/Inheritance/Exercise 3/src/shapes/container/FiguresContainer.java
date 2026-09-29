package shapes.container;

import shapes.GeometricFigure;

/**
 * A container class that manages a 2D matrix of {@link GeometricFigure} objects.
 * Demonstrates dynamic polymorphism by storing both polygons and parallelograms
 * under a common parent reference.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class FiguresContainer {

    /** * The 2D array (matrix) storing the figures.
     * Capable of holding any subclass of {@link GeometricFigure}.
     */
    private GeometricFigure[][] M;

    /** The total number of rows in the matrix. */
    private int rows;

    /** The total number of columns in the matrix. */
    private int columns;

    /**
     * Constructs a new empty {@link FiguresContainer}.
     * Initializes the matrix full of {@code null} values.
     *
     * @param rows The desired number of rows.
     * @param columns The desired number of columns.
     */
    public FiguresContainer(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.M = new GeometricFigure[this.rows][this.columns];
    }

    /**
     * Inserts a geometric figure into a specific coordinate of the matrix.
     *
     * @param figure The {@link GeometricFigure} to insert (can be a polygon or parallelogram).
     * @param row The target row index.
     * @param col The target column index.
     */
    public void addFigure(GeometricFigure figure, int row, int col) {
        if (row >= 0 && row < this.rows && col >= 0 && col < this.columns) {
            this.M[row][col] = figure;
        } else {
            System.out.println("Error: Coordinates out of bounds.");
        }
    }

    /**
     * Sums the areas of all figures located exactly on the main diagonal
     * (where row index equals column index).
     *
     * @return The sum of the areas, or {@code -1} if the matrix is not square.
     */
    public double sumAreas() {
        if (this.rows != this.columns) {
            return -1;
        }

        double totalArea = 0;
        for (int i = 0; i < this.rows; i++) {
            if (this.M[i][i] != null) {
                totalArea += this.M[i][i].calculateArea();
            }
        }
        return totalArea;
    }

    /**
     * Sums the perimeters of all figures located exactly on the secondary diagonal.
     *
     * @return The sum of the perimeters, or {@code -1} if the matrix is not square.
     */
    public double sumPerimeters() {
        if (this.rows != this.columns) {
            return -1;
        }

        double totalPerimeter = 0;
        for (int i = 0; i < this.rows; i++) {
            int secondaryCol = (this.columns - 1) - i;
            if (this.M[i][secondaryCol] != null) {
                totalPerimeter += this.M[i][secondaryCol].calculatePerimeter();
            }
        }
        return totalPerimeter;
    }
}
