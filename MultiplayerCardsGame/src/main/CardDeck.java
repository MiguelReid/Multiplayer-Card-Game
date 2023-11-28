package main;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final int name;

    private List<Card> cards = new ArrayList<>();

    private List<String> auxCards = new ArrayList<>();

    public CardDeck(int name){
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getAuxCards() {
        return auxCards;
    }

    public void addCard(Card newCard){
        cards.add(newCard);
        auxCards.add(String.valueOf(newCard.cardValue()));
    }

    public void removeCard(Card card) {
        cards.remove(card);
        auxCards.remove(String.valueOf(card.cardValue()));
    }
}
