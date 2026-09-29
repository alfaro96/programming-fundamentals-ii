import com.realestate.management.model.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive test suite for the real estate management system.
 * <p>
 * This class validates the business logic, state transitions, and data integrity
 * of all entities: {@link Apartment}, {@link Parking}, {@link Storage},
 * {@link Building}, and {@link Developer}.
 * </p>
 * <p>
 * It specifically covers:
 * <ul>
 * <li><b>Apartment:</b> Constructor, setters, {@link Apartment.Quality} pricing multipliers,
 * sell/reserve/release lifecycle, availability, match helpers, and output methods.</li>
 * <li><b>Parking:</b> Constructor, setters, size threshold logic, sell/release lifecycle,
 * availability, match helpers, and output methods.</li>
 * <li><b>Storage:</b> Constructor, setters, size threshold logic, sell/release lifecycle,
 * availability, match helpers, and output methods.</li>
 * <li><b>Building:</b> Initialization, index bounds, apartment/parking/storage counters,
 * income calculations, DNI queries, and apartment/storage merge logic.</li>
 * <li><b>Developer:</b> Portfolio management, capacity resizing, sell/reserve delegation,
 * and cross-building DNI aggregation.</li>
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
   * Verifies the initial state of a newly created {@link Apartment}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Creates an apartment with specific values.</li>
   * <li>Checks that status is {@link Apartment.Status#FREE}, quality and DNI are {@code null},
   * and all numeric attributes match the constructor arguments.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentConstructorInitialState() {
    // 1. Create apartment
    Apartment apt = new Apartment(200000.0, 75.5, 3);

    // 2. Verify initial state
    Assert.assertEquals("Initial status should be FREE",
            Apartment.Status.FREE, apt.getStatus());
    Assert.assertEquals("Base price should match constructor argument",
            200000.0, apt.getBasePrice(), DELTA);
    Assert.assertEquals("Square meters should match constructor argument",
            75.5, apt.getSquareMeters(), DELTA);
    Assert.assertEquals("Rooms should match constructor argument",
            3, apt.getRooms());
    Assert.assertNull("Buyer DNI should be null initially", apt.getBuyerDni());
    Assert.assertNull("Quality should be null initially", apt.getQuality());
  }

  /**
   * Verifies that all setters of {@link Apartment} correctly update each field.
   * <p>
   * <b>Scenario:</b> Sets each field individually and asserts the updated value is returned
   * by its corresponding getter.
   * </p>
   */
  @Test
  public void testApartmentSetters() {
    Apartment apt = new Apartment(100000.0, 60.0, 2);

    apt.setBasePrice(250000.0);
    apt.setSquareMeters(120.0);
    apt.setRooms(5);
    apt.setBuyerDni("99999999Z");
    apt.setStatus(Apartment.Status.SOLD);
    apt.setQuality(Apartment.Quality.PLUS);

    Assert.assertEquals("setBasePrice should update the base price",
            250000.0, apt.getBasePrice(), DELTA);
    Assert.assertEquals("setSquareMeters should update the surface area",
            120.0, apt.getSquareMeters(), DELTA);
    Assert.assertEquals("setRooms should update the room count",
            5, apt.getRooms());
    Assert.assertEquals("setBuyerDni should update the DNI",
            "99999999Z", apt.getBuyerDni());
    Assert.assertEquals("setStatus should update the status",
            Apartment.Status.SOLD, apt.getStatus());
    Assert.assertEquals("setQuality should update the quality tier",
            Apartment.Quality.PLUS, apt.getQuality());
  }

  /**
   * Verifies the multiplier and label values of each {@link Apartment.Quality} tier.
   * <p>
   * <b>Scenario:</b> Checks that STANDARD has a 1.0x multiplier, PLUS has 1.05x,
   * and DELUXE has 1.10x, along with their corresponding display labels.
   * </p>
   */
  @Test
  public void testQualityEnumValues() {
    // 1. STANDARD
    Assert.assertEquals("STANDARD multiplier should be 1.0",
            1.0, Apartment.Quality.STANDARD.getMultiplier(), DELTA);
    Assert.assertEquals("STANDARD label should be 'Standard'",
            "Standard", Apartment.Quality.STANDARD.getLabel());

    // 2. PLUS
    Assert.assertEquals("PLUS multiplier should be 1.05",
            1.05, Apartment.Quality.PLUS.getMultiplier(), DELTA);
    Assert.assertEquals("PLUS label should be 'Plus'",
            "Plus", Apartment.Quality.PLUS.getLabel());

    // 3. DELUXE
    Assert.assertEquals("DELUXE multiplier should be 1.10",
            1.10, Apartment.Quality.DELUXE.getMultiplier(), DELTA);
    Assert.assertEquals("DELUXE label should be 'Deluxe'",
            "Deluxe", Apartment.Quality.DELUXE.getLabel());
  }

  /**
   * Verifies the price calculation logic for {@link Apartment}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Creates an apartment with no quality assigned and verifies the base price is returned.</li>
   * <li>Assigns {@link Apartment.Quality#STANDARD} and verifies no change (multiplier 1.0).</li>
   * <li>Applies {@link Apartment.Quality#PLUS} (1.05x) and verifies the increase.</li>
   * <li>Applies {@link Apartment.Quality#DELUXE} (1.10x) and verifies the increase.</li>
   * <li>Confirms {@link Apartment#getBasePrice()} remains unchanged throughout.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentPricing() {
    // 1. No quality assigned — price equals base price
    Apartment apt = new Apartment(100000.0, 90.0, 3);
    Assert.assertEquals("Without quality, price should match base price",
            100000.0, apt.getPrice(), DELTA);

    // 2. STANDARD quality — multiplier 1.0, no change
    apt.setQuality(Apartment.Quality.STANDARD);
    Assert.assertEquals("STANDARD quality should not change the price",
            100000.0, apt.getPrice(), DELTA);

    // 3. PLUS quality — multiplier 1.05
    apt.setQuality(Apartment.Quality.PLUS);
    Assert.assertEquals("PLUS quality should increase price by 5%",
            105000.0, apt.getPrice(), DELTA);

    // 4. DELUXE quality — multiplier 1.10
    apt.setQuality(Apartment.Quality.DELUXE);
    Assert.assertEquals("DELUXE quality should increase price by 10%",
            110000.0, apt.getPrice(), DELTA);

    // 5. Base price must remain unmodified
    Assert.assertEquals("Base price attribute should remain constant",
            100000.0, apt.getBasePrice(), DELTA);
  }

  /**
   * Verifies the full transaction lifecycle of {@link Apartment}: sell, reserve, and release.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks the initial {@link Apartment.Status#FREE} state and {@link Apartment#isAvailable()}.</li>
   * <li>Reserves the apartment and verifies status, DNI, and quality are stored correctly.</li>
   * <li>Releases the apartment and verifies the reset to {@link Apartment.Status#FREE}
   * with {@code null} DNI and quality.</li>
   * <li>Sells the apartment using the overloaded method and verifies the final state.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentLifecycle() {
    Apartment apt = new Apartment(150000.0, 100.0, 4);

    // 1. Initial state
    Assert.assertEquals("New apartment should be FREE",
            Apartment.Status.FREE, apt.getStatus());
    Assert.assertTrue("isAvailable() should return true for a FREE apartment",
            apt.isAvailable());

    // 2. Reserve
    boolean reserveSuccess = apt.reserve("12345678A", Apartment.Quality.PLUS);
    Assert.assertTrue("Reservation should succeed with a valid DNI", reserveSuccess);
    Assert.assertEquals("Status should be RESERVED after reservation",
            Apartment.Status.RESERVED, apt.getStatus());
    Assert.assertEquals("Buyer DNI should be stored after reservation",
            "12345678A", apt.getBuyerDni());
    Assert.assertEquals("Quality should be stored after reservation",
            Apartment.Quality.PLUS, apt.getQuality());
    Assert.assertFalse("isAvailable() should return false when RESERVED",
            apt.isAvailable());

    // 3. Release
    apt.release();
    Assert.assertEquals("Status should return to FREE after release",
            Apartment.Status.FREE, apt.getStatus());
    Assert.assertNull("Buyer DNI should be null after release", apt.getBuyerDni());
    Assert.assertNull("Quality should be null after release", apt.getQuality());
    Assert.assertTrue("isAvailable() should return true after release", apt.isAvailable());

    // 4. Sell using the overloaded (no quality) method
    boolean sellSuccess = apt.sell("87654321B");
    Assert.assertTrue("Sale should succeed with a valid DNI", sellSuccess);
    Assert.assertEquals("Status should be SOLD after sale",
            Apartment.Status.SOLD, apt.getStatus());
    Assert.assertEquals("Quality defaults to STANDARD with the overloaded sell method",
            Apartment.Quality.STANDARD, apt.getQuality());
    Assert.assertFalse("isAvailable() should return false when SOLD", apt.isAvailable());
  }

  /**
   * Verifies the sell validation logic of {@link Apartment}.
   * <p>
   * <b>Scenario:</b> Attempts to sell with {@code null}, empty, and whitespace-only DNI strings
   * and verifies that the status remains {@link Apartment.Status#FREE} in all cases.
   * </p>
   */
  @Test
  public void testApartmentSellValidation() {
    Apartment apt = new Apartment(100000.0, 80.0, 2);

    // 1. NULL DNI
    Assert.assertFalse("sell() should fail with null DNI", apt.sell(null));
    Assert.assertEquals("Status should remain FREE after failed sell with null DNI",
            Apartment.Status.FREE, apt.getStatus());

    // 2. Empty DNI
    Assert.assertFalse("sell() should fail with empty DNI", apt.sell(""));
    Assert.assertEquals("Status should remain FREE after failed sell with empty DNI",
            Apartment.Status.FREE, apt.getStatus());

    // 3. Whitespace-only DNI
    Assert.assertFalse("sell() should fail with whitespace-only DNI", apt.sell("   "));
    Assert.assertEquals("Status should remain FREE after failed sell with whitespace DNI",
            Apartment.Status.FREE, apt.getStatus());
  }

  /**
   * Verifies the reserve validation logic of {@link Apartment}.
   * <p>
   * <b>Scenario:</b> Attempts to reserve with {@code null}, empty, and whitespace-only DNI
   * strings and verifies that the status remains {@link Apartment.Status#FREE}.
   * </p>
   */
  @Test
  public void testApartmentReserveValidation() {
    Apartment apt = new Apartment(100000.0, 80.0, 2);

    // 1. NULL DNI
    Assert.assertFalse("reserve() should fail with null DNI", apt.reserve(null));
    Assert.assertEquals("Status should remain FREE after failed reserve with null DNI",
            Apartment.Status.FREE, apt.getStatus());

    // 2. Empty DNI
    Assert.assertFalse("reserve() should fail with empty DNI", apt.reserve(""));

    // 3. Whitespace-only DNI
    Assert.assertFalse("reserve() should fail with whitespace-only DNI", apt.reserve("   "));
  }

  /**
   * Verifies that {@link Apartment#release()} correctly resets the price returned
   * by {@link Apartment#getPrice()} back to the base price.
   * <p>
   * <b>Scenario:</b> Sells the apartment with {@link Apartment.Quality#DELUXE}, then releases it
   * and checks that the price no longer includes the quality multiplier.
   * </p>
   */
  @Test
  public void testApartmentReleaseRestoresPrice() {
    Apartment apt = new Apartment(100000.0, 60.0, 2);

    // 1. Sell with DELUXE quality
    apt.sell("12345678A", Apartment.Quality.DELUXE);
    Assert.assertEquals("Price should include DELUXE multiplier before release",
            110000.0, apt.getPrice(), DELTA);

    // 2. Release and verify price resets
    apt.release();
    Assert.assertEquals("Price should return to base price after release",
            100000.0, apt.getPrice(), DELTA);
  }

  /**
   * Verifies the surface, price, and room match helpers of {@link Apartment}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Tests {@link Apartment#matchesSurface(double, double)} with values inside, outside,
   * and on the exact boundary of the range.</li>
   * <li>Tests {@link Apartment#matchesPrice(double, double)} without quality
   * and with {@link Apartment.Quality#PLUS} applied.</li>
   * <li>Tests {@link Apartment#matchesRooms(int, int)} with values inside and outside the range.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentMatchHelpers() {
    Apartment apt = new Apartment(100000.0, 80.0, 3);

    // 1. matchesSurface
    Assert.assertTrue("Surface 80.0 should be inside [60.0, 100.0]",
            apt.matchesSurface(60.0, 100.0));
    Assert.assertTrue("Surface 80.0 should match exact boundary [80.0, 80.0]",
            apt.matchesSurface(80.0, 80.0));
    Assert.assertFalse("Surface 80.0 should NOT be inside [90.0, 120.0]",
            apt.matchesSurface(90.0, 120.0));
    Assert.assertFalse("Surface 80.0 should NOT be inside [50.0, 79.9]",
            apt.matchesSurface(50.0, 79.9));

    // 2. matchesPrice — uses getPrice(), so quality affects result
    Assert.assertTrue("Price 100000.0 should be inside [90000.0, 110000.0]",
            apt.matchesPrice(90000.0, 110000.0));
    apt.setQuality(Apartment.Quality.PLUS); // price becomes 105000.0
    Assert.assertTrue("Price 105000.0 should be inside [100000.0, 110000.0]",
            apt.matchesPrice(100000.0, 110000.0));
    Assert.assertFalse("Price 105000.0 should NOT be inside [100000.0, 104999.0]",
            apt.matchesPrice(100000.0, 104999.0));

    // 3. matchesRooms
    Assert.assertTrue("3 rooms should be inside [1, 5]", apt.matchesRooms(1, 5));
    Assert.assertTrue("3 rooms should match exact boundary [3, 3]", apt.matchesRooms(3, 3));
    Assert.assertFalse("3 rooms should NOT be inside [4, 6]", apt.matchesRooms(4, 6));
    Assert.assertFalse("3 rooms should NOT be inside [1, 2]", apt.matchesRooms(1, 2));
  }

  /**
   * Verifies the {@link Apartment#toString()} and {@link Apartment#getDetails()} output methods.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks that {@code toString()} returns the correct single-character code
   * for each status (F, R, S).</li>
   * <li>Checks that {@code getDetails()} includes status, price, surface, and room count.</li>
   * <li>Checks that {@code getDetails()} includes quality label and buyer DNI after a sale.</li>
   * <li>Checks that quality and buyer fields are absent from {@code getDetails()} when {@code null}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testApartmentOutputMethods() {
    Apartment apt = new Apartment(100000.0, 80.0, 3);

    // 1. toString() codes per status
    Assert.assertEquals("FREE status should produce 'F'", "F", apt.toString());
    apt.reserve("12345678A");
    Assert.assertEquals("RESERVED status should produce 'R'", "R", apt.toString());
    apt.release();
    apt.sell("12345678A");
    Assert.assertEquals("SOLD status should produce 'S'", "S", apt.toString());

    // 2. getDetails() — sold apartment includes core fields
    String soldDetails = apt.getDetails();
    Assert.assertTrue("getDetails() should contain SOLD status", soldDetails.contains("SOLD"));
    Assert.assertTrue("getDetails() should contain price", soldDetails.contains("100000"));
    Assert.assertTrue("getDetails() should contain surface", soldDetails.contains("80"));
    Assert.assertTrue("getDetails() should contain room count", soldDetails.contains("3"));

    // 3. getDetails() — includes quality label and buyer after sell with quality
    apt.release();
    apt.sell("12345678A", Apartment.Quality.DELUXE);
    String qualityDetails = apt.getDetails();
    Assert.assertTrue("getDetails() should contain quality label", qualityDetails.contains("Deluxe"));
    Assert.assertTrue("getDetails() should contain buyer DNI", qualityDetails.contains("12345678A"));

    // 4. getDetails() — omits quality and buyer fields when null
    apt.release();
    String freeDetails = apt.getDetails();
    Assert.assertFalse("getDetails() should not contain 'Quality' when null",
            freeDetails.contains("Quality"));
    Assert.assertFalse("getDetails() should not contain 'Buyer' when null",
            freeDetails.contains("Buyer"));
  }

  /**
   * Verifies the initial state of a newly created {@link Parking} space.
   * <p>
   * <b>Scenario:</b> Creates a parking space and checks that status is {@link Parking.Status#FREE},
   * DNI is {@code null}, and numeric attributes match the constructor arguments.
   * </p>
   */
  @Test
  public void testParkingConstructorInitialState() {
    Parking p = new Parking(15000.0, 10.0);

    Assert.assertEquals("Initial status should be FREE",
            Parking.Status.FREE, p.getStatus());
    Assert.assertEquals("Price should match constructor argument",
            15000.0, p.getPrice(), DELTA);
    Assert.assertEquals("Square meters should match constructor argument",
            10.0, p.getSquareMeters(), DELTA);
    Assert.assertNull("Buyer DNI should be null initially", p.getBuyerDni());
  }

  /**
   * Verifies that all setters of {@link Parking} correctly update each field.
   * <p>
   * <b>Scenario:</b> Sets each field individually and asserts the updated value
   * is returned by its corresponding getter.
   * </p>
   */
  @Test
  public void testParkingSetters() {
    Parking p = new Parking(15000.0, 10.0);

    p.setPrice(20000.0);
    p.setSquareMeters(14.0);
    p.setBuyerDni("11111111A");
    p.setStatus(Parking.Status.SOLD);

    Assert.assertEquals("setPrice should update the price", 20000.0, p.getPrice(), DELTA);
    Assert.assertEquals("setSquareMeters should update the surface area",
            14.0, p.getSquareMeters(), DELTA);
    Assert.assertEquals("setBuyerDni should update the DNI", "11111111A", p.getBuyerDni());
    Assert.assertEquals("setStatus should update the status", Parking.Status.SOLD, p.getStatus());
  }

  /**
   * Verifies {@link Parking} size threshold logic using {@link Parking#THRESHOLD} (12.0 m²).
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Tests a space below the threshold (10.0 m²) — should NOT be large.</li>
   * <li>Tests a space exactly at the threshold (12.0 m²) — should NOT be large
   * because the logic uses {@code >}, not {@code >=}.</li>
   * <li>Tests a space above the threshold (12.5 m²) — SHOULD be large.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testParkingSizeThreshold() {
    Assert.assertEquals("THRESHOLD constant should be 12.0", 12.0, Parking.THRESHOLD, DELTA);

    // 1. Below threshold
    Assert.assertFalse("10.0 m² should NOT be considered Large",
            new Parking(10000.0, 10.0).isLarge());

    // 2. Exactly at threshold — uses >, so boundary is NOT large
    Assert.assertFalse("Exactly 12.0 m² should NOT be considered Large",
            new Parking(12000.0, 12.0).isLarge());

    // 3. Above threshold
    Assert.assertTrue("12.5 m² SHOULD be considered Large",
            new Parking(18000.0, 12.5).isLarge());
  }

  /**
   * Verifies the sell and release lifecycle of {@link Parking}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks initial availability.</li>
   * <li>Sells the space with a valid DNI and verifies status and DNI.</li>
   * <li>Releases the space and verifies reset to {@link Parking.Status#FREE}.</li>
   * <li>Attempts to sell with invalid DNI values and verifies rejection.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testParkingLifecycle() {
    Parking p = new Parking(15000.0, 10.0);

    // 1. Initial availability
    Assert.assertTrue("New parking space should be available", p.isAvailable());

    // 2. Sell with valid DNI
    Assert.assertTrue("sell() should succeed with a valid DNI", p.sell("12345678A"));
    Assert.assertEquals("Status should be SOLD after sale",
            Parking.Status.SOLD, p.getStatus());
    Assert.assertEquals("Buyer DNI should be stored after sale",
            "12345678A", p.getBuyerDni());
    Assert.assertFalse("isAvailable() should return false when SOLD", p.isAvailable());

    // 3. Release
    p.release();
    Assert.assertEquals("Status should return to FREE after release",
            Parking.Status.FREE, p.getStatus());
    Assert.assertNull("Buyer DNI should be null after release", p.getBuyerDni());
    Assert.assertTrue("isAvailable() should return true after release", p.isAvailable());

    // 4. Invalid DNI values
    Assert.assertFalse("sell() should fail with null DNI", p.sell(null));
    Assert.assertFalse("sell() should fail with empty DNI", p.sell(""));
    Assert.assertFalse("sell() should fail with whitespace-only DNI", p.sell("   "));
    Assert.assertEquals("Status should remain FREE after all failed sells",
            Parking.Status.FREE, p.getStatus());
  }

  /**
   * Verifies the surface, price, and size match helpers of {@link Parking}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Tests {@link Parking#matchesSurface(double, double)} with values inside, on boundary,
   * and outside the range.</li>
   * <li>Tests {@link Parking#matchesPrice(double, double)} similarly.</li>
   * <li>Tests {@link Parking#matchesSize(int)} with filters 0 (any), 1 (small), and 2 (large).</li>
   * </ol>
   * </p>
   */
  @Test
  public void testParkingMatchHelpers() {
    Parking small = new Parking(15000.0, 10.0);
    Parking large = new Parking(20000.0, 14.0);

    // 1. matchesSurface
    Assert.assertTrue("10.0 m² should be inside [8.0, 15.0]",
            small.matchesSurface(8.0, 15.0));
    Assert.assertTrue("10.0 m² should match exact boundary [10.0, 10.0]",
            small.matchesSurface(10.0, 10.0));
    Assert.assertFalse("10.0 m² should NOT be inside [11.0, 20.0]",
            small.matchesSurface(11.0, 20.0));

    // 2. matchesPrice
    Assert.assertTrue("15000.0 should be inside [10000.0, 20000.0]",
            small.matchesPrice(10000.0, 20000.0));
    Assert.assertTrue("15000.0 should match exact boundary [15000.0, 15000.0]",
            small.matchesPrice(15000.0, 15000.0));
    Assert.assertFalse("15000.0 should NOT be inside [20000.0, 30000.0]",
            small.matchesPrice(20000.0, 30000.0));

    // 3. matchesSize filters
    Assert.assertTrue("Filter 0 (any) should always return true for small", small.matchesSize(0));
    Assert.assertTrue("Filter 0 (any) should always return true for large", large.matchesSize(0));
    Assert.assertTrue("Filter 1 (small) should match small space", small.matchesSize(1));
    Assert.assertFalse("Filter 1 (small) should NOT match large space", large.matchesSize(1));
    Assert.assertFalse("Filter 2 (large) should NOT match small space", small.matchesSize(2));
    Assert.assertTrue("Filter 2 (large) should match large space", large.matchesSize(2));
  }

  /**
   * Verifies the {@link Parking#toString()} and {@link Parking#getDetails()} output methods.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks {@code toString()} returns "F" when free and "S" when sold.</li>
   * <li>Checks {@code getDetails()} contains the expected fields for a free small space.</li>
   * <li>Checks {@code getDetails()} contains "Large" and the buyer DNI after a sale.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testParkingOutputMethods() {
    Parking p = new Parking(15000.0, 10.0);

    // 1. toString() codes
    Assert.assertEquals("FREE status should produce 'F'", "F", p.toString());
    p.sell("12345678A");
    Assert.assertEquals("SOLD status should produce 'S'", "S", p.toString());

    // 2. getDetails() — free small space
    p.release();
    String freeDetails = p.getDetails();
    Assert.assertTrue("getDetails() should contain FREE status", freeDetails.contains("FREE"));
    Assert.assertTrue("getDetails() should contain the price", freeDetails.contains("15000"));
    Assert.assertTrue("getDetails() should classify as Small", freeDetails.contains("Small"));
    Assert.assertFalse("getDetails() should not contain Buyer when free",
            freeDetails.contains("Buyer"));

    // 3. getDetails() — large sold space
    Parking large = new Parking(20000.0, 14.0);
    large.sell("99887766B");
    String soldDetails = large.getDetails();
    Assert.assertTrue("getDetails() should contain SOLD status", soldDetails.contains("SOLD"));
    Assert.assertTrue("getDetails() should classify as Large", soldDetails.contains("Large"));
    Assert.assertTrue("getDetails() should contain buyer DNI", soldDetails.contains("99887766B"));
  }

  /**
   * Verifies the initial state of a newly created {@link Storage} unit.
   * <p>
   * <b>Scenario:</b> Creates a storage unit and checks that status is {@link Storage.Status#FREE},
   * DNI is {@code null}, and numeric attributes match the constructor arguments.
   * </p>
   */
  @Test
  public void testStorageConstructorInitialState() {
    Storage s = new Storage(3000.0, 5.0);

    Assert.assertEquals("Initial status should be FREE",
            Storage.Status.FREE, s.getStatus());
    Assert.assertEquals("Price should match constructor argument",
            3000.0, s.getPrice(), DELTA);
    Assert.assertEquals("Square meters should match constructor argument",
            5.0, s.getSquareMeters(), DELTA);
    Assert.assertNull("Buyer DNI should be null initially", s.getBuyerDni());
  }

  /**
   * Verifies that all setters of {@link Storage} correctly update each field.
   * <p>
   * <b>Scenario:</b> Sets each field individually and asserts the updated value
   * is returned by its corresponding getter.
   * </p>
   */
  @Test
  public void testStorageSetters() {
    Storage s = new Storage(3000.0, 5.0);

    s.setPrice(5000.0);
    s.setSquareMeters(9.0);
    s.setBuyerDni("11111111A");
    s.setStatus(Storage.Status.SOLD);

    Assert.assertEquals("setPrice should update the price", 5000.0, s.getPrice(), DELTA);
    Assert.assertEquals("setSquareMeters should update the surface area",
            9.0, s.getSquareMeters(), DELTA);
    Assert.assertEquals("setBuyerDni should update the DNI", "11111111A", s.getBuyerDni());
    Assert.assertEquals("setStatus should update the status", Storage.Status.SOLD, s.getStatus());
  }

  /**
   * Verifies {@link Storage} size threshold logic using {@link Storage#THRESHOLD} (7.0 m²).
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Tests a unit below the threshold (5.0 m²) — should NOT be large.</li>
   * <li>Tests a unit exactly at the threshold (7.0 m²) — should NOT be large
   * because the logic uses {@code >}, not {@code >=}.</li>
   * <li>Tests a unit above the threshold (8.0 m²) — SHOULD be large.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testStorageSizeThreshold() {
    Assert.assertEquals("THRESHOLD constant should be 7.0", 7.0, Storage.THRESHOLD, DELTA);

    // 1. Below threshold
    Assert.assertFalse("5.0 m² should NOT be Large", new Storage(3000.0, 5.0).isLarge());

    // 2. Exactly at threshold — uses >, so boundary is NOT large
    Assert.assertFalse("Exactly 7.0 m² should NOT be Large",
            new Storage(4000.0, 7.0).isLarge());

    // 3. Above threshold
    Assert.assertTrue("8.0 m² SHOULD be Large", new Storage(6000.0, 8.0).isLarge());
  }

  /**
   * Verifies the sell and release lifecycle of {@link Storage}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks initial availability.</li>
   * <li>Sells the unit with a valid DNI and verifies status and DNI.</li>
   * <li>Releases the unit and verifies reset to {@link Storage.Status#FREE}.</li>
   * <li>Attempts to sell with invalid DNI values and verifies rejection.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testStorageLifecycle() {
    Storage s = new Storage(3000.0, 5.0);

    // 1. Initial availability
    Assert.assertTrue("New storage unit should be available", s.isAvailable());

    // 2. Sell with valid DNI
    Assert.assertTrue("sell() should succeed with a valid DNI", s.sell("12345678A"));
    Assert.assertEquals("Status should be SOLD after sale",
            Storage.Status.SOLD, s.getStatus());
    Assert.assertEquals("Buyer DNI should be stored after sale",
            "12345678A", s.getBuyerDni());
    Assert.assertFalse("isAvailable() should return false when SOLD", s.isAvailable());

    // 3. Release
    s.release();
    Assert.assertEquals("Status should return to FREE after release",
            Storage.Status.FREE, s.getStatus());
    Assert.assertNull("Buyer DNI should be null after release", s.getBuyerDni());
    Assert.assertTrue("isAvailable() should return true after release", s.isAvailable());

    // 4. Invalid DNI values
    Assert.assertFalse("sell() should fail with null DNI", s.sell(null));
    Assert.assertFalse("sell() should fail with empty DNI", s.sell(""));
    Assert.assertFalse("sell() should fail with whitespace-only DNI", s.sell("   "));
    Assert.assertEquals("Status should remain FREE after all failed sells",
            Storage.Status.FREE, s.getStatus());
  }

  /**
   * Verifies the surface, price, and size match helpers of {@link Storage}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Tests {@link Storage#matchesSurface(double, double)} with values inside, on boundary,
   * and outside the range.</li>
   * <li>Tests {@link Storage#matchesPrice(double, double)} similarly.</li>
   * <li>Tests {@link Storage#matchesSize(int)} with filters 0 (any), 1 (small), and 2 (large).</li>
   * </ol>
   * </p>
   */
  @Test
  public void testStorageMatchHelpers() {
    Storage small = new Storage(3000.0, 5.0);
    Storage large = new Storage(6000.0, 9.0);

    // 1. matchesSurface
    Assert.assertTrue("5.0 m² should be inside [3.0, 8.0]",
            small.matchesSurface(3.0, 8.0));
    Assert.assertTrue("5.0 m² should match exact boundary [5.0, 5.0]",
            small.matchesSurface(5.0, 5.0));
    Assert.assertFalse("5.0 m² should NOT be inside [6.0, 10.0]",
            small.matchesSurface(6.0, 10.0));

    // 2. matchesPrice
    Assert.assertTrue("3000.0 should be inside [2000.0, 5000.0]",
            small.matchesPrice(2000.0, 5000.0));
    Assert.assertTrue("3000.0 should match exact boundary [3000.0, 3000.0]",
            small.matchesPrice(3000.0, 3000.0));
    Assert.assertFalse("3000.0 should NOT be inside [5000.0, 10000.0]",
            small.matchesPrice(5000.0, 10000.0));

    // 3. matchesSize filters
    Assert.assertTrue("Filter 0 (any) should always return true for small", small.matchesSize(0));
    Assert.assertTrue("Filter 0 (any) should always return true for large", large.matchesSize(0));
    Assert.assertTrue("Filter 1 (small) should match small unit", small.matchesSize(1));
    Assert.assertFalse("Filter 1 (small) should NOT match large unit", large.matchesSize(1));
    Assert.assertFalse("Filter 2 (large) should NOT match small unit", small.matchesSize(2));
    Assert.assertTrue("Filter 2 (large) should match large unit", large.matchesSize(2));
  }

  /**
   * Verifies the {@link Storage#toString()} and {@link Storage#getDetails()} output methods.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks {@code toString()} returns "F" when free and "S" when sold.</li>
   * <li>Checks {@code getDetails()} contains the expected fields for a free small unit.</li>
   * <li>Checks {@code getDetails()} contains "Large" and the buyer DNI after a sale.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testStorageOutputMethods() {
    Storage s = new Storage(3000.0, 5.0);

    // 1. toString() codes
    Assert.assertEquals("FREE status should produce 'F'", "F", s.toString());
    s.sell("12345678A");
    Assert.assertEquals("SOLD status should produce 'S'", "S", s.toString());

    // 2. getDetails() — free small unit
    s.release();
    String freeDetails = s.getDetails();
    Assert.assertTrue("getDetails() should contain FREE status", freeDetails.contains("FREE"));
    Assert.assertTrue("getDetails() should contain the price", freeDetails.contains("3000"));
    Assert.assertTrue("getDetails() should classify as Small", freeDetails.contains("Small"));
    Assert.assertFalse("getDetails() should not contain Buyer when free",
            freeDetails.contains("Buyer"));

    // 3. getDetails() — large sold unit
    Storage large = new Storage(6000.0, 9.0);
    large.sell("99887766B");
    String soldDetails = large.getDetails();
    Assert.assertTrue("getDetails() should contain SOLD status", soldDetails.contains("SOLD"));
    Assert.assertTrue("getDetails() should classify as Large", soldDetails.contains("Large"));
    Assert.assertTrue("getDetails() should contain buyer DNI", soldDetails.contains("99887766B"));
  }

  /**
   * Verifies the initialization and dimension getters of {@link Building}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Creates a building with specific dimensions.</li>
   * <li>Verifies all getters return the expected values.</li>
   * <li>Verifies {@link Building#GARAGE_FLOORS} is always 2.</li>
   * <li>Checks that random generation populated all slots (not {@code null}).</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingInitialization() {
    // 1. Create building
    Building b = new Building("Test Tower", 3, 4, 5, 6);

    // 2. Verify dimension getters
    Assert.assertEquals("getName() should return the building name",
            "Test Tower", b.getName());
    Assert.assertEquals("getNumFloors() should return 3", 3, b.getNumFloors());
    Assert.assertEquals("getApartmentsPerFloor() should return 4", 4, b.getApartmentsPerFloor());
    Assert.assertEquals("getSpotsPerGarageFloor() should return 5", 5, b.getSpotsPerGarageFloor());
    Assert.assertEquals("getNumStorageRooms() should return 6", 6, b.getNumStorageRooms());

    // 3. Garage floors constant
    Assert.assertEquals("GARAGE_FLOORS constant should always be 2", 2, Building.GARAGE_FLOORS);
    Assert.assertEquals("getGarageFloors() should return 2", 2, b.getGarageFloors());

    // 4. Random generation populated all slots
    Assert.assertNotNull("Apartment [0][0] should be initialized", b.getApartment(0, 0));
    Assert.assertNotNull("Apartment [2][3] should be initialized", b.getApartment(2, 3));
    Assert.assertNotNull("Parking [0][0] should be initialized", b.getParking(0, 0));
    Assert.assertNotNull("Storage [0] should be initialized", b.getStorage(0));
  }

  /**
   * Verifies that {@link Building#setName(String)} correctly updates the building name
   * and that {@link Building#toString()} includes the name and dimensions.
   * <p>
   * <b>Scenario:</b> Changes the name and checks the getter; then verifies
   * {@code toString()} contains the name and floor count.
   * </p>
   */
  @Test
  public void testBuildingNameAndToString() {
    Building b = new Building("Old Name", 5, 4, 10, 8);
    b.setName("New Name");
    Assert.assertEquals("setName() should update the building name", "New Name", b.getName());

    String s = b.toString();
    Assert.assertTrue("toString() should include the building name", s.contains("New Name"));
    Assert.assertTrue("toString() should include the floor count", s.contains("5"));
  }

  /**
   * Verifies the bounds checking for {@link Building#getApartment(int, int)}
   * and {@link Building#setApartment(int, int, Apartment)}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Accesses valid corner indices and verifies non-null results.</li>
   * <li>Accesses out-of-bounds indices and verifies {@code null} is returned.</li>
   * <li>Calls {@code setApartment()} with an invalid index and verifies it is silently ignored.</li>
   * <li>Calls {@code setApartment()} with a valid index and verifies replacement.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingApartmentIndexBounds() {
    Building b = new Building("B", 3, 4, 0, 0);
    Apartment apt = new Apartment(500000.0, 200.0, 6);

    // 1. Valid corner indices
    Assert.assertNotNull("getApartment(0, 0) should not be null", b.getApartment(0, 0));
    Assert.assertNotNull("getApartment(2, 3) should not be null", b.getApartment(2, 3));

    // 2. Out-of-bounds floor
    Assert.assertNull("Negative floor should return null", b.getApartment(-1, 0));
    Assert.assertNull("Floor equal to numFloors should return null", b.getApartment(3, 0));

    // 3. Out-of-bounds door
    Assert.assertNull("Negative door should return null", b.getApartment(0, -1));
    Assert.assertNull("Door equal to apartmentsPerFloor should return null", b.getApartment(0, 4));

    // 4. setApartment with invalid index is silently ignored
    b.setApartment(-1, 0, apt);
    Assert.assertNull("Invalid index should not modify the array", b.getApartment(-1, 0));

    // 5. setApartment with valid index replaces the slot
    b.setApartment(0, 0, apt);
    Assert.assertSame("setApartment() should replace the apartment at the given index",
            apt, b.getApartment(0, 0));
  }

  /**
   * Verifies the bounds checking for {@link Building#getParking(int, int)}.
   * <p>
   * <b>Scenario:</b> Accesses valid and invalid basement and spot indices and verifies
   * the correct return values.
   * </p>
   */
  @Test
  public void testBuildingParkingIndexBounds() {
    Building b = new Building("B", 1, 1, 4, 0);

    // Valid
    Assert.assertNotNull("getParking(0, 0) should not be null", b.getParking(0, 0));
    Assert.assertNotNull("getParking(1, 3) should not be null", b.getParking(1, 3));

    // Invalid basement
    Assert.assertNull("Negative basement should return null", b.getParking(-1, 0));
    Assert.assertNull("Basement equal to GARAGE_FLOORS should return null", b.getParking(2, 0));

    // Invalid spot
    Assert.assertNull("Negative spot should return null", b.getParking(0, -1));
    Assert.assertNull("Spot equal to spotsPerGarageFloor should return null", b.getParking(0, 4));
  }

  /**
   * Verifies the bounds checking for {@link Building#getStorage(int)}.
   * <p>
   * <b>Scenario:</b> Accesses valid and invalid storage indices and verifies
   * the correct return values.
   * </p>
   */
  @Test
  public void testBuildingStorageIndexBounds() {
    Building b = new Building("B", 1, 1, 0, 4);

    // Valid
    Assert.assertNotNull("getStorage(0) should not be null", b.getStorage(0));
    Assert.assertNotNull("getStorage(3) should not be null", b.getStorage(3));

    // Invalid
    Assert.assertNull("Negative index should return null", b.getStorage(-1));
    Assert.assertNull("Index equal to numStorageRooms should return null", b.getStorage(4));
  }

  /**
   * Verifies the apartment status counters of {@link Building}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sets three known apartments — all FREE initially — and checks all counters.</li>
   * <li>Sells one and verifies the sold/available counts update correctly.</li>
   * <li>Reserves another and verifies the reserved/available counts update correctly.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingApartmentCounters() {
    Building b = new Building("B", 1, 3, 0, 0);
    Apartment a1 = new Apartment(100000.0, 60.0, 2);
    Apartment a2 = new Apartment(100000.0, 60.0, 2);
    Apartment a3 = new Apartment(100000.0, 60.0, 2);
    b.setApartment(0, 0, a1);
    b.setApartment(0, 1, a2);
    b.setApartment(0, 2, a3);

    // 1. All FREE
    Assert.assertEquals("Should count 3 available apartments",
            3, b.countAvailableApartments());
    Assert.assertEquals("Should count 0 reserved apartments",
            0, b.countReservedApartments());
    Assert.assertEquals("Should count 0 sold apartments",
            0, b.countSoldApartments());
    Assert.assertEquals("getTotalApartments() should count all non-null slots",
            3, b.getTotalApartments());

    // 2. Sell one
    a1.sell("11111111A");
    Assert.assertEquals("Available should decrease to 2 after a sale",
            2, b.countAvailableApartments());
    Assert.assertEquals("Sold should increase to 1 after a sale",
            1, b.countSoldApartments());

    // 3. Reserve one
    a2.reserve("22222222B");
    Assert.assertEquals("Available should decrease to 1 after a reservation",
            1, b.countAvailableApartments());
    Assert.assertEquals("Reserved should increase to 1 after a reservation",
            1, b.countReservedApartments());
  }

  /**
   * Verifies {@link Building#getTotalApartments()} decreases after a merge operation.
   * <p>
   * <b>Scenario:</b> Creates a building with 3 known apartments, merges two of them,
   * and verifies the count drops from 3 to 2.
   * </p>
   */
  @Test
  public void testBuildingTotalApartmentsAfterMerge() {
    Building b = new Building("B", 1, 3, 0, 0);
    b.setApartment(0, 0, new Apartment(100000.0, 50.0, 2));
    b.setApartment(0, 1, new Apartment(100000.0, 50.0, 2));
    b.setApartment(0, 2, new Apartment(100000.0, 50.0, 2));

    Assert.assertEquals("Should count 3 apartments before merge", 3, b.getTotalApartments());
    b.joinApartments(0, 0, 1, "11111111H", Apartment.Quality.STANDARD);
    Assert.assertEquals("Should count 2 apartments after merge", 2, b.getTotalApartments());
  }

  /**
   * Verifies apartment income calculations of {@link Building}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sets two known apartments with specific prices.</li>
   * <li>Verifies {@link Building#calculatePotentialIncome()} sums both regardless of status.</li>
   * <li>Sells one and verifies {@link Building#calculateSoldIncome()} counts only that unit.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingApartmentIncome() {
    Building b = new Building("B", 1, 2, 0, 0);
    Apartment a1 = new Apartment(100000.0, 60.0, 2);
    Apartment a2 = new Apartment(200000.0, 80.0, 3);
    b.setApartment(0, 0, a1);
    b.setApartment(0, 1, a2);

    // 1. Potential income includes all units
    Assert.assertEquals("Potential income should be 100000 + 200000",
            300000.0, b.calculatePotentialIncome(), DELTA);

    // 2. Sold income is zero when none are sold
    Assert.assertEquals("Sold income should be 0 when nothing is sold",
            0.0, b.calculateSoldIncome(), DELTA);

    // 3. Sold income counts only sold units
    a1.sell("11111111A");
    Assert.assertEquals("Sold income should count only the sold apartment",
            100000.0, b.calculateSoldIncome(), DELTA);
    Assert.assertEquals("Potential income should still include all units after sale",
            300000.0, b.calculatePotentialIncome(), DELTA);
  }

  /**
   * Verifies parking counters and income calculations of {@link Building}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks total parking equals {@code GARAGE_FLOORS * spotsPerGarageFloor}.</li>
   * <li>Sells one space and verifies available/sold counts update.</li>
   * <li>Verifies potential income sums all spaces and sold income counts only sold ones.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingParkingCountersAndIncome() {
    Building b = new Building("B", 1, 1, 3, 0);

    // 1. Total parking = 2 basements * 3 spots = 6
    Assert.assertEquals("countTotalParking() should be 2 * 3 = 6", 6, b.countTotalParking());
    Assert.assertEquals("All 6 spaces should be available initially",
            6, b.countAvailableParking());
    Assert.assertEquals("No spaces should be sold initially", 0, b.countSoldParking());

    // 2. Sell one space
    b.getParking(0, 0).sell("11111111A");
    Assert.assertEquals("Available should decrease to 5 after a sale",
            5, b.countAvailableParking());
    Assert.assertEquals("Sold should increase to 1 after a sale",
            1, b.countSoldParking());

    // 3. Income — use actual prices to avoid hardcoding random values
    double soldPrice = b.getParking(0, 0).getPrice();
    double totalPotential = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        totalPotential += b.getParking(i, j).getPrice();
      }
    }
    Assert.assertEquals("Potential parking income should sum all spaces",
            totalPotential, b.calculatePotentialParkingIncome(), DELTA);
    Assert.assertEquals("Sold parking income should count only the sold space",
            soldPrice, b.calculateSoldParkingIncome(), DELTA);
  }

  /**
   * Verifies storage counters and income calculations of {@link Building}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Checks all units start available.</li>
   * <li>Sells one and verifies available/sold counts update.</li>
   * <li>Verifies potential income sums all units and sold income counts only sold ones.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingStorageCountersAndIncome() {
    Building b = new Building("B", 1, 1, 0, 4);

    // 1. All available initially
    Assert.assertEquals("All 4 units should be available initially",
            4, b.countAvailableStorage());
    Assert.assertEquals("No units should be sold initially", 0, b.countSoldStorage());

    // 2. Sell one unit
    b.getStorage(0).sell("11111111A");
    Assert.assertEquals("Available should decrease to 3 after a sale",
            3, b.countAvailableStorage());
    Assert.assertEquals("Sold should increase to 1 after a sale",
            1, b.countSoldStorage());

    // 3. Income
    double soldPrice = b.getStorage(0).getPrice();
    double totalPotential = b.getStorage(0).getPrice() + b.getStorage(1).getPrice()
            + b.getStorage(2).getPrice() + b.getStorage(3).getPrice();
    Assert.assertEquals("Potential storage income should sum all units",
            totalPotential, b.calculatePotentialStorageIncome(), DELTA);
    Assert.assertEquals("Sold storage income should count only the sold unit",
            soldPrice, b.calculateSoldStorageIncome(), DELTA);
  }

  /**
   * Verifies the DNI-based query methods of {@link Building} for apartments.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Assigns two apartments to one DNI and one to another.</li>
   * <li>Verifies {@link Building#countApartmentsByDni(String)} returns the correct counts.</li>
   * <li>Verifies {@link Building#apartmentInvestmentByDni(String)} sums prices correctly.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testBuildingApartmentQueryByDni() {
    Building b = new Building("B", 1, 3, 0, 0);
    Apartment a1 = new Apartment(100000.0, 60.0, 2);
    Apartment a2 = new Apartment(200000.0, 80.0, 3);
    Apartment a3 = new Apartment(150000.0, 70.0, 3);
    a1.sell("11111111A");
    a2.sell("11111111A");
    a3.sell("99999999Z");
    b.setApartment(0, 0, a1);
    b.setApartment(0, 1, a2);
    b.setApartment(0, 2, a3);

    // 1. countApartmentsByDni
    Assert.assertEquals("Should count 2 apartments for 11111111A",
            2, b.countApartmentsByDni("11111111A"));
    Assert.assertEquals("Should count 1 apartment for 99999999Z",
            1, b.countApartmentsByDni("99999999Z"));
    Assert.assertEquals("Should count 0 apartments for unknown DNI",
            0, b.countApartmentsByDni("00000000X"));

    // 2. apartmentInvestmentByDni
    Assert.assertEquals("Investment for 11111111A should be 100000 + 200000",
            300000.0, b.apartmentInvestmentByDni("11111111A"), DELTA);
  }

  /**
   * Verifies the DNI-based query methods of {@link Building} for parking spaces.
   * <p>
   * <b>Scenario:</b> Sells two spaces to one DNI and one to another, then verifies
   * {@link Building#countParkingByDni(String)} and {@link Building#parkingInvestmentByDni(String)}.
   * </p>
   */
  @Test
  public void testBuildingParkingQueryByDni() {
    Building b = new Building("B", 1, 1, 3, 0);
    b.getParking(0, 0).sell("11111111A");
    b.getParking(0, 1).sell("11111111A");
    b.getParking(1, 0).sell("99999999Z");

    // 1. countParkingByDni
    Assert.assertEquals("Should count 2 spaces for 11111111A",
            2, b.countParkingByDni("11111111A"));
    Assert.assertEquals("Should count 1 space for 99999999Z",
            1, b.countParkingByDni("99999999Z"));
    Assert.assertEquals("Should count 0 spaces for unknown DNI",
            0, b.countParkingByDni("00000000X"));

    // 2. parkingInvestmentByDni
    double expected = b.getParking(0, 0).getPrice() + b.getParking(0, 1).getPrice();
    Assert.assertEquals("Investment should sum prices of both spaces for 11111111A",
            expected, b.parkingInvestmentByDni("11111111A"), DELTA);
  }

  /**
   * Verifies the DNI-based query methods of {@link Building} for storage units.
   * <p>
   * <b>Scenario:</b> Sells two units to one DNI, then verifies
   * {@link Building#countStorageByDni(String)} and {@link Building#storageInvestmentByDni(String)}.
   * </p>
   */
  @Test
  public void testBuildingStorageQueryByDni() {
    Building b = new Building("B", 1, 1, 0, 3);
    b.getStorage(0).sell("11111111A");
    b.getStorage(1).sell("11111111A");

    // 1. countStorageByDni
    Assert.assertEquals("Should count 2 units for 11111111A",
            2, b.countStorageByDni("11111111A"));
    Assert.assertEquals("Should count 0 units for unknown DNI",
            0, b.countStorageByDni("99999999Z"));

    // 2. storageInvestmentByDni
    double expected = b.getStorage(0).getPrice() + b.getStorage(1).getPrice();
    Assert.assertEquals("Investment should sum prices of both units",
            expected, b.storageInvestmentByDni("11111111A"), DELTA);
  }

  /**
   * Verifies {@link Building#canJoinApartments(int, int, int)} validation logic.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Two FREE contiguous apartments — should return {@code true}.</li>
   * <li>Non-contiguous apartments (gap of 2) — should return {@code false}.</li>
   * <li>One apartment is RESERVED — should return {@code false}.</li>
   * <li>One apartment is SOLD — should return {@code false}.</li>
   * <li>Invalid floor index — should return {@code false}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testCanJoinApartments() {
    Building b = new Building("B", 1, 4, 0, 0);
    b.setApartment(0, 0, new Apartment(100000.0, 50.0, 2));
    b.setApartment(0, 1, new Apartment(100000.0, 50.0, 2));
    b.setApartment(0, 2, new Apartment(100000.0, 50.0, 2));
    b.setApartment(0, 3, new Apartment(100000.0, 50.0, 2));

    // 1. Both FREE and contiguous
    Assert.assertTrue("Two FREE contiguous apartments should be joinable",
            b.canJoinApartments(0, 0, 1));

    // 2. Non-contiguous
    Assert.assertFalse("Non-contiguous apartments (doors 0 and 2) should not be joinable",
            b.canJoinApartments(0, 0, 2));

    // 3. One RESERVED
    b.getApartment(0, 2).reserve("11111111A");
    Assert.assertFalse("RESERVED apartment should prevent joining",
            b.canJoinApartments(0, 2, 3));

    // 4. One SOLD
    b.setApartment(0, 2, new Apartment(100000.0, 50.0, 2));
    b.getApartment(0, 2).sell("11111111A");
    Assert.assertFalse("SOLD apartment should prevent joining",
            b.canJoinApartments(0, 2, 3));

    // 5. Invalid floor
    Assert.assertFalse("Invalid floor index should prevent joining",
            b.canJoinApartments(99, 0, 1));
  }

  /**
   * Verifies the complex logic of merging two apartments via {@link Building#joinApartments}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sets four known apartments on the same floor.</li>
   * <li>Merges doors 0 and 1 and verifies the result is {@code true}.</li>
   * <li>Checks that the merged apartment has the summed surface, base price, and room count.</li>
   * <li>Checks that the merged apartment is auto-sold with the provided DNI and quality.</li>
   * <li>Checks the array shift: a3 moves to door 1, a4 to door 2, door 3 becomes {@code null}.</li>
   * <li>Verifies that passing doors in reversed order still succeeds.</li>
   * <li>Verifies that {@link Apartment.Quality#DELUXE} is applied correctly to the merged price.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testJoinApartments() {
    Building b = new Building("B", 1, 4, 0, 0);
    Apartment a1 = new Apartment(100000.0, 50.0, 2);
    Apartment a2 = new Apartment(150000.0, 70.0, 3);
    Apartment a3 = new Apartment(200000.0, 80.0, 4);
    Apartment a4 = new Apartment(200000.0, 90.0, 4);
    b.setApartment(0, 0, a1);
    b.setApartment(0, 1, a2);
    b.setApartment(0, 2, a3);
    b.setApartment(0, 3, a4);

    // 1. Merge doors 0 and 1
    boolean result = b.joinApartments(0, 0, 1, "11111111H", Apartment.Quality.STANDARD);
    Assert.assertTrue("joinApartments() should return true for valid adjacent apartments", result);

    // 2. Merged apartment physical attributes
    Apartment merged = b.getApartment(0, 0);
    Assert.assertNotNull("Merged apartment should exist at door 0", merged);
    Assert.assertEquals("Merged base price should be 100000 + 150000",
            250000.0, merged.getBasePrice(), DELTA);
    Assert.assertEquals("Merged surface should be 50 + 70",
            120.0, merged.getSquareMeters(), DELTA);
    Assert.assertEquals("Merged room count should be 2 + 3", 5, merged.getRooms());

    // 3. Merged apartment is auto-sold with STANDARD quality
    Assert.assertEquals("Merged apartment should be SOLD",
            Apartment.Status.SOLD, merged.getStatus());
    Assert.assertEquals("Merged apartment buyer DNI should match",
            "11111111H", merged.getBuyerDni());
    Assert.assertEquals("Merged price with STANDARD quality = 250000 * 1.0",
            250000.0, merged.getPrice(), DELTA);

    // 4. Array shift: a3 moves to door 1, a4 to door 2, door 3 becomes null
    Assert.assertSame("Door 1 should now reference the old a3", a3, b.getApartment(0, 1));
    Assert.assertSame("Door 2 should now reference the old a4", a4, b.getApartment(0, 2));
    Assert.assertNull("Door 3 (last slot) should be null after shift", b.getApartment(0, 3));

    // 5. Reversed door order also succeeds
    Building b2 = new Building("B2", 1, 3, 0, 0);
    b2.setApartment(0, 0, new Apartment(100000.0, 50.0, 2));
    b2.setApartment(0, 1, new Apartment(100000.0, 50.0, 2));
    Assert.assertTrue("joinApartments() should succeed even when door order is reversed",
            b2.joinApartments(0, 1, 0, "11111111H", Apartment.Quality.STANDARD));

    // 6. DELUXE quality is applied to the merged base price
    Building b3 = new Building("B3", 1, 3, 0, 0);
    b3.setApartment(0, 0, new Apartment(100000.0, 50.0, 2));
    b3.setApartment(0, 1, new Apartment(100000.0, 50.0, 2));
    b3.joinApartments(0, 0, 1, "11111111H", Apartment.Quality.DELUXE);
    Assert.assertEquals("Merged price with DELUXE quality = 200000 * 1.10",
            220000.0, b3.getApartment(0, 0).getPrice(), DELTA);
  }

  /**
   * Verifies that {@link Building#joinApartments} rejects non-contiguous apartments.
   * <p>
   * <b>Scenario:</b> Attempts to join door 0 and door 2 (gap of 1) and verifies
   * the method returns {@code false} without modifying the array.
   * </p>
   */
  @Test
  public void testJoinApartmentsNonContiguousRejected() {
    Building b = new Building("B", 1, 4, 0, 0);
    b.setApartment(0, 0, new Apartment(100000.0, 50.0, 2));
    b.setApartment(0, 2, new Apartment(100000.0, 50.0, 2));

    Assert.assertFalse("joinApartments() should return false for non-contiguous doors",
            b.joinApartments(0, 0, 2, "11111111H", Apartment.Quality.STANDARD));
  }

  /**
   * Verifies the validation and merge logic of {@link Building#joinStorage(int, int, String)}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Verifies {@link Building#canJoinStorage(int, int)} rejects non-contiguous or sold units.</li>
   * <li>Merges two contiguous free units and checks the combined price and surface.</li>
   * <li>Verifies the merged unit is auto-sold with the correct buyer DNI.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testJoinStorage() {
    Building b = new Building("B", 1, 1, 0, 4);

    // 1. canJoinStorage validation
    Assert.assertTrue("Adjacent free units should be joinable", b.canJoinStorage(0, 1));
    Assert.assertFalse("Non-contiguous units should not be joinable", b.canJoinStorage(0, 2));
    b.getStorage(0).sell("11111111A");
    Assert.assertFalse("SOLD unit should prevent joining", b.canJoinStorage(0, 1));

    // 2. Merge units 1 and 2 (both still free)
    Building b2 = new Building("B2", 1, 1, 0, 4);
    Storage s1 = b2.getStorage(1);
    Storage s2 = b2.getStorage(2);
    double expectedPrice = s1.getPrice() + s2.getPrice();
    double expectedSurface = s1.getSquareMeters() + s2.getSquareMeters();

    Assert.assertTrue("joinStorage() should return true for valid adjacent units",
            b2.joinStorage(1, 2, "22222222B"));

    // 3. Merged unit attributes
    Storage merged = b2.getStorage(1);
    Assert.assertEquals("Merged price should sum both units",
            expectedPrice, merged.getPrice(), DELTA);
    Assert.assertEquals("Merged surface should sum both units",
            expectedSurface, merged.getSquareMeters(), DELTA);
    Assert.assertEquals("Merged unit should be auto-sold",
            Storage.Status.SOLD, merged.getStatus());
  }

  /**
   * Verifies the constructor and basic structure of {@link Developer}.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Creates a developer and checks the name and the two default buildings.</li>
   * <li>Adds a building and verifies the count and object reference.</li>
   * <li>Adds buildings beyond the initial capacity of 3 and verifies automatic resizing.</li>
   * <li>Checks that an invalid index returns {@code null}.</li>
   * <li>Verifies {@link Developer#getBuildings()} returns a trimmed array.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testDeveloperStructure() {
    Developer dev = new Developer("Inmobiliaria XYZ");

    // 1. Default state
    Assert.assertEquals("getName() should return the developer name",
            "Inmobiliaria XYZ", dev.getName());
    Assert.assertEquals("Constructor should create 2 default buildings",
            2, dev.getNumBuildings());
    Assert.assertNotNull("Default building 0 should not be null", dev.getBuilding(0));
    Assert.assertNotNull("Default building 1 should not be null", dev.getBuilding(1));

    // 2. Add a building
    Building extra = new Building("Extra", 1, 2, 0, 0);
    dev.addBuilding(extra);
    Assert.assertEquals("addBuilding() should increase count to 3", 3, dev.getNumBuildings());
    Assert.assertSame("getBuilding(2) should return the added building",
            extra, dev.getBuilding(2));

    // 3. Resize — initial capacity is 3; adding a 4th forces resize
    dev.addBuilding(new Building("B4", 1, 1, 0, 0));
    Assert.assertEquals("Count should be 4 after resize", 4, dev.getNumBuildings());
    Assert.assertNotNull("getBuilding(3) should not be null after resize", dev.getBuilding(3));

    // 4. Invalid index returns null
    Assert.assertNull("Negative index should return null", dev.getBuilding(-1));
    Assert.assertNull("Out-of-bounds index should return null", dev.getBuilding(99));

    // 5. getBuildings() returns trimmed array
    Assert.assertEquals("getBuildings() should have exactly numBuildings elements",
            4, dev.getBuildings().length);
  }

  /**
   * Verifies {@link Developer#setName(String)} and {@link Developer#toString()}.
   * <p>
   * <b>Scenario:</b> Changes the developer name and verifies the getter;
   * then checks that {@code toString()} contains the updated name and building count.
   * </p>
   */
  @Test
  public void testDeveloperNameAndToString() {
    Developer dev = new Developer("Old Name");
    dev.setName("New Name");
    Assert.assertEquals("setName() should update the developer name", "New Name", dev.getName());

    String s = dev.toString();
    Assert.assertTrue("toString() should include the developer name", s.contains("New Name"));
    Assert.assertTrue("toString() should include the building count", s.contains("2"));
  }

  /**
   * Verifies {@link Developer#sellApartment} delegation and validation.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sells an apartment successfully and verifies the status on the underlying object.</li>
   * <li>Attempts to sell with an invalid building index — should return {@code false}.</li>
   * <li>Attempts to sell with an invalid floor — should return {@code false}.</li>
   * <li>Attempts to sell with a {@code null} DNI — should return {@code false}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testDeveloperSellApartment() {
    Developer dev = new Developer("Dev");
    Apartment apt = new Apartment(100000.0, 60.0, 2);
    dev.getBuilding(0).setApartment(0, 0, apt);

    // 1. Successful sale
    Assert.assertTrue("sellApartment() should return true with valid arguments",
            dev.sellApartment(0, 0, 0, "11111111A", Apartment.Quality.STANDARD));
    Assert.assertEquals("Apartment status should be SOLD after sale",
            Apartment.Status.SOLD, apt.getStatus());
    Assert.assertEquals("Buyer DNI should be stored", "11111111A", apt.getBuyerDni());

    // 2. Invalid building index
    Assert.assertFalse("sellApartment() should return false for invalid building index",
            dev.sellApartment(99, 0, 0, "22222222B", Apartment.Quality.STANDARD));

    // 3. Invalid floor
    Assert.assertFalse("sellApartment() should return false for invalid floor",
            dev.sellApartment(0, 999, 0, "22222222B", Apartment.Quality.STANDARD));

    // 4. Null DNI
    dev.getBuilding(0).setApartment(0, 1, new Apartment(100000.0, 60.0, 2));
    Assert.assertFalse("sellApartment() should return false with null DNI",
            dev.sellApartment(0, 0, 1, null, Apartment.Quality.STANDARD));
  }

  /**
   * Verifies {@link Developer#reserveApartment} delegation and validation.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Reserves an apartment successfully and verifies the status on the underlying object.</li>
   * <li>Attempts to reserve with an invalid building index — should return {@code false}.</li>
   * <li>Attempts to reserve with an invalid floor — should return {@code false}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testDeveloperReserveApartment() {
    Developer dev = new Developer("Dev");
    Apartment apt = new Apartment(100000.0, 60.0, 2);
    dev.getBuilding(0).setApartment(0, 0, apt);

    // 1. Successful reservation
    Assert.assertTrue("reserveApartment() should return true with valid arguments",
            dev.reserveApartment(0, 0, 0, "11111111A", Apartment.Quality.PLUS));
    Assert.assertEquals("Apartment status should be RESERVED",
            Apartment.Status.RESERVED, apt.getStatus());
    Assert.assertEquals("Buyer DNI should be stored", "11111111A", apt.getBuyerDni());

    // 2. Invalid building index
    Assert.assertFalse("reserveApartment() should return false for invalid building index",
            dev.reserveApartment(99, 0, 0, "22222222B", Apartment.Quality.STANDARD));

    // 3. Invalid floor
    Assert.assertFalse("reserveApartment() should return false for invalid floor",
            dev.reserveApartment(0, 999, 0, "22222222B", Apartment.Quality.STANDARD));
  }

  /**
   * Verifies {@link Developer#sellParking} delegation and validation.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sells a parking space successfully and verifies the underlying object status.</li>
   * <li>Attempts to sell an already-sold space — should return {@code false}.</li>
   * <li>Attempts to sell with an invalid building or spot index — should return {@code false}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testDeveloperSellParking() {
    Developer dev = new Developer("Dev");

    // 1. Successful sale
    Assert.assertTrue("sellParking() should return true with valid arguments",
            dev.sellParking(0, 0, 0, "11111111A"));
    Assert.assertEquals("Parking status should be SOLD",
            Parking.Status.SOLD, dev.getBuilding(0).getParking(0, 0).getStatus());

    // 2. Already sold
    Assert.assertFalse("sellParking() should return false when the space is already sold",
            dev.sellParking(0, 0, 0, "22222222B"));

    // 3. Invalid building index
    Assert.assertFalse("sellParking() should return false for invalid building index",
            dev.sellParking(99, 0, 0, "33333333C"));

    // 4. Invalid spot index
    Assert.assertFalse("sellParking() should return false for invalid spot index",
            dev.sellParking(0, 0, 999, "33333333C"));
  }

  /**
   * Verifies {@link Developer#sellStorage} delegation and validation.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sells a storage unit successfully and verifies the underlying object status.</li>
   * <li>Attempts to sell an already-sold unit — should return {@code false}.</li>
   * <li>Attempts to sell with an invalid building or storage index — should return {@code false}.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testDeveloperSellStorage() {
    Developer dev = new Developer("Dev");

    // 1. Successful sale
    Assert.assertTrue("sellStorage() should return true with valid arguments",
            dev.sellStorage(0, 0, "11111111A"));
    Assert.assertEquals("Storage status should be SOLD",
            Storage.Status.SOLD, dev.getBuilding(0).getStorage(0).getStatus());

    // 2. Already sold
    Assert.assertFalse("sellStorage() should return false when the unit is already sold",
            dev.sellStorage(0, 0, "22222222B"));

    // 3. Invalid building index
    Assert.assertFalse("sellStorage() should return false for invalid building index",
            dev.sellStorage(99, 0, "33333333C"));

    // 4. Invalid storage index
    Assert.assertFalse("sellStorage() should return false for invalid storage index",
            dev.sellStorage(0, 999, "33333333C"));
  }

  /**
   * Verifies that {@link Developer#countParkingByDni(String)} and
   * {@link Developer#countStorageByDni(String)} aggregate counts across all buildings.
   * <p>
   * <b>Scenario:</b>
   * <ol>
   * <li>Sells parking spaces in both buildings to the same DNI and verifies the aggregated count.</li>
   * <li>Sells storage units in both buildings to the same DNI and verifies the aggregated count.</li>
   * </ol>
   * </p>
   */
  @Test
  public void testDeveloperCrossBuildingDniCounters() {
    Developer dev = new Developer("Dev");

    // 1. Parking across both buildings
    dev.sellParking(0, 0, 0, "11111111A");
    dev.sellParking(0, 0, 1, "11111111A");
    dev.sellParking(1, 0, 0, "11111111A");
    Assert.assertEquals("countParkingByDni() should aggregate across all buildings",
            3, dev.countParkingByDni("11111111A"));
    Assert.assertEquals("countParkingByDni() should return 0 for unknown DNI",
            0, dev.countParkingByDni("99999999Z"));

    // 2. Storage across both buildings
    dev.sellStorage(0, 0, "11111111A");
    dev.sellStorage(0, 1, "11111111A");
    dev.sellStorage(1, 0, "11111111A");
    Assert.assertEquals("countStorageByDni() should aggregate across all buildings",
            3, dev.countStorageByDni("11111111A"));
    Assert.assertEquals("countStorageByDni() should return 0 for unknown DNI",
            0, dev.countStorageByDni("99999999Z"));
  }
}
