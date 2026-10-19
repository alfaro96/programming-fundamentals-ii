package associations;

import commerces.CarDealership;
import commerces.Commerce;
import commerces.Employee;
import commerces.Restaurant;

/**
 * Represents an association of {@link Commerce} objects.
 *
 * <p>Groups multiple commerces (of any subtype) together under a
 * common address and president. Provides collective management
 * operations such as adding a commerce, replacing a
 * {@link Restaurant} with a {@link CarDealership}, and listing all
 * employees of member dealerships.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Commerce
 * @see Restaurant
 * @see CarDealership
 */
public class CommerceAssociation {

    /** Default maximum number of commerces in the association. */
    private static final int DEFAULT_CAPACITY = 20;

    /** Array holding the member commerces. */
    private Commerce[] commerces;

    /** Number of commerces currently registered. */
    private int commerceCount;

    /** Physical address of the association's headquarters. */
    private String address;

    /** Name of the president of the association. */
    private String president;

    /**
     * Default constructor. Creates an empty commerces array with a
     * capacity of {@value #DEFAULT_CAPACITY}.
     */
    public CommerceAssociation() {
        this.commerces = new Commerce[DEFAULT_CAPACITY];
        this.commerceCount = 0;
        this.address = null;
        this.president = null;
    }

    /**
     * Parameterised constructor.
     *
     * @param size The maximum number of member commerces
     * @param address The address of the association
     * @param president The name of the association president
     */
    public CommerceAssociation(int size, String address, String president) {
        this.commerces = new Commerce[size];
        this.commerceCount = 0;
        this.address = address;
        this.president = president;
    }

    /**
     * Copy constructor. Creates a copy of the given
     * {@link CommerceAssociation}, with an array of its own; the commerces in it are shared.
     *
     * @param other The instance to copy
     */
    public CommerceAssociation(CommerceAssociation other) {
        this.address = other.address;
        this.president = other.president;
        this.commerces = new Commerce[other.commerces.length];
        for (int i = 0; i < other.commerceCount; i++) {
            this.commerces[i] = other.commerces[i];
        }
        this.commerceCount = other.commerceCount;
    }

    /**
     * Returns a shallow copy of the employee array containing only
     * the valid employees (no trailing {@code null}s).
     *
     * @return The array of member {@link Commerce} objects
     */
    public Commerce[] getCommerces() {
        Commerce[] copy = new Commerce[this.commerceCount];
        for (int i = 0; i < this.commerceCount; i++) {
            copy[i] = this.commerces[i];
        }
        return copy;
    }

    /**
     * Returns the address of the association.
     *
     * @return The address {@code String}
     */
    public String getAddress() { return this.address; }

    /**
     * Sets the address of the association.
     *
     * @param address The new address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Returns the name of the association president.
     *
     * @return The president's name
     */
    public String getPresident() { return this.president; }

    /**
     * Sets the name of the association president.
     *
     * @param president The new president name
     */
    public void setPresident(String president) { this.president = president; }

    /**
     * Adds a {@link Commerce} to the association. The commerce will
     * <em>not</em> be added if:
     * <ul>
     *   <li>it is already present in the array (checked via
     *       {@link Commerce#equals(Object)}), or</li>
     *   <li>the array has reached its maximum capacity.</li>
     * </ul>
     *
     * @param commerce The commerce to add
     */
    public void addCommerce(Commerce commerce) {
        if (this.commerceCount >= this.commerces.length) return;
        for (int i = 0; i < this.commerceCount; i++) {
            if (this.commerces[i].equals(commerce)) return;
        }
        this.commerces[this.commerceCount++] = commerce;
    }

    /**
     * Finds the given {@link Restaurant} in the association and
     * replaces it with the provided {@link CarDealership}. The search
     * uses {@link Restaurant#equals(Object)} for comparison.
     * If the restaurant is not found, no change is made.
     *
     * @param restaurant The restaurant to look for and replace
     * @param carDealership The car dealership to put in its place
     */
    public void replaceRestaurant(Restaurant restaurant, CarDealership carDealership) {
        for (int i = 0; i < this.commerceCount; i++) {
            if (this.commerces[i] instanceof Restaurant &&
                this.commerces[i].equals(restaurant)) {
                this.commerces[i] = carDealership;
                return;
            }
        }
    }

    /**
     * Prints to standard output the details of all employees working
     * in the car dealerships that are members of this association.
     *
     * <p>For each {@link CarDealership} found, the method prints its
     * name followed by each employee's {@link Employee#toString()} output.</p>
     */
    public void printDealershipEmployees() {
        for (int i = 0; i < this.commerceCount; i++) {
            if (this.commerces[i] instanceof CarDealership cd) {
                System.out.println("=== CarDealership: " + cd.getName() + " ===");
                for (Employee e : cd.getEmployees()) {
                    System.out.println("  " + e);
                }
            }
        }
    }

    /**
     * Returns a human-readable representation of this
     * {@link CommerceAssociation}.
     *
     * @return The formatted association {@code String}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CommerceAssociation{");
        sb.append("address='").append(this.address).append('\'');
        sb.append(", president='").append(this.president).append('\'');
        sb.append(", commerces=[");
        for (int i = 0; i < this.commerceCount; i++) {
            sb.append(this.commerces[i].getName());
            if (i < this.commerceCount - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }

    /**
     * Compares this association to another object for equality based
     * on address and president.
     *
     * @param object The object to compare against
     * @return {@code true} if address and president are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        CommerceAssociation other = (CommerceAssociation) object;
        boolean sameAddress = (this.address == null ? other.address == null
                                               : this.address.equals(other.address));
        boolean samePresident = (this.president == null ? other.president == null
                                                   : this.president.equals(other.president));
        return sameAddress && samePresident;
    }

}
