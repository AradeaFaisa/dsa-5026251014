import java.util.*;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> food = new LinkedList<>();
        LinkedList<String[]> drink = new LinkedList<>();
        LinkedList<String[]> successfull = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNext()) {

            String[] order = new String[4];

            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();

            orders.add(order);
        }

        scanner.close();

        food.add(new String[]{"Bakso","2"});
        food.add(new String[]{"Sate","1"});
        food.add(new String[]{"Soto","2"});

        drink.add(new String[]{"Es Teh","4"});
        drink.add(new String[]{"Es Jeruk","2"});
        drink.add(new String[]{"Es Jeruk","1"});


        while (!orders.isEmpty()) {
            queue.offer(orders.removeFirst());
        }

        // Memproses orders dari Queue
        while (!queue.isEmpty()) {

            String[] order = queue.poll();

            String name = order[0];
            String sideDish = order[1];
            String drinkChoice = order[2];
            String table = order[3];

            boolean sideDishAvailable = true;
            boolean drinkAvailable = true;

            if (!food.equals("-")) {

                for (String[] data : food) {

                    if (data[0].equals(sideDish)) {

                        int stock = Integer.parseInt(data[1]);

                        if (stock <= 0) {
                            sideDishAvailable = false;
                        } 
                        break;
                    }
                }
            }

            if (!drink.equals("-")) {

                for (String[] data : drink) {

                    if (data[0].equals(drinkChoice)) {

                        int stock = Integer.parseInt(data[1]);

                        if (stock <= 0) {
                            drinkAvailable = false;
                        } 
                        break;
                    }
                
                }
            }

              if (sideDishAvailable && drinkAvailable) {
                
                if (!food.equals("-")) {
                    for (String[] data : foods) {
                        if (data[0].equals(sideDish)) {
                            int stock = Integer.parseInt(data[1]);
                            stock--;
                            data[1] = String.valueOf(stock);
                            break;
                        }
                    }
                }

                // Kurangi stock minuman
                if (!drink.equals("-")) {
                    for (String[] data : drinks) {
                        if (data[0].equals(drinkChoice)) {
                            int stock = Integer.parseInt(data[1]);
                            stock--;
                            data[1] = String.valueOf(stock);
                            break;
                        }
                    }
                }
                successfulOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(
                drink[0] + " : " + drink[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}