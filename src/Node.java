public class Node {

    // Declarando os atributos de um Node
    int data;
    Node prev, next;

    // Construtor
    public Node(int data) {
        this.data = data;

        // Sempre que um Node for criado, os atributos e anterior terao valores nulos
        this.prev = null;
        this.next = null;
    }

}