import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(Scanner scanner) throws ParseException {
        System.out.print("Id do livro: ");
        Long idLivro = scanner.nextLong();
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
        Long idAutor = scanner.nextLong();
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
}
