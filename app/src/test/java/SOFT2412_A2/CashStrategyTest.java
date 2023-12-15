package SOFT2412_A2;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;



public class CashStrategyTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void DisplayCurrentItemTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "3\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CashierStrategy sc = new CashierStrategy();
        sc.CashierStrategy();
        System.setIn(stdin);
        String expectedOutput  = "Please input your User and Pa\n" +
                "Please select the following\n" +
                "1. Deposit Coin\n" +
                "2. Withdraw Coin\n" +
                "3. Display Change\n" +
                "4. Display Record\n" +
                "5. Quit\n" +
                "Current available change:\n" +
                "Currency: $50    Amount: 10   \n" +
                "Currency: $20    Amount: 12   \n" +
                "Currency: $10    Amount: 9    \n" +
                "Currency: $5     Amount: 8    \n" +
                "Currency: $2     Amount: 10   \n" +
                "Currency: $1     Amount: 21   \n" +
                "Currency: 50cents Amount: 10   \n" +
                "Currency: 20cents Amount: 10   \n" +
                "Currency: 10cents Amount: 10   \n" +
                "Currency: 5cents Amount: 10   \n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void SummaryOfItemSoldTest () {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "4\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CashierStrategy sc = new CashierStrategy();
        sc.CashierStrategy();
        System.setIn(stdin);
        String expectedOutput  = "Please input your User and Pa\n" +
                "Please select the following\n" +
                "1. Deposit Coin\n" +
                "2. Withdraw Coin\n" +
                "3. Display Change\n" +
                "4. Display Record\n" +
                "5. Quit\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}
