package Battle;
import Abilities.MagicalAttack;
import Abilities.MeleeAttack;
import DataStructure.Fila;
import DataStructure.Lista;
import DataStructure.Pilha;
import Entities.Entidade;
import Entities.Jogador;
import Entities.Monster;
import Entities.Personagem;
import ItemType.ArmaFisicaItem;
import ItemType.Item;
import ItemType.ItemNarrativo;
import ItemType.PergaminhoDeMagia;

import java.util.Scanner;

public class FlorestaManager {

    public static void explorarFloresta(Personagem personagem, Jogador jogador, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n🌲 Você entra mais fundo na floresta...");

            Monster monstro = gerarMonstroAleatorio();
            System.out.println("⚔ Um " + monstro.getNome() + " apareceu!");

            Lista<Entidade> lista = new Lista<>();
            Fila<Entidade> fila = new Fila<>();
            Pilha<Entidade> pilha = new Pilha<>(30);
            Arena arena = new Arena(1, lista, fila, pilha);

            arena.adicionarParticipante(personagem);
            arena.adicionarParticipante(monstro);
            arena.iniciarBatalha();

            while (personagem.estaVivo() && monstro.estaVivo()) {
                arena.executarTurno();
            }

            if (!personagem.estaVivo()) {
                System.out.println("☠ Você foi derrotado... seu personagem foi removido.");
                jogador.removerPersonagem(personagem);
                return;
            }

            if (Math.random() < 0.7) {
                Item drop = gerarItemAleatorio();
                personagem.adicionarItem(drop);
                System.out.println("Você encontrou um item em meio os escombros: " + drop.getNome());
            }

            personagem.ganharExperiencia(20);

            System.out.println("\nSerá que devo continuar em Dormar?");
            System.out.println("1 - Continuar");
            System.out.println("2 - Sair");
            System.out.print("> ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 2) {
                continuar = false;
                System.out.println("As vezes só sobreviver é um dadiva.");
            }
        }
    }

    public static Monster gerarMonstroAleatorio() {
        int escolha = (int)(Math.random() * 4); // agora temos 4 monstros
        Monster m;

        switch (escolha) {
            case 0:
                m = new Monster(null, 20, 20, 25, 15, 25, "Lurker", 6, 15);
                m.adicionarHabilidade(new MeleeAttack("Toque Silencioso", 10, 2, 3));
                break;

            case 1:
                m = new Monster(null, 0, 0, 22, 18, 25, "Seeker", 7, 15);
                m.adicionarHabilidade(new MeleeAttack("Perfuração Mental", 12, 3, 4));
                break;

            case 2:
                m = new Monster(null, 10, 10, 18, 22, 20, "Watcher", 8, 15);
                m.adicionarHabilidade(new MagicalAttack("Olhar Infinito", 11, 6, 5));
                break;

            case 3:
            default:
                m = new Monster(null, 15, 15, 28, 25, 30, "Devourer", 9, 20);
                m.adicionarHabilidade(new MagicalAttack("Dissolução Abissal", 16, 8, 6));
                break;
        }

        return m;
    }


    public static Item gerarItemAleatorio() {
        double chance = Math.random();

        if (chance < 0.5) {
            // 50% - Itens comuns
            int tipo = (int)(Math.random() * 3);
            switch (tipo) {
                case 0: return new Item("Liquido Viscoso", "cura", 30);
                case 1: return new Item("Garrafa ensanguentada", "mana", 20);
                case 2: return new Item("Gosma Preta", "vigor", 25);
            }

        } else if (chance < 0.75) {
            // 25% - Magias via pergaminho
            return gerarPergaminhoAleatorio();

        } else if (chance < 0.95) {
            // 20% - Armas físicas
            return gerarArmaFisicaAleatoria();

        } else {
            // 5% - Lore narrativa
            return gerarPergaminhoNarrativoAleatorio();
        }

        return null;
    }

    public static Item gerarArmaFisicaAleatoria() {
        int roleta = (int)(Math.random() * 100); // de 0 a 99

        if (roleta < 2) {
            // 2% de chance - Espada Lendária
            return new ArmaFisicaItem("Espada Lendária", new MeleeAttack("Corte Lendário", 45, 0, 10));
        } else {
            int tipo = (int)(Math.random() * 4);
            switch (tipo) {
                case 0: return new ArmaFisicaItem("Espada Sagrada", new MeleeAttack("Espadada Sagrada", 25, 0, 5));
                case 1: return new ArmaFisicaItem("Espada das Sombras", new MeleeAttack("Corte Sombrio", 28, 0, 6));
                case 2: return new ArmaFisicaItem("Espada Mágica", new MeleeAttack("Corte Arcano", 22, 0, 4));
                case 3: return new ArmaFisicaItem("Machado Rúnico", new MeleeAttack("Machadada Brutal", 30, 0, 8));
                default: return null;
            }
        }
    }
    public static Item gerarPergaminhoNarrativoAleatorio() {
        int i = (int)(Math.random() * 8);

        switch (i) {
            case 0: return new ItemNarrativo("Pergaminho: Dormar", new String[]{
                    "Dormar não foi construído. Dormar emergiu.",
                    "Suas torres não tocam os céus — elas os rasgam.",
                    "Dentro de seus salões, o tempo geme.",
                    "Quem entra, deixa para trás a linearidade.",
                    "Os ecos do passado vivem mais que seus visitantes."
            }, "Dormar");

            case 1: return new ItemNarrativo("Pergaminho: Lurker", new String[]{
                    "O Lurker não caça. Ele espera.",
                    "Sob os escombros, sob a carne, sob o sono.",
                    "Não tema os passos, tema o silêncio.",
                    "Pois quando tudo cessar, ele estará próximo."
            }, "Lurker");

            case 2: return new ItemNarrativo("Pergaminho: Seeker", new String[]{
                    "O Seeker não tem olhos, mas vê.",
                    "Ele sonda pensamentos, examina a fé.",
                    "Ele conhece teu medo antes de você mesmo.",
                    "E ele nunca desiste."
            }, "Seeker");

            case 3: return new ItemNarrativo("Pergaminho: Watcher", new String[]{
                    "O Watcher não interfere.",
                    "Ele observa. Anota. Recorda.",
                    "Dizem que ao morrer, sua última visão é a do Watcher —",
                    "E ele está sorrindo."
            }, "Watcher");

            case 4: return new ItemNarrativo("Pergaminho: Devourer", new String[]{
                    "O Devourer não mata por fome.",
                    "Ele consome por propósito.",
                    "Cada alma arrancada se torna parte de sua massa informe.",
                    "Um monumento de tudo o que deveria ser esquecido."
            }, "Devourer");

            case 5: return new ItemNarrativo("Pergaminho: Os Acendedores", new String[]{
                    "Foram poucos os que se levantaram com a tocha.",
                    "Chamaram-se Acendedores.",
                    "Não pela luz que carregam,",
                    "Mas pela escuridão que desafiam."
            }, "Acendedores");

            case 6: return new ItemNarrativo("Pergaminho: Lian", new String[]{
                    "Lian era luz. Era voz. Era beleza viva.",
                    "Não era de carne, mas caminhava entre os homens.",
                    "E ao ser traída, ela não amaldiçoou.",
                    "Apenas se calou — e o mundo caiu em trevas."
            }, "Lian");

            case 7: return new ItemNarrativo("Pergaminho: A Traição", new String[]{
                    "Os homens ofereceram tronos, correntes e adoração.",
                    "Mas não amor.",
                    "Tentaram moldar a luz com mãos sujas de ambição.",
                    "E ao ver isso, Lian se retirou.",
                    "E a noite começou."
            }, "Traição");

            default: return null;
        }
    }

    public static Item gerarPergaminhoAleatorio() {
        int i = (int)(Math.random() * 5);

        switch (i) {
            case 0: return new PergaminhoDeMagia("Pergaminho: Chuva de Meteoros",
                    new MagicalAttack("Chuva de Meteoros", 35, 0, 12));
            case 1: return new PergaminhoDeMagia("Pergaminho: Cura",
                    new MagicalAttack("Cura", -30, 0, 6)); // Cura
            case 2: return new PergaminhoDeMagia("Pergaminho: Furacão de Fogo",
                    new MagicalAttack("Furacão de Fogo", 32, 0, 10));
            case 3: return new PergaminhoDeMagia("Pergaminho: Tempestade de Neve",
                    new MagicalAttack("Tempestade de Neve", 28, 0, 9));
            case 4: return new PergaminhoDeMagia("Pergaminho: Lança do Trovão",
                    new MagicalAttack("Lança do Trovão", 26, 0, 8));
            default: return null;
        }
    }

}

