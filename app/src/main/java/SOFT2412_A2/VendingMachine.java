package SOFT2412_A2;

import org.w3c.dom.css.Counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.io.FileOutputStream;

public class VendingMachine {

    private ArrayList<Card> cards;
    private ArrayList<User> users;
    private HashMap<String, ArrayList<Item>> items;
    private ArrayList<Coin> coinsPouch;
    private ArrayList<String> popItem;
    private ArrayList<String> cancelRecord;
    private int popIndex;
    private ArrayList<ProUser> proUsers;

    private  ArrayList<String> Records;
    public VendingMachine () {
        cards = LoadJason.LoadCards();
        users = LoadJason.LoadUserList();
        items = LoadJason.LoadItemsHashMap();
        coinsPouch = LoadJason.LoadCoins();
        popItem = LoadJason.LoadPopList();
        Records = LoadJason.LoadRecordTransaction();
        cancelRecord = LoadJason.LoadRecordCancel();
        popIndex = popItem.size() - 1;
        proUsers = LoadJason.loadProUser();
    }

    public ArrayList<Card> getCardArrayList() {
        return cards;
    }

    public HashMap<String, ArrayList<Item>> getItems() {
        return items;
    }

    public ArrayList<User> getUserArrayList() {
        return users;
    }

    public ArrayList<String> getPopList() {
        return popItem;
    }

    public ArrayList<Coin> getCoinsPouch() {
        return coinsPouch;
    }

    public ArrayList<String> getRecords() {
        return Records;
    }

    public ArrayList<String> getCancelRecord() {
        return cancelRecord;
    }
    public ArrayList<ProUser> getProUsers() {
        return this.proUsers;
    }

    public void setPopItem(String name) {
        if (popIndex == 4) {
            popIndex = 0;
        }
        this.popItem.set(popIndex, name);
        popIndex += 1;
    }

    public void AscentVM() {
        LoadJason.backToJsonLoadCoinPouch(this.coinsPouch);
        LoadJason.backToItemHashMap(this.items);
        LoadJason.backToJsonPopList(this.popItem);
        LoadJason.backToJsonUserArrayList(this.users);
        LoadJason.BackTORecordTransaction(this.Records);
        LoadJason.BackToRecordCancel(this.cancelRecord);
        LoadJason.loadBacckProUser(this.proUsers);
    }

    public void printItem() {
        // Prints the item in a specific category including items name, price and quantity.
        HashMap<String, ArrayList<Item>> items = this.items;
        int counter = 1;
        for (String key : items.keySet()) {
            counter = 1;
            System.out.println(key + ":");
            for (Item i: items.get(key)) {
                try {
                    String message = String.format("%d. Name: %-15s Price: %-10.2f Quantity: %-10d\n", counter, i.getName(), i.getPrice(), i.getQuantity());
                    FileOutputStream fos = new FileOutputStream("./src/main/resources/CurrentItems.txt", true);
                    fos.write(message.getBytes());
                    fos.close();
                    System.out.printf(message);
                }
                catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                counter += 1;
            }
        }
    }

    public ArrayList<Item> returnCategory(String category) {
        HashMap<String, ArrayList<Item>> items = this.items;
        for (String key : items.keySet()) {
            if (key.equals(category)) {
                return this.items.get(key);
            }
        }
        return null;
    }




//    public Item getItem(String category, String itemName) {
//        for (String key : this.items.keySet()) {
//
//        }
//    }

//    public void UserStrategy() {
//        int userType = 0;
//        VendingMachine app = new VendingMachine();
//        String message = "1.Anon\n2.Existing\n3.Create User\n4.Exit";
//
//        while (true) {
//            userType = LoadJason.inputCheck(message, 1,3,4);
//
//            if (userType == 1) {
//                //need change in cash function
//            } else if (userType == 2) {
//                CardStrategy cardStrategy = new CardStrategy();
//                String popItem = cardStrategy.pay();
//                if (popItem == null) {
//                    System.out.println("There is no such card or invalid card");
//                    continue;
//                }
//                this.setPopItem(popItem);
//            } else {
//
//            }
//        }
//
//    }


}
