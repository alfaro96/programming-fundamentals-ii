/**
 * Represents a lawyer in the company.
 * Extends the {@link Employee} class with modified benefits and specific behaviors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Lawyer extends Employee {

    /**
     * Gets the number of vacation days.
     * A lawyer has half the vacation days of a regular employee.
     *
     * @return The number of vacation days.
     */
    @Override
    public int getVacationDays() {
        return super.getVacationDays() / 2;
    }

    /**
     * Gets the salary of the lawyer.
     * A lawyer earns 10000 euros more than a regular employee.
     *
     * @return The annual salary in euros.
     */
    @Override
    public double getSalary() {
        return super.getSalary() + 10000.0;
    }

    /**
     * Gets the working hours per week.
     * A lawyer works 5 more hours per week than a regular employee.
     *
     * @return The number of hours worked per week.
     */
    @Override
    public int getHours() {
        return super.getHours() + 5;
    }

    /**
     * Gets the designated vacation month.
     * A lawyer takes vacation in July instead of August.
     *
     * @return The vacation month.
     */
    @Override
    public String getVacationMonth() {
        return "July";
    }

    /**
     * Performs a specific action for the lawyer.
     */
    public void attendCourt() {
        System.out.println("I am in court");
    }
}
