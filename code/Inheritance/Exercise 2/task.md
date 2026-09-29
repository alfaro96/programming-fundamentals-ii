# Exercise 2

Using the skeleton provided, implement the following classes and methods to manage a collection of antiques, ensuring proper inheritance, data handling, and method functionality:

* **`Painting`** class that extends `Antique`, containing:
  * A modified constructor to initialize all attributes of a `Painting`.
  * The `toString()` method to print all attributes. This method will result in a compilation error due to access restrictions on certain attributes. Modify the access permissions as needed, but using `public` is prohibited.

* **`Furniture`** class that extends `Antique`, containing:
  * A modified constructor to initialize all attributes of a `Furniture`.
  * The `toString()` method to print all attributes.

* **`Collection`** class containing:
  * A method `deleteFurniture(int year)`, which will remove all furniture from an instance of the `Collection` class that is dated in a given year.
  * A method `addFurniture(Antique furniture)`, which will insert `furniture` into the row corresponding to its century and in the first `null` position of that row. If no spaces are available, the item will not be inserted.

Suppose the matrix of antiques contains the following information, where row `i` corresponds to century `i`:

| Century | Col 0 | Col 1 | Col 2 | Col 3 |
| :--- | :--- | :--- | :--- | :--- |
| **Century 1** | `A1` | `A2` | `A3` | `A4` |
| **Century 2** | `A5` | `A6` | `null` | `null` |
| **Century 3** | `A7` | `A8` | `A9` | `null` |
| **Century 4** | `A10` | `A11` | `A12` | `null` |

Suppose antiques `A1` and `A11` are dated to the years 45 and 350, respectively, and we call the method `deleteFurniture(45)` and `deleteFurniture(350)`. The resulting matrix will be:

| Century | Col 0 | Col 1 | Col 2 | Col 3 |
| :--- | :--- | :--- | :--- | :--- |
| **Century 1** | `A2` | `A3` | `A4` | `null` |
| **Century 2** | `A5` | `A6` | `null` | `null` |
| **Century 3** | `A7` | `A8` | `A9` | `null` |
| **Century 4** | `A10` | `A12` | `null` | `null` |

Create a main program class named `Main`, which:

* Creates a `Collection` with a matrix like the one in the example.
* Calls `deleteFurniture()` twice.
* Displays the resulting matrix on the screen by calling `toString()` on each antique.
