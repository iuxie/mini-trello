package br.com.minitrello.classes;

public enum PriorityTags {

    BAIXA("Baixa"),
    NORMAL("Normal"),
        ALTA("Alta"),
    URGENTE("Urgente");

    private final String name;

    PriorityTags(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
