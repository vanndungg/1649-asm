import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager systemA = new Manager();
        Manager systemB = new Manager();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Main Menu:");
            System.out.println("1. System A");
            System.out.println("2. System B");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    systemMenuA(systemA, systemB);
                    break;
                case 2:
                    systemMenuB(systemB, systemA);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 3);

        scanner.close();
    }


    public static void systemMenuA(Manager systemA, Manager systemB) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nSystem Menu A:");
            System.out.println("1. Connect");
            System.out.println("2. Disconnect");
            System.out.println("3. Check connection");
            System.out.println("4. Send");
            System.out.println("5. Receive");
            System.out.println("6. Process");
            System.out.println("7. Show Outbox");
            System.out.println("8. Show Stack");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    systemA.connectedSystem(systemB);
                    break;
                case 2:
                    systemA.disconnectedSystem(systemB);
                    break;
                case 3:
                    systemA.checkConnection();
                    break;
                case 4:
                    systemA.inputMessages();
                    systemA.sendMessage();
                    break;
                case 5:
                    if (systemA.inbox.isEmpty()) {
                        // Kiểm tra inbox của hệ thống A
                        systemB.sendMessage(); // Gửi tin nhắn từ hệ thống A nếu inbox A trống
                    }
                    systemA.receive();
                    break;
                case 6:
                    if (!systemA.inbox.isEmpty()) {
                        systemA.process();
                    } else {
                        System.out.println("Inbox is empty. Please receive messages first.");
                    }
                    break;
                case 7:
                    systemA.showQueue();
                    break;
                case 8:
                    systemA.showStack();
                    break;
                case 9:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (option != 8);
    }

    public static void systemMenuB(Manager systemB, Manager systemA) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nSystem Menu B:");
            System.out.println("1. Connect");
            System.out.println("2. Check connection");
            System.out.println("3. Send");
            System.out.println("4. Receive");
            System.out.println("5. Process");
            System.out.println("6. Show Outbox");
            System.out.println("7. Show Stack");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    systemB.connectedSystem(systemA);
                    break;
                case 2:
                    systemB.disconnectedSystem(systemA);
                    break;
                case 3:
                    systemB.checkConnection();
                    break;
                case 4:
                    systemB.inputMessages();
                    systemB.sendMessage();
                    break;
                case 5:
                    if (systemB.inbox.isEmpty()) {
                        // Kiểm tra inbox của hệ thống B
                        systemA.sendMessage(); // Gửi tin nhắn từ hệ thống A nếu inbox B trống
                    }
                    systemB.receive();
                    break;
                case 6:
                    if (!systemB.inbox.isEmpty()) {
                        systemB.process();
                    } else {
                        System.out.println("Inbox is empty. Please receive messages first.");
                    }
                    break;
                case 7:
                    systemB.showQueue();
                    break;
                case 8:
                    systemB.showStack();
                    break;
                case 9:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (option != 8);
    }
}
