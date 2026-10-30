import java.util.*;

public class Main{
    public static void main(String[] args){
        
        Scanner scan = new Scanner(Main.class.getResourceAsStream("/branches.txt"));

        int n = scan.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i<branches.length; i++){
            branches[i] = scan.nextInt();
        }

        int m = scan.nextInt();
        int[] target = new int[m];

        for (int i=0; i<target.length; i++){
            target[i] = scan.nextInt();
        }

        scan.close();

        for(int i = 1; i<=3; i++){
            int key = branches[i];
            int j = i-1;

            while (j>=0 && branches[j]<key){
                branches[j+1] = branches [j];
                j--;
            }

            branches [j+1]=key;
        }

        for (int i=0;i<branches.length; i++){
            System.out.print(branches[i]);

            if (i< branches.length-1){
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i=0; i<target.length; i++){
            int index = -1;

            for (int j=0; j<branches.length; j++){
                if (branches[j]==target[i]){
                    index = j;
                    break;
                }
            }

            if (index != -1){
                System.out.println(target[i]+ ": FOUND at index " + index);
            }else{
                System.out.println(target[i]+ ": NOT FOUND");
            }
        }
    }
}