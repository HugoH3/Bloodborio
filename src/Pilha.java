public class Pilha {
    Node head;
    int tamanho, quantidade;

    public Pilha(int tamanho) {
        this.tamanho = tamanho;
    }


    public void push(int data) { // Metodo para adicionar um item ao topo da pilha
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            quantidade = 1;

        }
        else{
            if(quantidade < tamanho) {
                newNode.next = head;
                head = newNode;
                quantidade++;
            }
        }
    }
    public int pop() {
        int poppedItem = head.data;
        head = head.next;
        quantidade--;

        return poppedItem;

    }
    public int peek(){
        if(isEmpty()){
            System.out.println("ERRO: Pilha vazia");
        }
        return head.data;
    }
    public boolean isEmpty(){
        return head == null; // Retorna o valor booleano dessa operacao logica
    }
    public boolean isFull(){
        return quantidade == tamanho;
    }
}