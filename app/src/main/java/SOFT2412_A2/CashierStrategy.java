package SOFT2412_A2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;

public class CashierStrategy {

    private VendingMachine vendingMachine;

    public CashierStrategy() {
        this.vendingMachine = new VendingMachine();
    }

    public void getRecord () {
        for (String i: this.vendingMachine.getRecords()) {
            System.out.println(i);
        }
    }

    public void CashierStrategy() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your User and Pa");
        String message = "Please select the following\n1. Deposit Coin\n2. Withdraw Coin\n3. Display Change\n4. Display Record\n5. Quit";
        int uerInput = LoadJason.inputCheck(message, 1, 4, 5, true, sc);
        if (uerInput == 1) {
            this.modifyChange();
        } else if (uerInput == 2) {
            this.withdrawChange();
        } else if (uerInput == 3) {
            this.cashierDisplayChange();
        } else if (uerInput == 4){
            this.getRecord();
        } else {
            return;
        }
    }


    public void modifyChange() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Current available change:");


        ArrayList<Coin> coins = this.vendingMachine.getCoinsPouch();

        // Display coins
        for (int i = 0; i < coins.size(); i++) {
            System.out.printf("Dollar: %-5.2f Quantity: %d\n",coins.get(i).getDollar(), coins.get(i).getAmount());
        }

        System.out.println("\nEnter change to modify e.g. 50, 20, ... 0.1, 0.05");
        float currCoin = Float.parseFloat(scan.nextLine());

        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getDollar() == currCoin) {

                System.out.printf("Vending machine has %d of %.2f\nEnter amount to fill\n",
                        coins.get(i).getAmount(), coins.get(i).getDollar());

                int toAdd = Integer.parseInt(scan.nextLine());

                if (toAdd <= 0) {
                    System.out.println("Cannot add 0 or negative amounts of cash/coins");
                    return;
                }
                int total = coins.get(i).getAmount() + toAdd;

                if (total > 15) {
                    System.out.println("Cannot add amount of cash/coins that creates quantity > 15");
                    return;
                }
                coins.get(i).setAmount(total);
                this.vendingMachine.AscentVM();
                return;
            }
        }
        System.out.println("Error: did not enter a valid cash/coin");
    }

    public void withdrawChange() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Current available change:");


        ArrayList<Coin> coins = this.vendingMachine.getCoinsPouch();

        // Display coins
        for (int i = 0; i < coins.size(); i++) {
            System.out.printf("Dollar: %-5.2f Quantity: %d\n",coins.get(i).getDollar(), coins.get(i).getAmount());
        }

        System.out.println("\nEnter change to modify e.g. 50, 20, ... 0.1, 0.05");
        float currCoin = Float.parseFloat(scan.nextLine());

        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getDollar() == currCoin) {

                System.out.printf("Vending machine has %d of %.2f\nEnter amount to withdraw\n",
                        coins.get(i).getAmount(), coins.get(i).getDollar());

                int toWithdraw0 = Integer.parseInt(scan.nextLine());
                int toWithdraw = Math.abs(toWithdraw0);

                if (toWithdraw >= coins.get(i).getAmount()) {
                    System.out.println("Cannot withdraw more than avaiable cash/coins");
                    return;
                }
                int total = coins.get(i).getAmount() - toWithdraw;
//
//                if (total > 15) {
//                    System.out.println("Cannot add amount of cash/coins that creates quantity > 15");
//                    return;
//                }
                coins.get(i).setAmount(total);
                this.vendingMachine.AscentVM();
                return;
            }
        }
        System.out.println("Error: did not enter a valid cash/coin");
    }

    public void cashierDisplayChange() {
        System.out.println("Current available change:");
        ArrayList<Coin> coinsPouch = this.vendingMachine.getCoinsPouch();
        
        for (int i = 0; i < coinsPouch.size(); i++) {

            try {
                FileOutputStream fos = new FileOutputStream("./src/main/resources/DisplayChange.txt", true);
                
                
                if (coinsPouch.get(i).getDollar() >= 1) {
                    String message = String.format("Currency: $%-5.0f Amount: %-5d\n", coinsPouch.get(i).getDollar(), coinsPouch.get(i).getAmount());
                    System.out.printf(message);
                    fos.write(message.getBytes());
                    
                    
                }
                else {
                    String message = String.format("Currency: %.0fcents Amount: %-5d\n", coinsPouch.get(i).getDollar() * 100, coinsPouch.get(i).getAmount());
                    System.out.printf(message);
                    fos.write(message.getBytes());
                    
                }
                fos.close();
            }
            catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
