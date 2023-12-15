package SOFT2412_A2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.checkerframework.common.reflection.qual.NewInstance;

import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;


import java.io.FileOutputStream;

public class SellerStrategy {

    private VendingMachine vendingMachine;

    public SellerStrategy() {
        this.vendingMachine = new VendingMachine();
    }


    public void sellStrat() {
        Scanner sc = new Scanner(System.in);
        String message = "Please select the following\n1. Modify Item\n2. Display Current Item\n3. Summary of Items Sold\n4. Quit";
        int userInput = LoadJason.inputCheck(message, 1, 3, 4, true, sc);
        if (userInput == 1) {
            this.vendingMachine.printItem();
            this.fillModifyItem();
        } else if (userInput == 2) {
            this.vendingMachine.printItem();
        } else if (userInput == 3) {
            this.sellerSummary();
        } else {
            return;
        }
    }

    public boolean existingItemName(String itemName) {
        Map<String, ArrayList<Item>> itemMap = this.vendingMachine.getItems();
        for (ArrayList<Item> items : itemMap.values()) {
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).getName().equals(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existingItemCode(int itemCode) {
        Map<String, ArrayList<Item>> itemMap = this.vendingMachine.getItems();
        for (ArrayList<Item> items : itemMap.values()) {
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).getCode() == itemCode) {
                    return true;
                }
            }
        }
        return false;
    }

    public void fillModifyItem() {
        Scanner scan = new Scanner(System.in);
        String category = "Drink";

        System.out.println("Select Item Category:");
        System.out.println("1.Drink\n2.Chocolates\n3.Chips\n4.Candies\n5.Exit");
        int categorySelection = Integer.parseInt(scan.nextLine());

        if (categorySelection == 1) {
            category = "Drink";
        }
        else if (categorySelection == 2) {
            category = "Chocolates";
        }
        else if (categorySelection == 3) {
            category = "Chips";
        }
        else if (categorySelection == 4) {
            category = "Candies";
        }
        else if (categorySelection == 5) {
            return;
        }

        Map<String, ArrayList<Item>> itemMap = this.vendingMachine.getItems();
        ArrayList<Item> items = itemMap.get(category);

        System.out.println("Enter Item name to modify");
        String itemName = scan.nextLine();
        Item itemToModify = null;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                // Finds item specificed by user input
                itemToModify = items.get(i);
            }
        }

        if (itemToModify == null) {
            System.out.println("No Item with that name exists");
            return;
        }

        System.out.println("Select modify type");
        System.out.println("1.Item name\n2.Item code\n3.Item Category\n4.Item quantity\n5.Item price");
        int selection = Integer.parseInt(scan.nextLine());
        if (selection == 1) {
            // Modify item name
            System.out.println("Enter new item name");
            String newName = scan.nextLine();
            if (existingItemName(newName)) {
                System.out.println("Error: Item name entered already exists");
            }
            itemToModify.setName(newName);


        }
        else if (selection == 2) {
            // Modify item code
            System.out.println("Enter new item code");
            int newCode = Integer.parseInt(scan.nextLine());
            if (existingItemCode(newCode)) {
                System.out.println("Error: Item code entered already exists");
            }
            itemToModify.setCode(newCode);
        }
        else if (selection == 3) {
            // Modify item Category

            System.out.println("Enter new item category");
            String newCategory = scan.nextLine();

            if (category.equals(newCategory)) {
                System.out.println("Error: item is already in the category entered");
                return;
            }
            else if (!newCategory.equals("Drink") && !newCategory.equals("Chocolates") &&
                    !newCategory.equals("Chips") && !newCategory.equals("Candies")) {
                System.out.println("Error: category entried does not exists");
                return;
            }

            // Item tempItem = itemToModify;
            items.remove(itemToModify);

            for (Map.Entry<String, ArrayList<Item>> entry : itemMap.entrySet()) {
                if (entry.getKey().equals(newCategory)) {
                    // Found the new category to place the item in
                    entry.getValue().add(itemToModify);
                }
            }
        }
        else if (selection == 4) {
            // Modify item quantity
            System.out.println("Enter quantity to fill vending machine");
            int toAdd = Integer.parseInt(scan.nextLine());
            if (toAdd <= 0) {
                System.out.println("Cannot add 0 or negative quantites of an item");
                return;
            }
            int total = toAdd + itemToModify.getQuantity();
            if (total > 15) {
                System.out.println("Cannot fill item stock above 15");
                return;
            }
            itemToModify.setQuantity(total);
        }
        else if (selection == 5) {
            // Modify item price
            System.out.println("Enter new item price");

            int newPrice = Integer.parseInt(scan.nextLine());
            if (newPrice <= 0) {
                System.out.println("Cannot have 0 or negative price for item");
                return;
            }
            itemToModify.setPrice(newPrice);
        }
        // DOUBLE CHECK
        this.vendingMachine.AscentVM();
    }

    public void sellerSummary() {
        boolean sold = false;
        Map<String, ArrayList<Item>> itemMap = this.vendingMachine.getItems();
        System.out.println("Seller Summary:");
        for (Map.Entry<String, ArrayList<Item>> entry : itemMap.entrySet()) {
            ArrayList<Item> items = entry.getValue();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getSold() > 0) {
                    sold = true;
                    try {
                        String message = String.format("Item Code: %-5d Name: %-15s Sold: %-5d\n", items.get(i).getCode(), items.get(i).getName(), items.get(i).getSold());
                        FileOutputStream fos = new FileOutputStream("./src/main/resources/ItemSellerSummary.txt", true);
                        fos.write(message.getBytes());
                        fos.close();
                        System.out.printf(message);
                    }
                    catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                  
                }
            }
        }
        if (sold == false) {
            System.out.println("Currently no Items have been sold");
        }
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
