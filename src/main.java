import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    private static final String CSV_FILE = "./students_record.csv";
    public static final DateTimeFormatter BIRTHDATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // static format for time
    static ArrayList<StudentRecord> records = new ArrayList<>(); // set static ArrayList of Student object using studentRecord variable
    static Scanner scanner = new Scanner(System.in); // set static scanner

    public static void main(String[] args) throws IOException {
        loadCSVFile();
        int choice = 0;

        /*
         * Displays the main menu and handles user input for menu selection.
         * Handles invalid input using a try-catch block
         * Stops looping when user enters 0
         */
        do{
            try {
                // Display the menu
                printMenu();
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                // Execute option based on user choice
                switch (choice) {
                    case 1 -> viewAllStudents();
                    case 2 -> addNewStudent();
                    case 3 -> removeStudent();
                    case 4 -> viewStudentDetails();
                    default -> System.out.println("Invalid choice\n");
                }

            } catch (InputMismatchException | IOException e){
                System.out.println("Invalid Input\n");
                scanner.nextLine(); // clears the invalid input
                choice -= 1; // ignore the invalid input
            }
        }
        while (choice != 0);
    }

    // method for the Menu Screen
    static void printMenu(){
        System.out.println("====College Panel====");
        System.out.println("1. View All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Remove Student");
        System.out.println("4. View Student Details (not available)");
        System.out.println("0. back");
    }

    /*
     * Display all student records in a formatted table
     */
    static void viewAllStudents(){
        System.out.println("====All Students====");

        // Table header
        System.out.printf("%-5s %-20s %-40s %-10s %-7s %-16s %-10s %-10s%n",
                "#", "Student ID", "Name", "Gender", "Age", "Birth Date", "Course", "Section");
        int count = 1;

        // Print each student record in table format
        for (StudentRecord record : records){
            System.out.printf("%-5d %-20s %-40s %-10s %-7s %-16s %-10s %-10s%n",
                    count++, //increase the counter
                    record.getStudentID(),
                    record.getStudentName(),
                    record.getStudentGender(),
                    record.getStudentAge(),
                    record.getStudentBirthDate(),
                    record.getStudentCourse(),
                    record.getStudentSection()
            );
        }
    }

    /*
     * User inputs Student information
     */
    static void addNewStudent() throws IOException {
        String studentId = askStudentId();

        // Checks the records if there is an existing Student ID
        for (StudentRecord record : records){
            if (studentId.equals(record.getStudentID())){
                System.out.println("Student ID already exists\n");
                return;
            }
        }

        String studentName = askStudentName();

        // Check the records if there is an existing Student Name
        for (StudentRecord record : records){
            if (studentName.equalsIgnoreCase(record.getStudentName())){
                System.out.println("Student name already exists\n");
                return;
            }
        }

        String studentGender = askStudentGender();
        String studentAge = askStudentAge();
        LocalDate studentBD = askStudentBirthDate();
        String studentCourse = askStudentCourse();
        String studentSection = askStudentSection();
        String studentYear = askStudentYear();

        StudentRecord record = new StudentRecord(studentId, studentName, studentGender, studentAge, studentBD, studentCourse, studentSection, studentYear);
        records.add(record); // Add the processed information to the ArrayList
        saveCSVFile(record); // Saves the information in the file
    }

    /*
     * Asks the user for the ID of student
     * Split input into 2 parts for different category
     * Validates the input
     */
    static public String askStudentId(){
        while (true){
            System.out.print("Enter Student ID (1234-1234567): ");
            String studentId = scanner.nextLine().trim();
            String[] parts =  studentId.split("-"); // Split the input into 2 parts

            // Ensures that input is valid before accepting
            if (parts.length != 2) {
                System.out.println("Invalid ID number format(1234-1234567)\n");
                continue; // restarts the loop
            }

            String first = parts[0].trim();
            String second = parts[1].trim();

            if (!first.matches("[0-9]+") && !second.matches("[0-9]+")) System.out.println("Student ID can only contain numbers\n"); // Ensures that input can only consist of numbers
            else if (studentId.contains(" ")) System.out.println("Student ID cannot contains spaces\n"); // Ensures that input cannot have spaces
            else if (first.length() != 4) System.out.println("Id First part must be 4 digits\n"); // Ensure that part[1] is 4 digits
            else if (second.length() != 7) System.out.println("Id Second part must be 7 digits\n"); // Ensure that part[2] is 7 digits
            else return studentId;
        }
    }

    /*
     * Asks the user for the Student Name
     * Validated the input
     */
    static public String askStudentName(){
        while (true){
            System.out.print("Enter Student Name(Surname, First Name, Middle Name): ");
            String studentName = scanner.nextLine().toUpperCase();

            if (studentName.trim().isEmpty()) System.out.println("Student Name cannot be empty\n"); // Ensures that input is not empty
            else if (!studentName.contains(",")) System.out.println("Student Name must contain coma as separator\n"); // Ensures that there is a coma to separate different sections of the name
            else if (studentName.matches("[A-Z ,]+")) return studentName; // Ensures that input only accepts Alphabetical, Spaces, and Commas
        }
    }

    /*
     * Asks user for student gender
     * Validates the input
     */
    static public String askStudentGender(){
        while (true){
            System.out.print("Enter Student Gender(M/F): ");
            String studentGender = scanner.nextLine().trim();

            if (studentGender.isEmpty()) System.out.println("Student Gender cannot be empty\n"); // Ensures that input is not empty
            else if (studentGender.length() > 1 ||
                     !studentGender.matches("[MmFf]")) System.out.println("Student Gender can only be M or F\n"); // Ensures that char is only accepted by the input
            else if (studentGender.equalsIgnoreCase("M")) return "Male";
            else if (studentGender.equalsIgnoreCase("F")) return "Female";
        }
    }

    /*
     * Asks the user for the Student Age
     * Validates user input
     */
    static public String askStudentAge(){
        while (true){
            System.out.print("Enter Student Age: ");
            String studentAge = scanner.nextLine().trim();
            if (studentAge.isEmpty()) System.out.println("Student Age cannot be empty\n"); // Ensures that input is not empty
            else if (!studentAge.matches("[0-9]+")) System.out.println("Student Age can only contain numbers\n"); // Ensures that input can only contain numbers
            else if (studentAge.length() == 1) System.out.println("Student Age cannot be lower than 2 digits\n"); // Ensures that age cannot be less than 2 digits
            else if (studentAge.length() >= 3) System.out.println("Student Age cannot exceed 3 digits\n"); // Ensures that age cannot exceed 3 digits
            else return studentAge;
        }
    }

    /*
     * Asks the user for the Student BirthDate
     * Validates the date format
     */
    static public LocalDate askStudentBirthDate(){
        while (true){
            System.out.print("Enter Student Birth Date (MM/DD/YYYY): ");
            String studentBD = scanner.nextLine();
            try{
                return LocalDate.parse(studentBD, BIRTHDATE_FORMAT); // convert the input into the proper date format
            } catch (DateTimeParseException e){
                System.out.println("Invalid Date Format\n");
            }
        }
    }

    /*
     *Asks user for Student Course
     * Validates the input
     */
    static public String askStudentCourse(){
        while (true){
            System.out.print("Enter Student Course: ");
            String studentCourse = scanner.nextLine();

            if (studentCourse.isEmpty()) System.out.println("Student Course cannot be empty\n"); // Ensures that input is not empty
            else if (studentCourse.matches("[0-9]+")) System.out.println("Student Course can only contain letters\n"); // Ensures that input can only be Alphabet
            else return studentCourse;
        }
    }

    /*
     * Asks the user for Student Section
     * Splits the input into 2 parts
     * Validates the input
     */
    static public String askStudentSection(){
        while (true){
            System.out.print("Enter Student Section: ");
            String studentSection = scanner.nextLine().trim().toUpperCase();

            String[] parts = studentSection.split("-"); // Splits the input into 2 parts

            // Ensure that input has the right format
            if (parts.length != 2){
                System.out.println("Invalid Section Format(LET-123)\n");
                continue; // repeat the loop from the top
            }

            String first = parts[0].trim();
            String second = parts[1].trim();

            if (studentSection.contains(" ")) System.out.println("Student Section cannot contain spaces\n"); // Ensure that input does not contain spaces
            else if (!first.matches("[A-Za-z]+")) System.out.println("First part of Student Section can only contain letters\n"); // Ensures that part[0] only contains letters
            else if (!second.matches("[0-9]+"))  System.out.println("Second part of Student Section can only contain numbers\n"); // Ensures that part[1] only contain numbers
            else if (first.length() <= 2) System.out.println("First part cannot be less than 2 letters\n"); // Ensures that first is valid
            else if (second.length() <= 2) System.out.println("Second part cannot be less than 2 numbers\n"); // Ensures that second is valid
            else return studentSection;
        }
    }

    /*
     * Asks user for Student Year
     * Validates the input
     */
    static public String askStudentYear(){
        while (true){
            System.out.print("Enter Student Year(1st, 2nd, ....): ");
            String studentYear = scanner.nextLine().trim().toUpperCase();

            if (studentYear.isEmpty()) System.out.println("Student Year cannot be empty\n"); // Ensures that input is not empty
            else if (!studentYear.matches("^(1ST|2ND|3RD|4TH|5TH|6TH)$")) System.out.println("Invalid Year\n"); // Ensures that input only match inside the regex
            else return studentYear;
        }
    }


    // method for removing student
    static void removeStudent(){

    }

    // method for viewing student details
    static void viewStudentDetails(){

    }

    /*
     * Create a new file
     * Saves the information per line
     */
    static void saveCSVFile(StudentRecord record) {

        // Created a file that saves it to the dir of CSV_FILE
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            pw.println(record.toCSVLine()); // Inputs the information per line
        } catch (IOException e){
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }


    /*
     * Looks for the file and reads its contents then outputs its information to the system
     * The loop goes until the content of the file is empty
     */
    static void loadCSVFile() throws IOException {
        File file = new File(CSV_FILE); // Looks for the file

        // Made a scanner to look inside the contents of the file
        try (Scanner fileScanner = new Scanner(new File(CSV_FILE))){

            // Loops continuously until it reaches the end of the file
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                StudentRecord recordLine = StudentRecord.fromCSVLine(line); // transfer the different parts from the file to the system
                records.add(recordLine);
            }
        }
    }
}
