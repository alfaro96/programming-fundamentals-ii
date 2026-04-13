import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for the {@link Pet}, {@link Dog}, and {@link Cat} classes.
 * <p>
 * Verifies that inheritance is properly established and that both inherited
 * and specific methods output the correct strings to the console.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * Tests the instantiation, overridden {@link Dog#toString()}, and {@link Dog#bark()} method
   * of the {@link Dog} class.
   */
  @Test
  public void testDogBehaviors() {
    Dog dog = new Dog("Rex");

    // Verify state initialization via inheritance
    Assert.assertEquals("The name should be correctly initialized", "Rex", dog.getName());

    // Intercept System.out
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    // Test Step 1: Inherited speak()
    dog.speak();
    String expectedSpeak = "Rex makes a sound.";

    // Test Step 2: Specific bark()
    dog.bark();
    String expectedBark = "Rex: woof!";

    System.setOut(originalOut); // Restore standard output

    // The output will contain both lines separated by a newline
    String output = outContent.toString().trim();
    Assert.assertTrue("Output should contain the speak phrase", output.contains(expectedSpeak));
    Assert.assertTrue("Output should contain the bark phrase", output.contains(expectedBark));
  }

  /**
   * Tests the instantiation, overridden {@link Cat#toString()}, and {@link Cat#meow()} method
   * of the {@link Cat} class.
   */
  @Test
  public void testCatBehaviors() {
    Cat cat = new Cat("Luna");

    // Verify state initialization via inheritance
    Assert.assertEquals("The name should be correctly initialized", "Luna", cat.getName());

    // Intercept System.out
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    // Test Step 1: Inherited speak()
    cat.speak();
    String expectedSpeak = "Luna makes a sound.";

    // Test Step 2: Specific meow()
    cat.meow();
    String expectedMeow = "Luna: meow!";

    System.setOut(originalOut); // Restore standard output

    // The output will contain both lines separated by a newline
    String output = outContent.toString().trim();
    Assert.assertTrue("Output should contain the speak phrase", output.contains(expectedSpeak));
    Assert.assertTrue("Output should contain the meow phrase", output.contains(expectedMeow));
  }
}
