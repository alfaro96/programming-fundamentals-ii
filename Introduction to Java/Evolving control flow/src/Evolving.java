public class Evolving {

   public static void main(String[] args) {

      // 1. Strict boolean conditions
      int status = 1;
      // if (status) { } // int cannot be converted to boolean
      if (status == 1) { // Expression must be strictly boolean
         System.out.println("Status is active.");
      }

      // 2. The enhanced for loop (for-each)
      String[] colors = {"Red", "Green", "Blue"};
      System.out.println("Iterating through colors:");
      for (String color : colors) {
         System.out.println("- " + color);
      }

      // 3. Switch with strings
      String dayType = "Weekend";
      switch (dayType) {
         case "Weekday":
            System.out.println("Time to work!");
            break;
         case "Weekend":
            System.out.println("Time to rest!");
            break;
         default:
            System.out.println("Unknown day type.");
      }
   }
}
