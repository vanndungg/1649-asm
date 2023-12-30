import java.util.Scanner;

public class Manager {
    public Queue<String> outbox;
    public Queue<String> inbox;
    public Stack<String> stack;
    private String[] messages;
    private Manager connectedSystem = null;
    private Manager disconnectedSystem = null;

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
        //System.out.println("Connected");
    }
    public void disconnectedSystem(Manager system) {
        if (disconnectedSystem == null) {
            System.out.println("Disconnected");
            return;
        }
        disconnectedSystem = system;
        system.disconnectedSystem(this);
        System.out.println("Disconnected");
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
            // Moved the 'else' block outside the 'for' loop
            if (connectedSystem == null) {
                System.out.println("Not connected to any system.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showQueue() {
        System.out.println(outbox);
    }

    public void receive() {
        if (this.connectedSystem != null) {
            while (!outbox.isEmpty()) {
                System.out.println("Received message: \n");
                String messageToSend = outbox.dequeue();
                this.connectedSystem.inbox.enqueue(messageToSend);
            }
        } else {
            System.out.println("Not connected. Please connect first.");
        }
    }

    public void process() {
        while (!inbox.isEmpty()) {
            String message = inbox.dequeue();
            System.out.println("Processing message: \n" + message);
            stack.push(message);
        }
    }

    public void showStack() {
        System.out.println(stack);
    }
}
