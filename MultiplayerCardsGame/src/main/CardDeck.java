package main;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    public int name;

    private List<Card> cards = new ArrayList<>();

    private List<String> auxCards = new ArrayList<>();

    public CardDeck(int name){
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getAuxCards() {
        return auxCards;
    }

    public void addCard(Card newCard){
        cards.add(newCard);
        auxCards.add(String.valueOf(newCard.getCardValue()));
    }

    public void removeCard(Card card) {
        cards.remove(card);
        auxCards.remove(String.valueOf(card.getCardValue()));
    }
}
