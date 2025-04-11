package Entities;

import Abilities.Ability;
import ItemType.ItemEquipavel;
import DataStructure.Lista;
public class Personagem extends Entidade {

    private int xpAtual;
    private int xpTotal;

    private ItemEquipavel armaEquipada;
    private ItemEquipavel armaduraEquipada;


    public Personagem(Lista<Ability> habilidades, int manaAtual, int manaMaxima, int vidaAtual, int vidaMaxima, int nivel, String nome, int id, int xpAtual, int xpTotal) {
        super(habilidades, manaAtual, manaMaxima, vidaAtual, vidaMaxima, nivel, nome, id);
        this.xpAtual = xpAtual;
        this.xpTotal = xpTotal;
        
    }

    public void usarHabilidade(Entidade alvo, int opcao) {
        if(opcao == 1){
            alvo.receberDanos(getHabilidades().getHead().data.getDamage());
        }else if(opcao == 2){
            alvo.receberDanos(getHabilidades().getTail().data.getDamage());
        }else if(opcao != 1 && opcao != 2){
            System.out.printf("Opção invalida!\n");
        }
    }
    public int getXpAtual() {
        return xpAtual;
    }

    public void setXpAtual(int xpAtual) {
        this.xpAtual = xpAtual;
    }

    public int getXpTotal() {
        return xpTotal;
    }

    public void setXpTotal(int xpTotal) {
        this.xpTotal = xpTotal;
    }

    public ItemEquipavel getArma(){
        return armaEquipada;
    }

    public void setArma(ItemEquipavel arma){
        this.armaEquipada = arma;
    }

    public void setAramdura(ItemEquipavel armadura){
        this.armaduraEquipada = armadura;
    }

    public void equiparArma(ItemEquipavel arma) {
        if (arma.getTipo() == ItemEquipavel.TipoEquipamento.ARMA_FISICA) {
    
            this.armaEquipada = arma;
            System.out.println("Arma equipada com sucesso!");
        } else {
            System.out.println("Esse item não é uma arma.");
        }
    }



}
