import antiques.Collection;
import antiques.Furniture;
import antiques.Painting;
import exceptions.CollectionFullException;
import exceptions.FurnitureNotFoundException;
import exceptions.InvalidPriceException;
import exceptions.YearOutOfRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides an interactive text menu that allows the user to add antiques to a
 * {@link Collection} or remove furniture by year. All foreseeable error conditions
 * (invalid price, out-of-range year, full century row, furniture not found, and
 * wrong input type) are caught and reported so that the menu always reappears after
 * an error.
 *
 * <p>The collection is pre-configured with {@value #CENTURIES} centuries and a maximum
 * of {@value #MAX_ITEMS_PER_CENTURY} items per century.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /** Number of centuries (rows) the collection matrix covers. */
    private static final int CENTURIES = 20;

    /** Maximum number of antiques stored per century row. */
    private static final int MAX_ITEMS_PER_CENTURY = 5;

    /** Menu option: add a piece of furniture. */
    private static final int OPTION_ADD_FURNITURE = 1;

    /** Menu option: add a painting. */
    private static final int OPTION_ADD_PAINTING = 2;

    /** Menu option: delete furniture by year. */
    private static final int OPTION_DELETE_FURNITURE = 3;

    /** Menu option: display the current matrix. */
    private static final int OPTION_DISPLAY = 4;

    /** Menu option: exit the program. */
    private static final int OPTION_EXIT = 0;

    /**
     * Runs the interactive menu loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collection collection = new Collection(CENTURIES, MAX_ITEMS_PER_CENTURY);

        int option = -1;

        do {
            printMenu();

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // consume the trailing newline

                switch (option) {
                    case OPTION_ADD_FURNITURE -> addFurniture(scanner, collection);
                    case OPTION_ADD_PAINTING -> addPainting(scanner, collection);
                    case OPTION_DELETE_FURNITURE -> deleteFurniture(scanner, collection);
                    case OPTION_DISPLAY -> collection.displayMatrix();
                    case OPTION_EXIT -> System.out.println("Exiting. Goodbye!");
                    default -> System.out.println("Invalid option. Please choose a number from the menu.");
                }

            } catch (InputMismatchException e) {
                // The user typed a non-integer when the menu option was expected
                System.err.println("[InputMismatchException] Please enter a valid integer for the menu option.");
                scanner.nextLine(); // discard the offending token so the scanner is ready again
                option = -1; // prevent an accidental exit (option 0)
            }

        } while (option != OPTION_EXIT);

        scanner.close();
    }

    /**
     * Prints the main menu to standard output.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println(OPTION_ADD_FURNITURE + ". Add furniture");
        System.out.println(OPTION_ADD_PAINTING + ". Add painting");
        System.out.println(OPTION_DELETE_FURNITURE + ". Delete furniture by year");
        System.out.println(OPTION_DISPLAY + ". Display collection");
        System.out.println(OPTION_EXIT + ". Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Reads furniture data from the keyboard and attempts to add it to the collection.
     *
     * <p>Handles {@link InputMismatchException} (non-numeric year or price),
     * {@link YearOutOfRangeException} (year outside the accepted range; unchecked),
     * {@link InvalidPriceException} (negative price), and
     * {@link CollectionFullException} (no slot available in the target century).</p>
     *
     * @param scanner The {@link Scanner} used to read user input.
     * @param collection The {@link Collection} to which the furniture will be added.
     */
    private static void addFurniture(Scanner scanner, Collection collection) {
        try {
            System.out.print("  Identifier: ");
            String id = scanner.nextLine().trim();

            System.out.print("  Year (1–1900): ");
            int year = scanner.nextInt();

            System.out.print("  Price (euros): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("  Material: ");
            String material = scanner.nextLine().trim();

            Furniture furniture = new Furniture(id, year, price, material);
            collection.addAntique(furniture);
            System.out.println("Furniture added successfully: " + furniture);

        } catch (InputMismatchException e) {
            System.err.println("[InputMismatchException] Year and price must be numeric values.");
            scanner.nextLine(); // discard the offending token
        } catch (YearOutOfRangeException e) {
            System.err.println("[YearOutOfRangeException] " + e.getMessage());
        } catch (InvalidPriceException e) {
            System.err.println("[InvalidPriceException] " + e.getMessage());
        } catch (CollectionFullException e) {
            System.err.println("[CollectionFullException] " + e.getMessage());
        }
    }

    /**
     * Reads painting data from the keyboard and attempts to add it to the collection.
     *
     * <p>Handles {@link InputMismatchException} (non-numeric year or price),
     * {@link YearOutOfRangeException} (year outside the accepted range; unchecked),
     * {@link InvalidPriceException} (negative price), and
     * {@link CollectionFullException} (no slot available in the target century).</p>
     *
     * @param scanner The {@link Scanner} used to read user input.
     * @param collection The {@link Collection} to which the painting will be added.
     */
    private static void addPainting(Scanner scanner, Collection collection) {
        try {
            System.out.print("  Identifier: ");
            String id = scanner.nextLine().trim();

            System.out.print("  Year (1–1900): ");
            int year = scanner.nextInt();

            System.out.print("  Price (euros): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("  Technique: ");
            String technique = scanner.nextLine().trim();

            Painting painting = new Painting(id, year, price, technique);
            collection.addAntique(painting);
            System.out.println("Painting added successfully: " + painting);

        } catch (InputMismatchException e) {
            System.err.println("[InputMismatchException] Year and price must be numeric values.");
            scanner.nextLine(); // discard the offending token
        } catch (YearOutOfRangeException e) {
            System.err.println("[YearOutOfRangeException] " + e.getMessage());
        } catch (InvalidPriceException e) {
            System.err.println("[InvalidPriceException] " + e.getMessage());
        } catch (CollectionFullException e) {
            System.err.println("[CollectionFullException] " + e.getMessage());
        }
    }

    /**
     * Reads a year from the keyboard and attempts to delete all matching furniture
     * from the collection.
     *
     * <p>Handles {@link InputMismatchException} (non-numeric year),
     * {@link YearOutOfRangeException} (year maps outside the matrix; unchecked), and
     * {@link FurnitureNotFoundException} (no furniture for that year exists).</p>
     *
     * @param scanner The {@link Scanner} used to read user input.
     * @param collection The {@link Collection} from which furniture will be deleted.
     */
    private static void deleteFurniture(Scanner scanner, Collection collection) {
        try {
            System.out.print("  Year of furniture to delete: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // consume newline

            collection.deleteFurniture(year);
            System.out.println("All furniture from year " + year + " removed successfully.");

        } catch (InputMismatchException e) {
            System.err.println("[InputMismatchException] Year must be a numeric value.");
            scanner.nextLine(); // discard the offending token
        } catch (YearOutOfRangeException e) {
            System.err.println("[YearOutOfRangeException] " + e.getMessage());
        } catch (FurnitureNotFoundException e) {
            System.err.println("[FurnitureNotFoundException] " + e.getMessage());
        }
    }
}
