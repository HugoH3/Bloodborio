public class Node<T> {

    // Declarando os atributos de um Node
    T data;
    Node<T> prev;
    Node<T> next;

    // Construtor
    public Node(T data) {
        this.data = data;

        // Sempre que um Node for criado, os atributos e anterior terao valores nulos
        this.prev = null;
        this.next = null;
    }

}