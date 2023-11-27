package test;

import main.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getCardValue() {
        var card = new Card(3);
        assertEquals(3, card.getCardValue());
    }
}