# Real estate development management system

## Phase 1

### 1. Introduction

#### 1.1. Problem description

A **real estate developer** needs a computer system to **manage multiple residential buildings**. Each building contains different types of properties: **apartments** on the upper floors, **parking spaces** in the basements, and **storage rooms**. The developer requires the ability to:

* **Register and visualize** the status of all properties (**free, reserved, or sold**).
* **Manage the sale and reservation** of apartments with different **quality levels**.
* **Sell** parking spaces and storage rooms **individually**.
* **Search** for available properties according to various criteria like **price, surface area, and rooms**.
* **Generate statistics and reports** on income and availability.
* **Save and recover** all system information.

The system will be developed using an **incremental methodology**, starting with the **most basic classes** (individual properties) and progressing towards higher complexity layers.

#### 1.2. About this document

This document outlines the implementation of the **core entities** for the real estate management system. It focuses on the three foundational classes representing individual properties: `Apartment`, `Parking`, and `Storage`.

#### 1.3. Objectives

The primary goals for this initial development phase are:

* **Implement the core property classes** and define their essential attributes.
* **Develop getter** and **setter methods** to ensure **controlled data access**.
* **Create robust query and validation methods** for data integrity.
* **Program the core transaction logic** for property **sales and releases**.

### 2. `Apartment` class

#### 2.1. Definition and package

The class must be defined as **public** to be accessible throughout the system layers.

* **Package**: `com.realestate.management.model`
* **Class name**: `Apartment`

#### 2.2. Internal data structures

To maintain data integrity, **define** the following **public enumeration** structures inside the class:

* `Status`: Represents the sales lifecycle of a unit.
    * `FREE`: The unit is available for sale or reservation.
    * `RESERVED`: The unit is temporarily held by a client.
    * `SOLD`: The unit has been successfully transferred to a buyer.

* `Quality`: Defines construction tiers and their respective **price multipliers**.
    * `STANDARD`: Multiplier **1.0** (base price).
    * `PLUS`: Multiplier **1.05** (+5% increase).
    * `DELUXE`: Multiplier **1.10** (+10% increase).

#### 2.3. Private attributes

All attributes must be **private** to prevent direct external access and ensure proper **data encapsulation**:

* `status` (`Status`): Current availability state.
* `price` (`double`): Base monetary value in euros.
* `squareMeters` (`double`): The surface area of the unit.
* `rooms` (`int`): Number of bedrooms.
* `buyerDni` (`String`): Identification of the customer (`null` if available).
* `quality` (`Quality`): The specific tier applied to the unit.

#### 2.4. Public constructor

The **public constructor** initializes the apartment with the essential parameters provided at creation:

* **Signature**: `public Apartment(double price, double squareMeters, int rooms)`.
* **Initial state**: `status` must be set to `FREE`.
* **Defaults**: `buyerDni` and `quality` are initialized as `null`.

#### 2.5. Public methods and business logic

##### 2.5.1. Accessors

**Implement public** methods to provide controlled access to all **private attributes**. Each field must have a **getter** to retrieve the value and a **setter** to modify it, following these specific rules:

* **Status management**: `getStatus()` and `setStatus(Status)` to track the property lifecycle.
* **Surface and rooms**:
    * `getSquareMeters()` and `setSquareMeters(double)`.
    * `getRooms()` and `setRooms(int)`.
* **Buyer information**: `getBuyerDni()` and `setBuyerDni(String)` to manage ownership data.
* **Quality tier**: `getQuality()` and `setQuality(Quality)` to handle construction standards.
* **Price logic**:
    * `setPrice(double)`: This setter **modifies the base price** of the unit.
    * `getBasePrice()`: Returns the **original price** without any multipliers applied.
    * `getPrice()`: This getter **must calculate and return** the final price by applying the **quality multiplier** (if a quality tier is assigned).

##### 2.5.2. Search and query logic

These methods return `boolean` values based on specific search filters:

* `isAvailable()`: Returns `true` if the state is `FREE`.
* `matchesSurface(double min, double max)`: Verifies if the area falls within the range.
* `matchesPrice(double min, double max)`: Verifies if the **calculated price** falls within the range.
* `matchesRooms(int min, int max)`: Verifies if the bedroom count is within the range.

##### 2.5.3. Transaction management

* `sell(String dni, Quality quality)`: Updates state to `SOLD`. **It is mandatory to validate** that the DNI is not `null` or empty.
* `sell(String dni)`: **Overloaded version** that performs the sale using `Quality.STANDARD` by default.
* `reserve(String dni, Quality quality)`: Updates state to `RESERVED` following similar validation.
* `reserve(String dni)`: **Overloaded version** using `Quality.STANDARD` by default.
* `release()`: **Resets** the property to `FREE` and clears the `buyerDni` and `quality` fields.

##### 2.5.4. Representation

* `toString()`: **Overrides** the standard method to return a **single-letter code** representing the current **Status** for map visualization:
    * `"F"` if the status is `FREE`.
    * `"R"` if the status is `RESERVED`.
    * `"S"` if the status is `SOLD`.
* `getDetails()`: Returns a `String` containing the full status, including final price, area, and buyer info if applicable.

### 3. `Parking` class

#### 3.1. Definition and package

This class models a parking space within the building's basement levels.

* **Package**: `com.realestate.management.model`
* **Class name**: `Parking`

#### 3.2. Internal data structures

The parking unit has a simplified lifecycle compared to apartments:

* `Status`: Defines the availability of the parking space.
    * `FREE`: The space is available for purchase.
    * `SOLD`: The space has been successfully sold to a buyer.

#### 3.3. Private attributes

Attributes must remain **private** to ensure proper **encapsulation**:

* `status` (`Status`): Current availability state.
* `price` (`double`): Monetary value of the space in euros.
* `squareMeters` (`double`): The surface area of the parking spot.
* `buyerDni` (`String`): Identification of the customer (`null` if not sold).

#### 3.4. Constants

The class uses a specific **static threshold** to classify the size of the space:

* `THRESHOLD`: A `static final double` set to **12.0**.

#### 3.5. Public constructor

The **public constructor** initializes the unit with the dimensions and price:

* **Signature**: `public Parking(double price, double squareMeters)`.
* **Initial state**: `status` is set to `FREE`.
* **Defaults**: `buyerDni` is initialized as `null`.

#### 3.6. Public methods and business logic

##### 3.6.1. Accessors

**Implement public** methods to provide controlled access to all **private attributes**. Each field must have a **getter** to retrieve the value and a **setter** to modify it, following these specific rules:

* **Status management**: `getStatus()` and `setStatus(Status)` to track whether the space is currently available or sold.
* **Surface area**: `getSquareMeters()` and `setSquareMeters(double)` to manage the physical dimensions of the parking spot.
* **Buyer information**: `getBuyerDni()` and `setBuyerDni(String)` to handle the identification of the owner (`null` if the spot is `FREE`).
* **Price logic**: `getPrice()` and `setPrice(double)` to manage the monetary value of the unit. Note that unlike apartments, parking prices are **standard** and do not involve quality multipliers or tiers.

##### 3.6.2. Search and query logic

These methods facilitate property filtering and size classification:

* `isLarge()`: Returns `true` if `squareMeters` exceeds the `THRESHOLD` (**12.0**).
* `matchesSurface(min, max)`: Verifies if the area falls within the specified range.
* `matchesPrice(min, max)`: Verifies if the price is within the range.
* `matchesSize(int filter)`: Filters by size based on the following input:
    * `0`: Any size.
    * `1`: Small units ($ \leq $ **12.0** $ m^2 $).
    * `2`: Large units ($ > $ **12.0** $ m^2 $).
* `isAvailable()`: Returns `true` if the state is `FREE`.

##### 3.6.3. Transaction management

* `sell(String dni)`: Updates state to `SOLD` and assigns the buyer. **It is mandatory to validate** that the DNI is not `null` or empty.
* `release()`: **Resets** the property to `FREE` and clears the `buyerDni`.

##### 3.6.4. Representation

* `toString()`: **Overrides** the standard method to return a **single-letter code**:
    * `"F"` if the status is `FREE`.
    * `"S"` if the status is `SOLD`.
* `getDetails()`: Returns a `String` containing the full status, price, surface, and whether it is classified as **large** or **small**.

### 4. `Storage` class

#### 4.1. Definition and package

The `Storage` class represents a storage unit typically located in the basement or specific service areas of the building.

* **Package**: `com.realestate.management.model`
* **Class name**: `Storage`

#### 4.2. Internal data structures

Similar to parking spaces, storage units follow a simplified status model:

* `Status`: Defines the availability of the unit.
    * `FREE`: The unit is available for purchase.
    * `SOLD`: The unit has been successfully sold.

#### 4.3. Private attributes

Attributes must be declared as **private** to ensure proper **data encapsulation**:

* `status` (`Status`): Current availability state.
* `price` (`double`): Monetary value of the storage unit.
* `squareMeters` (`double`): The surface area of the unit.
* `buyerDni` (`String`): Identification of the owner (`null` if not sold).

#### 4.4. Constants

The `Storage` class uses a specific **threshold** for classification that differs from parking units:

* `THRESHOLD`: A `static final double` set to **7.0**.

#### 4.5. Public constructor

The **public constructor** initializes the storage unit with its specific dimensions and base price:

* **Signature**: `public Storage(double price, double squareMeters)`.
* **Initial state**: `status` is set to `FREE`.
* **Defaults**: `buyerDni` is initialized as `null`.

#### 4.6. Public methods and business logic

##### 4.6.1. Accessors

**Implement public** methods for controlled access:

* **Status management**: `getStatus()` and `setStatus(Status)`.
* **Surface area**: `getSquareMeters()` and `setSquareMeters(double)`.
* **Buyer information**: `getBuyerDni()` and `setBuyerDni(String)`.
* **Price logic**: `getPrice()` and `setPrice(double)`. As with parking, there are no multipliers.

##### 4.6.2. Search and query logic

Methods to verify criteria and classify units:

* `isLarge()`: Returns `true` if `squareMeters` exceeds **7.0**.
* `matchesSurface(min, max)`: Verifies if the area falls within the specified range.
* `matchesPrice(min, max)`: Verifies if the price is within the range.
* `matchesSize(int filter)`: Filters by size:
    * `0`: Any size.
    * `1`: Small units ($ \leq $ **7.0** $ m^2 $).
    * `2`: Large units ($ > $ **7.0** $ m^2 $).
* `isAvailable()`: Returns `true` if the state is `FREE`.

##### 4.6.3. Transaction management

Logic for managing the unit's lifecycle:

* `sell(String dni)`: Updates state to `SOLD` and assigns the buyer. **It is mandatory to validate** that the DNI is not `null` or empty.
* `release()`: **Resets** the property to `FREE` and clears the `buyerDni`.

##### 4.6.4. Representation

Methods for human-legible output:

* `toString()`: **Overrides** the standard method to return a **single-letter code**:
    * `"F"` if the status is `FREE`.
    * `"S"` if the status is `SOLD`.
* `getDetails()`: Returns a `String` containing the full status, price, surface, and size classification (**large** or **small**).

### 5. Implementation workflow

#### 5.1. Implementation order

1. **Project structure and base classes**:
    * **Task**: Create the package `com.realestate.management.model` and define the empty class files: `Apartment.java`, `Parking.java`, and `Storage.java`.
    * **Testing**: Verify that the compiler recognizes the package and the files without errors.

2. **Internal enumerations**:
    * **Task**: Define the `Status` and `Quality` enums inside the relevant classes to establish the possible states and tiers.
    * **Testing**: Compile the code to ensure the enum definitions are syntactically correct.

3. **Private attributes**:
    * **Task**: Declare all class fields as **private** to enforce **encapsulation**.
    * **Testing**: Ensure you are using the correct data types (e.g., `double` for price, `String` for DNI).

4. **Public constructors**:
    * **Task**: Implement the constructors to initialize objects with specific values and set default states (like `FREE`).
    * **Testing**: Go to your `Main` class and try to **instantiate** one object of each type to check if they are created correctly.

5. **Accessors**:
    * **Task**: Implement all getters and setters, including the **dynamic price calculation** for apartments.
    * **Testing**: In the `Main` class, use a **setter** to change a value and a **getter** to print it. Verify that the apartment's `getPrice()` correctly applies the **quality multiplier**.

6. **Query methods**:
    * **Task**: Develop the logic for `isAvailable()`, `matchesSurface()`, `matchesPrice()`, and `isLarge()`.
    * **Testing**: Call these methods with different input parameters and **print the `boolean` results** to confirm the logic is sound.

7. **Transaction methods**:
    * **Task**: Implement `sell()`, `reserve()`, and `release()`, ensuring you **validate** the buyer's DNI.
    * **Testing**: Perform a **test sale**. Check that the status changes from `FREE` to `SOLD` and that the buyer's DNI is correctly assigned.

8. **Representation methods**:
    * **Task**: Override `toString()` for state codes and implement `getDetails()` for full reports.
    * **Testing**: Print your objects directly to the console to verify that the **formatted output** looks professional and clear.

### 6. Testing environment

#### 6.1. `Main` class

To verify the entire system, you must create a separate class named `Main.java`.

* **Location**: This file should be placed **directly within the `src` folder**, outside of the package.
* **Imports**: Since it is in a different package, you will need to **import** your model classes to use them.
* **Purpose**: Use this class as your **central testing hub**. It should contain a `public static void main(String[] args)` method where you simulate different scenarios, such as creating a deluxe apartment, selling a large parking space, or searching for units within a price range.

**Reminder**: **Adopt an incremental testing strategy**. Always **verify** that the current step works perfectly before implementing the next one. This prevents small errors from becoming difficult-to-solve problems later.
