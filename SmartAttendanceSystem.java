import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    int totalDays;
    int presentDays;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.totalDays = 0;
        this.presentDays = 0;
    }

    void markAttendance(boolean present) {
        totalDays++;
        if (present) {
            presentDays++;
        }
    }

    double getAttendancePercentage() {
        if (totalDays == 0) return 0;
        return (presentDays * 100.0) / totalDays;
    }
}

public class AttendanceSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Smart Attendance Tracking System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance Report");
            System.out.println("4. View Defaulter List");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> markAttendance();
                case 3 -> viewReport();
                case 4 -> defaulterList();
                case 5 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    static void markAttendance() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.id == id) {
                System.out.print("Present today? (true/false): ");
                boolean present = sc.nextBoolean();
                s.markAttendance(present);
                System.out.println("Attendance marked.");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void viewReport() {
        System.out.println("\n--- Attendance Report ---");
        for (Student s : students) {
            System.out.printf("ID: %d | Name: %s | Attendance: %.2f%%\n",
                    s.id, s.name, s.getAttendancePercentage());
        }
    }

    static void defaulterList() {
        System.out.println("\n--- Defaulter List (<75%) ---");
        for (Student s : students) {
            if (s.getAttendancePercentage() < 75) {
                System.out.printf("ID: %d | Name: %s | Attendance: %.2f%%\n",
                        s.id, s.name, s.getAttendancePercentage());
            }
        }
    }
}
