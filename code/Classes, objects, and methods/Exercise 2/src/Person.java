/**
 * Represents a person with physical attributes and a registry of children.
 * This class demonstrates object relationships, array management, and recursion.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Person {

    /**
     * The name of the person.
     */
    public String name;

    /**
     * The age in years.
     */
    public int age;

    /**
     * The height in meters.
     */
    public double height;

    /**
     * The weight in kilograms.
     */
    public double weight;

    /**
     * The residential address.
     */
    public String address;

    /**
     * A fixed-size array containing references to the person's children.
     */
    public Person[] children;

    /**
     * Counter for the children currently added to the array.
     */
    private int currentChildrenCount = 0;

    /**
     * Constructs a {@link Person} with a specific capacity for children.
     *
     * @param name             Name of the person.
     * @param age              Age in years.
     * @param height           Height in meters.
     * @param weight           Weight in kilograms.
     * @param address          Home address.
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
     * @param name    Name of the person.
     * @param age     Age in years.
     * @param height  Height in meters.
     * @param weight  Weight in kilograms.
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
}
