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
        Random random = new Random();
        for (int i = 0; i < this.numFloors; i++) {
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                // Floor number for calculation
                int floorNum = i + 1;

                double price = 80000 + (floorNum * 10000) + random.nextInt(120001);
                double surface = 40 + random.nextInt(141); // 40 to 180
                int rooms = 1 + random.nextInt(5); // 1 to 5

                this.apartments[i][j] = new Apartment(price, surface, rooms);
            }
        }
    }

    /**
     * Populates the garage matrix with random values.
     * Ranges: Price (8k-30k), Surface (8-20 m²).
     */
    private void generateRandomGarage() {
        Random random = new Random();
        for (int i = 0; i < Building.GARAGE_FLOORS; i++) {
            for (int j = 0; j < this.spotsPerGarageFloor; j++) {
                double price = 8000 + random.nextInt(22001);
                double surface = 8 + random.nextInt(13);
                this.garage[i][j] = new Parking(price, surface);
            }
        }
    }

    /**
     * Populates the storage array with random values.
     * Ranges: Price (1.5k-8k), Surface (3-15 m²).
     */
    private void generateRandomStorage() {
        Random random = new Random();
        for (int i = 0; i < this.numStorageRooms; i++) {
            double price = 1500 + random.nextInt(6501);
            double surface = 3 + random.nextInt(13);
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
     * @param door Door index (0 to {@link #apartmentsPerFloor} -1).
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
     * Internal helper method to validate if the provided floor and door indices are within the building's bounds.
     * <p>
     * Checks if the floor is between 0 and {@link #numFloors} - 1, and if the door is between 0 and {@link #apartmentsPerFloor} - 1.
     * </p>
     *
     * @param floor The floor index to check.
     * @param door The door index to check.
     * @return {@code true} if both indices are valid, {@code false} otherwise.
     */
    private boolean isValidApartmentIndex(int floor, int door) {
        return floor >= 0 && floor < this.numFloors && door >= 0 && door < this.apartmentsPerFloor;
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

        // Iterate from top floor down to 0
        for (int i = this.numFloors - 1; i >= 0; i--) {
            System.out.printf("Floor %2d ", i + 1);
            for (int j = 0; j < this.apartmentsPerFloor; j++) {
                Apartment apt = this.apartments[i][j];
                if (apt == null) {
                    System.out.print(" [ ]"); // Empty space (e.g. after merge)
                } else {
                    System.out.printf(" [%s]", apt.toString());
                }
            }
            System.out.println();
        }
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
     * <p>
     * Iterates through the entire building matrix, checking that the {@link Apartment} exists (is not {@code null})
     * and that its status is specifically {@link Apartment.Status#FREE}.
     * </p>
     *
     * @return The count of apartments where {@link Apartment#isAvailable} returns {@code true}.
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
     * Calculates the total potential income of the building.
     * <p>
     * Sums the price of <b>all</b> existing {@link Apartment} objects, regardless of their current status
     * ({@link Apartment.Status#SOLD}, {@link Apartment.Status#RESERVED}, or {@link Apartment.Status#FREE}).
     * This represents the maximum revenue possible for the residential area.
     * </p>
     *
     * @return The total sum of prices obtained via {@link Apartment#getPrice}.
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
     * Searches for apartments meeting combined criteria.
     *
     * @param minSurf Minimum surface area.
     * @param maxSurf Maximum surface area.
     * @param minPrice Minimum price.
     * @param maxPrice Maximum price.
     * @param minRooms Minimum rooms.
     * @param maxRooms Maximum rooms.
     */
    public void searchApartments(double minSurf, double maxSurf, double minPrice, double maxPrice, int minRooms, int maxRooms) {
        System.out.println("Searching apartments...");
        for (int i = 0; i < numFloors; i++) {
            for (int j = 0; j < apartmentsPerFloor; j++) {
                Apartment apt = apartments[i][j];
                if (apt != null && apt.isAvailable() &&
                        apt.matchesSurface(minSurf, maxSurf) &&
                        apt.matchesPrice(minPrice, maxPrice) &&
                        apt.matchesRooms(minRooms, maxRooms)) {
                    System.out.println("Found at Floor " + (i+1) + " Door " + (j+1) + ": " + apt.getDetails());
                }
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
     * @see #joinApartments(int, int, int, String, Apartment.Quality)
     */
    public boolean canJoinApartments(int floor, int door1, int door2) {
        if (!isValidApartmentIndex(floor, door1) || !isValidApartmentIndex(floor, door2)) return false;
        if (Math.abs(door1 - door2) != 1) return false; // Must be contiguous

        Apartment apt1 = apartments[floor][door1];
        Apartment apt2 = apartments[floor][door2];

        return apt1 != null && apt2 != null && apt1.isAvailable() && apt2.isAvailable();
    }

    /**
     * Merges two contiguous apartments into one larger unit.
     * The new unit is sold immediately to the buyer.
     *
     * @param floor Floor index.
     * @param door1 First door index.
     * @param door2 Second door index.
     * @param dni Buyer's DNI.
     * @param quality Quality for the new merged apartment.
     * @return {@code true} if merge was successful.
     */
    public boolean joinApartments(int floor, int door1, int door2, String dni, Apartment.Quality quality) {
        // Ensure door1 is the left one
        if (door1 > door2) {
            int temp = door1; door1 = door2; door2 = temp;
        }

        if (!canJoinApartments(floor, door1, door2)) {
            System.err.println("Error: Cannot join apartments at " + floor + "-" + door1 + "/" + door2);
            return false;
        }

        Apartment apt1 = apartments[floor][door1];
        Apartment apt2 = apartments[floor][door2];

        // 1. Calculate new attributes
        double newPrice = apt1.getBasePrice() + apt2.getBasePrice();
        double newSurface = apt1.getSquareMeters() + apt2.getSquareMeters();
        int newRooms = apt1.getRooms() + apt2.getRooms();

        // 2. Create new merged apartment
        Apartment mergedApt = new Apartment(newPrice, newSurface, newRooms);
        mergedApt.setQuality(quality); // Set quality first to adjust price calculation
        mergedApt.sell(dni, quality);  // Mark as SOLD

        // 3. Update grid logic

        // Place merged apartment in the first slot
        apartments[floor][door1] = mergedApt;

        // Shift remaining apartments to the left to fill the gap left by door2
        for (int k = door2; k < apartmentsPerFloor - 1; k++) {
            apartments[floor][k] = apartments[floor][k + 1];
        }

        // Nullify the last position
        apartments[floor][apartmentsPerFloor - 1] = null;

        System.out.println("Success: Joined apartments at floor " + (floor + 1));

        return true;
    }
}
