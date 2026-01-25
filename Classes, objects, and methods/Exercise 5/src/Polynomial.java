/**
 * Represents a mathematical polynomial composed of {@link Monomial} objects.
 * This implementation maps array indices directly to monomial exponents.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Polynomial {

   /** Array of monomials where the index corresponds to the exponent. */
   public Monomial[] monomials;

   /** The maximum capacity of the polynomial. */
   public int maxSize;

   /** The current number of monomials in the polynomial. */
   public int numMonomials;

   /**
    * Constructs an empty polynomial with a specified capacity.
    *
    * @param maxSize The maximum size for the monomial array.
    */
   public Polynomial(int maxSize) {
      this.maxSize = maxSize;
      this.monomials = new Monomial[maxSize];
      this.numMonomials = 0;
   }

   /**
    * Constructs a polynomial from an array of coefficients.
    *
    * @param coefficients Array where the index is the exponent.
    */
   public Polynomial(double[] coefficients) {
      this.maxSize = coefficients.length;
      this.monomials = new Monomial[this.maxSize];
      this.numMonomials = 0;
      for (int i = 0; i < coefficients.length; i++) {
         if (coefficients[i] != 0) {
            this.monomials[i] = new Monomial(coefficients[i], i);
            this.numMonomials++;
         }
      }
   }

   /**
    * Constructs a polynomial from an array of {@link Monomial} objects.
    *
    * @param monomials Input array of {@link Monomial}.
    */
   public Polynomial(Monomial[] monomials) {
      this.maxSize = monomials.length;
      this.monomials = new Monomial[this.maxSize];
      this.numMonomials = 0;
      for (int i = 0; i < monomials.length; i++) {
         if (monomials[i] != null) {
            this.monomials[i] = new Monomial(monomials[i].coefficient, monomials[i].exponent);
            this.numMonomials++;
         }
      }
   }

   /**
    * Adds a monomial to the polynomial at the position matching its exponent.
    * @param m The {@link Monomial} to add.
    */
   public void addMonomial(Monomial m) {
      if (m != null && m.exponent < this.maxSize) {
         if (this.monomials[m.exponent] == null) {
            this.numMonomials++;
         }
         this.monomials[m.exponent] = m;
      }
   }

   /**
    * Solves the polynomial for a specific value of X.
    *
    * @param xValue The value to substitute for X.
    * @return The numerical result.
    */
   public double solve(double xValue) {
      double result = 0;
      for (Monomial m : this.monomials) {
         if (m != null) {
            result += m.evaluate(xValue);
         }
      }
      return result;
   }

   /**
    * Returns a human-legible representation of the polynomial.
    *
    * @return Formatted string of the equation.
    */
   @Override
   public String toString() {
      if (this.numMonomials == 0) return "0";
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < this.maxSize; i++) {
         if (this.monomials[i] != null) {
            if (sb.length() > 0 && this.monomials[i].coefficient > 0) sb.append(" + ");
            sb.append(this.monomials[i].toString());
         }
      }
      return sb.toString();
   }

   /**
    * Entry point to verify the {@link Polynomial} class requirements.
    * <p>
    * This method tests:
    * <ul>
    * <li>Initialization via all three constructors.</li>
    * <li>Adding monomials and solving for specific X values.</li>
    * <li>Verification of polynomial formatting.</li>
    * </ul>
    * </p>
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Object creation
      double[] coeffs = {1.5, 2.0, 0, 3.0}; // 1.5 + 2X + 3X^3
      Polynomial p1 = new Polynomial(coeffs);

      Polynomial p2 = new Polynomial(5);
      p2.addMonomial(new Monomial(4.0, 2)); // 4X^2

      // 2. Display information
      System.out.println("Polynomial 1: " + p1);
      System.out.println("Polynomial 2: " + p2);

      // 3. Solving for X
      // P1(2) = 1.5 + 2(2) + 3(2^3) = 1.5 + 4 + 24 = 29.5
      System.out.println("Result of P1 for X=2: " + p1.solve(2.0));

      // 4. Equality check
      Polynomial p1Clone = new Polynomial(coeffs);
      System.out.println("Is P1 equal to its clone? " + p1.toString().equals(p1Clone.toString()));
   }
}
