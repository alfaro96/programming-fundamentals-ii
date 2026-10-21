/**
 * Represents a cat, a pet that also meows.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Cat extends Pet {

    /**
     * Creates a cat with a name.
     *
     * @param name the name of the cat.
     */
    public Cat(String name) {
        super(name);
    }

    /**
     * Prints the name of the cat followed by its meow.
     */
    public void meow() {
        System.out.println(this.getName() + ": meow!");
    }
}
