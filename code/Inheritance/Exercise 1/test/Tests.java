import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite to validate the {@link Employee} class hierarchy and its overrides.
 * Ensures that {@link Lawyer}, {@link Secretary}, and {@link Janitor} meet all specific requirements.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * Delta value for floating-point comparisons ({@code double}).
   */
  private static final double DELTA = 1e-9;

  /**
   * Validates the default values of a standard {@link Employee}.
   */
  @Test
  public void testEmployeeDefaults() {
    Employee emp = new Employee();

    assertEquals("Standard salary should be 40000.0", 40000.0, emp.getSalary(), DELTA);
    assertEquals("Standard hours should be 30", 30, emp.getHours());
    assertEquals("Standard vacation days should be 20", 20, emp.getVacationDays());
    assertEquals("Standard vacation month should be August", "August", emp.getVacationMonth());
  }

  /**
   * Validates the specific overrides and logic for the Lawyer class.
   */
  @Test
  public void testLawyerOverrides() {
    Lawyer lawyer = new Lawyer();

    // Earns 10000 euros more than an Employee (40000 + 10000)
    assertEquals("Lawyer salary should be 50000.0", 50000.0, lawyer.getSalary(), DELTA);

    // Works 5 more hours per week (30 + 5)
    assertEquals("Lawyer hours should be 35", 35, lawyer.getHours());

    // Has half the vacation days (20 / 2)
    assertEquals("Lawyer vacation days should be 10", 10, lawyer.getVacationDays());

    // Takes vacation in July instead of August
    assertEquals("Lawyer vacation month should be July", "July", lawyer.getVacationMonth());
  }

  /**
   * Validates the specific overrides and logic for the Secretary class.
   */
  @Test
  public void testSecretaryOverrides() {
    Secretary secretary = new Secretary();

    // Earns 5000 euros more than an Employee (40000 + 5000)
    assertEquals("Secretary salary should be 45000.0", 45000.0, secretary.getSalary(), DELTA);

    // Inherits default hours
    assertEquals("Secretary hours should be 30", 30, secretary.getHours());

    // Inherits default vacation days
    assertEquals("Secretary vacation days should be 20", 20, secretary.getVacationDays());

    // Inherits default vacation month
    assertEquals("Secretary vacation month should be August", "August", secretary.getVacationMonth());
  }

  /**
   * Validates the specific overrides and logic for the Janitor class.
   */
  @Test
  public void testJanitorOverrides() {
    Janitor janitor = new Janitor();

    // Inherits default salary
    assertEquals("Janitor salary should be 40000.0", 40000.0, janitor.getSalary(), DELTA);

    // Inherits default hours
    assertEquals("Janitor hours should be 30", 30, janitor.getHours());

    // Has 5 more vacation days (20 + 5)
    assertEquals("Janitor vacation days should be 25", 25, janitor.getVacationDays());

    // Takes vacation in September instead of August
    assertEquals("Janitor vacation month should be September", "September", janitor.getVacationMonth());
  }
}