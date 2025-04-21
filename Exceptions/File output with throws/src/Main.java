import java.io.FileWriter;
import java.io.IOException;

/**
 * Demonstrates how to use the {@code throws} keyword to delegate
 * the responsibility of handling a checked exception such as {@link IOException}.
 * <p>
 * This example writes a simple message to a text file using {@link FileWriter}.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 * @see IOException
 */
public class Main {

    /**
     * Saves a welcome message to a file named {@code message.txt}.
     * The method does not handle the exception directly, but declares it with {@code throws}.
     *
     * @throws IOException if the file cannot be created or written to
     */
    public static void writeMessageToFile() throws IOException {
        FileWriter writer = new FileWriter("message.txt");
        writer.write("Hello from Java!");
        writer.close();
    }

    /**
     * The main method calls {@link #writeMessageToFile} and handles any {@link IOException}
     * using a {@code try} and {@code catch} block.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            writeMessageToFile();
            System.out.println("Message saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to write message: " + e.getMessage());
        }
    }
}
