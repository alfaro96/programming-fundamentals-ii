package com.realestate.management;

import com.realestate.management.model.Apartment;
import com.realestate.management.model.Building;
import com.realestate.management.model.Developer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Entry point and user interface of the real estate developer management system.
 * <p>
 * Provides a complete console-based menu system to interact with all features of the
 * application. All input/output is handled through a shared {@link Scanner} instance.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Global scanner shared across all input methods.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The developer instance being managed throughout the session.
     */
    private static Developer developer;

    /**
     * Entry point of the program.
     * <p>
     * Initializes the system and launches the main menu loop.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        initialize();
        mainMenu();
    }

    /**
     * Initializes the system by either loading existing data or creating a new developer.
     */
    private static void initialize() {
        System.out.println("============================================");
        System.out.println("   REAL ESTATE DEVELOPMENT MANAGEMENT");
        System.out.println("============================================");

        System.out.print("Enter the developer's name: ");
        String name = readString();
        developer = new Developer(name);
        System.out.println("Welcome, " + developer.getName() + "! Two default buildings have been created.");
    }

    /**
     * Displays and drives the main menu loop.
     * <p>
     * Iterates until the user selects option {@code 0}. Before exiting, offers to save data.
     * </p>
     */
    private static void mainMenu() {
        int option;
        do {
            System.out.println("\n============================================");
            System.out.println("MAIN MENU");
            System.out.println("============================================");
            System.out.println(" --- Apartments ---");
            System.out.println("  1. View apartment status");
            System.out.println("  2. Sell or reserve apartment");
            System.out.println("  3. Join apartments");
            System.out.println("  4. Check available apartments");
            System.out.println(" --- Garage ---");
            System.out.println("  5. View garage status");
            System.out.println("  6. Sell parking space");
            System.out.println("  7. Check available parking spaces");
            System.out.println(" --- Storage ---");
            System.out.println("  8. View storage status");
            System.out.println("  9. Sell storage unit");
            System.out.println(" 10. Join storage units");
            System.out.println(" 11. Check available storage units");
            System.out.println(" --- General ---");
            System.out.println(" 12. Manage buildings");
            System.out.println(" 13. View building matrix");
            System.out.println(" 14. Check properties by DNI");
            System.out.println(" 15. Search apartments");
            System.out.println(" 16. Search parking spaces");
            System.out.println(" 17. Search storage units");
            System.out.println(" 18. View statistics");
            System.out.println("  0. Exit");
            System.out.println("============================================");
            System.out.print("Select an option: ");

            option = readInteger();

            switch (option) {
                case 1:  viewApartmentStatus(); break;
                case 2:  sellReserveMenu(); break;
                case 3:  joinApartments(); break;
                case 4:  checkAvailableApartments(); break;
                case 5:  viewGarageStatus(); break;
                case 6:  sellParkingMenu(); break;
                case 7:  checkAvailableParking(); break;
                case 8:  viewStorageStatus(); break;
                case 9:  sellStorageMenu(); break;
                case 10: joinStorage(); break;
                case 11: checkAvailableStorage(); break;
                case 12: manageBuildings(); break;
                case 13: viewBuildingMatrix(); break;
                case 14: checkPropertiesByDni(); break;
                case 15: searchApartments(); break;
                case 16: searchParking(); break;
                case 17: searchStorage(); break;
                case 18: developer.showGeneralStatistics(); break;
                case 0:  break;
                default: System.out.println("Invalid option. Please try again.");
            }

        } while (option != 0);

        System.out.println("Goodbye!");
        scanner.close();
    }

    /**
     * Safely reads an integer from the console.
     * <p>
     * Clears the input buffer after reading. If the input is not a valid integer,
     * prints an error, clears the buffer, and returns {@code -1}.
     * </p>
     *
     * @return The integer entered by the user, or {@code -1} on invalid input.
     */
    public static int readInteger() {
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            System.err.println("Error: Please enter a valid integer.");
            scanner.nextLine();
            return -1;
        }
    }

    /**
     * Safely reads a decimal number from the console.
     * <p>
     * Clears the input buffer after reading. If the input is not a valid double,
     * prints an error, clears the buffer, and returns {@code -1.0}.
     * </p>
     *
     * @return The double entered by the user, or {@code -1.0} on invalid input.
     */
    public static double readDouble() {
        try {
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            System.err.println("Error: Please enter a valid decimal number.");
            scanner.nextLine();
            return -1.0;
        }
    }

    /**
     * Reads a non-empty string from the console.
     * <p>
     * Keeps prompting the user until a non-blank line is entered.
     * </p>
     *
     * @return The validated non-empty string entered by the user.
     */
    public static String readString() {
        String value;
        do {
            value = scanner.nextLine();
            if (value.trim().isEmpty()) {
                System.err.println("Error: Input cannot be empty. Please try again: ");
            }
        } while (value.trim().isEmpty());
        return value.trim();
    }

    /**
     * Prompts the user to select an existing building by index.
     * <p>
     * Lists all registered buildings and returns the selected 0-based index.
     * Returns {@code -1} if no buildings are registered.
     * </p>
     *
     * @return The 0-based building index selected by the user, or {@code -1} if none exist.
     */
    private static int selectBuilding() {
        if (developer.getNumBuildings() == 0) {
            System.out.println("No buildings registered.");
            return -1;
        }
        developer.listBuildings();
        System.out.print("Select building index: ");
        return readInteger();
    }

    /**
     * Prompts the user to select a building and displays its apartment status grid.
     */
    private static void viewApartmentStatus() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }
        building.showStatus();
    }

    /**
     * Submenu that allows the user to either sell or reserve an apartment.
     * <p>
     * The user selects the building, floor, door, buyer DNI, and quality tier.
     * Then delegates to {@link Developer#sellApartment} or {@link Developer#reserveApartment}.
     * </p>
     */
    private static void sellReserveMenu() {
        System.out.println("\n--- SELL OR RESERVE APARTMENT ---");
        System.out.println("1. Sell apartment");
        System.out.println(" 2. Reserve apartment");
        System.out.print("Select option: ");
        int option = readInteger();
        if (option != 1 && option != 2) {
            System.out.println("Invalid option.");
            return;
        }

        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }

        building.showStatus();

        System.out.print("Enter floor number (1-" + building.getNumFloors() + "): ");
        int floor = readInteger() - 1;

        System.out.print("Enter door number (1-" + building.getApartmentsPerFloor() + "): ");
        int door = readInteger() - 1;

        System.out.print("Enter buyer's DNI: ");
        String dni = readString();

        Apartment.Quality quality = selectQuality();
        if (quality == null) return;

        if (option == 1) {
            developer.sellApartment(buildingIndex, floor, door, dni, quality);
        } else {
            developer.reserveApartment(buildingIndex, floor, door, dni, quality);
        }
    }

    /**
     * Prompts the user to select a quality tier from the available options.
     *
     * @return The selected {@link Apartment.Quality}, or {@code null} on invalid input.
     */
    private static Apartment.Quality selectQuality() {
        System.out.println("Select quality tier:");
        System.out.println("  1. Standard");
        System.out.println("  2. Plus (+5%)");
        System.out.println("  3. Deluxe (+10%)");
        System.out.print("Option: ");
        int q = readInteger();
        switch (q) {
            case 1: return Apartment.Quality.STANDARD;
            case 2: return Apartment.Quality.PLUS;
            case 3: return Apartment.Quality.DELUXE;
            default:
                System.err.println("Error: Invalid quality option.");
                return null;
        }
    }

    /**
     * Guides the user through joining two contiguous apartments on the same floor.
     * <p>
     * The user selects the building, floor, two adjacent door numbers, buyer DNI,
     * and quality tier. Delegates to {@link Building#joinApartments}.
     * </p>
     */
    private static void joinApartments() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }

        building.showStatus();

        System.out.print("Enter floor number (1-" + building.getNumFloors() + "): ");
        int floor = readInteger() - 1;

        System.out.print("Enter first door number: ");
        int door1 = readInteger() - 1;

        System.out.print("Enter second door number (must be adjacent to first): ");
        int door2 = readInteger() - 1;

        System.out.print("Enter buyer's DNI: ");
        String dni = readString();

        Apartment.Quality quality = selectQuality();
        if (quality == null) return;

        building.joinApartments(floor, door1, door2, dni, quality);
    }

    /**
     * Displays available apartments across all buildings.
     */
    private static void checkAvailableApartments() {
        System.out.println("\n--- AVAILABLE APARTMENTS ---");
        developer.searchApartments(0, Double.MAX_VALUE, 0, Double.MAX_VALUE, 0, Integer.MAX_VALUE);
    }

    /**
     * Prompts the user to select a building and displays its garage status grid.
     */
    private static void viewGarageStatus() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }
        building.showGarageStatus();
    }

    /**
     * Guides the user through selling a parking space in a selected building.
     * <p>
     * The user selects building, basement level, spot number, and buyer DNI.
     * Delegates to {@link Developer#sellParking}.
     * </p>
     */
    private static void sellParkingMenu() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }

        building.showGarageStatus();

        System.out.print("Enter basement level (1 or 2): ");
        int basement = readInteger() - 1;

        System.out.print("Enter spot number (1-" + building.getSpotsPerGarageFloor() + "): ");
        int spot = readInteger() - 1;

        System.out.print("Enter buyer's DNI: ");
        String dni = readString();

        developer.sellParking(buildingIndex, basement, spot, dni);
    }

    /**
     * Displays available parking spaces across all buildings.
     */
    private static void checkAvailableParking() {
        System.out.println("\n--- AVAILABLE PARKING SPACES ---");
        developer.searchParking(0, Double.MAX_VALUE, 0, Double.MAX_VALUE, 0);
    }

    /**
     * Prompts the user to select a building and displays its storage unit status.
     */
    private static void viewStorageStatus() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }
        building.showStorageStatus();
    }

    /**
     * Guides the user through selling a storage unit in a selected building.
     * <p>
     * The user selects building, storage index, and buyer DNI.
     * Delegates to {@link Developer#sellStorage}.
     * </p>
     */
    private static void sellStorageMenu() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }

        building.showStorageStatus();

        System.out.print("Enter storage unit number (1-" + building.getNumStorageRooms() + "): ");
        int storageIndex = readInteger() - 1;

        System.out.print("Enter buyer's DNI: ");
        String dni = readString();

        developer.sellStorage(buildingIndex, storageIndex, dni);
    }

    /**
     * Guides the user through joining two contiguous storage units.
     * <p>
     * Merging combines two adjacent storage units into one by summing their surface and price.
     * Both units must be available. The resulting unit replaces the first slot.
     * </p>
     */
    private static void joinStorage() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }

        building.showStorageStatus();

        System.out.print("Enter first storage unit number: ");
        int index1 = readInteger() - 1;

        System.out.print("Enter second storage unit number (must be adjacent): ");
        int index2 = readInteger() - 1;

        // Validate indices
        if (Math.abs(index1 - index2) != 1) {
            System.err.println("Error: Storage units must be adjacent to be joined.");
            return;
        }

        // Ensure index1 < index2
        if (index1 > index2) {
            int temp = index1; index1 = index2; index2 = temp;
        }

        com.realestate.management.model.Storage s1 = building.getStorage(index1);
        com.realestate.management.model.Storage s2 = building.getStorage(index2);

        if (s1 == null || s2 == null) {
            System.err.println("Error: One or both storage units do not exist.");
            return;
        }

        if (!s1.isAvailable() || !s2.isAvailable()) {
            System.err.println("Error: Both storage units must be available to join them.");
            return;
        }

        System.out.print("Enter buyer's DNI: ");
        String dni = readString();

        // Merge: combine price and surface, sell the first unit
        double newPrice = s1.getPrice() + s2.getPrice();
        double newSurface = s1.getSquareMeters() + s2.getSquareMeters();

        s1.setPrice(newPrice);
        s1.setSquareMeters(newSurface);
        s1.sell(dni);

        // Shift remaining storage units left to fill the gap
        for (int k = index2; k < building.getNumStorageRooms() - 1; k++) {
            com.realestate.management.model.Storage next = building.getStorage(k + 1);
            com.realestate.management.model.Storage current = building.getStorage(k);
            current.setPrice(next.getPrice());
            current.setSquareMeters(next.getSquareMeters());
            current.setStatus(next.getStatus());
            current.setBuyerDni(next.getBuyerDni());
        }

        // Reset the last storage unit
        com.realestate.management.model.Storage last = building.getStorage(building.getNumStorageRooms() - 1);
        if (last != null) {
            last.release();
            last.setPrice(0);
            last.setSquareMeters(0);
        }

        System.out.println("Success: Storage units T" + (index1 + 1) + " and T" + (index2 + 1) + " joined.");
        System.out.println("Result: " + s1.getDetails());
    }

    /**
     * Displays available storage units across all buildings.
     */
    private static void checkAvailableStorage() {
        System.out.println("\n--- AVAILABLE STORAGE UNITS ---");
        developer.searchStorage(0, Double.MAX_VALUE, 0, Double.MAX_VALUE, 0);
    }

    /**
     * Submenu for managing buildings.
     * <p>
     * Allows the user to list existing buildings or create a new one by specifying
     * all required parameters. New buildings automatically generate random properties
     * via the {@link Building} constructor.
     * </p>
     */
    private static void manageBuildings() {
        System.out.println("\n--- MANAGE BUILDINGS ---");
        System.out.println("  1. List existing buildings");
        System.out.println("  2. Create new building");
        System.out.print("Select option: ");
        int option = readInteger();

        switch (option) {
            case 1:
                developer.listBuildings();
                break;
            case 2:
                System.out.print("Building name: ");
                String name = readString();

                System.out.print("Number of floors: ");
                int floors = readInteger();

                System.out.print("Apartments per floor: ");
                int aptsPerFloor = readInteger();

                System.out.print("Parking spots per basement level: ");
                int spotsPerBasement = readInteger();

                System.out.print("Number of storage units: ");
                int numStorage = readInteger();

                Building newBuilding = new Building(name, floors, aptsPerFloor, spotsPerBasement, numStorage);
                developer.addBuilding(newBuilding);
                System.out.println("Building '" + name + "' created and added successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Prompts the user to select a building and displays its full property matrix.
     */
    private static void viewBuildingMatrix() {
        int buildingIndex = selectBuilding();
        if (buildingIndex < 0) return;

        Building building = developer.getBuilding(buildingIndex);
        if (building == null) {
            System.err.println("Error: Invalid building index.");
            return;
        }
        building.showBuildingMatrix();
    }

    /**
     * Prompts the user for a DNI and delegates to {@link Developer#listPropertiesByDni}.
     */
    private static void checkPropertiesByDni() {
        System.out.print("Enter DNI to search: ");
        String dni = readString();
        developer.listPropertiesByDni(dni);
    }

    /**
     * Submenu for searching available apartments across all buildings.
     * <p>
     * Offers search by surface, price, rooms, or a combined multi-criteria search.
     * </p>
     */
    private static void searchApartments() {
        System.out.println("\n--- SEARCH APARTMENTS ---");
        System.out.println("1. Search by surface area");
        System.out.println("2. Search by price");
        System.out.println("3. Search by number of rooms");
        System.out.println("4. Combined search (all criteria)");
        System.out.print("Select option: ");
        int option = readInteger();

        switch (option) {
            case 1:
                System.out.print("Minimum surface (m²): ");
                double minSurf = readDouble();
                System.out.print("Maximum surface (m²): ");
                double maxSurf = readDouble();
                developer.searchApartmentsBySurface(minSurf, maxSurf);
                break;

            case 2:
                System.out.print("Minimum price (€): ");
                double minPrice = readDouble();
                System.out.print("Maximum price (€): ");
                double maxPrice = readDouble();
                developer.searchApartmentsByPrice(minPrice, maxPrice);
                break;

            case 3:
                System.out.print("Minimum rooms: ");
                int minRooms = readInteger();
                System.out.print("Maximum rooms: ");
                int maxRooms = readInteger();
                developer.searchApartmentsByRooms(minRooms, maxRooms);
                break;

            case 4:
                System.out.print("Minimum surface (m²): ");
                double cMinSurf = readDouble();
                System.out.print("Maximum surface (m²): ");
                double cMaxSurf = readDouble();
                System.out.print("Minimum price (€): ");
                double cMinPrice = readDouble();
                System.out.print("Maximum price (€): ");
                double cMaxPrice = readDouble();
                System.out.print("Minimum rooms: ");
                int cMinRooms = readInteger();
                System.out.print("Maximum rooms: ");
                int cMaxRooms = readInteger();
                developer.searchApartments(cMinSurf, cMaxSurf, cMinPrice, cMaxPrice, cMinRooms, cMaxRooms);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Submenu for searching available parking spaces across all buildings.
     * <p>
     * Offers search by surface, price, size classification, or a combined multi-criteria search.
     * </p>
     */
    private static void searchParking() {
        System.out.println("\n--- SEARCH PARKING SPACES ---");
        System.out.println("1. Search by surface area");
        System.out.println("2. Search by price");
        System.out.println("3. Search by size (small/large)");
        System.out.println("4. Combined search (all criteria)");
        System.out.print("Select option: ");
        int option = readInteger();

        switch (option) {
            case 1:
                System.out.print("Minimum surface (m²): ");
                double minSurf = readDouble();
                System.out.print("Maximum surface (m²): ");
                double maxSurf = readDouble();
                developer.searchParkingBySurface(minSurf, maxSurf);
                break;

            case 2:
                System.out.print("Minimum price (€): ");
                double minPrice = readDouble();
                System.out.print("Maximum price (€): ");
                double maxPrice = readDouble();
                developer.searchParkingByPrice(minPrice, maxPrice);
                break;

            case 3:
                int sizeFilter = readSizeFilter();
                developer.searchParkingBySize(sizeFilter);
                break;

            case 4:
                System.out.print("Minimum surface (m²): ");
                double cMinSurf = readDouble();
                System.out.print("Maximum surface (m²): ");
                double cMaxSurf = readDouble();
                System.out.print("Minimum price (€): ");
                double cMinPrice = readDouble();
                System.out.print("Maximum price (€): ");
                double cMaxPrice = readDouble();
                int cSizeFilter = readSizeFilter();
                developer.searchParking(cMinSurf, cMaxSurf, cMinPrice, cMaxPrice, cSizeFilter);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Submenu for searching available storage units across all buildings.
     * <p>
     * Offers search by surface, price, size classification, or a combined multi-criteria search.
     * </p>
     */
    private static void searchStorage() {
        System.out.println("\n--- SEARCH STORAGE UNITS ---");
        System.out.println("1. Search by surface area");
        System.out.println("2. Search by price");
        System.out.println("3. Search by size (small/large)");
        System.out.println("4. Combined search (all criteria)");
        System.out.print("Select option: ");
        int option = readInteger();

        switch (option) {
            case 1:
                System.out.print("Minimum surface (m²): ");
                double minSurf = readDouble();
                System.out.print("Maximum surface (m²): ");
                double maxSurf = readDouble();
                developer.searchStorageBySurface(minSurf, maxSurf);
                break;

            case 2:
                System.out.print("Minimum price (€): ");
                double minPrice = readDouble();
                System.out.print("Maximum price (€): ");
                double maxPrice = readDouble();
                developer.searchStorageByPrice(minPrice, maxPrice);
                break;

            case 3:
                int sizeFilter = readSizeFilter();
                developer.searchStorageBySize(sizeFilter);
                break;

            case 4:
                System.out.print("Minimum surface (m²): ");
                double cMinSurf = readDouble();
                System.out.print("Maximum surface (m²): ");
                double cMaxSurf = readDouble();
                System.out.print("Minimum price (€): ");
                double cMinPrice = readDouble();
                System.out.print("Maximum price (€): ");
                double cMaxPrice = readDouble();
                int cSizeFilter = readSizeFilter();
                developer.searchStorage(cMinSurf, cMaxSurf, cMinPrice, cMaxPrice, cSizeFilter);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    /**
     * Prompts the user to choose a size classification filter.
     *
     * @return The filter code: {@code 0} for any, {@code 1} for small, {@code 2} for large.
     */
    private static int readSizeFilter() {
        System.out.println("Size filter:");
        System.out.println("  0. Any size");
        System.out.println("  1. Small");
        System.out.println("  2. Large");
        System.out.print("Select filter: ");
        int filter = readInteger();
        if (filter < 0 || filter > 2) {
            System.out.println("Invalid filter. Defaulting to 'any size' (0).");
            return 0;
        }
        return filter;
    }
}
