## Mini Trello

Sistema de gerenciamento de tarefas baseado no método Kanban, com execução interativa via terminal. O projeto foi desenvolvido em Java, aplicando conceitos de Programação Orientada a Objetos (POO) e persistência de dados local em formato JSON.
Funcionalidades

- Autenticação: Criação de conta e sistema de login de usuários.

- Dashboards: Criação, listagem, edição de título e exclusão de painéis de trabalho individuais por usuário.

- Colunas e Cards: Adição, edição e remoção de colunas e tarefas (cards).

- Movimentação: Transferência de cards entre diferentes colunas dentro de um dashboard.

- Prioridades: Classificação de tarefas utilizando enumerações (Baixa, Normal, Alta e Urgente).

- Persistência de Dados: Salvamento automático de todas as alterações e estado do sistema no arquivo database.json.

### Tecnologias

- Java 21

- Gson 2.14.0 (para serialização e desserialização JSON)

### Como Executar

- Certifique-se de ter o JDK 21 ou superior instalado em sua máquina.

- Clone o repositório do projeto.

- Adicione o arquivo da biblioteca gson-2.14.0.jar ao Build Path / Libraries da sua IDE.

- Execute o arquivo Main.java para iniciar o sistema no console.

- O arquivo database.json será gerado automaticamente na raiz do projeto na primeira execução ou registro de usuário.

### Arquitetura

O código fonte está dividido para separar a interação do usuário das regras de negócio e do banco de dados:

- MenuController: Gerencia a entrada de dados via terminal, validações e navegação.

- JsonDatabase: Responsável exclusivo pela leitura e gravação no arquivo JSON.

- Modelos: Classes User, Dashboard, Column e Card contendo a estrutura de dados e as lógicas internas do Kanban.

Autor: Iure Fernando Xavier - Engenharia de Software (Univille)