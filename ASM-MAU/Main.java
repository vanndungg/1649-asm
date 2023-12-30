import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainSystem systemA = new mainSystem("System A");
        mainSystem systemB = new mainSystem("System B");
        boolean isConnected = false;

        while (true) {
            printMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    if (!isConnected) {
                        systemA.connectTo(systemB);
                        isConnected = true;
                        System.out.println("Connected!");
                    } else {
                        System.out.println("Already connected!");
                    }
                    break;
                case 2:
                    if (isConnected) {
                        System.out.println("Connection Status: Connected");
                    } else {
                        System.out.println("Connection Status: Not connected");
                    }
                    break;
                case 3:
                    if (isConnected) {
                        systemA.disconnect();
                        isConnected = false;
                        System.out.println("Disconnected!");
                    } else {
                        System.out.println("Not connected!");
                    }
                    break;
                case 4:
                    if (isConnected) {
                        try {
                            scanner.nextLine();
                            System.out.print("Enter message to send: ");
                            String message = scanner.nextLine();
                            if (message.length() == 0 || message.length() > 250) {
                                throw new IllegalArgumentException("Message length must be between 1 and 250 characters");
                            }
                            systemA.sendMessage(message);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Not connected");
                    }
                    break;
                case 5:
                    if (isConnected) {
                        systemB.receiveMessage(systemA);
                        systemB.displayInbox();
                    } else {
                        System.out.println("Not connected. Inbox is empty.");
                    }
                    break;

                case 6:
                    if (isConnected && !systemB.getInbox().isEmpty() ){
                        systemB.pushMessagesFromInboxToStack();
                        System.out.println("Message sent to Stack successfully");
                    } else {
                        System.out.println("Not connected or Inbox is empty");
                    }
                    break;
                case 7:
                    if (isConnected){
                        systemB.displayMessageStack();
                    } else {
                        System.out.println("Not connected");
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 8.");
                    break;
            }
        }
    }
    private static void printMenu() {
        System.out.println("1. Connect");
        System.out.println("2. Check connection");
        System.out.println("3. Disconnect");
        System.out.println("4. Send message");
        System.out.println("5. Receive message");
        System.out.println("6. Process message");
        System.out.println("7. Check inbox of system");
        System.out.println("8. Exit");
        System.out.print("Enter your option: ");
    }
}