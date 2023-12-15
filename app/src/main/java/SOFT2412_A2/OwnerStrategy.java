package SOFT2412_A2;

import java.util.Locale;
import java.util.Scanner;

public class OwnerStrategy {

    private VendingMachine vendingMachine;

    public OwnerStrategy() {
        this.vendingMachine = new VendingMachine();
    }

    public void OwnerStrategy() {
        Scanner sc = new Scanner(System.in);
        String message = "1.Seller\n2.Cashier \n3.Owner \n4.Exit\n";
        int UserInput = LoadJason.inputCheck(message, 1, 3, 4, true, sc);

        if (UserInput == 1) {
            SellerStrategy ss = new SellerStrategy();
            ss.sellStrat();
        } else if (UserInput == 2) {
            CashierStrategy cs = new CashierStrategy();
            cs.CashierStrategy();
        } else if (UserInput == 3) {
            this.OwnerChoose(sc);
        } else {
            return;
        }
    }

    public void OwnerChoose(Scanner sc) {
        String message = "1.Display all User\n2.Add User\n3.List Cancel \n4.Exit\n";
        int UserInput = LoadJason.inputCheck(message, 1, 3, 4, true, sc);
        if (UserInput == 1) {
            for (User i: this.vendingMachine.getUserArrayList()) {
                System.out.println("Customer: "+ i.getUserName());
            }

            for (ProUser i: this.vendingMachine.getProUsers()) {
                System.out.printf("%s: %s\n",i.getRole(), i.getName());
            }

        } else if (UserInput == 2) {
            this.addAndRemoveUser(sc);
        } else if (UserInput == 3) {
            for (String i: this.vendingMachine.getCancelRecord()) {
                System.out.println(i);
            }
        }
    }

    public void addAndRemoveUser(Scanner sc) {
        VendingMachine vm = this.vendingMachine;
        String message = "1. Add User\n2. Remove User\n3. Cancel \n";
        int UserInput = LoadJason.inputCheck(message, 1, 2, 3, true, sc);
        if (UserInput == 1) {
            System.out.println("Please Input the Role you want to create");
            String role = "";
            while (sc.hasNextLine()) {
                role = sc.nextLine();
                if (role.toLowerCase().equals("owner")) {
                    role = "owner";
                    break;
                } else if (role.toLowerCase().equals("cashier")) {
                    role = "cashier";
                    break;
                } else if (role.toLowerCase().equals("seller")) {
                    role = "seller";
                    break;
                } else {
                    System.out.println("Please input a valid name!");
                }
            }

            System.out.println("Please Input the name");
            String name = "";

            while (sc.hasNextLine()) {
                name = sc.nextLine();
                for (ProUser i: this.vendingMachine.getProUsers()) {
                    if (name.equals(i.getName())) {
                        System.out.println("Please Input no duplicated name");
                        continue;
                    }
                }
                break;
            }

            System.out.println("Please Input the password");
            String password = "";

            password = sc.nextLine();

            ProUser pu = new ProUser(role, name, password);
            vm.getProUsers().add(pu);

        } else if (UserInput == 2) {
            System.out.println("Please Input the name you want to create");
            String name = sc.nextLine();
            ProUser user = null;

            for (ProUser i: vm.getProUsers()) {
                if (i.getName().equals(name)) {
                    user = i;
                }
            }

            if (user == null) {
                System.out.println("There is no such User");
                return;
            }

            vm.getProUsers().remove(user);
        }
        vm.AscentVM();
    }
}
