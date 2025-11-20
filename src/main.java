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

        String studentName = askStudentName(); // calls askStudentName method
        System.out.println(studentName); //debug
    }
    static public String askStudentName(){

        // uses while loop for good user experience
        while(true){

            // asks user for student name
            System.out.println("Enter Student Name: ");
            String studentName = scanner.nextLine(); // accepts user input for student name

            if(studentName.trim().isEmpty()){ // check if name is empty
                System.out.println("Student Name cannot be empty");
            }
            else if(studentName.matches("[A-Za-z ]+")){ // check if name contains alphabetical input
                return studentName; // returns output
            }
            else System.out.println("Student Name can only contain letters"); // prompts that input can only be letters
        }
    }

    // method for removing student
    static void removeStudent(){

    }

    // method for viewing student details
    static void viewStudentDetails(){

    }
}
