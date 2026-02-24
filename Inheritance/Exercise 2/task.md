# Exercise: Real Estate Management

Implement a package called `housing` containing the following class hierarchy and logic.

* **`Housing`** abstract class with:
  * An attribute `double price`.
  * A constructor that receives this attribute.
  * A getter for `price`.
  * An abstract method `double tax()`.
  * An abstract `toString()` method.

* **`SingleFamily`** class that extends `Housing` and implements the `Comparable<SingleFamily>` interface, containing:
  * An attribute `int numberOfFloors`.
  * An attribute `double basementSize`.
  * A single constructor that initializes all attributes, including inherited ones.
  * A method `double tax()`, where the tax is calculated as 20% of the price.
  * The `compareTo()` method, comparing by `tax()` and the class name.
  * The `toString()` method.

* **Additional classes** that inherit as follows (ensure each has a proper constructor to initialize all its attributes):
  * **`Chalet`**, extending `SingleFamily`, with an additional attribute `double gardenSize`.
  * **`CountryHouse`**, extending `Housing`, with attributes `double plotSize` and `Chalet chalet`. The tax is the chalet's tax plus 0.5 euros per square meter of the plot.
  * **`Apartment`**, extending `Housing`, with an additional attribute `boolean terrace`. The tax is calculated as `price` multiplied by 1.5.

* **`RealEstate`** class containing:
  * A private array of `Housing` objects.
  * A constructor that initializes the array with `n` elements, where `n` is passed as a parameter (but no housing objects are added initially).
  * A `counter` attribute indicating the position in the array where the next housing object should be inserted.
  * A method `addHousing(Housing housing)` to add a new housing object.
  * A method `countNonChaletSingleFamily()` to count how many `SingleFamily` homes are not `Chalet`.
  * A method `getHighestTaxHousing()` to determine which housing object has the highest tax.

Create a **`Main`** class (outside the `housing` package) that performs the following:
* Creates a `RealEstate` instance with three housing objects: a `Chalet`, a `SingleFamily` home, and a `CountryHouse` (use any necessary values).
* Displays how many `SingleFamily` homes are not `Chalet`.
* Displays the housing object with the highest tax (implement `toString()` for each housing type).
