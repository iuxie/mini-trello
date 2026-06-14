package br.com.minitrello.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final UUID id;
    private String name;
    private final String email, password;
    private final List<Dashboard> dashboards;

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.dashboards = new ArrayList<>();
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

    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    @Override
    public String toString() {
        return "Nome: " + name + " | E-mail: " + email;
    }

}
