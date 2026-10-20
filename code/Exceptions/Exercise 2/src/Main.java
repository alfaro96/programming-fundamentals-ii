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
 * Manages a {@link Collection} of antiques through a menu read from the keyboard.
 *
 * <p>Every error, a wrong value or a number typed as text, is reported with its message, and
 * the menu appears again.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Shows the menu and runs the chosen option until the user chooses to exit.
     *
     * @param args the command line arguments, not used.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collection collection = new Collection(19, 4);
        int option = -1;

        while (option != 0) {
            System.out.println();
            System.out.println("1. Add furniture");
            System.out.println("2. Add painting");
            System.out.println("3. Delete furniture by year");
            System.out.println("4. Display collection");
            System.out.println("0. Exit");
            System.out.print("Option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addFurniture(scanner, collection);
                        break;
                    case 2:
                        addPainting(scanner, collection);
                        break;
                    case 3:
                        System.out.print("Year: ");
                        collection.deleteFurniture(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println("Deleted.");
                        break;
                    case 4:
                        collection.displayMatrix();
                        break;
                    case 0:
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Unknown option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Type a number.");
                scanner.nextLine();
                option = -1;
            } catch (InvalidPriceException | YearOutOfRangeException | CollectionFullException
                     | FurnitureNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Reads the data of a piece of furniture from the keyboard and adds it to the collection.
     *
     * @param scanner the {@link Scanner} the data is read from.
     * @param collection the collection to add it to.
     * @throws InvalidPriceException if the price typed is negative.
     * @throws CollectionFullException if its century has no room for it.
     */
    private static void addFurniture(Scanner scanner, Collection collection)
            throws InvalidPriceException, CollectionFullException {
        System.out.print("Identifier: ");
        String id = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Material: ");
        String material = scanner.nextLine();

        collection.addFurniture(new Furniture(id, year, price, material));
        System.out.println("Added.");
    }

    /**
     * Reads the data of a painting from the keyboard and adds it to the collection.
     *
     * @param scanner the {@link Scanner} the data is read from.
     * @param collection the collection to add it to.
     * @throws InvalidPriceException if the price typed is negative.
     * @throws CollectionFullException if its century has no room for it.
     */
    private static void addPainting(Scanner scanner, Collection collection)
            throws InvalidPriceException, CollectionFullException {
        System.out.print("Identifier: ");
        String id = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Technique: ");
        String technique = scanner.nextLine();

        collection.addFurniture(new Painting(id, year, price, technique));
        System.out.println("Added.");
    }
}
