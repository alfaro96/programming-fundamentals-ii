package commerces;

import java.time.LocalDate;

/**
 * Abstract base class representing a generic commerce (business).
 *
 * <p>Holds common data shared by all commerce subtypes
 * ({@link CarDealership} and {@link Restaurant}): commercial name,
 * address, tax identifier, a daily-sales matrix, a stock array, and
 * an array of {@link Employee} objects.</p>
 *
 * <p>The {@code dailySales} matrix has dimensions
 * {@value #MONTHS}&times;{@value #DAYS_PER_MONTH}, where the first
 * index represents the month (0-based) and the second the day
 * (0-based).</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see CarDealership
 * @see Restaurant
 * @see Employee
 */
public abstract class Commerce implements Cloneable {

    /** Number of months in the sales matrix. */
    protected static final int MONTHS = 12;

    /** Number of day slots per month in the sales matrix. */
    protected static final int DAYS_PER_MONTH = 31;

    /** Default initial capacity for the employee array. */
    private static final int DEFAULT_EMPLOYEE_CAPACITY = 10;

    /** Commercial name of the business. */
    private String name;

    /** Physical address of the business. */
    private String address;

    /** Tax identification code of the business. */
    private String identifier;

    /**
     * Two-dimensional sales matrix of size
     * {@value #MONTHS}&times;{@value #DAYS_PER_MONTH}.
     * Each cell stores the total sales amount for a specific
     * month–day combination.
     */
    private double[][] dailySales;

    /**
     * Current stock array. Each position represents the stock level
     * for a specific product.
     */
    private double[] stock;

    /**
     * Array of employees. Only positions {@code [0, employeeCount)}
     * contain valid (non-{@code null}) employee references.
     */
    private Employee[] employees;

    /** Number of employees currently stored in {@code employees}. */
    private int employeeCount;

    /**
     * Default constructor. Initializes {@code String} fields to
     * {@code null} and allocates the {@link Commerce#dailySales} matrix,
     * an empty {@link Commerce#stock} array, and an empty {@link Commerce#employees}
     * array with default capacity.
     */
    public Commerce() {
        this.name = null;
        this.address = null;
        this.identifier = null;
        this.dailySales = new double[MONTHS][DAYS_PER_MONTH];
        this.stock = new double[0];
        this.employees = new Employee[DEFAULT_EMPLOYEE_CAPACITY];
        this.employeeCount = 0;
    }

    /**
     * Parameterised constructor.
     *
     * @param name The commercial name
     * @param address The physical address
     * @param identifier The tax identification code
     */
    public Commerce(String name, String address, String identifier) {
        this.name = name;
        this.address = address;
        this.identifier = identifier;
        this.dailySales = new double[MONTHS][DAYS_PER_MONTH];
        this.stock = new double[0];
        this.employees = new Employee[DEFAULT_EMPLOYEE_CAPACITY];
        this.employeeCount = 0;
    }

    /**
     * Parameterised constructor with explicit stock size.
     *
     * @param name The commercial name
     * @param address The physical address
     * @param identifier The tax identification code
     * @param stockSize The number of distinct products tracked
     */
    public Commerce(String name, String address, String identifier, int stockSize) {
        this.name = name;
        this.address = address;
        this.identifier = identifier;
        this.dailySales = new double[MONTHS][DAYS_PER_MONTH];
        this.stock = new double[stockSize];
        this.employees = new Employee[DEFAULT_EMPLOYEE_CAPACITY];
        this.employeeCount = 0;
    }

    /**
     * Copy constructor. Creates a deep copy of the given
     * {@link Commerce}.
     *
     * @param other The {@link Commerce} instance to copy
     */
    public Commerce(Commerce other) {
        this.name = other.name;
        this.address = other.address;
        this.identifier = other.identifier;

        // Deep-copy dailySales matrix
        this.dailySales = new double[MONTHS][DAYS_PER_MONTH];
        for (int i = 0; i < MONTHS; i++) {
            for  (int j = 0; j < DAYS_PER_MONTH; j++) {
                this.dailySales[i][j] = other.dailySales[i][j];
            }
        }

        // Deep-copy stock array
        this.stock = new double[other.stock.length];
        for (int i = 0; i < stock.length; i++) {
            this.stock[i] = other.stock[i];
        }

        // Deep-copy employees array
        this.employees = new Employee[other.employees.length];
        for (int i = 0; i < other.employeeCount; i++) {
            this.employees[i] = other.employees[i].clone();
        }
        this.employeeCount = other.employeeCount;
    }

    /**
     * Returns the commercial name of the business.
     *
     * @return The name {@code String}
     */
    public String getName() { return name; }

    /**
     * Sets the commercial name of the business.
     *
     * @param name The new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the physical address of the business.
     *
     * @return The address {@code String}
     */
    public String getAddress() { return address; }

    /**
     * Sets the physical address of the business.
     *
     * @param address The new address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Returns the tax identification code.
     *
     * @return The identifier {@code String}
     */
    public String getIdentifier() { return identifier; }

    /**
     * Sets the tax identification code (CIF).
     *
     * @param identifier The new identifier value
     */
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    /**
     * Returns the stock array.
     *
     * @return The stock array
     */
    public double[] getStock() { return stock; }

    /**
     * Sets the stock array.
     *
     * @param stock The new stock array
     */
    public void setStock(double[] stock) { this.stock = stock; }

    /**
     * Returns the employees array.
     *
     * @return The array of current employees
     */
    public Employee[] getEmployees() {
        Employee[] employees = new Employee[employeeCount];
        for (int i = 0; i < this.employeeCount; i++) {
            employees[i] = this.employees[i].clone();
        }
        return employees;
    }

    /**
     * Returns the number of employees currently registered.
     *
     * @return The employee count
     */
    public int getEmployeeCount() { return employeeCount; }

    /**
     * Calculates and returns the total sum of all values stored in
     * the {@link Commerce#dailySales} matrix.
     *
     * @return The total sales across all months and days
     */
    public double totalSales() {
        double total = 0.0;
        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS_PER_MONTH; d++) {
                total += dailySales[m][d];
            }
        }
        return total;
    }

    /**
     * Returns the sum of all sales recorded for the specified month.
     *
     * @param month The 0-based month index (0 = January, 11 = December)
     * @return The total sales for the given month
     */
    public double salesInMonth(int month) {
        double total = 0.0;
        for (int d = 0; d < DAYS_PER_MONTH; d++) {
            total += dailySales[month][d];
        }
        return total;
    }

    /**
     * Returns the 0-based index of the month with the highest total
     * sales. Uses {@link #salesInMonth(int)} to compute each monthly
     * total.
     *
     * @return The index of the top-sales month (0–11)
     */
    public int topSalesMonth() {
        int topMonth = 0;
        double topSales = salesInMonth(0);
        for (int m = 1; m < MONTHS; m++) {
            double s = salesInMonth(m);
            if (s > topSales) {
                topSales = s;
                topMonth = m;
            }
        }
        return topMonth;
    }

    /**
     * Adds the given sale amount to the specified month and day.
     *
     * @param month The 0-based month index (0–11)
     * @param day The 0-based day index (0–30)
     * @param amount The sale amount to add
     */
    public void updateSales(int month, int day, double amount) {
        dailySales[month][day] += amount;
    }

    /**
     * Adds the given sale amount to the current date's cell in the
     * {@link Commerce#dailySales} matrix. Uses {@link LocalDate#now()} to
     * determine today's month and day.
     *
     * @param amount The sale amount to add
     */
    public void updateSales(double amount) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue() - 1; // 0-based
        int day = today.getDayOfMonth() - 1;   // 0-based
        dailySales[month][day] += amount;
    }

    /**
     * Adds a new employee to the commerce. If the internal array is
     * full, its size is doubled before inserting the new employee.
     *
     * @param employee The {@link Employee} to hire
     */
    public void hireEmployee(Employee employee) {
        if (employeeCount == employees.length) {
            Employee[] newEmployees = new Employee[employees.length * 2];
            for (int i = 0; i < employees.length; i++) {
                newEmployees[i] = employees[i];
            }
            employees = newEmployees;
        }
        employees[employeeCount++] = employee;
    }

    /**
     * Removes the employee with the given identifier from the commerce,
     * compacts the array, and returns the removed employee.
     *
     * @param identifier The identifier of the employee to fire
     * @return The removed {@link Employee}, or {@code null} if not found
     */
    public Employee fireEmployee(String identifier) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getIdentifier().equals(identifier)) {
                Employee removed = employees[i];
                // Compact: shift left
                for (int j = i; j < employeeCount - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--employeeCount] = null;
                return removed;
            }
        }
        return null;
    }

    /**
     * Returns a formatted {@code String} showing the total sales per month.
     *
     * @return The monthly sales summary string
     */
    public String toStringMonthlySales() {
        StringBuilder sb = new StringBuilder("Monthly Sales:\n");
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        for (int m = 0; m < MONTHS; m++) {
            sb.append(String.format("  %s: %.2f%n", months[m], salesInMonth(m)));
        }
        return sb.toString();
    }

    /**
     * Returns a formatted {@code String} showing the sales for every
     * month–day combination that has a non-zero value.
     *
     * @return The daily sales detail {@code String}
     */
    public String toStringDailySales() {
        StringBuilder sb = new StringBuilder("Daily sales:\n");
        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS_PER_MONTH; d++) {
                if (dailySales[m][d] > 0) {
                    sb.append(String.format("  Month %02d, day %02d: %.2f%n",
                              m + 1, d + 1, dailySales[m][d]));
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns a human-readable representation of this {@code Commerce},
     * including name, address, identifier, and employee count.
     *
     * @return formatted commerce string
     */
    @Override
    public String toString() {
        return "Commerce{name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", identifier='" + identifier + '\'' +
               ", employees=" + employeeCount +
               '}';
    }

    /**
     * Compares this commerce to another object for equality based on tax identification.
     *
     * @param object the object to compare against
     * @return {@code true} if both objects share the same identifier; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Commerce other = (Commerce) object;
        return identifier != null && identifier.equals(other.identifier);
    }

    /**
     * Creates and returns a deep copy of this {@link Commerce}.
     * Subclasses should override this method to include their own
     * additional fields.
     *
     * @return A cloned {@link Commerce} instance
     * @throws CloneNotSupportedException If cloning is not supported
     */
    @Override
    public Commerce clone() throws CloneNotSupportedException {
        Commerce cloned = (Commerce) super.clone();
        // Deep-copy dailySales
        cloned.dailySales = new double[MONTHS][DAYS_PER_MONTH];
        for (int i = 0; i < MONTHS; i++) {
            for (int  j = 0; j < DAYS_PER_MONTH; j++) {
                cloned.dailySales[i][j] = dailySales[i][j];
            }
        }
        // Deep-copy stock
        cloned.stock = new double[stock.length];
        for (int i = 0; i < stock.length; i++) {
            cloned.stock[i] = this.stock[i];
        }
        // Deep-copy employees
        cloned.employees = new Employee[this.employees.length];
        for (int i = 0; i < this.employeeCount; i++) {
            cloned.employees[i] = this.employees[i].clone();
        }
        return cloned;
    }
}
