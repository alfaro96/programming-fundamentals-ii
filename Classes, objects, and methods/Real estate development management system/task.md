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

## Phase 3

### 1. Introduction

This document describes the implementation of the highest level of the system: the `Developer` class, which manages multiple buildings, and the user interface (`Main`). This phase integrates all previously developed components.

#### 1.1. Prerequisites

Before implementing this phase, the following must be completed:

* `Apartment`, `Parking`, and `Storage` classes.
* `Building` class with all its methods.

#### 1.2. Objectives of this phase

* Manage dynamic collections of buildings.
* Implement aggregated operations across multiple buildings.
* Develop a complete user interface with menus.

### 2. `Developer` class

#### 2.1. Structure

##### 2.1.1. Main attributes

* **name** (`String`): Name of the real estate developer.
* **buildings** (`Building[]`): Dynamic array of buildings.
* **numBuildings** (`int`): Counter for buildings currently in the array.

##### 2.1.2. Dynamic array management

The `buildings` array is managed dynamically:

* Initial capacity: 3 buildings.
* When full, it automatically doubles in capacity.
* `numBuildings` tracks how many buildings actually exist.

#### 2.2. Constructor and building management

##### 2.2.1. `public Developer(String name)`

1. Assigns the developer's name.
2. Initializes the array with capacity 3: `buildings = new Building[3]`.
3. Creates two new buildings with fixed parameters and places them in the array:
   * `new Building("Building 1", 5, 6, 30, 30)`
   * `new Building("Building 2", 3, 2, 12, 12)`
4. Sets `numBuildings = 2`.

##### 2.2.2. `addBuilding(Building building)`

1. Verify if the array is full (`numBuildings == buildings.length`).
2. If full: double capacity with `Arrays.copyOf(buildings, buildings.length * 2)`.
3. Add the building at the `numBuildings` position.
4. Increment `numBuildings`.

##### 2.2.3. Access methods

* `getBuilding(int index)`: Returns the building at the specified index (with bounds validation).
* `getBuildings()`: Returns a copy of the array containing only valid buildings.
* `getNumBuildings()`: Returns the number of buildings.

#### 2.3. Sales operations

The `Developer` delegates sales operations to the corresponding buildings, but adds validation and informative messages.

##### 2.3.1. `public boolean sellApartment(int buildingIndex, int floor, int door, String dni, Apartment.Quality quality)`

1. Obtain the building using `getBuilding(buildingIndex)`.
2. If the building is `null`: show an error and return `false`.
3. Obtain the apartment from the building.
4. If the apartment is `null`: show an error and return `false`.
5. Attempt to sell the apartment by calling `apartment.sell(dni, quality)`.
6. If successful: show a confirmation message with details.
7. Return the result.

##### 2.3.2. `public boolean reserveApartment(int buildingIndex, int floor, int door, String dni, Apartment.Quality quality)`

Works similarly to `sellApartment()` but calls `apartment.reserve()` instead of `sell()`.

##### 2.3.3. `public boolean sellParking(int buildingIndex, int basement, int spot, String dni)`

* Validate that the building exists.
* Validate that the parking spot exists.
* Validate that the parking spot is available.
* Sell and show a confirmation message.

##### 2.3.4. `public boolean sellStorage(int buildingIndex, int storageIndex, String dni)`

Works analogously to `sellParking()`.

#### 2.4. Aggregated statistics

##### 2.4.1. `public void showGeneralStatistics()`

Show a complete summary of all properties across all buildings:

* **For `Apartment`**: Total apartments; quantity free, reserved, and sold; potential income (sum of all); real income (sum of sold).
* **For `Parking` spaces**: Total parking spaces; quantity free and sold; potential and real income.
* **For `Storage` units**: Total storage units; quantity free and sold; potential and real income.

The process is:

1. Iterate through all buildings of the developer.
2. For each building, use its counting methods (e.g., `countAvailableApartments()`).
3. Accumulate the totals.
4. Show a formatted summary.

#### 2.5. DNI queries at developer level

##### 2.5.1. `listPropertiesByDni(String dni)`

Show all properties of a buyer across all buildings:

1. Iterate through all buildings.
2. For each building, search for apartments associated with the specified DNI.
3. If any are found, call `building.listApartmentsByDni(dni)`.
4. Accumulate the total number of apartments and the investment.
5. Repeat the process for parking spaces.
6. Repeat the process for storage units.
7. Show a total summary with the global investment.

##### 2.5.2. Auxiliary methods

* `countParkingByDni(String dni)`: Sums the results from all buildings.
* `countStorageByDni(String dni)`: Sums the results from all buildings.

#### 2.6. Developer-level searches

The `Developer` implements search methods that iterate through all buildings.

##### 2.6.1. Apartment searches

* `searchApartmentsBySurface(double minSurface, double maxSurface)`
* `searchApartmentsByPrice(double minPrice, double maxPrice)`
* `searchApartmentsByRooms(int minRooms, int maxRooms)`
* `searchApartments(double minSurface, double maxSurface, double minPrice, double maxPrice, int minRooms, int maxRooms)`

##### 2.6.2. Parking searches

* `searchParkingBySurface(double minSurface, double maxSurface)`
* `searchParkingByPrice(double minPrice, double maxPrice)`
* `searchParkingBySize(int sizeFilter)`
* `searchParking(double minSurface, double maxSurface, double minPrice, double maxPrice, int sizeFilter)`

##### 2.6.3. Storage searches

Analogous methods to parking searches.

##### 2.6.4. Implementation pattern

All these methods follow this pattern:

1. Display search title.
2. Verify if there are registered buildings.
3. Iterate through all buildings.
4. For each building, call the corresponding search method.

### 3. User interface (`Main`)

The `Main` class provides the user interface through a console-based menu system.

#### 3.1. Static attributes

* **scanner** (`Scanner`): Global scanner to read user input.
* **developer** (`Developer`): Instance of the developer being managed.

#### 3.2. `main(String[] args)` method

**Entry point of the program**:

1. Call `initialize()`.
2. Call `mainMenu()`.

##### 3.2.1. `initialize()` method

Initialize the system by creating a new developer:

1. Display a welcome message.
2. Ask the user for the developer's name and create a new `Developer`.

#### 3.3. Main menu structure

##### 3.3.1. Menu options

| Section | Option | Description |
| :--- | :---: | :--- |
| **`Apartment`** | 1 | View apartment status. |
| | 2 | Sell or reserve apartment. |
| | 3 | Join apartments. |
| | 4 | Check available apartments. |
| **`Garage`** | 5 | View garage status. |
| | 6 | Sell parking space. |
| | 7 | Check available parking spaces. |
| **`Storage`** | 8 | View storage status. |
| | 9 | Sell storage unit. |
| | 10 | Join storage units. |
| | 11 | Check available storage units. |
| **General** | 12 | Manage buildings. |
| | 13 | View building matrix. |
| | 14 | Check properties by DNI. |
| | 15 | Search apartments. |
| | 16 | Search parking spaces. |
| | 17 | Search storage units. |
| | 18 | View statistics. |
| | 0 | Exit. |

##### 3.3.2. Main loop implementation

* `do-while` loop that continues until the user selects 0.
* Display the menu and read the option using `readInteger()`.
* `switch` statement to execute the corresponding function.

#### 3.4. Auxiliary input methods

##### 3.4.1. `public static int readInteger()`

Safely read an integer:

1. Attempt to read an `int` using `scanner.nextInt()`.
2. Clear the buffer with `scanner.nextLine()`.
3. If an `InputMismatchException` occurs: show an error, clear the buffer, and return -1.

##### 3.4.2. `public static double readDouble()`

Similar to `readInteger()` but for decimal numbers.

##### 3.4.3. `public static String readString()`

Read a string and validate that it is not empty:

1. Read a line using `scanner.nextLine()`.
2. If empty (`trim().isEmpty()`): show an error and ask again.
3. Return the valid string.

#### 3.5. Main menu functions

##### 3.5.1. `public static void manageBuildings()`

Submenu with options to:

* List existing buildings.
* Create a new building (requesting all its parameters). Upon creation, the building automatically generates properties with random values.
* **Important note**: The `Building` constructor automatically calls the random generation methods, so it is not necessary to include a menu option to generate random data.

##### 3.5.2. `public static void sellReserveMenu()`

Submenu that allows:

* Selling an apartment.
* Reserving an apartment.

Process for selling:

1. Select building.
2. Select floor and door.
3. Ask for the buyer's DNI.
4. Select quality (`STANDARD`, `PLUS`, `DELUXE`).
5. Call `developer.sellApartment(...)`.

##### 3.5.3. `public static void joinApartments()`

1. Select building.
2. Select floor.
3. Select two contiguous doors.
4. Ask for DNI and quality.
5. Call `building.joinApartments(...)`.

##### 3.5.4. `public static void searchApartments()`

Submenu with options:

* Search by surface.
* Search by price.
* Search by rooms.
* Combined search (all criteria).

For each option, ask for the necessary parameters and call the corresponding `Developer` method.

##### 3.5.5. `public static void checkPropertiesByDni()`

1. Ask the user for a DNI.
2. Call `developer.listPropertiesByDni(dni)`.

### 4. Recommended implementation order

#### 4.1. `Developer`: Basics

* Declare attributes and constructor.
* Implement `addBuilding()` with dynamic expansion.
* Implement basic getters.
* Implement `listBuildings()`.

#### 4.2. `Developer`: Sales operations

* Implement `sellApartment()`.
* Implement `reserveApartment()`.
* Implement `sellParking()`.
* Implement `sellStorage()`.

#### 4.3. `Developer`: Statistics

* Implement `showGeneralStatistics()`.
* Implement DNI queries.

#### 4.4. `Developer`: Searches

* Implement apartment searches.
* Implement parking searches.
* Implement storage searches.

#### 4.5. `Main`: Basic structure

* Declare static attributes.
* Implement `main()`, `initialize()`.
* Implement auxiliary reading methods.
* Implement the main menu structure.

#### 4.6. `Main`: Building management

* Implement `manageBuildings()`.
* Implement visualization functions.

#### 4.7. `Main`: Sales operations

* Implement `sellReserveMenu()`.
* Implement `sellParking()`.
* Implement `sellStorage()`.

#### 4.8. `Main`: Complex operations

* Implement `joinApartments()`.
* Implement `joinStorage()`.

#### 4.9. `Main`: Searches and queries

* Implement search menus.
* Implement `checkPropertiesByDni()`.
* Implement view statistics.

### 5. Key concepts of this phase

#### 5.1. Advanced concepts

* **Dynamic arrays**: Management of collections that grow as needed.
* **Multi-level delegation**: `Main` -> `Developer` -> `Building` -> Property.
* **User interface**: Nested menu system with robust validation.

#### 5.2. Applied best practices

* **Separation of concerns**: Each class has a clear purpose.
* **Code reusability**: Methods from basic classes are used across all levels.
* **Exhaustive validation**: Index verification, null pointer checks, and input validation.
* **Informative messages**: Clear feedback to the user after each operation.

### 6. Conclusion and overview

This real estate developer management system demonstrates a well-organized layered architecture:

* **Basic data layer**: `Apartment`, `Parking`, `Storage`.
* **Aggregation layer**: `Building`.
* **Global management layer**: `Developer`.
* **Presentation layer**: `Main`.

### 6.1. Extensibility

The system is designed to allow for future extensions such as:

* New property types (commercial spaces, offices).
* Management of rental contracts.
* Reservation system with deadlines.
* Statistical reports.
* Graphical user interface.
