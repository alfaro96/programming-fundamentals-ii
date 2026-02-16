import com.realestate.management.model.Apartment;
import com.realestate.management.model.Parking;
import com.realestate.management.model.Storage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for the real estate management system core entities.
 * <p>
 * This class validates the business logic, state transitions, and data integrity
 * of the {@link Apartment}, {@link Parking}, and {@link Storage} classes.
 * </p>
 * <p>
 * It specifically covers:
 * <ul>
 * <li><b>Pricing logic:</b> Interaction between {@link Apartment} and {@link Apartment.Quality} multipliers.</li>
 * <li><b>Lifecycle:</b> State transitions defined in {@link Apartment.Status}, {@link Parking.Status}, and {@link Storage.Status}.</li>
 * <li><b>Size classification:</b> Threshold logic using {@link Parking#THRESHOLD} and {@link Storage#THRESHOLD}.</li>
 * <li><b>Validation:</b> Data integrity checks (e.g., non-null DNI).</li>
 * </ul>
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * Delta value for floating-point comparisons ({@code double}).
   * Necessary to handle minor precision errors in calculations.
   */
  private static final double DELTA = 0.001;

  /**
   * Verifies the price calculation logic for {@link Apartment}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Creates a standard apartment.</li>
   * <li>Verifies the base price.</li>
   * <li>Applies {@link Apartment.Quality#PLUS} (1.05x) and {@link Apartment.Quality#DELUXE} (1.10x).</li>
   * <li>Asserts that {@link Apartment#getPrice} reflects the correct multipliers.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentPricing() {
    // 1. Setup: Create a STANDARD apartment (base price: 100,000.0)
    Apartment apt = new Apartment(100000.0, 90.0, 3);

    // 2. Test base price (no quality assigned yet)
    Assert.assertEquals("Initial price should match base price",
            100000.0, apt.getPrice(), DELTA);

    // 3. Test quality: PLUS (+5%)
    apt.setQuality(Apartment.Quality.PLUS);
    Assert.assertEquals("Plus quality should increase price by 5%",
            105000.0, apt.getPrice(), DELTA);

    // 4. Test quality: DELUXE (+10%)
    apt.setQuality(Apartment.Quality.DELUXE);
    Assert.assertEquals("Deluxe quality should increase price by 10%",
            110000.0, apt.getPrice(), DELTA);

    // 5. Verify base price attribute remains immutable during calculations
    Assert.assertEquals("Base price attribute should remain constant",
            100000.0, apt.getBasePrice(), DELTA);
  }

  /**
   * Verifies the transaction lifecycle (sell, reserve, release) for {@link Apartment}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks initial {@link Apartment.Status#FREE} state.</li>
   * <li>Performs a reservation and checks status change to {@link Apartment.Status#RESERVED}.</li>
   * <li>Releases the property and checks return to {@link Apartment.Status#FREE}.</li>
   * <li>Performs a final sale and checks status change to {@link Apartment.Status#SOLD}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentLifecycle() {
    Apartment apt = new Apartment(150000.0, 100.0, 4);

    // 1. Check initial state
    Assert.assertEquals("New apartment should be FREE",
            Apartment.Status.FREE, apt.getStatus());
    Assert.assertTrue("isAvailable() should return true", apt.isAvailable());

    // 2. Test reservation
    boolean reserveSuccess = apt.reserve("12345678A", Apartment.Quality.STANDARD);
    Assert.assertTrue("Reservation should return true with valid DNI", reserveSuccess);
    Assert.assertEquals("Status should be RESERVED",
            Apartment.Status.RESERVED, apt.getStatus());
    Assert.assertEquals("Buyer DNI should be set correctly",
            "12345678A", apt.getBuyerDni());

    // 3. Test release (reset)
    apt.release();
    Assert.assertEquals("Status should return to FREE after release",
            Apartment.Status.FREE, apt.getStatus());
    Assert.assertNull("Buyer DNI should be null after release", apt.getBuyerDni());
    Assert.assertNull("Quality should be null after release", apt.getQuality());

    // 4. Test final sale
    boolean sellSuccess = apt.sell("87654321B"); // Uses overloaded method
    Assert.assertTrue("Sale should return true", sellSuccess);
    Assert.assertEquals("Status should be SOLD",
            Apartment.Status.SOLD, apt.getStatus());
  }

  /**
   * Verifies data validation logic to ensure data integrity.
   * <p>
   * <b>Scenario:</b> Attempts to perform transactions with {@code null} or empty DNI strings
   * and verifies that the system rejects them, keeping the {@link Apartment.Status} as {@link Apartment.Status#FREE}.
   * </p>
   */
  @Test
  public void testDataValidation() {
    Apartment apt = new Apartment(100000.0, 80.0, 2);
    Parking parking = new Parking(15000.0, 12.0);

    // 1. Attempt sale with NULL DNI
    boolean successNull = apt.sell(null);
    Assert.assertFalse("Sale should fail (return false) with null DNI", successNull);
    Assert.assertEquals("Status should remain FREE",
            Apartment.Status.FREE, apt.getStatus());

    // 2. Attempt sale with EMPTY DNI
    boolean successEmpty = apt.sell("");
    Assert.assertFalse("Sale should fail with empty DNI string", successEmpty);

    // 3. Verify validation extends to Parking class
    boolean parkingSuccess = parking.sell(null);
    Assert.assertFalse("Parking sale should fail with null DNI", parkingSuccess);
  }

  /**
   * Verifies {@link Parking} specific logic, particularly the size threshold.
   * <p>
   * <b>Scenario:</b> Checks if {@link Parking#isLarge} correctly identifies
   * spaces based on {@link Parking#THRESHOLD} (12.0 m²).
   * </p>
   */
  @Test
  public void testParkingSizeLogic() {
    // 1. Test small parking (10.0 m² <= 12.0)
    Parking smallP = new Parking(10000.0, 10.0);
    Assert.assertFalse("10.0m² should NOT be considered Large", smallP.isLarge());

    // 2. Test large parking (12.5 m² > 12.0)
    Parking largeP = new Parking(18000.0, 12.5);
    Assert.assertTrue("12.5m² SHOULD be considered Large", largeP.isLarge());

    // 3. Test boundary condition (exactly 12.0 m²)
    // Logic: > 12.0 means 12.0 is NOT large.
    Parking boundaryP = new Parking(12000.0, 12.0);
    Assert.assertFalse("Exactly 12.0m² should NOT be considered Large", boundaryP.isLarge());
  }

  /**
   * Verifies {@link Storage} specific logic.
   * <p>
   * <b>Scenario:</b> Checks if {@link Storage#isLarge()} correctly identifies
   * units based on {@link Storage#THRESHOLD} (7.0 m²).
   * </p>
   */
  @Test
  public void testStorageSizeLogic() {
    // 1. Test small storage (5.0 m² <= 7.0)
    Storage smallS = new Storage(3000.0, 5.0);
    Assert.assertFalse("5.0m² should NOT be Large", smallS.isLarge());

    // 2. Test large storage (8.0 m² > 7.0)
    Storage largeS = new Storage(6000.0, 8.0);
    Assert.assertTrue("8.0m² SHOULD be Large", largeS.isLarge());

    // 3. Test filter matching logic
    Assert.assertTrue("Should match size filter 2 (Large)", largeS.matchesSize(2));
    Assert.assertFalse("Should NOT match size filter 1 (Small)", largeS.matchesSize(1));
  }
}
