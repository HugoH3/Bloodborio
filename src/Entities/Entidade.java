package Entities;

import Abilities.Ability;
import Abilities.MeleeAttack;
import DataStructure.Lista;

public abstract class Entidade {
    private int id;
    public String nome;
    public int nivel;
    public int vidaMaxima;
    public int vidaAtual;
    public int manaMaxima;
    public int manaAtual;

    public Lista<Ability> habilidades;

    public Entidade(Lista<Ability> habilidades, int manaAtual, int manaMaxima, int vidaAtual, int vidaMaxima, int nivel, String nome, int id) {
        this.habilidades = new Lista<>();
        this.manaAtual = manaAtual;
        this.manaMaxima = manaMaxima;
        this.vidaAtual = vidaAtual;
        this.vidaMaxima = vidaMaxima;
        this.nivel = nivel;
        this.nome = nome;
        this.id = id;
    }

    public Entidade() {

    }

    public void receberDanos(int dano) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }
    }

    public void curar(int valor) {
        if (this.vidaAtual > this.vidaMaxima) {
            this.vidaAtual = this.vidaMaxima;
        }
    }

    public boolean estaVivo(){
        return this.vidaAtual > 0;
    }

    public void subirNivel(){
        this.nivel++;
        this.vidaMaxima += 10;
        this.manaMaxima += 10;
        this.vidaAtual = this.vidaMaxima;
        this.manaAtual = this.manaMaxima;
        System.out.printf(nome + " Subiu para o nível " + nivel + "!");
    }

    public void adicionarHabilidade(Ability habilidade) {
        habilidades.inserirHead(habilidade);
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


    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public Lista<Ability> getHabilidades() {
        return habilidades;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public void setManaMaxima(int manaMaxima) {
        this.manaMaxima = manaMaxima;
    }

    public void setManaAtual(int manaAtual) {
        this.manaAtual = manaAtual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public void setHabilidades(Lista<Ability> habilidades) {
        this.habilidades = new Lista<>();
    }
         
}