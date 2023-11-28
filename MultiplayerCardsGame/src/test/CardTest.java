package test;

import main.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

    @Test
    public void getCardValue() {
        Card card = new Card(3);
        assertEquals(3, card.value());
    }
}