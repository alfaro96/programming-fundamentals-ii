import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Test suite for the methods of {@link Main} that take lists of pets with wildcards.
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
   * Verifies that {@link Main#processElements} takes a list of dogs, a list of cats and a list
   * of pets, and makes each element speak.
   */
  @Test
  public void testProcessElements() {
    List<Dog> dogs = new ArrayList<>();
    dogs.add(new Dog("Rex"));
    List<Cat> cats = new ArrayList<>();
    cats.add(new Cat("Luna"));
    List<Pet> pets = new ArrayList<>();
    pets.add(new Dog("Toby"));
    pets.add(new Cat("Kitty"));

    assertEquals("A list of dogs should speak", "Rex makes a sound.\n",
        capture(() -> Main.processElements(dogs)));
    assertEquals("A list of cats should speak", "Luna makes a sound.\n",
        capture(() -> Main.processElements(cats)));
    assertEquals("A list of pets should speak in order", "Toby makes a sound.\nKitty makes a sound.\n",
        capture(() -> Main.processElements(pets)));
  }

  /**
   * Verifies that {@link Main#addDog} adds a dog with the given name to a list of dogs, of pets
   * and of objects.
   */
  @Test
  public void testAddDog() {
    List<Dog> dogs = new ArrayList<>();
    List<Pet> pets = new ArrayList<>();
    pets.add(new Cat("Luna"));
    List<Object> objects = new ArrayList<>();

    Main.addDog(dogs, "Rex");
    Main.addDog(pets, "Toby");
    Main.addDog(objects, "Max");

    assertEquals("The list of dogs should have one dog", 1, dogs.size());
    assertEquals("The dog should have the given name", "Rex", dogs.get(0).getName());
    assertEquals("The dog should go at the end of the list of pets", 2, pets.size());
    assertTrue("The new pet should be a dog", pets.get(1) instanceof Dog);
    assertEquals("The dog should have the given name", "Toby", pets.get(1).getName());
    assertEquals("The list of objects should have one element", 1, objects.size());
    assertTrue("The new object should be a dog", objects.get(0) instanceof Dog);
    assertEquals("The dog should have the given name", "Max", ((Dog) objects.get(0)).getName());
  }

  /**
   * Verifies that {@code main} makes the dogs and cats speak, catches the string in the list with
   * no type, and adds the three dogs.
   */
  @Test
  public void testMain() {
    String expected = "Bobby makes a sound.\nJimmy makes a sound.\n"
        + "Garfield makes a sound.\nWhiskers makes a sound.\n"
        + "Oasis makes a sound.\nThe list holds something that is not a pet\n"
        + "Max is now in the list\nToby makes a sound.\n";

    assertEquals("main should print what the statement asks for, in order", expected,
        capture(() -> Main.main(new String[0])));
  }
}
