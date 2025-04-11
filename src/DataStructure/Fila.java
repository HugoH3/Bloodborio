package DataStructure;

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

    // Enfileirar (enqueue)
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        qtd++;
    }

    // Desenfileirar (dequeue)
    public T remove() {
        if (isEmpty()) {
            System.out.println("DataStructure.Fila vazia");
            return null;
        } else {
            T removedItem = head.getData();
            head = head.getNext();
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
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    // Getter do início da fila (opcional)
    public Node<T> getHead() {
        return head;
    }
}
