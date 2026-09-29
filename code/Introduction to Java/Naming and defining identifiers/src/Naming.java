public class Naming {

   public static void main(String[] args) {

      // Valid names
      int playerAge = 20; // Standard
      double $price = 19.99; // Starting with $ is allowed
      String _name = "Alex"; // Starting with _ is allowed
      int score2 = 100; // Numbers are allowed if not at the start
      System.out.println("All these variables compiled correctly!");

      // Invalid names
      // int 2ndPlayer = 10; // Cannot start with a digit
      // double void = 5.5; // "void" is a reserved keyword
      // String user-name = "A"; // Hyphens are not allowed
      // int total score = 50; // Spaces are not allowed
   }
}
