public class Fila<T> {
    private Node<T> head;
    private Node<T> tail;
    private int qtd;

    public Fila() {
        this.head = null;
        this.tail = null;
        this.qtd = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        qtd++;
    }

    public T remove() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
            return null;
        } else {
            T removedItem = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            qtd--;
            return removedItem;
        }
    }

    public int size() {
        return qtd;
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
