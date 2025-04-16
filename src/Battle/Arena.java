package Battle;

import DataStructure.Fila;
import DataStructure.Lista;
import DataStructure.Node;
import DataStructure.Pilha;
import Entities.Entidade;
import Entities.Personagem;

import java.util.Scanner;

public class Arena {
    private int idBatalha;
    private Lista<Entidade> listaDeParticipantes;
    private Fila<Entidade> filaTurnos;
    private Pilha<Entidade> historicoEliminados;
    private int turnoAtual;
    private boolean emAndamento;

    public Arena(int idBatalha, Lista<Entidade> listaDeParticipantes, Fila<Entidade> filaTurnos, Pilha<Entidade> historicoEliminados) {
        this.idBatalha = idBatalha;
        this.listaDeParticipantes = listaDeParticipantes;
        this.filaTurnos = filaTurnos;
        this.historicoEliminados = historicoEliminados;
        this.turnoAtual = 1;
        this.emAndamento = false;
    }

    public void adicionarParticipante(Entidade entidade) {
        listaDeParticipantes.inserirHead(entidade);
    }

    public void iniciarBatalha() {
        System.out.println(" Batalha #" + idBatalha + " iniciada!");
        Node<Entidade> atual = listaDeParticipantes.getHead();
        while (atual != null) {
            if (atual.getData().estaVivo()) {
                filaTurnos.add(atual.getData());
            }
            atual = atual.getNext();
        }
        emAndamento = true;
    }

    public void executarTurno() {




        if (!emAndamento || filaTurnos.isEmpty()) {
            System.out.println("Batalha encerrada ou sem combatentes.");
            return;
        }

        System.out.println("\n=== Turno " + turnoAtual + " ===");

        Entidade atual = filaTurnos.remove();

        if (atual.estaVivo()) {
            Entidade alvo = escolherAlvo(atual);

            if (alvo == null || !alvo.estaVivo()) {
                System.out.println("⚠ Nenhum alvo válido disponível.");
                verificarVencedor();
                return;
            }

            if (atual instanceof Personagem) {
                mostrarStatus((Personagem) atual);

                Scanner scanner = new Scanner(System.in);
                int opcao = scanner.nextInt();

                ((Personagem) atual).usarHabilidade(alvo, opcao);

            } else {
                // Monstro ataca automático com primeira habilidade
                atual.usarHabilidade(alvo, 1);
            }

            if (!alvo.estaVivo()) {
                System.out.println(" " + alvo.getNome() + " foi derrotado!");
                historicoEliminados.push(alvo);
            }

            if (atual.estaVivo()) {
                filaTurnos.add(atual);
            } else {
                System.out.println(" " + atual.getNome() + " foi derrotado!");
                historicoEliminados.push(atual);
            }

            turnoAtual++;
            verificarVencedor();
        }
    }

    private void mostrarStatus(Personagem p) {
        System.out.println("\n Status de " + p.getNome() + ":");
        System.out.println("Vida: " + p.getVidaAtual());
        System.out.println("Mana: " + p.getManaAtual());

    }

    private Entidade escolherAlvo(Entidade atacante) {
        Node<Entidade> atual = listaDeParticipantes.getHead();
        while (atual != null) {
            Entidade alvo = atual.getData();
            if (!alvo.equals(atacante) && alvo.estaVivo()) {
                return alvo;
            }
            atual = atual.getNext();
        }
        return null;
    }

    public void verificarVencedor() {
        int vivos = 0;
        Entidade ultimoVivo = null;

        Node<Entidade> atual = listaDeParticipantes.getHead();
        while (atual != null) {
            Entidade e = atual.getData();
            if (e.estaVivo()) {
                vivos++;
                ultimoVivo = e;
            }
            atual = atual.getNext();
        }

        if (vivos <= 1) {
            emAndamento = false;
            if (ultimoVivo != null) {
                historicoEliminados.push(ultimoVivo);
                System.out.println(" Vencedor: " + ultimoVivo.getNome());
            } else {
                System.out.println("⚔ Todos foram derrotados!");
            }
            exibirRankingFinal();
        }
    }

    public void exibirRankingFinal() {
        System.out.println("\n===  Ranking Final ===");
        int pos = historicoEliminados.size();
        while (!historicoEliminados.isEmpty()) {
            Entidade e = historicoEliminados.pop();
            System.out.println(pos + "º lugar: " + e.getNome());
            pos++;
        }
    }
}
