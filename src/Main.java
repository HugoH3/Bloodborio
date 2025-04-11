import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Jogador jogador = null;

        System.out.printf("BLOODBORIO\n");

        System.out.println("--- Cadastro ---\n");
        System.out.println("Digite seu nomme de jogador: ");
        String nome = sc.nextLine();

        System.out.printf("\nEscolha uma senha: ");
        String senha = sc.nextLine();

        jogador = new Jogador(1, nome, senha, 0, null);

        System.out.printf("\n--- Login ---");
        boolean autenticado = false;

        while (!autenticado) {
            System.out.printf("\nUsuario: ");
            String usuario = sc.nextLine();

            System.out.printf("\nSenha: ");
            String tentativa = sc.nextLine();

            if(jogador.autenticar(usuario, senha)) {
                System.out.println("Login bem-sucedido!");
                autenticado = true;
            } else {
                System.out.println("Senha ou login incorretos!");
            }
        }

        int opcao;

        do{
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1. Criar personagem");
            System.out.println("2. Iniciar Batalha");
            System.out.println("3. Loja");
            System.out.println("4. Sair");
            System.out.println("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:

                    System.out.println("\nDigite o nome do Personagem: ");
                    jogador.criarPersonagem();



                    break;
                case 2:
                    if(jogador.getPersonagens().isEmpty()){
                        System.out.println("Crie um personagem antes de iniciar!");
                        break;
                    }


            }
        }
    }
}
