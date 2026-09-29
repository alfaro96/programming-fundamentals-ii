import playerRoles.Forward;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class demonstrates how to use multiple catch blocks in the
 * strictly required order: from most specific to most general.
 */
public class Main {

   /**
    * Starts the application and handles different transfer errors.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      Forward myForward = new Forward();

      System.out.println("Main: The transfer window is open.");

      try {
         // We intentionally pass "null" and "0" to trigger errors.
         // Java will stop at the first error it encounters inside the method.
         myForward.processTransfer(null, 5000000, 0);

      } catch (ArithmeticException e) {
         // Specific catch 1: Handles division by zero
         System.out.println("Main [CAUGHT ARITHMETIC]: Cannot divide fee into zero installments.");

      } catch (NullPointerException e) {
         // Specific catch 2: Handles missing objects (nulls)
         System.out.println("Main [CAUGHT NULL POINTER]: The team name is missing.");

      } catch (Exception e) {
         // General catch: The ultimate safety net
         // This MUST be at the bottom. If placed at the top, it would catch everything
         // and trigger a compilation error because the blocks below would be unreachable.
         System.out.println("Main [CAUGHT GENERAL EXCEPTION]: An unexpected error occurred.");
      }

      System.out.println("Main: Transfer processing sequence finished.");
   }
}