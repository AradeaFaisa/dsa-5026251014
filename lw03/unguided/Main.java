import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== Event Check-In Results =====");

        Set<String> registrationId = new LinkedHashSet<>();
        Set<String> checkinId = new LinkedHashSet<>();

        int rejected = 0;

        Scanner scan1 = new Scanner(
            Main.class.getResourceAsStream("registrations.txt")
        );
        while (scan1.hasNextLine()) {
            String studentId = scan1.nextLine();

            if (!studentId.isEmpty()) {
                registrationId.add(studentId);
            }
        }
        scan1.close();

        Scanner scan2 = new Scanner(
            Main.class.getResourceAsStream("checkins.txt")
        );
        while (scan2.hasNextLine()) {
            String studentId = scan2.nextLine();

            if (studentId.isEmpty()) {
                continue;
            }

            if (!registrationId.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejected++;

            } else if (checkinId.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejected++;

            } else {
                checkinId.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }
        scan2.close();

        System.out.println();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registrationId.size());
        System.out.println("Successful check-ins: " + checkinId.size());
        System.out.println("Absent students: " +(registrationId.size() - checkinId.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}