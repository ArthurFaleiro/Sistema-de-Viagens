public class Orcamento {
    private Viagem viagem;
    private double totalGasto;

    public Orcamento(Viagem viagem) {
        this.viagem = viagem;
        this.totalGasto = 0.0;
    }

    public void adicionarCusto(double valor) {
        this.totalGasto += valor;
    }

    public double getTotalGasto() {
        return totalGasto;
    }
}
