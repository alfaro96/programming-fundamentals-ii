# Exercise 3

Write two independent classes, **`Dog`** and **`Cat`**, and keep their objects together in one list.

* Each class has a private attribute `String name`, given by its constructor.
* `Dog` has a method `bark()` that prints `[name]: woof!`, and `Cat` a method `meow()` that prints `[name]: meow!`, where `[name]` is the name of the animal.
* Each class overrides `toString()`, so that printing a dog prints `I am the dog [name]`, and printing a cat, `I am the cat [name]`.

Write a **`Main`** class whose `main`:

1. Creates an `ArrayList` named `animals`, with no type in angle brackets.
2. Adds the dogs `"Bobby"`, `"Jimmy"`, `"Oasis"` and `"Sanson"`, and the cats `"Garfield"`, `"Boots"` and `"Whiskers"`.
3. Prints every animal with a for-each loop.
4. Goes through `animals` again with a for-each loop and makes each dog bark and each cat meow. The list gives back `Object`s, so check the class of each one before calling its method.
5. Creates a `List<Dog>` named `dogs`, adds a dog, and tries to add a cat. Leave that line as a comment, with the compiler's error and a short explanation of it in comments above it.
