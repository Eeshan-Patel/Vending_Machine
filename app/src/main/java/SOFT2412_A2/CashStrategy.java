package SOFT2412_A2;

import java.util.*;



public class CashStrategy {

    public static Double cashTransaction(double user_total, Scanner sc) {
        ArrayList<Coin> buffer = new ArrayList<Coin>();
        VendingMachine vm = new VendingMachine();
        double currency = 0;
        int currencyAmount = 0;
        double userTotal = 0;
        int userInput = 0;
        boolean buttonSwitch = true;
        boolean found = false;
        while (true) {
            do {
                try {
                    System.out.println("Please Enter the currency e.g. 50, 20, etc.");
                    String str = Timeout.inputTimeout(sc);
                    if (str == null) {
                        // Timeout return back to default page
                        System.out.println("Press enter once before selecting Customer/Seller/Cashier/Owner\n");
                        return -1.0;
                    
                    }
                    else if (str.equals("Cancel")) {
                        return -1.0;
                    }

                    currency = Double.parseDouble(str);

                    for (Coin i : vm.getCoinsPouch()) {
                        if (i.getDollar() == currency) {
                            buttonSwitch = false;
                            break;
                        }
                    }
                    if (buttonSwitch) {
                        System.out.println("Please Input a valid command here");
                    }
                } catch (InputMismatchException | NumberFormatException nf) {
                    System.out.println("Please Input a valid command\n");
                }
            } while (buttonSwitch&&sc.hasNextLine());

            //get change to vm
            for (Coin i: buffer) {
                if (i.getDollar() == currency) {
                    found = true;
                }
            }
            if (!found) {
                buffer.add(new Coin(currency, 0));
            }

            do {
                try {
                    System.out.printf("Enter the quantity of the chosen currency (%.2f)\n", currency);
                    String str = Timeout.inputTimeout(sc);
                    if (str == null) {
                        // Timeout return back to default page
                        System.out.println("Press enter once before selecting Customer/Seller/Cashier/Owner\n");
                        return -1.0;
                    }
                    else if (str.equals("Cancel")) {
                        return -1.0;
                    }
                    currencyAmount = Integer.parseInt(str);
                    break;
                } catch (InputMismatchException | NumberFormatException nf) {
                    System.out.println("Please Input a valid command\n");
                }
            } while (sc.hasNextLine());

            for (Coin i: buffer) {
                if (i.getDollar() == currency) {
                    i.setAmount(i.getAmount() + currencyAmount);
                }
            }

            userInput = LoadJason.inputCheckPayment("Press 1 to enter more money\nPress 2 to select category\nPress 3 to cancel",
                    1, 2,3, true, sc);
            userTotal = currencyAmount * currency;
            
            ///////////////////////
            // WHEN WOULD THIS BE 0
            ///////////////////////

            ///////////////////////
            // NEED TO ADD TIMEOUT 
            //////////////////////


           
            if (userInput == 1) {
                continue;
            } else if(userInput == 0) {
                buffer.clear();
                LoadJason.loadBackBuffer(buffer);
                return 0.000;
            ///////////////////////
            // NEED TO ADD TIMEOUT 
            //////////////////////
            } else if (userInput == -1) {
                return -1.0;
            } else {
                LoadJason.loadBackBuffer(buffer);
                return userTotal;
            }
        }
    }

    public static ArrayList<Coin> getChange(double amount, double expectAmount, ArrayList<Coin> coins, Scanner sc) {

        ArrayList<Coin> returnCoin = new ArrayList<Coin>();
        boolean isEmpty = false;

        boolean added = false;

        while (amount < expectAmount) {
            System.out.println("Insufficient funds");
            System.out.println("Heading back to pay!!");
            double temp = cashTransaction(amount, sc);

            if (temp == 0.000) {
                returnCoin.add(new Coin(0, 0));
                return returnCoin;

            } else {
                amount += temp;
            }
        }

        System.out.printf("You paid %.2f\n", amount);
        double change = amount - expectAmount;
        Collections.sort(coins,Collections.reverseOrder());
        System.out.println("The changes are:");
        for (int i = 0; i < coins.size(); i++) {
            added = false;

            amount = Math.floor(change / coins.get(i).getDollar());

            if (amount <= 0) {
                continue;
            }

            if (coins.get(i).getAmount() < amount) {
                continue;
            }

            System.out.printf("Currency: %-5.2f Amount: %d\n", coins.get(i).getDollar(), (int)amount);

            for (Coin y: returnCoin) {
                if ( coins.get(i).getDollar() == y.getDollar()) {
                    y.setAmount(y.getAmount() + 1);
                    added = true;
                }
            }
            if (added == false) {
                returnCoin.add(new Coin(coins.get(i).getDollar(), 1));
            }

            change -= (amount * coins.get(i).getDollar());

            if (change == 0) {
                isEmpty = true;
                break;
            }
        }
        if (isEmpty == false) {
            System.out.println("There is no cash/coin to change");
        }
        return returnCoin;
    }

}
