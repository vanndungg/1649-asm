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

    public void inputMessages() {
        System.out.print("Enter messages: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        messages = input.split(",");
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

    public void sendMessage() {
        if (connectedSystem == null) {
            System.out.println("Not connected to any system.");
        } else {
            try {
                for (String message : messages) {
                    if (message.isEmpty()) {
                        throw new Exception("Empty message detected.");
                    } else if (message.length() > 250) {
                        System.out.println("Message length exceeds 250 characters. Truncating the message.");
                        while (message.length() > 250) {
                            String truncatedMessage = message.substring(0, 250);
                            outbox.enqueue(truncatedMessage);
                            System.out.println("Transferring message: \n" + truncatedMessage + " ...");
                            message = message.substring(250);
                        }
                    } else {
                        System.out.println("Send Message successful");
                        outbox.enqueue(message);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showQueue() {
        System.out.println(outbox);
    }

    public void receive() {
        if (connectedSystem == null) {
            System.out.println("Not connected to any system.");
        } else {
            while (true) {
                try {
                    if (outbox.isEmpty()) {
                        System.out.println("Error: Outbox is empty. No message to receive.");
                        System.out.println("Please try again.");
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
        // Implement checking message logic
        if (stack.isEmpty()) {
            System.out.println("Nothing in stack.");
        } else {
            System.out.println("Popped from STACK: " + stack.pop());
        }
    }
}
