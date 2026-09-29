/**
 * Main application class to demonstrate the {@link Person} class.
 * Creates several persons, adds children, and exercises all their operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Create Person 1 with capacity for 2 children
        Person person1 = new Person("Alice", 35, 1.68, 62.0, "123 Main St", 2);
        System.out.println("--- Person 1 created (capacity: 2 children) ---");
        System.out.println(person1);
        System.out.println();

        // 2. Create Person 2 with capacity for 3 children
        Person person2 = new Person("Bob", 42, 1.80, 80.0, "456 Elm Ave", 3);
        System.out.println("--- Person 2 created (capacity: 3 children) ---");
        System.out.println(person2);
        System.out.println();

        // 3. Create Person 3 using the childless constructor
        Person person3 = new Person("Helen", 29, 1.65, 55.0, "789 Oak Rd");
        System.out.println("--- Person 3 created (childless constructor) ---");
        System.out.println("Children array length (expected 0): " + person3.children.length);
        System.out.println(person3);
        System.out.println();

        // 4. Create children and add them to Person 1
        Person child1a = new Person("Charlie",  8, 1.20, 25.0, "123 Main St");
        Person child1b = new Person("Diana",   10, 1.35, 30.0, "123 Main St");
        person1.addChild(child1a);
        person1.addChild(child1b);
        System.out.println("--- Added 2 children to Person 1 ---");
        System.out.println(person1);
        System.out.println();

        // 5. Create children and add them to Person 2
        Person child2a = new Person("Edward",   5, 1.05, 17.0, "456 Elm Ave");
        Person child2b = new Person("Fiona",   13, 1.55, 45.0, "456 Elm Ave");
        Person child2c = new Person("George",   3, 0.95, 14.0, "456 Elm Ave");
        person2.addChild(child2a);
        person2.addChild(child2b);
        person2.addChild(child2c);
        System.out.println("--- Added 3 children to Person 2 ---");
        System.out.println(person2);
        System.out.println();

        // 6. Change the age of Person 1 and verify the change
        System.out.println("--- Change age of Person 1 ---");
        System.out.println("Age before change: " + person1.age);
        person1.age = 36;
        System.out.println("Age after change (expected 36): " + person1.age);
        System.out.println();

        // 7. Retrieve and display the children array of Person 1
        System.out.println("--- Children array of Person 1 ---");
        for (int i = 0; i < person1.children.length; i++) {
            if (person1.children[i] != null) {
                System.out.println("children[" + i + "]: " + person1.children[i].name
                        + " (age " + person1.children[i].age + ")");
            }
        }
        System.out.println();

        // 8. Get oldest and youngest child of Person 2
        Person oldest   = person2.getOldestChild();
        Person youngest = person2.getYoungestChild();
        System.out.println("--- Oldest and youngest child of Person 2 ---");
        System.out.println("Oldest (expected Fiona, age 13): "
                + oldest.name + ", age " + oldest.age);
        System.out.println("Youngest (expected George, age 3): "
                + youngest.name + ", age " + youngest.age);
        System.out.println();

        // 9. Attempt to overflow the children array of Person 1 (capacity is 2, already full)
        System.out.println("--- Overflow test: add a third child to Person 1 (capacity 2) ---");
        Person extra = new Person("Extra", 1, 0.80, 10.0, "123 Main St");
        person1.addChild(extra); // should print an error, not throw
        System.out.println("(No exception thrown - error was handled internally)");
        System.out.println();
    }
}
