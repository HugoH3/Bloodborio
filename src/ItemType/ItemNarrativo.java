package ItemType;

import Entities.Personagem;

public class ItemNarrativo extends Item {
    private String[] fragmentos;
    private int leituraAtual;
    private String codigo;

    public ItemNarrativo(String nome, String[] fragmentos, String codigo) {
        super(nome, "lore", 0);
        this.fragmentos = fragmentos;
        this.codigo = codigo; // usado para identificar o conteúdo (ex: “Lian”)
        this.leituraAtual = 0;
    }

    @Override
    public void usar(Personagem p) {
        System.out.println("\n📖 [" + codigo + " - Fragmento " + (leituraAtual + 1) + "/" + fragmentos.length + "]");
        System.out.println(fragmentos[leituraAtual]);
        leituraAtual = (leituraAtual + 1) % fragmentos.length;
    }
}
