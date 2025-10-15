import java.util.Date;

public class Emprestimo {

    int id;
    Livro livro;
    String nomeCliente;
    Date dataEmprestimo;
    Date dataDevolucao;

    public Emprestimo(int id, Livro livro, String nomeCliente) {
        this.id = id;
        this.livro = livro;
        this.nomeCliente = nomeCliente;
        this.dataEmprestimo = new Date();
        this.dataDevolucao = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return String.format("ID: %d%nLivro: %s%nCliente: %s%nData Empréstimo: %s%nData Devolução: %s",
                this.id,
                this.livro.getTitulo(),
                this.nomeCliente,
                this.dataEmprestimo,
                this.dataDevolucao != null ? this.dataDevolucao : "Não devolvido");
    }
}
