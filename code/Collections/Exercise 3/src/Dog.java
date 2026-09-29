/**
 * Represents a {@link Dog} with a specific name and behavior.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Dog {

    /**
     * Private state to store the dog's name.
     */
    private String name;

    /**
     * Constructor that initializes the dog's name.
     *
     * @param name The name of the dog.
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * Specific behavior for the dog.
     * Prints the dog's name followed by a barking sound.
     */
    public void bark() {
        System.out.println(this.name + ": woof!");
    }

    /**
     * Overrides the default {@code toString()} method from the {@link Object} class.
     *
     * @return A custom string representation of the dog.
     */
    @Override
    public String toString() {
        return "I am the dog " + this.name;
    }
}