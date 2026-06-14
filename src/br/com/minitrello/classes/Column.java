package br.com.minitrello.classes;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Column {

    private final UUID id;
    private String title;
    private final List<Card> cards;

    public Column(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.cards = new LinkedList<>();
    }

    public void addCard(Card c) {
        cards.add(c);
    }

    public void removeCard(Card c) {
        cards.remove(c);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return cards.toString();
    }

}
