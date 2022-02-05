import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestPickShareFunctional {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // @BeforeAll
    // public void setUpStreams() {
    //     // System.setOut(new PrintStream(outContent));
    //     // System.setErr(new PrintStream(errContent));
    // }

    @Test
    public void out() {
        PickShareFunctional.findHighPriced(Shares.symbols.stream());
        // System.out.print("hello");
        // Assertions.assertEquals("hello", outContent.toString());
    }

    // @Test
    // public void err() {
    //     System.err.print("hello again");
    //     assertEquals("hello again", errContent.toString());
    // }

    // @AfterAll
    // public void restoreStreams() {
    //     // System.setOut(originalOut);
    //     // System.setErr(originalErr);
    // }
}
