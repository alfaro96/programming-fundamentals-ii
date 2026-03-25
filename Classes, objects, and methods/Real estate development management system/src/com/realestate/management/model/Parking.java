package com.realestate.management.model;

/**
 * Represents a parking space ({@link Parking}) within the building's basement.
 * <p>
 * This class manages the simple lifecycle of the parking unit ({@link Status#FREE} or {@link Status#SOLD}),
 * its physical dimensions, and size classification logic.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Parking {

    /**
     * Defines the threshold in square meters to classify a parking space as "Large".
     * <p>
     * Current value: {@code 12.0} square meters.
     * </p>
     */
    public static final double THRESHOLD = 12.0;

    /**
     * Defines the possible lifecycle states of a parking space.
     * <p>
     * Unlike apartments, parking spaces do not have a reservation state:
     * <ul>
     * <li>{@link #FREE}: The space is available for purchase.</li>
     * <li>{@link #SOLD}: The space has been permanently transferred to a buyer.</li>
     * </ul>
     */
    public enum Status {

        /**
         * The parking space is available.
         */
        FREE,

        /**
         * The parking space has been sold.
         */
        SOLD
    }

    /**
     * The current availability status of the parking space.
     * Possible values: {@link Status#FREE} or {@link Status#SOLD}.
     */
    private Status status;

    /**
     * The monetary value of the parking space in euros.
     */
    private double price;

    /**
     * The surface area of the parking space in square meters.
     */
    private double squareMeters;

    /**
     * The unique identification of the current owner.
     * It is {@code null} if the parking space is {@link Status#FREE}.
     */
    private String buyerDni;

    /**
     * Creates a new {@link Parking} space with the specified dimensions and price.
     * The initial status is set to {@link Status#FREE}.
     *
     * @param price The monetary value of the space in euros.
     * @param squareMeters The surface area in square meters.
     */
    public Parking(double price, double squareMeters) {
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
     * Sets the status of the parking space manually.
     *
     * @param status The new {@link Status} to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the price of the parking space.
     *
     * @return The price in euros.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the parking space.
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
     * Determines if the parking space is considered "Large".
     *
     * @return {@code true} if {@link #squareMeters} is greater than {@link #THRESHOLD} ({@code 12.0}).
     */
    public boolean isLarge() {
        return this.squareMeters > Parking.THRESHOLD;
    }

    /**
     * Checks if the parking space is currently available for purchase.
     *
     * @return {@code true} if the status is {@link Status#FREE}.
     */
    public boolean isAvailable() {
        return this.status == Status.FREE;
    }

    /**
     * Verifies if the parking space's surface area falls within a specific range.
     *
     * @param min The minimum surface area.
     * @param max The maximum surface area.
     * @return {@code true} if {@link #squareMeters} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesSurface(double min, double max) {
        return this.squareMeters >= min && this.squareMeters <= max;
    }

    /**
     * Verifies if the parking space's price falls within a specific range.
     *
     * @param min The minimum price.
     * @param max The maximum price.
     * @return {@code true} if {@link #price} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesPrice(double min, double max) {
        return this.price >= min && this.price <= max;
    }

    /**
     * Filters the parking space based on its size classification.
     *
     * @param filter The filter code:
     * <ul>
     * <li>{@code 0}: Any size (always returns {@code true}).</li>
     * <li>{@code 1}: Small spaces (returns {@code true} if NOT large).</li>
     * <li>{@code 2}: Large spaces (returns {@code true} if large).</li>
     * </ul>
     * @return {@code true} if the space matches the filter criteria.
     */
    public boolean matchesSize(int filter) {
        return filter == 0
                || (filter == 1 && !this.isLarge())
                || (filter == 2 && this.isLarge());
    }

    /**
     * Marks the parking space as {@link Status#SOLD} to a specific buyer.
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
     * Releases the parking space, resetting it to its initial available state.
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
     * Generates a detailed report of the parking space's current state.
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
