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
        int batalhasConsecutivas = 0;
        int chefesDerrotados = 0;

        while (continuar) {
            System.out.println("\n🌲 Você entra mais fundo na floresta...");

            Monster monstro;
            if (batalhasConsecutivas > 0 && batalhasConsecutivas % 3 == 0) {
                System.out.println("\n⚠ Um horror maior se aproxima...");
                monstro = gerarChefeAleatorio();
            } else {
                monstro = gerarMonstroAleatorio();
            }

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
                System.out.println(monstro.nome + "Se alimentou das suas entranhas");
                jogador.removerPersonagem(personagem);
                return;
            }

            if (Math.random() < 0.7) {
                Item drop = gerarItemAleatorio();
                personagem.adicionarItem(drop);
                System.out.println("Você encontrou um item em meio os escombros: " + drop.getNome());
            }

            personagem.ganharExperiencia(20);
            batalhasConsecutivas++;

            if (monstro.getNome().contains("Chefe")) {
                chefesDerrotados++;
            }

            if (chefesDerrotados >= 3) {
                System.out.println("\n🌟 Você enfrentou os horrores de Dormar e triunfou sobre eles.");
                System.out.println("Mas a luz... o que fará com ela?");
                System.out.println("1 - Apagar a luz.");
                System.out.println("2 - Se entregar à luz.");
                System.out.print("> ");
                int escolhaFinal = scanner.nextInt();
                scanner.nextLine();

                if (escolhaFinal == 1) {
                    System.out.println("\n🌑 E assim, o último raio se extinguiu.");
                    System.out.println("O mundo jamais mais conheceu aurora.");
                } else {
                    System.out.println("\n✨ Você entregou tudo... e a luz cantou de novo.");
                    System.out.println("Dormar sonhou, por um instante, com o amanhecer.");
                }
                return;
            }
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
                case 0: return new ArmaFisicaItem("Lamina Sagrada de Lian", new MeleeAttack("Corte Luminoso", 25, 0, 5));
                case 1: return new ArmaFisicaItem("Sangue de Lian", new MeleeAttack("Corte Sombrio", 28, 0, 6));
                case 2: return new ArmaFisicaItem("Claymore de Merlin", new MeleeAttack("Corte Arcano", 22, 0, 4));
                case 3: return new ArmaFisicaItem("Machado Rúnico", new MeleeAttack("Brutalizar", 30, 0, 8));
                case 4: return new ArmaFisicaItem("Lança Profanada", new MeleeAttack("Empalar", 32, 0, 7));
                case 5: return new ArmaFisicaItem("Gadanha de Ossos", new MeleeAttack("Ceifa Sombria", 28, 0, 6));
                case 6: return new ArmaFisicaItem("Alabarda de Ruína", new MeleeAttack("Quebra-Coluna", 35, 0, 9));
                case 7: return new ArmaFisicaItem("Punhal Rachado", new MeleeAttack("Perfuração Precisa", 22, 0, 4));
                case 8: return new ArmaFisicaItem("Machado do Carrasco", new MeleeAttack("Decapitação Ritualística", 40, 0, 10));
                case 9: return new ArmaFisicaItem("Espinho de Dormar", new MeleeAttack("Espetamento Rúnico", 30, 0, 8));
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
        int i = (int)(Math.random() * 10);

        switch (i) {
            case 0: return new PergaminhoDeMagia("Pergaminho: Tormenta de Fogo",
                    new MagicalAttack("Tormenta", 35, 0, 12));

            case 1: return new PergaminhoDeMagia("Pergaminho: Oração",
                    new MagicalAttack("Orar", -30, 0, 6)); // Cura

            case 2: return new PergaminhoDeMagia("Pergaminho: Dilacerar",
                    new MagicalAttack("Dilacerar", 32, 0, 10));

            case 3: return new PergaminhoDeMagia("Pergaminho: Noite de Trevas",
                    new MagicalAttack("Obscurecer", 28, 0, 9));

            case 4: return new PergaminhoDeMagia("Pergaminho: Julgamento",
                    new MagicalAttack("Julgar", 26, 0, 8));


            case 5: return new PergaminhoDeMagia("Pergaminho: Sussurros da Agonia",
                    new MagicalAttack("Sussurros da Agonia", 18, 0, 6));

            case 6: return new PergaminhoDeMagia("Pergaminho: Pântano da Lamentação",
                    new MagicalAttack("Pântano da Lamentação", 14, 0, 4));

            case 7: return new PergaminhoDeMagia("Pergaminho: Chamas Negras de Uroth",
                    new MagicalAttack("Chamas Negras de Uroth", 22, 0, 7));

            case 8: return new PergaminhoDeMagia("Pergaminho: Lamento Espectral",
                    new MagicalAttack("Lamento Espectral", 20, 0, 8));

            case 9: return new PergaminhoDeMagia("Pergaminho: Fragmento de Dormar",
                    new MagicalAttack("Fragmento de Dormar", 28, 0, 12));

            default: return null;
        }
    }
    public static Monster gerarChefeAleatorio() {
        int escolha = (int)(Math.random() * 5);
        Monster chefe;

        switch (escolha) {
            case 0:
                chefe = new Monster(null, 30, 30, 60, 60, 50, "Chefe: Casseb, Horror de Dormar", 99, 50);
                chefe.adicionarHabilidade(new MagicalAttack("Rugido Primordial", 20, 10, 6));
                chefe.adicionarHabilidade(new MeleeAttack("Colapso Carnal", 25, 0, 8));
                break;

            case 1:
                chefe = new Monster(null, 28, 28, 55, 50, 48, "Chefe: Liborio, O Traidor de Lian", 98, 45);
                chefe.adicionarHabilidade(new MagicalAttack("Corrupção da Promessa", 25, 10, 5));
                chefe.adicionarHabilidade(new MeleeAttack("Lâmina Profanada", 28, 0, 8));
                break;

            case 2:
                chefe = new Monster(null, 35, 20, 50, 45, 55, "Chefe: Isaac,  A Chama Rachada", 97, 45);
                chefe.adicionarHabilidade(new MagicalAttack("Explosão Interior", 35, 0, 12));
                chefe.adicionarHabilidade(new MeleeAttack("Estilhaço Flamejante", 22, 0, 6));
                break;

            case 3:
                chefe = new Monster(null, 20, 40, 48, 55, 52, "Chefe: Girotto, o Sentinela do Abismo", 96, 45);
                chefe.adicionarHabilidade(new MagicalAttack("Véu de Vazio", 18, 6, 5));
                chefe.adicionarHabilidade(new MeleeAttack("Golpe Abissal", 30, 0, 9));
                break;

            case 4:
            default:
                chefe = new Monster(null, 25, 30, 52, 52, 53, "Chefe: Ale, a Voz Esquecida", 95, 45);
                chefe.adicionarHabilidade(new MagicalAttack("Cântico da Ruína", 32, 11, 6));
                chefe.adicionarHabilidade(new MeleeAttack("Silêncio Cortante", 24, 0, 7));
                break;
        }

        return chefe;
    }




}

