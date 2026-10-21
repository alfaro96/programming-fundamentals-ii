/**
 * Represents a dog, a pet that also barks.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Pet
 */
public class Dog extends Pet {

    /**
     * Creates a dog with a name.
     *
     * @param name the name of the dog.
     */
    public Dog(String name) {
        super(name);
    }

    /**
     * Prints the name of the dog followed by its bark.
     */
    public void bark() {
        System.out.println(this.getName() + ": woof!");
    }
}
