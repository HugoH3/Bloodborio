package ItemType;

import Entities.Personagem;

public class ItemEquipavel_Armadura extends ItemEquipavel {

    public ItemEquipavel_Armadura(int id, String nome, String desscricao, int nivelNecessario, Raridade raridade, int preco, int danoArma, int defesa, int critico, TipoEquipamento tipo) {
        super(id, nome, desscricao, nivelNecessario, raridade, preco, 0, defesa, critico, tipo);
    }

    @Override
    public void usar(Personagem personagem){
        equipar(personagem);
    }

    @Override 
    public void equipar(Personagem personagem){
        personagem.setAramdura(this);
        System.out.println(personagem.getNome() + " equipou a armadura " + this.getNome());
    }


    
}
