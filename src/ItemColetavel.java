public abstract class ItemColetavel extends Item {
    private boolean empilhavel;
    private TipoColetavel tipo;

    public enum TipoColetavel{
        USAVEL, INGREDIENTE, QUEST_ITEM, DROP_COMUM
    }

    public ItemColetavel(int id, String nome, String desscricao, int nivelNecessario, Item.Raridade raridade, int preco,
            boolean empilhavel, ItemColetavel.TipoColetavel tipo) {
        super(id, nome, desscricao, nivelNecessario, raridade, preco);
        this.empilhavel = empilhavel;
        this.tipo = tipo;
    }

    public abstract void usar(Personagem personagem);
    
}
