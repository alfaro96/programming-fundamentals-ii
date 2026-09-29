/**
 * Represents a {@link Cat} with a specific name and behavior.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Cat {

    /**
     * Private state to store the cat's name.
     */
    private String name;

    /**
     * Constructor that initializes the cat's name.
     *
     * @param name The name of the cat.
     */
    public Cat(String name) {
        this.name = name;
    }

    /**
     * Specific behavior for the cat.
     * Prints the cat's name followed by a meowing sound.
     */
    public void meow() {
        System.out.println(this.name + ": meow!");
    }

    /**
     * Overrides the default {@code toString()} method from the {@link Object} class.
     *
     * @return A custom string representation of the cat.
     */
    @Override
    public String toString() {
        return "I am the cat " + this.name;
    }
}