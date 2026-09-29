package antiques;

/**
 * Manages a 2D array (matrix) of antiques categorized by centuries.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Collection {

    /** The matrix containing the antiques. */
    private Antique[][] matrix;

    /**
     * Constructs an empty collection matrix.
     *
     * @param centuries The number of rows (centuries).
     * @param maxItems The maximum number of items per century (columns).
     */
    public Collection(int centuries, int maxItems) {
        this.matrix = new Antique[centuries][maxItems];
    }

    /**
     * Inserts an antique into the row corresponding to its century.
     * Finds the first null position. If full, it does not insert.
     *
     * @param item The antique to add.
     */
    public void addFurniture(Antique item) {
        int rowIndex = (item.getYear() - 1) / 100;

        if (rowIndex >= 0 && rowIndex < this.matrix.length) {
            for (int col = 0; col < this.matrix[rowIndex].length; col++) {
                if (this.matrix[rowIndex][col] == null) {
                    this.matrix[rowIndex][col] = item;
                    return; // Successfully inserted, exit method
                }
            }
            System.out.println("Warning: No spaces available in century " + (rowIndex + 1) + " for " + item.id);
        }
    }

    /**
     * Removes all furniture dated in a given year.
     *
     * @param year The specific year to search and delete.
     */
    public void deleteFurniture(int year) {
        int rowIndex = (year - 1) / 100;

        if (rowIndex >= 0 && rowIndex < this.matrix.length) {
            int insertPos = 0; // Tracks the next valid position for remaining items

            // Iterate through the row, keeping items that should not be deleted
            for (int col = 0; col < this.matrix[rowIndex].length; col++) {
                Antique current = this.matrix[rowIndex][col];

                if (current != null) {
                    boolean isTargetFurniture = (current instanceof Furniture) && (current.getYear() == year);

                    // If it's not the furniture we want to delete, shift it to the left-most available spot
                    if (!isTargetFurniture) {
                        this.matrix[rowIndex][insertPos] = current;
                        insertPos++;
                    }
                }
            }

            // Fill all remaining positions at the end of the row with null
            for (int col = insertPos; col < this.matrix[rowIndex].length; col++) {
                this.matrix[rowIndex][col] = null;
            }
        }
    }

    /**
     * Retrieves the antique located at a specific row and column in the matrix.
     * This helper method is particularly useful for unit testing to verify the internal state.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The {@link Antique} at the specified position, or {@code null} if the position is empty or out of bounds.
     */
    public Antique getAntiqueAt(int row, int col) {
        if (row >= 0 && row < this.matrix.length && col >= 0 && col < this.matrix[row].length) {
            return this.matrix[row][col];
        }
        return null;
    }

    /**
     * Displays the current state of the matrix to the console.
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