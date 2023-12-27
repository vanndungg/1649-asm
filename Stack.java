class CustomStack {
    private Node top;
    private int nodesCount;

    public CustomStack() {
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
        Node node = new Node(element);
        node.next = top;
        top = node;
        nodesCount++;
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("\nStack Underflow");
            return null;
        } else {
            String popped = top.getData();
            top = top.getNext();
            nodesCount--;
            return popped;
        }
    }

    public void print() {
        if (top == null) {
            System.out.printf("\nStack Underflow");
        } else {
            Node temp = top;
            while (temp != null) {
                System.out.print(temp.getData() + " -> ");
                temp = temp.getNext();
            }
            System.out.println("null\n");
        }
    }
}