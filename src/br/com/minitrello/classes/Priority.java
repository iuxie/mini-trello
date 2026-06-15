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

    public static void showPrioritys() {
        System.out.println("1 - Baixa");
        System.out.println("2 - Normal");
        System.out.println("3 - Alta");
        System.out.println("4 - Urgente");
    }

}
