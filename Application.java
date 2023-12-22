package asm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        try (Scanner input = new Scanner(System.in)){
            Queue queue = new Queue();
            Stack stack = new Stack();
            int selection=0;
            do {
                System.out.println("\n===MENU SYSTEM=== ");
                System.out.println("1.Send Message");
                System.out.println("2.Receiver");
                System.out.println("3.Show queue");
                System.out.println("4.Show stack");
                System.out.println("5.Exit");
                System.out.println("Enter your selection: ");
                try{
                    selection = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Error!!!");
                    input.nextLine();
                    continue;
                }
                switch (selection) {
                    case 1:
                        System.out.print("\nEnter your message: ");
                        String message = input.nextLine();
                        if (message.length() > 250){
                            System.out.println("\nError!!!\n");
                            break;
                        }
                        if (message.length() < 1){
                            System.out.println("\nError!!!\n");
                            break;
                        }
                        queue.enqueue(message);
                        break;
                    case 2:
                        if (queue.isEmpty()) {
                            System.out.println("NULL!!!");
                            break;
                        }
                        stack.push(queue.dequeue());
                        break;
                    case 3:
                        queue.print();
                        break;
                    case 4:
                        if(stack.isEmpty()){
                            System.out.println("EMPTY!!!");
                            break;
                        }
                        stack.print();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("\nSelection is not valid!!!\n");
                        break;
                }
            }
            while (selection !=5);
        }
    }
}
