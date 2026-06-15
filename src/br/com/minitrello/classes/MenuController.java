package br.com.minitrello.classes;

import java.util.Scanner;

public class MenuController {

    private final Scanner input;
    private User loggedUser;
    private boolean running = true;

    public MenuController() {
        this.input = new Scanner(System.in);
        this.loggedUser = null;
    }

    public void start() {
        while (running) {
            if (loggedUser == null) {
                authenticateMenu();
            } else {
                mainMenu();
            }
        }
        System.out.println("Finalizando o programa...");
        input.close();
    }

    public void authenticateMenu() {

    }

    public void mainMenu() {

    }

}
