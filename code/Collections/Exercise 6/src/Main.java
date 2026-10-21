import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Goes through a list of pets with iterators, and removes elements from a list of names while
 * going through it, in the two ways that work.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 * @see Iterator
 */
public class Main {

    /**
     * Prints the names of the pets with two iterators, keeps only the names of five letters in
     * two copies of the list of names, and prints the result reversed.
     *
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Dog("Bobby"));
        pets.add(new Dog("Rex"));
        pets.add(new Cat("Garfield"));
        pets.add(new Cat("Boots"));
        pets.add(new Dog("Jimmy"));
        pets.add(new Cat("Luna"));

        System.out.println("Step 1 - Iterator with no type");
        Iterator rawIterator = pets.iterator();
        while (rawIterator.hasNext()) {
            Pet pet = (Pet) rawIterator.next();
            System.out.println(pet.getName());
        }

        System.out.println("Step 2 - Iterator<Pet>");
        Iterator<Pet> iterator = pets.iterator();
        while (iterator.hasNext()) {
            Pet pet = iterator.next();
            System.out.println(pet.getName());
        }

        List<String> names = new ArrayList<>();
        List<String> copy = new ArrayList<>();
        for (Pet pet : pets) {
            names.add(pet.getName());
            copy.add(pet.getName());
        }
        System.out.println("Step 3 - Names: " + names);

        Iterator<String> nameIterator = names.iterator();
        while (nameIterator.hasNext()) {
            if (nameIterator.next().length() != 5) {
                nameIterator.remove();
            }
        }
        System.out.println("Step 4 - Five letters, with an iterator: " + names);

        // From the end, removing the element at i only moves those already checked
        for (int i = copy.size() - 1; i >= 0; i--) {
            if (copy.get(i).length() != 5) {
                copy.remove(i);
            }
        }
        System.out.println("Step 5 - Five letters, by position: " + copy);

        Collections.reverse(names);
        System.out.println("Step 6 - Reversed: " + names);
    }
}
