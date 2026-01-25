# Materializing state and active behavior in memory

This example **demonstrates** the **lifecycle** of an **object**, **from** its **declaration** as a **`null`** reference **to** its **instantiation** and **modification** in memory.

---

## What does it do?

The program **`Main` executes** a series of logical **steps** to **manage** how a **`Player`** exists in the system:

* **Reference declaration**: The code **defines** a **variable** named **`player`** but **sets** it to **`null`** to **show** that **no memory** has been **allocated** for the data yet.
* **Error prevention**: It **includes** a **commented-out section** to **illustrate** that **accessing attributes** of a **`null` reference** results in a **crash**.
* **Object creation**: The program **uses** the **`new`** keyword to **instantiate** the **class**, effectively "**materializing**" the **object** in the heap memory.
* **State definition**: It **assigns** specific **values** to the **`name`**, **`age`**, and **`nationality`** fields by **accessing** them **directly** through the object reference.
* **Verification**: The code **outputs** the **final state** to the console to **confirm** the **attributes** were successfully **modified**.

---

## Key concepts

* The **`null` value** represents the **absence** of an **object**; the reference variable exists, but it doesn't point to any data yet.
* **Instantiation via** the **`new`** operator is the process of **allocating memory** and **creating** a **real instance** of a class.
* **Dot notation** (`player.name`) is the **mechanism** used to **reach** the **attributes** of an object once it has been materialized.

---

## View the files

* [Player.java](file://src/Player.java)
* [Main.java](file://src/Main.java)
