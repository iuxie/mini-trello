package br.com.minitrello.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Dashboard {

    private final UUID id;
    private String title;
    private final List<Column> columns;

    public Dashboard(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.columns = new ArrayList<>();
    }

    public void addColumn(Column c) {
        columns.add(c);
    }

    public void removeColumn(Column c) {
        columns.remove(c);
    }

    public void changeCardColumn(Card c, Column origin, Column destiny) {
        origin.removeCard(c);
        destiny.addCard(c);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Título: " + title + " | Colunas: " + columns;
    }

}
