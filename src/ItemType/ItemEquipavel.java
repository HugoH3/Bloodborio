package ItemType;

import Entities.Personagem;

public abstract class ItemEquipavel extends Item {

    private int danoArma;
    private int defesa;
    private int critico;
    protected  TipoEquipamento tipo;

    public enum TipoEquipamento{
        ARMA_FISICA, ARMADURA
    }

    public ItemEquipavel(int id, String nome, String desscricao, int nivelNecessario, Item.Raridade raridade, int preco,
            int danoArma, int defesa, int critico, ItemEquipavel.TipoEquipamento tipo) {
        super(id, nome, desscricao, nivelNecessario, raridade, preco);
        this.danoArma = danoArma;
        this.defesa = defesa;
        this.critico = critico;
        this.tipo = tipo;
    }

    public abstract void equipar(Personagem personagem);

    public TipoEquipamento getTipo() {
        return tipo;
    }



    

    



    
}
