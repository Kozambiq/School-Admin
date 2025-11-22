import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    public static final DateTimeFormatter BIRTHDATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // static format for time
    static ArrayList<Student> studentRecord = new ArrayList<>(); // set static ArrayList of Student object using studentRecord variable
    static Scanner scanner = new Scanner(System.in); // set static scanner

    public static void main(String[] args){
        int choice = 0;

        do{
            try {
                // outputs the Menu Text Screen
                printMenu(); // calls the printMenu method
                System.out.print("Choose an option: ");
                choice = scanner.nextInt(); // reads user input
                scanner.nextLine();

                // validates user input
                switch (choice) {
                    case 1 -> viewAllStudents();
                    case 2 -> addNewStudent();
                    case 3 -> removeStudent();
                    case 4 -> viewStudentDetails();
                    default -> System.out.println("Invalid choice\n");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid Input\n");
                scanner.nextLine();
                choice -= 1;
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
    //debugging stage
    // method for adding new student
    static void addNewStudent(){
        //String studentId = askStudentId(); // calls askStudentId method
        //String studentName = askStudentName(); // calls askStudentName method
        String studentGender = askStudentGender();
        //int studentAge = askStudentAge(); // calls askStudentAge method
        //LocalDate studentBD = askStudentBirthDate();
        //String studentCourse = askStudentCourse();
        //String studentSection = askStudentSection();
        // debug
        //System.out.println(studentId);
        //System.out.println(studentName);
        //System.out.println(studentAge);
        //System.out.println(studentBD);
        //System.out.println(studentCourse);
        //System.out.println(studentSection);
        System.out.println(studentGender);
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
            else if(studentId.contains(" ")) System.out.println("Student ID cannot contains spaces\n"); // check if ID contains space
            else if(first.length() != 4) System.out.println("Id First part must be 4 digits\n"); // check if first part of ID has valid count
            else if(second.length() != 7) System.out.println("Id Second part must be 7 digits\n"); // check if second part of ID has valid count
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

    static public String askStudentGender(){
        while(true){
            System.out.print("Enter Student Gender(M/F): ");
            String studentGender = scanner.nextLine().trim(); // reads user input
            if(studentGender.isEmpty()) System.out.println("Student Gender cannot be empty\n"); // check if input is empty
            else if(studentGender.length() > 1) System.out.println("Student Gender can only be M or F\n"); // check if input is more than one letter
            else if(!studentGender.matches("[MmFf]")) System.out.println("Student Gender can only be one letter(M/F)\n"); // checks if input is valid
            else if(studentGender.equalsIgnoreCase("M")) return "Male"; // validates input if male
            else if(studentGender.equalsIgnoreCase("F")) return "Female"; // validates input if female
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

    static public LocalDate askStudentBirthDate(){
        while(true){
            System.out.print("Enter Student Birth Date (MM/DD/YYYY): ");
            String studentBD = scanner.nextLine(); // reads input
            try{
                return LocalDate.parse(studentBD, BIRTHDATE_FORMAT); // parses the input and checks if it has the correct format
            }
            catch(DateTimeParseException e){ // catches if there is error
                System.out.println("Invalid Date Format\n");
            }
        }
    }

    static public String askStudentCourse(){
        while(true){
            System.out.print("Enter Student Course: ");
            String studentCourse = scanner.nextLine(); // read input
            if(studentCourse.isEmpty()) System.out.println("Student Course cannot be empty\n"); // check if input is empty
            else if(studentCourse.matches("[0-9]+")) System.out.println("Student Course can only contain letters\n"); // check if input contains numbers
            else return studentCourse; // returns the value
        }
    }

    static public String askStudentSection(){
        while(true){
            System.out.print("Enter Student Section: ");
            String studentSection = scanner.nextLine().trim().toUpperCase(); // reads the user input
            String[] parts = studentSection.split("-"); // created and divided the input into array and making "-" the seperator
            if(parts.length != 2){ // checks if the input format is incorrect
                System.out.println("Invalid Section Format(LET-123)\n");
                continue; // restarts loop
            }
            String first = parts[0].trim(); // divided first part
            String second = parts[1].trim(); // divided second part
            if(studentSection.contains(" ")) System.out.println("Student Section cannot contain spaces\n"); // checks if input contains spaces
            else if(!first.matches("[A-Za-z]+")) System.out.println("First part of Student Section can only contain letters\n"); // if input does not contain letters then prints the statement
            else if(!second.matches("[0-9]+"))  System.out.println("Second part of Student Section can only contain numbers\n"); // if input does not contain numbers then prints the statement
            else if(first.length() <= 2) System.out.println("First part cannot be less than 2 letters\n"); // if before dash part is less then or equal to 2 then print statement
            else if(second.length() <= 2) System.out.println("Second part cannot be less than 2 numbers\n"); // if after dash part is less than or equal to 2 then print statement
            else return studentSection;
        }
    }

    // method for removing student
    static void removeStudent(){

    }

    // method for viewing student details
    static void viewStudentDetails(){

    }
}
