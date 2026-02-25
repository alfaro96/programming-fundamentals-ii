package commerces;

/**
 * Represents a restaurant, a type of {@link Commerce}.
 *
 * <p>Extends {@link Commerce} with restaurant-specific attributes:
 * a weekly menu array, number of tables, and seating capacity.</p>
 *
 * <p>The {@link Restaurant#dailyMenus} array has exactly {@value #DAYS_IN_WEEK}
 * positions (one per day, starting at index 0 for Monday).</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Commerce
 */
public class Restaurant extends Commerce {

    /** Number of days in a week (size of {@code dailyMenus}). */
    private static final int DAYS_IN_WEEK = 7;

    /**
     * Array of menus, one per day of the week.
     * Index 0 corresponds to Monday; index 6 to Sunday.
     */
    private String[] dailyMenus;

    /** Total number of tables in the restaurant. */
    private int numTables;

    /** Total seating capacity of the restaurant. */
    private int capacity;

    /**
     * Default constructor. Delegates to the parent default constructor
     * and initialises {@link Restaurant#dailyMenus} with empty strings.
     */
    public Restaurant() {
        super();
        this.dailyMenus = new String[DAYS_IN_WEEK];
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            this.dailyMenus[i] = "";
        }
        this.numTables = 0;
        this.capacity = 0;
    }

    /**
     * Parameterised constructor.
     *
     * @param name The commercial name
     * @param address The physical address
     * @param identifier The tax identification code
     * @param numTables The number of tables
     * @param capacity The seating capacity
     */
    public Restaurant(String name, String address, String identifier,
                      int numTables, int capacity) {
        super(name, address, identifier);
        this.dailyMenus = new String[DAYS_IN_WEEK];
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            this.dailyMenus[i] = "";
        }
        this.numTables = numTables;
        this.capacity = capacity;
    }

    /**
     * Copy constructor. Creates a deep copy of the given
     * {@link Restaurant}.
     *
     * @param other the instance to copy
     */
    public Restaurant(Restaurant other) {
        super(other);
        this.dailyMenus = new String[DAYS_IN_WEEK];
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            this.dailyMenus[i] = other.dailyMenus[i];
        }
        this.numTables = other.numTables;
        this.capacity = other.capacity;
    }

    /**
     * Returns the number of tables.
     *
     * @return The number of tables
     */
    public int getNumTables() { return numTables; }

    /**
     * Sets the number of tables.
     *
     * @param numTables The new table count
     */
    public void setNumTables(int numTables) { this.numTables = numTables; }

    /**
     * Returns the seating capacity.
     *
     * @return The total capacity
     */
    public int getCapacity() { return capacity; }

    /**
     * Sets the seating capacity.
     *
     * @param capacity The new capacity value
     */
    public void setCapacity(int capacity) { this.capacity = capacity; }

    /**
     * Stores the given menu {@code String} for the specified day of the week.
     *
     * @param menu The menu description
     * @param day The 0-based day index (0 = Monday, 6 = Sunday)
     */
    public void setDailyMenu(String menu, int day) {
        if (day >= 0 && day < DAYS_IN_WEEK) {
            dailyMenus[day] = menu;
        }
    }

    /**
     * Returns the menu for the specified day of the week.
     *
     * @param day The 0-based day index (0 = Monday, 6 = Sunday)
     * @return The menu {@code String}, or {@code null} if the index is invalid
     */
    public String getDailyMenu(int day) {
        if (day >= 0 && day < DAYS_IN_WEEK) {
            return dailyMenus[day];
        }
        return null;
    }

    /**
     * Returns a human-readable representation of this
     * {@link Restaurant}, including inherited commerce fields and
     * restaurant-specific attributes.
     *
     * @return The formatted restaurant {@code String}
     */
    @Override
    public String toString() {
        String dailyMenus = "";
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            dailyMenus += this.dailyMenus[i];
        }
        return "Restaurant{" +
               super.toString() +
               ", numTables=" + numTables +
               ", capacity=" + capacity +
               ", dailyMenus=" + dailyMenus +
               '}';
    }

    /**
     * Compares this restaurant to another object for equality.
     * Two restaurants are equal if they share the same memory
     * reference <em>or</em> if they have exactly the same daily menus.
     *
     * @param object The object to compare against
     * @return {@code true} if equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Restaurant other = (Restaurant) object;
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            if (!this.dailyMenus[i].equals(other.dailyMenus[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates and returns a deep copy of this {@link Restaurant}.
     *
     * @return A cloned {@link Restaurant} instance
     */
    @Override
    public Restaurant clone() {
        try {
            Restaurant cloned = (Restaurant) super.clone();
            cloned.dailyMenus = new String[DAYS_IN_WEEK];
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                cloned.dailyMenus[i] = dailyMenus[i];
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            return new Restaurant(this);
        }
    }
}
