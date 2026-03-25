package com.realestate.management.model;

import java.util.Random;

/**
 * Represents a residential building managing apartments, parking spaces, and storage units.
 * <p>
 * This class handles the lifecycle of the building's properties, including:
 * <ul>
 * <li>Random generation of properties upon initialization.</li>
 * <li>Management of 2D arrays for apartments and parking, and 1D arrays for storage.</li>
 * <li>Complex operations like merging contiguous apartments.</li>
 * <li>Statistical reporting and visualization.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Building {

    /** Name of the building. */
    private String name;

    /** 2D matrix representing apartments: [{@code floor}][{@code door}]. */
    private Apartment[][] apartments;

    /** Total number of floors in the building. */
    private int numFloors;

    /** Number of apartments per floor. */
    private int apartmentsPerFloor;

    /** 2D matrix representing the garage: [{@code basement}][{@code spot}]. */
    private Parking[][] garage;

    /** Number of spots per garage floor (basement). */
    private int spotsPerGarageFloor;

    /** Array representing storage units. */
    private Storage[] storageRooms;

    /** Total number of storage rooms. */
    private int numStorageRooms;

    /** Constant: The garage always has exactly 2 floors (Basement -1 and -2). */
    public static final int GARAGE_FLOORS = 2;

    /** Seed for the random number generator, ensuring reproducible results. */
    private static final long SEED = 12345L;

    /** Shared seeded random number generator for all random generation methods. */
    private static final Random rand = new Random(SEED);

    /**
     * Constructs a new {@link Building} and initializes all properties with random values.
     *
     * @param name Name of the building.
     * @param numFloors Number of residential floors.
     * @param apartmentsPerFloor Number of apartments per floor.
     * @param spotsPerGarageFloor Number of spots per basement level.
     * @param numStorageRooms Total number of storage units.
     */
    public Building(String name, int numFloors, int apartmentsPerFloor, int spotsPerGarageFloor, int numStorageRooms) {
        this.name = name;
        this.numFloors = numFloors;
        this.apartmentsPerFloor = apartmentsPerFloor;
        this.spotsPerGarageFloor = spotsPerGarageFloor;
        this.numStorageRooms = numStorageRooms;

        // Initialize structures
        this.apartments = new Apartment[numFloors][apartmentsPerFloor];
        this.garage = new Parking[Building.GARAGE_FLOORS][spotsPerGarageFloor];
        this.storageRooms = new Storage[numStorageRooms];

        // Automatic random generation
        this.generateRandomApartments();
        this.generateRandomGarage();
        this.generateRandomStorage();
    }

    /**
     * Populates the apartments matrix with random realistic values.
     * <p>
     * Logic:
     * <ul>
     * <li>Price: 80,000 + (floor * 10,000) + random(0-120,000).</li>
     * <li>Surface: 40 to 180 m².</li>
     * <li>Rooms: 1 to 5.</li>
     * </ul>
     */
    private void generateRandomApartments() {
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                int floorNum = i + 1;
                double price = 80000 + (floorNum * 10000) + rand.nextInt(120001);
                double surface = 40 + rand.nextInt(141);
                int rooms = 1 + rand.nextInt(5);
                this.apartments[i][j] = new Apartment(price, surface, rooms);
            }
        }
    }

    /**
     * Populates the garage matrix with random values.
     * Ranges: Price (8k-30k), Surface (8-20 m²).
     */
    private void generateRandomGarage() {
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                double price = 8000 + rand.nextInt(22001);
                double surface = 8 + rand.nextInt(13);
                this.garage[i][j] = new Parking(price, surface);
            }
        }
    }

    /**
     * Populates the storage array with random values.
     * Ranges: Price (1.5k-8k), Surface (3-15 m²).
     */
    private void generateRandomStorage() {
        for (int i = 0; i < this.numStorageRooms; i++) {
            double price = 1500 + rand.nextInt(6501);
            double surface = 3 + rand.nextInt(13);
            this.storageRooms[i] = new Storage(price, surface);
        }
    }

    /**
     * Gets the name of the building.
     *
     * @return The name of the building.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the building.
     *
     * @param name The new name for the building.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the total number of residential floors.
     *
     * @return The number of floors.
     */
    public int getNumFloors() {
        return this.numFloors;
    }

    /**
     * Gets the number of apartments per residential floor.
     *
     * @return The number of apartments per floor.
     */
    public int getApartmentsPerFloor() {
        return this.apartmentsPerFloor;
    }

    /**
     * Gets the fixed number of garage floors.
     * <p>
     * According to the specifications, this always returns {@link #GARAGE_FLOORS} (2).
     * </p>
     *
     * @return The constant number of garage floors.
     */
    public int getGarageFloors() {
        return Building.GARAGE_FLOORS;
    }

    /**
     * Gets the number of parking spots per garage floor (basement).
     *
     * @return The number of spots per basement.
     */
    public int getSpotsPerGarageFloor() {
        return this.spotsPerGarageFloor;
    }

    /**
     * Gets the total number of storage rooms in the building.
     *
     * @return The total count of storage units.
     */
    public int getNumStorageRooms() {
        return this.numStorageRooms;
    }

    /**
     * Retrieves a specific apartment.
     *
     * @param floor Floor index (0 to {@link #numFloors} - 1).
     * @param door Door index (0 to {@link #apartmentsPerFloor} - 1).
     * @return The {@link Apartment} or {@code null} if indices are invalid.
     */
    public Apartment getApartment(int floor, int door) {
        if (isValidApartmentIndex(floor, door)) {
            return this.apartments[floor][door];
        }
        return null;
    }

    /**
     * Sets a specific apartment at the given position.
     *
     * @param floor Floor index.
     * @param door Door index.
     * @param apartment The new apartment object.
     */
    public void setApartment(int floor, int door, Apartment apartment) {
        if (isValidApartmentIndex(floor, door)) {
            this.apartments[floor][door] = apartment;
        }
    }

    /**
     * Retrieves a specific parking space from the garage.
     *
     * @param basement Basement index (0 to {@link #GARAGE_FLOORS} - 1).
     * @param spot Spot index (0 to {@link #spotsPerGarageFloor} - 1).
     * @return The {@link Parking} space, or {@code null} if indices are invalid.
     */
    public Parking getParking(int basement, int spot) {
        if (isValidParkingIndex(basement, spot)) {
            return this.garage[basement][spot];
        }
        return null;
    }

    /**
     * Retrieves a specific storage unit.
     *
     * @param index The index of the storage unit (0 to {@link #numStorageRooms} - 1).
     * @return The {@link Storage} unit, or {@code null} if the index is invalid.
     */
    public Storage getStorage(int index) {
        if (index >= 0 && index < this.numStorageRooms) {
            return this.storageRooms[index];
        }
        return null;
    }

    /**
     * Internal helper to validate apartment indices.
     *
     * @param floor The floor index to check.
     * @param door The door index to check.
     * @return {@code true} if both indices are within valid bounds.
     */
    private boolean isValidApartmentIndex(int floor, int door) {
        return floor >= 0 && floor < this.numFloors && door >= 0 && door < this.apartmentsPerFloor;
    }

    /**
     * Internal helper to validate parking indices.
     *
     * @param basement The basement index to check.
     * @param spot The spot index to check.
     * @return {@code true} if both indices are within valid bounds.
     */
    private boolean isValidParkingIndex(int basement, int spot) {
        return basement >= 0 && basement < Building.GARAGE_FLOORS && spot >= 0 && spot < this.spotsPerGarageFloor;
    }

    /**
     * Displays a tabular view of the apartments status.
     * Prints floors from top (highest) to bottom (lowest).
     */
    public void showStatus() {
        System.out.println("\n[ BUILDING: " + this.name + " ]");
        System.out.println("Legend: F=Free, R=Reserved, S=Sold\n");

        System.out.print("          ");
        for (int j = 0; j < this.apartmentsPerFloor; j++) {
            System.out.printf(" P%d ", j + 1);
        }
        System.out.println();

        for (int i = this.numFloors - 1; i >= 0; i--) {
            System.out.printf("Floor %2d ", i + 1);
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt == null) {
                    System.out.print(" [ ]");
                } else {
                    System.out.printf(" [%s]", apt.toString());
                }
            }
            System.out.println();
        }
    }

    /**
     * Displays a tabular view of the garage status.
     * Shows each basement level with all its parking spots.
     */
    public void showGarageStatus() {
        System.out.println("\n[ GARAGE: " + this.name + " ]");
        System.out.println("Legend: F=Free, S=Sold\n");

        System.out.print("            ");
        for (int j = 0; j < this.spotsPerGarageFloor; j++) {
            System.out.printf(" S%d ", j + 1);
        }
        System.out.println();

        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            System.out.printf("Basement %d ", -(i + 1));
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                System.out.printf(" [%s]", this.garage[i][j].toString());
            }
            System.out.println();
        }
    }

    /**
     * Displays the list of all storage units with their current status.
     */
    public void showStorageStatus() {
        System.out.println("\n[ STORAGE: " + this.name + " ]");
        System.out.println("Legend: F=Free, S=Sold\n");
        for (int i = 0; i < this.numStorageRooms; i++) {
            System.out.printf(" T%d[%s]", i + 1, this.storageRooms[i].toString());
        }
        System.out.println("\n");
    }

    /**
     * Displays the full matrix of the building ({@link Apartment}, {@link Parking}, {@link Storage}).
     */
    public void showBuildingMatrix() {
        System.out.println("=== FULL BUILDING MATRIX ===");
        this.showStatus();

        System.out.println("\n[ GARAGE ]");
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            System.out.printf("Basement %d ", -(i + 1));
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                System.out.printf(" [%s]", this.garage[i][j].toString());
            }
            System.out.println();
        }

        System.out.println("\n[ STORAGE ]");
        for (int i = 0; i < this.numStorageRooms; i++) {
            System.out.printf(" T%d[%s]", i + 1, this.storageRooms[i].toString());
        }
        System.out.println("\n");
    }

    /**
     * Counts the total number of apartments that are currently available.
     *
     * @return The count of apartments with {@link Apartment.Status#FREE} status.
     */
    public int countAvailableApartments() {
        int count = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                if (this.apartments[i][j] != null && this.apartments[i][j].isAvailable()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Counts the total number of apartments that are currently reserved.
     *
     * @return The count of apartments with {@link Apartment.Status#RESERVED} status.
     */
    public int countReservedApartments() {
        int count = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                if (this.apartments[i][j] != null && this.apartments[i][j].getStatus() == Apartment.Status.RESERVED) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Counts the total number of apartments that have been sold.
     *
     * @return The count of apartments with {@link Apartment.Status#SOLD} status.
     */
    public int countSoldApartments() {
        int count = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                if (this.apartments[i][j] != null && this.apartments[i][j].getStatus() == Apartment.Status.SOLD) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Counts the total number of existing (non-null) apartments in the building.
     * <p>
     * This may differ from {@code numFloors * apartmentsPerFloor} if apartments have been merged.
     * </p>
     *
     * @return The total count of existing {@link Apartment} objects.
     */
    public int getTotalApartments() {
        int count = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                if (this.apartments[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Calculates the total potential income from all apartments.
     * <p>
     * Sums the price of <b>all</b> existing {@link Apartment} objects, regardless of status.
     * </p>
     *
     * @return The total sum of prices obtained via {@link Apartment#getPrice()}.
     */
    public double calculatePotentialIncome() {
        double total = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                if (this.apartments[i][j] != null) {
                    total += this.apartments[i][j].getPrice();
                }
            }
        }
        return total;
    }

    /**
     * Calculates the real income from apartments already sold.
     * <p>
     * Only sums the price of apartments with {@link Apartment.Status#SOLD} status.
     * </p>
     *
     * @return The total sum of prices for sold apartments.
     */
    public double calculateSoldIncome() {
        double total = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && apt.getStatus() == Apartment.Status.SOLD) {
                    total += apt.getPrice();
                }
            }
        }
        return total;
    }

    /**
     * Counts the number of parking spaces currently available.
     *
     * @return The count of parking spaces with {@link Parking.Status#FREE} status.
     */
    public int countAvailableParking() {
        int count = 0;
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                if (this.garage[i][j].isAvailable()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Counts the number of parking spaces that have been sold.
     *
     * @return The count of parking spaces with {@link Parking.Status#SOLD} status.
     */
    public int countSoldParking() {
        int count = 0;
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                if (this.garage[i][j].getStatus() == Parking.Status.SOLD) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns the total number of parking spaces in the building.
     *
     * @return {@link #GARAGE_FLOORS} multiplied by {@link #spotsPerGarageFloor}.
     */
    public int countTotalParking() {
        return Building.GARAGE_FLOORS * this.spotsPerGarageFloor;
    }

    /**
     * Calculates the total potential income from all parking spaces.
     *
     * @return The sum of prices of all {@link Parking} objects.
     */
    public double calculatePotentialParkingIncome() {
        double total = 0;
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                total += this.garage[i][j].getPrice();
            }
        }
        return total;
    }

    /**
     * Calculates the real income from parking spaces already sold.
     *
     * @return The sum of prices of sold {@link Parking} objects.
     */
    public double calculateSoldParkingIncome() {
        double total = 0;
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (p.getStatus() == Parking.Status.SOLD) {
                    total += p.getPrice();
                }
            }
        }
        return total;
    }

    /**
     * Counts the number of storage units currently available.
     *
     * @return The count of storage units with {@link Storage.Status#FREE} status.
     */
    public int countAvailableStorage() {
        int count = 0;
        for (int i = 0; i < this.numStorageRooms; i++) {
            if (this.storageRooms[i].isAvailable()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of storage units that have been sold.
     *
     * @return The count of storage units with {@link Storage.Status#SOLD} status.
     */
    public int countSoldStorage() {
        int count = 0;
        for (int i = 0; i < this.numStorageRooms; i++) {
            if (this.storageRooms[i].getStatus() == Storage.Status.SOLD) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the total potential income from all storage units.
     *
     * @return The sum of prices of all {@link Storage} objects.
     */
    public double calculatePotentialStorageIncome() {
        double total = 0;
        for (int i = 0; i < this.numStorageRooms; i++) {
            total += this.storageRooms[i].getPrice();
        }
        return total;
    }

    /**
     * Calculates the real income from storage units already sold.
     *
     * @return The sum of prices of sold {@link Storage} objects.
     */
    public double calculateSoldStorageIncome() {
        double total = 0;
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (s.getStatus() == Storage.Status.SOLD) {
                total += s.getPrice();
            }
        }
        return total;
    }

    /**
     * Counts the number of apartments associated with a specific buyer DNI.
     *
     * @param dni The buyer's identification string.
     * @return The count of apartments whose {@code buyerDni} matches the given DNI.
     */
    public int countApartmentsByDni(String dni) {
        int count = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && dni.equalsIgnoreCase(apt.getBuyerDni())) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Calculates the total investment in apartments for a specific buyer DNI.
     *
     * @param dni The buyer's identification string.
     * @return The sum of prices of all apartments belonging to the given DNI.
     */
    public double apartmentInvestmentByDni(String dni) {
        double total = 0;
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && dni.equalsIgnoreCase(apt.getBuyerDni())) {
                    total += apt.getPrice();
                }
            }
        }
        return total;
    }

    /**
     * Lists all apartments associated with a specific buyer DNI, printing their details.
     *
     * @param dni The buyer's identification string.
     */
    public void listApartmentsByDni(String dni) {
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && dni.equalsIgnoreCase(apt.getBuyerDni())) {
                    System.out.printf("  [%s] Floor %d, Door %d: %s%n",
                            this.name, i + 1, j + 1, apt.getDetails());
                }
            }
        }
    }

    /**
     * Counts the number of parking spaces associated with a specific buyer DNI.
     *
     * @param dni The buyer's identification string.
     * @return The count of parking spaces whose {@code buyerDni} matches the given DNI.
     */
    public int countParkingByDni(String dni) {
        int count = 0;
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                if (dni.equalsIgnoreCase(this.garage[i][j].getBuyerDni())) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Calculates the total investment in parking spaces for a specific buyer DNI.
     *
     * @param dni The buyer's identification string.
     * @return The sum of prices of all parking spaces belonging to the given DNI.
     */
    public double parkingInvestmentByDni(String dni) {
        double total = 0;
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (dni.equalsIgnoreCase(p.getBuyerDni())) {
                    total += p.getPrice();
                }
            }
        }
        return total;
    }

    /**
     * Lists all parking spaces associated with a specific buyer DNI, printing their details.
     *
     * @param dni The buyer's identification string.
     */
    public void listParkingByDni(String dni) {
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (dni.equalsIgnoreCase(p.getBuyerDni())) {
                    System.out.printf("  [%s] Basement %d, Spot %d: %s%n",
                            this.name, -(i + 1), j + 1, p.getDetails());
                }
            }
        }
    }

    /**
     * Counts the number of storage units associated with a specific buyer DNI.
     *
     * @param dni The buyer's identification string.
     * @return The count of storage units whose {@code buyerDni} matches the given DNI.
     */
    public int countStorageByDni(String dni) {
        int count = 0;
        for (int i = 0; i < this.numStorageRooms; i++) {
            if (dni.equalsIgnoreCase(this.storageRooms[i].getBuyerDni())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the total investment in storage units for a specific buyer DNI.
     *
     * @param dni The buyer's identification string.
     * @return The sum of prices of all storage units belonging to the given DNI.
     */
    public double storageInvestmentByDni(String dni) {
        double total = 0;
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (dni.equalsIgnoreCase(s.getBuyerDni())) {
                total += s.getPrice();
            }
        }
        return total;
    }

    /**
     * Lists all storage units associated with a specific buyer DNI, printing their details.
     *
     * @param dni The buyer's identification string.
     */
    public void listStorageByDni(String dni) {
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (dni.equalsIgnoreCase(s.getBuyerDni())) {
                System.out.printf("  [%s] Storage T%d: %s%n",
                        this.name, i + 1, s.getDetails());
            }
        }
    }

    /**
     * Searches for available apartments whose surface area falls within the given range.
     *
     * @param minSurface Minimum surface area in m².
     * @param maxSurface Maximum surface area in m².
     */
    public void searchApartmentsBySurface(double minSurface, double maxSurface) {
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && apt.isAvailable() && apt.matchesSurface(minSurface, maxSurface)) {
                    System.out.printf("  [%s] Floor %d, Door %d: %s%n",
                            this.name, i + 1, j + 1, apt.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available apartments whose final price falls within the given range.
     *
     * @param minPrice Minimum price in euros.
     * @param maxPrice Maximum price in euros.
     */
    public void searchApartmentsByPrice(double minPrice, double maxPrice) {
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && apt.isAvailable() && apt.matchesPrice(minPrice, maxPrice)) {
                    System.out.printf("  [%s] Floor %d, Door %d: %s%n",
                            this.name, i + 1, j + 1, apt.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available apartments whose room count falls within the given range.
     *
     * @param minRooms Minimum number of rooms.
     * @param maxRooms Maximum number of rooms.
     */
    public void searchApartmentsByRooms(int minRooms, int maxRooms) {
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && apt.isAvailable() && apt.matchesRooms(minRooms, maxRooms)) {
                    System.out.printf("  [%s] Floor %d, Door %d: %s%n",
                            this.name, i + 1, j + 1, apt.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available apartments matching all combined criteria simultaneously.
     *
     * @param minSurf Minimum surface area.
     * @param maxSurf Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param minRooms Minimum rooms.
     * @param maxRooms Maximum rooms.
     */
    public void searchApartments(double minSurf, double maxSurf, double minPrice, double maxPrice, int minRooms, int maxRooms) {
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt != null && apt.isAvailable()
                        && apt.matchesSurface(minSurf, maxSurf)
                        && apt.matchesPrice(minPrice, maxPrice)
                        && apt.matchesRooms(minRooms, maxRooms)) {
                    System.out.printf("  [%s] Floor %d, Door %d: %s%n",
                            this.name, i + 1, j + 1, apt.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available parking spaces whose surface area falls within the given range.
     *
     * @param minSurface Minimum surface area in m².
     * @param maxSurface Maximum surface area in m².
     */
    public void searchParkingBySurface(double minSurface, double maxSurface) {
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (p.isAvailable() && p.matchesSurface(minSurface, maxSurface)) {
                    System.out.printf("  [%s] Basement %d, Spot %d: %s%n",
                            this.name, -(i + 1), j + 1, p.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available parking spaces whose price falls within the given range.
     *
     * @param minPrice Minimum price in euros.
     * @param maxPrice Maximum price in euros.
     */
    public void searchParkingByPrice(double minPrice, double maxPrice) {
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (p.isAvailable() && p.matchesPrice(minPrice, maxPrice)) {
                    System.out.printf("  [%s] Basement %d, Spot %d: %s%n",
                            this.name, -(i + 1), j + 1, p.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available parking spaces that match the specified size classification.
     *
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     * @see Parking#matchesSize(int)
     */
    public void searchParkingBySize(int sizeFilter) {
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (p.isAvailable() && p.matchesSize(sizeFilter)) {
                    System.out.printf("  [%s] Basement %d, Spot %d: %s%n",
                            this.name, -(i + 1), j + 1, p.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available parking spaces matching all combined criteria simultaneously.
     *
     * @param minSurf Minimum surface area.
     * @param maxSurf Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     */
    public void searchParking(double minSurf, double maxSurf, double minPrice, double maxPrice, int sizeFilter) {
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                Parking p = this.garage[i][j];
                if (p.isAvailable()
                        && p.matchesSurface(minSurf, maxSurf)
                        && p.matchesPrice(minPrice, maxPrice)
                        && p.matchesSize(sizeFilter)) {
                    System.out.printf("  [%s] Basement %d, Spot %d: %s%n",
                            this.name, -(i + 1), j + 1, p.getDetails());
                }
            }
        }
    }

    /**
     * Searches for available storage units whose surface area falls within the given range.
     *
     * @param minSurface Minimum surface area in m².
     * @param maxSurface Maximum surface area in m².
     */
    public void searchStorageBySurface(double minSurface, double maxSurface) {
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (s.isAvailable() && s.matchesSurface(minSurface, maxSurface)) {
                System.out.printf("  [%s] Storage T%d: %s%n", this.name, i + 1, s.getDetails());
            }
        }
    }

    /**
     * Searches for available storage units whose price falls within the given range.
     *
     * @param minPrice Minimum price in euros.
     * @param maxPrice Maximum price in euros.
     */
    public void searchStorageByPrice(double minPrice, double maxPrice) {
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (s.isAvailable() && s.matchesPrice(minPrice, maxPrice)) {
                System.out.printf("  [%s] Storage T%d: %s%n", this.name, i + 1, s.getDetails());
            }
        }
    }

    /**
     * Searches for available storage units that match the specified size classification.
     *
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     * @see Storage#matchesSize(int)
     */
    public void searchStorageBySize(int sizeFilter) {
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (s.isAvailable() && s.matchesSize(sizeFilter)) {
                System.out.printf("  [%s] Storage T%d: %s%n", this.name, i + 1, s.getDetails());
            }
        }
    }

    /**
     * Searches for available storage units matching all combined criteria simultaneously.
     *
     * @param minSurf Minimum surface area.
     * @param maxSurf Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param sizeFilter The size filter code (0 = any, 1 = small, 2 = large).
     */
    public void searchStorage(double minSurf, double maxSurf, double minPrice, double maxPrice, int sizeFilter) {
        for (int i = 0; i < this.numStorageRooms; i++) {
            Storage s = this.storageRooms[i];
            if (s.isAvailable()
                    && s.matchesSurface(minSurf, maxSurf)
                    && s.matchesPrice(minPrice, maxPrice)
                    && s.matchesSize(sizeFilter)) {
                System.out.printf("  [%s] Storage T%d: %s%n", this.name, i + 1, s.getDetails());
            }
        }
    }

    /**
     * Verifies if two apartments on the same floor can be joined into a single unit.
     * <p>
     * This validation checks four strict conditions:
     * <ol>
     * <li>The indices for floor and doors are valid boundaries.</li>
     * <li>The doors are strictly contiguous (adjacent indices, e.g., 1 and 2).</li>
     * <li>Both {@link Apartment} objects exist (are not {@code null}).</li>
     * <li>Both apartments are currently {@link Apartment.Status#FREE} (available).</li>
     * </ol>
     *
     * @param floor The floor index where the apartments are located.
     * @param door1 The index of the first door.
     * @param door2 The index of the second door.
     * @return {@code true} if all conditions for merging are met, {@code false} otherwise.
     */
    public boolean canJoinApartments(int floor, int door1, int door2) {
        if (!isValidApartmentIndex(floor, door1) || !isValidApartmentIndex(floor, door2)) return false;
        if (Math.abs(door1 - door2) != 1) return false;

        Apartment apt1 = this.apartments[floor][door1];
        Apartment apt2 = this.apartments[floor][door2];

        return apt1 != null && apt2 != null && apt1.isAvailable() && apt2.isAvailable();
    }

    /**
     * Merges two contiguous apartments into one larger unit and sells it immediately.
     * <p>
     * The new unit's attributes (price, surface, rooms) are the sum of both originals.
     * After the merge, the second slot is removed and the remaining apartments shift left.
     * </p>
     *
     * @param floor Floor index.
     * @param door1 First door index.
     * @param door2 Second door index.
     * @param dni Buyer's DNI.
     * @param quality Quality tier for the merged apartment.
     * @return {@code true} if the merge was successful.
     */
    public boolean joinApartments(int floor, int door1, int door2, String dni, Apartment.Quality quality) {
        if (door1 > door2) {
            int temp = door1; door1 = door2; door2 = temp;
        }

        if (!canJoinApartments(floor, door1, door2)) {
            System.err.println("Error: Cannot join apartments at " + floor + "-" + door1 + "/" + door2);
            return false;
        }

        Apartment apt1 = this.apartments[floor][door1];
        Apartment apt2 = this.apartments[floor][door2];

        double newPrice = apt1.getBasePrice() + apt2.getBasePrice();
        double newSurface = apt1.getSquareMeters() + apt2.getSquareMeters();
        int newRooms = apt1.getRooms() + apt2.getRooms();

        Apartment mergedApt = new Apartment(newPrice, newSurface, newRooms);
        mergedApt.sell(dni, quality);

        this.apartments[floor][door1] = mergedApt;

        for (int k = door2; k < this.apartmentsPerFloor - 1; k++) {
            this.apartments[floor][k] = this.apartments[floor][k + 1];
        }
        this.apartments[floor][this.apartmentsPerFloor - 1] = null;

        System.out.println("Success: Joined apartments at floor " + (floor + 1));
        return true;
    }

    /**
     * Verifies if two storage units can be merged into a single unit.
     * <p>
     * Conditions:
     * <ol>
     * <li>Both indices must be within valid bounds.</li>
     * <li>The indices must be strictly contiguous: |index1 - index2| == 1.</li>
     * <li>Both {@link Storage} objects must exist (not {@code null}).</li>
     * <li>Both must be currently {@link Storage.Status#FREE} (available).</li>
     * </ol>
     *
     * @param index1 Index of the first storage unit.
     * @param index2 Index of the second storage unit.
     * @return {@code true} if all conditions for merging are met, {@code false} otherwise.
     */
    public boolean canJoinStorage(int index1, int index2) {
        if (index1 < 0 || index1 >= this.numStorageRooms) return false;
        if (index2 < 0 || index2 >= this.numStorageRooms) return false;
        if (Math.abs(index1 - index2) != 1) return false;

        Storage s1 = this.storageRooms[index1];
        Storage s2 = this.storageRooms[index2];

        return s1 != null && s2 != null && s1.isAvailable() && s2.isAvailable();
    }

    /**
     * Merges two contiguous storage units into one larger unit and sells it immediately.
     * <p>
     * The new unit's price and surface are the sum of both originals.
     * After the merge, the second slot is removed and the array shifts left.
     * </p>
     *
     * @param index1 Index of the first storage unit.
     * @param index2 Index of the second storage unit.
     * @param dni Buyer's DNI.
     * @return {@code true} if the merge was successful.
     */
    public boolean joinStorage(int index1, int index2, String dni) {
        if (index1 > index2) {
            int temp = index1; index1 = index2; index2 = temp;
        }

        if (!canJoinStorage(index1, index2)) {
            System.err.println("Error: Cannot join storage units at " + index1 + "/" + index2);
            return false;
        }

        Storage s1 = this.storageRooms[index1];
        Storage s2 = this.storageRooms[index2];

        double newPrice = s1.getPrice() + s2.getPrice();
        double newSurface = s1.getSquareMeters() + s2.getSquareMeters();

        Storage mergedStorage = new Storage(newPrice, newSurface);
        mergedStorage.sell(dni);

        this.storageRooms[index1] = mergedStorage;

        for (int k = index2; k < this.numStorageRooms - 1; k++) {
            this.storageRooms[k] = this.storageRooms[k + 1];
        }
        this.storageRooms[this.numStorageRooms - 1] = null;

        System.out.println("Success: Joined storage units at indices " + (index1 + 1) + " and " + (index2 + 1));
        return true;
    }

    /**
     * Returns a short string representation of the building for list displays.
     *
     * @return The building name and its main dimensions.
     */
    @Override
    public String toString() {
        return String.format("%s (%d floors, %d apt/floor, %d parking/basement, %d storage)",
                this.name, this.numFloors, this.apartmentsPerFloor,
                this.spotsPerGarageFloor, this.numStorageRooms);
    }
}