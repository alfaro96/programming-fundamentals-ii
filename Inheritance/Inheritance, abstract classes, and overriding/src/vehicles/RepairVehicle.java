package vehicles;

import commerces.CarDealership;

/**
 * Represents a vehicle brought to a {@link CarDealership} for repair.
 *
 * <p>Extends {@link Vehicle} with repair-specific attributes: damage
 * description, number plate, repair status, and priority level
 * (where {@code 1} is the highest priority and {@code 3} is the lowest).</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Vehicle
 * @see CarDealership
 */
public class RepairVehicle extends Vehicle {

    /** Description of the damage the vehicle has sustained. */
    private String damage;

    /** Number plate that uniquely identifies the vehicle. */
    private String numberPlate;

    /** Whether the vehicle has already been repaired. */
    private boolean repaired;

    /**
     * Repair priority of the vehicle.
     * Valid values are {@code 1} (highest) to {@code 3} (lowest).
     */
    private int priority;

    /**
     * Default constructor. Delegates to the parent default constructor
     * and sets all fields to their default values.
     */
    public RepairVehicle() {
        super();
        this.damage = null;
        this.numberPlate = null;
        this.repaired = false;
        this.priority = 3;
    }

    /**
     * Parameterised constructor. Initializes the vehicle's inherited
     * fields via {@link Vehicle#Vehicle(String, String)} and sets all
     * repair-specific attributes.
     *
     * @param brand The vehicle brand
     * @param model The vehicle model
     * @param damage The damage description
     * @param numberPlate The number plate identifier
     * @param repaired The initial repair status
     * @param priority The repair priority (1–3, where 1 is the highest)
     */
    public RepairVehicle(String brand, String model,
                         String damage, String numberPlate,
                         boolean repaired, int priority) {
        super(brand, model);
        this.damage = damage;
        this.numberPlate = numberPlate;
        this.repaired = repaired;
        setPriority(priority); // Validation via setter
    }

    /**
     * Copy constructor. Creates a deep copy of the given
     * {@link RepairVehicle}.
     *
     * @param other The instance to copy
     */
    public RepairVehicle(RepairVehicle other) {
        super(other);
        this.damage = other.damage;
        this.numberPlate = other.numberPlate;
        this.repaired = other.repaired;
        this.priority = other.priority;
    }

    /**
     * Returns the damage description.
     *
     * @return The damage {@code String}
     */
    public String getDamage() { return damage; }

    /**
     * Sets the damage description.
     *
     * @param damage The new damage description
     */
    public void setDamage(String damage) { this.damage = damage; }

    /**
     * Returns the number plate of the vehicle.
     *
     * @return The number plate {@code String}
     */
    public String getNumberPlate() { return numberPlate; }

    /**
     * Sets the number plate of the vehicle.
     *
     * @param numberPlate The new number plate
     */
    public void setNumberPlate(String numberPlate) { this.numberPlate = numberPlate; }

    /**
     * Returns whether the vehicle has been repaired.
     *
     * @return {@code true} if the vehicle is repaired; {@code false} otherwise
     */
    public boolean isRepaired() { return repaired; }

    /**
     * Sets the repair status of the vehicle.
     *
     * @param repaired {@code true} to mark as repaired; {@code false} otherwise
     */
    public void setRepaired(boolean repaired) { this.repaired = repaired; }

    /**
     * Returns the repair priority (1–3).
     *
     * @return The priority value
     */
    public int getPriority() { return priority; }

    /**
     * Sets the repair priority of the vehicle. The value must be
     * between {@code 1} (highest) and {@code 3} (lowest) inclusive.
     * Out-of-range values are clamped silently.
     *
     * @param priority The new priority value (1–3)
     */
    public void setPriority(int priority) {
        if (priority < 1) this.priority = 1;
        else if (priority > 3) this.priority = 3;
        else this.priority = priority;
    }

    /**
     * Returns a human-readable representation of this
     * {@link RepairVehicle}, including the inherited vehicle fields.
     *
     * @return Formatted {@code String} with all repair vehicle attributes
     */
    @Override
    public String toString() {
        return "RepairVehicle{" +
               super.toString() +
               ", damage='" + damage + '\'' +
               ", numberPlate='" + numberPlate + '\'' +
               ", repaired=" + repaired +
               ", priority=" + priority +
               '}';
    }

    /**
     * Compares this repair vehicle to another object for equality.
     * Two repair vehicles are equal if their parent fields match,
     * and they share the same number plate.
     *
     * @param object the object to compare against
     * @return {@code true} if equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        RepairVehicle other = (RepairVehicle) object;
        return numberPlate != null && numberPlate.equals(other.numberPlate);
    }

    /**
     * Creates and returns a deep copy of this {@link RepairVehicle}.
     *
     * @return A cloned {@link RepairVehicle} instance
     */
    @Override
    public RepairVehicle clone() {
        try {
            return (RepairVehicle) super.clone();
        } catch (CloneNotSupportedException e) {
            return new RepairVehicle(this);
        }
    }
}
