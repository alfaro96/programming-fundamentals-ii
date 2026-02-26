import associations.CommerceAssociation;
import commerces.CarDealership;
import commerces.Commerce;
import commerces.Employee;
import commerces.Restaurant;

/**
 * Entry point for the lab assignment.
 *
 * <p>Demonstrates the full hierarchy of {@link Commerce} subtypes by:
 * <ol>
 *   <li>Creating {@link Restaurant} and {@link CarDealership} objects.</li>
 *   <li>Hiring employees into each commerce.</li>
 *   <li>Building a {@link CommerceAssociation} and adding all commerces.</li>
 *   <li>Printing the employees of all dealerships in the association.</li>
 *   <li>Cloning the association.</li>
 *   <li>Replacing one of the restaurants with a new car dealership.</li>
 * </ol>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Commerce
 * @see Restaurant
 * @see CarDealership
 * @see CommerceAssociation
 * @see Employee
 */
public class Main {

    /**
     * Main method — execution entry point.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // 1. Object creation: two Restaurants and two CarDealerships

        Restaurant restaurant1 = new Restaurant(
                "La Buena Mesa", "Calle Mayor 1, Madrid", "B12345678", 15, 60);
        Restaurant restaurant2 = new Restaurant(
                "El Buen Sabor", "Avenida Sol 22, Sevilla", "B87654321", 10, 40);

        CarDealership dealership1 = new CarDealership(
                "AutoCenter Norte", "Calle Norte 5, Bilbao", "A11111111");
        CarDealership dealership2 = new CarDealership(
                "AutoCenter Sur", "Calle Sur 9, Valencia", "A22222222");

        // 2. Employee management

        Employee emp1 = new Employee(
                "12345678A", "Ana García",    "C/ Luna 3",
                "600111222", "ana@buenamesaa.com",  "2020-01-10", "Camarera", 1400.0);
        Employee emp2 = new Employee(
                "23456789B", "Luis Pérez",    "C/ Sol 7",
                "600333444", "luis@buenamesa.com",  "2021-03-15", "Cocinero",  1600.0);
        restaurant1.hireEmployee(emp1);
        restaurant1.hireEmployee(emp2);

        Employee emp3 = new Employee(
                "34567890C", "Marta López",  "C/ Mar 5",
                "600555666", "marta@sabor.com",     "2019-06-01", "Camarera", 1350.0);
        Employee emp4 = new Employee(
                "45678901D", "Carlos Ruiz",  "C/ Río 2",
                "600777888", "carlos@sabor.com",    "2022-09-20", "Jefe cocina", 1900.0);
        restaurant2.hireEmployee(emp3);
        restaurant2.hireEmployee(emp4);

        Employee emp5 = new Employee(
                "56789012E", "Pedro Sanz",   "C/ Alta 11",
                "601100200", "pedro@autocnorte.com","2018-02-28", "Vendedor",  1800.0);
        Employee emp6 = new Employee(
                "67890123F", "Sofía Mora",   "C/ Baja 4",
                "601300400", "sofia@autocnorte.com","2020-07-14", "Mecánica",  1700.0);
        Employee emp7 = new Employee(
                "78901234G", "Javier Gil",   "C/ Centro 8",
                "601500600", "javier@autocnorte.com","2021-11-05","Gerente",   2500.0);
        dealership1.hireEmployee(emp5);
        dealership1.hireEmployee(emp6);
        dealership1.hireEmployee(emp7);

        Employee emp8 = new Employee(
                "89012345H", "Elena Castro", "C/ Este 6",
                "601700800", "elena@autocsur.com",  "2017-05-22", "Vendedora", 1850.0);
        Employee emp9 = new Employee(
                "90123456I", "Miguel Torres","C/ Oeste 3",
                "601900000", "miguel@autocsur.com", "2019-08-30", "Mecánico",  1650.0);
        Employee emp10 = new Employee(
                "01234567J", "Laura Vega",   "C/ Norte 1",
                "602100200", "laura@autocsur.com",  "2023-01-15", "Recepcionista",1300.0);
        dealership2.hireEmployee(emp8);
        dealership2.hireEmployee(emp9);
        dealership2.hireEmployee(emp10);

        // 3. Association initialization

        CommerceAssociation association =
                new CommerceAssociation(10, "Plaza España 1, Madrid", "Roberto Navarro");

        // 4. Add all four commerces to the association

        association.addCommerce(restaurant1);
        association.addCommerce(restaurant2);
        association.addCommerce(dealership1);
        association.addCommerce(dealership2);

        System.out.println("Association created: " + association);
        System.out.println();

        // 5. Print dealership employees

        System.out.println("--- Dealership employees ---");
        association.printDealershipEmployees();
        System.out.println();

        // 6. Clone the association

        CommerceAssociation associationCopy = association.clone();
        System.out.println("Association cloned successfully.");
        System.out.println("Original equals copy: " + association.equals(associationCopy));
        System.out.println("Same object: " + (association == associationCopy));
        System.out.println();

        // 7. Replace one restaurant with a new CarDealership

        CarDealership dealership3 = new CarDealership(
                "AutoCenter Este", "Calle Este 15, Zaragoza", "A33333333");

        association.replaceRestaurant(restaurant1, dealership3);
        System.out.println("After replacing restaurant1 with dealership3:");
        System.out.println(association);
    }
}
