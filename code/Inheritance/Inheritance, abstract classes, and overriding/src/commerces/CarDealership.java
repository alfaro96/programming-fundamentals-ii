package commerces;

import vehicles.RepairVehicle;
import vehicles.SaleVehicle;

/**
 * Represents a car dealership, a type of {@link Commerce}.
 *
 * <p>Extends {@link Commerce} with two managed vehicle collections:
 * an array of {@link SaleVehicle} objects (vehicles available for
 * purchase) and an array of {@link RepairVehicle} objects (vehicles
 * awaiting or undergoing repair). Vehicles for repair are kept ordered
 * by priority, with priority {@code 1} at the front.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Commerce
 * @see SaleVehicle
 * @see RepairVehicle
 */
public class CarDealership extends Commerce {

    /** Default maximum number of vehicles in each array. */
    private static final int DEFAULT_CAPACITY = 20;

    /** Array of vehicles currently available for sale. */
    private SaleVehicle[] vehiclesOnSale;

    /** Number of vehicles currently on sale. */
    private int saleCount;

    /** Array of vehicles currently in for repair, ordered by priority. */
    private RepairVehicle[] vehiclesForRepair;

    /** Number of vehicles currently in for repair. */
    private int repairCount;

    /**
     * Default constructor. Creates both vehicle arrays with a capacity
     * of {@value #DEFAULT_CAPACITY}.
     */
    public CarDealership() {
        super();
        this.vehiclesOnSale = new SaleVehicle[DEFAULT_CAPACITY];
        this.saleCount = 0;
        this.vehiclesForRepair = new RepairVehicle[DEFAULT_CAPACITY];
        this.repairCount = 0;
    }

    /**
     * Parameterised constructor with a custom array capacity.
     *
     * @param name The commercial name
     * @param address The physical address
     * @param identifier The tax identification code
     * @param capacity The maximum number of vehicles per array
     */
    public CarDealership(String name, String address, String identifier, int capacity) {
        super(name, address, identifier);
        this.vehiclesOnSale = new SaleVehicle[capacity];
        this.saleCount = 0;
        this.vehiclesForRepair = new RepairVehicle[capacity];
        this.repairCount = 0;
    }

    /**
     * Parameterised constructor using the default capacity.
     *
     * @param name The commercial name
     * @param address The physical address
     * @param identifier The tax identification code
     */
    public CarDealership(String name, String address, String identifier) {
        this(name, address, identifier, DEFAULT_CAPACITY);
    }

    /**
     * Copy constructor. Creates a copy of the given
     * {@link CarDealership}.
     *
     * @param other The instance to copy
     */
    public CarDealership(CarDealership other) {
        super(other);
        this.vehiclesOnSale = new SaleVehicle[other.vehiclesOnSale.length];
        for (int i = 0; i < other.saleCount; i++) {
            this.vehiclesOnSale[i] = other.vehiclesOnSale[i];
        }
        this.saleCount = other.saleCount;

        this.vehiclesForRepair = new RepairVehicle[other.vehiclesForRepair.length];
        for (int i = 0; i < other.repairCount; i++) {
            this.vehiclesForRepair[i] = other.vehiclesForRepair[i];
        }
        this.repairCount = other.repairCount;
    }

    /**
     * Returns a copy of the valid portion of the sale vehicles array.
     *
     * @return An array of {@link SaleVehicle} currently on sale
     */
    public SaleVehicle[] getVehiclesOnSale() {
        SaleVehicle[] copy = new SaleVehicle[this.vehiclesOnSale.length];
        for (int i = 0; i < this.saleCount; i++) {
            copy[i] = this.vehiclesOnSale[i];
        }
        return copy;
    }

    /**
     * Returns a copy of the valid portion of the repair vehicles array.
     *
     * @return An array of {@link RepairVehicle} awaiting or undergoing repair
     */
    public RepairVehicle[] getVehiclesForRepair() {
        RepairVehicle[] copy = new RepairVehicle[this.vehiclesForRepair.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = this.vehiclesForRepair[i];
        }
        return copy;
    }

    /**
     * Adds a {@link RepairVehicle} to the repair queue, maintaining
     * the order by priority (lower number = higher priority, stored
     * first). If the array is full the vehicle is not added.
     *
     * @param vehicle The vehicle to add to the repair queue
     */
    public void addVehicleForRepair(RepairVehicle vehicle) {
        if (this.repairCount >= this.vehiclesForRepair.length) return;

        // Find insertion position (first vehicle with lower priority)
        int pos = this.repairCount;
        for (int i = 0; i < this.repairCount; i++) {
            if (this.vehiclesForRepair[i].getPriority() > vehicle.getPriority()) {
                pos = i;
                break;
            }
        }
        // Shift right to make room
        for (int i = this.repairCount; i > pos; i--) {
            this.vehiclesForRepair[i] = this.vehiclesForRepair[i - 1];
        }
        this.vehiclesForRepair[pos] = vehicle;
        this.repairCount++;
    }

    /**
     * Marks the vehicle with the given number plate as repaired.
     *
     * @param numberPlate The number plate of the vehicle to mark repaired
     */
    public void repairVehicle(String numberPlate) {
        for (int i = 0; i < this.repairCount; i++) {
            if (this.vehiclesForRepair[i].getNumberPlate().equals(numberPlate)) {
                this.vehiclesForRepair[i].setRepaired(true);
                return;
            }
        }
    }

    /**
     * Removes a repaired vehicle from the queue and returns it.
     * The array is compacted after removal. If the vehicle is not
     * found or has not been repaired, {@code null} is returned.
     *
     * @param numberPlate The number plate of the vehicle to pick up
     * @return The picked-up {@link RepairVehicle}, or {@code null}
     */
    public RepairVehicle pickupVehicle(String numberPlate) {
        for (int i = 0; i < this.repairCount; i++) {
            RepairVehicle rv = this.vehiclesForRepair[i];
            if (rv.getNumberPlate().equals(numberPlate) && rv.isRepaired()) {
                // Compact
                for (int j = i; j < this.repairCount - 1; j++) {
                    this.vehiclesForRepair[j] = this.vehiclesForRepair[j + 1];
                }
                this.vehiclesForRepair[--this.repairCount] = null;
                return rv;
            }
        }
        return null;
    }

    /**
     * Adds a {@link SaleVehicle} to the array of vehicles on sale.
     * If the array is full the vehicle is not added.
     *
     * @param vehicle The vehicle to add
     */
    public void addSale(SaleVehicle vehicle) {
        if (this.saleCount < this.vehiclesOnSale.length) {
            this.vehiclesOnSale[this.saleCount++] = vehicle;
        }
    }

    /**
     * Sells the vehicle identified by the given catalogue identifier.
     * Removes it from the sale array (compacting afterwards), records
     * its price in today's sales via
     * {@link Commerce#updateSales(double)}, and returns it.
     * Returns {@code null} if the identifier is not found.
     *
     * @param identifier The catalogue identifier of the vehicle to sell
     * @return The sold {@link SaleVehicle}, or {@code null} if not found
     */
    public SaleVehicle sellVehicle(String identifier) {
        for (int i = 0; i < this.saleCount; i++) {
            if (this.vehiclesOnSale[i].getIdentifier().equals(identifier)) {
                SaleVehicle sold = this.vehiclesOnSale[i];
                // Compact
                for (int j = i; j < this.saleCount - 1; j++) {
                    this.vehiclesOnSale[j] = this.vehiclesOnSale[j + 1];
                }
                this.vehiclesOnSale[--this.saleCount] = null;
                this.updateSales(sold.getPrice());
                return sold;
            }
        }
        return null;
    }

    /**
     * Returns a formatted {@code String} listing all vehicles currently
     * available for sale.
     *
     * @return The sale vehicles list {@code String}
     */
    public String toStringVehiclesOnSale() {
        StringBuilder sb = new StringBuilder("Vehicles on sale:\n");
        for (int i = 0; i < this.saleCount; i++) {
            sb.append("  ").append(this.vehiclesOnSale[i]).append('\n');
        }
        return sb.toString();
    }

    /**
     * Returns a formatted {@code String} listing all vehicles currently in
     * the repair queue, in priority order.
     *
     * @return The repair vehicles list {@code String}
     */
    public String toStringVehiclesForRepair() {
        StringBuilder sb = new StringBuilder("Vehicles for repair:\n");
        for (int i = 0; i < this.repairCount; i++) {
            sb.append("  ").append(this.vehiclesForRepair[i]).append('\n');
        }
        return sb.toString();
    }

    /**
     * Returns a human-readable representation of this
     * {@link CarDealership}, including inherited commerce fields and
     * both vehicle arrays.
     *
     * @return The formatted car dealership {@code String}
     */
    @Override
    public String toString() {
        return "CarDealership{" +
               super.toString() +
               ", saleCount=" + this.saleCount +
               ", repairCount=" + this.repairCount +
               '}';
    }

    /**
     * Compares this car dealership to another object for equality.
     * Two dealerships are equal if they share the same memory
     * reference <em>or</em> if they have exactly the same vehicles
     * for repair and on sale (by content).
     *
     * @param object the object to compare against
     * @return {@code true} if equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        CarDealership other = (CarDealership) object;
        if (this.saleCount != other.saleCount || this.repairCount != other.repairCount) return false;
        for (int i = 0; i < this.saleCount; i++) {
            if (!this.vehiclesOnSale[i].equals(other.vehiclesOnSale[i])) return false;
        }
        for (int i = 0; i < this.repairCount; i++) {
            if (!this.vehiclesForRepair[i].equals(other.vehiclesForRepair[i])) return false;
        }
        return true;
    }

}
