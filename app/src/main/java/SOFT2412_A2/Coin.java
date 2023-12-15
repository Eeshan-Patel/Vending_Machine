package SOFT2412_A2;
public class Coin implements Comparable<Coin>{
    private double dollar;
    private int amount;

    public Coin(double dollar, int amount) {
        this.dollar = dollar;
        this.amount = amount;
    }

    public double getDollar() {
        return dollar;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Coin comparestu) {
        Double compareage = ((Coin)comparestu).getDollar();
        return (int) (this.dollar - compareage);
    }
}
