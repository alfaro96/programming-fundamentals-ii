package com.realestate.management.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a real estate developer ({@link Developer}) that manages multiple buildings.
 * <p>
 * This class sits at the top of the system hierarchy, aggregating buildings and delegating
 * sales, searches, and statistical operations across the entire portfolio.
 * It uses a dynamic array for building management, automatically doubling its capacity when full.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Developer implements Serializable {

    /** Serial version UID for object serialization. */
    private static final long serialVersionUID = 1L;

    /** Initial capacity of the buildings array. */
    private static final int INITIAL_CAPACITY = 3;

    /**
     * The name of the real estate developer.
     */
    private String name;

    /**
     * Dynamic array holding the buildings managed by this developer.
     * Automatically doubles in size when full.
     */
    private Building[] buildings;

    /**
     * The number of buildings currently stored in the array.
     */
    private int numBuildings;

    /**
     * Creates a new {@link Developer} with the specified name and two default buildings.
     * <p>
     * The buildings array is initialized with a capacity of {@value #INITIAL_CAPACITY}.
     * Two buildings are created automatically upon construction.
     * </p>
     *
     * @param name The name of the real estate developer.
     */
    public Developer(String name) {
        this.name = name;
        this.buildings = new Building[INITIAL_CAPACITY];
        this.buildings[0] = new Building("Tower A", 5, 4, 8, 6);
        this.buildings[1] = new Building("Tower B", 4, 3, 6, 4);
        this.numBuildings = 2;
    }

    /**
     * Adds a new building to the developer's portfolio.
     * <p>
     * If the internal array is full ({@code numBuildings == buildings.length}),
     * the array is automatically doubled in capacity using {@link Arrays#copyOf}.
     * </p>
     *
     * @param building The {@link Building} to add.
     */
    public void addBuilding(Building building) {
        if (this.numBuildings == this.buildings.length) {
            this.buildings = Arrays.copyOf(this.buildings, this.buildings.length * 2);
        }
        this.buildings[this.numBuildings] = building;
        this.numBuildings++;
    }

    /**
     * Retrieves a building by its index with bounds validation.
     *
     * @param index The index of the building (0 to {@link #numBuildings} - 1).
     * @return The {@link Building} at the given index, or {@code null} if out of bounds.
     */
    public Building getBuilding(int index) {
        if (index >= 0 && index < this.numBuildings) {
            return this.buildings[index];
        }
        return null;
    }

    /**
     * Returns a copy of the array containing only the valid (non-null) buildings.
     *
     * @return A trimmed copy of the buildings array with exactly {@link #numBuildings} elements.
     */
    public Building[] getBuildings() {
        return Arrays.copyOf(this.buildings, this.numBuildings);
    }

    /**
     * Gets the number of buildings currently managed.
     *
     * @return The value of {@link #numBuildings}.
     */
    public int getNumBuildings() {
        return this.numBuildings;
    }

    /**
     * Gets the name of the developer.
     *
     * @return The developer's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the developer.
     *
     * @param name The new name to assign.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prints a numbered list of all registered buildings to the console.
     */
    public void listBuildings() {
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        System.out.println("\n[ BUILDINGS - " + this.name + " ]");
        for (int i = 0; i < this.numBuildings; i++) {
            System.out.printf("  [%d] %s%n", i, this.buildings[i].toString());
        }
    }

    /**
     * Sells an apartment in a specific building to a buyer with a chosen quality tier.
     * <p>
     * Validates that both the building and the apartment exist before attempting the sale.
     * On success, prints a confirmation with the apartment's details.
     * </p>
     *
     * @param buildingIndex Index of the target building.
     * @param floor Floor index (0-based) within the building.
     * @param door Door index (0-based) on the floor.
     * @param dni The buyer's identification string.
     * @param quality The {@link Apartment.Quality} tier chosen for the finishings.
     * @return {@code true} if the sale was completed successfully, {@code false} otherwise.
     */
    public boolean sellApartment(int buildingIndex, int floor, int door, String dni, Apartment.Quality quality) {
        Building building = this.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Building index " + buildingIndex + " does not exist.");
            return false;
        }

        Apartment apartment = building.getApartment(floor, door);
        if (apartment == null) {
            System.err.println("Error: Apartment at floor " + (floor + 1) + ", door " + (door + 1) + " does not exist.");
            return false;
        }

        boolean result = apartment.sell(dni, quality);
        if (result) {
            System.out.println("Sale confirmed: " + apartment.getDetails());
        }
        return result;
    }

    /**
     * Reserves an apartment in a specific building for a potential buyer.
     * <p>
     * Works analogously to {@link #sellApartment} but calls {@link Apartment#reserve} instead.
     * </p>
     *
     * @param buildingIndex Index of the target building.
     * @param floor Floor index (0-based) within the building.
     * @param door Door index (0-based) on the floor.
     * @param dni The buyer's identification string.
     * @param quality The {@link Apartment.Quality} tier reserved.
     * @return {@code true} if the reservation was completed successfully, {@code false} otherwise.
     */
    public boolean reserveApartment(int buildingIndex, int floor, int door, String dni, Apartment.Quality quality) {
        Building building = this.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Building index " + buildingIndex + " does not exist.");
            return false;
        }

        Apartment apartment = building.getApartment(floor, door);
        if (apartment == null) {
            System.err.println("Error: Apartment at floor " + (floor + 1) + ", door " + (door + 1) + " does not exist.");
            return false;
        }

        boolean result = apartment.reserve(dni, quality);
        if (result) {
            System.out.println("Reservation confirmed: " + apartment.getDetails());
        }
        return result;
    }

    /**
     * Sells a parking space in a specific building to a buyer.
     * <p>
     * Validates that the building exists, the parking space exists,
     * and that it is currently available before proceeding.
     * </p>
     *
     * @param buildingIndex Index of the target building.
     * @param basement Basement index (0-based).
     * @param spot Spot index (0-based) within the basement.
     * @param dni The buyer's identification string.
     * @return {@code true} if the sale was completed successfully, {@code false} otherwise.
     */
    public boolean sellParking(int buildingIndex, int basement, int spot, String dni) {
        Building building = this.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Building index " + buildingIndex + " does not exist.");
            return false;
        }

        Parking parking = building.getParking(basement, spot);
        if (parking == null) {
            System.err.println("Error: Parking space at basement " + (-(basement + 1)) + ", spot " + (spot + 1) + " does not exist.");
            return false;
        }

        if (!parking.isAvailable()) {
            System.err.println("Error: Parking space at basement " + (-(basement + 1)) + ", spot " + (spot + 1) + " is not available.");
            return false;
        }

        boolean result = parking.sell(dni);
        if (result) {
            System.out.println("Sale confirmed: " + parking.getDetails());
        }
        return result;
    }

    /**
     * Sells a storage unit in a specific building to a buyer.
     * <p>
     * Validates that the building exists, the storage unit exists,
     * and that it is currently available before proceeding.
     * </p>
     *
     * @param buildingIndex Index of the target building.
     * @param storageIndex Index of the storage unit (0-based).
     * @param dni The buyer's identification string.
     * @return {@code true} if the sale was completed successfully, {@code false} otherwise.
     */
    public boolean sellStorage(int buildingIndex, int storageIndex, String dni) {
        Building building = this.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Building index " + buildingIndex + " does not exist.");
            return false;
        }

        Storage storage = building.getStorage(storageIndex);
        if (storage == null) {
            System.err.println("Error: Storage unit T" + (storageIndex + 1) + " does not exist.");
            return false;
        }

        if (!storage.isAvailable()) {
            System.err.println("Error: Storage unit T" + (storageIndex + 1) + " is not available.");
            return false;
        }

        boolean result = storage.sell(dni);
        if (result) {
            System.out.println("Sale confirmed: " + storage.getDetails());
        }
        return result;
    }

    /**
     * Displays a complete summary of all properties across all buildings managed by this developer.
     * <p>
     * For each property type, shows totals by status and both potential and real income figures.
     * Potential income includes all units; real income only counts sold units.
     * </p>
     */
    public void showGeneralStatistics() {
        System.out.println("\n========================================");
        System.out.println("GENERAL STATISTICS - " + this.name);
        System.out.println("========================================");

        int totalApts = 0, freeApts = 0, reservedApts = 0, soldApts = 0;
        double potentialAptIncome = 0, realAptIncome = 0;

        int totalParking = 0, freeParking = 0, soldParking = 0;
        double potentialParkingIncome = 0, realParkingIncome = 0;

        int totalStorage = 0, freeStorage = 0, soldStorage = 0;
        double potentialStorageIncome = 0, realStorageIncome = 0;

        for (int i = 0; i < this.numBuildings; i++) {
            Building b = this.buildings[i];

            totalApts += b.countTotalApartments();
            freeApts += b.countAvailableApartments();
            reservedApts += b.countReservedApartments();
            soldApts += b.countSoldApartments();
            potentialAptIncome += b.calculatePotentialIncome();
            realAptIncome += b.calculateRealApartmentIncome();

            totalParking += b.countTotalParking();
            freeParking += b.countAvailableParking();
            soldParking += b.countSoldParking();
            potentialParkingIncome += b.calculatePotentialParkingIncome();
            realParkingIncome += b.calculateRealParkingIncome();

            totalStorage += b.getNumStorageRooms();
            freeStorage += b.countAvailableStorage();
            soldStorage += b.countSoldStorage();
            potentialStorageIncome += b.calculatePotentialStorageIncome();
            realStorageIncome += b.calculateRealStorageIncome();
        }

        System.out.println("\n--- APARTMENTS ---");
        System.out.printf("Total: %d | Free: %d | Reserved: %d | Sold: %d%n",
                totalApts, freeApts, reservedApts, soldApts);
        System.out.printf("Potential income: %,.2f €%n", potentialAptIncome);
        System.out.printf("Real income: %,.2f €%n", realAptIncome);

        System.out.println("\n--- PARKING SPACES ---");
        System.out.printf("Total: %d | Free: %d | Sold: %d%n",
                totalParking, freeParking, soldParking);
        System.out.printf("Potential income: %,.2f €%n", potentialParkingIncome);
        System.out.printf("Real income: %,.2f €%n", realParkingIncome);

        System.out.println("\n--- STORAGE UNITS ---");
        System.out.printf("Total: %d | Free: %d | Sold: %d%n",
                totalStorage, freeStorage, soldStorage);
        System.out.printf("Potential income: %,.2f €%n", potentialStorageIncome);
        System.out.printf("Real income: %,.2f €%n", realStorageIncome);

        double totalPotential = potentialAptIncome + potentialParkingIncome + potentialStorageIncome;
        double totalReal = realAptIncome + realParkingIncome + realStorageIncome;

        System.out.println("\n--- GLOBAL TOTAL ---");
        System.out.printf("Total potential income: %,.2f €%n", totalPotential);
        System.out.printf("Total real income: %,.2f €%n", totalReal);
        System.out.println("========================================\n");
    }

    /**
     * Lists all properties belonging to a specific buyer across all buildings.
     * <p>
     * Iterates through every building and prints apartments, parking spaces, and storage units
     * associated with the given DNI. Finishes with a global investment summary.
     * </p>
     *
     * @param dni The buyer's identification string to search for.
     */
    public void listPropertiesByDni(String dni) {
        System.out.println("\n========================================");
        System.out.printf("Properties for DNI: %s%n", dni);
        System.out.println("========================================");

        int totalApts = 0;
        double totalAptInvestment = 0;

        int totalPark = 0;
        double totalParkInvestment = 0;

        int totalStor = 0;
        double totalStorInvestment = 0;

        for (int i = 0; i < this.numBuildings; i++) {
            Building b = this.buildings[i];

            int aptCount = b.countApartmentsByDni(dni);
            if (aptCount > 0) {
                b.listApartmentsByDni(dni);
                totalApts += aptCount;
                totalAptInvestment += b.apartmentInvestmentByDni(dni);
            }

            int parkCount = b.countParkingByDni(dni);
            if (parkCount > 0) {
                b.listParkingByDni(dni);
                totalPark += parkCount;
                totalParkInvestment += b.parkingInvestmentByDni(dni);
            }

            int storCount = b.countStorageByDni(dni);
            if (storCount > 0) {
                b.listStorageByDni(dni);
                totalStor += storCount;
                totalStorInvestment += b.storageInvestmentByDni(dni);
            }
        }

        if (totalApts + totalPark + totalStor == 0) {
            System.out.println("No properties found for DNI: " + dni);
        } else {
            System.out.println("\n--- SUMMARY ---");
            System.out.printf("Apartments: %d  (%.2f €)%n", totalApts, totalAptInvestment);
            System.out.printf("Parking spaces: %d  (%.2f €)%n", totalPark, totalParkInvestment);
            System.out.printf("Storage units: %d  (%.2f €)%n", totalStor, totalStorInvestment);
            System.out.printf("TOTAL INVESTMENT: %.2f €%n",
                    totalAptInvestment + totalParkInvestment + totalStorInvestment);
        }
        System.out.println("========================================\n");
    }

    /**
     * Counts the total number of parking spaces owned by a specific buyer across all buildings.
     *
     * @param dni The buyer's identification string.
     * @return The aggregated count of parking spaces associated with the given DNI.
     */
    public int countParkingByDni(String dni) {
        int total = 0;
        for (int i = 0; i < this.numBuildings; i++) {
            total += this.buildings[i].countParkingByDni(dni);
        }
        return total;
    }

    /**
     * Counts the total number of storage units owned by a specific buyer across all buildings.
     *
     * @param dni The buyer's identification string.
     * @return The aggregated count of storage units associated with the given DNI.
     */
    public int countStorageByDni(String dni) {
        int total = 0;
        for (int i = 0; i < this.numBuildings; i++) {
            total += this.buildings[i].countStorageByDni(dni);
        }
        return total;
    }

    /**
     * Searches for available apartments by surface area across all buildings.
     *
     * @param minSurface Minimum surface area in m².
     * @param maxSurface Maximum surface area in m².
     */
    public void searchApartmentsBySurface(double minSurface, double maxSurface) {
        System.out.printf("%n--- Apartments by surface: [%.1f - %.1f m²] ---%n", minSurface, maxSurface);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchApartmentsBySurface(minSurface, maxSurface);
        }
    }

    /**
     * Searches for available apartments by final price across all buildings.
     *
     * @param minPrice Minimum price in euros.
     * @param maxPrice Maximum price in euros.
     */
    public void searchApartmentsByPrice(double minPrice, double maxPrice) {
        System.out.printf("%n--- Apartments by price: [%.2f € - %.2f €] ---%n", minPrice, maxPrice);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchApartmentsByPrice(minPrice, maxPrice);
        }
    }

    /**
     * Searches for available apartments by number of rooms across all buildings.
     *
     * @param minRooms Minimum number of rooms.
     * @param maxRooms Maximum number of rooms.
     */
    public void searchApartmentsByRooms(int minRooms, int maxRooms) {
        System.out.printf("%n--- Apartments by rooms: [%d - %d] ---%n", minRooms, maxRooms);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchApartmentsByRooms(minRooms, maxRooms);
        }
    }

    /**
     * Searches for available apartments matching all combined criteria across all buildings.
     *
     * @param minSurface Minimum surface area.
     * @param maxSurface Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param minRooms Minimum rooms.
     * @param maxRooms Maximum rooms.
     */
    public void searchApartments(double minSurface, double maxSurface, double minPrice, double maxPrice, int minRooms, int maxRooms) {
        System.out.printf("%n--- Combined apartment search ---%n");
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchApartments(minSurface, maxSurface, minPrice, maxPrice, minRooms, maxRooms);
        }
    }

    /**
     * Searches for available parking spaces by surface area across all buildings.
     *
     * @param minSurface Minimum surface area in m².
     * @param maxSurface Maximum surface area in m².
     */
    public void searchParkingBySurface(double minSurface, double maxSurface) {
        System.out.printf("%n--- Parking by surface: [%.1f - %.1f m²] ---%n", minSurface, maxSurface);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchParkingBySurface(minSurface, maxSurface);
        }
    }

    /**
     * Searches for available parking spaces by price across all buildings.
     *
     * @param minPrice Minimum price in euros.
     * @param maxPrice Maximum price in euros.
     */
    public void searchParkingByPrice(double minPrice, double maxPrice) {
        System.out.printf("%n--- Parking by price: [%.2f € - %.2f €] ---%n", minPrice, maxPrice);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchParkingByPrice(minPrice, maxPrice);
        }
    }

    /**
     * Searches for available parking spaces by size classification across all buildings.
     *
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     * @see Parking#matchesSize(int)
     */
    public void searchParkingBySize(int sizeFilter) {
        System.out.printf("%n--- Parking by size filter [%d] ---%n", sizeFilter);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchParkingBySize(sizeFilter);
        }
    }

    /**
     * Searches for available parking spaces matching all combined criteria across all buildings.
     *
     * @param minSurface Minimum surface area.
     * @param maxSurface Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     */
    public void searchParking(double minSurface, double maxSurface, double minPrice, double maxPrice, int sizeFilter) {
        System.out.printf("%n--- Combined parking search ---%n");
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchParking(minSurface, maxSurface, minPrice, maxPrice, sizeFilter);
        }
    }

    /**
     * Searches for available storage units by surface area across all buildings.
     *
     * @param minSurface Minimum surface area in m².
     * @param maxSurface Maximum surface area in m².
     */
    public void searchStorageBySurface(double minSurface, double maxSurface) {
        System.out.printf("%n--- Storage by surface: [%.1f - %.1f m²] ---%n", minSurface, maxSurface);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchStorageBySurface(minSurface, maxSurface);
        }
    }

    /**
     * Searches for available storage units by price across all buildings.
     *
     * @param minPrice Minimum price in euros.
     * @param maxPrice Maximum price in euros.
     */
    public void searchStorageByPrice(double minPrice, double maxPrice) {
        System.out.printf("%n--- Storage by price: [%.2f € - %.2f €] ---%n", minPrice, maxPrice);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchStorageByPrice(minPrice, maxPrice);
        }
    }

    /**
     * Searches for available storage units by size classification across all buildings.
     *
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     * @see Storage#matchesSize(int)
     */
    public void searchStorageBySize(int sizeFilter) {
        System.out.printf("%n--- Storage by size filter [%d] ---%n", sizeFilter);
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchStorageBySize(sizeFilter);
        }
    }

    /**
     * Searches for available storage units matching all combined criteria across all buildings.
     *
     * @param minSurface Minimum surface area.
     * @param maxSurface Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     */
    public void searchStorage(double minSurface, double maxSurface, double minPrice, double maxPrice, int sizeFilter) {
        System.out.printf("%n--- Combined storage search ---%n");
        if (this.numBuildings == 0) {
            System.out.println("No buildings registered.");
            return;
        }
        for (int i = 0; i < this.numBuildings; i++) {
            this.buildings[i].searchStorage(minSurface, maxSurface, minPrice, maxPrice, sizeFilter);
        }
    }

    /**
     * Returns a short string representation of the developer.
     *
     * @return The developer's name and the number of buildings managed.
     */
    @Override
    public String toString() {
        return String.format("Developer: %s | Buildings: %d", this.name, this.numBuildings);
    }
}
