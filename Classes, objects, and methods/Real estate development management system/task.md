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

## Phase 2

### 1. Introduction

This document describes the implementation of the `Building` class, which represents the **intermediate level of complexity** of the system. A building manages collections of **apartments**, **parking spaces**, and **storage rooms**, utilizing the basic classes developed in the previous phase.

#### 1.1. Prerequisites

Before implementing this class, the following classes must be completed:

* `Apartment` (including `Status` and `Quality` enumerations).
* `Parking`.
* `Storage`.

#### 1.2. Objectives of this phase

* **Manage 2D arrays** (apartments, garage) and **1D arrays** (storage rooms).
* **Implement initialization methods** and automatic data generation.
* **Create visualizations** of the building status.
* **Develop search and filtering functions**.
* **Implement complex operations** such as merging properties.

### 2. Structure of the `Building` class

#### 2.1. Main attributes

* `name` (`String`): Name of the building.
* `apartments` (`Apartment[][]`): **2D matrix** representing `[floor][door]`.
* `numFloors` (`int`): Total number of floors.
* `apartmentsPerFloor` (`int`): Number of apartments per floor.
* `garage` (`Parking[][]`): **2D matrix** representing `[floor][spot]`. **There are 2 fixed garage floors**.
* `spotsPerGarageFloor` (`int`): Number of parking spots per garage floor.
* `storageRooms` (`Storage[]`): **1D array** of storage units.
* `numStorageRooms` (`int`): Total number of storage rooms.

#### 2.2. Constants

* `GARAGE_FLOORS`: A constant set to **2**. The garage always has exactly 2 floors (basement -1 and basement -2).

### 3. Constructor and initialization

#### 3.1. Main constructor

* **Signature**: `public Building(String name, int numFloors, int apartmentsPerFloor, int spotsPerGarageFloor, int numStorageRooms)`.
* **Actions**:
    1. Assign parameters to corresponding attributes.
    2. **Initialize** the apartments matrix: `new Apartment[numFloors][apartmentsPerFloor]`.
    3. **Initialize** the garage matrix: `new Parking[Building.GARAGE_FLOORS][spotsPerGarageFloor]`.
    4. **Initialize** the storage array: `new Storage[numStorageRooms]`.
    5. Call `generateRandomApartments()`.
    6. Call `generateRandomGarage()`.
    7. Call `generateRandomStorage()`.

#### 3.2. Automatic random generation

The constructor automatically calls the random generation methods. It is not necessary to manually initialize with default values.

##### 3.2.1. `generateRandomApartments()`

Generate apartments with realistic random characteristics.

* **Logic**: Iterate through all floors and doors. For each position, generate random values within realistic ranges.
* **Ranges**:
    * **Price**: $ 80,000 + (floor \times 10,000) + random(0 - 120,000) $ euros.
    * **Surface**: **40** to **180** $ m^2 $.
    * **Rooms**: **1** to **5**.

##### 3.2.2. `generateRandomGarage()`

Generate parking spaces with random characteristics.

* **Ranges**:
    * **Price**: **8,000** to **30,000** euros.
    * **Surface**: **8** to **20** $ m^2 $.

##### 3.2.3. `generateRandomStorage()`

Generate storage units with random characteristics.

* **Ranges**:
    * **Price**: **1,500** to **8,000** euros.
    * **Surface**: **3** to **15** $ m^2 $.

### 4. Basic access methods

#### 4.1. General getters

* `getName()` and `setName(String)`: Gets and sets the building name, respectively.
* `getNumFloors()`: Returns the number of floors.
* `getApartmentsPerFloor()`: Returns the number of apartments per floor.
* `getGarageFloors()`: Returns the constant `GARAGE_FLOORS`.
* `getSpotsPerGarageFloor()`: Returns the number of spots per garage floor.
* `getNumStorageRooms()`: Returns the number of storage rooms.

#### 4.2. Property access methods

* `getApartment(int floor, int door)`:
    * **Validates** that indices are within limits.
    * Returns the `Apartment` at the specific position or `null` if invalid.
* `setApartment(int floor, int door, Apartment apartment)`:
    * **Validates** indices before assignment.
    * Useful for merging operations.
* **Note**: Methods `getParking(int basement, int spot)` and `getStorage(int index)` work similarly.

### 5. Visualization methods

#### 5.1. `showStatus()`

Show a tabular view of the building's apartments.

* **Output format**:
    * Title with building name and legend.
    * Header with door numbers.
    * Floors displayed from **top to bottom** (highest to lowest).
    * Status of each apartment using `toString()` (`"F"`/`"R"`/`"S"`).

#### 5.2. `showBuildingMatrix()`

Show a complete view of the building including apartments, garage, and storage. Includes three sections:

1. **Apartments**: Matrix of floors and doors.
2. **Garage**: Matrix of basements and spots.
3. **Storage**: Linear array of storage units.

### 6. Counting and statistics methods

These methods provide quantitative information about the building's state.

#### 6.1. `Apartment` counting methods

* `countAvailableApartments()`: Counts apartments with status `FREE`.
* `countReservedApartments()`: Counts apartments with status `RESERVED`.
* `countSoldApartments()`: Counts apartments with status `SOLD`.
* `getTotalApartments()`: Counts all non-null apartments.

#### 6.2. Income calculation methods

* `calculatePotentialIncome()`: Sums the prices of **all** apartments (sold or not).
* `calculateSoldIncome()`: Sums the prices of **only** `SOLD` apartments.
* **Note**: Counting and income methods must have equivalent versions for `Parking` and `Storage`.

### 7. Search and filter methods

#### 7.1. Individual criteria search (apartments)

* `searchApartmentsBySurface(double min, double max)`:
    * Iterates through all apartments.
    * Uses `matchesSurface()` from the `Apartment` class.
    * Displays available ones meeting criteria.
* `searchApartmentsByPrice(double min, double max)`: Similar to above, filtering by price.
* `searchApartmentsByRooms(int min, int max)`: Filters by number of rooms.

#### 7.2. Combined search (apartments)

* `searchApartments(double minSurface, double maxSurface, double minPrice, double maxPrice, int minRooms, int maxRooms)`:
    * Searches for apartments meeting **ALL** criteria simultaneously.
    * Uses the three `matches...()` methods from the `Apartment` class.

#### 7.3. `Parking` and `Storage` search

Implement analogous methods for `Parking` and `Storage` (e.g., search by surface, price, or size classification).

### 8. Complex operations

#### 8.1. Merging apartments

##### 8.1.1. `canJoinApartments(int floor, int door1, int door2)`

Verify if two apartments can be joined.

* **Conditions**:
    * Doors must be **contiguous**: $ | $`door1` $ - $ `door2`$ | == 1 $.
    * Both apartments must **exist** (not `null`).
    * Both must be **available** (status `FREE`).

##### 8.1.2. `joinApartments(int floor, int door1, int door2, String dni, Quality quality)`

Merge two contiguous apartments into one.

* **Process**:
    1. Ensure `door1` $ < $ `door2` (swap if necessary).
    2. Verify they can be joined (call `canJoinApartments`).
    3. **Sum**: Price, square meters, and rooms of both units.
    4. Create a `new Apartment` with summed values.
    5. **Sell** the new apartment to the buyer with specified quality.
    6. Place the unified apartment at `door1`.
    7. **Shift** all subsequent apartments to the left starting from `door2`.
    8. Set the last position to `null`.
    9. Return `true` if successful.

#### 8.2. Merging `Storage` units

Works similarly to apartments:

* `canJoinStorage(int index1, int index2)`: Verify contiguous indices and existence.
* `joinStorage(int index1, int index2, String dni)`:
    * Sum price and surface.
    * Create new unit and mark as `SOLD`.
    * **Shift** the array to the left.

### 9. DNI query methods

These methods find all properties belonging to a specific buyer.

#### 9.1. Counting methods

* `countApartmentsByDni(String dni)`
* `countParkingByDni(String dni)`
* `countStorageByDni(String dni)`

#### 9.2. Listing methods

* `listApartmentsByDni(String dni)`
* `listParkingByDni(String dni)`
* `listStorageByDni(String dni)`
* **Implementation**: Iterate through properties, compare DNI using `equalsIgnoreCase()`, and display matches.

### 10. Recommended implementation order

1. **Basic structure**: Class declaration, attributes, constructor, random generation, basic getters.
2. **Property access**: `getApartment`, `setApartment`, etc.
3. **Visualization**: `showStatus` (apartments) and `showBuildingMatrix` (full).
4. **Counting methods**: Counters for all property types and states.
5. **Income calculation**: Revenue logic.
6. **Search and filter**: Individual and combined searches.
7. **DNI queries**: Counts and lists by buyer.
8. **Complex operations**: Merging logic for apartments and storage.

### 11. Key concepts

* **Array management**: Handling 2D matrices and 1D arrays.
* **Index validation**: Checking bounds before accessing arrays.
* **Array shifting**: Technique to remove elements while maintaining order.
