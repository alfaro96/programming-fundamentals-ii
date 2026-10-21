import java.util.ArrayList;
import java.util.List;

/**
 * Keeps dogs and cats in one {@link ArrayList} with no type of element, and then tries a list
 * that only holds dogs.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Dog
 * @see Cat
 */
public class Main {

    /**
     * Adds four dogs and three cats to one list, prints them, makes each one bark or meow, and
     * creates a {@code List<Dog>}.
     *
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args) {
        ArrayList animals = new ArrayList();
        animals.add(new Dog("Bobby"));
        animals.add(new Dog("Jimmy"));
        animals.add(new Dog("Oasis"));
        animals.add(new Dog("Sanson"));
        animals.add(new Cat("Garfield"));
        animals.add(new Cat("Boots"));
        animals.add(new Cat("Whiskers"));

        for (Object animal : animals) {
            System.out.println(animal);
        }

        for (Object animal : animals) {
            if (animal instanceof Dog dog) {
                dog.bark();
            } else if (animal instanceof Cat cat) {
                cat.meow();
            }
        }

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Rex"));

        // Main.java: error: incompatible types: Cat cannot be converted to Dog
        // A List<Dog> only takes dogs: the compiler rejects the cat where it is added, instead of
        // a ClassCastException later, when the program runs.
        // dogs.add(new Cat("Luna"));
    }
}
