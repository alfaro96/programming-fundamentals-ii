/**
 * Main application class to demonstrate polymorphism and inheritance.
 * Creates an array of employees of every type and executes their methods.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Store ten employees of the four types in an array
        Employee[] employees = {
                new Employee(), new Lawyer(), new Secretary(), new Janitor(), new Lawyer(),
                new Janitor(), new Employee(), new Secretary(), new Lawyer(), new Janitor()
        };

        // 2. Iterate through the array and call methods
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];

            System.out.println("--- Employee #" + (i + 1) + " (" + employee.getClass().getSimpleName() + ") ---");
            System.out.println("Salary: " + employee.getSalary() + " euros");
            System.out.println("Hours per week: " + employee.getHours());
            System.out.println("Vacation days: " + employee.getVacationDays());
            System.out.println("Vacation month: " + employee.getVacationMonth());

            // Check specific types to call additional methods (downcasting)
            if (employee instanceof Lawyer) {
                Lawyer lawyer = (Lawyer) employee;
                lawyer.attendCourt();
            } else if (employee instanceof Secretary) {
                Secretary secretary = (Secretary) employee;
                secretary.makePhotocopies();
            }

            System.out.println();
        }
    }
}