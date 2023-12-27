class CustomQueue {
    private Node front;
    private Node rear;
    private int nodesCount;

    public CustomQueue() {
        front = rear = null;
        nodesCount = 0;
    }

    public void enqueue(String element) {
        Node temp = new Node(element);
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }
        this.rear.setNext(temp);
        this.rear = temp;
        nodesCount++;
    }

    public String dequeue() {
        if (front == null)
            return null;
        Node temp = front;
        front = front.getNext();
        if (front == null)
            rear = null;

        nodesCount--;
        return temp.getData();
    }

    public boolean isEmpty() {
        return rear == null && front == null;
    }

    public int size() {
        return nodesCount;
    }

    public void print() {
        System.out.print("\nMessage in the Queue:\n");
        if (isEmpty()) {
            System.out.print("Empty!!!\n");
            return;
        }
        Node ptr = front;
        int i = 1;
        while (ptr != rear.getNext()) {
            System.out.println(i + "." + ptr.getData());
            ptr = ptr.getNext();
            i++;
        }
        System.out.println("\n");
    }
}