public class Initialization {

   public static void main(String[] args) {

      // Initializing an array using the short-hand syntax
      int[] scores = {95, 88, 72, 100};

      System.out.println("The scores array has " + scores.length + " elements.");

      // Using the length property to ensure we visit every initialized element
      for (int i = 0; i < scores.length; i++) {
         System.out.println("Score " + (i + 1) + ": " + scores[i]);
      }
   }
}