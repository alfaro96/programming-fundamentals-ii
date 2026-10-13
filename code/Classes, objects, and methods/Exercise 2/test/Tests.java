import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Person} class: both constructors, adding children to an array of fixed
 * size, finding the oldest and youngest child, and the written form of a person.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Person
 */
public class Tests {

    /**
     * The largest difference allowed when comparing two {@code double} values.
     */
    private static final double DELTA = 1e-9;

    /**
     * Creates a parent with room for three children and adds three, aged 5, 12 and 8.
     *
     * @return the parent, with its three children added.
     */
    private static Person createParentWithThreeChildren() {
        Person parent = new Person("Leo", 44, 1.75, 72.0, "3 Birch Blvd", 3);
        parent.addChild(new Person("Mia", 5, 1.05, 17.0, "3 Birch Blvd"));
        parent.addChild(new Person("Noah", 12, 1.48, 40.0, "3 Birch Blvd"));
        parent.addChild(new Person("Olivia", 8, 1.28, 27.0, "3 Birch Blvd"));
        return parent;
    }

    /**
     * Checks that the constructor with the number of children stores every attribute and
     * creates an array of that size, still empty.
     */
    @Test
    public void testConstructorWithChildren() {
        Person p = new Person("Alice", 35, 1.68, 62.0, "123 Main St", 3);

        assertEquals("The name should be Alice", "Alice", p.name);
        assertEquals("The age should be 35", 35, p.age);
        assertEquals("The height should be 1.68", 1.68, p.height, DELTA);
        assertEquals("The weight should be 62.0", 62.0, p.weight, DELTA);
        assertEquals("The address should be 123 Main St", "123 Main St", p.address);
        assertNotNull("The array of children should be created", p.children);
        assertEquals("The array of children should have room for 3", 3, p.children.length);
        for (Person child : p.children) {
            assertNull("A new person should have no children added yet", child);
        }
    }

    /**
     * Checks that the constructor without children stores every attribute and creates an
     * empty array, not {@code null}.
     */
    @Test
    public void testConstructorWithoutChildren() {
        Person p = new Person("Bob", 28, 1.80, 75.0, "456 Elm Ave");

        assertEquals("The name should be Bob", "Bob", p.name);
        assertEquals("The age should be 28", 28, p.age);
        assertEquals("The height should be 1.80", 1.80, p.height, DELTA);
        assertEquals("The weight should be 75.0", 75.0, p.weight, DELTA);
        assertEquals("The address should be 456 Elm Ave", "456 Elm Ave", p.address);
        assertNotNull("The array of children should be created, not null", p.children);
        assertEquals("The array of children should be empty", 0, p.children.length);
    }

    /**
     * Checks that {@link Person#addChild} fills the array in order, from the first position.
     */
    @Test
    public void testAddChild() {
        Person parent = createParentWithThreeChildren();

        assertEquals("The first child should be Mia", "Mia", parent.children[0].name);
        assertEquals("The second child should be Noah", "Noah", parent.children[1].name);
        assertEquals("The third child should be Olivia", "Olivia", parent.children[2].name);
    }

    /**
     * Checks that {@link Person#addChild} with a full array neither grows it nor replaces
     * the children already added.
     */
    @Test
    public void testAddChildWhenFull() {
        Person parent = new Person("Ivy", 45, 1.72, 68.0, "2 Pine Ln", 1);
        parent.addChild(new Person("Jack", 6, 1.10, 20.0, "2 Pine Ln"));
        parent.addChild(new Person("Kim", 4, 1.00, 16.0, "2 Pine Ln"));

        assertEquals("The array of children should not grow", 1, parent.children.length);
        assertEquals("The child already added should stay", "Jack", parent.children[0].name);
    }

    /**
     * Checks that {@link Person#getOldestChild} returns the child with the highest age.
     */
    @Test
    public void testGetOldestChild() {
        Person oldest = createParentWithThreeChildren().getOldestChild();

        assertNotNull("The oldest child should not be null", oldest);
        assertEquals("The oldest child should be Noah, aged 12", "Noah", oldest.name);
    }

    /**
     * Checks that {@link Person#getYoungestChild} returns the child with the lowest age.
     */
    @Test
    public void testGetYoungestChild() {
        Person youngest = createParentWithThreeChildren().getYoungestChild();

        assertNotNull("The youngest child should not be null", youngest);
        assertEquals("The youngest child should be Mia, aged 5", "Mia", youngest.name);
    }

    /**
     * Checks that both methods also work when not every position of the array is filled,
     * and that they return {@code null} for a person without children.
     */
    @Test
    public void testOldestAndYoungestPartialAndEmpty() {
        Person parent = new Person("Pam", 41, 1.62, 55.0, "4 Cedar Ct", 3);
        parent.addChild(new Person("Quinn", 9, 1.30, 28.0, "4 Cedar Ct"));

        assertEquals("With one child, the oldest should be that child", "Quinn", parent.getOldestChild().name);
        assertEquals("With one child, the youngest should be that child", "Quinn", parent.getYoungestChild().name);

        Person childless = new Person("Ray", 30, 1.70, 70.0, "5 Ash St");
        assertNull("A person without children should have no oldest child", childless.getOldestChild());
        assertNull("A person without children should have no youngest child", childless.getYoungestChild());
    }

    /**
     * Checks that {@link Person#describe} includes every attribute of the person and the
     * data of each child.
     */
    @Test
    public void testDescribe() {
        Person parent = new Person("Tom", 35, 1.78, 80.5, "5 Willow Way", 2);
        parent.addChild(new Person("Uma", 7, 1.15, 22.5, "6 Elm Row"));

        String text = parent.describe();

        assertTrue("It should include the name", text.contains("Tom"));
        assertTrue("It should include the age", text.contains("35"));
        assertTrue("It should include the height", text.contains("1.78"));
        assertTrue("It should include the weight", text.contains("80.5"));
        assertTrue("It should include the address", text.contains("5 Willow Way"));
        assertTrue("It should include the child's name", text.contains("Uma"));
        assertTrue("It should include the child's height", text.contains("1.15"));
        assertTrue("It should include the child's address", text.contains("6 Elm Row"));
    }
}
