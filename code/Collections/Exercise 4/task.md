# Exercise 4

Turn the dogs and cats of Exercise 3 into a hierarchy, and keep them in a list of their superclass.

* Write a class **`Pet`** with a private attribute `String name`, given by its constructor, a getter `getName()`, and a method `speak()` that prints `[name] makes a sound.`
* Write two subclasses of `Pet`, **`Dog`** and **`Cat`**, whose constructors receive the name and pass it to the constructor of `Pet`. `Dog` adds a method `bark()` that prints `[name]: woof!`, and `Cat` a method `meow()` that prints `[name]: meow!`.

Write a **`Main`** class whose `main`:

1. Creates a `List<Pet>` named `pets`, as an `ArrayList`.
2. Adds the dog `"Bobby"`, the cat `"Garfield"`, the dog `"Sanson"` and the cat `"Whiskers"`, in that order.
3. Goes through `pets` with a for-each loop and, for each pet, first makes it bark if it is a dog or meow if it is a cat, and then calls its `speak()`.

Explain in a comment why `bark()` and `meow()` need a check of the class of the object, and `speak()` does not.
