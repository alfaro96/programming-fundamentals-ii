import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;
import housing.*;

/**
 * Demonstrates proper creation of housing objects with validated inputs,
 * handling both {@link InvalidPriceException} and {@link InvalidSquareMetersException}
 * where they arise. Each object creation is wrapped in its own try-catch block so that
 * a single invalid property does not prevent the rest from being registered.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Creates a set of housing properties, registers them in a {@link RealEstate}
     * catalogue, and prints summary information.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        RealEstate realEstate = new RealEstate(10);

        // Valid objects

        try {
            Apartment apartment = new Apartment(150_000, true);
            realEstate.addHousing(apartment);
            System.out.println("Added: " + apartment);
        } catch (InvalidPriceException e) {
            System.err.println("Could not create apartment: " + e.getMessage());
        }

        try {
            SingleFamily singleFamily = new SingleFamily(200_000, 2, 50);
            realEstate.addHousing(singleFamily);
            System.out.println("Added: " + singleFamily);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.err.println("Could not create single-family home: " + e.getMessage());
        }

        try {
            Chalet chalet = new Chalet(350_000, 3, 80, 200);
            realEstate.addHousing(chalet);
            System.out.println("Added: " + chalet);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.err.println("Could not create chalet: " + e.getMessage());
        }

        try {
            Chalet innerChalet = new Chalet(300_000, 2, 60, 150);
            CountryHouse countryHouse = new CountryHouse(500_000, 5_000, innerChalet);
            realEstate.addHousing(countryHouse);
            System.out.println("Added: " + countryHouse);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.err.println("Could not create country house: " + e.getMessage());
        }

        // Invalid objects: these should trigger exceptions

        System.out.println("\nTesting invalid inputs");

        try {
            Apartment invalidApartment = new Apartment(-500, false);
            realEstate.addHousing(invalidApartment);
        } catch (InvalidPriceException e) {
            System.err.println("[InvalidPriceException] " + e.getMessage());
        }

        try {
            SingleFamily invalidSingleFamily = new SingleFamily(200_000, 2, -30);
            realEstate.addHousing(invalidSingleFamily);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.err.println("[" + e.getClass().getSimpleName() + "] " + e.getMessage());
        }

        try {
            Chalet invalidChalet = new Chalet(300_000, 1, 50, -100);
            realEstate.addHousing(invalidChalet);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.err.println("[" + e.getClass().getSimpleName() + "] " + e.getMessage());
        }

        try {
            Chalet validChalet = new Chalet(300_000, 2, 60, 150);
            CountryHouse invalidCountryHouse = new CountryHouse(400_000, -200, validChalet);
            realEstate.addHousing(invalidCountryHouse);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.err.println("[" + e.getClass().getSimpleName() + "] " + e.getMessage());
        }

        System.out.println("\nCatalogue summary");
        System.out.println("Non-chalet single-family homes: " + realEstate.countNonChaletSingleFamily());

        Housing highestTax = realEstate.getHighestTaxHousing();
        if (highestTax != null) {
            System.out.println("Property with highest tax: " + highestTax);
        }
    }
}
