import java.util.Random;

/**
 * Main application class to demonstrate polymorphism and inheritance.
 * Creates an array of random employees and executes their methods.
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
        Employee[] employees = new Employee[10];
        Random random = new Random();

        // 1. Store ten random employees in the array
        for (int i = 0; i < employees.length; i++) {
            int choice = random.nextInt(4);

            switch (choice) {
                case 0:
                    employees[i] = new Employee();
                    break;
                case 1:
                    employees[i] = new Lawyer();
                    break;
                case 2:
                    employees[i] = new Secretary();
                    break;
                case 3:
                    employees[i] = new Janitor();
                    break;
            }
        }

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