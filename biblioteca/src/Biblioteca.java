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

        System.out.print("Nome do Autor: ");
        String nomeAutor = scanner.nextLine();

        System.out.print("Data de nascimento do Autor (dd/MM/yyyy: ");
        String dateStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(dateStr);

        int idAutor = buscarIdAutor(nomeAutor);

        Autor autor = new Autor(idAutor, nomeAutor, date);

        if (idAutor > this.autores.size()) this.autores.add(autor);

        Livro novoLivro = new Livro(idLivro, tituloLivro, autor);
        this.livros.add(novoLivro);

        System.out.printf("\n✅ Livro cadastrado com sucesso: %n%s%n", novoLivro.toString());
    }

    private int buscarIdAutor(String nomeAutor) {
        for (Autor autor : this.autores) {
            if (Objects.equals(autor.getNome(), nomeAutor)) {
                return autor.getId();
            }
        }

        return this.autores.size() + 1;
    }

    public void pesquisarLivro(Scanner scanner) {
        System.out.print("Escolha a opção que deseja pesquisar: ");
        System.out.println("\n1. Por Id");
        System.out.println("2. Por Título");
        System.out.println("3. Por nome do Autor");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        List<Livro> livrosPesquisado = new ArrayList<>();

        switch (opcao) {
            case 1 -> livrosPesquisado = pesquisarLivroPorId(scanner);
            case 2 -> livrosPesquisado = pesquisarLivroPorTitulo(scanner);
            case 3 -> livrosPesquisado = pesquisarLivroPorNomeAutor(scanner);
            default -> System.out.println("\n❌ Opção inválida. Operação cancelada.");
        }

        if (livrosPesquisado.isEmpty()) {
            System.out.println("\n❌ Livro não encontrado.");
            return;
        }

        System.out.println("\n✅ Livro(s) encontrado(s):");
        livrosPesquisado.forEach(livro -> System.out.println(livro.toString()));
    }

    private List<Livro> pesquisarLivroPorId(Scanner scanner) {
        System.out.print("Id do livro: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();

        return this.livros.stream().filter(l -> Objects.equals(l.getId(), idLivro)).toList();
    }

    private List<Livro> pesquisarLivroPorTitulo(Scanner scanner) {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        return this.livros.stream().filter(l -> Objects.equals(l.getTitulo(), titulo)).toList();
    }

    private List<Livro> pesquisarLivroPorNomeAutor(Scanner scanner) {
        System.out.print("Nome do autor: ");
        String nomeAutor = scanner.nextLine();

        return this.livros.stream().filter(l -> Objects.equals(l.getAutor().getNome(), nomeAutor)).toList();
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

        System.out.print("\nId do livro a ser emprestado: ");
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
                livro.setDataAtualizacao(new Date());

                System.out.printf("\n✅ Livro emprestado com sucesso: %n%s%n", emprestimo.toString());
                return;
            }
        }
    }

    public void devolverLivro(Scanner scanner) {
        System.out.print("Gostaria de listar os empréstimos ativos? (S/N): ");
        String listar = scanner.nextLine().toUpperCase();

        if (listar.equals("S")) {
            listarEmprestimos(true);
        }

        System.out.print("\nId do empréstimo a ser devolvido: ");
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
                emprestimo.getLivro().setDataAtualizacao(new Date());

                System.out.printf("\n✅ Livro devolvido com sucesso: %n%s%n", emprestimo.toString());
                return;
            }
        }

        System.out.println("\n❌ Empréstimo não encontrado. Operação cancelada.");
    }

    public void listarEmprestimos(boolean ativos) {
        if (this.emprestimos.isEmpty()) {
            System.out.println("\n❌ Nenhum empréstimo ativo registrado na biblioteca.");
            return;
        }

        System.out.println("===== EMPRÉSTIMOS DA BIBLIOTECA =====");
        this.emprestimos.forEach(emprestimo -> {
            if (!ativos || emprestimo.getDataDevolucao() == null) {
                System.out.println(emprestimo.toString());
            }
        });
    }
}
