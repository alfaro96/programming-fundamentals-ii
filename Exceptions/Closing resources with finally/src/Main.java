import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Demonstrates how to use a {@code finally} block.
 * <p>
 * This example tries to open a file using {@link FileReader}. If the file is not found,
 * it catches the {@link FileNotFoundException}. Regardless of whether an exception occurs,
 * the {@code finally} block runs and attempts to close the file (if it was opened).
 * </p>
 * <p>
 * The {@code finally} block is useful for executing cleanup code that must run under all circumstances.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 * @see FileNotFoundException
 * @see IOException
 */
public class Main {

    /**
     * The main method demonstrates exception handling with a {@code finally} block.
     * It attempts to open a file and always executes the cleanup logic, whether the file
     * is found or not.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Declare the file reader outside the try block for later use
        FileReader reader = null;

        try {
            // Try to open the file, which may throw an exception
            reader = new FileReader("file.txt");
            System.out.println("File opened.");
        } catch (FileNotFoundException e) {
            // This block runs if the file does not exist or can't be found
            System.out.println("File not found.");
        } finally {
            // The finally block always runs, no matter what
            System.out.println("This always runs: cleanup time.");

            // Check if the file was successfully opened before trying to close it
            if (reader != null) {
                try {
                    // Close the file to free up system resources
                    reader.close();
                    System.out.println("File closed.");
                } catch (IOException e) {
                    // This block runs if an error occurs while closing the file
                    System.out.println("Failed to close the file.");
                }
            }
        }
    }
}
