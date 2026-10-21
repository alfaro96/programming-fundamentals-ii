# Exercise 1

Write a **`Main`** class whose `main` works with a list of colors, step by step, using an `ArrayList` with no type in angle brackets, which takes any object.

1. Create an `ArrayList` named `colors`, initially empty, with no type in angle brackets.
2. Add the strings `"Red"`, `"Green"`, `"Orange"`, `"White"` and `"Black"`, in that order.
3. Print `Step 3 - Initial list: ` followed by the list.
4. Insert `"Pink"` at position 0, and print `Step 4 - After inserting Pink at 0: ` followed by the list.
5. Replace the element at position 3, which is now `"Orange"`, with `"Blue"`.
6. Add `"Purple"` at the end, and print `Step 6 - After adding Purple at the end: ` followed by the list.
7. Print `Step 7 - Elements at even positions: ` followed by the elements at positions 0, 2, 4..., separated by `, `, on the same line.
8. Remove the element at position 4, and print `Step 8 - After removing the element at position 4: ` followed by the list.
9. Sort the list with `Collections.sort(...)`, and print `Step 9 - Sorted list: ` followed by the list.
10. Add the integer `100` to the list, and print `Step 10 - After adding an integer: ` followed by the list.
11. Print `Step 11 - Length of the string at position 3: ` followed by the length of the string at position 3.

Each step that prints writes a single line, and a list is printed by joining it to the text with `+`, as the slides print it. Think about why step 10 compiles, and what step 11 needs before it can call `length()`.
