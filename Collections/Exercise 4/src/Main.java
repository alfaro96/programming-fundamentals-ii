import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point for the inheritance and polymorphism exercise.
 * <p>
 * This class demonstrates storing subclasses in a generic collection of the
 * parent type, handling runtime type identification, and exploring compiler
 * behaviors regarding inherited vs. specific methods.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 * @see Dog
 * @see Cat
 */
public class Main {

    /**
     * Executes the exercise steps directly without relying on external helper methods.
     * * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // 3. Create a generic ArrayList designed to hold Pet objects.
        ArrayList<Pet> pets = new ArrayList<Pet>();

        // Instantiate several dog and cat objects and add them to the list.
        pets.add(new Dog("Bobby"));
        pets.add(new Cat("Garfield"));
        pets.add(new Dog("Sanson"));
        pets.add(new Cat("Whiskers"));

        System.out.println("--- Iterating through the Pet collection ---");

        // 4 & 5. Iterate through the pets list using a for-each loop.
        for (Pet currentPet : pets) {

            /*
             * STEP 4: Calling specific, non-inherited methods.
             * Since 'currentPet' is referenced as a generic 'Pet', the compiler
             * does not know if it's a Dog or a Cat. We must use 'instanceof'
             * to check the runtime type, and then cast it to the specific
             * subclass to access bark() or meow().
             */
            if (currentPet instanceof Dog) {
                ((Dog) currentPet).bark();
            } else if (currentPet instanceof Cat) {
                ((Cat) currentPet).meow();
            }

            /*
             * STEP 5: Calling the inherited method.
             * EXPLANATION OF COMPILER BEHAVIOR:
             * Unlike bark() and meow(), the speak() method is defined in the base
             * 'Pet' class. Therefore, the compiler guarantees that ANY object in
             * this list (whether a Dog or a Cat) possesses the speak() method.
             * We can call it directly from the 'Pet' reference without needing
             * any type-checking (instanceof) or casting. This is a core benefit
             * of polymorphism.
             */
            currentPet.speak();

            System.out.println("-"); // Separator for readability
        }
    }
}
