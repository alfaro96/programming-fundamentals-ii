import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests to verify the wildcard implementation in the {@link Main} class.
 * @author Juan Carlos Alfaro Jiménez
 *
 * @see Main
 */
public class Tests {

  /**
   * Verifies that the {@link Main#processElements(List)} method
   * correctly handles lists of subclasses and invokes the {@link Pet#speak()} method.
   */
  @Test
  public void testUpperBoundedWildcard() {
    List<Dog> dogList = new ArrayList<Dog>();
    dogList.add(new Dog("Rex"));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    Main.processElements(dogList);

    System.setOut(originalOut);

    Assert.assertTrue("The output should contain the Dog's sound",
            outContent.toString().contains("Rex makes a sound."));
  }

  /**
   * Verifies that the {@link Main#processLowerBoundedElements(List)} method
   * accepts a list of {@link Object} and prints its contents.
   */
  @Test
  public void testLowerBoundedWildcard() {
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("General Object");

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    Main.processLowerBoundedElements(objectList);

    System.setOut(originalOut);

    Assert.assertTrue("The output should contain the object's string representation",
            outContent.toString().contains("General Object"));
  }
}
