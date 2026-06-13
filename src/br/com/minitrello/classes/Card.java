package br.com.minitrello.classes;

import java.util.UUID;

public class Card {

    //  TODO: Implementar atributo de prioridade após a criacao do ENUM.
    private final UUID id;
    private String title, description;
    private User user;

    public Card(String title, String description, User user) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Título: " + title + " | Descricao: " + description + " | Usuário: " + user;
    }

}
