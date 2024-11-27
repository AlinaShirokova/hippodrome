import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;

public class MainTest {

    @Test
    //@Disabled("Disabled for quick testing. Enable to test main method performance.")
    @Timeout(22) // Check that the main method executes in less than 22 seconds
    @DisplayName("Should complete main method execution within 22 seconds")
    void testMainMethodPerformance() throws Exception {
        long startTime = System.currentTimeMillis();

        // Run the main method
        Main.main(new String[]{});

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Check that execution completed within 22 seconds
        assertTrue(duration <= 22000, "Main method took too long to execute.");
    }
}