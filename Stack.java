package asm;

public class Stack {
    private Node top;
    private int nodesCount;
    public Stack(){
        top = null;
        nodesCount = 0;
    }
    public boolean isEmpty() {
        return top == null;
    }
    public int size() {
        return nodesCount;
    }
    public void push(String element) {
        Node node = new Node();
        System.out.println(("Addeed: " + element));
        node.data = element;
        node.next = top;
        top = node;
        nodesCount++;
    }
    public void print(){
        if (top == null){
            System.out.printf("\nStack Underflow");
            System.exit(-1);
        }
        else {
            Node temp = top;
            while (temp != null) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.println("null\n");
        }
    }
}
