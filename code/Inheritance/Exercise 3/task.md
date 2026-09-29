# Exercise 3

In this exercise, you are asked to implement a set of geometric figures using inheritance. You will start by creating an abstract class for general geometric figures, then create subclasses for specific types of figures like regular polygons and parallelograms. Finally, you will implement a container class to manage a collection of these figures. Follow the steps below:

1. Create a package named `shapes`.

2. Implement an abstract class called `GeometricFigure` within the `shapes` package with the following attributes:
   * `String fillColor`
   * `String borderColor`

Define two constructors:
  * `GeometricFigure(String fillColor)`
  * `GeometricFigure(String fillColor, String borderColor)`. **This constructor must call the previous constructor explicitly**.

Define the following methods:
  * `double calculatePerimeter()`: Calculates the perimeter of the geometric figure. If possible, implement it; otherwise, make sure subclasses must implement it.
  * `double calculateArea()`: Calculates the area of the geometric figure. If possible, implement it; otherwise, make sure subclasses must implement it.

**Ensure that neither constructors nor attributes are public**. Decide the best visibility for each one.

3. Create a package named `shapes.polygons`.

4. Create a class called `RegularPolygon`, which is a subclass of `GeometricFigure`, within the `shapes.polygons` package. This class should have the following attributes:
   * `int numberOfSides`
   * `double sideLength`
   * `double angle`

Define a constructor with the following format:
  * `RegularPolygon(int numberOfSides, double sideLength, double angle, String fillColor, String borderColor)`

**This constructor must call the parent class constructor**.

In this class, make public what you consider necessary, except for the attributes.

5. Create a package named `shapes.quadrilaterals`.

6. Create a class called `Parallelogram`, a subclass of `GeometricFigure`, within the `shapes.quadrilaterals` package with the following attributes:
   * `double side1, side2`
   * `double angle1, angle2`

Define two constructors:
  * `Parallelogram(String fillColor, String borderColor, double side1, double side2, double angle1, double angle2)`
  * `Parallelogram(double side, double angle, String fillColor, String borderColor)`

**Always ensure that constructors call the parent class constructor, otherwise they will try to call the default one**.

Add any setters and getters you deem necessary. In this class, make public what you consider appropriate, except for the attributes.

7. Create a package named `shapes.container`.

8. Implement a class called `FiguresContainer` within the `shapes.container` package with the following attributes:
   * A single two-dimensional matrix `M` that will contain both `RegularPolygon` and `Parallelogram` objects.
   * `int rows`
   * `int columns`

**The size of the matrix will be set in the constructor of the class and will initially be empty**.

Add setters and getters as needed.

9. Create a method called `addFigure` that can receive either a `RegularPolygon` or a `Parallelogram` object as the first argument and add it to the corresponding row and column passed as arguments.

10. Create a method called `sumAreas` that returns the sum of the areas of the figures located on the main diagonal. **Be careful with positions that are `null`. If the matrix is not square, the method will return `-1`**.

11. Create a method called `sumPerimeters` that returns the sum of the perimeters of the figures located on the secondary diagonal. **Be careful with positions that are `null`. If the matrix is not square, the method will return `-1`**.

In this class, make public what you consider appropriate, except for the attributes.