/**
 * Represents a person with physical attributes and a registry of children.
 * This class demonstrates object relationships, array management, and recursion.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Person {

    /** The name of the person. */
    public String name;

    /** The age in years. */
    public int age;

    /** The height in meters. */
    public double height;

    /** The weight in kilograms. */
    public double weight;

    /** The residential address. */
    public String address;

    /** A fixed-size array containing references to the person's children. */
    public Person[] children;

    /** Counter for the children currently added to the array. */
    private int currentChildrenCount = 0;

    /**
     * Constructs a {@link Person} with a specific capacity for children.
     *
     * @param name Name of the person.
     * @param age Age in years.
     * @param height Height in meters.
     * @param weight Weight in kilograms.
     * @param address Home address.
     * @param numberOfChildren The fixed size of the children array.
     */
    public Person(String name, int age, double height, double weight, String address, int numberOfChildren) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.children = new Person[numberOfChildren];
    }

    /**
     * Constructs a childless {@link Person}.
     * The children array is initialized with size 0.
     *
     * @param name Name of the person.
     * @param age Age in years.
     * @param height Height in meters.
     * @param weight Weight in kilograms.
     * @param address Home address.
     */
    public Person(String name, int age, double height, double weight, String address) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.children = new Person[0];
    }

    /**
     * Adds a child to the array if there is available space.
     * Displays an error if the array is full.
     *
     * @param child The {@link Person} to be added.
     */
    public void addChild(Person child) {
        if (this.currentChildrenCount < this.children.length) {
            this.children[this.currentChildrenCount] = child;
            this.currentChildrenCount++;
        } else {
            System.out.println("Error: The children array for " + this.name + " is full.");
        }
    }

    /**
     * Identifies the oldest child in the registry.
     *
     * @return The child with the highest age, or {@code null} if no children exist.
     */
    public Person getOldestChild() {
        if (this.currentChildrenCount == 0) return null;

        Person oldest = this.children[0];
        for (int i = 1; i < this.currentChildrenCount; i++) {
            if (this.children[i].age > oldest.age) {
                oldest = this.children[i];
            }
        }
        return oldest;
    }

    /**
     * Identifies the youngest child in the registry.
     *
     * @return The child with the lowest age, or {@code null} if no children exist.
     */
    public Person getYoungestChild() {
        if (this.currentChildrenCount == 0) return null;

        Person youngest = this.children[0];
        for (int i = 1; i < this.currentChildrenCount; i++) {
            if (this.children[i].age < youngest.age) {
                youngest = this.children[i];
            }
        }
        return youngest;
    }

    /**
     * Returns a recursive string representation of the person and their children.
     *
     * @return Formatted string with all personal and offspring information.
     */
    @Override
    public String toString() {
        String info = "Name: " + this.name + " (Age: " + this.age + "), Address: " + this.address;

        if (this.currentChildrenCount > 0) {
            info += "\n  Children of " + this.name + ":";
            for (int i = 0; i < this.currentChildrenCount; i++) {
                // Recursive call with indentation for clarity
                info += "\n    - " + this.children[i].toString().replace("\n", "\n    ");
            }
        }
        return info;
    }

    /**
     * Entry point to verify all exercise requirements.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // 1. Object creation and setup 
        Person p1 = new Person("Alice Smith", 42, 1.68, 62.0, "123 Maple St", 2);
        Person p2 = new Person("Bob Brown", 50, 1.82, 88.0, "456 Oak Ave", 3);
        Person p3 = new Person("Helen White", 28, 1.72, 65.0, "789 Pine Rd");

        // 2. Adding children 
        p1.addChild(new Person("Charlie", 12, 1.55, 45.0, "123 Maple St"));
        p1.addChild(new Person("Daisy", 9, 1.35, 32.0, "123 Maple St"));

        p2.addChild(new Person("Edward", 20, 1.78, 75.0, "456 Oak Ave"));
        p2.addChild(new Person("Fiona", 16, 1.62, 54.0, "456 Oak Ave"));
        p2.addChild(new Person("George", 23, 1.88, 92.0, "456 Oak Ave"));

        // 3. Functional verification 
        System.out.println("--- Full family registry ---");
        System.out.println(p1.toString() + "\n");
        System.out.println(p2.toString() + "\n");
        System.out.println(p3.toString() + "\n");

        System.out.println("--- Update test ---");
        p1.age = 43; // Change age 
        System.out.println("New age of " + p1.name + ": " + p1.age);

        System.out.println("\n--- Exercise 2 1 offspring list  ---");
        for (Person child : p1.children) {
            System.out.println("-> Child: " + child.name + " (" + child.age + " years old)");
        }

        System.out.println("\n--- Exercise 2 2 age extremes  ---");
        System.out.println("Youngest child: " + p2.getYoungestChild().name);
        System.out.println("Oldest child: " + p2.getOldestChild().name);
    }
}