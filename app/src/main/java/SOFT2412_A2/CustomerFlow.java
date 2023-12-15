package SOFT2412_A2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Console;

public class  CustomerFlow {


    public static void AnonymousUserFlow(boolean isCash, User user, String date, Scanner sc) {
        String records = "";
        //Initialise
        VendingMachine vm = new VendingMachine();
        Item item = null;
        int itemQuantity = 0;
        ArrayList<Coin> coins = null;
        double cash = 0;

        //Display pop_item for customer
        System.out.println("Popular items are:");
        if (user == null) {
            for (String i : vm.getPopList()) {
                System.out.println("- " + i);
            }
        } else {
            for (int i = 0; i < user.getPopItems().length; i++) {
                System.out.println("- " + user.getPopItems()[i]);
            }
        }

        //Input date
        //Check if user pay in cash or card
        if (isCash) {

            //cash processing
            cash = CashStrategy.cashTransaction(0, sc);

            if (cash == -1.0) {
                // Timeout return to default page
                return;
            }

            if (cash == 0.000) {
                RecordCancel(date, user,"Cash Cancel");
                return;
            }

            ArrayList<Item> items = CustomerStrategy.selectItem(vm, sc);
            if (items == null) {
                System.out.println("Something wrong with Item array List");
                return;
            }
            item = CustomerStrategy.getItem(items, sc);
            if (item == null) {
                RecordCancel(date, user,"Item Cancel During Item selection");
                return;
            }


            itemQuantity = CustomerStrategy.selectQuantity(item, sc);
            if (itemQuantity == -1) {
                RecordCancel(date, user,"Item Cancel During Item Quantity selection");
                return;
            }

            if (itemQuantity == 100) {
                RecordCancel(date, user,"Item Cancel During Item Quantity selection");
                return;
            }

            if (itemQuantity == 0) {
                RecordCancel(date, user,"No item selected");
                return;
            }

            double amount = itemQuantity * item.getPrice();

            System.out.printf("You pick %d %s\nThe total is %.2f\n", itemQuantity, item.getName(), amount);

            coins = CashStrategy.getChange(cash, amount, vm.getCoinsPouch(), sc);


            //if there is no coin left to change
            if (coins == null) {
                System.out.println("There is no change return, please use other methods");
                RecordCancel(date, user,"Cash Cancel Coin Empty");
                return;
            }

            //if user cancel transaction during adding more coin
            if (coins.size() != 0) {
                if (coins.get(0).getDollar() == 0) {
                    RecordCancel(date, user,"Cash Cancel while picking item");
                    return;
                }
            }

            //change the amount
            records += String.format("%-10s. Name: %-15s Paid: %-10.2f\n", date, item.getName(), cash);
            for (Coin i : vm.getCoinsPouch()) {
                for (Coin y : coins) {
                    if (i.getDollar() == y.getDollar()) {
                        i.setAmount(i.getAmount() - y.getAmount());
                        records += String.format("Coins/Money: %-10.2f, %d\n", i.getDollar(), y.getAmount());
                    }
                }
            }

            ArrayList<Coin> buffer = LoadJason.loadBuffer();

            for (Coin i : vm.getCoinsPouch()) {
                for (Coin y : buffer) {
                    if (i.getDollar() == y.getDollar()) {
                        i.setAmount(i.getAmount() + y.getAmount());
                    }
                }
            }

            buffer.clear();

            LoadJason.loadBackBuffer(buffer);

            records += String.format("Cash\n");

        } else {

            String cardNumber = null;
            boolean dontRember = true;
            //pay in card
            if (user != null) {
                if (user.getCardNumber() == null) {
                    cardNumber = CardStrategy.cardPay(vm.getCardArrayList(), sc);
                } else {
                    cardNumber = user.getCardNumber();
                    dontRember = false;
                }
            } else {
                cardNumber = CardStrategy.cardPay(vm.getCardArrayList(), sc);
            }

            if (cardNumber == null) {
                RecordCancel(date, user,"Cancel Payment Card is not Verify");
                return;
            }

//            ArrayList<Item> items = CustomerStrategy.selectItem(vm, sc);
//
//            item = CustomerStrategy.getItem(items, sc);
//
//            if (item == null) {
//                RecordCancel(date, user,"Item Cancel During Item selection");
//                return;
//            }
//            itemQuantity = CustomerStrategy.selectQuantity(item, sc);
//            if (itemQuantity == 100) {
//                RecordCancel(date, user,"Item Cancel During Item Quantity selection");
//                return;
//            }
//            double amount = itemQuantity * item.getPrice();
            ArrayList<Item> items = CustomerStrategy.selectItem(vm, sc);
            if (items == null) {
                System.out.println("Something wrong with Item array List");
                return;
            }
            item = CustomerStrategy.getItem(items, sc);
            if (item == null) {
                RecordCancel(date, user,"Item Cancel During Item selection");
                return;
            }


            itemQuantity = CustomerStrategy.selectQuantity(item, sc);
            if (itemQuantity == -1) {
                RecordCancel(date, user,"Item Cancel During Item Quantity selection");
                return;
            }

            if (itemQuantity == 100) {
                RecordCancel(date, user,"Item Cancel During Item Quantity selection");
                return;
            }

            if (itemQuantity == 0) {
                RecordCancel(date, user,"No item selected");
                return;
            }

            double amount = itemQuantity * item.getPrice();

            //confirmed card payment
            int userInput = 0;
            userInput = LoadJason.inputCheckPayment("1. Confirmed\n2. Cancel\n", 1, 1, 2, true, sc);
            if (userInput == 1) {
                System.out.printf("You have paid $ %s\n", amount);
                // TIMEOUT
            } else if (userInput == 2 || userInput == -1) {
                RecordCancel(date, user,"Cancel Payment Card");
                return;
            }

            //for existing user
            if (user != null) {
                if (dontRember) {
                    int isRemember = CardStrategy.rememberCard(user, cardNumber, sc);
                    if (isRemember == 1) {
                        user.setCardNumber(cardNumber);
                    }
                }

            }
            records += String.format("%-10s. Name: %-15s Price: %-10.2f\n", date, item.getName(), amount);
            records += String.format("Card\n");
        }


        //different type user different save
        if (user == null) {
            vm.setPopItem(item.getName());
        } else {
            for (User i : vm.getUserArrayList()) {
                if (i.getUserName().equals(user.getUserName())) {
                    i.setPopItems(item.getName());
                    i.setCardNumber(user.getCardNumber());
                }
            }
        }

        if (itemQuantity == 0) {
            vm.AscentVM();
            return;
        }
        for (String i : vm.getItems().keySet()) {
            for (Item y : vm.getItems().get(i)) {
                if (y.getName().equals(item.getName())) {
                    y.setSold(y.getSold() + itemQuantity);
                    y.setQuantity(y.getQuantity() - itemQuantity);
                }
            }
        }
        System.out.println(records);
        vm.getRecords().add(records);
        vm.AscentVM();
    }

    public static void UserNameFlow(boolean isCash, String date) {
        Scanner sc = new Scanner(System.in);
        VendingMachine vm = new VendingMachine();
        System.out.println("Please input your name");
        String name = sc.nextLine();
        User user = LoadJason.checkUser(vm.getUserArrayList(), name);
        if (user == null) {
            System.out.println("There is no such User Name!");
            RecordCancel(date, user,"No such Username Cancel");
            return;
        }

        int i;
        for (i = 0; i < 3; i++) {
            String password = PasswordField.readPassword("Enter password: ");
            if (user.getPassWord().equals(password)) {
                System.out.println();
                break;
            } else {
                System.out.printf("Please enter a invalid password, you have %d more chance\n", 3 - i);
                sc.nextLine();
            }
        }

        if (i == 3) {
            System.out.println("You used all the your chance");
            RecordCancel(date, user,"Password Used 3 Times Cancel");
            return;
        }

        AnonymousUserFlow(isCash, user, date, sc);

    }

    public static void createUser() {
        VendingMachine vm = new VendingMachine();
        Scanner sc = new Scanner(System.in);
        String name = null;
        int counter = 0;
        while (true) {
            System.out.println("Please input your name");
            name = sc.next();
            for (int i = 0; i < vm.getUserArrayList().size(); i++) {
                if (vm.getUserArrayList().get(i).getUserName().equals(name)) {
                    System.out.println("Name can not be duplicated!!");
                } else {
                    counter += 1;
                }
            }
            sc.nextLine();
            if (counter == vm.getUserArrayList().size()) {
                break;
            }
        }

        System.out.println("Please input your password");
        String password = sc.nextLine();

        String[] popItem = new String[0];

        User a = new User(name, password, "", 0, popItem);

        vm.getUserArrayList().add(a);
        vm.AscentVM();
    }

    public static void RecordCancel(String date, User user, String reasons) {
        String name = "";
        if (user == null) {
            name = "Anon";
        } else {
            name = user.getUserName();
        }
        VendingMachine vm = new VendingMachine();
        String string = String.format("%s: %s %-10s", date, name, reasons);
//        System.out.println(string);
        vm.getCancelRecord().add(string);
        vm.AscentVM();
        return;
    }

}
