package SOFT2412_A2;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.GsonBuilder;

import java.io.*;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LoadJason {
    public static int inputCheck(String message, int rangeMin, int rangeMax, int exitPoint, boolean isCancel, Scanner sc) {
        int userType = 0;
        do {
            try {
                System.out.println(message);
                userType = Integer.parseInt(sc.nextLine());
                if (userType >= rangeMin && userType <= rangeMax) {
//                    break;
                    return userType;
                } else if (userType == exitPoint) {
                    if (isCancel) {
                        return 0;
                    } else {
                        System.out.println("See you and Bye");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Please Input a valid command");
                    continue;
                }
            } catch (InputMismatchException | NumberFormatException nf) {
                System.out.println("Please Input a valid command here");
            }
        } while (sc.hasNextLine());
        return userType;
    }

    public static int inputCheckPayment(String message, int rangeMin, int rangeMax, int exitPoint, boolean isCancel, Scanner sc) {
        int userType = 0;
        do {
            try {
                System.out.println(message);
                String str = Timeout.inputTimeout(sc);

                if (str == null) {
                    // Timeout return back to default page
                    System.out.println("Press enter once before selecting Customer/Seller/Cashier/Owner\n");
                    return -1;
                }
                else if (str.equals("Cancel")) {
                    return -1;
                }
                userType = Integer.parseInt(str);
                if (userType >= rangeMin && userType <= rangeMax) {
//                    break;
                    return userType;
                } else if (userType == exitPoint) {
                    if (isCancel) {
                        return 0;
                    } else {
                        System.out.println("See you and Bye");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Please Input a valid command");
                    continue;
                }
            } catch (InputMismatchException | NumberFormatException nf) {
                System.out.println("Please Input a valid command here");
            }
        } while (sc.hasNextLine());
        return userType;
    }


    public static User checkUser(ArrayList<User> userArrayList, String name) {
        if (userArrayList == null) {
            return null;
        }
        for (User i: userArrayList) {
            if (i.getUserName().equals(name)) {
                return i;
            }
        }
        return null;
    }



    public static HashMap<String, ArrayList<Item>> LoadItemsHashMap() {
        Gson gson = new Gson();
        HashMap<String, ArrayList<Item>> map = null;
        File newFile = new File("./src/main/resources/Item.json");

        if (newFile.length() == 0) {
            map = new HashMap<String, ArrayList<Item>>();
            return map;
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Item.json")));
            Type type = new TypeToken<HashMap<String, ArrayList<Item>>>() {
            }.getType();
            map = gson.fromJson(json, type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public static void backToItemHashMap(HashMap<String, ArrayList<Item>> items) {

        try (FileWriter file = new FileWriter("./src/main/resources/Item.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(items);
            file.write(json);
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static ArrayList<Card> LoadCards() {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/credit_cards.json")));
            Card[] array = gson.fromJson(json, Card[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static ArrayList<String> LoadPopList() {
        JSONParser parser = new JSONParser();

        ArrayList<String> items = new ArrayList<String>();
        try {
            Object object = parser.parse(new FileReader("./src/main/resources/PopularItem.json"));
            JSONArray jsonObject = (JSONArray) object;
            for (Object obj : jsonObject) {
                items.add((String) obj);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void backToJsonPopList(ArrayList<String> popList) {
        try (FileWriter writer = new FileWriter("./src/main/resources/PopularItem.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(popList);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Coin> LoadCoins() {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Coins.json")));
            Coin[] array = gson.fromJson(json, Coin[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void backToJsonLoadCoinPouch(ArrayList<Coin> coins) {
        try (FileWriter writer = new FileWriter("./src/main/resources/Coins.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(coins);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static ArrayList<User> LoadUserList() {
        Gson gson = new Gson();
        ArrayList<User> userArrayList = new ArrayList<User>();
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Users.json")));
            User[] array = gson.fromJson(json, User[].class);
            return new ArrayList<>(Arrays.asList(array));
            //check assignment 1
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void backToJsonUserArrayList(ArrayList<User> userArry) {
        try (FileWriter writer = new FileWriter("./src/main/resources/Users.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(userArry);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> LoadRecordTransaction() {
        JSONParser parser = new JSONParser();

        ArrayList<String> items = new ArrayList<String>();
        try {
            Object object = parser.parse(new FileReader("./src/main/resources/TransactionRecord.json"));
            JSONArray jsonObject = (JSONArray) object;
            for (Object obj : jsonObject) {
                items.add((String) obj);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void BackTORecordTransaction(ArrayList<String> popList) {
        try (FileWriter writer = new FileWriter("./src/main/resources/TransactionRecord.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(popList);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> LoadRecordCancel() {
        JSONParser parser = new JSONParser();

        ArrayList<String> items = new ArrayList<String>();
        try {
            Object object = parser.parse(new FileReader("./src/main/resources/CancelRecord.json"));
            JSONArray jsonObject = (JSONArray) object;
            for (Object obj : jsonObject) {
                items.add((String) obj);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void BackToRecordCancel(ArrayList<String> popList) {
        try (FileWriter writer = new FileWriter("./src/main/resources/CancelRecord.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(popList);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Coin> loadBuffer() {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Buffer.json")));
            Coin[] array = gson.fromJson(json, Coin[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void loadBackBuffer (ArrayList<Coin> coins) {
        try (FileWriter writer = new FileWriter("./src/main/resources/Buffer.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(coins);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ProUser> loadProUser() {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/ProUser.json")));
            ProUser[] array = gson.fromJson(json, ProUser[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void loadBacckProUser (ArrayList<ProUser> coins) {
        try (FileWriter writer = new FileWriter("./src/main/resources/ProUser.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(coins);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

