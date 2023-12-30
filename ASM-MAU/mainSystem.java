import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class mainSystem {
    private String name;
    private boolean isConnected;
    private Queue<String> outbox;
    private Queue<String> inbox;
    private Stack<String> messageStack;

    public mainSystem(String name) {
        this.name = name;
        this.isConnected = false;
        this.outbox = new LinkedList<>();
        this.inbox = new LinkedList<>();
        this.messageStack = new Stack<>();
    }

    public void connectTo(mainSystem otherSystem) {
        this.isConnected = true;
    }
    public void disconnect() {
        this.isConnected = false;
    }
    public void sendMessage(String message) {
        outbox.offer(message);
    }
    public Queue<String> getOutbox() {
        return outbox;
    }
    public Queue<String> getInbox() {
        return inbox;
    }
    public void receiveMessage(mainSystem otherSystem) {
//        long startTime = System.nanoTime();
        Queue<String> outbox = otherSystem.getOutbox();
        Queue<String> inbox = getInbox();
        if(!outbox.isEmpty()){
            String message = outbox.poll();
            inbox.offer(message);
        } else {
            System.out.println("Out box is empty");
        }
//        long endTime = System.nanoTime();
//        long elapsed = endTime - startTime;
//        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void pushMessagesFromInboxToStack() {
        String message = inbox.poll();
        MessageStack(message);
    }
    public void MessageStack(String message) {
        messageStack.push(message);
    }
    public void displayInbox() {
        System.out.println("Inbox of " + name + ":");
        displayQueue(inbox);
    }
    public void displayMessageStack() {
        System.out.println("Message Stack of " + name + ":");
        if(!messageStack.isEmpty()){
            String poppedMessage = messageStack.pop();
            System.out.println(poppedMessage);
        }else {
            System.out.println("Stack is empty");
        }
    }
    private void displayQueue(Queue<String> queue) {
        if (queue.isEmpty()) {
            System.out.println("Inbox is empty.");
        } else {
            for (String message : queue) {
                System.out.println(message);
            }
        }
    }
}