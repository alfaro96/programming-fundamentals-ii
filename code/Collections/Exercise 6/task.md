# Exercise 6

Start from the `Pet`, `Dog` and `Cat` classes of Exercise 4, and write a **`Main`** class whose `main` goes through a list of pets with iterators.

Create a `List<Pet>` named `pets` with the dog `"Bobby"`, the dog `"Rex"`, the cat `"Garfield"`, the cat `"Boots"`, the dog `"Jimmy"` and the cat `"Luna"`, in that order. Then:

1. Print `Step 1 - Iterator with no type`, and go through `pets` with an `Iterator` with no type in angle brackets, printing the name of each pet on its own line.
2. Print `Step 2 - Iterator<Pet>`, and do the same with an `Iterator<Pet>`.
3. Create a `List<String>` named `names` with the names of the pets, in the same order, and a second one, `copy`, with the same names. Print `Step 3 - Names: ` followed by `names`.
4. Remove from `names` every name that does not have five letters, going through it with an iterator. Print `Step 4 - Five letters, with an iterator: ` followed by `names`.
5. Do the same on `copy` without an iterator, with a loop by position. Print `Step 5 - Five letters, by position: ` followed by `copy`.
6. Reverse `names`, and print `Step 6 - Reversed: ` followed by it.

Think about which way the loop of step 5 has to go, and why a for-each loop that calls `remove` on the list does not work.
