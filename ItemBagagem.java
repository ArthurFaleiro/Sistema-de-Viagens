public class ItemBagagem {
    private String descricao;
    private boolean check;

    public ItemBagagem(String descricao) {
        this.descricao = descricao;
        this.check = false;
    }
    public String getDescricao() { return descricao; }
    public String toString() { return "[ ] " + descricao; }
}
