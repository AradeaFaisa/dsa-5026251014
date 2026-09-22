import java.util.Scanner;

public class Main{
    public static void main(String[] args){
         
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        WashService[] washes = new WashService[4];

        for (int i = 0; i < washes.length && scanner.hasNext(); i++){
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            WashService wash;

            if(type.equals("MOTORCYCLE")){
                wash = new MotorcycleWash(id, days);
            }else{
                wash = new CarWash(id, days);
            }

            washes[scanner.nextInt()] = wash;
        }

        scanner.close();

        for(WashService wash: washes){
            System.out.println(wash.label() + ": " + wash.calculateCharge());
        }

    }
}