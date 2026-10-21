/**
 * Represents a cat with a name, which meows.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Dog
 */
public class Cat {

    /** The name of the cat. */
    private String name;

    /**
     * Creates a cat with a name.
     *
     * @param name the name of the cat.
     */
    public Cat(String name) {
        this.name = name;
    }

    /**
     * Prints the name of the cat followed by its meow.
     */
    public void meow() {
        System.out.println(this.name + ": meow!");
    }

    /**
     * Returns a description of the cat.
     *
     * @return {@code "I am the cat "} followed by its name.
     */
    @Override
    public String toString() {
        return "I am the cat " + this.name;
    }
}
