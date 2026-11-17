# Exercise 1

Start from your solution to Exercise 5 of Unit 3: Inheritance, the `housing` package with `Housing`, `SingleFamily`, `Chalet`, `CountryHouse`, `Apartment` and `RealEstate`, and make its constructors reject invalid values with exceptions.

* Create a package called `exceptions` with two unchecked exceptions, both extending `RuntimeException`, since a negative value is a mistake in the code that calls the constructor:
  * **`InvalidPriceException`**, with a constructor that receives the invalid price (`double`) and builds a message that includes it.
  * **`InvalidSquareMetersException`**, with a constructor that receives the name of the attribute (`String`) and its invalid value (`double`), and builds a message that includes both.

* Throw them from the constructors, which do not have to declare them with `throws`:
  * `Housing` throws an `InvalidPriceException` if the price is negative, so every constructor of its subclasses may throw it too.
  * `SingleFamily` throws an `InvalidSquareMetersException` if `basementSize` is negative, `Chalet` if `gardenSize` is negative, and `CountryHouse` if `plotSize` is negative.
  * A value of 0 is valid.

Update the **`Main`** class so that it:
* Creates the same three housing objects as in Unit 3 and adds them to the `RealEstate`, with no `try`/`catch`, since their values are valid.
* Displays the same summary as in Unit 3.
* Tries to create an `Apartment` with a negative price and a `Chalet` with a negative garden size, and prints the message of each exception instead of stopping. Each attempt goes in its own `try`/`catch` just to see its message: a correct program checks the values before calling the constructor.
