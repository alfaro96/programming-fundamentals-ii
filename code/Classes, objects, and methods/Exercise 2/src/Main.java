/**
 * Exercise 2: tries the {@link Person} class with three people and their children.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Person
 */
class Main {

    /**
     * Creates Person 1 with two children, Person 2 with three and Person 3 without
     * children, and checks each method of {@link Person} on them.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Person 1 and Person 2 have room for their children; Person 3 has none
        Person person1 = new Person("Alice", 35, 1.68, 62.0, "123 Main St", 2);
        Person person2 = new Person("Bob", 42, 1.80, 80.0, "456 Elm Ave", 3);
        Person person3 = new Person("Helen", 29, 1.65, 55.0, "789 Oak Rd");

        person1.addChild(new Person("Charlie", 8, 1.20, 25.0, "123 Main St"));
        person1.addChild(new Person("Diana", 10, 1.35, 30.0, "123 Main St"));

        person2.addChild(new Person("Edward", 5, 1.05, 17.0, "456 Elm Ave"));
        person2.addChild(new Person("Fiona", 13, 1.55, 45.0, "456 Elm Ave"));
        person2.addChild(new Person("George", 3, 0.95, 14.0, "456 Elm Ave"));

        System.out.println("Person 1: " + person1.describe());
        System.out.println("Person 2: " + person2.describe());
        System.out.println("Person 3: " + person3.describe());

        // Changing an attribute changes the object
        person1.age = 36;
        System.out.println("Person 1 after the change: " + person1.describe());

        System.out.println("Children of Person 1:");
        for (Person child : person1.children) {
            System.out.println("  " + child.describe());
        }

        System.out.println("Oldest child of Person 2: " + person2.getOldestChild().name + " (should be Fiona)");
        System.out.println("Youngest child of Person 2: " + person2.getYoungestChild().name + " (should be George)");

        // Person 1 has no room left: this prints an error
        person1.addChild(new Person("Ian", 1, 0.80, 10.0, "123 Main St"));

        // Person 3 has no children
        System.out.println("Oldest child of Person 3: " + person3.getOldestChild() + " (should be null)");
    }
}
