# Exercise 4

1. Create a base class **`Pet`**. It must manage a state for the pet's name, which should be initialized via its constructor. Implement a **`speak()`** method that prints exactly **`"[name] makes a sound."`**
2. Create two child classes, **`Dog`** and **`Cat`**, that inherit from **`Pet`**. In addition to the inherited capabilities, implement a specific **`bark()`** method for the dog class (printing **`"[name]: woof!"`**) and a **`meow()`** method for the cat class (printing **`"[name]: meow!"`**). Think about how to properly initialize the inherited state from these subclasses.
3. Create a generic **`ArrayList`** designed to hold **`Pet`** objects, named **`pets`**. Instantiate several dog and cat objects and add them to this collection.
4. Iterate through the **`pets`** list using a **`for-each`** loop. Implement the necessary logic to safely identify the specific runtime type of each animal and invoke its unique, non-inherited sound method (**`bark()`** or **`meow()`**).
5. Finally, within the same loop, invoke the **`speak()`** method on each object directly from the **`Pet`** reference. Observe, analyze, and explain how the compiler handles this compared to the type-checking required in the previous step.
