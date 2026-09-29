import housing.Chalet;
import housing.CountryHouse;
import housing.RealEstate;
import housing.SingleFamily;
import housing.Housing;

/**
 * Main application class to demonstrate the Real Estate Management system.
 * Shows object instantiation, array insertion, and polymorphic method calls.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Create a RealEstate instance
        RealEstate agency = new RealEstate(3);

        // 2. Create the three required housing objects
        Chalet luxuryChalet = new Chalet(200000.0, 2, 50.0, 300.0);
        SingleFamily standardHome = new SingleFamily(150000.0, 1, 40.0);
        CountryHouse hugeEstate = new CountryHouse(300000.0, 1000.0, luxuryChalet);

        // Add them to the agency
        agency.addHousing(luxuryChalet);
        agency.addHousing(standardHome);
        agency.addHousing(hugeEstate);

        System.out.println("--- Real Estate summary ---");

        // 3. Display how many SingleFamily homes are not Chalet
        int nonChalets = agency.countNonChaletSingleFamily();
        System.out.println("SingleFamily homes (excluding chalets): " + nonChalets);

        // 4. Display the housing object with the highest tax
        Housing highestTax = agency.getHighestTaxHousing();
        System.out.println("\nHousing with the highest tax:");
        if (highestTax != null) {
            System.out.println(highestTax.toString());
        }
    }
}