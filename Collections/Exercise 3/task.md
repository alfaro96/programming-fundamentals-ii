# Exercise 3

1. Create two independent classes, **`Dog`** and **`Cat`**. Both classes must maintain a private state for their name, which must be provided at the time the object is instantiated.
2. Implement specific behaviors for each animal: a **`bark()`** method in the **`Dog`** class that prints **`"[name]: woof!"`**, and a **`meow()`** method in the **`Cat`** class that prints **`"[name]: meow!"`**.
3. Research and override the appropriate method inherited from the **`Object`** class so that printing the object directly outputs exactly **`"I am the dog [name]"`** or **`"I am the cat [name]"`**, respectively.
4. Create a non-generic **`ArrayList`** named **`animals`**.
5. Instantiate and add the following dogs (**`"Bobby"`**, **`"Jimmy"`**, **`"Oasis"`**, **`"Sanson"`**) and cats (**`"Garfield"`**, **`"Boots"`**, **`"Whiskers"`**) to your list.
6. Iterate through the **`animals`** list using a for-each loop. Because the list holds generic objects, you must write the necessary logic to check the type of each object at runtime in order to safely call either **`bark()`** or **`meow()`**.
7. Finally, create a generic **`ArrayList<Dog>`** and attempt to add both dogs and cats to it. Observe and explain the compiler's behavior.