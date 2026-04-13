/**
 * Represents a {@link Dog}, which is a specific type of {@link Pet}.
 * <p>
 * Inherits the name state from the parent class and adds dog-specific behavior.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Dog extends Pet {

    /**
     * Constructor that initializes the inherited state.
     *
     * @param name The name of the dog.
     */
    public Dog(String name) {
        super(name);
    }

    /**
     * Specific behavior for the dog.
     * Prints the dog's name followed by a barking sound.
     */
    public void bark() {
        System.out.println(this.getName() + ": woof!");
    }
}
