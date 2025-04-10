public class Pilha<T> {
    private Node<T> topo;
    private int tamanhoMaximo;
    private int quantidade;

    public Pilha(int tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
        this.quantidade = 0;
        this.topo = null;
    }

    public void push(T data) {
        if (isFull()) {
            System.out.println("ERRO: Pilha cheia");
            return;
        }

        Node<T> novo = new Node<>(data);
        novo.next = topo;
        topo = novo;
        quantidade++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("ERRO: Pilha vazia");
            return null;
        }

        T dadoRemovido = topo.data;
        topo = topo.next;
        quantidade--;
        return dadoRemovido;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("ERRO: Pilha vazia");
            return null;
        }
        return topo.data;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public boolean isFull() {
        return quantidade == tamanhoMaximo;
    }

    public int size() {
        return quantidade;
    }
}
