package SOFT2412_A2;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
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
    public void CustomerStrategyTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "2\n4\n2\n2\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        CustomerFlow.AnonymousUserFlow(false, null, "2011-01-01", sc);
        System.setIn(stdin);
        String expectedOutput  = "Popular items are:\n" +
                "- Mineral Water\n" +
                "Please input your card number(you have 3 more chance)\n" +
                "\n" +
                "Please Input a valid command\n" +
                "Please input your card number(you have 2 more chance)\n" +
                "\n" +
                "Please Input a valid command\n" +
                "Please input your card number(you have 1 more chance)\n" +
                "\n" +
                "Please Input a valid command\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void CustomerStrategyTest2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "1\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CustomerFlow.UserNameFlow(false, "2011-01-01");
        System.setIn(stdin);
        String expectedOutput  = "Please input your name\n" +
                "There is no such User Name!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void CustomerStrategyTest3() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "2\n5\n3";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        CustomerFlow.AnonymousUserFlow(true, null, "2011-01-01", sc);
        System.setIn(stdin);
        String expectedOutput  = "Popular items are:\n" +
                "- Mineral Water\n" +
                "Please Enter the currency e.g. 50, 20, etc.\n" +
                "\n" +
                "Enter the quantity of the chosen currency (2.00)\n" +
                "\n" +
                "Press 1 to enter more money\n" +
                "Press 2 to select category\n" +
                "Press 3 to cancel\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }
//
    @Test
    public void selectItemTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "3\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        VendingMachine vm = new VendingMachine();
        Scanner sc = new Scanner(System.in);
        CustomerStrategy.selectItem(vm, sc);
        System.setIn(stdin);
        String expectedOutput  = "Select Item Category\n" +
                "1. Drink\n" +
                "2. Chocolates\n" +
                "3. Chips\n" +
                "4. Candies\n" +
                "5. Cancel\n" +
                "\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void CustomerStrategyTest4() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "1\n1\n2\n1\n1\n100\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        CustomerFlow.AnonymousUserFlow(true, null, "2011-01-01", sc);
        System.setIn(stdin);
        String expectedOutput  = "Popular items are:\n" +
                "- Mineral Water\n" +
                "Please Enter the currency e.g. 50, 20, etc.\n" +
                "\n" +
                "Enter the quantity of the chosen currency (1.00)\n" +
                "\n" +
                "Press 1 to enter more money\n" +
                "Press 2 to select category\n" +
                "Press 3 to cancel\n" +
                "\n" +
                "Select Item Category\n" +
                "1. Drink\n" +
                "2. Chocolates\n" +
                "3. Chips\n" +
                "4. Candies\n" +
                "5. Cancel\n" +
                "\n" +
                "\n" +
                "1. Name: Mineral Water   Price: 1.00       Quantity: 0         \n" +
                "2. Name: Sprite          Price: 1.00       Quantity: 7         \n" +
                "3. Name: Coca cola       Price: 1.00       Quantity: 13        \n" +
                "4. Name: Pepsi           Price: 1.00       Quantity: 15        \n" +
                "5. Name: Orange Juice    Price: 1.00       Quantity: 0         \n" +
                "6: Exit\n" +
                "\n" +
                "\n" +
                "Enter the quantity you want to purchase of that item\n" +
                "To cancel the transaction input 100\n" +
                "\n" +
                "\n";
        assertEquals(expectedOutput, outContent.toString());
    }




    @Test
    public void selectQuantityTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "3\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        VendingMachine vm = new VendingMachine();
        ArrayList<Item> items = vm.returnCategory("Drink");
        Scanner sc = new Scanner(System.in);
        CustomerStrategy.getItem(items, sc);
        System.setIn(stdin);
        String expectedOutput  = "1. Name: Mineral Water   Price: 1.00       Quantity: 0         \n" +
                "2. Name: Sprite          Price: 1.00       Quantity: 7         \n" +
                "3. Name: Coca cola       Price: 1.00       Quantity: 13        \n" +
                "4. Name: Pepsi           Price: 1.00       Quantity: 15        \n" +
                "5. Name: Orange Juice    Price: 1.00       Quantity: 0         \n" +
                "6: Exit\n" +
                "\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }



    @Test
    public void getChangeTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "1\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        VendingMachine vm = new VendingMachine();
        Scanner sc = new Scanner(System.in);
        CashStrategy.getChange(100.0, 20, vm.getCoinsPouch(),sc);
        System.setIn(stdin);
        String expectedOutput  = "You paid 100.00\n" +
                "The changes are:\n" +
                "Currency: 50.00 Amount: 1\n" +
                "Currency: 20.00 Amount: 1\n" +
                "Currency: 10.00 Amount: 1\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void CustomerStrategyTest5() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "42689\n1\n1\n0\n1\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        CustomerFlow.AnonymousUserFlow(false, null,"2011-01-01", sc);
        System.setIn(stdin);
        String expectedOutput  = "Popular items are:\n" +
                "- Mineral Water\n" +
                "Please input your card number(you have 3 more chance)\n" +
                "\n" +
                "Card Confirmed\n" +
                "Select Item Category\n" +
                "1. Drink\n" +
                "2. Chocolates\n" +
                "3. Chips\n" +
                "4. Candies\n" +
                "5. Cancel\n" +
                "\n" +
                "\n" +
                "1. Name: Mineral Water   Price: 1.00       Quantity: 0         \n" +
                "2. Name: Sprite          Price: 1.00       Quantity: 7         \n" +
                "3. Name: Coca cola       Price: 1.00       Quantity: 13        \n" +
                "4. Name: Pepsi           Price: 1.00       Quantity: 15        \n" +
                "5. Name: Orange Juice    Price: 1.00       Quantity: 0         \n" +
                "6: Exit\n" +
                "\n" +
                "\n" +
                "Enter the quantity you want to purchase of that item\n" +
                "To cancel the transaction input 100\n" +
                "\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void CreateUserTest () {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "Newuser\nuser\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CustomerFlow.createUser();
        System.setIn(stdin);
        VendingMachine vm = new VendingMachine();
        User u = vm.getUserArrayList().get(2);
        User user = new User("Newuser", "user", null, 0,null );
        assertEquals(user.getUserName(), u.getUserName());
    }
}

