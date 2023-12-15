package SOFT2412_A2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void ItemTest1() {
        Item i = new Item("Smith", 21, 15, 1.0F, 0);
        assertEquals("Smith", i.getName());
        assertEquals(21, i.getCode());
        assertEquals(15, i.getQuantity());
        assertEquals(1.0, i.getPrice(), 0.1f);
        assertEquals(0, i.getSold());
    }

    @Test
    public void ItemTest2() {
        Item i = new Item("Mars", 20, 13, 50.0F, 2);
        assertEquals("Mars", i.getName());
        assertEquals(20, i.getCode());
        assertEquals(13, i.getQuantity());
        i.setCode(12);
        assertEquals(12, i.getCode());
        assertEquals(50, i.getPrice(), 0.1f);
        assertEquals(2, i.getSold());
    }

    @Test
    public void ItemTest3() {
        Item i = new Item("Kettle", 15, 14, 1.50F, 1);
        assertEquals("Kettle", i.getName());
        assertEquals(15, i.getCode());
        assertEquals(14, i.getQuantity());
        assertEquals(1.5, i.getPrice(), 0.1f);
        assertEquals(1, i.getSold());
    }

    @Test
    public void ItemTest4() {
        Item i = new Item("Pepsi", 4, 15, 2.5F, 4);
        assertEquals("Pepsi", i.getName());
        assertEquals(4, i.getCode());
        assertEquals(15, i.getQuantity());
        assertEquals(2.5, i.getPrice(), 0.1f);
        i.setName("Coca cola");
        i.setCode(3);
        assertEquals("Coca cola", i.getName());
        assertEquals(3, i.getCode());
        assertEquals(4, i.getSold());
        i.setQuantity(27);
        assertEquals(27, i.getQuantity());
    }

    @Test
    public void ItemTestSetName1() {
        Item i = new Item("sknd", 0, 0, 0.0F, 0);
        assertEquals("sknd", i.getName());
        assertEquals(0, i.getCode());
        assertEquals(0, i.getSold());
        i.setName("Coca cola");
        i.setQuantity(27);
        i.setCode(52);
        assertEquals("Coca cola", i.getName());
        assertEquals(52, i.getCode());
        assertEquals(27, i.getQuantity());
        assertEquals(0, i.getPrice(), 0.1f);

    }

    @Test
    public void ItemTestSetName2() {
        Item i = new Item("Mentos", 15, 100, 1.2F, 11);
        assertEquals("Mentos", i.getName());
        assertEquals(15, i.getCode());
        assertEquals(100, i.getQuantity());
        assertEquals(1.2, i.getPrice(), 0.1f);
        i.setName("Skittles");
        assertEquals("Skittles", i.getName());
        assertEquals(11, i.getSold());
    }

    @Test
    public void ItemTestSetNameEmpty() {
        Item i = new Item("Sprite", 01, 10, 5.0F, 5);
        assertEquals("Sprite", i.getName());
        assertEquals(01, i.getCode());
        assertEquals(10, i.getQuantity());
        assertEquals(5, i.getPrice(), 0.1f);
        i.setName("");
        assertEquals("", i.getName());
        i.setQuantity(8);
        assertEquals(8, i.getQuantity());
    }

    @Test
    public void ItemTestSetSold1() {
        Item i = new Item("Pepsi", 02, 10, 3.0F, 2);
        assertEquals("Pepsi", i.getName());
        assertEquals(02, i.getCode());
        assertEquals(10, i.getQuantity());
        assertEquals(3, i.getPrice(), 0.1f);
        i.setSold(2);
        assertEquals(2, i.getSold());
    }

    @Test
    public void ItemTestSetSold2() {
        Item i = new Item("Bounty", 14, 35, 2.0F, 22);
        assertEquals("Bounty", i.getName());
        assertEquals(14, i.getCode());
        assertEquals(35, i.getQuantity());
        assertEquals(2, i.getPrice(), 0.1f);
        i.setSold(20);
        assertEquals(20, i.getSold());
    }
}
