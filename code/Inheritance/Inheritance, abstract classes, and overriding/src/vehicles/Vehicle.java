package vehicles;

/**
 * Abstract base class representing a generic vehicle.
 *
 * <p>Provides common attributes (brand and model) shared by all vehicle
 * subtypes ({@link RepairVehicle} and {@link SaleVehicle}). This class
 * cannot be instantiated directly.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see RepairVehicle
 * @see SaleVehicle
 */
public abstract class Vehicle implements Cloneable {

    /** Brand (manufacturer) of the vehicle. */
    private String brand;

    /** Model name of the vehicle. */
    private String model;

    /**
     * Default constructor. Initializes brand and model to {@code null}.
     */
    public Vehicle() {
        this.brand = null;
        this.model = null;
    }

    /**
     * Parameterised constructor.
     *
     * @param brand The vehicle brand
     * @param model The vehicle model
     */
    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    /**
     * Copy constructor. Creates a new {@code Vehicle} with the same
     * brand and model as the provided instance.
     *
     * @param other The {@code Vehicle} to copy
     */
    public Vehicle(Vehicle other) {
        this.brand = other.brand;
        this.model = other.model;
    }

    /**
     * Returns the brand of the vehicle.
     *
     * @return The brand string
     */
    public String getBrand() { return brand; }

    /**
     * Sets the brand of the vehicle.
     *
     * @param brand The new brand value
     */
    public void setBrand(String brand) { this.brand = brand; }

    /**
     * Returns the model of the vehicle.
     *
     * @return The model string
     */
    public String getModel() { return model; }

    /**
     * Sets the model of the vehicle.
     *
     * @param model The new model value
     */
    public void setModel(String model) { this.model = model; }

    /**
     * Returns a human-readable string of the base vehicle properties.
     * Subclasses should call {@code super.toString()} and append their
     * own fields.
     *
     * @return {@code String} containing brand and model
     */
    @Override
    public String toString() {
        return "Vehicle{brand='" + brand + "', model='" + model + "'}";
    }

    /**
     * Compares this vehicle to another object for equality based on
     * brand and model.
     *
     * @param object The object to compare against
     * @return {@code true} if brand and model match; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vehicle other = (Vehicle) object;
        return (brand == null ? other.brand == null : brand.equals(other.brand)) &&
               (model == null ? other.model == null : model.equals(other.model));
    }

    /**
     * Creates and returns a deep copy of this {@link Vehicle}.
     * Concrete subclasses should override this method to clone their
     * own fields as well.
     *
     * @return A cloned {@code Vehicle} instance
     * @throws CloneNotSupportedException If cloning is not supported
     */
    @Override
    public Vehicle clone() throws CloneNotSupportedException {
        return (Vehicle) super.clone();
    }
}
