import org.junit.Test;
import static org.junit.Assert.*;

import housing.Apartment;
import housing.Chalet;
import housing.CountryHouse;
import housing.RealEstate;
import housing.SingleFamily;
import housing.Housing;

/**
 * Test suite to validate the housing hierarchy, tax calculations, and array logic.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /** Delta value for floating-point comparisons to prevent precision errors. */
  private static final double DELTA = 1e-9;

  /**
   * Verifies that the tax for a {@link SingleFamily} home is correctly calculated as 20% of the price.
   */
  @Test
  public void testSingleFamilyTax() {
    SingleFamily sf = new SingleFamily(100000.0, 2, 50.0);
    // 100000 * 0.20 = 20000.0
    assertEquals("SingleFamily tax should be 20% of price", 20000.0, sf.tax(), DELTA);
  }

  /**
   * Verifies that the tax for an {@link Apartment} is correctly calculated as price * 1.5.
   */
  @Test
  public void testApartmentTax() {
    Apartment apt = new Apartment(100000.0, true);
    // 100000 * 1.5 = 150000.0
    assertEquals("Apartment tax should be price * 1.5", 150000.0, apt.tax(), DELTA);
  }

  /**
   * Verifies that the tax for a {@link CountryHouse} includes the internal {@link Chalet} tax plus the plot tax.
   */
  @Test
  public void testCountryHouseTax() {
    Chalet c = new Chalet(100000.0, 2, 50.0, 100.0);
    // Chalet tax = 20000.0
    CountryHouse ch = new CountryHouse(200000.0, 1000.0, c);
    // CountryHouse tax = Chalet tax (20000.0) + Plot tax (1000 * 0.5 = 500) = 20500.0
    assertEquals("CountryHouse tax should sum Chalet tax and plot tax", 20500.0, ch.tax(), DELTA);
  }

  /**
   * Verifies the {@link Comparable} implementation inside SingleFamily.
   */
  @Test
  public void testSingleFamilyCompareTo() {
    SingleFamily cheapHome = new SingleFamily(100000.0, 1, 20.0); // Tax: 20000
    SingleFamily expensiveHome = new SingleFamily(200000.0, 2, 40.0); // Tax: 40000

    assertTrue("cheapHome should be less than expensiveHome based on tax",
            cheapHome.compareTo(expensiveHome) < 0);

    Chalet chaletTie = new Chalet(100000.0, 2, 50.0, 100.0); // Tax: 20000
    // Same tax, but "Chalet" comes before "SingleFamily" alphabetically
    assertTrue("Chalet should come before SingleFamily if taxes are equal",
            chaletTie.compareTo(cheapHome) < 0);
  }

  /**
   * Verifies that {@link RealEstate} correctly identifies {@link SingleFamily} objects while excluding {@link Chalet}.
   */
  @Test
  public void testCountNonChaletSingleFamily() {
    RealEstate agency = new RealEstate(5);
    agency.addHousing(new SingleFamily(100000.0, 1, 20.0)); // Should count (+1)
    agency.addHousing(new Chalet(150000.0, 2, 30.0, 100.0)); // Should NOT count
    agency.addHousing(new SingleFamily(120000.0, 2, 40.0)); // Should count (+1)
    agency.addHousing(new Apartment(90000.0, false)); // Should NOT count

    assertEquals("Should count exactly 2 SingleFamily homes that are not Chalets",
            2, agency.countNonChaletSingleFamily());
  }

  /**
   * Verifies that RealEstate correctly calculates the housing with the maximum tax.
   */
  @Test
  public void testHighestTaxHousing() {
    RealEstate agency = new RealEstate(3);
    Apartment apt = new Apartment(100000.0, false); // Tax: 150000.0
    SingleFamily sf = new SingleFamily(100000.0, 1, 20.0); // Tax: 20000.0

    agency.addHousing(sf);
    agency.addHousing(apt);

    Housing max = agency.getHighestTaxHousing();
    assertEquals("Apartment should have the highest tax", apt, max);
  }
}
