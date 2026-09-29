import java.util.ArrayList;
import java.util.List;

/**
 * Main class for Exercise 3.
 * <p>
 * This class demonstrates the use of custom objects within both non-generic
 * and generic collections, showing runtime type checking and compile-time
 * type safety constraints.
 * </p>
 * * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        // 4. Create a non-generic ArrayList named animals.
        List animals = new ArrayList();

        // 5. Instantiate and add dogs and cats to the list.
        animals.add(new Dog("Bobby"));
        animals.add(new Dog("Jimmy"));
        animals.add(new Dog("Oasis"));
        animals.add(new Dog("Sanson"));
        animals.add(new Cat("Garfield"));
        animals.add(new Cat("Boots"));
        animals.add(new Cat("Whiskers"));

        System.out.println("--- Testing overridden toString() method ---");
        System.out.println(animals.get(0)); // Should print: I am the dog Bobby
        System.out.println(animals.get(4)); // Should print: I am the cat Garfield

        System.out.println("\n--- Iterating and triggering specific behaviors ---");

        // 6. Iterate through the animals list using a for-each loop.
        for (Object animal : animals) {
            // Type checking at runtime
            if (animal instanceof Dog) {
                ((Dog) animal).bark();
            } else if (animal instanceof Cat) {
                ((Cat) animal).meow();
            }
        }

        // 7. Create a generic ArrayList<Dog> and attempt to add both.
        List<Dog> dogsOnly = new ArrayList<Dog>();
        dogsOnly.add(new Dog("Rex")); // This works perfectly.

        /*
         * EXPLANATION OF COMPILER BEHAVIOR:
         * If we uncomment the line below, the Java compiler will throw an error:
         * "The method add(Dog) in the type ArrayList<Dog> is not applicable for the arguments (Cat)"
         * * This happens because generics enforce "Type Safety" at compile-time.
         * The compiler guarantees that ONLY objects of type Dog (or its subclasses)
         * can be added to this specific list, preventing accidental ClassCastExceptions
         * later at runtime.
         */
        // dogsOnly.add(new Cat("Luna")); // COMPILATION ERROR
    }
}
