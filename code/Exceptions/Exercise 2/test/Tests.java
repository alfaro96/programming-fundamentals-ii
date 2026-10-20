import org.junit.Test;
import static org.junit.Assert.*;

import antiques.Antique;
import antiques.Collection;
import antiques.Furniture;
import antiques.Painting;
import exceptions.CollectionFullException;
import exceptions.FurnitureNotFoundException;
import exceptions.InvalidPriceException;
import exceptions.YearOutOfRangeException;

/**
 * Test suite to validate the {@link Antique} {@link Collection} logic and the exceptions it
 * throws.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /** Delta value for floating-point comparisons to prevent precision errors. */
  private static final double DELTA = 1e-9;

  /**
   * Verifies that the {@code toString} methods include every attribute, the price among them.
   *
   * @throws Exception if a constructor rejects a valid value.
   */
  @Test
  public void testToStringOverrides() throws Exception {
    Antique p = new Painting("A1", 150, 800.0, "Oil");
    Antique f = new Furniture("A2", 200, 300.0, "Wood");

    assertTrue("Painting toString should contain its identifier", p.toString().contains("A1"));
    assertTrue("Painting toString should contain its price", p.toString().contains("800"));
    assertTrue("Painting toString should contain its technique", p.toString().contains("Oil"));
    assertTrue("Furniture toString should contain its identifier", f.toString().contains("A2"));
    assertTrue("Furniture toString should contain its material", f.toString().contains("Wood"));
    assertEquals("The price should be kept", 300.0, f.getPrice(), DELTA);
  }

  /**
   * Verifies that a negative price throws an {@link InvalidPriceException} whose message
   * includes it, and that a price of 0 is valid.
   *
   * @throws Exception if a constructor rejects a price of 0.
   */
  @Test
  public void testNegativePrice() throws Exception {
    try {
      new Furniture("F1", 1750, -999.0, "Oak");
      fail("A negative price should throw an InvalidPriceException");
    } catch (InvalidPriceException e) {
      assertTrue("The message should include the price", e.getMessage().contains("-999"));
    }
    try {
      new Painting("P1", 101, -1.0, "Fresco");
      fail("A negative price should throw an InvalidPriceException");
    } catch (InvalidPriceException e) {
      // expected
    }
    assertEquals("A price of 0 should be valid", 0.0, new Furniture("F0", 101, 0.0, "Pine").getPrice(), DELTA);
  }

  /**
   * Verifies that a year out of range throws a {@link YearOutOfRangeException}, and that the
   * first and last years of the range are valid.
   *
   * @throws Exception if a constructor rejects a valid value.
   */
  @Test
  public void testYearOutOfRange() throws Exception {
    try {
      new Furniture("F1", 0, 100.0, "Oak");
      fail("Year 0 should throw a YearOutOfRangeException");
    } catch (YearOutOfRangeException e) {
      assertTrue("The message should include the year", e.getMessage().contains("0"));
    }
    try {
      new Painting("P1", 2025, 100.0, "Oil");
      fail("Year 2025 should throw a YearOutOfRangeException");
    } catch (YearOutOfRangeException e) {
      assertTrue("The message should include the year", e.getMessage().contains("2025"));
    }
    assertEquals("The first year should be valid", Antique.MIN_YEAR,
        new Furniture("F2", Antique.MIN_YEAR, 100.0, "Stone").getYear());
    assertEquals("The last year should be valid", Antique.MAX_YEAR,
        new Furniture("F3", Antique.MAX_YEAR, 100.0, "Cherry").getYear());
  }

  /**
   * Verifies that antiques are placed in the correct row based on their century.
   * Year 45 is century 1 (row 0), year 150 is century 2 (row 1).
   *
   * @throws Exception if a constructor or {@code addFurniture} throws.
   */
  @Test
  public void testAddFurnitureCorrectCentury() throws Exception {
    Collection collection = new Collection(4, 4);
    Antique f1 = new Furniture("A1", 45, 100.0, "Oak");
    Antique p1 = new Painting("A2", 150, 200.0, "Fresco");
    collection.addFurniture(f1);
    collection.addFurniture(p1);

    assertSame("Year 45 should go to row 0", f1, collection.getAntiqueAt(0, 0));
    assertSame("Year 150 should go to row 1", p1, collection.getAntiqueAt(1, 0));
  }

  /**
   * Verifies that adding to a full row throws a {@link CollectionFullException} that names the
   * century, and that a year with no row in the matrix throws a {@link YearOutOfRangeException}.
   *
   * @throws Exception if a constructor throws.
   */
  @Test
  public void testAddFurnitureErrors() throws Exception {
    Collection collection = new Collection(4, 2);
    collection.addFurniture(new Furniture("A1", 10, 100.0, "Oak"));
    collection.addFurniture(new Furniture("A2", 20, 100.0, "Pine"));
    try {
      collection.addFurniture(new Furniture("A3", 30, 100.0, "Elm"));
      fail("A full row should throw a CollectionFullException");
    } catch (CollectionFullException e) {
      assertTrue("The message should include the century", e.getMessage().contains("1"));
    }
    try {
      collection.addFurniture(new Furniture("A4", 450, 100.0, "Ash"));
      fail("A year with no row should throw a YearOutOfRangeException");
    } catch (YearOutOfRangeException e) {
      // expected
    }
  }

  /**
   * Verifies the example of the statement: deleting the furniture of years 45 and 350 removes
   * A1 and A11, and the rest of their rows move to the left.
   *
   * @throws Exception if a constructor, {@code addFurniture} or {@code deleteFurniture} throws.
   */
  @Test
  public void testDeleteFurnitureAndShift() throws Exception {
    Collection collection = new Collection(4, 4);
    Antique a1 = new Furniture("A1", 45, 100.0, "Wood");
    Antique a2 = new Painting("A2", 50, 100.0, "Fresco");
    Antique a4 = new Furniture("A4", 70, 100.0, "Oak");
    Antique a12 = new Painting("A12", 360, 100.0, "Tempera");
    collection.addFurniture(a1);
    collection.addFurniture(a2);
    collection.addFurniture(new Painting("A3", 60, 100.0, "Oil"));
    collection.addFurniture(a4);
    collection.addFurniture(new Painting("A10", 310, 100.0, "Gouache"));
    collection.addFurniture(new Furniture("A11", 350, 100.0, "Cedar"));
    collection.addFurniture(a12);

    collection.deleteFurniture(45);
    collection.deleteFurniture(350);

    assertSame("A2 should move to the first column", a2, collection.getAntiqueAt(0, 0));
    assertSame("A4 should move to the third column", a4, collection.getAntiqueAt(0, 2));
    assertNull("The last column of century 1 should be null", collection.getAntiqueAt(0, 3));
    assertSame("A12 should move to the second column", a12, collection.getAntiqueAt(3, 1));
    assertNull("The third column of century 4 should be null", collection.getAntiqueAt(3, 2));
  }

  /**
   * Verifies that deleting a year with no furniture, even with a painting of that year,
   * throws a {@link FurnitureNotFoundException} that includes the year and leaves the row as it
   * was.
   *
   * @throws Exception if a constructor or {@code addFurniture} throws.
   */
  @Test
  public void testDeleteFurnitureNotFound() throws Exception {
    Collection collection = new Collection(4, 4);
    Antique painting = new Painting("P1", 50, 100.0, "Fresco");
    collection.addFurniture(painting);
    try {
      collection.deleteFurniture(50);
      fail("A year with no furniture should throw a FurnitureNotFoundException");
    } catch (FurnitureNotFoundException e) {
      assertTrue("The message should include the year", e.getMessage().contains("50"));
    }
    assertSame("The painting should stay where it was", painting, collection.getAntiqueAt(0, 0));
  }
}
