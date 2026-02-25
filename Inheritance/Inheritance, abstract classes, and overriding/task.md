# Inheritance, abstract classes, and overriding

## 1. Goals

The **main focus** of this laboratory assignment is to **transition** from **class diagrams** to **functional classes** using abstraction and inheritance. You will **develop** the skills necessary to define **hierarchical object structures**, **implement specific behaviors**, and **manage arrays of objects** using the principles of object-oriented programming.

By the **end** of this **session**, **you will** be able to:

* **Translate class diagrams** into **classes** with appropriate attributes, visibilities, and methods.
* **Use inheritance and abstract classes** to handle shared concepts between related families.
* **Override methods** to re-implement parent behaviors in child classes.
* **Manage object arrays**, including dynamic resizing (doubling size) and array compaction.

---

## 2. Tasks

### 2.1. Implementation

#### 2.1.1. `Commerce`

1. **Attributes**:
    * `name`, `address`, and `cif` of type `String`.
    * `dailySales`: A **matrix** (two-dimensional array) of size 12×31 where each position represents the **total sale price** for a specific month and day.
    * `stock`: An **array** where each position specifies the **current stock** for a specific product.
    * `employees`: An **array** of `Employee` objects.

2. **Constructors**: **Implement** the necessary constructors. **Remember** that **no constructors** should directly take the matrix of daily sales or the list of employees. These must be **initialized** with their **expected sizes** and **default values**.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Sales management methods**:
    * `totalSales()`: **Calculates** and **returns** the **total sum** of sales contained within the `dailySales` matrix.
    * `salesInMonth()`: **Given** a specific **month** as an argument, **returns** the **sum of sales** for that specific month.
    * `topSalesMonth()`: **Returns** the **month** (index) with the **highest volume of sales**. You can use `salesInMonth()` to compute the values for each month.
    * `updateSales()`: **Overload** this method to provide two different implementations:
        * **Given** a **month**, a **day**, and a **sale amount**, **updates** (adds to) the sales amount for the specified date.
        * **Given** **only** a **sale amount**, **updates** the sales amount for the **current month and day**. **Note**: Use the `java.time` libraries to retrieve the current date.

5. **Employee management methods**:
    * `hireEmployee()`: **Given** a new `Employee`, **adds** it to the array. **If** the array is **full**, you must **double its size** before adding the new employee.
    * `fireEmployee()`: **Given** the **DNI** of an employee, **finds** it in the array, **removes** it, and **returns** the object. The array **must be compacted** after removal (i.e., there should be no empty `null` positions between existing elements).

6. **Standard overrides**: **Implement** the specific printing methods (`toStringMonthlySales()` and `toStringDailySales()`), and **override** the standard `toString()`, `equals()`, and `clone()` methods according to the standard rules of object-oriented programming.

---

#### 2.1.2. `CarDealership`

1. **Attributes**:
    * `vehiclesOnSale`: An **array** of `SaleVehicle` objects.
    * `vehiclesForRepair`: An **array** of `RepairVehicle` objects.

2. **Constructors**: **Implement** the required constructors. The vehicle arrays **must be initialized** as empty with a default or specified maximum size. **Do not** pass the arrays directly as arguments.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Repair management methods**:
    * `addVehicleForRepair()`: **Given** a `RepairVehicle`, **adds** it to the repair list. The vehicles **must be ordered by their priority**, meaning that a vehicle with priority `1` (maximum priority) should be stored before vehicles with lower priorities.
    * `repairVehicle()`: **Given** a `numberPlate`, **finds** the specific car and **sets** its `repaired` status to `true`.
    * `pickupVehicle()`: **Given** a `numberPlate`, **checks** the array of vehicles to repair. **If** the vehicle exists and is already repaired, **removes** it from the array and **returns** it. The array **must be compacted** after removal.

5. **Sales management methods**:
    * `addSale()`: **Adds** a `SaleVehicle` to the array of vehicles on sale.
    * `sellVehicle()`: **Given** a car `identifier`, **checks** the array of vehicles for sale. **If** found, **removes** it, **increases** the daily sales of today with its price, and **returns** it. The array **must be compacted** after removal.

6. **Standard overrides**: **Implement** the specific printing methods (`toStringVehiclesOnSale()` and `toStringVehiclesForRepair()`), along with the standard `toString()`, `clone()`, and `equals()`. **Two car dealerships** are considered equivalent if they share a memory position **or** if they have exactly the same vehicles in repair and for sale.

---

#### 2.1.3. `Restaurant`

1. **Attributes**:
    * `dailyMenus`: An **array** of `String` objects representing the menu for each day.
    * `numTables`: An `int` representing the number of tables.
    * `capacity`: An `int` representing the total customer capacity.

2. **Constructors**: **Implement** the necessary constructors. **Remember** that **no constructors** should directly take a list of menus. The list of menus **must be initialized** with default values.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Menu management methods**:
    * `setDailyMenu()`: **Given** a menu (as a `String`) and a day of the week, **stores** the specified menu in the `dailyMenus` array.
    * `getDailyMenu()`: **Given** a day of the week, **returns** the appropriate menu as a `String`.

5. **Standard overrides**: **Override** the standard `toString()`, `equals()`, and `clone()` methods according to the standard rules of object-oriented programming. **Two restaurants** are considered equivalent if they share a memory position **or** if they have exactly the same daily menus.

---

#### 2.1.4. `Employee`

1. **Attributes**:
    * `DNI`, `name`, `address`, `phoneNumber`, `email`, `startingDate`, and `position`, all of type `String`.
    * `salary` of type `double`.

2. **Constructors**: **Implement** the necessary constructors to initialize the employee details.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Standard overrides**: **Override** the `toString()`, `equals()`, and `clone()` methods according to the standard rules of object-oriented programming.

---

#### 2.1.5. `Vehicle`

1. **Attributes**: **Define** the following **private** attributes:
    * `brand` and `model`, both of type `String`.

2. **Constructors**: **Implement** the required constructors to initialize the vehicle's base properties.

3. **Getters and Setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Standard overrides**: **Override** the `toString()`, `equals()`, and `clone()` methods. As an abstract class, these methods will be inherited and adapted by its child classes.

---

#### 2.1.6. `RepairVehicle`

1. **Attributes**:
    * `damage` and `numberPlate`, both of type `String`.
    * `repaired` of type `boolean`.
    * `priority` of type `int`. The value **should be contained** within the range `1` to `3`, where `1` is the **maximum priority** and `3` is the lowest priority.

2. **Constructors**: **Implement** the necessary constructors, ensuring that a call to the superclass's constructor is made to initialize inherited attributes.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Standard overrides**: **Override** the `toString()`, `equals()`, and `clone()` methods according to the standard rules of object-oriented programming, ensuring they include both the superclass's and the subclass's specific attributes.

---

#### 2.1.7. `SaleVehicle`

1. **Attributes**:
    * `price` of type `double`.
    * `discount` of type `int`.
    * `identifier` of type `String`.

2. **Constructors**: **Implement** the necessary constructors, making sure to invoke the superclass constructor appropriately.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Standard overrides**: **Override** the `toString()`, `equals()`, and `clone()` methods according to the standard rules of object-oriented programming, combining parent properties with these new specific fields.

---

#### 2.1.8. `CommerceAssociation`

1. **Attributes**:
    * `commerces`: An **array** of `Commerce` objects.
    * `address` and `president`, both of type `String`.

2. **Constructors**:
    * A **default constructor** that creates an array of commerces with a size of `20`.
    * A **parameterized constructor** that takes, as arguments, the **size** of the array of commerces and the values for all other attributes to initialize them.

3. **Getters and setters**: **Implement** the necessary getter and setter methods. **Generate** only the ones strictly required to access or modify the internal state of the class appropriately.

4. **Association management methods**:
    * `addCommerce()`: **Adds** a commerce of any type to the association. **If** the commerce is already in the array or if the array is full, it **will not be added**.
    * `replaceRestaurant()`: **Finds** a previously created restaurant within the association. **If** it is found, **replaces** it with a given car dealership.
    * `printDealershipEmployees()`: A method that **shows on screen** the employees of those commerces that are car dealerships.

5. **Standard overrides**: **Override** the `toString()`, `equals()`, and `clone()` methods according to the standard rules of object-oriented programming.

#### 2.1.9. `Main`

1. **Object creation**: **Create** two `Restaurant` objects and two `CarDealership` objects.
2. **Employee management**: **Add** two employees to the restaurants and three employees to the car dealerships.
3. **Association initialization**: **Create** an object of type `CommerceAssociation`.
4. **Adding commerces**: **Add** the two restaurants and the two car dealerships to the association.
5. **Print dealership employees**: **Show on screen** the data of the employees working at the car dealerships within the association by calling the appropriate method of the `CommerceAssociation` class.
6. **Object cloning**: **Create** a copy of the `CommerceAssociation` object.
7. **Commerce replacement**: **Replace** one of the restaurants in the association with a **new** car dealership.
