package playerRoles;

/**
 * Represents a forward player, extending the base {@link Player} class.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Forward extends Player {

    /** The number of goals scored by the forward. */
    private int goals;

    /**
     * Constructs a new {@link Forward}.
     *
     * @param name The player's full name.
     * @param age The player's age.
     * @param goals The initial amount of goals scored.
     */
    public Forward(String name, int age, int goals) {
        super(name, age);
        this.goals = goals;
    }

    /**
     * Copy constructor for {@link Forward}.
     * Uses the parent's copy constructor to ensure safe duplication.
     *
     * @param other The {@link Forward} instance to copy.
     */
    public Forward(Forward other) {
        super(other);
        this.goals = other.goals;
    }

    /**
     * Returns a string representation of the forward.
     * Includes inherited attributes plus the specific goals count.
     *
     * @return A descriptive string of the forward.
     */
    @Override
    public String toString() {
        return "Forward{name='" + getName() + "', age=" + getAge() + ", goals=" + goals + "}";
    }

    /**
     * Compares two forwards for logical equivalence.
     * Checks both the parent's attributes and the specific forward attributes.
     *
     * @param o The object to compare.
     * @return {@code true} if it is the same logical forward.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Forward forward = (Forward) o;
        return goals == forward.goals;
    }
}
