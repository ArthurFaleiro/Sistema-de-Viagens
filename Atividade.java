public class Atividade {
    private int id;
    private String descricao;
    private double custoEstimado;
    private Viagem viagem;

    public Atividade(int id, String descricao, double custoEstimado, Viagem viagem) {
        this.id = id;
        this.descricao = descricao;
        this.custoEstimado = custoEstimado;
        this.viagem = viagem;
    }
    public String getDescricao() { return descricao; }
    public double getCustoEstimado() { return custoEstimado; }
    public Viagem getViagem() { return viagem; }
}
