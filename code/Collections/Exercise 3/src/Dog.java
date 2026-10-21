/**
 * Represents a dog with a name, which barks.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Cat
 */
public class Dog {

    /** The name of the dog. */
    private String name;

    /**
     * Creates a dog with a name.
     *
     * @param name the name of the dog.
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * Prints the name of the dog followed by its bark.
     */
    public void bark() {
        System.out.println(this.name + ": woof!");
    }

    /**
     * Returns a description of the dog.
     *
     * @return {@code "I am the dog "} followed by its name.
     */
    @Override
    public String toString() {
        return "I am the dog " + this.name;
    }
}
