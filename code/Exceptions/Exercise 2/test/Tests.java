import antiques.Collection;
import antiques.Furniture;
import antiques.Painting;
import exceptions.CollectionFullException;
import exceptions.FurnitureNotFoundException;
import exceptions.InvalidPriceException;
import exceptions.YearOutOfRangeException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>Covers the four exception classes ({@link InvalidPriceException},
 * {@link YearOutOfRangeException}, {@link CollectionFullException},
 * {@link FurnitureNotFoundException}) as well as the core logic of
 * {@link Collection#addAntique(antiques.Antique)} and
 * {@link Collection#deleteFurniture(int)}.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /** Small collection used across most tests: 5 centuries, 3 slots per century. */
  private Collection collection;

  /**
   * Resets the shared {@link Collection} before every test so that tests are
   * completely independent of one another.
   */
  @Before
  public void setUp() {
    collection = new Collection(5, 3);
  }

  /**
   * A negative price on a {@link Furniture} must throw {@link InvalidPriceException}.
   */
  @Test(expected = InvalidPriceException.class)
  public void testFurnitureNegativePriceThrows() throws InvalidPriceException {
    new Furniture("F01", 1750, -100, "Oak");
  }

  /**
   * A negative price on a {@link Painting} must throw {@link InvalidPriceException}.
   */
  @Test(expected = InvalidPriceException.class)
  public void testPaintingNegativePriceThrows() throws InvalidPriceException {
    new Painting("P01", 101, -1, "Oil on canvas");
  }

  /**
   * A price of exactly 0 is valid and must not throw any exception.
   */
  @Test
  public void testZeroPriceIsValid() throws InvalidPriceException {
    Furniture furniture = new Furniture("F00", 101, 0, "Pine");
    Assert.assertEquals(0.0, furniture.getPrice(), 0.001);
  }

  /**
   * The message of {@link InvalidPriceException} must mention the offending value.
   */
  @Test
  public void testInvalidPriceExceptionMessage() {
    try {
      new Furniture("F01", 101, -999, "Walnut");
      Assert.fail("Expected InvalidPriceException was not thrown");
    } catch (InvalidPriceException e) {
      Assert.assertTrue(e.getMessage().contains("-999"));
    }
  }

  /**
   * Year 0 is below {@link YearOutOfRangeException#MIN_YEAR} and must throw.
   */
  @Test(expected = YearOutOfRangeException.class)
  public void testFurnitureYearZeroThrows() throws InvalidPriceException {
    new Furniture("F02", 0, 500, "Mahogany");
  }

  /**
   * Year 1901 is above {@link YearOutOfRangeException#MAX_YEAR} and must throw.
   */
  @Test(expected = YearOutOfRangeException.class)
  public void testFurnitureYearTooLargeThrows() throws InvalidPriceException {
    new Furniture("F03", 1901, 200, "Ebony");
  }

  /**
   * Year 1900 is exactly on the upper boundary and must be valid.
   */
  @Test
  public void testFurnitureYearMaxBoundaryIsValid() throws InvalidPriceException {
    Furniture furniture = new Furniture("F04", 1900, 300, "Cherry");
    Assert.assertEquals(1900, furniture.getYear());
  }

  /**
   * Year 1 is exactly on the lower boundary and must be valid.
   */
  @Test
  public void testFurnitureYearMinBoundaryIsValid() throws InvalidPriceException {
    Furniture furniture = new Furniture("F05", 1, 100, "Stone");
    Assert.assertEquals(1, furniture.getYear());
  }

  /**
   * The message of {@link YearOutOfRangeException} must mention the offending year
   * and the accepted bounds.
   */
  @Test
  public void testYearOutOfRangeExceptionMessage() {
    try {
      new Furniture("F06", 2025, 500, "Oak");
      Assert.fail("Expected YearOutOfRangeException was not thrown");
    } catch (YearOutOfRangeException e) {
      Assert.assertTrue(e.getMessage().contains("2025"));
      Assert.assertTrue(e.getMessage().contains(String.valueOf(YearOutOfRangeException.MIN_YEAR)));
      Assert.assertTrue(e.getMessage().contains(String.valueOf(YearOutOfRangeException.MAX_YEAR)));
    } catch (InvalidPriceException e) {
      Assert.fail("Wrong exception type thrown: " + e.getClass().getSimpleName());
    }
  }

  /**
   * A valid antique is placed in the first slot of the correct century row.
   * Year 101 maps to row index 1 (2nd century).
   */
  @Test
  public void testAddAntiqueInsertedInCorrectRow() throws InvalidPriceException, CollectionFullException {
    Furniture furniture = new Furniture("F10", 101, 400, "Oak");
    collection.addAntique(furniture);
    Assert.assertSame(furniture, collection.getAntiqueAt(1, 0));
  }

  /**
   * Two antiques in the same century are placed in consecutive columns.
   */
  @Test
  public void testAddAntiqueConsecutiveColumns() throws InvalidPriceException, CollectionFullException {
    Furniture f1 = new Furniture("F11", 101, 400, "Oak");
    Painting  p1 = new Painting("P11", 150, 800, "Fresco");
    collection.addAntique(f1);
    collection.addAntique(p1);
    Assert.assertSame(f1, collection.getAntiqueAt(1, 0));
    Assert.assertSame(p1, collection.getAntiqueAt(1, 1));
  }

  /**
   * Antiques from different centuries end up in their respective rows.
   */
  @Test
  public void testAddAntiqueDifferentCenturies() throws InvalidPriceException, CollectionFullException {
    Furniture century2 = new Furniture("F20", 101, 100, "Pine");
    Furniture century3 = new Furniture("F30", 201, 200, "Elm");
    collection.addAntique(century2);
    collection.addAntique(century3);
    Assert.assertSame(century2, collection.getAntiqueAt(1, 0));
    Assert.assertSame(century3, collection.getAntiqueAt(2, 0));
  }

  /**
   * Adding a fourth antique to a century with only 3 slots must throw
   * {@link CollectionFullException}.
   */
  @Test(expected = CollectionFullException.class)
  public void testAddAntiqueCollectionFullThrows() throws InvalidPriceException, CollectionFullException {
    collection.addAntique(new Furniture("F01", 10, 100, "Oak"));
    collection.addAntique(new Furniture("F02", 20, 200, "Pine"));
    collection.addAntique(new Furniture("F03", 30, 300, "Elm"));
    collection.addAntique(new Furniture("F04", 40, 400, "Ash")); // must throw
  }

  /**
   * The message of {@link CollectionFullException} must mention the century number.
   */
  @Test
  public void testCollectionFullExceptionMessage() throws InvalidPriceException {
    try {
      collection.addAntique(new Furniture("F01", 10, 100, "Oak"));
      collection.addAntique(new Furniture("F02", 20, 200, "Pine"));
      collection.addAntique(new Furniture("F03", 30, 300, "Elm"));
      collection.addAntique(new Furniture("F04", 40, 400, "Ash"));
      Assert.fail("Expected CollectionFullException was not thrown");
    } catch (CollectionFullException e) {
      Assert.assertTrue(e.getMessage().contains("1")); // century 1
    }
  }

  /**
   * An antique whose year maps beyond the matrix dimensions must throw
   * {@link YearOutOfRangeException}. Our 5-row collection covers years 1 to 500;
   * year 501 maps to row 5 which is out of bounds.
   */
  @Test(expected = YearOutOfRangeException.class)
  public void testAddAntiqueYearBeyondMatrixThrows() throws InvalidPriceException, CollectionFullException {
    collection.addAntique(new Furniture("F99", 501, 999, "Iron"));
  }

  /**
   * After deletion, matching furniture is removed and remaining items are
   * compacted to the left. Vacated trailing slots must be {@code null}.
   */
  @Test
  public void testDeleteFurnitureRemovesAndCompacts() throws InvalidPriceException, CollectionFullException, FurnitureNotFoundException {
    Furniture keep = new Furniture("FK", 50, 100, "Oak");
    Furniture remove1 = new Furniture("FR1", 75, 200, "Pine");
    Furniture remove2 = new Furniture("FR2", 75, 300, "Elm");
    collection.addAntique(keep);
    collection.addAntique(remove1);
    collection.addAntique(remove2);

    collection.deleteFurniture(75);

    Assert.assertSame(keep, collection.getAntiqueAt(0, 0));
    Assert.assertNull(collection.getAntiqueAt(0, 1));
    Assert.assertNull(collection.getAntiqueAt(0, 2));
  }

  /**
   * {@link Painting} objects in the target year must NOT be deleted; only
   * {@link Furniture} is removed.
   */
  @Test
  public void testDeleteFurnitureLeavsPaintingsUntouched() throws InvalidPriceException, CollectionFullException, FurnitureNotFoundException {
    Furniture furniture = new Furniture("F1", 50, 200, "Oak");
    Painting  painting  = new Painting("P1",  75, 500, "Watercolour");
    collection.addAntique(furniture);
    collection.addAntique(painting);

    collection.deleteFurniture(50);

    // Painting (different year) must still be at slot 0 after compaction
    Assert.assertSame(painting, collection.getAntiqueAt(0, 0));
    Assert.assertNull(collection.getAntiqueAt(0, 1));
  }

  /**
   * Deleting all furniture in a century results in a fully null row.
   */
  @Test
  public void testDeleteFurnitureAllItemsResultsInEmptyRow() throws InvalidPriceException, CollectionFullException, FurnitureNotFoundException {
    collection.addAntique(new Furniture("F1", 10, 100, "Oak"));
    collection.addAntique(new Furniture("F2", 10, 200, "Pine"));

    collection.deleteFurniture(10);

    Assert.assertNull(collection.getAntiqueAt(0, 0));
    Assert.assertNull(collection.getAntiqueAt(0, 1));
  }

  /**
   * Deleting from an empty collection must throw {@link FurnitureNotFoundException}.
   */
  @Test(expected = FurnitureNotFoundException.class)
  public void testDeleteFurnitureEmptyCollectionThrows() throws FurnitureNotFoundException {
    collection.deleteFurniture(50);
  }

  /**
   * Deleting a year for which no furniture exists must throw
   * {@link FurnitureNotFoundException}, even when other antiques are present.
   */
  @Test(expected = FurnitureNotFoundException.class)
  public void testDeleteFurnitureNotFoundThrows() throws InvalidPriceException, CollectionFullException, FurnitureNotFoundException {
    collection.addAntique(new Furniture("F1", 10, 100, "Oak")); // year 10
    collection.deleteFurniture(50); // year 50 does not exist
  }

  /**
   * Deleting a year that contains only a {@link Painting} (no furniture) must throw
   * {@link FurnitureNotFoundException}.
   */
  @Test(expected = FurnitureNotFoundException.class)
  public void testDeleteFurnitureOnlyPaintingThrows() throws InvalidPriceException, CollectionFullException, FurnitureNotFoundException {
    collection.addAntique(new Painting("P1", 50, 500, "Fresco"));
    collection.deleteFurniture(50);
  }

  /**
   * The message of {@link FurnitureNotFoundException} must mention the requested year.
   */
  @Test
  public void testFurnitureNotFoundExceptionMessage() {
    try {
      collection.deleteFurniture(99);
      Assert.fail("Expected FurnitureNotFoundException was not thrown");
    } catch (FurnitureNotFoundException e) {
      Assert.assertTrue(e.getMessage().contains("99"));
    }
  }

  /**
   * Deleting furniture for a year that maps beyond the matrix dimensions must
   * throw {@link YearOutOfRangeException} (unchecked). Year 501 → row 5 in a
   * 5-row matrix.
   */
  @Test(expected = YearOutOfRangeException.class)
  public void testDeleteFurnitureYearBeyondMatrixThrows() throws FurnitureNotFoundException {
    collection.deleteFurniture(501);
  }

  /**
   * {@link Furniture#toString()} must contain the identifier, year, price, and material.
   */
  @Test
  public void testFurnitureToString() throws InvalidPriceException {
    Furniture furniture = new Furniture("DESK01", 350, 1500, "Walnut");
    String str = furniture.toString();
    Assert.assertTrue(str.contains("DESK01"));
    Assert.assertTrue(str.contains("350"));
    Assert.assertTrue(str.contains("1500"));
    Assert.assertTrue(str.contains("Walnut"));
  }

  /**
   * {@link Painting#toString()} must contain the identifier, year, price, and technique.
   */
  @Test
  public void testPaintingToString() throws InvalidPriceException {
    Painting painting = new Painting("ART01", 200, 8000, "Tempera");
    String str = painting.toString();
    Assert.assertTrue(str.contains("ART01"));
    Assert.assertTrue(str.contains("200"));
    Assert.assertTrue(str.contains("8000"));
    Assert.assertTrue(str.contains("Tempera"));
  }
}