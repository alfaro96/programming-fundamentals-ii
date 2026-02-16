import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Person} class to verify
 * the correctness of constructors, array management, age comparison logic,
 * and recursive string representation.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Person
 */
public class Tests {

    /**
     * Verifies the <b>main constructor</b>.
     * <p>
     * <b>Requirement:</b> "A constructor that takes... and the number of children."
     * </p>
     * Tests that the children array is initialized with the correct fixed capacity.
     */
    @Test
    public void testMainConstructor() {
        int capacity = 3;
        Person p = new Person("John", 40, 1.80, 80.0, "Main St", capacity);

        assertNotNull("Person object should be created", p);
        assertEquals("Name should be set correctly", "John", p.name);
        assertNotNull("Children array should be initialized", p.children);
        assertEquals("Children array capacity should match constructor argument",
                capacity, p.children.length);
    }

    /**
     * Verifies the <b>childless constructor</b>.
     * <p>
     * <b>Requirement:</b> "A constructor that only takes... assumed to be childless."
     * </p>
     * Tests that the children array is initialized with a size of 0.
     */
    @Test
    public void testChildlessConstructor() {
        Person p = new Person("Solo", 25, 1.70, 70.0, "Lonely Rd");

        assertNotNull("Children array should be initialized", p.children);
        assertEquals("Childless person should have array size 0",
                0, p.children.length);
    }

    /**
     * Verifies the <b>public attribute modification</b>.
     * <p>
     * <b>Requirement:</b> "Change the age of Person 1 and verify the change."
     * </p>
     */
    @Test
    public void testAttributeModification() {
        Person p = new Person("Alice", 30, 1.65, 60.0, "Wonderland");

        // Direct attribute access as defined in the provided class
        p.age = 31;
        p.address = "New Address";

        assertEquals("Age should be updated", 31, p.age);
        assertEquals("Address should be updated", "New Address", p.address);
    }

    /**
     * Verifies the {@link Person#addChild)} logic.
     * <p>
     * <b>Requirement:</b> "adds it to the children array."
     * </p>
     * Tests adding children within the array's capacity.
     */
    @Test
    public void testAddChildSuccess() {
        Person parent = new Person("Parent", 50, 1.80, 80.0, "Home", 2);
        Person child1 = new Person("Child1", 10, 1.20, 30.0, "Home");
        Person child2 = new Person("Child2", 8, 1.10, 25.0, "Home");

        parent.addChild(child1);
        parent.addChild(child2);

        assertEquals("First child should be stored at index 0", child1, parent.children[0]);
        assertEquals("Second child should be stored at index 1", child2, parent.children[1]);
    }

    /**
     * Verifies the {@link Person#addChild)} overflow protection.
     * <p>
     * <b>Requirement:</b> "if it is full, the method should show an error" (and implies not crashing).
     * </p>
     * Tests that adding a child to a full array does not throw an exception.
     */
    @Test
    public void testAddChildOverflow() {
        Person parent = new Person("Parent", 50, 1.80, 80.0, "Home", 1);
        Person child1 = new Person("Child1", 10, 1.20, 30.0, "Home");
        Person child2 = new Person("Child2", 8, 1.10, 25.0, "Home");

        parent.addChild(child1); // Fills the array

        // Attempt to add to full array (Should print error to console, but not crash)
        try {
            parent.addChild(child2);
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Method should handle array overflow gracefully without throwing exception");
        }

        assertEquals("Array content should remain unchanged after overflow attempt",
                child1, parent.children[0]);
    }

    /**
     * Verifies {@link Person#getOldestChild} logic.
     * <p>
     * <b>Requirement:</b> "return the oldest... child in the children array."
     * </p>
     */
    @Test
    public void testGetOldestChild() {
        Person parent = new Person("Parent", 40, 1.70, 70.0, "Home", 3);
        Person c1 = new Person("Mid", 10, 1.0, 30.0, "Home");
        Person c2 = new Person("Oldest", 15, 1.5, 50.0, "Home");
        Person c3 = new Person("Youngest", 5, 0.8, 20.0, "Home");

        parent.addChild(c1);
        parent.addChild(c2);
        parent.addChild(c3);

        Person result = parent.getOldestChild();
        assertEquals("Should return the child with highest age", c2, result);
    }

    /**
     * Verifies {@link Person#getYoungestChild} logic.
     * <p>
     * <b>Requirement:</b> "return the... youngest child in the children array."
     * </p>
     */
    @Test
    public void testGetYoungestChild() {
        Person parent = new Person("Parent", 40, 1.70, 70.0, "Home", 3);
        Person c1 = new Person("Mid", 10, 1.0, 30.0, "Home");
        Person c2 = new Person("Oldest", 15, 1.5, 50.0, "Home");
        Person c3 = new Person("Youngest", 5, 0.8, 20.0, "Home");

        parent.addChild(c1);
        parent.addChild(c2);
        parent.addChild(c3);

        Person result = parent.getYoungestChild();
        assertEquals("Should return the child with lowest age", c3, result);
    }

    /**
     * Verifies age extremes methods when <b>no children exist</b>.
     * <p>
     * Tests boundary condition to ensure {@code null} is returned instead of crashing.
     * </p>
     */
    @Test
    public void testAgeExtremesWithNoChildren() {
        Person parent = new Person("Parent", 40, 1.70, 70.0, "Home", 2);

        assertNull("getOldestChild should return null if no children added",
                parent.getOldestChild());
        assertNull("getYoungestChild should return null if no children added",
                parent.getYoungestChild());
    }

    /**
     * Verifies the recursive {@link Person#toString} method.
     * <p>
     * <b>Requirement:</b> "returns a String representation... including all information and the information of each child."
     * </p>
     */
    @Test
    public void testRecursiveToString() {
        Person parent = new Person("BigDad", 50, 1.90, 90.0, "Home", 1);
        Person child = new Person("LilBoy", 10, 1.20, 30.0, "Home");

        parent.addChild(child);

        String output = parent.toString();

        assertTrue("Output should contain parent name", output.contains("BigDad"));
        assertTrue("Output should contain child name", output.contains("LilBoy"));
        assertTrue("Output should contain child label", output.contains("Children of BigDad"));
    }
}
