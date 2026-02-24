package playerRoles;

import java.util.Objects;

/**
 * Represents a base professional player in the system.
 * Demonstrates the implementation of inherited {@link Object} methods.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The full name of the player. */
    private String name;

    /** The current age of the player in years. */
    private int age;

    /**
     * Constructs a new {@link Player}.
     *
     * @param name The player's full name.
     * @param age The player's age.
     */
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Copy constructor.
     * The recommended way to create object duplicates, avoiding the discouraged {@link Object#clone} method.
     *
     * @param other The {@link Player} instance to copy.
     */
    public Player(Player other) {
        this(other.name, other.age);
    }

    /**
     * Gets the player's name.
     * @return The name of the player.
     */
    public String getName() { return name; }

    /**
     * Gets the player's age.
     * @return The age of the player.
     */
    public int getAge() { return age; }

    /**
     * Returns a string representation of the object.
     * This is essential for debugging and logging purposes.
     *
     * @return A descriptive string containing the player's data.
     */
    @Override
    public String toString() {
        return "Player{name='" + name + "', age=" + age + "}";
    }

    /**
     * Compares two instances to check if they are logically equivalent.
     * Checks data equality rather than just comparing memory addresses.
     *
     * @param o The object to compare with this player.
     * @return {@code true} if both objects have the same name and age; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Both point to the same memory address
        if (o == null || this.getClass() != o.getClass()) return false; // Invalid memory address or not being from the same class
        Player player = (Player) o; // Safe casting, we know the input object is from the same class
        return age == player.age && Objects.equals(name, player.name);
    }
}
