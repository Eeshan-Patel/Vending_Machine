package SOFT2412_A2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CardStrategy {
    public static int rememberCard(User user, String cardNumber, Scanner sc) {

        int isRemember = LoadJason.inputCheck("Would you like you card to be remember?\n1. Yes\n2. No",
                1, 1, 2, true, sc);
        return isRemember;
    }


    public static String cardPay(ArrayList<Card> cards, Scanner sc) {
        String cardNumber = "";

        for (int i = 0; i < 3; i++ ) {
            System.out.printf("Please input your card number(you have %d more chance)\n", 3-i);
            // 
            if (sc.hasNextLine()) {
                // TIMEOUT
                cardNumber = Timeout.inputTimeout(sc);
                if (cardNumber == null) {
                    // Timeout return back to default page
                    System.out.println("Press enter once before selecting Customer/Seller/Cashier/Owner\n");
                    return null;
                }
                else if (cardNumber.equals("Cancel")) {
                    return null;
                }
            }

            if (!checkCard(cardNumber, cards)) {
                System.out.println("Please Input a valid command");
            } else {
                System.out.println("Card Confirmed");
                return cardNumber;
            }
        }
        return null;
    }
    public static boolean checkCard(String Card, ArrayList<Card> cards) {
        for (Card i: cards) {
            if (i.getNumber().equals(Card)) {
                return true;
            }
        }
        return  false;
    }




}
