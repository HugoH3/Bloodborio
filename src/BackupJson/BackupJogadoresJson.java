package BackupJson;

import ItemType.Item;
import com.google.gson.*;
import Entities.*;
import DataStructure.*;
import Abilities.Ability;
import java.io.*;

public class BackupJogadoresJson {

    private static final String caminho = "E:\\cesupa 2025\\estrutura de dados\\trabalho issac\\Bloodborio\\Data\\jogadores.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvarJogadores(ListaDeJogadores jogadores) {
        JsonArray jsonArray = new JsonArray();
        Node<Jogador> atual = jogadores.getJogadores().getHead();

        while (atual != null) {
            Jogador jogador = atual.getData();
            JsonObject jogadorJson = new JsonObject();

            jogadorJson.addProperty("nome", jogador.getNome());
            jogadorJson.addProperty("senha", jogador.getSenha());

            // Serializa personagens
            JsonArray personagensArray = new JsonArray();
            Node<Personagem> atualPersonagem = jogador.getPersonagens().getHead();
            while (atualPersonagem != null) {
                Personagem personagem = atualPersonagem.getData();
                JsonObject personagemJson = new JsonObject();

                personagemJson.addProperty("nome", personagem.getNome());
                personagemJson.addProperty("xpAtual", personagem.getXpAtual());
                personagemJson.addProperty("xpTotal", personagem.getXpTotal());
                personagemJson.addProperty("nivel", personagem.getNivel());
                personagemJson.addProperty("vidaAtual", personagem.getVidaAtual());
                personagemJson.addProperty("vidaMaxima", personagem.getVidaMaxima());
                personagemJson.addProperty("manaAtual", personagem.getManaAtual());
                personagemJson.addProperty("manaMaxima", personagem.getManaMaxima());
                personagemJson.addProperty("vigor", personagem.getVigor());

                // Serializa abilities
                JsonArray abilitiesArray = new JsonArray();
                Node<Ability> atualAbility = personagem.getHabilidades().getHead();
                while (atualAbility != null) {
                    Ability ability = atualAbility.getData();
                    JsonObject abilityJson = new JsonObject();

                    abilityJson.addProperty("nome", ability.getName());
                    abilityJson.addProperty("dano", ability.getDamage());
                    abilityJson.addProperty("mana", ability.getMana());

                    abilitiesArray.add(abilityJson);
                    atualAbility = atualAbility.getNext();
                }
                personagemJson.add("habilidades", abilitiesArray);

                personagensArray.add(personagemJson);
                atualPersonagem = atualPersonagem.getNext();
            }
            jogadorJson.add("personagens", personagensArray);

            jsonArray.add(jogadorJson);
            atual = atual.getNext();
        }

        try (Writer writer = new FileWriter(caminho)) {
            gson.toJson(jsonArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListaDeJogadores carregarJogadores() {
        ListaDeJogadores listaDeJogadores = new ListaDeJogadores();

        try (Reader reader = new FileReader(caminho)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jogadorJson = element.getAsJsonObject();
                Jogador jogador = new Jogador();

                jogador.setNome(jogadorJson.get("nome").getAsString());
                jogador.setSenha(jogadorJson.get("senha").getAsString());

                if (jogador.getPersonagens() == null) {
                    jogador.setPersonagens(new Lista<>());
                }

                JsonArray personagensArray = jogadorJson.getAsJsonArray("personagens");
                for (JsonElement personagemElement : personagensArray) {
                    JsonObject personagemJson = personagemElement.getAsJsonObject();
                    Personagem personagem = new Personagem();

                    personagem.setNome(personagemJson.get("nome").getAsString());
                    personagem.setXpAtual(personagemJson.get("xpAtual").getAsInt());
                    personagem.setXpTotal(personagemJson.get("xpTotal").getAsInt());
                    personagem.setNivel(personagemJson.get("nivel").getAsInt());
                    personagem.setVidaAtual(personagemJson.get("vidaAtual").getAsInt());
                    personagem.setVidaMaxima(personagemJson.get("vidaMaxima").getAsInt());
                    personagem.setManaAtual(personagemJson.get("manaAtual").getAsInt());
                    personagem.setManaMaxima(personagemJson.get("manaMaxima").getAsInt());

                    if (personagem.getHabilidades() == null) {
                        personagem.setHabilidades(new Lista<>());
                    }

                    JsonArray abilitiesArray = personagemJson.getAsJsonArray("habilidades");
                    for (JsonElement abilityElement : abilitiesArray) {
                        JsonObject abilityJson = abilityElement.getAsJsonObject();
                        Ability ability = new Ability();

                        ability.setName(abilityJson.get("nome").getAsString());
                        ability.setDamage(abilityJson.get("dano").getAsInt());
                        ability.setMana(abilityJson.get("mana").getAsInt());

                        personagem.getHabilidades().inserirTail(ability);
                    }

                    jogador.getPersonagens().inserirTail(personagem);
                }

                listaDeJogadores.adicionarJogador(jogador);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaDeJogadores;
    }
}