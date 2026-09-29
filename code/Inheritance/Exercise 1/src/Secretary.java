/**
 * Represents a secretary in the company.
 * Extends the {@link Employee} class with specific salary adjustments and behaviors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Secretary extends Employee {

    /**
     * Gets the salary of the secretary.
     * A secretary earns 5000 euros more than a regular employee.
     *
     * @return The annual salary in euros.
     */
    @Override
    public double getSalary() {
        return super.getSalary() + 5000.0;
    }

    /**
     * Performs a specific action for the secretary.
     */
    public void makePhotocopies() {
        System.out.println("I am making photocopies");
    }
}
