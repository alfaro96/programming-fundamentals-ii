import java.util.*;

/**
 * Main application class for Exercise 6.
 * <p>
 * This class demonstrates various ways to traverse collections using raw 
 * iterators, generic iterators, and loop constructs. It also explores 
 * safe removal of elements from a collection during traversal.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 * @see java.util.Iterator
 */
public class Main {

    /**
     * Main entry point for the iterator demonstration.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Setup: Create the 'pets' list required for the exercise
        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(new Dog("Bobby")); // 5 letters
        pets.add(new Dog("Rex")); // 3 letters
        pets.add(new Cat("Garfield")); // 8 letters
        pets.add(new Cat("Boots")); // 5 letters
        pets.add(new Dog("Jimmy")); // 5 letters
        pets.add(new Cat("Luna")); // 4 letters

        System.out.println("--- Step 1: Raw iterator traverse ---");
        // 1. Traverse pets using a raw iterator and print names.
        Iterator rawIterator = pets.iterator();
        while (rawIterator.hasNext()) {
            Object obj = rawIterator.next();
            if (obj instanceof Pet) {
                System.out.println(((Pet) obj).getName());
            }
        }

        System.out.println("\n--- Step 2: Generic iterator traverse ---");
        // 2. Traverse it again using a generic iterator.
        Iterator<Pet> genericIterator = pets.iterator();
        while (genericIterator.hasNext()) {
            Pet pet = genericIterator.next();
            System.out.println(pet.getName());
        }

        System.out.println("\n--- Step 3: Create and populate names list ---");
        // 3. Create ArrayList<String> names and add names from pets.
        List<String> names = new ArrayList<>();
        for (Pet pet : pets) {
            names.add(pet.getName());
        }
        System.out.println("Initial names: " + names);

        // We will duplicate the list to demonstrate both Step 4 and Step 5 independently.
        List<String> namesForIteratorRemoval = new ArrayList<String>(names);
        List<String> namesForLoopRemoval = new ArrayList<String>(names);

        System.out.println("\n--- Step 4: Remove != 5 letters (USING Iterator) ---");
        // 4. Remove all strings from names that are not 5 letters long using an iterator.
        Iterator<String> removalIterator = namesForIteratorRemoval.iterator();
        while (removalIterator.hasNext()) {
            String currentName = removalIterator.next();
            if (currentName.length() != 5) {
                removalIterator.remove(); // Safely removes the current element
            }
        }
        System.out.println("Names after iterator removal: " + namesForIteratorRemoval);

        System.out.println("\n--- Step 5: Remove != 5 letters (WITHOUT Iterator) ---");
        /*
         * Attempting to remove elements from a collection directly (list.remove())
         * while iterating over it with a for-each loop throws a
         * ConcurrentModificationException.
         *
         * A for-each loop implicitly uses an Iterator. By calling the list's
         * remove method instead of the Iterator's remove method, the collection's
         * modification count goes out of sync with what the implicit Iterator expects.
         */
        try {
            for (String currentName : namesForLoopRemoval) {
                if (currentName.length() != 5) {
                    namesForLoopRemoval.remove(currentName); // Triggers the exception
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Exception caught: ConcurrentModificationException!");
            System.out.println("Explanation: You cannot structurally modify a collection while iterating it with a for-each loop.");
        }
        System.out.println("Names after failed for-each removal: " + namesForLoopRemoval);

        System.out.println("\n--- Step 6: Print reverse order ---");
        // 6. Print the contents of names in reverse order.
        // We will use the successfully filtered list from Step 4
        Collections.reverse(namesForIteratorRemoval);
        System.out.println("Reversed filtered names: " + namesForIteratorRemoval);
    }
}