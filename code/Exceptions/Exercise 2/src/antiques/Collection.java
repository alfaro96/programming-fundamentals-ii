package antiques;

import exceptions.CollectionFullException;
import exceptions.FurnitureNotFoundException;
import exceptions.YearOutOfRangeException;

/**
 * Manages a 2D array (matrix) of antiques categorized by centuries.
 *
 * <p>Row {@code i} holds the antiques of century {@code i + 1}. Adding to a full row, deleting
 * furniture that is not there, and a year with no row in the matrix are reported with
 * exceptions.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see CollectionFullException
 * @see FurnitureNotFoundException
 * @see YearOutOfRangeException
 */
public class Collection {

    /** The matrix containing the antiques. */
    private Antique[][] matrix;

    /**
     * Constructs an empty collection matrix.
     *
     * @param centuries the number of rows (centuries).
     * @param maxItems the maximum number of items per century (columns).
     */
    public Collection(int centuries, int maxItems) {
        this.matrix = new Antique[centuries][maxItems];
    }

    /**
     * Returns the row of the matrix for a year.
     *
     * @param year the year of an antique.
     * @return the index of the row of its century.
     * @throws YearOutOfRangeException if the matrix has no row for the century of {@code year}.
     */
    private int rowOf(int year) {
        int rowIndex = (year - 1) / 100;
        if (year < 1 || rowIndex >= this.matrix.length) {
            throw new YearOutOfRangeException(year);
        }
        return rowIndex;
    }

    /**
     * Inserts an antique in the first {@code null} position of the row of its century.
     *
     * @param item the antique to add.
     * @throws YearOutOfRangeException if the matrix has no row for its century.
     * @throws CollectionFullException if the row of its century has no {@code null} position.
     */
    public void addFurniture(Antique item) throws CollectionFullException {
        int rowIndex = this.rowOf(item.getYear());

        for (int col = 0; col < this.matrix[rowIndex].length; col++) {
            if (this.matrix[rowIndex][col] == null) {
                this.matrix[rowIndex][col] = item;
                return;
            }
        }
        throw new CollectionFullException(rowIndex + 1);
    }

    /**
     * Removes all the furniture dated in a given year, moving the rest of its row to the left.
     *
     * @param year the year of the furniture to delete.
     * @throws YearOutOfRangeException if the matrix has no row for the century of {@code year}.
     * @throws FurnitureNotFoundException if there is no furniture dated in {@code year}.
     */
    public void deleteFurniture(int year) throws FurnitureNotFoundException {
        int rowIndex = this.rowOf(year);
        int insertPos = 0;
        boolean deleted = false;

        for (int col = 0; col < this.matrix[rowIndex].length; col++) {
            Antique current = this.matrix[rowIndex][col];

            if (current != null) {
                if (current instanceof Furniture && current.getYear() == year) {
                    deleted = true;
                } else {
                    this.matrix[rowIndex][insertPos] = current;
                    insertPos++;
                }
            }
        }

        if (!deleted) {
            throw new FurnitureNotFoundException(year);
        }

        for (int col = insertPos; col < this.matrix[rowIndex].length; col++) {
            this.matrix[rowIndex][col] = null;
        }
    }

    /**
     * Returns the antique located at a specific row and column in the matrix.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the {@link Antique} at that position, or {@code null} if it is empty or out of
     *         bounds.
     */
    public Antique getAntiqueAt(int row, int col) {
        if (row >= 0 && row < this.matrix.length && col >= 0 && col < this.matrix[row].length) {
            return this.matrix[row][col];
        }
        return null;
    }

    /**
     * Displays the current state of the matrix on the console.
     */
    public void displayMatrix() {
        System.out.println("--- Current collection matrix ---");
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.print("Century " + (i + 1) + ": ");
            for (int j = 0; j < this.matrix[i].length; j++) {
                if (this.matrix[i][j] != null) {
                    System.out.print(this.matrix[i][j].id + "\t");
                } else {
                    System.out.print("null\t");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
}
