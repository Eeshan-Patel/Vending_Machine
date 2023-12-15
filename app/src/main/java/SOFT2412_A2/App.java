/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package SOFT2412_A2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App {

//    public App() {
//        sc = new Scanner(System.in);
//    }
    public static void main(String[] args) {
        App app = new App();
        VendingMachine vm = new VendingMachine();

        int userType = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String message = "1.Customer\n2.Seller\n3.Cashier\n4.Owner\n5.Exit";
            userType = LoadJason.inputCheck(message, 1, 4, 5, true, sc);
            ArrayList<Coin> coins = vm.getCoinsPouch();

            if (userType == 1) {
                UserStrategy.buyStuff();
            } else if (userType >= 2 && userType <=4){
                int newType = checkUserName(sc, userType);
                if (newType == 2) {
                    SellerStrategy ss = new SellerStrategy();
                    ss.sellStrat();
                } else if (newType == 3) {
                    CashierStrategy cs = new CashierStrategy();
                    cs.CashierStrategy();
                } else if (newType == 4) {
                    OwnerStrategy os = new OwnerStrategy();
                    os.OwnerStrategy();
                }
            } else {
                System.out.println("See you and Bye");
                break;
            }
        }


    }

    public static int checkUserName(Scanner sc, int input){
        VendingMachine vm = new VendingMachine();
        System.out.println("Please input your name");
        String name = sc.nextLine();
        ProUser user = null;
        for (ProUser i: vm.getProUsers()) {
            if (i.getName().equals(name)) {
                user = i;
            }
        }

        if (user == null) {
            System.out.println("There is no such user!!");
            return 0;
        }

        int i;
        for (i = 0; i < 3; i++) {
            String password = PasswordField.readPassword("Enter password: ");
            if (user.getPassword().equals(password)) {
                System.out.println();
                break;
            } else {
                System.out.printf("Please enter a invalid password, you have %d more chance\n", 3 - i);
                sc.nextLine();
            }
        }

        if (i == 3) {
            System.out.println("You used all the your chance");
            return 0;
        }

        System.out.printf("You are a %s\n", user.getRole());
        return input;
    }

}
