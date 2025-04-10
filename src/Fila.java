public class Fila {
    Node head;
    Node tail;
    int qtd;

    public Fila() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void add(int data){
        Node newNode = new Node(data);

        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
        }
        qtd++;
    }
    public int remove(){
        if(isEmpty()){
            System.out.println("Fila vazia");
        }
        else{
            removedItem = head.data();

        }
    }

}