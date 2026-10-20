# Exercise 2

Start from your solution to Exercise 2 of Unit 3: Inheritance, the `antiques` package with `Antique`, `Painting`, `Furniture` and `Collection`, and make it report its errors with exceptions.

* Add a price to every antique:
  * `Antique` gets an attribute `double price`, which its constructor receives after `year`, and a getter for it.
  * The constructors of `Painting` and `Furniture` receive the price after `year` too, and every `toString()` includes it.

* Create a package called `exceptions` with the following exceptions:
  * **`InvalidPriceException`**, a checked exception (it extends `Exception`) thrown by the constructor of `Antique` when the price is negative. Its constructor receives the price.
  * **`YearOutOfRangeException`**, an unchecked exception (it extends `RuntimeException`) thrown by the constructor of `Antique` when the year is not between `Antique.MIN_YEAR` and `Antique.MAX_YEAR`, two constants equal to 1 and 1900, and by `addFurniture()` and `deleteFurniture()` when the matrix has no row for the century of the year. Its constructor receives the year.
  * **`CollectionFullException`**, a checked exception thrown by `addFurniture()` when the row of the century has no `null` position, instead of leaving the antique out without a word. Its constructor receives the century.
  * **`FurnitureNotFoundException`**, a checked exception thrown by `deleteFurniture()` when there is no furniture dated in the given year. Its constructor receives the year.

  Each constructor builds a message that includes the value it receives. The constructors and methods that may throw a checked exception have to declare it.

Replace the **`Main`** class with a menu that is shown again after every option, until the user chooses to exit:
* `1`: add a piece of furniture, reading its identifier, year, price and material from the keyboard with a `Scanner`.
* `2`: add a painting, reading its identifier, year, price and technique.
* `3`: delete the furniture of a year read from the keyboard.
* `4`: display the matrix with `displayMatrix()`.
* `0`: exit.

The collection covers centuries 1 to 19, with room for 4 antiques per century. When an option fails, the program prints the message of the exception and shows the menu again. This includes the `InputMismatchException` that `Scanner` throws when the user types a word where a number is expected: discard what was typed with `nextLine()` before showing the menu again.
