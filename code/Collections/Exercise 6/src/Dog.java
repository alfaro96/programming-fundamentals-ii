/**
 * Represents a {@link Dog}, which is a specific implementation of a {@link Pet}.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Dog extends Pet {

    /**
     * Constructs a new {@link Dog} and initializes its name in the parent class.
     *
     * @param name The {@code String} name of the dog.
     */
    public Dog(String name) {
        super(name);
    }

    /**
     * Specific behavior for dogs that prints a barking sound.
     * Uses {@link Pet#getName()} to retrieve the name.
     */
    public void bark() {
        System.out.println(this.getName() + ": woof!");
    }

    /**
     * Returns a string representation of the {@link Dog} object.
     *
     * @return A {@link String} specific to the {@link Dog} class.
     */
    @Override
    public String toString() {
        return "Dog{name='" + this.getName() + "'}";
    }
}