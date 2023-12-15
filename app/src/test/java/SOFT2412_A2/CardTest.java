package SOFT2412_A2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void CardTest1(){
        Card c = new Card("Pat", "12345678");
        assertEquals("Pat",c.getName());
        assertEquals("12345678",c.getNumber());
    }

    @Test
    public void CardTest2(){
        Card c = new Card("David", "87654321");
        assertEquals("David",c.getName());
        assertEquals("87654321",c.getNumber());
    }

    @Test
    public void CardTestEmptyName1(){
        Card c = new Card("", "56784321");
        assertEquals("",c.getName());
        assertEquals("56784321",c.getNumber());
    }

    @Test
    public void CardTestEmptyName2(){
        Card c = new Card("", "123456");
        assertEquals("",c.getName());
        assertEquals("123456",c.getNumber());
    }

    @Test
    public void CardTestEmptyNumber1(){
        Card c = new Card("Steve", "");
        assertEquals("Steve",c.getName());
        assertEquals("",c.getNumber());
    }

    @Test
    public void CardTestEmptyNumber2(){
        Card c = new Card("Aaron", "");
        assertEquals("Aaron",c.getName());
        assertEquals("",c.getNumber());
    }
    
}
