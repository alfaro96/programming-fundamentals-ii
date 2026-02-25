package vehicles;

import commerces.CarDealership;

/**
 * Represents a vehicle available for sale at a {@link CarDealership}.
 *
 * <p>Extends {@link Vehicle} with sale-specific attributes: sale price,
 * discount percentage, and a unique catalogue identifier.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Vehicle
 * @see CarDealership
 */
public class SaleVehicle extends Vehicle {

    /** Listed sale price of the vehicle. */
    private double price;

    /** Discount applied to the listed price (percentage). */
    private int discount;

    /** Unique catalogue identifier of the vehicle. */
    private String identifier;

    /**
     * Default constructor. Delegates to the parent default constructor
     * and sets numeric fields to {@code 0}.
     */
    public SaleVehicle() {
        super();
        this.price = 0.0;
        this.discount = 0;
        this.identifier = null;
    }

    /**
     * Parameterised constructor. Initializes the inherited vehicle
     * fields and all sale-specific attributes.
     *
     * @param brand The vehicle brand
     * @param model The vehicle model
     * @param price The listed sale price
     * @param discount The discount percentage
     * @param identifier The unique catalogue identifier
     */
    public SaleVehicle(String brand, String model,
                       double price, int discount, String identifier) {
        super(brand, model);
        this.price = price;
        this.discount = discount;
        this.identifier = identifier;
    }

    /**
     * Copy constructor. Creates a deep copy of the given
     * {@link SaleVehicle}.
     *
     * @param other The instance to copy
     */
    public SaleVehicle(SaleVehicle other) {
        super(other);
        this.price = other.price;
        this.discount = other.discount;
        this.identifier = other.identifier;
    }

    /**
     * Returns the listed sale price.
     *
     * @return The price
     */
    public double getPrice() { return price; }

    /**
     * Sets the listed sale price.
     *
     * @param price The new price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Returns the discount percentage.
     *
     * @return The discount
     */
    public int getDiscount() { return discount; }

    /**
     * Sets the discount percentage.
     *
     * @param discount The new discount percentage
     */
    public void setDiscount(int discount) { this.discount = discount; }

    /**
     * Returns the unique catalogue identifier of this vehicle.
     *
     * @return The identifier {@code String}
     */
    public String getIdentifier() { return identifier; }

    /**
     * Sets the catalogue identifier of this vehicle.
     *
     * @param identifier The new identifier
     */
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    /**
     * Returns a human-readable representation of this
     * {@link SaleVehicle}, including the inherited vehicle fields.
     *
     * @return The formatted {@code String} with all sale vehicle attributes
     */
    @Override
    public String toString() {
        return "SaleVehicle{" +
               super.toString() +
               ", price=" + price +
               ", discount=" + discount +
               ", identifier='" + identifier + '\'' +
               '}';
    }

    /**
     * Compares this sale vehicle to another object for equality.
     * Two sale vehicles are equal if their parent fields match,
     * and they share the same identifier.
     *
     * @param object The object to compare against
     * @return {@code true} if equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        SaleVehicle other = (SaleVehicle) object;
        return identifier != null && identifier.equals(other.identifier);
    }

    /**
     * Creates and returns a deep copy of this {@link SaleVehicle}.
     *
     * @return A cloned {@link SaleVehicle} instance
     */
    @Override
    public SaleVehicle clone() {
        try {
            return (SaleVehicle) super.clone();
        } catch (CloneNotSupportedException e) {
            return new SaleVehicle(this);
        }
    }
}
