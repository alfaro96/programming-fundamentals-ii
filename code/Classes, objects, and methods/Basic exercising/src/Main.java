/**
 * Laboratory assignment 1: tries every constructor and method of the {@link ComplexNumber}
 * class.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see ComplexNumber
 */
class Main {

    /**
     * Creates complex numbers with the four constructors, operates with them and prints each
     * result next to the value it should have, worked out by hand.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        ComplexNumber zero = new ComplexNumber();
        ComplexNumber c1 = new ComplexNumber(1, 2);
        ComplexNumber c2 = new ComplexNumber(3.5, 2.0);
        ComplexNumber copy = new ComplexNumber(c2);
        System.out.println("zero = " + zero.describe() + " (should be 0.0 + 0.0i)");
        System.out.println("c1 = " + c1.describe() + " (should be 1.0 + 2.0i)");
        System.out.println("c2 = " + c2.describe() + " (should be 3.5 + 2.0i)");
        System.out.println("copy of c2 = " + copy.describe() + " (should be 3.5 + 2.0i)");

        // The copy is a different object: changing it does not change c2
        copy.real = 0;
        System.out.println("c2 after changing its copy = " + c2.describe() + " (should be 3.5 + 2.0i)");

        System.out.println("c1 + c2 = " + c1.add(c2).describe() + " (should be 4.5 + 4.0i)");
        System.out.println("c1 - c2 = " + c1.subtract(c2).describe() + " (should be -2.5 + 0.0i)");
        System.out.println("c1 * 3 = " + c1.multiply(3.0).describe() + " (should be 3.0 + 6.0i)");
        System.out.println("c1 * c2 = " + c1.multiply(c2).describe() + " (should be -0.5 + 9.0i)");

        // i * i = -1
        ComplexNumber i = new ComplexNumber(0, 1);
        System.out.println("i * i = " + i.multiply(i).describe() + " (should be -1.0 + 0.0i)");

        // 0.1 + 0.2 is not exactly 0.3, but within EPSILON
        ComplexNumber a = new ComplexNumber(0.1 + 0.2, 1.0);
        ComplexNumber b = new ComplexNumber(0.3, 1.0);
        System.out.println("a = " + a.describe() + ", b = " + b.describe());
        System.out.println("a equals b: " + a.isEqualTo(b) + " (should be true)");
        System.out.println("c1 equals c2: " + c1.isEqualTo(c2) + " (should be false)");
    }
}
