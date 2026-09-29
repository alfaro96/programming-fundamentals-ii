/**
 * Represents a janitor in the company.
 * Extends the {@link Employee} class with modified vacation benefits.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Janitor extends Employee {

    /**
     * Gets the number of vacation days.
     * A janitor has 5 more vacation days than a regular employee.
     *
     * @return The number of vacation days.
     */
    @Override
    public int getVacationDays() {
        return super.getVacationDays() + 5;
    }

    /**
     * Gets the designated vacation month.
     * A janitor takes vacation in September instead of August.
     *
     * @return The vacation month.
     */
    @Override
    public String getVacationMonth() {
        return "September";
    }
}
