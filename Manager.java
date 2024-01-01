import java.util.NoSuchElementException;
import java.util.Scanner;

public class Manager {
    public Queue<String> outbox;
    public Queue<String> inbox;
    public Stack<String> stack;
    private String[] messages;
    private Manager connectedSystem = null;

    public Manager() {
        outbox = new Queue<>();
        inbox = new Queue<>();
        stack = new Stack<>();
    }
    public void connectedSystem(Manager system) {
        if (connectedSystem != null) {
            System.out.println("Already connected");
            return;
        }
        connectedSystem = system;
        system.connectedSystem(this);
        System.out.println("Connected");
    }

    public boolean isConnected() {

        return connectedSystem != null;
    }

    public void checkConnection() {
        if (isConnected()) {
            System.out.println("Connected to another system.");
        } else {
            System.out.println("Not connected to any system.");
        }
    }
    public void inputMessages() {
        System.out.print("Enter messages: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        messages = input.split(",");
    }

    public void sendMessage() {
        long startTime = System.nanoTime();
        if (connectedSystem == null) {
            System.out.println("Not connected to any system.");
        } else {
            for (String message : messages) {
                if (message.length() == 0 || message.length() > 250) {
                    System.out.println("Invalid message length.");
                    return;
                }
                outbox.enqueue(message);
                System.out.println("Send Message success");
                System.out.println("Display all messages: ");
                System.out.println(outbox);
            }
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken to send message: " + elapsedTime + " nanoseconds");
    }


    public void receive() {
        if (connectedSystem == null) {
            System.out.println("Not connected to any system.");
        } else {
            while (true) {
                try {
                    if (outbox.isEmpty()) {
                        System.out.println("Outbox is empty");
                        return;
                    }
                    inbox.enqueue(outbox.dequeue());
                    System.out.println("Message received successfully.");
                    System.out.println("Display all messages: ");
                    System.out.println(inbox);
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Please try again.");
                }
            }
        }
    }

    public void process() {
        if (!inbox.isEmpty()) {
            stack.push(inbox.dequeue());
            System.out.println("Message pushed to STACK");
        } else {
            System.out.println("No Message in INBOX.");
        }
    }

    public void check() {
        if (stack.isEmpty()) {
            System.out.println("Nothing in Stack.");
        } else {
            System.out.println("Popped from STACK: " + stack.pop());
        }
    }
}
