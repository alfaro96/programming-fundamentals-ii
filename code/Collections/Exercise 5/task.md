# Exercise 5

Start from the `Pet`, `Dog` and `Cat` classes of Exercise 4, and write in the **`Main`** class two static methods that take lists of different kinds of pet.

1. **`processElements`** receives a `List<? extends Pet>` and calls `speak()` on each of its elements.
2. In `main`, create a `List<Dog>` with the dogs `"Bobby"` and `"Jimmy"`, and a `List<Cat>` with the cats `"Garfield"` and `"Whiskers"`, and pass each one to `processElements`.
3. Try to pass `processElements` a `List<String>`: leave the call as a comment, with the compiler's error and a short explanation above it. Then pass it an `ArrayList` with no type in angle brackets, holding the dog `"Oasis"` and the string `"Boots"`: it compiles, so explain in a comment what happens when it runs, and catch the exception it throws, printing `The list holds something that is not a pet`.
4. **`addDog`** receives a `List<? super Dog>` and a name (`String`), and adds a new dog with that name to the list.
5. In `main`, call `addDog` with the `List<Dog>` of step 2 and the name `"Rex"`, with a new `List<Pet>` and the name `"Toby"`, and with a new `List<Object>` and the name `"Max"`. Then go through the `List<Object>` with a for-each loop, printing `[name] is now in the list` for each dog, and pass the `List<Pet>` to `processElements`.

Explain in a comment why the for-each loop of step 5 reads each element as an `Object`, while the one in `processElements` reads it as a `Pet`.
