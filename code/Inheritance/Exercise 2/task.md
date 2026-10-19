# Exercise 2

The `antiques` package already holds part of a program to manage a collection of antiques: the `Antique` class, with an identifier (`id`) and a year (`year`), and the `Collection` class, which keeps the antiques in a matrix where row `i` holds those of century `i + 1`, with its constructor, `getAntiqueAt()` and `displayMatrix()`. Complete it with the following classes and methods:

* **`Painting`** class that extends `Antique`, containing:
  * An attribute `String technique`.
  * A constructor that receives `id`, `year` and `technique`, and initializes all the attributes of a `Painting`.
  * The `toString()` method, returning a `String` with all its attributes. This method will result in a compilation error due to access restrictions on the attributes of `Antique`. Modify their access permissions as needed, but using `public` is prohibited.

* **`Furniture`** class that extends `Antique`, containing:
  * An attribute `String material`.
  * A constructor that receives `id`, `year` and `material`, and initializes all the attributes of a `Furniture`.
  * The `toString()` method, returning a `String` with all its attributes.

* **`Collection`** class, with two new methods:
  * `addFurniture(Antique furniture)`, which inserts `furniture` into the row corresponding to its century, in the first `null` position of that row. If no spaces are available, the item is not inserted.
  * `deleteFurniture(int year)`, which removes from the collection all the furniture (only `Furniture` objects, not paintings) dated in the given year. The remaining antiques of that row move to the left, so the `null` positions stay at the end.

Suppose the matrix of antiques contains the following information, where row `i` corresponds to century `i`:

| Century | Col 0 | Col 1 | Col 2 | Col 3 |
| :--- | :--- | :--- | :--- | :--- |
| **Century 1** | `A1` | `A2` | `A3` | `A4` |
| **Century 2** | `A5` | `A6` | `null` | `null` |
| **Century 3** | `A7` | `A8` | `A9` | `null` |
| **Century 4** | `A10` | `A11` | `A12` | `null` |

Suppose antiques `A1` and `A11` are pieces of furniture dated to the years 45 and 350, respectively, and we call the method `deleteFurniture(45)` and `deleteFurniture(350)`. The resulting matrix will be:

| Century | Col 0 | Col 1 | Col 2 | Col 3 |
| :--- | :--- | :--- | :--- | :--- |
| **Century 1** | `A2` | `A3` | `A4` | `null` |
| **Century 2** | `A5` | `A6` | `null` | `null` |
| **Century 3** | `A7` | `A8` | `A9` | `null` |
| **Century 4** | `A10` | `A12` | `null` | `null` |

Create a main program class named `Main`, which:

* Creates a `Collection` with a matrix like the one in the example, adding paintings and furniture with `addFurniture()`.
* Displays the matrix with `displayMatrix()`.
* Calls `deleteFurniture()` twice, as in the example.
* Displays the resulting matrix again.
* Prints one painting and one piece of furniture with `toString()`.
