import java.awt.print.Printable;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static ArrayList<Student> studentRecord = new ArrayList<>(); // set static ArrayList of Student object using studentRecord variable
    static Scanner scanner = new Scanner(System.in); // set static scanner
    public static void main(String[] args){

        int choice = 0;

        do{
            // outputs the Menu Text Screen
            printMenu();
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            // validates user input
            switch (choice){
                case 1 -> viewAllStudents();
                case 2 -> addNewStudent();
                case 3 -> removeStudent();
                case 4 -> viewStudentDetails();
            }
        }
        while(choice != 0); // stops the program when user inputs "0"
    }

    // method for the Menu Text Screen
    static void printMenu(){
        System.out.println("====College Panel====");
        System.out.println("1. View All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Remove Student");
        System.out.println("4. View Student Details (not available)");
        System.out.println("0. back");

    }

    // method for Viewing all students information
    static void viewAllStudents(){

    }

    // method for adding new student
    static void addNewStudent(){

        String studentId = askStudentId(); // calls askStudentId method

        String studentName = askStudentName(); // calls askStudentName method

        int studentAge = askStudentAge(); // calls askStudentAge method

        // debug
        System.out.println(studentId);
        System.out.println(studentName);
        System.out.println(studentAge);
    }

    static public String askStudentId(){

        while(true){

            System.out.print("Enter Student ID (1234-1234567): ");
            String studentId = scanner.nextLine().trim(); // reads user input

            String[] parts =  studentId.split("-"); // parsed each ID to an array and made - as the separator

            if (parts.length != 2) { // check if user entered an ID number that is less than
                System.out.println("Invalid ID number format(1234-1234567)\n");
                continue; // restarts the loop
            }

            String first = parts[0].trim(); // initialized the first part of ID (before -)
            String second = parts[1].trim(); // initialized the second part of ID (after -)

            if(!first.matches("[0-9]+") && !second.matches("[0-9]+")) System.out.println("Student ID can only contain numbers\n"); // check if user input only contains numbers
            else if(studentId.contains(" ")) System.out.println("Student ID contains spaces\n"); // check if ID contains space
            else if(first.length() != 4) System.out.println("Id First part must be 4 digits\n"); // check if first part of ID has valid count
            else if(second.length() != 7) System.out.println("Id Second part must be 7 digits\n"); // check if second part of ID has valid count
            else if(!studentId.contains("-")) System.out.println("Student ID must contain '-' as separator\n"); // checks if student ID doesn't have a "-" (this else-if statement is unnecessary)
            else return studentId; // returns the student id
        }
    }

    static public String askStudentName(){

        // uses while loop for good user experience
        while(true){

            // asks user for student name
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine(); // accepts user input for student name

            if(studentName.trim().isEmpty()) System.out.println("Student Name cannot be empty\n"); // check if name is empty
            else if(studentName.matches("[A-Za-z ]+")) return studentName; // check if name contains alphabetical input
            else System.out.println("Student Name can only contain letters\n"); // prompts that input can only be letters
        }
    }

    static public int askStudentAge(){

        while(true){

            System.out.print("Enter Student Age: ");
            String studentAge = scanner.nextLine().trim(); // reads user input

            if(studentAge.isEmpty()) System.out.println("Student Age cannot be empty\n"); // checks if input is empty
            else if(!studentAge.matches("[0-9]+")) System.out.println("Student Age can only contain numbers\n"); // check if input only contains numbers
            else if(studentAge.length() == 1) System.out.println("Student Age cannot be lower than 2 digits\n"); // checks if age is only 1 digit
            else if(studentAge.length() >= 3) System.out.println("Student Age cannot exceed 3 digits\n"); // check if age exceeds more than 2 digit
            else return Integer.parseInt(studentAge); // return the String data type and turns it into an int
        }
    }

    // method for removing student
    static void removeStudent(){

    }

    // method for viewing student details
    static void viewStudentDetails(){

    }
}
