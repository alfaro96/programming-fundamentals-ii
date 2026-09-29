import java.util.ArrayList;
import java.util.List;

/**
 * Main application class for Exercise 5.
 * <p>
 * This class explores Java generics wildcards, specifically:
 * <ul>
 * <li>Upper bounded wildcards ({@code ? extends T})</li>
 * </ul>
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Main {

    /**
     * Main entry point for the wildcard demonstration.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("--- Step 2: Upper bounded wildcards ---");

        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("Bobby"));
        dogList.add(new Dog("Jimmy"));

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat("Garfield"));
        catList.add(new Cat("Whiskers"));

        // Accepted lists of Pet subclasses
        processElements(dogList);
        processElements(catList);

        System.out.println("\n--- Step 5: Lower bounded wildcards ---");

        ArrayList<Pet> petList = new ArrayList<>();
        petList.add(new Dog("Oasis"));

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add(new Cat("Boots"));
        objectList.add("String objects are also objects");

        // Accepted lists of Pet or its superclasses
        processLowerBoundedElements(petList);
        processLowerBoundedElements(objectList);
    }

    /**
     * Processes a list using an upper bounded wildcard.
     * <p>
     * This method can accept an {@link List} of {@link Pet} or any of its
     * subclasses (e.g., {@link Dog}, {@link Cat}). It is safe to call 
     * {@link Pet#speak()} because every element is guaranteed to be a {@code Pet}.
     * </p>
     *
     * @param list A {@link List} restricted by {@code ? extends Pet}.
     * @see Pet#speak()
     */
    public static void processElements(List<? extends Pet> list) {
        for (Pet pet : list) {
            pet.speak();
        }
    }

    /**
     * Processes a list using a lower bounded wildcard.
     * <p>
     * This method accepts an {@link List} of {@link Pet} or its superclasses
     * (e.g., {@link Object}). Since we don't know the exact type, elements are
     * safely extracted as {@link Object}.
     * </p>
     * <p>
     * <b>Difference:</b> While Upper Bounds allow safe access to parent methods,
     * Lower Bounds are typically used for writing to a collection or when we
     * only need the {@link Object} base behavior.
     * </p>
     *
     * @param list An {@link List} restricted by {@code ? super Pet}.
     * @see Object#toString()
     */
    public static void processLowerBoundedElements(List<? super Pet> list) {
        for (Object obj : list) {
            System.out.println(obj.toString());
        }
    }
}