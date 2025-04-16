import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void testMainOutput() {
        // Redirect standard output to capture it
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Run the main method
        Main.main(new String[]{});

        // Restore standard output
        System.setOut(originalOut);

        String expectedOutputRegex = "Vehicle Specs:\\s*" +
                                     "Model: Hatch \\| Daily Rate: R\\$60\\.00\\s*" +
                                     "---------\\s*" +
                                     "Rental Cost Projections:\\s*" +
                                     "Standard Hire \\(5 days\\): R\\$300,00\\s*" +
                                     "Extended Hire \\(10 days\\): R\\$540,00\\s*" +
                                     "Late Return Fee \\(3 days\\): R\\$3,60\\s*";

        assertTrue(outContent.toString().matches(expectedOutputRegex),
                   "Actual output:\n" + outContent.toString());
    }
}
