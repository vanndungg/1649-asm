import java.util.Scanner;

public class Main {
    static CustomStack stackA = new CustomStack();
    static CustomQueue queueA = new CustomQueue();
    static CustomStack stackB = new CustomStack();
    static CustomQueue queueB = new CustomQueue();
    static boolean connectionEstablished = false;
    static boolean systemAConnected = false;
    static boolean systemBConnected = false;
    static String[] messages;

    private static void inputMessages() {
        System.out.print("Enter messages: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        messages = input.split(",");
    }

    private static void establishConnection() {
        System.out.println("Connecting systems...");
        connectionEstablished = true;
        System.out.println("Connection successful!");
    }

    private static void destroyConnection() {
        System.out.println("Destroying connection...");
        connectionEstablished = false;
        systemAConnected = false;
        systemBConnected = false;
        System.out.println("Connection destroyed!");
    }

    private static void sendMessage(CustomQueue senderQueue, CustomStack receiverStack) {
        try {
            for (String message : messages) {
                if (message.equals("")) {
                    throw new Exception("Empty message detected.");
                } else if (message.length() > 250) {
                    System.out.println("Message length exceeds 250 characters. Truncating the message.");
                    while (message.length() > 250) {
                        String truncatedMessage = message.substring(0, 250);
                        senderQueue.enqueue(truncatedMessage);
                        System.out.println("Transferring message: \n" + truncatedMessage + " ...");
                        message = message.substring(250);
                    }
                } else {
                    senderQueue.enqueue(message);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void process(CustomQueue senderQueue, CustomStack receiverStack) {
        while (!senderQueue.isEmpty()) {
            String message = senderQueue.dequeue();
            System.out.println("Received message: \n" + message);
            receiverStack.push(message);
        }
    }

    private static void result(CustomStack stack) {
        while (!stack.isEmpty()) {
            System.out.println("Message received: \n" + stack.pop());
        }
    }

    private static void showQueue(CustomQueue queue) {
        queue.print();
    }

    private static void showStack(CustomStack stack) {
        stack.print();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose System: ");
            System.out.println("1. System A");
            System.out.println("2. System B");
            System.out.println("3. Exit");
            System.out.println("Enter your selection: ");

            int systemChoice = scanner.nextInt();
            scanner.nextLine();

            switch (systemChoice) {
                case 1:
                    if (!systemAConnected) {
                        establishConnection();
                        systemAConnected = true;
                    }

                    System.out.println("System A Menu:");
                    while (true) {
                        System.out.println("1. Send Message");
                        System.out.println("2. Receive Message");
                        System.out.println("3. Show Queue");
                        System.out.println("4. Show Stack");
                        System.out.println("5. Exit");
                        System.out.println("Enter your selection: ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                inputMessages();
                                sendMessage(queueA, stackB);
                                break;
                            case 2:
                                if (!connectionEstablished) {
                                    System.out.println("Error: Connection not established.");
                                } else {
                                    process(queueB, stackA);
                                }
                                break;
                            case 3:
                                showQueue(queueA);
                                break;
                            case 4:
                                showStack(stackA);
                                break;
                            case 5:
                                destroyConnection();
                                break;
                            default:
                                System.out.println("Invalid option. Please choose again.");
                                break;
                        }

                        if (choice == 5) {
                            break;
                        }
                    }
                    break;

                case 2:
                    if (!systemBConnected) {
                        establishConnection();
                        systemBConnected = true;
                    }

                    System.out.println("System B Menu:");
                    while (true) {
                        System.out.println("1. Send Message");
                        System.out.println("2. Receive Message");
                        System.out.println("3. Show Queue");
                        System.out.println("4. Show Stack");
                        System.out.println("5. Exit");
                        System.out.println("Enter your selection: ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                inputMessages();
                                sendMessage(queueB, stackA);
                                break;
                            case 2:
                                if (!connectionEstablished) {
                                    System.out.println("Error: Connection not established.");
                                } else {
                                    process(queueA, stackB);
                                }
                                break;
                            case 3:
                                showQueue(queueB);
                                break;
                            case 4:
                                showStack(stackB);
                                break;
                            case 5:
                                destroyConnection();
                                break;
                            default:
                                System.out.println("Invalid option. Please choose again.");
                                break;
                        }

                        if (choice == 5) {
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}