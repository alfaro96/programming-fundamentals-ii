/**
 * Represents a pet with a name, which makes a sound.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Dog
 * @see Cat
 */
public class Pet {

    /** The name of the pet. */
    private String name;

    /**
     * Creates a pet with a name.
     *
     * @param name the name of the pet.
     */
    public Pet(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the pet.
     *
     * @return the name of the pet.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Prints the name of the pet followed by {@code " makes a sound."}.
     */
    public void speak() {
        System.out.println(this.name + " makes a sound.");
    }
}
