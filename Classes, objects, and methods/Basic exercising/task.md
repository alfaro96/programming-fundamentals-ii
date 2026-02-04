# Basic exercising

This assignment **demonstrates** how to **apply object-oriented abstraction** by **creating** a **`ComplexNumber` class** to **handle mathematical concepts** within Java.

---

## What do you need to do?

**Create** a **project** using a **name of your preference**. You must **implement** the following **structure and logic** to **model complex numbers** ($ real + imaginary \times i $):

* **Package organization**: **Group** your **classes** into a **package using** a **professional naming standard** (e.g., `es.uclm.esi.math.complex`). **Packages** are a **mechanism** used to **group related code**, acting **like** a **folder system** to **organize code** and **prevent naming conflicts**.
* **Attributes**: **Define** two **attributes**, **`real`** and **`imaginary`**, using **appropriate double data types**.
* **Constructor overloading**: **Implement four** different **constructors** to allow flexible object creation: a **default** constructor ($ 0, 0 $), an **integer** constructor, a **double** constructor, and a **copy** constructor.
* **Method implementation**: **Develop** the **following behaviors** for the class:
  * **Arithmetic**: **Implement `add()`**, **`subtract()`**, and **overloaded `multiply()`** (scalar and complex) methods that **return** a **new `ComplexNumber`**.
  * **Comparison**: **Implement `equals()` using** an **epsilon comparison** to **account** for **double rounding errors**.
* **Incremental testing**: **Create** a **separate `Main` class**. **Remember to test every component you implement individually before moving to the next task and performing a final verification**.
* **Documentation**: **Write Javadoc comments** (`/** */`) for **every class**, **attribute**, and **method**, then **generate** and **verify** the **technical documentation** via **`Tools -> Generate Javadoc...`**.

---

## Key concepts

* **Abstraction** is the **process** of **translating real-world concepts** (like complex numbers) **into Java classes** with **attributes** and **methods**.
* **Method overloading allows** you to have **multiple methods** with the **same name** but **different arguments**, providing functional flexibility.
* The **`toString()` method returns** a **human-legible text representation** of your **object** (e.g., `"3.5 + 2.0i"`), **formatting** its **internal attributes** into a **clear `String`** for display.
* **Packages group related classes**, acting **like** a **folder system** to **organize code** and **prevent naming conflicts** according to industry standards.
* **Javadoc transforms comments** into **professional** HTML **pages**, **listing** all **parameters** and **return types** for the project.

---

## View the files

* [ComplexNumber.java](file://src/es/uclm/esi/math/complex/ComplexNumber.java)
* [Main.java](file://src/es/uclm/esi/math/complex/Main.java)