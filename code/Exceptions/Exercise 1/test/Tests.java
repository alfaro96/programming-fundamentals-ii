import org.junit.Test;
import static org.junit.Assert.*;

import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;
import housing.Apartment;
import housing.Chalet;
import housing.CountryHouse;
import housing.RealEstate;
import housing.SingleFamily;

/**
 * Test suite to validate the housing hierarchy, its tax calculations and the exceptions
 * thrown by its constructors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /** Delta value for floating-point comparisons to prevent precision errors. */
  private static final double DELTA = 1e-9;

  /**
   * Verifies that the taxes are still calculated as in Unit 3 when the values are valid.
   *
   * @throws Exception if a constructor rejects a valid value.
   */
  @Test
  public void testTaxesWithValidValues() throws Exception {
    Chalet chalet = new Chalet(100000.0, 2, 50.0, 100.0);
    assertEquals("SingleFamily tax should be 20% of price", 20000.0,
        new SingleFamily(100000.0, 2, 50.0).tax(), DELTA);
    assertEquals("Apartment tax should be price * 1.5", 150000.0,
        new Apartment(100000.0, true).tax(), DELTA);
    assertEquals("CountryHouse tax should sum Chalet tax and plot tax", 20500.0,
        new CountryHouse(200000.0, 1000.0, chalet).tax(), DELTA);
  }

  /**
   * Verifies that an {@link Apartment} with a negative price throws an
   * {@link InvalidPriceException} whose message includes the price.
   */
  @Test
  public void testApartmentNegativePrice() {
    try {
      new Apartment(-250.0, true);
      fail("A negative price should throw an InvalidPriceException");
    } catch (InvalidPriceException e) {
      assertTrue("The message should include the price", e.getMessage().contains("-250"));
    }
  }

  /**
   * Verifies that {@link SingleFamily}, {@link Chalet} and {@link CountryHouse} reject a
   * negative price, through the constructor of {@code Housing}.
   *
   * @throws Exception if a constructor throws a different exception.
   */
  @Test
  public void testSubclassesNegativePrice() throws Exception {
    Chalet chalet = new Chalet(100000.0, 2, 50.0, 100.0);
    try {
      new SingleFamily(-1.0, 1, 20.0);
      fail("SingleFamily should reject a negative price");
    } catch (InvalidPriceException e) {
      // expected
    }
    try {
      new Chalet(-1.0, 1, 20.0, 30.0);
      fail("Chalet should reject a negative price");
    } catch (InvalidPriceException e) {
      // expected
    }
    try {
      new CountryHouse(-1.0, 1000.0, chalet);
      fail("CountryHouse should reject a negative price");
    } catch (InvalidPriceException e) {
      // expected
    }
  }

  /**
   * Verifies that a negative basement throws an {@link InvalidSquareMetersException} whose
   * message names the attribute and includes its value.
   *
   * @throws Exception if the constructor throws a different exception.
   */
  @Test
  public void testNegativeBasement() throws Exception {
    try {
      new SingleFamily(100000.0, 1, -99.0);
      fail("A negative basement should throw an InvalidSquareMetersException");
    } catch (InvalidSquareMetersException e) {
      assertTrue("The message should name the attribute", e.getMessage().contains("basementSize"));
      assertTrue("The message should include the value", e.getMessage().contains("-99"));
    }
  }

  /**
   * Verifies that a negative basement or garden in a {@link Chalet}, and a negative plot in a
   * {@link CountryHouse}, throw an {@link InvalidSquareMetersException}.
   *
   * @throws Exception if a constructor throws a different exception.
   */
  @Test
  public void testNegativeSizes() throws Exception {
    Chalet chalet = new Chalet(100000.0, 2, 50.0, 100.0);
    try {
      new Chalet(100000.0, 1, -5.0, 30.0);
      fail("Chalet should reject a negative basement");
    } catch (InvalidSquareMetersException e) {
      // expected
    }
    try {
      new Chalet(100000.0, 1, 20.0, -10.0);
      fail("Chalet should reject a negative garden");
    } catch (InvalidSquareMetersException e) {
      assertTrue("The message should name the attribute", e.getMessage().contains("gardenSize"));
    }
    try {
      new CountryHouse(200000.0, -50.0, chalet);
      fail("CountryHouse should reject a negative plot");
    } catch (InvalidSquareMetersException e) {
      assertTrue("The message should name the attribute", e.getMessage().contains("plotSize"));
    }
  }

  /**
   * Verifies that a price and sizes of 0 are valid.
   *
   * @throws Exception if a constructor rejects a value of 0.
   */
  @Test
  public void testZeroIsValid() throws Exception {
    Chalet chalet = new Chalet(0.0, 1, 0.0, 0.0);
    assertEquals("A price of 0 should be kept", 0.0, chalet.getPrice(), DELTA);
    assertEquals("A plot of 0 should add nothing to the tax", 0.0,
        new CountryHouse(0.0, 0.0, chalet).tax(), DELTA);
  }

  /**
   * Verifies {@link SingleFamily#paysMoreTaxThan} and the methods of {@link RealEstate}.
   *
   * @throws Exception if a constructor rejects a valid value.
   */
  @Test
  public void testRealEstate() throws Exception {
    SingleFamily cheapHome = new SingleFamily(100000.0, 1, 20.0);
    SingleFamily expensiveHome = new SingleFamily(200000.0, 2, 40.0);
    Apartment apartment = new Apartment(100000.0, false);
    assertTrue("The expensive home should pay more tax", expensiveHome.paysMoreTaxThan(cheapHome));
    assertFalse("The cheap home should not pay more tax", cheapHome.paysMoreTaxThan(expensiveHome));

    RealEstate agency = new RealEstate(4);
    agency.addHousing(cheapHome);
    agency.addHousing(new Chalet(300000.0, 2, 60.0, 100.0));
    agency.addHousing(expensiveHome);
    agency.addHousing(apartment);
    assertEquals("Only the homes that are not chalets should be counted", 2,
        agency.countNonChaletSingleFamily());
    assertSame("The apartment should have the highest tax", apartment, agency.getHighestTaxHousing());
  }
}
