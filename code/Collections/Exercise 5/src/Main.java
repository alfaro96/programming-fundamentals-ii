import java.util.ArrayList;
import java.util.List;

/**
 * Writes two methods that take lists of different kinds of {@link Pet}: one that reads from
 * them, with {@code ? extends}, and one that adds to them, with {@code ? super}.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 * @see Dog
 * @see Cat
 */
public class Main {

    /**
     * Makes every pet of a list speak.
     *
     * <p>The list can hold {@link Pet}s or objects of any subclass of it, such as a
     * {@code List<Dog>}: every element is read as a {@link Pet}, so {@link Pet#speak} can be
     * called on it, but nothing can be added to the list.</p>
     *
     * @param pets the list of pets.
     */
    public static void processElements(List<? extends Pet> pets) {
        for (Pet pet : pets) {
            pet.speak();
        }
    }

    /**
     * Adds a new dog to a list.
     *
     * <p>The list can hold {@link Dog}s or objects of any superclass of it, such as a
     * {@code List<Pet>} or a {@code List<Object>}: a dog fits in all of them, but what the list
     * gives back is only known to be an {@link Object}.</p>
     *
     * @param list the list to add the dog to.
     * @param name the name of the new dog.
     */
    public static void addDog(List<? super Dog> list, String name) {
        list.add(new Dog(name));
    }

    /**
     * Passes lists of dogs, cats, pets and objects to {@link #processElements} and
     * {@link #addDog}.
     *
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Bobby"));
        dogs.add(new Dog("Jimmy"));

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Garfield"));
        cats.add(new Cat("Whiskers"));

        processElements(dogs);
        processElements(cats);

        // Main.java: error: incompatible types: List<String> cannot be converted to
        // List<? extends Pet>
        // A String is not a Pet, so a list of strings is rejected while compiling.
        List<String> names = new ArrayList<>();
        // processElements(names);

        // A list with no type in angle brackets compiles, with a warning, because the compiler
        // cannot check what it holds. Its string is only found while the program runs, when
        // the loop reads it as a Pet: a ClassCastException.
        ArrayList anything = new ArrayList();
        anything.add(new Dog("Oasis"));
        anything.add("Boots");
        try {
            processElements(anything);
        } catch (ClassCastException e) {
            System.out.println("The list holds something that is not a pet");
        }

        List<Pet> pets = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        addDog(dogs, "Rex");
        addDog(pets, "Toby");
        addDog(objects, "Max");

        // Reading from a List<? super Dog> only gives an Object: the list could be a
        // List<Object>, whose elements need not be pets.
        for (Object object : objects) {
            Dog dog = (Dog) object;
            System.out.println(dog.getName() + " is now in the list");
        }
        processElements(pets);
    }
}
