import org.junit.Test;
import static org.junit.Assert.*;

import antiques.Collection;
import antiques.Antique;
import antiques.Furniture;
import antiques.Painting;

/**
 * Test suite to validate the {@link Antique} {@link Collection} logic.
 * Covers insertion by century, polymorphic deletion, and left-shift algorithms.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * Verifies that the {@link Antique#toString} methods are correctly overridden and
   * use the protected attributes from the superclass.
   */
  @Test
  public void testToStringOverrides() {
    Antique p = new Painting("A1", 150, "Oil");
    Antique f = new Furniture("A2", 200, "Wood");

    assertTrue("Painting toString should contain its identifier", p.toString().contains("A1"));
    assertTrue("Painting toString should contain its technique", p.toString().contains("Oil"));
    assertTrue("Furniture toString should contain its identifier", f.toString().contains("A2"));
    assertTrue("Furniture toString should contain its material", f.toString().contains("Wood"));
  }

  /**
   * Verifies that antiques are placed in the correct row based on their century.
   * Year 45 -> Century 1 (Row 0). Year 150 -> Century 2 (Row 1).
   */
  @Test
  public void testAddFurnitureCorrectCentury() {
    Collection collection = new Collection(4, 4);
    Antique f1 = new Furniture("A1", 45, "Oak");
    Antique p1 = new Painting("A2", 150, "Fresco");

    collection.addFurniture(f1);
    collection.addFurniture(p1);

    assertEquals("A1 should be in row 0 and column 0", f1, collection.getAntiqueAt(0, 0));
    assertEquals("A2 should be in row 1, column 0", p1, collection.getAntiqueAt(1, 0));
  }

  /**
   * Verifies that the {@link Collection#deleteFurniture} method correctly removes only {@link Furniture}
   * from the specified year and shifts the remaining elements to the left.
   */
  @Test
  public void testDeleteFurnitureAndShift() {
    Collection collection = new Collection(1, 4); // 1 century, 4 spots

    Antique f1 = new Furniture("A1", 45, "Wood"); // Target
    Antique p1 = new Painting("A2", 50, "Fresco"); // Should stay (is Painting)
    Antique f2 = new Furniture("A3", 60, "Oak");   // Should stay (wrong year)
    Antique f3 = new Furniture("A4", 45, "Pine"); // Target

    collection.addFurniture(f1);
    collection.addFurniture(p1);
    collection.addFurniture(f2);
    collection.addFurniture(f3);

    // Call the deletion method for year 45
    collection.deleteFurniture(45);

    // Expected state: p1 and f2 should have shifted to index 0 and 1.
    // Indices 2 and 3 should be null.
    assertEquals("First element should now be A2 (Painting)", p1, collection.getAntiqueAt(0, 0));
    assertEquals("Second element should now be A3 (Furniture)", f2, collection.getAntiqueAt(0, 1));
    assertNull("Third element should be null after shift", collection.getAntiqueAt(0, 2));
    assertNull("Fourth element should be null after shift", collection.getAntiqueAt(0, 3));
  }

  /**
   * Verifies that if a century is full, the method handles it gracefully
   * without throwing {@code ArrayOutOfBounds} exceptions.
   */
  @Test
  public void testAddFurnitureFullRow() {
    Collection collection = new Collection(1, 2); // Only 2 spots in century 1

    Antique a1 = new Furniture("A1", 10, "Wood");
    Antique a2 = new Furniture("A2", 20, "Wood");
    Antique a3 = new Furniture("A3", 30, "Wood"); // Should not be added

    collection.addFurniture(a1);
    collection.addFurniture(a2);
    collection.addFurniture(a3); // Matrix row is full

    assertEquals("Spot 0 should be A1", a1, collection.getAntiqueAt(0, 0));
    assertEquals("Spot 1 should be A2", a2, collection.getAntiqueAt(0, 1));
    // If we try to get spot 2, it would throw an error, meaning A3 was safely ignored.
  }
}