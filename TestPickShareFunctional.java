import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPickShareFunctional {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    List<String> symbols = Arrays.asList(
        "JMIA", //currently $9
        "CHPT", //currently $13
        "AAL", //currently $16
        "MO", //currently $50
        "GPS", //currently $17
        "GOOG", //currently $2800
        "MSFT", //currently $300
        "AMZN" //currently $3100
        );

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testFunctional() {
        //What should print
        //symbol: MSFT price: <CURRENT_PRICE>
        //the correct answer will always be MSFT becuase every other stock is obviously
        //higher or obviously lower

        PickShareFunctional.findHighPriced(symbols.stream());

        boolean isFound =  outContent.toString().indexOf("MSFT") !=-1? true: false;
        assertEquals(isFound, true);
    }
}
