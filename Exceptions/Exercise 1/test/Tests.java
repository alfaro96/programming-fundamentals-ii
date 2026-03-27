import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;
import housing.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Covers both the happy path (valid inputs produce correct objects and tax values)
 * and the error path (invalid inputs trigger the appropriate checked exceptions).
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * A negative price on an {@link Apartment} must throw {@link InvalidPriceException}.
   */
  @Test(expected = InvalidPriceException.class)
  public void testApartmentNegativePriceThrows() throws InvalidPriceException {
    new Apartment(-1, true);
  }

  /**
   * A negative price on a {@link SingleFamily} must throw {@link InvalidPriceException}.
   */
  @Test(expected = InvalidPriceException.class)
  public void testSingleFamilyNegativePriceThrows() throws InvalidPriceException, InvalidSquareMetersException {
    new SingleFamily(-500, 2, 30);
  }

  /**
   * A negative price on a {@link Chalet} must throw {@link InvalidPriceException}.
   */
  @Test(expected = InvalidPriceException.class)
  public void testChaletNegativePriceThrows()
          throws InvalidPriceException, InvalidSquareMetersException {
    new Chalet(-100, 1, 20, 50);
  }

  /**
   * A negative price on a {@link CountryHouse} must throw {@link InvalidPriceException}.
   */
  @Test(expected = InvalidPriceException.class)
  public void testCountryHouseNegativePriceThrows() throws InvalidPriceException, InvalidSquareMetersException {
    Chalet chalet = new Chalet(300_000, 2, 60, 100);
    new CountryHouse(-200, 1_000, chalet);
  }

  /**
   * A price of exactly 0 is valid and must not throw any exception.
   */
  @Test
  public void testZeroPriceIsValid() throws InvalidPriceException {
    Apartment apartment = new Apartment(0, false);
    Assert.assertEquals(0.0, apartment.getPrice(), 0.001);
  }

  /**
   * A negative basement size on a {@link SingleFamily} must throw
   * {@link InvalidSquareMetersException}.
   */
  @Test(expected = InvalidSquareMetersException.class)
  public void testSingleFamilyNegativeBasementThrows() throws InvalidPriceException, InvalidSquareMetersException {
    new SingleFamily(200_000, 2, -1);
  }

  /**
   * A negative garden size on a {@link Chalet} must throw
   * {@link InvalidSquareMetersException}.
   */
  @Test(expected = InvalidSquareMetersException.class)
  public void testChaletNegativeGardenThrows() throws InvalidPriceException, InvalidSquareMetersException {
    new Chalet(300_000, 2, 50, -10);
  }

  /**
   * A negative basement size on a {@link Chalet} (inherited validation) must throw
   * {@link InvalidSquareMetersException}.
   */
  @Test(expected = InvalidSquareMetersException.class)
  public void testChaletNegativeBasementThrows() throws InvalidPriceException, InvalidSquareMetersException {
    new Chalet(300_000, 2, -5, 100);
  }

  /**
   * A negative plot size on a {@link CountryHouse} must throw
   * {@link InvalidSquareMetersException}.
   */
  @Test(expected = InvalidSquareMetersException.class)
  public void testCountryHouseNegativePlotThrows() throws InvalidPriceException, InvalidSquareMetersException {
    Chalet chalet = new Chalet(300_000, 2, 60, 150);
    new CountryHouse(500_000, -50, chalet);
  }

  /**
   * Square meters of exactly 0 are valid and must not throw any exception.
   */
  @Test
  public void testZeroSquareMetersIsValid() throws InvalidPriceException, InvalidSquareMetersException {
    SingleFamily singleFamily = new SingleFamily(200_000, 1, 0);
    Assert.assertEquals(200_000.0, singleFamily.getPrice(), 0.001);
  }

  /**
   * A valid {@link Apartment} stores the price correctly.
   */
  @Test
  public void testApartmentGetPrice() throws InvalidPriceException {
    Apartment apartment = new Apartment(150_000, true);
    Assert.assertEquals(150_000.0, apartment.getPrice(), 0.001);
  }

  /**
   * {@link Apartment#tax()} must equal price × 1.5.
   */
  @Test
  public void testApartmentTax() throws InvalidPriceException {
    Apartment apartment = new Apartment(100_000, false);
    Assert.assertEquals(150_000.0, apartment.tax(), 0.001);
  }

  /**
   * A valid {@link SingleFamily} stores the price correctly.
   */
  @Test
  public void testSingleFamilyGetPrice() throws InvalidPriceException, InvalidSquareMetersException {
    SingleFamily sf = new SingleFamily(200_000, 2, 40);
    Assert.assertEquals(200_000.0, sf.getPrice(), 0.001);
  }

  /**
   * {@link SingleFamily#tax()} must equal price × 0.20.
   */
  @Test
  public void testSingleFamilyTax()
          throws InvalidPriceException, InvalidSquareMetersException {
    SingleFamily sf = new SingleFamily(200_000, 2, 40);
    Assert.assertEquals(40_000.0, sf.tax(), 0.001);
  }

  /**
   * A valid {@link Chalet} stores the price correctly.
   */
  @Test
  public void testChaletGetPrice() throws InvalidPriceException, InvalidSquareMetersException {
    Chalet chalet = new Chalet(400_000, 3, 80, 200);
    Assert.assertEquals(400_000.0, chalet.getPrice(), 0.001);
  }

  /**
   * {@link Chalet#tax()} inherits the {@link SingleFamily} formula: price × 0.20.
   */
  @Test
  public void testChaletTax() throws InvalidPriceException, InvalidSquareMetersException {
    Chalet chalet = new Chalet(400_000, 3, 80, 200);
    Assert.assertEquals(80_000.0, chalet.tax(), 0.001);
  }

  /**
   * {@link CountryHouse#tax()} must equal chalet tax + (plotSize × 0.5).
   */
  @Test
  public void testCountryHouseTax() throws InvalidPriceException, InvalidSquareMetersException {
    Chalet chalet = new Chalet(300_000, 2, 60, 150);
    CountryHouse countryHouse = new CountryHouse(600_000, 2_000, chalet);
    Assert.assertEquals(61_000.0, countryHouse.tax(), 0.001);
  }

  /**
   * A valid {@link CountryHouse} stores its own price, not the chalet's.
   */
  @Test
  public void testCountryHouseGetPrice() throws InvalidPriceException, InvalidSquareMetersException {
    Chalet chalet = new Chalet(300_000, 2, 60, 150);
    CountryHouse countryHouse = new CountryHouse(600_000, 1_000, chalet);
    Assert.assertEquals(600_000.0, countryHouse.getPrice(), 0.001);
  }

  /**
   * {@link SingleFamily#compareTo} returns a negative value when {@code this}
   * has a lower tax than {@code other}.
   */
  @Test
  public void testCompareToLowerTax() throws InvalidPriceException, InvalidSquareMetersException {
    SingleFamily cheaper = new SingleFamily(100_000, 1, 20);
    SingleFamily pricier = new SingleFamily(200_000, 2, 40);
    Assert.assertTrue(cheaper.compareTo(pricier) < 0);
  }

  /**
   * {@link SingleFamily#compareTo} returns a positive value when {@code this}
   * has a higher tax than {@code other}.
   */
  @Test
  public void testCompareToHigherTax() throws InvalidPriceException, InvalidSquareMetersException {
    SingleFamily pricier = new SingleFamily(200_000, 2, 40);
    SingleFamily cheaper = new SingleFamily(100_000, 1, 20);
    Assert.assertTrue(pricier.compareTo(cheaper) > 0);
  }

  /**
   * When taxes are equal, {@link SingleFamily#compareTo} breaks the tie
   * alphabetically by class name ("Chalet" &lt; "SingleFamily").
   */
  @Test
  public void testCompareToTieBreakerByClassName() throws InvalidPriceException, InvalidSquareMetersException {
    SingleFamily sf = new SingleFamily(200_000, 2, 40);
    Chalet chalet = new Chalet(200_000, 2, 40, 100);

    Assert.assertTrue(chalet.compareTo(sf) < 0);
    Assert.assertTrue(sf.compareTo(chalet) > 0);
  }

  /**
   * {@link RealEstate#countNonChaletSingleFamily()} must count only pure
   * {@link SingleFamily} instances, excluding {@link Chalet} objects.
   */
  @Test
  public void testCountNonChaletSingleFamily() throws InvalidPriceException, InvalidSquareMetersException {
    RealEstate realEstate = new RealEstate(5);
    realEstate.addHousing(new SingleFamily(150_000, 2, 30));
    realEstate.addHousing(new SingleFamily(200_000, 3, 50));
    realEstate.addHousing(new Chalet(300_000, 2, 60, 100));
    realEstate.addHousing(new Apartment(100_000, true));

    Assert.assertEquals(2, realEstate.countNonChaletSingleFamily());
  }

  /**
   * An empty catalogue must return 0 for {@link RealEstate#countNonChaletSingleFamily()}.
   */
  @Test
  public void testCountNonChaletSingleFamilyEmpty() {
    RealEstate realEstate = new RealEstate(5);
    Assert.assertEquals(0, realEstate.countNonChaletSingleFamily());
  }

  /**
   * {@link RealEstate#getHighestTaxHousing()} must return the property with the
   * greatest tax, regardless of its runtime type.
   */
  @Test
  public void testGetHighestTaxHousing() throws InvalidPriceException, InvalidSquareMetersException {
    RealEstate realEstate = new RealEstate(5);

    Apartment apartment  = new Apartment(100_000, false);
    SingleFamily sf = new SingleFamily(200_000, 2, 30);

    realEstate.addHousing(sf);
    realEstate.addHousing(apartment);

    Assert.assertSame(apartment, realEstate.getHighestTaxHousing());
  }

  /**
   * An empty catalogue must return {@code null}.
   */
  @Test
  public void testGetHighestTaxHousingEmpty() {
    RealEstate realEstate = new RealEstate(5);
    Assert.assertNull(realEstate.getHighestTaxHousing());
  }

  /**
   * A catalogue with a single element must return that element.
   */
  @Test
  public void testGetHighestTaxHousingSingleElement() throws InvalidPriceException {
    RealEstate realEstate = new RealEstate(5);
    Apartment apartment = new Apartment(200_000, true);
    realEstate.addHousing(apartment);
    Assert.assertSame(apartment, realEstate.getHighestTaxHousing());
  }

  /**
   * The message of {@link InvalidPriceException} must mention the offending value.
   */
  @Test
  public void testInvalidPriceExceptionMessage() {
    try {
      new Apartment(-250, true);
      Assert.fail("Expected InvalidPriceException was not thrown");
    } catch (InvalidPriceException e) {
      Assert.assertTrue(e.getMessage().contains("-250"));
    }
  }

  /**
   * The message of {@link InvalidSquareMetersException} must mention the field name
   * and the offending value.
   */
  @Test
  public void testInvalidSquareMetersExceptionMessage() {
    try {
      new SingleFamily(100_000, 1, -99);
      Assert.fail("Expected InvalidSquareMetersException was not thrown");
    } catch (InvalidPriceException e) {
      Assert.fail("Wrong exception type thrown: " + e.getClass().getSimpleName());
    } catch (InvalidSquareMetersException e) {
      Assert.assertTrue(e.getMessage().contains("basementSize"));
      Assert.assertTrue(e.getMessage().contains("-99"));
    }
  }
}