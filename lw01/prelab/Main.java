import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("lw01/prelab/jobs.txt"));
        ArrayList<PrintJob> jobs = new ArrayList<>();

        while(scanner.hasNext()){
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            if(type.equals("MONO")){
                jobs.add(new MonoPrint(id, pages));
            }else{
                jobs.add(new ColourPrint(id, pages));
            }
        }

        for(PrintJob job: jobs){
            System.out.println(job.summary());
        }

        scanner.close();
    }
}