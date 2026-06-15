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
        System.out.println("\n==== Menu de Autenticacao ====");
        System.out.println("1 - Fazer Login");
        System.out.println("2 - Criar usuário");
        System.out.println("3 - Finalizar programa");
        System.out.print("Escolha uma opcao: ");

        int opcao = Integer.parseInt(input.nextLine());

        switch (opcao) {
            case 1:
                System.out.println("Implementando lógica");
                break;
            case 2:
                System.out.println("Implementando lógica");
                break;
            case 3:
                running = false;
                break;
            default:
                System.out.println("Opcao inválida! Tente novamente.");
                break;
        }

    }

    public void mainMenu() {

    }

}
