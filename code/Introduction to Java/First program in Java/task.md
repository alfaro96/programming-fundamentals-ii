# First program in Java

## Goals

You'll **develop fundamental Java programming skills** in this laboratory assignment by **transitioning from** imperative logic in **C to** the **Java** development environment. The **main focus** is on **setting up the workspace** and **translating basic algorithms**.

By the **end** of this **session**, **you will** be able to:

* **Configure and navigate** through the **IDE**.
* **Translate** simple programs from **C to Java** syntax.
* **Implement methods** to solve mathematical problems.
* **Perform console output** using `System.out.println` as an equivalent to `printf`.
* **Verify logic** by **comparing** different **implementation techniques** for the same problem.

## Tasks

**Implement** a **project** to **calculate** the **sum** of the **first** $ n $ **numbers** using iterative logic and mathematical formulas.

### Environment setup

1. **Install and configure** the **IDE**: **Download and install** from: <https://www.jetbrains.com/idea/download/>.
2. **Create the project structure**:
    1. **Select** `New Project` from the **welcome screen**.
    2. **Choose** `Java` in the **left menu**.
    3. **Configure** the **project details**:
        * **Project name**: `Laboratory assignment 0`.
        * **Project location**: **Choose** a **folder** where **you can save** and later delete **your work**.
    4. **Click** the `Create` button to **generate** the **workspace**.

### Implementation of summation functions

**Once** the **project workspace** has been **generated**, **remove** the `Main.java` **file** inside the **source** (`src`) **folder** of your project. **Copy** the `Sum.java` template file **to serve as** the **basis** for your **implementation**. Then, **solve** these **tasks**:

1. **Iterative sum implementation**: **Implement** a `public static` **method** named `sum1` that **calculates** the **summation** of the **first** $ n $ **numbers**: $ sum1(n) = \sum_{i = 0}^{n} i $.
   * **Logic**: **Translate** the implementation **from C**. **Initialize** a **variable to** $0$ and **use** a `for` loop to **accumulate** the **values** from $ 0 $ **to** $ n $.
   * **Syntax**: **Prefix** the method **declaration** with the `public` and `static` **keywords**. These are **access and scope modifiers** required by Java; **for now**, **focus** solely **on** the **implementation**, as their specific technical implications will be covered in later sessions.

2. **Formulaic sum implementation**: **Implement** a second `public static` **method** named `sum2` **using** a **mathematical shortcut**.
   * **Logic**: **Calculate** the **sum using** the **arithmetic progression** formula: $ sum2(n) = \frac{n(n+1)}{2} $.
   * **Efficiency**: **Write** the body of this **function** using **only one or two lines** of code.

3. **Verification and testing**: **Implement** the **logic** within the `main` **method** to **verify** your **functions**. You must **call** both `sum1` and `sum2` and **display** the **results** to the console.
   1. **Individual test**: **Call** the `sum1` method with the **input value** $ 11 $. The **expected output** should be $ 66 $.
   2. **Console output**: **Use** `System.out.println` to **show** the **result**, which is the Java equivalent of `printf` in C.
   3. **Batch testing**: **Implement** a **loop** to **process** the **following array** of values: `{3, 4, 13, 21, 67, 102, 155, 365, 1007}`.
   4. **Comparison**: For each input, **print** the **results** of **both methods** and **verify** they are **identical**.

4. **Automation of the verification process**: **Implement** a logic to **automate** the **comparison** of results, eliminating the need for manual inspection.
   * **Objective**: **Modify** the testing **loop** so the **program provides** a **direct** `boolean` **response** (`true` or `false`) for **each input**. This confirms whether the results of both methods are identical without the user having to perform mental arithmetic.
   * **Logic**: **Update** the `main` method to **compare** the **returned values** of `sum1` and `sum2` using an equality operator for **every element** in the array.

5. **Documentation**: **Generate** the **technical documentation** of your project to **visualize** the **impact** of your **comments**.
   * **Navigate** to the `Tools` menu.
   * **Select** `Generate JavaDoc...`.
   * **Configure** the **output directory** by **selecting** your **project folder followed** by `/doc`.
   * **Check** your **project directory once** the process is **complete**; a **new folder** named `doc` should have been **created**.
   * **Verify** the **result** by **opening** the `index.html` file in a web browser. **Observe** how your **Javadoc comments** (`/** ... */`) have been **transformed** into a **professional** HTML **documentation** page, listing all class details, parameters, and return types.
