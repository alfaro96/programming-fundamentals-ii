/**
 * Represents a {@link Cat}, which is a specific implementation of a {@link Pet}.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Cat extends Pet {

    /**
     * Constructs a new {@link Cat} and initializes its name in the parent class.
     *
     * @param name The {@code String} name of the cat.
     */
    public Cat(String name) {
        super(name);
    }

    /**
     * Specific behavior for cats that prints a meowing sound.
     * Uses {@link Pet#getName()} to retrieve the name.
     */
    public void meow() {
        System.out.println(this.getName() + ": meow!");
    }

    /**
     * Returns a string representation of the {@link Cat} object.
     *
     * @return A {@link String} specific to the {@link Cat} class.
     */
    @Override
    public String toString() {
        return "Cat{name='" + this.getName() + "'}";
    }
}