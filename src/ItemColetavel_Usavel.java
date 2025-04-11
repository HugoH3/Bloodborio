public class ItemColetavel_Usavel extends ItemColetavel{

    private String efeito;

    public ItemColetavel_Usavel(int id, String nome, String desscricao, int nivelNecessario, Item.Raridade raridade,
            int preco, boolean empilhavel, ItemColetavel.TipoColetavel tipo, String efeito) {
        super(id, nome, desscricao, nivelNecessario, raridade, preco, empilhavel, tipo);
        this.efeito = efeito;
    }

    @Override 
    public void usar(Personagem personagem){
        System.out.println("Usando:" + efeito);
    }
    
}
