/**
 * Represents a professional player in the system.
 * This version uses the {@code this} keyword to resolve scope ambiguity.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Player {

   /** The full name of the player. */
   public String name;

   /** The current age of the player in years. */
   public int age;

   /** The country of origin or nationality of the player. */
   public String nationality;

   /**
    * Constructs a new {@link Player} using {@code this} to resolve shadowing.
    *
    * @param name The full name of the player.
    * @param age The current age in years.
    * @param nationality The country of origin.
    */
   public Player(String name, int age, String nationality) {
      // Here "this.name" refers to the attribute, while "name" is the parameter
      this.name = name;
      this.age = age;
      this.nationality = nationality;
   }

   /**
    * Performs a celebration.
    * Uses {@code this} to explicitly access the instance field {@link #name}.
    */
   public void celebrate() {
      System.out.println(this.name + " performs a signature jump!");
   }

   /**
    * Scores a goal and then uses {@code this} to trigger {@link #celebrate()}.
    */
   public void scoreGoal() {
      System.out.println(this.name + " has scored!");

      // Internal delegation via "this"
      this.celebrate();
   }

   /**
    * Simulates training using {@code this} for architectural clarity.
    *
    * @param exercise The training drill name.
    * @param minutes The duration in minutes.
    */
   public void train(String exercise, int minutes) {
      System.out.println(this.name + " trains " + exercise + " for " + minutes + " minutes.");
   }

   /**
    * Calculates years to reach a target using the instance's {@code this.age}.
    *
    * @param targetAge The age to compare against.
    * @return Years remaining.
    */
   public int calculateYearsToAge(int targetAge) {
      return targetAge - this.age;
   }
}
