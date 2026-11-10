import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(
            Main.class.getResourceAsStream("parcels.txt")
        );

        int n = scan.nextInt();
        int[] parcels = new int[n];
        for(int i=0; i<parcels.length; i++) {
            parcels[i] = scan.nextInt();
        }

        int m = scan.nextInt();
        int[] target = new int[m];
        for(int i=0; i<target.length; i++) {
            target[i] = scan.nextInt();
        }
        scan.close();

        for(int i=0; i<2; i++){
            int key = parcels[i];
            int j = i-1;
            
            while (j>=0 && parcels[j]<key){
                parcels[j+1] = parcels [j];
                j--;
            }

            parcels [j+1]=key;
        }

        int[] SetelahDuaPasses = new int[n];
        for(int i=0; i<parcels.length; i++) {
            SetelahDuaPasses[i] = parcels[i];
        }

        for(int i=2; i<parcels.length; i++) {
            for(int j=0; j<parcels.length-i; j++) {
                if(parcels[j]<parcels[j+1]) {
                    int temp = parcels[j];
                    parcels[j] = parcels[j+1];
                    parcels[j+1] = temp;
                }
            }
        }

        System.out.println("==== After 2 Passes ====");
        for(int i=0; i<SetelahDuaPasses.length; i++) {
            System.out.print(SetelahDuaPasses[i]);
            if(i<SetelahDuaPasses.length-1) {
                System.out.print(" ");
            }
        }
        
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for(int i=0; i<parcels.length; i++) {
            System.out.print(parcels[i]);
            if(i<parcels.length-1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        System.out.println("==== Search Results ====");

        for(int i=0; i<target.length; i++) {
            int low=0;
            int high=parcels.length - 1;
            int index=-1;
            int step = 0;

            while(low <= high) {
                int mid = (low + high) / 2;
                step++;

                if(parcels[mid]==target[i]) {
                    index = mid;
                    break;
                } else if(parcels[mid]<target[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if(index != -1) {
                System.out.println(target[i] + ": FOUND at index " + index + " (steps: " + step + ")");
            } else {
                System.out.println(target[i] + ": NOT FOUND (steps: " + step + ")");
            }
        }

    }
}