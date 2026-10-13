/**
 * Represents a person: their personal data and their children, who are people too.
 * <p>
 * The children are kept in an array of fixed size, filled from the start: the empty
 * positions, all at the end, hold {@code null}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
class Person {

    /**
     * The name of the person.
     */
    String name;

    /**
     * The age of the person, in years.
     */
    int age;

    /**
     * The height of the person, in meters.
     */
    double height;

    /**
     * The weight of the person, in kilograms.
     */
    double weight;

    /**
     * The address of the person.
     */
    String address;

    /**
     * The children of the person, with {@code null} in the positions not yet filled.
     */
    Person[] children;

    /**
     * Creates a person with room for the given number of children, none of them added yet.
     *
     * @param name the name of the person.
     * @param age the age of the person, in years.
     * @param height the height of the person, in meters.
     * @param weight the weight of the person, in kilograms.
     * @param address the address of the person.
     * @param numberOfChildren the number of children the person can have.
     */
    Person(String name, int age, double height, double weight, String address, int numberOfChildren) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.children = new Person[numberOfChildren];
    }

    /**
     * Creates a person without children, whose array of children is empty.
     *
     * @param name the name of the person.
     * @param age the age of the person, in years.
     * @param height the height of the person, in meters.
     * @param weight the weight of the person, in kilograms.
     * @param address the address of the person.
     */
    Person(String name, int age, double height, double weight, String address) {
        this(name, age, height, weight, address, 0);
    }

    /**
     * Adds a child in the first empty position of the array of children.
     * <p>
     * The array never grows: if it is full, an error message is printed and the child is
     * not added.
     *
     * @param child the child to add.
     */
    void addChild(Person child) {
        for (int i = 0; i < this.children.length; i++) {
            if (this.children[i] == null) {
                this.children[i] = child;
                return;
            }
        }
        System.out.println("Error: " + this.name + " cannot have more than " + this.children.length + " children");
    }

    /**
     * Finds the oldest child. If several are the oldest, returns the first one added.
     *
     * @return the oldest child, or {@code null} if the person has no children.
     */
    Person getOldestChild() {
        Person oldest = null;
        for (Person child : this.children) {
            if (child != null && (oldest == null || child.age > oldest.age)) {
                oldest = child;
            }
        }
        return oldest;
    }

    /**
     * Finds the youngest child. If several are the youngest, returns the first one added.
     *
     * @return the youngest child, or {@code null} if the person has no children.
     */
    Person getYoungestChild() {
        Person youngest = null;
        for (Person child : this.children) {
            if (child != null && (youngest == null || child.age < youngest.age)) {
                youngest = child;
            }
        }
        return youngest;
    }

    /**
     * Returns all the data of the person, followed by that of each child in brackets,
     * such as {@code "Ana, 40 years, 1.65 m, 60.0 kg, Main St 1, children: [Luis, 8 years,
     * 1.3 m, 28.0 kg, Main St 1, children: []]"}.
     * <p>
     * The data of each child comes from calling this same method on the child. The text is
     * built in a {@link StringBuilder}, which grows instead of being copied for each child.
     *
     * @return the data of the person and of their children.
     */
    String describe() {
        StringBuilder text = new StringBuilder();
        text.append(this.name).append(", ").append(this.age).append(" years, ")
                .append(this.height).append(" m, ").append(this.weight).append(" kg, ")
                .append(this.address).append(", children: [");
        boolean first = true;
        for (Person child : this.children) {
            if (child != null) {
                if (!first) {
                    text.append("; ");
                }
                text.append(child.describe());
                first = false;
            }
        }
        return text.append("]").toString();
    }
}
