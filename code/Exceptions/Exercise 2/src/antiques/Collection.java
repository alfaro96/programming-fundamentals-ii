package antiques;

import exceptions.CollectionFullException;
import exceptions.FurnitureNotFoundException;
import exceptions.YearOutOfRangeException;

/**
 * Manages a 2D array (matrix) of antiques categorized by century.
 *
 * <p>Each row of the matrix corresponds to one century (row 0 = 1st century,
 * row 1 = 2nd century, etc.). The number of rows and the maximum number of items
 * per row are fixed at construction time.</p>
 *
 * <p>Operations that modify the matrix declare checked exceptions so that callers
 * are forced to handle error conditions explicitly:</p>
 * <ul>
 *   <li>{@link CollectionFullException}: no free slot in the target century row.</li>
 *   <li>{@link FurnitureNotFoundException}: no matching furniture found for deletion.</li>
 *   <li>{@link YearOutOfRangeException}: the year maps to a century outside the matrix (unchecked, but documented).</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Antique
 * @see exceptions.CollectionFullException
 * @see exceptions.FurnitureNotFoundException
 * @see exceptions.YearOutOfRangeException
 */
public class Collection {

    /** The matrix storing antiques organized by century (rows) and insertion order (columns). */
    private Antique[][] matrix;

    /**
     * Constructs an empty collection matrix.
     *
     * @param centuries The number of centuries (rows) the collection covers.
     * @param maxItems The maximum number of antiques stored per century (columns).
     */
    public Collection(int centuries, int maxItems) {
        this.matrix = new Antique[centuries][maxItems];
    }

    /**
     * Inserts an antique into the row corresponding to its creation century.
     *
     * <p>The target row is determined as {@code (item.getYear() - 1) / 100}.
     * The antique is placed in the first available ({@code null}) slot of that row.</p>
     *
     * @param item The antique to add.
     * @throws YearOutOfRangeException If the antique's year maps to a century index that exceeds the matrix dimensions (unchecked).
     * @throws CollectionFullException If every slot in the target century row is occupied.
     */
    public void addAntique(Antique item) throws CollectionFullException {
        int rowIndex = (item.getYear() - 1) / 100;

        if (rowIndex < 0 || rowIndex >= this.matrix.length) {
            throw new YearOutOfRangeException(item.getYear());
        }

        for (int col = 0; col < this.matrix[rowIndex].length; col++) {
            if (this.matrix[rowIndex][col] == null) {
                this.matrix[rowIndex][col] = item;
                return; // Successfully inserted
            }
        }

        // No free slot was found in the row
        throw new CollectionFullException(rowIndex + 1);
    }

    /**
     * Removes all {@link Furniture} objects dated in the given year from the collection.
     *
     * <p>Items that must not be deleted are compacted to the left of their century row,
     * and all trailing positions are set to {@code null}.</p>
     *
     * @param year The specific year whose furniture entries should be deleted.
     * @throws YearOutOfRangeException If {@code year} maps to a century index outside the matrix (unchecked).
     * @throws FurnitureNotFoundException If no {@link Furniture} dated {@code year} exists in the collection.
     */
    public void deleteFurniture(int year) throws FurnitureNotFoundException {
        int rowIndex = (year - 1) / 100;

        if (rowIndex < 0 || rowIndex >= this.matrix.length) {
            throw new YearOutOfRangeException(year);
        }

        int insertPos = 0; // Next free position for retained items
        boolean deleted = false; // Tracks whether at least one item was removed

        for (int col = 0; col < this.matrix[rowIndex].length; col++) {
            Antique current = this.matrix[rowIndex][col];

            if (current != null) {
                boolean isTargetFurniture = (current instanceof Furniture)
                        && (current.getYear() == year);

                if (isTargetFurniture) {
                    deleted = true; // Mark that at least one item will be removed
                } else {
                    // Keep the item and shift it to the left-most available slot
                    this.matrix[rowIndex][insertPos] = current;
                    insertPos++;
                }
            }
        }

        if (!deleted) {
            throw new FurnitureNotFoundException(year);
        }

        // Clear the trailing positions left behind after compaction
        for (int col = insertPos; col < this.matrix[rowIndex].length; col++) {
            this.matrix[rowIndex][col] = null;
        }
    }

    /**
     * Retrieves the antique at a specific position in the matrix.
     *
     * <p>This helper is particularly useful for unit testing to inspect the internal state
     * without exposing the full matrix.</p>
     *
     * @param row The row (century) index.
     * @param col The column index within that century.
     * @return The {@link Antique} at the specified position, or {@code null} if the position is empty or out of bounds.
     */
    public Antique getAntiqueAt(int row, int col) {
        if (row >= 0 && row < this.matrix.length
                && col >= 0 && col < this.matrix[row].length) {
            return this.matrix[row][col];
        }
        return null;
    }

    /**
     * Prints the current state of the matrix to standard output.
     *
     * <p>Each row is labeled with its century number. Empty slots are shown as
     * {@code null}.</p>
     */
    public void displayMatrix() {
        System.out.println("Current collection matrix");
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.print("Century " + (i + 1) + ": ");
            for (int j = 0; j < this.matrix[i].length; j++) {
                System.out.print(this.matrix[i][j] != null
                        ? this.matrix[i][j].id + "\t"
                        : "null\t");
            }
        }
    }
}
