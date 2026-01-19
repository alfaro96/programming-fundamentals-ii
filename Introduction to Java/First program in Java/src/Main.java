/**
 * Laboratory Assignment 0: Introduction to the compiler.
 * This class implements and compares iterative and formulaic summation methods.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Calculates the sum of the first n natural numbers using an iterative approach.
    *
    * @param n The upper limit of the summation.
    * @return The accumulated sum of integers from 0 up to n.
    */
   public static int sum1(int n) {
      int sum = 0;
      for (int i = 0; i <= n; i++) {
         sum = sum + i;
      }
      return sum;
   }

   /**
    * Calculates the sum of the first n natural numbers using the provided formula.
    *
    * @param n The upper limit of the summation.
    * @return The result of the mathematical formula.
    */
   public static int sum2(int n) {
      return (n * (n + 1)) / 2;
   }

   /**
    * Main entry point of the program.
    * It is used to call and verify the summation methods.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // Individual test: Value 11
      int testValue = 11;
      System.out.println("Individual test for n = " + testValue);
      System.out.println("sum1: " + sum1(testValue));
      System.out.println("sum2: " + sum2(testValue));
      System.out.println();

      // Batch testing with array
      int[] data = {3, 4, 13, 21, 67, 102, 155, 365, 1007};
      System.out.println("--- Automated verification ---");

      for (int n : data) {
         int res1 = sum1(n);
         int res2 = sum2(n);

         // Automation: Compare results directly
         boolean match = (res1 == res2);

         System.out.println("Input: " + n + " | Match: " + match + " (Value: " + res1 + ")");
      }
   }
}
