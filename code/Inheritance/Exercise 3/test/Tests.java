import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import shapes.polygons.RegularPolygon;
import shapes.quadrilaterals.Parallelogram;
import shapes.container.FiguresContainer;

/**
 * Test suite for the geometric figures collection exercise.
 * Evaluates mathematical calculations, polymorphism, and 2D array matrix operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  private RegularPolygon square;
  private Parallelogram rectangle;
  private Parallelogram rhombus;

  /**
   * Initializes a fresh set of figures before each test is executed.
   */
  @Before
  public void setUp() {
    // A square is a regular polygon: 4 sides, side length 10, internal angle 90 degrees.
    square = new RegularPolygon(4, 10.0, Math.PI / 2, "Blue", "Black");

    // A rectangle is a parallelogram: sides 10 and 20, angles 90 degrees.
    rectangle = new Parallelogram("Red", "White", 10.0, 20.0, Math.PI / 2, Math.PI / 2);

    // A rhombus using the second constructor: side 10, angle 30 degrees.
    rhombus = new Parallelogram(10.0, Math.PI / 6, "Green", "Yellow");
  }

  /**
   * Tests the perimeter and area calculations of a {@link RegularPolygon}.
   */
  @Test
  public void testRegularPolygonMath() {
    assertEquals("Perimeter of the square should be 40.0", 40.0, square.calculatePerimeter(), 0.001);
    assertEquals("Area of the square should be 100.0", 100.0, square.calculateArea(), 0.001);
  }

  /**
   * Tests the perimeter and area calculations of {@link Parallelogram} (both constructors).
   */
  @Test
  public void testParallelogramMath() {
    // Rectangle test
    assertEquals("Perimeter of the rectangle should be 60.0", 60.0, rectangle.calculatePerimeter(), 0.001);
    assertEquals("Area of the rectangle should be 200.0", 200.0, rectangle.calculateArea(), 0.001);

    // Rhombus test
    assertEquals("Perimeter of the rhombus should be 40.0", 40.0, rhombus.calculatePerimeter(), 0.001);
    assertEquals("Area of the rhombus should be 50.0", 50.0, rhombus.calculateArea(), 0.001);
  }

  /**
   * Tests the main diagonal area summation in a square matrix.
   */
  @Test
  public void testContainerSumAreas() {
    FiguresContainer container = new FiguresContainer(3, 3);

    // Add elements to the main diagonal
    container.addFigure(square, 0, 0);
    // (1, 1) is left null intentionally to test null-safety
    container.addFigure(rectangle, 2, 2);

    // Add a distraction element not on the main diagonal
    container.addFigure(rhombus, 0, 1);

    double expectedSum = 100.0 + 200.0;
    assertEquals("Sum of areas on the main diagonal is incorrect.", expectedSum, container.sumAreas(), 0.001);
  }

  /**
   * Tests the secondary diagonal perimeter summation in a square matrix.
   */
  @Test
  public void testContainerSumPerimeters() {
    FiguresContainer container = new FiguresContainer(3, 3);

    // Add elements to the secondary diagonal
    container.addFigure(square, 0, 2);
    container.addFigure(rhombus, 1, 1);
    container.addFigure(rectangle, 2, 0);

    // Add a distraction element not on the secondary diagonal
    container.addFigure(square, 0, 0);

    double expectedSum = 40.0 + 40.0 + 60.0;
    assertEquals("Sum of perimeters on the secondary diagonal is incorrect.", expectedSum, container.sumPerimeters(), 0.001);
  }

  /**
   * Tests that methods correctly return -1 when the matrix is not square.
   */
  @Test
  public void testNonSquareMatrix() {
    FiguresContainer container = new FiguresContainer(2, 3);
    container.addFigure(square, 0, 0);

    assertEquals("Non-square matrix should return -1 for sumAreas.", -1.0, container.sumAreas(), 0.0);
    assertEquals("Non-square matrix should return -1 for sumPerimeters.", -1.0, container.sumPerimeters(), 0.0);
  }

  /**
   * Tests out of bounds behavior when adding a figure.
   * Ensure it doesn't throw a runtime exception.
   */
  @Test
  public void testAddFigureOutOfBounds() {
    FiguresContainer container = new FiguresContainer(2, 2);

    try {
      // Attempting to add outside the matrix
      container.addFigure(square, 5, 5);
      // If it reaches this point, the test passes successfully (no exception thrown)
    } catch (Exception e) {
      fail("Adding a figure out of bounds should be handled gracefully and not throw an exception.");
    }
  }
}
