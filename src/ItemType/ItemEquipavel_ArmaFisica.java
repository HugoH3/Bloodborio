package ItemType;

import Entities.Personagem;

public class ItemEquipavel_ArmaFisica extends ItemEquipavel {

    public ItemEquipavel_ArmaFisica(int id, String nome, String desscricao, int nivelNecessario, Item.Raridade raridade,
            int preco, int danoArma, int defesa, int critico, ItemEquipavel.TipoEquipamento tipo, String tipoArma) {
        super(id, nome, desscricao, nivelNecessario, raridade, preco, danoArma, 0, critico, tipo);
        
    }

    @Override
    public void usar(Personagem personagem) {
        equipar(personagem);
    }

    @Override
    public void equipar(Personagem personagem){
        personagem.setArma(this);
        System.out.println(personagem.getNome() + " equipou " + this.getNome());
    }
     
}
