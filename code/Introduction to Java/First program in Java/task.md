# First program in Java

## Goals

In this laboratory assignment you'll take your first steps in Java, moving from C to the Java development environment. The main focus is on **setting up the workspace** and **translating basic algorithms**.

By the end of this session, you will be able to:

* Configure and navigate through the IDE.
* Translate simple programs from C to Java syntax.
* Write methods that solve mathematical problems.
* Print to the console with `System.out.printf`, as in C.
* Document your code with Javadoc comments and generate its documentation.
* Verify logic by comparing different implementations of the same problem.

## Tasks

Write a program that calculates the **sum of the integers from $0$ to $n$** in two ways, with a loop and with a mathematical formula, and checks that both agree.

### Environment setup

1. **Install and configure the IDE**: download and install it from <https://www.jetbrains.com/idea/download/>.
2. **Create the project structure**:
    1. Select `New Project` from the welcome screen.
    2. Choose `Java` in the left menu.
    3. Configure the project details:
        * **Project name**: `Laboratory assignment 0`.
        * **Project location**: choose a folder where you can save and later delete your work.
    4. Click the `Create` button to generate the workspace.

### Implementation of the summation methods

Once the project workspace has been generated, remove the `Main.java` file inside the source (`src`) folder of your project and copy the `Sum.java` template provided with this assignment into it. Each `TODO` comment in the template marks a part you have to write.

In Java, functions are called **methods**. You declare one as a function in C, with the `public` and `static` keywords in front, for example `public static int sum1(int n)`. These keywords are required by Java; for now, write them as they are, as in `main`: what they mean will be covered in later sessions.

1. **Iterative sum**: write a method named `sum1` that receives an `int` $n$ and returns the sum of the integers from $0$ to $n$: $sum1(n) = \sum_{i = 0}^{n} i$.
    * **Logic**: translate the implementation from C. Initialize a variable to $0$ and use a `for` loop to add the values from $0$ to $n$.

2. **Formula sum**: write a second method named `sum2` that returns the same sum using the arithmetic progression formula: $sum2(n) = \frac{n(n + 1)}{2}$.
    * **Logic**: its body is a single `return` statement.

3. **Verification**: write the body of `main` to test both methods, printing the results with `System.out.printf`, which works as `printf` in C (with `%d` for an `int` and `\n` to end the line).
    1. **Individual test**: print the results of `sum1` and `sum2` for $n = 11$. Both must be $66$.
    2. **Batch test**: use a loop to go through the following array of values: `{3, 4, 13, 21, 67, 102, 155, 365, 1007}`. For each value, print $n$, the results of both methods and whether they are equal.
    3. **Automatic comparison**: instead of checking the numbers yourself, let the program tell you. Compare the results of `sum1` and `sum2` with `==`, which gives a `boolean`, and print it with `%b`, which prints `true` or `false`. Every value in the array must print `true`.

4. **Documentation comments**: document the `Sum` class and the `sum1` and `sum2` methods with **Javadoc comments** (`/** ... */`), placed right above what they describe. The comment of `main` in the template is an example to follow:
    * The first sentence says what the class or method does.
    * `@param` followed by a parameter's name describes that parameter, one line per parameter.
    * `@return` describes the value the method returns.
    * `@author` followed by your name, in the comment of the class.
    * Javadoc comments are HTML, not Markdown: write code (names, expressions) as `{@code sum1}` and use HTML for formulas, such as `<i>n</i>(<i>n</i> + 1) / 2`.

5. **Documentation generation**: generate the documentation of your project to see what your comments become.
    1. Open the `Tools` menu.
    2. Select `Generate JavaDoc...`.
    3. Set the output directory to your project folder followed by `/doc`.
    4. Once the process is complete, check that a new folder named `doc` has been created in your project directory.
    5. Open its `index.html` file in a web browser. Observe how your Javadoc comments have been turned into an HTML documentation page, listing the class, its methods, their parameters and their return values.
