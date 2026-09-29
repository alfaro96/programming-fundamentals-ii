import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link Person} test suite.
 * <p>
 * This class performs unit testing on the {@link Person} class to verify
 * the correctness of constructors, child management, and string representation.
 * </p>
 * <p><b>Covered scenarios:</b></p>
 * <ul>
 * <li>Full constructor ({@code name}, {@code age}, {@code height}, {@code weight}, {@code address}, number of children).</li>
 * <li>Childless constructor ({@code name}, {@code age}, {@code height}, {@code weight}, {@code address}).</li>
 * <li>{@link Person#addChild(Person)} – adding children up to capacity.</li>
 * <li>{@link Person#addChild(Person)} – attempting to add a child to a full array (error case).</li>
 * <li>{@link Person#getOldestChild()} – returns the child with the highest age.</li>
 * <li>{@link Person#getYoungestChild()} – returns the child with the lowest age.</li>
 * <li>{@link Person#toString()} – includes all fields and recursively lists children.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Person
 */
public class Tests {

    /**
     * Verifies the full constructor with a specified maximum number of children.
     * <p>
     * <b>Requirement:</b> All fields must be stored correctly and the {@code children}
     * array must be initialized with the given capacity.
     * </p>
     */
    @Test
    public void testFullConstructor() {
        Person p = new Person("Alice", 35, 1.68, 62.0, "123 Main St", 3);

        assertEquals("Name should match", "Alice", p.name);
        assertEquals("Age should match", 35, p.age);
        assertEquals("Height should match", 1.68, p.height, 1e-9);
        assertEquals("Weight should match", 62.0, p.weight, 1e-9);
        assertEquals("Address should match", "123 Main St", p.address);
        assertNotNull("Children array must be initialized", p.children);
        assertEquals("Children array capacity must match constructor argument", 3, p.children.length);
    }

    /**
     * Verifies the childless constructor.
     * <p>
     * <b>Requirement:</b> A {@link Person} created without specifying children must have
     * an empty (zero-length) {@code children} array.
     * </p>
     */
    @Test
    public void testChildlessConstructor() {
        Person p = new Person("Bob", 28, 1.80, 75.0, "456 Elm Ave");

        assertEquals("Name should match", "Bob", p.name);
        assertEquals("Age should match", 28, p.age);
        assertEquals("Height should match", 1.80, p.height, 1e-9);
        assertEquals("Weight should match", 75.0, p.weight, 1e-9);
        assertEquals("Address should match", "456 Elm Ave", p.address);
        assertNotNull("Children array must not be null", p.children);
        assertEquals("Children array length must be 0 for childless constructor", 0, p.children.length);
    }

    /**
     * Verifies that {@link Person#addChild(Person)} stores a child correctly in the first
     * available slot and that the child's attributes are accessible afterwards.
     * <p>
     * <b>Requirement:</b> The first call to {@code addChild()} must place the child at
     * index {@code 0} of the {@code children} array.
     * </p>
     */
    @Test
    public void testAddChildStoresChild() {
        Person parent = new Person("Carol", 40, 1.70, 65.0, "789 Oak Rd", 2);
        Person child = new Person("Dave", 8, 1.20, 25.0, "789 Oak Rd");

        parent.addChild(child);

        assertNotNull("First slot of children array must not be null after addChild()", parent.children[0]);
        assertEquals("Stored child name must match", "Dave", parent.children[0].name);
    }

    /**
     * Verifies that multiple children can be added sequentially up to the array capacity.
     * <p>
     * <b>Requirement:</b> Each child is placed in the next available slot; no slot
     * must be skipped or overwritten.
     * </p>
     */
    @Test
    public void testAddChildMultiple() {
        Person parent = new Person("Eve", 38, 1.65, 58.0, "1 Maple Dr", 3);
        Person child1 = new Person("Frank", 5, 1.05, 18.0, "1 Maple Dr");
        Person child2 = new Person("Grace", 9, 1.30, 28.0, "1 Maple Dr");
        Person child3 = new Person("Hank", 12, 1.45, 38.0, "1 Maple Dr");

        parent.addChild(child1);
        parent.addChild(child2);
        parent.addChild(child3);

        assertEquals("First child name", "Frank", parent.children[0].name);
        assertEquals("Second child name", "Grace", parent.children[1].name);
        assertEquals("Third child name", "Hank", parent.children[2].name);
    }

    /**
     * Verifies that {@link Person#addChild(Person)} handles a full array gracefully.
     * <p>
     * <b>Requirement:</b> When the {@code children} array is already at capacity, the method
     * must print an error message without throwing an uncaught exception.
     * </p>
     */
    @Test
    public void testAddChildWhenFull() {
        Person parent = new Person("Ivy", 45, 1.72, 68.0, "2 Pine Ln", 1);
        Person child1 = new Person("Jack", 6, 1.10, 20.0, "2 Pine Ln");
        Person child2 = new Person("Kim", 4, 1.00, 16.0, "2 Pine Ln");

        parent.addChild(child1); // fills the only slot

        // Should not throw; just prints an error internally
        try {
            parent.addChild(child2);
        } catch (Exception e) {
            fail("addChild() must not throw an exception when the array is full; "
                    + "it should show an error message instead. Exception: " + e.getMessage());
        }

        // The original child must still be intact
        assertEquals("Only the first child must be stored", "Jack", parent.children[0].name);
    }

    /**
     * Verifies that {@link Person#getOldestChild()} returns the child with the
     * highest age value.
     * <p>
     * <b>Setup:</b> Three children with ages {@code 5}, {@code 12}, and {@code 8}.
     * Expected oldest: age {@code 12}.
     * </p>
     */
    @Test
    public void testGetOldestChild() {
        Person parent = new Person("Leo", 44, 1.75, 72.0, "3 Birch Blvd", 3);
        Person c1 = new Person("Mia", 5, 1.05, 17.0, "3 Birch Blvd");
        Person c2 = new Person("Noah", 12, 1.48, 40.0, "3 Birch Blvd");
        Person c3 = new Person("Olivia", 8, 1.28, 27.0, "3 Birch Blvd");

        parent.addChild(c1);
        parent.addChild(c2);
        parent.addChild(c3);

        Person oldest = parent.getOldestChild();

        assertNotNull("getOldestChild() must not return null when children exist", oldest);
        assertEquals("Oldest child should be Noah (age 12)", "Noah", oldest.name);
        assertEquals("Oldest child age must be 12", 12, oldest.age);
    }

    /**
     * Verifies that {@link Person#getYoungestChild()} returns the child with the
     * lowest age value.
     * <p>
     * <b>Setup:</b> Three children with ages {@code 5}, {@code 12}, and {@code 8}.
     * Expected youngest: age {@code 5}.
     * </p>
     */
    @Test
    public void testGetYoungestChild() {
        Person parent = new Person("Pam", 41, 1.62, 55.0, "4 Cedar Ct", 3);
        Person c1 = new Person("Quinn", 5, 1.05, 17.0, "4 Cedar Ct");
        Person c2 = new Person("Ryan", 12, 1.48, 40.0, "4 Cedar Ct");
        Person c3 = new Person("Sara", 8, 1.28, 27.0, "4 Cedar Ct");

        parent.addChild(c1);
        parent.addChild(c2);
        parent.addChild(c3);

        Person youngest = parent.getYoungestChild();

        assertNotNull("getYoungestChild() must not return null when children exist", youngest);
        assertEquals("Youngest child should be Quinn (age 5)", "Quinn", youngest.name);
        assertEquals("Youngest child age must be 5", 5, youngest.age);
    }

    /**
     * Verifies that {@link Person#toString()} includes all key fields.
     * <p>
     * <b>Requirement:</b> The returned {@link String} must contain the person's
     * {@code name}, {@code age}, {@code height}, {@code weight}, and {@code address}.
     * When a person has children, their information must also appear (recursive call).
     * </p>
     */
    @Test
    public void testToString() {
        Person parent = new Person("Tom", 35, 1.78, 80.0, "5 Willow Way", 1);
        Person child = new Person("Uma", 7, 1.15, 22.0, "5 Willow Way");
        parent.addChild(child);

        String str = parent.toString();

        assertNotNull("toString() must not return null", str);
        assertTrue("toString() must contain the parent's name", str.contains("Tom"));
        assertTrue("toString() must contain the child's name", str.contains("Uma"));
        assertTrue("toString() must contain the parent's address", str.contains("5 Willow Way"));
    }
}