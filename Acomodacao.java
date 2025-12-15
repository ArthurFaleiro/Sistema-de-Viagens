public class Acomodacao {
    private int id;
    private String nome;
    private double custoDiario;
    private int diasReservados;
    private Viagem viagem;

    public Acomodacao(int id, String nome, double custoDiario, int diasReservados, Viagem viagem) {
        this.id = id;
        this.nome = nome;
        this.custoDiario = custoDiario;
        this.diasReservados = diasReservados;
        this.viagem = viagem;
    }
    
    public double calcularCustoTotal() {
        return custoDiario * diasReservados;
    }
    
    public String getNome() { return nome; }
    public Viagem getViagem() { return viagem; }
}
