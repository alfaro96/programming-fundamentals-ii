/**
 * Base class representing a generic {@link Pet}.
 * <p>
 * This class serves as the root of the pet hierarchy, managing shared 
 * attributes like the name.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Dog
 * @see Cat
 */
public class Pet {

    /**
     * The unique name of the pet.
     */
    private String name;

    /**
     * Constructs a new {@link Pet} with the specified name.
     *
     * @param name The {@link String} representing the pet's name.
     */
    public Pet(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the pet.
     *
     * @return A {@link String} containing the pet's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Standard behavior for any pet.
     * Prints a message indicating the pet is making a generic sound.
     */
    public void speak() {
        System.out.println(this.name + " makes a sound.");
    }

    /**
     * Returns a string representation of the {@link Pet} object.
     *
     * @return A {@code String} formatted with the class name and pet name.
     */
    @Override
    public String toString() {
        return "Pet{name='" + name + "'}";
    }
}