package playerRoles;

/**
 * Represents a forward player in our football simulation.
 * <p>
 * This class demonstrates how to manually validate data and throw 
 * an exception object if the internal rules of the game are violated.
 */
public class Forward {

    /**
     * The current energy level of the forward player.
     */
    private int energyLevel = 100;

    /**
     * Updates the player's energy level.
     * <p>
     * In our simulation, energy cannot drop below 0. If a negative value 
     * is provided, the method manually creates and throws an error object.
     *
     * @param energyLevel the new energy level to set
     * @throws IllegalArgumentException if the provided energy level is negative
     */
    public void setEnergyLevel(int energyLevel) {
        // We establish our business rule
        if (energyLevel < 0) {
            // 1. We INSTANTIATE the object using "new IllegalArgumentException"
            // 2. We pass a DESCRIPTIVE MESSAGE as a String argument
            // 3. We use "throw" to LAUNCH it and disrupt the flow
            throw new IllegalArgumentException("Invalid data! Energy level cannot be negative.");
        }

        // This line only runs if the "throw" was NOT executed
        this.energyLevel = energyLevel;
        System.out.println("Forward: Energy level successfully updated.");
    }
}