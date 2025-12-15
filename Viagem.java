import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Viagem {
    private int id;
    private String destino;
    private String dataInicio;
    private String dataFim;
    private Usuario usuario;
    private ItemBagagem[] bagagem; 
    private int countBagagem;

    public Viagem(int id, String destino, String dataInicio, String dataFim, Usuario usuario) {
        this.id = id;
        this.destino = destino;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuario = usuario;
        
        this.bagagem = new ItemBagagem[20];
        this.countBagagem = 0;
    }

    public long calcularDuracaoDias() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate d1 = LocalDate.parse(dataInicio, dtf);
            LocalDate d2 = LocalDate.parse(dataFim, dtf);
            return ChronoUnit.DAYS.between(d1, d2);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean adicionarItemBagagem(String desc) {
        if (countBagagem < bagagem.length) {
            bagagem[countBagagem] = new ItemBagagem(desc);
            countBagagem++;
            return true;
        }
        return false;
    }

    public void listarBagagem() {
        System.out.println("--- Bagagem para " + destino + " ---");
        if (countBagagem == 0) System.out.println("Mala vazia.");
        for (int i = 0; i < countBagagem; i++) {
            System.out.println(bagagem[i].toString());
        }
    }

    public int getId() { return id; }
    public String getDestino() { return destino; }
    public String getDataInicio() { return dataInicio; }
    public String getDataFim() { return dataFim; }
    public Usuario getUsuario() { return usuario; }
}