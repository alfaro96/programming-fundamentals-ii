package playerRoles;

/**
 * Represents a professional player in the system.
 * This class acts as the superclass, containing common attributes and behaviors.
 * It demonstrates the four levels of data encapsulation and access restriction.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

    /** The full name of the player. */
    public String name;

    /** The current age of the player in years. */
    protected int age;

    /** The country of origin or nationality of the player. */
    String nationality;

    /** The confidential medical status of the player. */
    private String medicalCondition;

    /**
     * Default constructor.
     * Required to allow subclasses to initialize inherited attributes directly.
     */
    public Player() {

    }

    /**
     * Constructs a new {@link Player}.
     *
     * @param name The full name of the player.
     * @param age The current age in years.
     * @param nationality The country of origin.
     * @param medicalCondition The confidential medical status.
     */
    public Player(String name, int age, String nationality, String medicalCondition) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.medicalCondition = medicalCondition;
    }

    /**
     * Performs a celebration.
     */
    public void celebrate() {
        System.out.println(this.name + " performs a signature jump!");
    }

    /**
     * Safely exposes private data.
     */
    public void printMedicalRecord() {
        System.out.println("Medical record for " + this.name + ": " + this.medicalCondition);
    }
}
