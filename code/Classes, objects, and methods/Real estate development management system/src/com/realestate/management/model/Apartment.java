package com.realestate.management.model;

/**
 * Represents a residential unit ({@link Apartment}) within a building.
 * <p>
 * This class manages the lifecycle of the property ({@link Status#FREE}, {@link Status#RESERVED}, {@link Status#SOLD}),
 * its physical attributes, and the transaction logic including quality tiers.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Apartment {

    /**
     * Defines the possible lifecycle states of a property within the system.
     * <p>
     * These states control the availability of the unit for new transactions:
     * <ul>
     * <li>{@link #FREE}: The unit is available for any new operation.</li>
     * <li>{@link #RESERVED}: The unit is temporarily held for a specific client.</li>
     * <li>{@link #SOLD}: The unit has been permanently transferred to a buyer.</li>
     * </ul>
     */
    public enum Status {

        /**
         * The property is completely available.
         * It can be sold or reserved by any client.
         */
        FREE,

        /**
         * The property is temporarily held by a potential buyer.
         * It cannot be sold to another person unless the reservation is canceled.
         */
        RESERVED,

        /**
         * The property has been successfully sold.
         * The transaction is complete and ownership has been transferred.
         */
        SOLD
    }

    /**
     * Defines the construction quality tiers and their impact on the final price.
     * Each tier has an associated price multiplier and a display label.
     */
    public enum Quality {

        /**
         * Standard finishings. Represents the base price (multiplier {@code 1.0}).
         */
        STANDARD(1.0, "Standard"),

        /**
         * Improved finishings. Increases the base price by 5% (multiplier {@code 1.05}).
         */
        PLUS(1.05, "Plus"),

        /**
         * Luxury finishings. Increases the base price by 10% (multiplier {@code 1.10}).
         */
        DELUXE(1.10, "Deluxe");

        /**
         * The factor by which the base price is multiplied.
         */
        private final double multiplier;

        /**
         * A human-readable name for the quality tier.
         */
        private final String label;

        /**
         * Internal constructor for the {@link Quality} enum.
         *
         * @param multiplier The price multiplier (e.g., {@code 1.05} for a 5% increase).
         * @param label The display name of the quality tier.
         */
        Quality(double multiplier, String label) {
            this.multiplier = multiplier;
            this.label = label;
        }

        /**
         * Gets the price multiplier associated with this quality tier.
         *
         * @return The multiplier value (e.g., {@code 1.10}).
         */
        public double getMultiplier() {
            return multiplier;
        }

        /**
         * Gets the display label of the quality tier.
         *
         * @return The name of the quality (e.g., {@code "Deluxe"}).
         */
        public String getLabel() {
            return label;
        }
    }

    /**
     * The current lifecycle status of the apartment.
     * Possible values: {@link Status#FREE}, {@link Status#RESERVED}, or {@link Status#SOLD}.
     */
    private Status status;

    /**
     * The base monetary value of the unit in euros, before applying any quality multipliers.
     */
    private double basePrice;

    /**
     * The total surface area of the apartment in square meters.
     */
    private double squareMeters;

    /**
     * The number of bedrooms/rooms available in the unit.
     */
    private int rooms;

    /**
     * The unique identification of the current buyer or person reserving the unit.
     * It is {@code null} if the apartment is {@link Status#FREE}.
     */
    private String buyerDni;

    /**
     * The specific quality tier assigned to this unit.
     * It affects the final price calculation. It is {@code null} if not yet assigned.
     * See {@link #getPrice()} for calculation details.
     */
    private Quality quality;

    /**
     * Creates a new {@link Apartment} with the specified structural attributes.
     * The initial status is set to {@link Status#FREE}.
     *
     * @param basePrice The base monetary value of the unit in euros.
     * @param squareMeters The total surface area in square meters.
     * @param rooms The number of rooms in the unit.
     */
    public Apartment(double basePrice, double squareMeters, int rooms) {
        this.status = Status.FREE;
        this.basePrice = basePrice;
        this.squareMeters = squareMeters;
        this.rooms = rooms;
        this.buyerDni = null;
        this.quality = null;
    }

    /**
     * Gets the current availability status.
     *
     * @return The status enum ({@link Status#FREE}, {@link Status#RESERVED}, or {@link Status#SOLD}).
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the apartment manually.
     * <p>
     * <b>Note:</b> It is recommended to use {@link #sell(String, Quality)} or {@link #reserve(String, Quality)}
     * to maintain data consistency.
     * </p>
     *
     * @param status The new {@link Status} to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Calculates the final price of the apartment.
     * <p>
     * If a {@link Quality} tier is assigned, the base price is multiplied by the tier factor
     * (defined in {@link Quality#getMultiplier()}).
     * </p>
     *
     * @return The calculated final price.
     */
    public double getPrice() {
        if (this.quality != null) {
            return this.basePrice * this.quality.getMultiplier();
        }
        return this.basePrice;
    }

    /**
     * Gets the original base price without any quality multipliers applied.
     *
     * @return The raw base price.
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * Updates the base price of the unit.
     *
     * @param basePrice The new base price in euros.
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Gets the total surface area.
     *
     * @return The area in square meters.
     */
    public double getSquareMeters() {
        return this.squareMeters;
    }

    /**
     * Sets the total surface area.
     *
     * @param squareMeters The new area in square meters.
     */
    public void setSquareMeters(double squareMeters) {
        this.squareMeters = squareMeters;
    }

    /**
     * Gets the number of rooms.
     *
     * @return The room count.
     */
    public int getRooms() {
        return this.rooms;
    }

    /**
     * Sets the number of rooms.
     *
     * @param rooms The new room count.
     */
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    /**
     * Gets the identification of the current buyer or reserver.
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
     * Gets the quality tier currently assigned to the unit.
     *
     * @return The {@link Quality} enum, or {@code null} if not assigned.
     */
    public Quality getQuality() {
        return this.quality;
    }

    /**
     * Sets the quality tier manually.
     *
     * @param quality The {@link Quality} enum.
     */
    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    /**
     * Checks if the apartment is currently available for a new transaction.
     *
     * @return {@code true} if the status is {@link Status#FREE}, {@code false} otherwise.
     */
    public boolean isAvailable() {
        return this.status == Status.FREE;
    }

    /**
     * Verifies if the apartment's surface area falls within a specific range.
     *
     * @param min The minimum surface area.
     * @param max The maximum surface area.
     * @return {@code true} if {@link #squareMeters} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesSurface(double min, double max) {
        return this.squareMeters >= min && this.squareMeters <= max;
    }

    /**
     * Verifies if the apartment's <b>final price</b> falls within a specific range.
     *
     * @param min The minimum price.
     * @param max The maximum price.
     * @return {@code true} if the value returned by {@link #getPrice()} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesPrice(double min, double max) {
        double currentPrice = this.getPrice();
        return currentPrice >= min && currentPrice <= max;
    }

    /**
     * Verifies if the number of rooms falls within a specific range.
     *
     * @param min The minimum number of rooms.
     * @param max The maximum number of rooms.
     * @return {@code true} if {@link #rooms} is between {@code min} and {@code max} (inclusive).
     */
    public boolean matchesRooms(int min, int max) {
        return this.rooms >= min && this.rooms <= max;
    }

    /**
     * Marks the apartment as {@link Status#SOLD} to a specific buyer with a specific quality tier.
     * Validates that the DNI is valid before proceeding.
     *
     * @param dni The identification of the buyer (cannot be {@code null} or empty).
     * @param quality The quality tier chosen for the finishings.
     * @return {@code true} if the operation was successful, {@code false} if DNI was invalid.
     */
    public boolean sell(String dni, Quality quality) {
        if (dni == null || dni.trim().isEmpty()) {
            System.err.println("Error: A valid DNI is required to complete the sale.");
            return false;
        }
        this.status = Status.SOLD;
        this.buyerDni = dni;
        this.quality = quality;
        return true;
    }

    /**
     * Overloaded method. Marks the apartment as {@link Status#SOLD} with {@link Quality#STANDARD} quality by default.
     *
     * @param dni The identification of the buyer.
     * @return {@code true} if the operation was successful.
     * @see #sell(String, Quality)
     */
    public boolean sell(String dni) {
        return this.sell(dni, Quality.STANDARD);
    }

    /**
     * Marks the apartment as {@link Status#RESERVED} for a specific buyer.
     *
     * @param dni The identification of the interested party.
     * @param quality The quality tier reserved.
     * @return {@code true} if the operation was successful, {@code false} if DNI was invalid.
     */
    public boolean reserve(String dni, Quality quality) {
        if (dni == null || dni.trim().isEmpty()) {
            System.err.println("Error: A valid DNI is required to reserve.");
            return false;
        }
        this.status = Status.RESERVED;
        this.buyerDni = dni;
        this.quality = quality;
        return true;
    }

    /**
     * Overloaded method. Marks the apartment as {@link Status#RESERVED} with {@link Quality#STANDARD} quality by default.
     *
     * @param dni The identification of the interested party.
     * @return {@code true} if the operation was successful.
     * @see #reserve(String, Quality)
     */
    public boolean reserve(String dni) {
        return this.reserve(dni, Quality.STANDARD);
    }

    /**
     * Releases the apartment, resetting it to its initial available state.
     * <p>
     * Sets status to {@link Status#FREE}, and clears {@code buyerDni} and {@code quality} (sets them to {@code null}).
     * </p>
     */
    public void release() {
        this.status = Status.FREE;
        this.buyerDni = null;
        this.quality = null;
    }

    /**
     * Returns a single-character code representing the status.
     * Useful for grid visualizations.
     *
     * @return {@code "F"} for {@link Status#FREE}, {@code "R"} for {@link Status#RESERVED}, {@code "S"} for {@link Status#SOLD}.
     */
    @Override
    public String toString() {
        switch (this.status) {
            case FREE:
                return "F";
            case RESERVED:
                return "R";
            case SOLD:
                return "S";
            default:
                return "?";
        }
    }

    /**
     * Generates a detailed report of the apartment's current state.
     * Includes status, calculated price, dimensions, rooms, and buyer info if applicable.
     *
     * @return A formatted string with all property details.
     */
    public String getDetails() {
        StringBuilder details = new StringBuilder();

        // Requires showing status, price (with quality), surface, rooms
        details.append(String.format("Status: %s | Price: %.2f € | %.2f m² | %d rooms",
                this.status, this.getPrice(), this.squareMeters, this.rooms));

        // If quality is assigned, show the name
        if (this.quality != null) {
            details.append(" | Quality: ").append(this.quality.getLabel());
        }

        // If sold or reserved, show buyer DNI
        if (this.buyerDni != null && !this.buyerDni.isEmpty()) {
            details.append(" | Buyer: ").append(this.buyerDni);
        }

        return details.toString();
    }
}
