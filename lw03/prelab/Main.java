import java.util.*;

public class Main {
    public static void main(String[]args){
        List<String> playlist = new ArrayList<>();
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("playlist.txt")
        );

        while (scanner.hasNextLine()){
            String operation = scanner.nextLine();
            String[] parts = operation.split(" ");
            String type = parts[0];

            if (type.equals("ADD")) {
                String song = operation.substring(4);
                playlist.add(song);
            } else if (type.equals("INSERT")){
                int index = Integer.parseInt(parts[1]);
                String song = operation.substring(9);
                playlist.add(index, song);

            } else if (type.equals("REMOVE")){
                String song = operation.substring(7);
                playlist.remove(song);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i=0; i<playlist.size(); i++){
            System.out.println((i+1)+ ": " +playlist.get(i));
        }

                Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner scanner2 = new Scanner(
            Main.class.getResourceAsStream("participants.txt")
        );

        while (scanner2.hasNextLine()){
            String name = scanner2.nextLine();

            if (participants.contains(name)){
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        scanner2.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String participant : participants){
            System.out.println(number + ". " + participant);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);

                Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner3 = new Scanner(
            Main.class.getResourceAsStream("inventory.txt")
        );

        while (scanner3.hasNextLine()){
            String operation = scanner3.nextLine();
            String[] parts = operation.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")){

                if (inventory.containsKey(product)){
                    int stock = inventory.get(product);
                    inventory.put(product, stock + quantity);
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")){

                if (inventory.containsKey(product) &&
                    inventory.get(product) >= quantity){

                    int stock = inventory.get(product);
                    inventory.put(product, stock - quantity);

                } else {
                    failedSales++;
                }
            }
        }

        scanner3.close();

        System.out.println("===== Problem 3 =====");

        for (String product : inventory.keySet()){
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
    
}
