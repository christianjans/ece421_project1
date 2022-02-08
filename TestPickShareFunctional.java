import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

public class TestPickShareFunctional {
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
    public void testFunctional() {
        //What should print
        //"symbol: MSFT price: <CURRENT_PRICE>"
        //the correct answer will always be MSFT becuase every other stock is obviously
        //higher than $500 or obviously lower than MSFT. This way, even with daily price
        //fluctuations, MSFT will always be the correct answer. 

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

        PickShareFunctional.findHighPriced(symbols.stream());

        //make sure msft is printed
        boolean isFound =  outContent.toString().indexOf("MSFT") !=-1? true: false;
        assertEquals(isFound, true);

        //make sure everything else is not printed
        isFound =  outContent.toString().indexOf("JMIA") !=-1? true: false;
        assertEquals(isFound, false);
        isFound =  outContent.toString().indexOf("CHPT") !=-1? true: false;
        assertEquals(isFound, false);
        isFound =  outContent.toString().indexOf("AAL") !=-1? true: false;
        assertEquals(isFound, false);
        isFound =  outContent.toString().indexOf("MO") !=-1? true: false;
        assertEquals(isFound, false);
        isFound =  outContent.toString().indexOf("GPS") !=-1? true: false;
        assertEquals(isFound, false);
        isFound =  outContent.toString().indexOf("GOOG") !=-1? true: false;
        assertEquals(isFound, false);
        isFound =  outContent.toString().indexOf("AMZN") !=-1? true: false;
        assertEquals(isFound, false);
    }

    @Test
    public void testEmptyList() {
        //What should print
        //"Could not find highest price."
        
        List<String> symbols = Arrays.asList(); //empty list

        PickShareFunctional.findHighPriced(symbols.stream());

        //make sure msft is printed
        boolean isFound =  outContent.toString().indexOf("Could not find highest price.") !=-1? true: false;
        assertEquals(isFound, true);
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
