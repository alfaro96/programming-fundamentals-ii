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
    * Returns the monomial associated with a given exponent.
    *
    * @param exponent The exponent index to retrieve.
    * @return The {@link Monomial} at that position, or {@code null} if it does not exist.
    */
   public Monomial getMonomial(int exponent) {
      if (exponent >= 0 && exponent < this.maxSize) {
         return this.monomials[exponent];
      }
      return null;
   }

   /**
    * Adds a monomial to the polynomial at the position matching its exponent.
    * If a monomial already exists at that position, it is overwritten.
    *
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
    * Adds another polynomial to the current one.
    * Returns a <b>new</b> polynomial representing the sum.
    *
    * @param other The {@link Polynomial} to add.
    * @return A new {@link Polynomial} instance.
    */
   public Polynomial add(Polynomial other) {
      int newSize = Math.max(this.maxSize, other.maxSize);
      Polynomial result = new Polynomial(newSize);

      for (int i = 0; i < newSize; i++) {
         Monomial m1 = this.getMonomial(i);
         Monomial m2 = other.getMonomial(i);

         if (m1 != null && m2 != null) {
            // Add coefficients if both exist
            result.addMonomial(m1.addition(m2));
         } else if (m1 != null) {
            // Copy m1 if m2 is null
            result.addMonomial(new Monomial(m1.coefficient, m1.exponent));
         } else if (m2 != null) {
            // Copy m2 if m1 is null
            result.addMonomial(new Monomial(m2.coefficient, m2.exponent));
         }
      }
      return result;
   }

   /**
    * Multiplies the polynomial by a scalar value.
    * Returns a <b>new</b> polynomial.
    *
    * @param scalar The numerical value to multiply by.
    * @return A new {@link Polynomial} instance.
    */
   public Polynomial scalarProduct(double scalar) {
      Polynomial result = new Polynomial(this.maxSize);
      for (int i = 0; i < this.maxSize; i++) {
         if (this.monomials[i] != null) {
            result.addMonomial(this.monomials[i].scalarProduct(scalar));
         }
      }
      return result;
   }

   /**
    * Multiplies the current polynomial by another polynomial.
    * Returns a <b>new</b> polynomial representing the product.
    *
    * @param other The {@link Polynomial} to multiply with.
    * @return A new {@link Polynomial} instance.
    */
   public Polynomial product(Polynomial other) {
      // The degree of the product is roughly the sum of the max sizes
      int newSize = this.maxSize + other.maxSize;
      Polynomial result = new Polynomial(newSize);

      for (int i = 0; i < this.maxSize; i++) {
         if (this.monomials[i] != null) {
            for (int j = 0; j < other.maxSize; j++) {
               if (other.monomials[j] != null) {
                  Monomial productMonomial = this.monomials[i].product(other.monomials[j]);

                  // Check if we need to accumulate
                  Monomial existing = result.getMonomial(productMonomial.exponent);
                  if (existing != null) {
                     result.addMonomial(existing.addition(productMonomial));
                  } else {
                     result.addMonomial(productMonomial);
                  }
               }
            }
         }
      }
      return result;
   }

   /**
    * Solves the polynomial for a specific value of  $ X $.
    *
    * @param xValue The value to substitute for $ X $.
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
    * Checks if two polynomials are equal by verifying if all their respective monomials are equal.
    *
    * @param o The object to compare with.
    * @return {@code true} if both are polynomials and satisfy equality conditions.
    */
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Polynomial)) return false;

      Polynomial other = (Polynomial) o;
      int checkSize = Math.max(this.maxSize, other.maxSize);

      for (int i = 0; i < checkSize; i++) {
         Monomial m1 = this.getMonomial(i);
         Monomial m2 = other.getMonomial(i);

         if (m1 == null && m2 == null) continue;
         if (m1 == null || m2 == null) return false;

         // Using direct double comparison with epsilon logic for coefficients
         if (Math.abs(m1.coefficient - m2.coefficient) > 1e-9) {
            return false;
         }
      }
      return true;
   }
}