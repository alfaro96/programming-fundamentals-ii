package com.realestate.management.model;

/**
 * Represents a storage unit ({@link Storage}) typically located in the basement.
 * <p>
 * This class manages the lifecycle of the storage unit ({@link Status#FREE} or {@link Status#SOLD}),
 * its physical dimensions, and size classification logic using a specific threshold.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Storage {

    /**
     * Defines the threshold in square meters to classify a storage unit as "Large".
     * <p>
     * Current value: {@code 7.0} square meters.
     * </p>
     */
    public static final double THRESHOLD = 7.0;

    /**
     * Defines the possible lifecycle states of a storage unit.
     * <ul>
     * <li>{@link #FREE}: The unit is available for purchase.</li>
     * <li>{@link #SOLD}: The unit has been permanently transferred to a buyer.</li>
     * </ul>
     */
    public enum Status {

        /**
         * The storage unit is available.
         */
        FREE,

        /**
         * The storage unit has been sold.
         */
        SOLD
    }

    /**
     * The current availability status of the storage unit.
     */
    private Status status;

    /**
     * The monetary value of the storage unit in euros.
     */
    private double price;

    /**
     * The surface area of the storage unit in square meters.
     */
    private double squareMeters;

    /**
     * The unique identification of the current owner.
     * It is {@code null} if the storage unit is {@link Status#FREE}.
     */
    private String buyerDni;

    /**
     * Creates a new {@link Storage} unit with the specified dimensions and price.
     * The initial status is set to {@link Status#FREE}.
     *
     * @param price The monetary value of the unit in euros.
     * @param squareMeters The surface area in square meters.
     */
    public Storage(double price, double squareMeters) {
        this.status = Status.FREE;
        this.price = price;
        this.squareMeters = squareMeters;
        this.buyerDni = null;
    }

    /**
     * Gets the current availability status.
     *
     * @return The status enum ({@link Status#FREE} or {@link Status#SOLD}).
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the storage unit manually.
     *
     * @param status The new {@link Status} to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the price of the storage unit.
     *
     * @return The price in euros.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the storage unit.
     *
     * @param price The new price in euros.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the surface area.
     *
     * @return The area in square meters.
     */
    public double getSquareMeters() {
        return this.squareMeters;
    }

    /**
     * Sets the surface area.
     *
     * @param squareMeters The new area in square meters.
     */
    public void setSquareMeters(double squareMeters) {
        this.squareMeters = squareMeters;
    }

    /**
     * Gets the identification of the current owner.
     *
     * @return The DNI string, or {@code null} if available.
     */
    public String getBuyerDni() {
        return this.buyerDni;
    }

    /**
     * Sets the buyer's DNI manually.
     *
     * @param buyerDni The identification string.
     */
    public void setBuyerDni(String buyerDni) {
        this.buyerDni = buyerDni;
    }

    /**
     * Determines if the storage unit is considered "Large".
     *
     * @return {@code true} if {@link #squareMeters} is greater than {@link #THRESHOLD} ({@code 7.0}).
     */
    public boolean isLarge() {
        return this.squareMeters > Storage.THRESHOLD;
    }

    /**
     * Checks if the storage unit is currently available for purchase.
     *
     * @return {@code true} if the status is {@link Status#FREE}.
     */
    public boolean isAvailable() {
        return this.status == Status.FREE;
    }

    /**
     * Verifies if the storage unit's surface area falls within a specific range.
     *
     * @param min The minimum surface area.
     * @param max The maximum surface area.
     * @return {@code true} if {@link #squareMeters} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesSurface(double min, double max) {
        return this.squareMeters >= min && this.squareMeters <= max;
    }

    /**
     * Verifies if the storage unit's price falls within a specific range.
     *
     * @param min The minimum price.
     * @param max The maximum price.
     * @return {@code true} if {@link #price} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesPrice(double min, double max) {
        return this.price >= min && this.price <= max;
    }

    /**
     * Filters the storage unit based on its size classification.
     *
     * @param filter The filter code:
     * <ul>
     * <li>{@code 0}: Any size (always returns {@code true}).</li>
     * <li>{@code 1}: Small units (returns {@code true} if NOT large).</li>
     * <li>{@code 2}: Large units (returns {@code true} if large).</li>
     * </ul>
     * @return {@code true} if the unit matches the filter criteria.
     */
    public boolean matchesSize(int filter) {
        return filter == 0
                || (filter == 1 && !this.isLarge())
                || (filter == 2 && this.isLarge());
    }

    /**
     * Marks the storage unit as {@link Status#SOLD} to a specific buyer.
     * Validates that the DNI is valid before proceeding.
     *
     * @param dni The identification of the buyer (cannot be {@code null} or empty).
     * @return {@code true} if the operation was successful, {@code false} if DNI was invalid.
     */
    public boolean sell(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            System.err.println("Error: A valid DNI is required to complete the sale.");
            return false;
        }
        this.status = Status.SOLD;
        this.buyerDni = dni;
        return true;
    }

    /**
     * Releases the storage unit, resetting it to its initial available state.
     * <p>
     * Sets status to {@link Status#FREE} and clears {@code buyerDni}.
     * </p>
     */
    public void release() {
        this.status = Status.FREE;
        this.buyerDni = null;
    }

    /**
     * Returns a single-character code representing the status.
     * Useful for grid visualizations.
     *
     * @return {@code "F"} for {@link Status#FREE}, {@code "S"} for {@link Status#SOLD}.
     */
    @Override
    public String toString() {
        return this.status == Status.FREE ? "F" : "S";
    }

    /**
     * Generates a detailed report of the storage unit's current state.
     * Includes status, price, dimensions, size classification, and buyer info if sold.
     *
     * @return A formatted string with all property details.
     */
    public String getDetails() {
        String sizeLabel = this.isLarge() ? "Large" : "Small";
        StringBuilder details = new StringBuilder();

        details.append(String.format("Status: %s | Price: %.2f € | %.2f m² | %s",
                this.status, this.price, this.squareMeters, sizeLabel));

        if (this.buyerDni != null && !this.buyerDni.isEmpty()) {
            details.append(" | Buyer: ").append(this.buyerDni);
        }

        return details.toString();
    }
}