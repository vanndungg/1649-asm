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
            System.out.println("2. Check connect");
            System.out.println("3. Send");
            System.out.println("4. Receive");
            System.out.println("5. Process");
            System.out.println("6. Check");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    systemA.connectedSystem(systemB);
                    break;
                case 2:
                    systemA.checkConnection();
                    break;
                case 3:
                    systemA.inputMessages();
                    systemA.sendMessage();
                    break;
                case 4:
                    systemB.receive();
                    break;
                case 5:
                    systemA.process();
                    break;
                case 6:
                    systemA.check();
                    break;
                case 7:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (option != 7);
    }

    public static void systemMenuB(Manager systemB, Manager systemA) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nSystem Menu B:");
            System.out.println("1. Connect");
            System.out.println("2. Check connect");
            System.out.println("3. Send");
            System.out.println("4. Receive");
            System.out.println("5. Process");
            System.out.println("6. Check");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    systemB.connectedSystem(systemA);
                    break;
                case 2:
                    systemB.checkConnection();
                    break;
                case 3:
                    systemB.inputMessages();
                    systemB.sendMessage();
                    break;
                case 4:
                    systemA.receive();
                    break;
                case 5:
                    systemB.process();
                    break;
                case 6:
                    systemB.check();
                    break;
                case 7:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (option != 7);
    }
}
