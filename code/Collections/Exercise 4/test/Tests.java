import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test suite for the {@link Pet} hierarchy and the list of pets in {@link Main}.
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
   * Verifies that a dog is a {@link Pet} with its name, which speaks and barks.
   */
  @Test
  public void testDog() {
    Pet pet = new Dog("Rex");
    Dog dog = (Dog) pet;

    assertEquals("The name should be given to Pet's constructor", "Rex", pet.getName());
    assertEquals("speak() should be inherited from Pet", "Rex makes a sound.\n", capture(pet::speak));
    assertEquals("bark() should print the name and woof!", "Rex: woof!\n", capture(dog::bark));
  }

  /**
   * Verifies that a cat is a {@link Pet} with its name, which speaks and meows.
   */
  @Test
  public void testCat() {
    Pet pet = new Cat("Luna");
    Cat cat = (Cat) pet;

    assertEquals("The name should be given to Pet's constructor", "Luna", pet.getName());
    assertEquals("speak() should be inherited from Pet", "Luna makes a sound.\n", capture(pet::speak));
    assertEquals("meow() should print the name and meow!", "Luna: meow!\n", capture(cat::meow));
  }

  /**
   * Verifies that {@code main} makes each pet bark or meow, and then speak, in the order they
   * were added.
   */
  @Test
  public void testMain() {
    String expected = "Bobby: woof!\nBobby makes a sound.\n"
        + "Garfield: meow!\nGarfield makes a sound.\n"
        + "Sanson: woof!\nSanson makes a sound.\n"
        + "Whiskers: meow!\nWhiskers makes a sound.\n";

    assertEquals("main should print each pet's own sound, then speak", expected,
        capture(() -> Main.main(new String[0])));
  }
}
