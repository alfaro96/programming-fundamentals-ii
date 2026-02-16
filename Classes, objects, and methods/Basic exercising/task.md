# Basic exercising

## Goals

The **main focus** of this laboratory assignment is to **transition** from **real-world mathematical concepts** to **functional Java classes** through abstraction. You will **develop** the skills necessary to define **object structures** and **implement behaviors** using the principles of object-oriented programming.

By the **end** of this **session**, **you will** be able to:

* **Translate real-world concepts** into **Java classes** with appropriate attributes and methods.
* **Define and overload multiple constructors** with different argument sets.
* **Implement** class **methods** to **handle specific functionalities** and mathematical operations.
* **Generate** professional **documentation** using the **Javadoc** standard.

## Tasks

**Implement** a **project** to **handle complex numbers**, which are defined by a real part and an imaginary part ($ real + imaginary \times i $).

### Environment setup

1. **Create the project**: **Select** `New Project` and **choose** a **project name according to your preference**.
2. **Understand packages**: A **package** in Java is a **mechanism used to group related classes**. It acts **similarly** to a **folder system** in your operating system, helping to **avoid naming conflicts** and **making** the **code easier** to **maintain and find**.
3. **Organize the code**: **All** of your **classes** must be **contained** in a **package**. **Following** Java naming **standards**, **package names should be entirely in lowercase**. **Use** `es.uclm.esii.math.complex` as the **package name** for this project.

### Implementation of the `ComplexNumber` class

1. **Attributes**: Define **two attributes**, `real` and `imaginary`, **using** the **appropriate data types** (e.g., `double`).

2. **Constructors**: **Implement** the following **four constructors**:
   * **Default constructor**: Starts **both** fields to **0**.
   * **Integer constructor**: Takes **two integer** values.
   * **Double constructor**: Takes **two double** values.
   * **Copy constructor**: Takes a different `ComplexNumber` **object** and **copies** its **values**.

3. **Methods**: Implement the required functionality for the class:
   * `add()`: **Adds** a **complex number** to the **current one** and **returns** a **new** complex number.
   * `subtract()`: **Subtracts** a **complex number** from the **current one** and **returns** a **new** complex number.
   * `multiply()`: **Overload** this **method** to **accept** either a **scalar value or** another **complex number**.
   * `toString()`: **Returns** a **human-legible text representation** of your **object** , **formatting** its **internal attributes** into a **clear** `String` for **display**.
     * **Logic**: **Concatenate** the `real` and `imaginary` **parts** following the **standard mathematical format**: $ real + imaginary \times i $.
     * **Example**: For a **complex number** with `real = 3.5` and `imaginary = 2.0`, the **method** must **return** the exact **String** `"3.5 + 2.0i"`.
   * `equals()`: **Compares two complex numbers** **using** an **epsilon** comparison to handle rounding errors.

### Testing and documentation

1. **Incremental testing strategy**: **Adopt** a **step-by-step verification** approach. It is **highly recommended** to **test** each **method or constructor** **individually immediately after** its **implementation**. **Ensure** that the **current logic** is **correct before proceeding** to the **next task**, **rather than attempting to debug the entire project only after full completion**.

2. **Verification**: **Create** a separate `Main` **class with** a `main` **method** to serve as your testing environment.
   * **Verify** that **all** four **constructors** initialize the attributes correctly.
   * **Test** the **mathematical operations** (`add`, `subtract`, `multiply`) and **compare** the results against manual calculations.
   * **Check** that `toString()` and `equals()` provide the **expected outputs** for various edge cases.

3. **Javadoc**: **Document every class**, **attribute**, and **method** using the `/** ... */` format.
   * **Navigate** to `Tools` $ \rightarrow $ `Generate JavaDoc...` to **transform** your **comments** into a **professional** HTML **documentation** page.

### Extra: `ComplexNumberArray` class

**Implement** a **class** representing an **array** of **complex numbers**:

* **Attributes**: An **array** of `ComplexNumber` **objects**.
* **Constructors**: A **constructor** that **creates** an **array** of a **specified length** and **fills** it with **random values**.
* **Methods**: **Include** a `sumArray()` **method**, **and** a `toString()` method.
