# Exercise 2

1. Modify Exercise 2 from Unit 3: Inheritance to include the following exceptions in a package named `exceptions`:
   * **`InvalidPriceException`**: A checked exception that is thrown when the price is negative. It must extend `Exception`.
   * **`YearOutOfRangeException`**: An unchecked exception that is thrown when the year is out of the allowed range. It must extend `RuntimeException`.
   * **`CollectionFullException`**: A checked exception that is thrown when there is no space available in the collection to add a new piece of furniture. It must extend `Exception`.
   * **`FurnitureNotFoundException`**: A checked exception that is thrown when the furniture to be deleted is not found in the collection. It must extend `Exception`.
2. Create a menu that allows the user to choose an operation: add furniture or remove furniture. The furniture data must be entered via keyboard input.
3. Handle the defined exceptions (and any others that may arise, such as `InputMismatchException` when a letter is entered instead of an expected number) properly so that the program continues executing correctly in case of an error, and the menu reappears on the screen.
