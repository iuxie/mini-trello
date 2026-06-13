package br.com.minitrello.classes;

import java.util.UUID;

public class User {

    // TODO: Implementar atributo de List<Dashboard> quando a classe for criada.
    private final UUID id;
    private String name;
    private final String email, password;

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nome: " + name + " | E-mail: " + email;
    }

}
