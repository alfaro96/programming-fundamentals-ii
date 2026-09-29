/**
 * Base class representing a generic {@link Pet}.
 * <p>
 * This class manages the common state (name) and behavior (speak) 
 * for all types of pets in the hierarchy.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Pet {

    /**
     * Private state to store the pet's name.
     */
    private String name;

    /**
     * Constructor to initialize the pet's name.
     *
     * @param name The name of the pet.
     */
    public Pet(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the pet. 
     * Provided for subclasses to access the private state safely.
     *
     * @return The name of the pet.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Generic behavior for any pet.
     * Prints a generic sound message using the pet's name.
     */
    public void speak() {
        System.out.println(this.name + " makes a sound.");
    }
}