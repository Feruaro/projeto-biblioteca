import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int OPCAO_SAIR = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca();
        int opcao;

        try {
            do {
                exibirMenu();
                opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n-------------------------------\n");
                switch (opcao) {
                    case 1 -> biblioteca.cadastrarLivro(scanner);
                    case 2 -> System.out.println("❌ Opção inválida. Tente novamente.");
                    case 3 -> System.out.println("❌ Opção inválida. Tente novamente.");
                    case 4 -> System.out.println("❌ Opção inválida. Tente novamente.");
                    case 5 -> System.out.println("❌ Opção inválida. Tente novamente.");
                    case 6 -> System.out.println("❌ Opção inválida. Tente novamente.");
                    case 7 -> System.out.println("❌ Opção inválida. Tente novamente.");
                    case OPCAO_SAIR -> System.out.println("✅ Obrigado por usar o sistema da Biblioteca!");
                    default -> System.out.println("❌ Opção inválida. Tente novamente.");
                }
                System.out.println("\n-------------------------------\n");
            } while (opcao != OPCAO_SAIR);
        } catch (ParseException e) {
            System.out.println("\n-------------------------------\n");
            System.out.println("❌ Data inválida. Operação cancelada.");
            System.out.println("\n-------------------------------\n");
        } finally {
            System.out.println("\n-------------------------------\n");
            System.out.println("Saindo...");
            System.out.println("\n-------------------------------\n");
            scanner.close();
        }
    }

    private static void exibirMenu() {
        System.out.println("===== BIBLIOTECA - MENU =====");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Alterar livro");
        System.out.println("3. Pesquisar livro");
        System.out.println("4. Excluir livro");
        System.out.println("5. Listar acervo");
        System.out.println("6. Emprestar livro");
        System.out.println("7. Devolver livro");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
