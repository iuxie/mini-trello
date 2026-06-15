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

    private void authenticateMenu() {
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

    private void mainMenu() {
        System.out.println("\n==== Menu Principal ====");
        System.out.println("Usuário Logado: " + loggedUser.getName() + "\n");
        System.out.println("1 - Listar Dashboards");
        System.out.println("2 - Acessar Dashboard");
        System.out.println("3 - Criar Dashboard");
        System.out.println("4 - Excluir Dashboard");
        System.out.println("5 - Logout");
        System.out.print("Escolha uma opcao: ");

        int opcao = Integer.parseInt(input.nextLine());

        switch (opcao) {
            case 1:
                listarDashboards();
                break;
            case 2:
                acessarDashboard();
                break;
            case 3:
                criarDashboard();
                break;
            case 4:
                excluirDashboard();
                break;
            case 5:
                System.out.println("Fazendo logout...");
                loggedUser = null; // Isso faz o laço voltar para o menu de autenticação
                break;
            default:
                System.out.println("Opcao inválida! Tente novamente.");
                break;
        }
    }

    private void listarDashboards() {
        List<Dashboard> dashboards = loggedUser.getDashboards();

        if (dashboards.isEmpty()) {
            System.out.println("\nVocê ainda não possui nenhum Dashboard.");
            return;
        }

        System.out.println("\n--- Seus Dashboards ---");
        for (int i = 0; i < dashboards.size(); i++) {
            System.out.println((i + 1) + " - " + dashboards.get(i).getTitle());
        }
    }

    private void criarDashboard() {
        System.out.print("\nDigite o título do novo Dashboard: ");
        String titulo = input.nextLine();

        Dashboard novoDash = new Dashboard(titulo);
        loggedUser.getDashboards().add(novoDash);

        database.saveUsers(usersList);

        System.out.println("Dashboard '" + titulo + "' criado com sucesso!");
    }

    private void excluirDashboard() {
        listarDashboards();

        if (loggedUser.getDashboards().isEmpty()) {
            return;
        }

        System.out.print("\nDigite o número do Dashboard que deseja excluir (ou 0 para cancelar): ");
        int index = Integer.parseInt(input.nextLine()) - 1;

        if (index == -1) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (index >= 0 && index < loggedUser.getDashboards().size()) {
            Dashboard dashRemovido = loggedUser.getDashboards().remove(index);
            database.saveUsers(usersList);
            System.out.println("Dashboard '" + dashRemovido.getTitle() + "' excluído com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private void acessarDashboard() {
        listarDashboards();

        if (loggedUser.getDashboards().isEmpty()) {
            return;
        }

        System.out.print("\nDigite o número do Dashboard que deseja acessar: ");
        int index = Integer.parseInt(input.nextLine()) - 1;

        if (index >= 0 && index < loggedUser.getDashboards().size()) {
            Dashboard dashboardSelecionado = loggedUser.getDashboards().get(index);
            System.out.println("Acessando '" + dashboardSelecionado.getTitle() + "'...");

            menuDoDashboard(dashboardSelecionado);
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private void menuDoDashboard(Dashboard dashboard) {

    }

}