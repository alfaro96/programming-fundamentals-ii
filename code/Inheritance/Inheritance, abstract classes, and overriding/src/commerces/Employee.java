package commerces;

/**
 * Represents an employee of a {@link Commerce}.
 *
 * <p>Stores personal and professional data for a single employee,
 * including identification, contact details, and employment information.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Commerce
 */
public class Employee implements Cloneable {

    /** National identification number of the employee. */
    private String identifier;

    /** Full name of the employee. */
    private String name;

    /** Home address of the employee. */
    private String address;

    /** Phone number of the employee. */
    private String phoneNumber;

    /** Email address of the employee. */
    private String email;

    /** Date on which the employee started working (formatted as a {@code String}). */
    private String startingDate;

    /** Job position or role of the employee within the commerce. */
    private String position;

    /** Monthly salary of the employee. */
    private double salary;

    /**
     * Default constructor. Initializes all {@code String} fields to
     * {@code null} and {@code salary} to {@code 0.0}.
     */
    public Employee() {
        this.identifier = null;
        this.name = null;
        this.address = null;
        this.phoneNumber = null;
        this.email = null;
        this.startingDate = null;
        this.position = null;
        this.salary = 0.0;
    }

    /**
     * Parameterised constructor. Initializes every field with the
     * provided values.
     *
     * @param identifier The national identification number
     * @param name The full name
     * @param address The home address
     * @param phoneNumber The phone number
     * @param email The email address
     * @param startingDate The employment start date
     * @param position The job position
     * @param salary The monthly salary
     */
    public Employee(String identifier, String name, String address,
                    String phoneNumber, String email,
                    String startingDate, String position,
                    double salary) {
        this.identifier = identifier;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startingDate = startingDate;
        this.position = position;
        this.salary = salary;
    }

    /**
     * Copy constructor. Creates a new {@link Employee} with the same
     * field values as the given instance.
     *
     * @param other the {@link Employee} to copy
     */
    public Employee(Employee other) {
        this.identifier = other.identifier;
        this.name = other.name;
        this.address = other.address;
        this.phoneNumber = other.phoneNumber;
        this.email = other.email;
        this.startingDate = other.startingDate;
        this.position = other.position;
        this.salary = other.salary;
    }

    /**
     * Returns the identifier of the employee.
     *
     * @return The identifier string
     */
    public String getIdentifier() { return identifier; }

    /**
     * Sets the identifier of the employee.
     *
     * @param identifier The new DNI value
     */
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    /**
     * Returns the full name of the employee.
     *
     * @return The employee's name
     */
    public String getName() { return name; }

    /**
     * Sets the full name of the employee.
     *
     * @param name The new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the home address of the employee.
     *
     * @return The address {@code String}
     */
    public String getAddress() { return address; }

    /**
     * Sets the home address of the employee.
     *
     * @param address The new address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Returns the phone number of the employee.
     *
     * @return The phone number {@code String}
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Sets the phone number of the employee.
     *
     * @param phoneNumber The new phone number
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Returns the email address of the employee.
     *
     * @return The email string
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the employee.
     *
     * @param email The new email address
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the employment start date.
     *
     * @return The starting date string
     */
    public String getStartingDate() { return startingDate; }

    /**
     * Sets the employment start date.
     *
     * @param startingDate The new starting date
     */
    public void setStartingDate(String startingDate) { this.startingDate = startingDate; }

    /**
     * Returns the job position of the employee.
     *
     * @return The position {@code String}
     */
    public String getPosition() { return position; }

    /**
     * Sets the job position of the employee.
     *
     * @param position The new position
     */
    public void setPosition(String position) { this.position = position; }

    /**
     * Returns the monthly salary of the employee.
     *
     * @return The salary value
     */
    public double getSalary() { return salary; }

    /**
     * Sets the monthly salary of the employee.
     *
     * @param salary The new salary value
     */
    public void setSalary(double salary) { this.salary = salary; }

    /**
     * Returns a human-readable representation of this {@code Employee}.
     *
     * @return formatted string containing all employee fields
     */
    @Override
    public String toString() {
        return "Employee{" +
               "Identifier='" + identifier + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", email='" + email + '\'' +
               ", startingDate='" + startingDate + '\'' +
               ", position='" + position + '\'' +
               ", salary=" + salary +
               '}';
    }

    /**
     * Compares this employee to another object for equality.
     * Two employees are equal if they have the same {@link Employee#identifier}.
     *
     * @param object The object to compare against
     * @return {@code true} if the objects share the same identifier; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee other = (Employee) object;
        return identifier != null && identifier.equals(other.identifier);
    }

    /**
     * Creates and returns a deep copy of this {@link Employee}.
     *
     * @return A cloned {@link Employee} instance
     */
    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Employee(this);
        }
    }
}
