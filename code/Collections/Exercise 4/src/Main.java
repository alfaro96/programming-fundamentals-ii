import java.util.ArrayList;
import java.util.List;

/**
 * Keeps dogs and cats in a list of their superclass, {@link Pet}, and calls on each one the
 * method its own class adds and the method they all inherit.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 * @see Dog
 * @see Cat
 */
public class Main {

    /**
     * Adds two dogs and two cats to a {@code List<Pet>}, and makes each one bark or meow, and
     * then speak.
     *
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Dog("Bobby"));
        pets.add(new Cat("Garfield"));
        pets.add(new Dog("Sanson"));
        pets.add(new Cat("Whiskers"));

        for (Pet pet : pets) {
            // bark() and meow() are not in Pet: the compiler only accepts them after checking
            // the class of the object and casting it.
            if (pet instanceof Dog dog) {
                dog.bark();
            } else if (pet instanceof Cat cat) {
                cat.meow();
            }

            // speak() is in Pet, so every element has it: no check and no cast are needed.
            pet.speak();
        }
    }
}
