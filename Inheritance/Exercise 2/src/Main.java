import antiques.*;

/**
 * Main application class to test the {@link Antique} {@link Collection}  matrix operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    public static void main(String[] args) {
        // 1. Create a collection with a matrix of 4 centuries and 4 columns
        Collection myCollection = new Collection(4, 4);

        // Populate Century 1 (row 0)
        myCollection.addFurniture(new Furniture("A1", 45, "Wood")); // Target for deletion
        myCollection.addFurniture(new Painting("A2", 50, "Fresco"));
        myCollection.addFurniture(new Painting("A3", 60, "Oil"));
        myCollection.addFurniture(new Furniture("A4", 70, "Oak"));

        // Populate Century 2 (row 1)
        myCollection.addFurniture(new Painting("A5", 150, "Watercolor"));
        myCollection.addFurniture(new Furniture("A6", 160, "Pine"));

        // Populate Century 3 (row 2)
        myCollection.addFurniture(new Painting("A7", 250, "Pastel"));
        myCollection.addFurniture(new Painting("A8", 260, "Acrylic"));
        myCollection.addFurniture(new Furniture("A9", 270, "Mahogany"));

        // Populate Century 4 (row 3)
        myCollection.addFurniture(new Painting("A10", 310, "Gouache"));
        myCollection.addFurniture(new Furniture("A11", 350, "Cedar")); // Target for deletion
        myCollection.addFurniture(new Painting("A12", 360, "Tempera"));

        // Show initial state matching the first table in the prompt
        System.out.println("Initial state:");
        myCollection.displayMatrix();

        // 2. Call deleteFurniture() twice as requested
        myCollection.deleteFurniture(45); // Should delete A1 and shift A2, A3, A4
        myCollection.deleteFurniture(350); // Should delete A11 and shift A12

        // 3. Display the resulting matrix matching the second table
        System.out.println("\nState after deletions:");
        myCollection.displayMatrix();
    }
}
