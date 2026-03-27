package playerRoles;

import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Represents a forward player in our football simulation.
 * <p>
 * This class demonstrates methods that can trigger both checked 
 * and unchecked exceptions.
 */
public class Forward {

    /**
     * Simulates loading the forward's tactical profile from an external text file.
     * <p>
     * Because the file might not exist on the hard drive, Java considers this 
     * a high-risk external operation. Therefore, it throws a checked exception.
     *
     * @throws FileNotFoundException if the external file is missing (ehecked).
     */
    public void loadTactics() throws FileNotFoundException {
        // This line natively triggers a checked exception if the file doesn't exist.
        FileReader fileReader = new FileReader("tactics.txt");
    }

    /**
     * Calculates the shooting accuracy of the forward.
     * <p>
     * This method performs a division. Passing a zero is a programming logic error. 
     * Java considers this an unchecked exception, so the compiler won't force
     * the caller to handle it.
     *
     * @param goals the number of goals scored
     * @param missedShots the number of missed shots
     * @return the calculated accuracy ratio
     * @throws ArithmeticException if {@code missedShots} is zero (unchecked).
     */
    public int calculateAccuracy(int goals, int missedShots) throws ArithmeticException {
        // This line natively triggers an unchecked exception if missedShots is 0.
        return goals / missedShots;
    }
}
