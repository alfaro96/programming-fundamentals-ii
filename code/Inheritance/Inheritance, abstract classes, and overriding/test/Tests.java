import associations.CommerceAssociation;
import commerces.CarDealership;
import commerces.Commerce;
import commerces.Employee;
import commerces.Restaurant;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p>Covers all the operations performed in {@link Main}:
 * object creation, employee management, association handling,
 * cloning, equality checks, and restaurant replacement.</p>
 *
 * <p>Tests are grouped logically following the seven steps of
 * {@code Main#main(String[])}.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Commerce
 * @see Restaurant
 * @see CarDealership
 * @see CommerceAssociation
 * @see Employee
 */
public class Tests {

  /** First restaurant, as created in {@link Main}. */
  private Restaurant restaurant1;

  /** Second restaurant, as created in {@link Main}. */
  private Restaurant restaurant2;

  /** First car dealership, as created in {@link Main}. */
  private CarDealership dealership1;

  /** Second car dealership, as created in {@link Main}. */
  private CarDealership dealership2;

  /** Employees assigned to {@link #restaurant1}. */
  private Employee emp1;

  /** Employees assigned to {@link #restaurant1}. */
  private Employee emp2;

  /** Employees assigned to {@link #restaurant2}. */
  private Employee emp3;

  /** Employees assigned to {@link #restaurant2}. */
  private Employee emp4;

  /** Employees assigned to {@link #dealership1}. */
  private Employee emp5;

  /** Employees assigned to {@link #dealership1}. */
  private Employee emp6;

  /** Employees assigned to {@link #dealership1}. */
  private Employee emp7;

  /** Employees assigned to {@link #dealership2}. */
  private Employee emp8;

  /** Employees assigned to {@link #dealership2}. */
  private Employee emp9;

  /** Employees assigned to {@link #dealership2}. */
  private Employee emp10;

  /** Association populated with all four commerces. */
  private CommerceAssociation association;

  /**
   * Rebuilds the full object graph before each test, mirroring
   * exactly the setup in {@link Main#main(String[])}.
   */
  @Before
  public void setUp() {
    // Step 1: Restaurants and dealerships
    restaurant1 = new Restaurant(
            "La Buena Mesa", "Calle Mayor 1, Madrid", "B12345678", 15, 60);
    restaurant2 = new Restaurant(
            "El Buen Sabor", "Avenida Sol 22, Sevilla", "B87654321", 10, 40);

    dealership1 = new CarDealership(
            "AutoCenter Norte", "Calle Norte 5, Bilbao", "A11111111");
    dealership2 = new CarDealership(
            "AutoCenter Sur", "Calle Sur 9, Valencia", "A22222222");

    // Step 2: Employees
    emp1 = new Employee("12345678A", "Ana García",    "C/ Luna 3",
            "600111222", "ana@buenamesaa.com",   "2020-01-10", "Camarera",      1400.0);
    emp2 = new Employee("23456789B", "Luis Pérez",    "C/ Sol 7",
            "600333444", "luis@buenamesa.com",   "2021-03-15", "Cocinero",      1600.0);
    restaurant1.hireEmployee(emp1);
    restaurant1.hireEmployee(emp2);

    emp3 = new Employee("34567890C", "Marta López",  "C/ Mar 5",
            "600555666", "marta@sabor.com",      "2019-06-01", "Camarera",      1350.0);
    emp4 = new Employee("45678901D", "Carlos Ruiz",  "C/ Río 2",
            "600777888", "carlos@sabor.com",     "2022-09-20", "Jefe cocina",   1900.0);
    restaurant2.hireEmployee(emp3);
    restaurant2.hireEmployee(emp4);

    emp5 = new Employee("56789012E", "Pedro Sanz",   "C/ Alta 11",
            "601100200", "pedro@autocnorte.com", "2018-02-28", "Vendedor",      1800.0);
    emp6 = new Employee("67890123F", "Sofía Mora",   "C/ Baja 4",
            "601300400", "sofia@autocnorte.com", "2020-07-14", "Mecánica",      1700.0);
    emp7 = new Employee("78901234G", "Javier Gil",   "C/ Centro 8",
            "601500600", "javier@autocnorte.com","2021-11-05", "Gerente",       2500.0);
    dealership1.hireEmployee(emp5);
    dealership1.hireEmployee(emp6);
    dealership1.hireEmployee(emp7);

    emp8 = new Employee("89012345H", "Elena Castro", "C/ Este 6",
            "601700800", "elena@autocsur.com",   "2017-05-22", "Vendedora",     1850.0);
    emp9 = new Employee("90123456I", "Miguel Torres","C/ Oeste 3",
            "601900000", "miguel@autocsur.com",  "2019-08-30", "Mecánico",      1650.0);
    emp10 = new Employee("01234567J", "Laura Vega",   "C/ Norte 1",
            "602100200", "laura@autocsur.com",   "2023-01-15", "Recepcionista", 1300.0);
    dealership2.hireEmployee(emp8);
    dealership2.hireEmployee(emp9);
    dealership2.hireEmployee(emp10);

    // Step 3: Association
    association = new CommerceAssociation(
            10, "Plaza España 1, Madrid", "Roberto Navarro");

    // Step 4: Add commerces
    association.addCommerce(restaurant1);
    association.addCommerce(restaurant2);
    association.addCommerce(dealership1);
    association.addCommerce(dealership2);
  }

  /**
   * Verifies that {@link Restaurant} objects are created with the
   * correct name, address, and identifier.
   */
  @Test
  public void testRestaurantCreation() {
    assertEquals("La Buena Mesa", restaurant1.getName());
    assertEquals("Calle Mayor 1, Madrid", restaurant1.getAddress());
    assertEquals("B12345678", restaurant1.getIdentifier());
    assertEquals(15, restaurant1.getNumTables());
    assertEquals(60, restaurant1.getCapacity());

    assertEquals("El Buen Sabor", restaurant2.getName());
    assertEquals("B87654321", restaurant2.getIdentifier());
  }

  /**
   * Verifies that {@link CarDealership} objects are created with the
   * correct name and identifier.
   */
  @Test
  public void testCarDealershipCreation() {
    assertEquals("AutoCenter Norte", dealership1.getName());
    assertEquals("A11111111", dealership1.getIdentifier());

    assertEquals("AutoCenter Sur", dealership2.getName());
    assertEquals("A22222222", dealership2.getIdentifier());
  }

  /**
   * Verifies that {@link Employee} objects carry the correct field
   * values after construction.
   */
  @Test
  public void testEmployeeCreation() {
    assertEquals("12345678A", emp1.getIdentifier());
    assertEquals("Ana García", emp1.getName());
    assertEquals("Camarera", emp1.getPosition());
    assertEquals(1400.0, emp1.getSalary(), 0.001);
  }

  /**
   * Verifies that {@code hireEmployee()} correctly adds employees
   * and that {@code getEmployeeCount()} reflects the new total.
   */
  @Test
  public void testHireEmployee() {
    assertEquals(2, restaurant1.getEmployeeCount());
    assertEquals(2, restaurant2.getEmployeeCount());
    assertEquals(3, dealership1.getEmployeeCount());
    assertEquals(3, dealership2.getEmployeeCount());
  }

  /**
   * Verifies that {@link Commerce#fireEmployee(String identifier)} removes the employee,
   * returns the correct object, and compacts the array.
   */
  @Test
  public void testFireEmployee() {
    Employee fired = restaurant1.fireEmployee("12345678A");

    assertNotNull(fired);
    assertEquals("12345678A", fired.getIdentifier());
    assertEquals(1, restaurant1.getEmployeeCount());
    // Array must be compacted: emp2 should now be at index 0
    assertEquals(emp2, restaurant1.getEmployees()[0]);
  }

  /**
   * Verifies that firing a non-existent identifier returns {@code null}
   * and leaves the employee count unchanged.
   */
  @Test
  public void testFireNonExistentEmployee() {
    Employee result = restaurant1.fireEmployee("XXXXXXXXX");
    assertNull(result);
    assertEquals(2, restaurant1.getEmployeeCount());
  }

  /**
   * Verifies that {@link Commerce#hireEmployee(Employee employee)} doubles the internal array
   * when it reaches full capacity.
   */
  @Test
  public void testHireEmployeeArrayDoubling() {
    // Fill a fresh restaurant beyond default capacity (10)
    Restaurant r = new Restaurant("Test", "Addr", "T00000000", 5, 20);
    for (int i = 0; i < 12; i++) {
      r.hireEmployee(new Employee(
              "DNI" + i, "Name" + i, "Addr", "600",
              "e@e.com", "2020-01-01", "Pos", 1000.0));
    }
    assertEquals(12, r.getEmployeeCount());
  }

  /**
   * Verifies that the association is created with the correct
   * address and president.
   */
  @Test
  public void testAssociationCreation() {
    assertEquals("Plaza España 1, Madrid", association.getAddress());
    assertEquals("Roberto Navarro", association.getPresident());
  }

  /**
   * Verifies that all four commerces were added successfully.
   */
  @Test
  public void testAddCommerce() {
    Commerce[] commerces = association.getCommerces();
    assertEquals(2, commerces.length);
  }

  /**
   * Verifies that adding the same commerce twice does not duplicate
   * it in the association.
   */
  @Test
  public void testAddDuplicateCommerce() {
    association.addCommerce(restaurant1); // duplicate
    assertEquals(2, association.getCommerces().length);
  }

  /**
   * Verifies that adding a commerce when the association is full
   * does nothing (no exception, no change in count).
   */
  @Test
  public void testAddCommerceWhenFull() {
    CommerceAssociation small = new CommerceAssociation(
            2, "Addr", "President");
    small.addCommerce(restaurant1);
    small.addCommerce(restaurant2);
    small.addCommerce(dealership1); // should be silently ignored
    assertEquals(2, small.getCommerces().length);
  }

  /**
   * Verifies that {@link CommerceAssociation#clone()} returns a different object
   * reference but one that is logically equal to the original.
   */
  @Test
  public void testAssociationCloneIsNotSameReference() {
    CommerceAssociation copy = association.clone();
    assertNotSame(association, copy);
  }

  /**
   * Verifies that the original and its clone satisfy
   * {@link CommerceAssociation#equals(Object object)}.
   */
  @Test
  public void testAssociationCloneEquals() {
    CommerceAssociation copy = association.clone();
    assertEquals(association, copy);
  }

  /**
   * Verifies that modifying the clone's president does not affect
   * the original (deep copy of primitive and {@code String} fields).
   */
  @Test
  public void testAssociationCloneIsDeep() {
    CommerceAssociation copy = association.clone();
    copy.setPresident("Another Person");
    assertNotEquals(association.getPresident(), copy.getPresident());
  }

  /**
   * Verifies that {@link CommerceAssociation#replaceRestaurant(Restaurant restaurant, CarDealership carDealership)} swaps correctly.
   */
  @Test
  public void testReplaceRestaurant() {
    CarDealership dealership3 = new CarDealership(
            "AutoCenter Este", "Calle Este 15, Zaragoza", "A33333333");

    association.replaceRestaurant(restaurant1, dealership3);

    Commerce[] commerces = association.getCommerces();
    boolean containsDealership3 = false;
    boolean containsRestaurant1 = false;

    for (Commerce c : commerces) {
      if (c.equals(dealership3)) containsDealership3 = true;
      if (c.equals(restaurant1)) containsRestaurant1 = true;
    }

    assertTrue("dealership3 should be present after replacement",
            containsDealership3);
    assertFalse("restaurant1 should no longer be present after replacement",
            containsRestaurant1);
  }

  /**
   * Verifies that the total commerce count remains the same after
   * {@link CommerceAssociation#replaceRestaurant(Restaurant restaurant, CarDealership carDealership)} (one-for-one swap).
   */
  @Test
  public void testReplaceRestaurantKeepsCount() {
    CarDealership dealership3 = new CarDealership(
            "AutoCenter Este", "Calle Este 15, Zaragoza", "A33333333");
    association.replaceRestaurant(restaurant1, dealership3);
    assertEquals(2, association.getCommerces().length);
  }

  /**
   * Verifies that calling {@link CommerceAssociation#replaceRestaurant(Restaurant restaurant, CarDealership carDealership)}
   * with a restaurant that is not in the association leaves everything unchanged.
   */
  @Test
  public void testReplaceRestaurantNotFound() {
    Restaurant unknown = new Restaurant(
            "Unknown", "Nowhere", "X99999999", 5, 20);
    CarDealership dealership3 = new CarDealership(
            "AutoCenter Este", "Calle Este 15, Zaragoza", "A33333333");

    association.replaceRestaurant(unknown, dealership3);
    // Still 4 commerces, unchanged
    assertEquals(2, association.getCommerces().length);
  }

  /**
   * Verifies reflexive equality: an object must equal itself.
   */
  @Test
  public void testRestaurantEqualsItself() {
    assertEquals(restaurant1, restaurant1);
  }

  /**
   * Verifies that two distinct {@link Restaurant} instances with
   * the same daily menus are considered equal.
   */
  @Test
  public void testRestaurantEqualsSameMenus() {
    Restaurant r1 = new Restaurant("R1", "Addr1", "C11111111", 10, 40);
    Restaurant r2 = new Restaurant("R2", "Addr2", "C22222222", 5, 20);
    // Both have default empty menus → should be equal
    assertEquals(r1, r2);
  }

  /**
   * Verifies that two {@link Restaurant} instances with different
   * menus are not equal.
   */
  @Test
  public void testRestaurantNotEqualsDifferentMenus() {
    Restaurant r1 = new Restaurant("R1", "Addr1", "C11111111", 10, 40);
    Restaurant r2 = new Restaurant("R2", "Addr2", "C22222222", 5, 20);
    r1.setDailyMenu("Paella", 0);
    assertNotEquals(r1, r2);
  }

  /**
   * Verifies that two {@link CarDealership} instances with no
   * vehicles are considered equal.
   */
  @Test
  public void testCarDealershipEqualsEmpty() {
    CarDealership d1 = new CarDealership("D1", "Addr1", "D11111111");
    CarDealership d2 = new CarDealership("D2", "Addr2", "D22222222");
    assertEquals(d1, d2);
  }

  /**
   * Verifies that {@link Employee#equals(Object)} is based on DNI.
   */
  @Test
  public void testEmployeeEqualsByDNI() {
    Employee a = new Employee("12345678A", "Ana", "Addr",
            "600", "a@a.com", "2020-01-01", "Dev", 1000.0);
    Employee b = new Employee("12345678A", "Different Name", "Other",
            "700", "b@b.com", "2021-01-01", "QA", 2000.0);
    assertEquals(a, b);
  }

  /**
   * Verifies that {@link Restaurant#toString()}
   * is non-null and non-empty.
   */
  @Test
  public void testRestaurantToStringNotNull() {
    String s = restaurant1.toString();
    assertNotNull(s);
    assertFalse(s.isEmpty());
  }

  /**
   * Verifies that {@link CommerceAssociation#toString()}
   * is non-null and non-empty.
   */
  @Test
  public void testAssociationToStringNotNull() {
    String s = association.toString();
    assertNotNull(s);
    assertFalse(s.isEmpty());
  }

  /**
   * Verifies that a newly created commerce has zero total sales.
   */
  @Test
  public void testInitialTotalSalesIsZero() {
    assertEquals(0.0, restaurant1.totalSales(), 0.001);
  }

  /**
   * Verifies that {@link Restaurant#updateSales(int month, int day, double amount)} correctly
   * accumulates the value.
   */
  @Test
  public void testUpdateSalesWithDate() {
    restaurant1.updateSales(0, 0, 500.0);
    restaurant1.updateSales(0, 0, 300.0);
    assertEquals(800.0, restaurant1.salesInMonth(0), 0.001);
    assertEquals(800.0, restaurant1.totalSales(), 0.001);
  }

  /**
   * Verifies that {@link Restaurant#updateSales(double amount)} (no-arg date variant)
   * adds to the current month's total.
   */
  @Test
  public void testUpdateSalesCurrentDate() {
    restaurant1.updateSales(250.0);
    assertTrue(restaurant1.totalSales() >= 250.0);
  }

  /**
   * Verifies that {@link Restaurant#salesInMonth(int month)}} returns the correct
   * per-month total.
   */
  @Test
  public void testSalesInMonth() {
    restaurant1.updateSales(1, 0, 100.0);
    restaurant1.updateSales(1, 5, 200.0);
    assertEquals(300.0, restaurant1.salesInMonth(1), 0.001);
    assertEquals(0.0, restaurant1.salesInMonth(0), 0.001);
  }

  /**
   * Verifies that {@link Restaurant#topSalesMonth()} returns the index of the
   * month with the highest accumulated sales.
   */
  @Test
  public void testTopSalesMonth() {
    restaurant1.updateSales(3, 0, 1000.0); // April is best
    restaurant1.updateSales(0, 0, 500.0);
    assertEquals(3, restaurant1.topSalesMonth());
  }

  // ================================================================
  // Daily menus (Restaurant)
  // ================================================================

  /**
   * Verifies that {@link Restaurant#setDailyMenu(String menu, int day)}
   * and {@link Restaurant#getDailyMenu(int day)}
   * correctly store and retrieve menus by day index.
   */
  @Test
  public void testSetAndGetDailyMenu() {
    restaurant1.setDailyMenu("Paella valenciana", 0);
    restaurant1.setDailyMenu("Cocido madrileño", 4);

    assertEquals("Paella valenciana", restaurant1.getDailyMenu(0));
    assertEquals("Cocido madrileño", restaurant1.getDailyMenu(4));
  }

  /**
   * Verifies that {@link Restaurant#getDailyMenu(int day)} returns {@code null} for
   * an out-of-range day index.
   */
  @Test
  public void testGetDailyMenuOutOfRange() {
    assertNull(restaurant1.getDailyMenu(7));
    assertNull(restaurant1.getDailyMenu(-1));
  }
}