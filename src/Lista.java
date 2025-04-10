public class Lista<T> {

    private Node<T> head;
    private Node<T> tail;

    public Lista() {
        this.head = null;
        this.tail = null;
    }

    public void inserirHead(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void inserirTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void printHead() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void printTail() {
        Node<T> current = tail;
        while (current != null) {
            System.out.println(current.data);
            current = current.prev;
        }
    }

    public Node<T> getHead() {
        return head;
    }
}
