package br.com.minitrello.classes;

public enum Priority {

    BAIXA("Baixa"),
    NORMAL("Normal"),
    ALTA("Alta"),
    URGENTE("Urgente");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
