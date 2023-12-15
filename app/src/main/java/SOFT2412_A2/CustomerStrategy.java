package SOFT2412_A2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerStrategy {
    public static int selectQuantity(Item item, Scanner sc) {
        if (item == null) {
            System.out.println("No items have been select");
            return 0;
        }
        int item_quantity = 0;

        do {
            try {
                System.out.println("Enter the quantity you want to purchase of that item\nTo cancel the transaction input 100\n");
                String str = Timeout.inputTimeout(sc);
                if (str == null) {
                    // Timeout return back to default page
                    System.out.println("Press enter once before selecting Customer/Seller/Cashier/Owner\n");
                    return -1;
                
                }
                else if (str.equals("Cancel")) {
                    return -1;
                }
                item_quantity = Integer.parseInt(str);
                if (item_quantity >= 0 && item_quantity <= item.getAmount()) {
                    if (item.getAmount() == 0) {
                        return 0;
                    }
                    return item_quantity;
                } else if (item_quantity == 100) {
                    return 0;
                } else {
                    System.out.println("Please Input a invalid command");
                    continue;
                }
            } catch (InputMismatchException | NumberFormatException nf) {
                System.out.println("Please Input a invalid command");
            }

        } while (sc.hasNextLine());

        return item_quantity;
    }

    public static ArrayList<Item> selectItem(VendingMachine vm, Scanner sc) {
        String category;
        int userInput = 0;
        userInput = LoadJason.inputCheckPayment("Select Item Category\n1. Drink\n2. Chocolates\n3. Chips\n4. Candies\n5. Cancel\n",
                1, vm.getItems().keySet().size(), 5, true, sc);
        if (userInput == 1) {
            category = "Drink";
        } else if (userInput == 2) {
            category = "Chocolates";
        } else if (userInput == 3) {
            category = "Chips";
        } else if (userInput == 4) {
            category = "Candies";
        } else {
            return null;
        }

        if (category == null) {
            System.out.println("Error in userinput!!");
            return null;
        }
        ArrayList<Item> items = vm.returnCategory(category);

        return items;
    }

    public static Item getItem(ArrayList<Item> items, Scanner sc) {
        int userInput = 0;
        String item_message = "";

        if (items.size() == 0) {
            System.out.println("There is no items in this category!");
        }

        int counter = 1;
        for (Item i: items) {
            item_message += String.format("%d. Name: %-15s Price: %-10.2f Quantity: %-10d\n", counter, i.getName(), i.getPrice(), i.getQuantity());
            counter += 1;
        }
        item_message += String.valueOf(counter);
        item_message += ": ";
        item_message += "Exit\n";
        userInput = LoadJason.inputCheckPayment(item_message, 1, items.size(),items.size()+1, true, sc);

        if (userInput == -1) {
            return null;
        }


        if (userInput  == 0) {
            return null;
        }

        Item chosen_item = items.get(userInput-1);

        return chosen_item;
    }

}
