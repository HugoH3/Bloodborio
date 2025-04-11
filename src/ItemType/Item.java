package ItemType;

import Entities.Personagem;

public abstract class Item {
    private int id;
    private String nome;
    private String descricao;
    private int nivelNecessario;
    private Raridade raridade; 
    private int preco;

    public enum Raridade{
        COMUM, INCOMUN, RARO, ÉPICO, LENDÁRIO
    }

    public Item(int id, String nome, String descricao, int nivelNecessario, Item.Raridade raridade, int preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nivelNecessario = nivelNecessario;
        this.raridade = raridade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDesscricao() {
        return descricao;
    }

    public int getNivelNecessario() {
        return nivelNecessario;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public int getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNivelNecessario(int nivelNecessario) {
        this.nivelNecessario = nivelNecessario;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }


    public abstract void usar(Personagem personagem);

}
