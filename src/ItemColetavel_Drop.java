public class ItemColetavel_Drop extends ItemColetavel{

    private String origemDrop;

    public ItemColetavel_Drop(int id, String nome, String desscricao, int nivelNecessario, Item.Raridade raridade,
            int preco, boolean empilhavel, ItemColetavel.TipoColetavel tipo, String origemDrop) {
        super(id, nome, desscricao, nivelNecessario, raridade, preco, empilhavel, tipo);
        this.origemDrop = origemDrop;
    }

    @Override
    public void usar(Personagem personagem){
        System.out.println("Este item nao pode ser usado diretamente");
    }
    
    
}
