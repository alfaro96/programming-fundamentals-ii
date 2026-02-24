import numbers.*;
import numbers.Integer; // To prevent collisions with java.lang.Integer

/**
 * Main executable class designed to test the numerical hierarchy.
 * Demonstrates dynamic polymorphism and automatic type resolution during 
 * arithmetic operations at runtime.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Main entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        System.out.println("--- 1. Instantiating the real family ---");
        Natural nat1 = new Natural(5);
        Natural nat2 = new Natural(10);
        Integer int1 = new Integer(-3);
        Rational rat1 = new Rational(1, 2);
        Irrational pi = new Irrational(Math.PI);

        System.out.println("Natural object 1: " + nat1);
        System.out.println("Natural object 2: " + nat2);
        System.out.println("Integer object: " + int1);
        System.out.println("Rational object: " + rat1);
        System.out.println("Irrational object: " + pi);

        System.out.println("\n--- 2. Polymorphism and dynamic type returns ---");

        // Natural + Natural = Natural
        Real res1 = nat1.add(nat2);
        System.out.println(nat1 + " + " + nat2 + " = " + res1 + " \t| Returned class: " + res1.getClass().getSimpleName());

        // Natural - Natural = May result in a negative, forcing an Integer return type
        Real res2 = nat1.subtract(nat2);
        System.out.println(nat1 + " - " + nat2 + " = " + res2 + " \t| Returned class: " + res2.getClass().getSimpleName());

        // Integer / Integer = If the division is not exact, automatically returns a Rational
        Real res3 = nat1.divide(nat2);
        System.out.println(nat1 + " / " + nat2 + " = " + res3 + " \t| Returned class: " + res3.getClass().getSimpleName());

        // Rational + Rational = Returns a new Rational calculating implicit cross-multiplication
        Rational rat2 = new Rational(3, 4);
        Real res4 = rat1.add(rat2);
        System.out.println(rat1 + " + " + rat2 + " = " + res4 + " \t| Returned class: " + res4.getClass().getSimpleName());

        // Extreme mix: Rational + Irrational = Contaminates the result into an Irrational
        Real res5 = rat1.add(pi);
        System.out.println(rat1 + " + " + pi + " = " + res5 + " \t| Returned class: " + res5.getClass().getSimpleName());
    }
}
