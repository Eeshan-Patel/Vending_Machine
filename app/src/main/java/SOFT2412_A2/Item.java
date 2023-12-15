package SOFT2412_A2;

public class Item {
    private String name;
    private double price;
    private int code;
    private int quantity;
    private int sold;
    public Item(String name, int code, int quantity, double price, int sold) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.price = price;
        this.sold = sold;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return quantity;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
    public int getSold() {
        return this.sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setName(String name) {
        this.name = name;
    }
}
