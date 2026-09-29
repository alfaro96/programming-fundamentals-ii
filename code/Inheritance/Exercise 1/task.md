# Exercise 1

Given the `Employee` class **provided in the template**, extend it to create three new subclasses by overriding the necessary methods:

* **`Lawyer`**:
  * Has half the vacation days of a regular `Employee`.
  * Earns 10000 euros more than an `Employee`.
  * Works 5 more hours per week than an `Employee`.
  * Takes vacation in July instead of August.
  * Has an additional method called `attendCourt()` that prints: `"I am in court"`.

* **`Secretary`**:
  * Earns 5000 euros more than an `Employee`.
  * Works 30 hours per week, same as an `Employee`.
  * Has the same vacation days as an `Employee` and takes them in the same month.
  * Has an additional method called `makePhotocopies()` that prints: `"I am making photocopies"`.

* **`Janitor`**:
  * Has 5 more vacation days than an `Employee`.
  * Takes vacation in September instead of August.
  * Works the same hours and earns the same salary as an `Employee`.

Create a `Main` class that:
* Stores ten random employees, of any type, in an array.
* Iterates through the array, calling and displaying the output of all the defined methods for each employee. *(Hint: You will need to check the specific type of the object to call the additional methods).*
