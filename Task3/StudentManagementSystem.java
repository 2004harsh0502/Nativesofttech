import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String email;

    public Student(int id, String name, int age, String email) {
        this.id    = id;
        this.name  = name;
        this.age   = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age > 0) { // basic validation
            this.age = age;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\n" +
               "Name      : " + name + "\n" +
               "Age       : " + age + "\n" +
               "Email     : " + email;
    }
}

public class StudentManagementSystem {
    private static Map<Integer, Student> studentRecords = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextStudentId = 1;  

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("Welcome to the Student Management System (Admin Mode)");
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    updateStudent();
                    break;
                case "3":
                    removeStudent();
                    break;
                case "4":
                    retrieveStudent();
                    break;
                case "5":
                    listAllStudents();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n====== Student Management Menu ======");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Remove Student Record");
        System.out.println("4. Retrieve Student Details");
        System.out.println("5. List All Student Records");
        System.out.println("6. Exit");
    }


    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        int age = 0;
        while (true) {
            System.out.print("Enter student age: ");
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) {
                    System.out.println("Age must be positive. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Please enter a numeric value.");
            }
        }

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        Student newStudent = new Student(nextStudentId, name, age, email);
        studentRecords.put(nextStudentId, newStudent);
        System.out.println("Student added with ID: " + nextStudentId);
        nextStudentId++;
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid student ID. Operation aborted.");
            return;
        }

        Student student = studentRecords.get(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Current student details:");
        System.out.println(student);

        System.out.print("\nEnter new name (or press Enter to keep unchanged): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter new age (or press Enter to keep unchanged): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                int age = Integer.parseInt(ageInput);
                if (age > 0) {
                    student.setAge(age);
                } else {
                    System.out.println("Invalid age value. Age not updated.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Age not updated.");
            }
        }

        System.out.print("Enter new email (or press Enter to keep unchanged): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            student.setEmail(email);
        }

        System.out.println("Student record updated successfully.");
    }

    private static void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid student ID. Operation aborted.");
            return;
        }

        if (studentRecords.remove(id) != null) {
            System.out.println("Student record with ID " + id + " removed successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void retrieveStudent() {
        System.out.print("Enter student ID to retrieve: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid student ID. Operation aborted.");
            return;
        }

        Student student = studentRecords.get(id);
        if (student != null) {
            System.out.println("\nStudent Details:");
            System.out.println(student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void listAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\nAll Student Records:");
        for (Student student : studentRecords.values()) {
            System.out.println("-------------------------");
            System.out.println(student);
        }
    }
}