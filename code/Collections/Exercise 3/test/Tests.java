import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for the classes {@link Dog} and {@link Cat}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * Tests the instantiation, overridden {@link Dog#toString()}, and {@link Dog#bark()} method
   * of the {@link Dog} class.
   */
  @Test
  public void testDog() {
    Dog dog = new Dog("Bobby");

    // Test Step 3: overridden toString
    Assert.assertEquals("toString() should match the requested format",
            "I am the dog Bobby", dog.toString());

    // Test Step 2: bark() behavior by intercepting System.out
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    dog.bark();

    // Restore standard output
    System.setOut(originalOut);

    // Assert the printed text (trimming to ignore line separators)
    Assert.assertEquals("bark() should print '[name]: woof!'",
            "Bobby: woof!", outContent.toString().trim());
  }

  /**
   * Tests the instantiation, overridden {@link Cat#toString()}, and {@link Cat#meow()} method
   * of the {@link Cat} class.
   */
  @Test
  public void testCat() {
    Cat cat = new Cat("Garfield");

    // Test Step 3: overridden toString
    Assert.assertEquals("toString() should match the requested format",
            "I am the cat Garfield", cat.toString());

    // Test Step 2: meow() behavior by intercepting System.out
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    cat.meow();

    // Restore standard output
    System.setOut(originalOut);

    // Assert the printed text (trimming to ignore line separators)
    Assert.assertEquals("meow() should print '[name]: meow!'",
            "Garfield: meow!", outContent.toString().trim());
  }
}
