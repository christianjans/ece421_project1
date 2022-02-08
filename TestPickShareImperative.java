import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import static org.junit.Assert.*;

public class TestPickShareImperative {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    /**
     * For this test, we are assuming that the price for each symbol will remain relatively constant. There is room for
     * the prices to change by over 50% in either direction and this test will still pass, so the assumption should be
     * ok.
     */
    public void testImperative() {
        // Expected result:
        // "symbol: MSFT price: <CURRENT_PRICE>"

        List<String> shouldContainSymbols = List.of(
                "MSFT"      // ~$300
        );

        List<String> shouldNotContainSymbols = Arrays.asList(
                "JMIA",     // ~$9
                "CHPT",     // ~$13
                "AAL",      // ~$16
                "MO",       // ~$50
                "GPS",      // ~$17
                "GOOG",     // ~$2800
                "AMZN"      // ~$3100
        );

        // Call imperative version of findHighPriced method
        PickShareImperative.findHighPriced();

        // Test that only MSFT is printed
        shouldContainSymbols.forEach((symbol) -> assertTrue(outContent.toString().contains(symbol)));

        // Test that all other symbols are not printed
        shouldNotContainSymbols.forEach((symbol) -> assertFalse(outContent.toString().contains(symbol)));
    }

    @AfterAll
    public void finish() {
        // Wait one minute after the tests to refresh the API.
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
