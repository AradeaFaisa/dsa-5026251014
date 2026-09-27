import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main{
    public static void main(String[]args){

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("transactions.txt")
        );

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        while(scanner.hasNext()){
            String name = scanner.next();
            String type = scanner.next();
            String amount = scanner.next();
            
            transactions.add(new String[]{name, type, amount});
            boolean exists = false;

            for(String[] customer: customers){
                if(customer[0].equals(name)){
                    exists = true;
                    break;
                }
            }

            if(!exists){
                customers.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        Queue<String[]> queue = new LinkedList<>();
        while(!transactions.isEmpty()){
            queue.offer(transactions.removeFirst());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while(!queue.isEmpty()){
            String[] transaction = queue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for(String[] customer: customers){
                if(customer[0].equals(name)){
                    int balance = Integer.parseInt(customer[1]);
                    if(type.equals("DEPOSIT")){
                        balance += amount;
                        customer[1] = String.valueOf(balance);
                    }else{
                        if(amount>balance){
                            failedTransactions.push(transaction);
                        }else{
                            balance -= amount;
                            customer[1] = String.valueOf(balance);  
                        }
                    }
                    break;
                    
                }
            }  
        }

        System.out.println("=== Final Balances ===");
        for(String[] customer: customers){
            System.out.println(customer[0] + " : " + customer[1]);
        }
        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while(!failedTransactions.isEmpty()){
            String[] transaction = failedTransactions.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        }
    }
}