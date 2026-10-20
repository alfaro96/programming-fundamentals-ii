import exceptions.InvalidPriceException;
import exceptions.InvalidSquareMetersException;
import housing.Apartment;
import housing.Chalet;
import housing.CountryHouse;
import housing.Housing;
import housing.RealEstate;
import housing.SingleFamily;

/**
 * Creates housing properties, some of them with invalid values, and shows how the
 * exceptions of their constructors are handled.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Fills a {@link RealEstate} with three valid properties, prints its summary, and then
     * tries to create two invalid ones, printing the message of each exception.
     *
     * @param args the command line arguments, not used.
     */
    public static void main(String[] args) {
        RealEstate agency = new RealEstate(3);

        try {
            Chalet luxuryChalet = new Chalet(200000.0, 2, 50.0, 300.0);
            SingleFamily standardHome = new SingleFamily(150000.0, 1, 40.0);
            CountryHouse hugeEstate = new CountryHouse(300000.0, 1000.0, luxuryChalet);

            agency.addHousing(luxuryChalet);
            agency.addHousing(standardHome);
            agency.addHousing(hugeEstate);
        } catch (InvalidPriceException | InvalidSquareMetersException e) {
            System.out.println("Could not create a housing: " + e.getMessage());
        }

        System.out.println("--- Real Estate summary ---");
        System.out.println("SingleFamily homes (excluding chalets): " + agency.countNonChaletSingleFamily());

        Housing highestTax = agency.getHighestTaxHousing();
        System.out.println("\nHousing with the highest tax:");
        if (highestTax != null) {
            System.out.println(highestTax);
        }

        System.out.println("\n--- Invalid values ---");

        try {
            Apartment apartment = new Apartment(-500.0, false);
            System.out.println(apartment);
        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        }

        try {
            Chalet chalet = new Chalet(250000.0, 2, 40.0, -100.0);
            System.out.println(chalet);
        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        } catch (InvalidSquareMetersException e) {
            System.out.println(e.getMessage());
        }
    }
}
