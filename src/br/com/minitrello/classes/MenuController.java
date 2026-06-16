package br.com.minitrello.classes;

import br.com.minitrello.data.JsonDatabase;
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

        int option = readIntegerOption();

        switch (option) {
            case 1:
                login();
                break;
            case 2:
                createUser();
                break;
            case 3:
                running = false;
                break;
            default:
                System.out.println("Opcao inválida! Tente novamente.");
                break;
        }
    }

    private void login() {
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

    private void createUser() {
        String name = readRequiredString("Digite seu nome: ");
        String email = readRequiredString("Digite seu email: ");
        String password = readRequiredString("Digite sua senha: ");

        User newUser = new User(name, email, password);
        usersList.add(newUser);
        loggedUser = newUser;
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

        int option = readIntegerOption();

        switch (option) {
            case 1:
                showDashboard();
                break;
            case 2:
                enterDashboard();
                break;
            case 3:
                createDashboard();
                break;
            case 4:
                deleteDashboard();
                break;
            case 5:
                System.out.println("Fazendo logout...");
                loggedUser = null;
                break;
            default:
                System.out.println("Opcao inválida! Tente novamente.");
                break;
        }
    }

    private void showDashboard() {
        List<Dashboard> dashboards = loggedUser.getDashboards();

        if (dashboards.isEmpty()) {
            System.out.println("\nVocê ainda não possui nenhum Dashboard.");
            return;
        }

        System.out.println("\n--- Seus Dashboards ---");
        for (int i = 0; i < dashboards.size(); i++) {
            System.out.println((i) + " - " + dashboards.get(i).getTitle());
        }
    }

    private void createDashboard() {
        String title = readRequiredString("\nDigite o título do novo Dashboard: ");

        Dashboard newDash = new Dashboard(title);
        loggedUser.getDashboards().add(newDash);
        database.saveUsers(usersList);

        System.out.println("Dashboard '" + title + "' criado com sucesso!");
    }

    private void deleteDashboard() {
        showDashboard();

        if (loggedUser.getDashboards().isEmpty()) {
            return;
        }

        System.out.print("\nDigite o número do Dashboard que deseja excluir (ou -1 para cancelar): ");
        int index = readIntegerOption();

        if (index == -1) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (index >= 0 && index < loggedUser.getDashboards().size()) {
            Dashboard deletedDash = loggedUser.getDashboards().remove(index);
            database.saveUsers(usersList);
            System.out.println("Dashboard '" + deletedDash.getTitle() + "' excluído com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private void enterDashboard() {
        showDashboard();

        if (loggedUser.getDashboards().isEmpty()) {
            return;
        }

        System.out.print("\nDigite o número do Dashboard que deseja acessar: ");
        int index = readIntegerOption();

        if (index >= 0 && index < loggedUser.getDashboards().size()) {
            Dashboard selectedDash = loggedUser.getDashboards().get(index);
            System.out.println("Acessando '" + selectedDash.getTitle() + "'...");

            dashboardMenu(selectedDash);
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private void dashboardMenu(Dashboard dashboard) {
        boolean inDashboard = true;

        while (inDashboard) {
            System.out.println("\n==== Dashboard: " + dashboard.getTitle() + " ====");
            System.out.println("1 - Ver Colunas e Cards");
            System.out.println("2 - Criar Coluna");
            System.out.println("3 - Criar Card");
            System.out.println("4 - Deletar Coluna");
            System.out.println("5 - Deletar Card");
            System.out.println("6 - Editar Card");
            System.out.println("7 - Editar Coluna");
            System.out.println("8 - Alterar Título do Dashboard");
            System.out.println("9 - Mover Card");
            System.out.println("10 - Voltar ao Menu Principal");
            System.out.print("Escolha sua opcao: ");

            int option = readIntegerOption();

            switch (option) {
                case 1:
                    showColumns(dashboard);
                    break;
                case 2:
                    createColumn(dashboard);
                    break;
                case 3:
                    createCard(dashboard);
                    break;
                case 4:
                    deleteColumn(dashboard);
                    break;
                case 5:
                    deleteCard(dashboard);
                    break;
                case 6:
                    editCard(dashboard);
                    break;
                case 7:
                    editColumn(dashboard);
                    break;
                case 8:
                    editDashboardTitle(dashboard);
                    break;
                case 9:
                    moveCardColumn(dashboard);
                    break;
                case 10:
                    inDashboard = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void moveCardColumn(Dashboard d) {
        if (d.getColumns().isEmpty()) {
            System.out.println("\nEste dashboard não possui colunas.");
            return;
        }

        showColumns(d);

        System.out.print("\nDe qual coluna você quer mover o card? (Digite o número da coluna de origem): ");
        int originIndex = Integer.parseInt(input.nextLine());

        if (originIndex < 0 || originIndex >= d.getColumns().size()) {
            System.out.println("Coluna de origem inválida.");
            return;
        }

        Column originColumn = d.getColumns().get(originIndex);

        if (originColumn.getCards().isEmpty()) {
            System.out.println("A coluna '" + originColumn.getTitle() + "' não possui cards para mover.");
            return;
        }

        System.out.println("\n--- Cards na Coluna '" + originColumn.getTitle() + "' ---");
        for (int i = 0; i < originColumn.getCards().size(); i++) {
            System.out.println(i + " - " + originColumn.getCards().get(i).getTitle());
        }

        System.out.print("Digite o número do Card a ser movido: ");
        int cardIndex = Integer.parseInt(input.nextLine());

        if (cardIndex < 0 || cardIndex >= originColumn.getCards().size()) {
            System.out.println("Card inválido.");
            return;
        }

        Card cardToMove = originColumn.getCards().get(cardIndex);

        System.out.print("\nPara qual coluna deseja mover o card? (Digite o número da coluna de destino): ");
        int destinyIndex = Integer.parseInt(input.nextLine());

        if (destinyIndex < 0 || destinyIndex >= d.getColumns().size()) {
            System.out.println("Coluna de destino inválida.");
            return;
        }

        Column destinyColumn = d.getColumns().get(destinyIndex);

        if (originColumn.equals(destinyColumn)) {
            System.out.println("O card já está nesta coluna.");
            return;
        }

        d.changeCardColumn(cardToMove, originColumn, destinyColumn);

        database.saveUsers(usersList);

        System.out.println("Card '" + cardToMove.getTitle() + "' movido com sucesso para a coluna '" + destinyColumn.getTitle() + "'!");
    }

    private void showColumns(Dashboard d) {
        if (d.getColumns().isEmpty()) {
            System.out.println("\nEste dashboard ainda não possui colunas.");
            return;
        }
        System.out.println("\n--- Colunas ---");
        for (int i = 0; i < d.getColumns().size(); i++) {
            Column col = d.getColumns().get(i);
            System.out.println(i + " - [Coluna] " + col.getTitle());
            for (int j = 0; j < col.getCards().size(); j++) {
                System.out.println("    Card " + j + ": " + col.getCards().get(j).toString());
            }
        }
    }

    private void createColumn(Dashboard d) {
        String name = readRequiredString("Informe o nome da coluna: ");

        Column newColumn = new Column(name);
        d.addColumn(newColumn);
        database.saveUsers(usersList);

        System.out.println("Coluna '" + newColumn.getTitle() + "' adicionada com sucesso!");
    }

    private void createCard(Dashboard d) {
        if (d.getColumns().isEmpty()) {
            System.out.println("Você não tem colunas! Um Card não pode existir sem uma coluna.");
            return;
        }

        showColumns(d);
        System.out.print("\nEscolha a coluna em que o Card será criado (digite apenas o número): ");
        int index = Integer.parseInt(input.nextLine());

        if (index >= 0 && index < d.getColumns().size()) {

            String name = readRequiredString("Digite o nome do Card: ");
            String description = readRequiredString("Digite a descricao do Card: ");

            Priority.showPrioritys();
            System.out.print("Escolha a prioridade do Card: ");
            int option = Integer.parseInt(input.nextLine());
            Priority tag = Priority.NORMAL;

            switch (option) {
                case 1: tag = Priority.BAIXA; break;
                case 2: tag = Priority.NORMAL; break;
                case 3: tag = Priority.ALTA; break;
                case 4: tag = Priority.URGENTE; break;
                default:
                    System.out.println("Opção de prioridade inválida. Definido como NORMAL.");
                    break;
            }

            Card newCard = new Card(name, description, loggedUser, tag);
            d.getColumns().get(index).addCard(newCard);

            database.saveUsers(usersList);
            System.out.println("Card adicionado com sucesso!");
        } else {
            System.out.println("Coluna não encontrada.");
        }
    }

    private void deleteColumn(Dashboard d) {
        if (d.getColumns().isEmpty()) {
            System.out.println("Não há colunas para deletar.");
            return;
        }

        showColumns(d);
        System.out.print("\nDigite o número da Coluna que deseja excluir (Isso excluirá todos os cards nela): ");
        int index = readIntegerOption();

        if (index >= 0 && index < d.getColumns().size()) {
            d.getColumns().remove(index);
            database.saveUsers(usersList);
            System.out.println("Coluna removida com sucesso.");
        } else {
            System.out.println("Coluna inválida.");
        }
    }

    private void deleteCard(Dashboard d) {
        if (d.getColumns().isEmpty()) {
            System.out.println("Não há colunas/cards.");
            return;
        }

        showColumns(d);
        System.out.print("\nDe qual coluna você quer excluir um card? (Digite o número da coluna): ");
        int colIndex = readIntegerOption();

        if (colIndex >= 0 && colIndex < d.getColumns().size()) {
            Column col = d.getColumns().get(colIndex);
            if (col.getCards().isEmpty()) {
                System.out.println("Esta coluna não possui cards.");
                return;
            }

            System.out.print("Digite o número do Card a ser excluído: ");
            int cardIndex = readIntegerOption();

            if (cardIndex >= 0 && cardIndex < col.getCards().size()) {
                col.getCards().remove(cardIndex);
                database.saveUsers(usersList);
                System.out.println("Card removido com sucesso.");
            } else {
                System.out.println("Card inválido.");
            }
        } else {
            System.out.println("Coluna inválida.");
        }
    }

    private void editCard(Dashboard d) {
        if (d.getColumns().isEmpty()) return;

        showColumns(d);
        System.out.print("\nDe qual coluna é o card que deseja editar? ");
        int colIndex = readIntegerOption();

        if (colIndex >= 0 && colIndex < d.getColumns().size()) {
            Column col = d.getColumns().get(colIndex);
            System.out.print("Digite o número do Card a ser editado: ");
            int cardIndex = readIntegerOption();

            if (cardIndex >= 0 && cardIndex < col.getCards().size()) {
                Card card = col.getCards().get(cardIndex);

                System.out.print("Novo título (ou Enter para manter): ");
                String novoTitulo = input.nextLine();
                if (!novoTitulo.isEmpty()) card.setTitle(novoTitulo);

                System.out.print("Nova descrição (ou Enter para manter): ");
                String novaDescricao = input.nextLine();
                if (!novaDescricao.isEmpty()) card.setDescription(novaDescricao);

                database.saveUsers(usersList);
                System.out.println("Card atualizado!");
            }
        }
    }

    private void editColumn(Dashboard d) {
        if (d.getColumns().isEmpty()) return;

        showColumns(d);
        System.out.print("\nDigite o número da coluna a ser editada: ");
        int index = readIntegerOption();

        if (index >= 0 && index < d.getColumns().size()) {
            System.out.print("Novo título da coluna: ");
            String novoTitulo = input.nextLine();
            d.getColumns().get(index).setTitle(novoTitulo);

            database.saveUsers(usersList);
            System.out.println("Coluna atualizada!");
        }
    }

    private void editDashboardTitle(Dashboard d) {
        System.out.print("\nNovo título para o Dashboard: ");
        String novoTitulo = input.nextLine();

        if (!novoTitulo.isEmpty()) {
            d.setTitle(novoTitulo);
            database.saveUsers(usersList);
            System.out.println("Título alterado com sucesso!");
        }
    }

    private int readIntegerOption() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Por favor, digite apenas números: ");
            }
        }
    }

    private String readRequiredString(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            // Lemos o texto e usamos o .trim() para remover espaços em branco invisíveis
            String valor = input.nextLine().trim();

            if (!valor.isEmpty()) {
                return valor; // Se não estiver vazio, devolve o texto e sai do laço
            }

            // Se estiver vazio, exibe a mensagem de erro e o laço repete
            System.out.println("Erro: Este campo é obrigatório e não pode ficar em branco.");
        }
    }

}