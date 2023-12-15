package SOFT2412_A2;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SellerTest {
    @Test
    public void DisplayCurrentItemTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "2\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        SellerStrategy sc = new SellerStrategy();
        sc.sellStrat();
        System.setIn(stdin);
        String expectedOutput  = "Please select the following\n" +
                "1. Modify Item\n" +
                "2. Display Current Item\n" +
                "3. Summary of Items Sold\n" +
                "4. Quit\n" +
                "Chips:\n" +
                "1. Name: Smith           Price: 1.00       Quantity: 13        \n" +
                "2. Name: Pringles        Price: 1.00       Quantity: 15        \n" +
                "3. Name: Kettle          Price: 1.00       Quantity: 15        \n" +
                "4. Name: Thins           Price: 1.00       Quantity: 15        \n" +
                "Candies:\n" +
                "1. Name: Mentos          Price: 1.00       Quantity: 14        \n" +
                "2. Name: Sour Patch      Price: 1.00       Quantity: 15        \n" +
                "3. Name: Skittles        Price: 1.00       Quantity: 15        \n" +
                "Drink:\n" +
                "1. Name: Mineral Water   Price: 1.00       Quantity: 0         \n" +
                "2. Name: Sprite          Price: 1.00       Quantity: 7         \n" +
                "3. Name: Coca cola       Price: 1.00       Quantity: 13        \n" +
                "4. Name: Pepsi           Price: 1.00       Quantity: 15        \n" +
                "5. Name: Orange Juice    Price: 1.00       Quantity: 0         \n" +
                "Chocolates:\n" +
                "1. Name: Mars            Price: 1.00       Quantity: 0         \n" +
                "2. Name: M&M             Price: 1.00       Quantity: 13        \n" +
                "3. Name: Bounty          Price: 1.00       Quantity: 15        \n" +
                "4. Name: Snickers        Price: 1.00       Quantity: 15        \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void SummaryOfItemSoldTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "2\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        SellerStrategy sc = new SellerStrategy();
        sc.sellStrat();
        System.setIn(stdin);
        String expectedOutput  = "Please select the following\n" +
                "1. Modify Item\n" +
                "2. Display Current Item\n" +
                "3. Summary of Items Sold\n" +
                "4. Quit\n" +
                "Chips:\n" +
                "1. Name: Smith           Price: 1.00       Quantity: 13        \n" +
                "2. Name: Pringles        Price: 1.00       Quantity: 15        \n" +
                "3. Name: Kettle          Price: 1.00       Quantity: 15        \n" +
                "4. Name: Thins           Price: 1.00       Quantity: 15        \n" +
                "Candies:\n" +
                "1. Name: Mentos          Price: 1.00       Quantity: 14        \n" +
                "2. Name: Sour Patch      Price: 1.00       Quantity: 15        \n" +
                "3. Name: Skittles        Price: 1.00       Quantity: 15        \n" +
                "Drink:\n" +
                "1. Name: Mineral Water   Price: 1.00       Quantity: 0         \n" +
                "2. Name: Sprite          Price: 1.00       Quantity: 7         \n" +
                "3. Name: Coca cola       Price: 1.00       Quantity: 13        \n" +
                "4. Name: Pepsi           Price: 1.00       Quantity: 15        \n" +
                "5. Name: Orange Juice    Price: 1.00       Quantity: 0         \n" +
                "Chocolates:\n" +
                "1. Name: Mars            Price: 1.00       Quantity: 0         \n" +
                "2. Name: M&M             Price: 1.00       Quantity: 13        \n" +
                "3. Name: Bounty          Price: 1.00       Quantity: 15        \n" +
                "4. Name: Snickers        Price: 1.00       Quantity: 15        \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void existingItemCodeTest() {
        // 21 is the item code for Smiths chips
        SellerStrategy seller = new SellerStrategy();
        assertTrue(seller.existingItemCode(21));
        assertFalse(seller.existingItemCode(12345));
    }

    @Test
    public void existingItemNameTest() {
        // Smiths is the item code for Smiths chips
        SellerStrategy seller = new SellerStrategy();
        seller.sellerSummary();
        assertTrue(seller.existingItemName("Smith"));
        assertFalse(seller.existingItemName("abc"));
    }

}
