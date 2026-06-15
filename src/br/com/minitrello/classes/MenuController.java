package br.com.minitrello.classes;

import br.com.minitrello.data.JsonDatabase; // Importe a classe que criamos
import java.util.List;
import java.util.Scanner;

public class MenuController {

    private final Scanner input;
    private User loggedUser;
    private boolean running = true;
    private final JsonDatabase database;
    private final List<User> usersList;

    public MenuController() {
        this.input = new Scanner(System.in);
        this.loggedUser = null;
        this.database = new JsonDatabase();
        this.usersList = database.loadUsers();
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
                fazerLogin();
                break;
            case 2:
                criarUsuario();
                break;
            case 3:
                running = false;
                break;
            default:
                System.out.println("Opcao inválida! Tente novamente.");
                break;
        }
    }

    private void fazerLogin() {
        System.out.print("Digite seu email: ");
        String email = input.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        for (User u : usersList) {
            if (u.getEmail().equals(email) && u.getPassword().equals(senha)) {
                loggedUser = u;
                System.out.println("Login realizado com sucesso! Bem-vindo, " + u.getName());
                return;
            }
        }
        System.out.println("Email ou senha incorretos.");
    }

    private void criarUsuario() {
        System.out.print("Digite seu nome: ");
        String nome = input.nextLine();
        System.out.print("Digite seu email: ");
        String email = input.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        User novoUsuario = new User(nome, email, senha);
        usersList.add(novoUsuario);

        loggedUser = novoUsuario;

        database.saveUsers(usersList);

        System.out.println("Usuário criado com sucesso!");
    }

    public void mainMenu() {

    }
}