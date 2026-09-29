/**
 * Represents a {@link Cat}, which is a specific type of {@link Pet}.
 * <p>
 * Inherits the name state from the parent class and adds cat-specific behavior.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Cat extends Pet {

    /**
     * Constructor that initializes the inherited state.
     *
     * @param name The name of the cat.
     */
    public Cat(String name) {
        super(name);
    }

    /**
     * Specific behavior for the cat.
     * Prints the cat's name followed by a meowing sound.
     */
    public void meow() {
        System.out.println(this.getName() + ": meow!");
    }
}