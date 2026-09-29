public class Conversion {

   public static void main(String[] args) {

      // Widening (implicit conversion)
      int smallValue = 100;
      double largeValue = smallValue; // Automatically converted from int to double
      System.out.println("Widening (int to double): " + largeValue);

      // Narrowing (explicit conversion)
      double pi = 3.14159;
      // int truncatedPi = pi; // Incompatible types (possible lossy conversion)
      int truncatedPi = (int) pi; // Explicit cast: tells the compiler we accept the loss
      System.out.println("Narrowing (double to int): " + truncatedPi);

      // Working with suffixes
      long bigNumber = 9000000000L; // "L" suffix is required for long literals
      float price = 19.99f; // "f" suffix is required for float literals

      // Narrowing long to short (risk of overflow)
      short smallContainer = (short) bigNumber;
      System.out.println("Narrowing with overflow: " + smallContainer);
   }
}
