package SOFT2412_A2;

// import java.security.KeyStore.loadItemsStoreParameter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.*;
import java.util.stream.Collectors;

public class User {

    private String userName;
    private String passWord;
    private UserStrategy userStrategy;

    private String[] popItems;
    private String cardNumber;
    private int popPointer;

    public User(String userName, String password, String cardNumber, int popPointer, String[] popItems) {
        this.popPointer = popPointer;
        this.userName = userName;
        this.passWord = password;
        this.popItems = popItems;
        this.cardNumber = cardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String[] getPopItems() {
        return popItems;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public void setPopItems(String name) {
        if (this.popItems == null) {
            this.popItems = new String[5];
        }
        this.popItems[this.popPointer] = name;
        popPointer += 1;
        if (popPointer > 4) {
            popPointer = 0;
        }
    }


}

