import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(Scanner scanner) throws ParseException {
        System.out.print("Id do livro: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Título do livro: ");
        String tituloLivro = scanner.nextLine();

        for (Livro livro : this.livros) {
            if (Objects.equals(livro.getId(), idLivro)) {
                System.out.println("\n❌ Já existe um livro com esse Id. Operação cancelada.");
                return;
            }

            if (Objects.equals(livro.getTitulo(), tituloLivro)) {
                System.out.println("\n❌ Já existe um livro com esse Título. Operação cancelada.");
                return;
            }
        }

        System.out.print("Id do Autor: ");
        int idAutor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome do Autor: ");
        String nomeAutor = scanner.nextLine();

        System.out.print("Data de nascimento do Autor (dd/MM/yyyy: ");
        String dateStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(dateStr);

        Autor autor = new Autor(idAutor, nomeAutor, date);
        this.autores.add(autor);

        Livro novoLivro = new Livro(idLivro, tituloLivro, autor);
        this.livros.add(novoLivro);

        System.out.printf("\n✅ Livro cadastrado com sucesso: %n%s%n", novoLivro.toString());
    }

    public void excluirLivro(Scanner scanner) {
        System.out.print("Id do livro a ser excluído: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();

        for (Livro livro : this.livros) {
            if (Objects.equals(livro.getId(), idLivro)) {
                this.livros.remove(livro);
                System.out.println("\n✅ Livro excluído com sucesso.");
                return;
            }
        }

        System.out.println("\n❌ Livro não encontrado. Operação cancelada.");
    }

    public void listarAcervo() {
        if (this.livros.isEmpty()) {
            System.out.println("❌ Nenhum livro cadastrado na biblioteca.");
            return;
        }

        System.out.println("===== ACERVO DA BIBLIOTECA =====");
        this.livros.forEach(livro -> System.out.println(livro.toString()));
    }

    public void emprestarLivro(Scanner scanner) {
        System.out.print("Gostaria de listar os livros disponíveis? (S/N): ");
        String listar = scanner.nextLine().toUpperCase();

        if (listar.equals("S")) {
            listarAcervo();
        }

        System.out.print("Id do livro a ser emprestado: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();

        for (Livro livro : this.livros) {
            if (Objects.equals(livro.getId(), idLivro)) {
                if (!livro.isDisponivel()) {
                    System.out.println("\n❌ Livro indisponível para empréstimo.");
                    return;
                }

                System.out.print("Nome do cliente: ");
                String nomeCliente = scanner.nextLine();

                Emprestimo emprestimo = new Emprestimo(this.emprestimos.size() + 1, livro, nomeCliente);
                this.emprestimos.add(emprestimo);
                livro.setDisponivel(false);

                System.out.printf("\n✅ Livro emprestado com sucesso: %n%s%n", emprestimo.toString());
                return;
            }
        }
    }

    public void devolverLivro(Scanner scanner) {
        System.out.print("Gostaria de listar os empréstimos ativos? (S/N): ");
        String listar = scanner.nextLine().toUpperCase();

        if (listar.equals("S")) {
            listarEmprestimosAtivos();
        }

        System.out.print("Id do empréstimo a ser devolvido: ");
        int idEmprestimo = scanner.nextInt();
        scanner.nextLine();

        for (Emprestimo emprestimo : this.emprestimos) {
            if (Objects.equals(emprestimo.getId(), idEmprestimo)) {
                if (emprestimo.getDataDevolucao() != null) {
                    System.out.println("\n❌ Empréstimo já foi devolvido.");
                    return;
                }

                emprestimo.setDataDevolucao(new Date());
                emprestimo.getLivro().setDisponivel(true);

                System.out.printf("\n✅ Livro devolvido com sucesso: %n%s%n", emprestimo.toString());
                return;
            }
        }

        System.out.println("\n❌ Empréstimo não encontrado. Operação cancelada.");
    }

    public void listarEmprestimosAtivos() {
        if (this.emprestimos.isEmpty()) {
            System.out.println("\n❌ Nenhum empréstimo ativo registrado na biblioteca.");
            return;
        }

        System.out.println("===== EMPRÉSTIMOS DA BIBLIOTECA =====");
        this.emprestimos.forEach(emprestimo -> {
            if (emprestimo.getDataDevolucao() == null) {
                System.out.println(emprestimo.toString());
            }
        });
    }
}
