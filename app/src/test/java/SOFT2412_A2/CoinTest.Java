package SOFT2412_A2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CoinTest {

    @Test
    public void CoinTest1() {
        Coin c = new Coin(10.0F, 2);
        assertEquals(10, c.getDollar(), 0.1f);
        assertEquals(2, c.getAmount());
    }

    @Test
    public void CoinTest2() {
        Coin c = new Coin(50.0F, 5);
        assertEquals(50, c.getDollar(), 0.1f);
        assertEquals(5, c.getAmount());
    }

    @Test
    public void CoinSetAmountTest1() {
        Coin c = new Coin(2.0F, 2);
        c.setAmount(10);
        assertEquals(2, c.getDollar(), 0.1f);
        assertEquals(10, c.getAmount());
    }

    @Test
    public void CoinSetAmountTest2() {
        Coin c = new Coin(50F, 2);
        c.setAmount(50);
        assertEquals(50, c.getDollar(), 0.1f);
        assertEquals(50, c.getAmount());
    }

    @Test
    public void CoinCompareToTest1() {
        Double d = 50.0;
        assertEquals(0, d.compareTo(new Coin(50F, 2).getDollar()));
    }

    @Test
    public void CoinCompareToTest2() {
        Double d = 40.0;
        Coin c2 = new Coin(20F,2);
        assertEquals(1, d.compareTo(c2.getDollar()));
    }

    @Test
    public void CoinCompareToTest3() {
        Double d = 10.0;
        Coin c2 = new Coin(20F,2);
        assertEquals(-1, d.compareTo(c2.getDollar()));
    }

}
