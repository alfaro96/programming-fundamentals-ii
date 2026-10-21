import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test suite for the {@link Dog} and {@link Cat} classes and the list of animals in
 * {@link Main}.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  /**
   * Runs a piece of code and returns what it prints, with its line separators as {@code \n}.
   *
   * @param code the code to run.
   * @return the text printed to {@code System.out} while it runs.
   */
  private static String capture(Runnable code) {
    PrintStream original = System.out;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    System.setOut(new PrintStream(buffer));
    try {
      code.run();
    } finally {
      System.setOut(original);
    }
    return buffer.toString().replace(System.lineSeparator(), "\n");
  }

  /**
   * Verifies that a dog describes itself with its name and barks.
   */
  @Test
  public void testDog() {
    Dog dog = new Dog("Bobby");

    assertEquals("toString() should give the requested text", "I am the dog Bobby", dog.toString());
    assertEquals("bark() should print the name and woof!", "Bobby: woof!\n", capture(dog::bark));
  }

  /**
   * Verifies that a cat describes itself with its name and meows.
   */
  @Test
  public void testCat() {
    Cat cat = new Cat("Garfield");

    assertEquals("toString() should give the requested text", "I am the cat Garfield", cat.toString());
    assertEquals("meow() should print the name and meow!", "Garfield: meow!\n", capture(cat::meow));
  }

  /**
   * Verifies that {@code main} prints every animal, and then makes each dog bark and each cat
   * meow, in the order they were added.
   */
  @Test
  public void testMain() {
    String expected = "I am the dog Bobby\nI am the dog Jimmy\nI am the dog Oasis\n"
        + "I am the dog Sanson\nI am the cat Garfield\nI am the cat Boots\nI am the cat Whiskers\n"
        + "Bobby: woof!\nJimmy: woof!\nOasis: woof!\nSanson: woof!\n"
        + "Garfield: meow!\nBoots: meow!\nWhiskers: meow!\n";

    assertEquals("main should print the animals, then their sounds", expected,
        capture(() -> Main.main(new String[0])));
  }
}
