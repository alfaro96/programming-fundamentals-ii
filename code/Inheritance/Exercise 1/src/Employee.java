/**
 * Represents a standard employee in the company.
 * This class serves as the base (superclass) for other specific employee types.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Employee {

    /**
     * Gets the standard working hours per week.
     *
     * @return The number of hours worked per week.
     */
    public int getHours() {
        return 30;
    }

    /**
     * Gets the base salary of a standard employee.
     *
     * @return The annual salary in euros.
     */
    public double getSalary() {
        return 40000.0;
    }

    /**
     * Gets the number of vacation days for a standard employee.
     *
     * @return The number of vacation days.
     */
    public int getVacationDays() {
        return 20;
    }

    /**
     * Gets the designated vacation month for a standard employee.
     *
     * @return The vacation month.
     */
    public String getVacationMonth() {
        return "August";
    }
}
