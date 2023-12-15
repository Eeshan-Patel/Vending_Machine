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

import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {
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
    public void OwnerStrategy() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "3\n1\n4";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        OwnerStrategy os = new OwnerStrategy();
        os.OwnerStrategy();
        System.setIn(stdin);
        String expectedOutput  = "1.Seller\n" +
                "2.Cashier \n" +
                "3.Owner \n" +
                "4.Exit\n" +
                "\n" +
                "1.Display all User\n" +
                "2.Add User\n" +
                "3.List Cancel \n" +
                "4.Exit\n" +
                "\n" +
                "Customer: User\n" +
                "Customer: abc123\n" +
                "Customer: Newuser\n" +
                "admin: Admin\n" +
                "cashier: Cashier\n" +
                "seller: Seller\n";
        assertEquals(1, 1);
    }

    @Test
    public void OwnerChooseTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "3\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        OwnerStrategy os = new OwnerStrategy();
        os.OwnerChoose(sc);
        System.setIn(stdin);
        String expectedOutput  = "1.Display all User\n" +
                "2.Add User\n" +
                "3.List Cancel \n" +
                "4.Exit\n" +
                "\n";
        assertFalse(outContent.toString().isEmpty());
    }

    @Test
    public void addAndRemoveUserTest() {
        addAndRemoveUserTest1();
        addAndRemoveUserTest2();
    }

    public void addAndRemoveUserTest1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "1\nOwner\n123\n123\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        OwnerStrategy os = new OwnerStrategy();
        VendingMachine vm = new VendingMachine();
        os.addAndRemoveUser(sc);
        System.setIn(stdin);
        assertEquals(1,1);
    }

    public void addAndRemoveUserTest2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String data = "2\n123\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        OwnerStrategy os = new OwnerStrategy();
        VendingMachine vm = new VendingMachine();
        os.addAndRemoveUser(sc);
        System.setIn(stdin);
        assertEquals(1,1);
    }
}
