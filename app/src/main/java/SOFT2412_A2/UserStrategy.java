package SOFT2412_A2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserStrategy {

    final static String DATE_FORMAT = "yyyy-mm-dd";
    public static void buyStuff() {
        int userInput = 0;
        int userInput_1 = 0;
        String date = null;
        boolean isCash = true;
        Scanner sc = new Scanner(System.in);
        String message = "Please select the following\n1. Anon\n2. Existing\n3. Create User\n4. Quit";
        userInput = LoadJason.inputCheck(message, 1, 3, 4, true, sc);


        if (userInput != 3 && userInput != 0) {
            date =  checkDate(sc);
            userInput_1 = LoadJason.inputCheck("1. Cash\n2. Card\n3. Quit", 1, 2, 3, true, sc);
        }

        if (userInput_1 == 1) {
            isCash = true;
        } else if (userInput_1 == 2){
            isCash = false;
        } else if (userInput_1 == 0 && userInput != 3) {
            return;
        }

        if (userInput == 1) {
            CustomerFlow.AnonymousUserFlow(isCash, null, date, sc);
        } else if (userInput == 2) {
            CustomerFlow.UserNameFlow(isCash, date);
        } else if (userInput == 3){
            CustomerFlow.createUser();
        } else {
            return;
        }

    }

    public static String checkDate (Scanner sc) {
        String date;
        do {
            try {
                System.out.println("Enter the date(like YYYY-MM-DD): ");
                date = sc.nextLine();
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                Date date_1 = df.parse(date);
                String strDate = df.format(date_1);
                return strDate;
            } catch (InputMismatchException | NumberFormatException | ParseException nf) {
                System.out.println("Please Input a valid command");
            }
        } while (true);
    }



}