import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// initialize Student class
public class StudentRecord {

    public static final DateTimeFormatter BIRTHDATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // set all variable with private to efficiently use setter and getters
    private String studentID;
    private String studentName;
    private String gender;
    private String age;
    private LocalDate birthDate;
    private String course;
    private String section;
    private String gradeYear;

    // set-up of constructor
    public StudentRecord(String studentID, String studentName, String gender, String age, LocalDate birthDate, String course, String section, String gradeYear) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.birthDate = birthDate;
        this.course = course;
        this.section = section;
        this.gradeYear = gradeYear;
    }

    public String getStudentID(){ // student id getter
        return studentID;
    }
    public void setStudentID(String studentID){ // student id setter
        this.studentID = studentID;
    }

    public String getStudentName(){ // student name getter
        return studentName;
    }
    public void setStudentName(String studentName){ // student name setter
        this.studentName = studentName;
    }

    public String getStudentGender(){
        return gender;
    }
    public void setStudentGender(String gender){
        this.gender = gender;
    }

    public String getStudentAge(){ // student age getter
        return age;
    }
    public void setStudentAge(String age){ // student age setter
        this.age = age;
    }

    public LocalDate getBirthDate(){ // student birthdate getter
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate){ // student birthdate setter
        this.birthDate = birthDate;
    }

    public String getCourse(){ // student course getter
        return course;
    }
    public void setCourse(String course){ // student course setter
        this.course = course;
    }

    public String getSection(){ // student section getter
        return section;
    }
    public void setSection(String section){ // student section setter
        this.section = section;
    }

    public String getGradeYear(){ // student grade year getter
        return gradeYear;
    }
    public void setGradeYear(String gradeYear){ // student grade year setter
        this.gradeYear = gradeYear;
    }

    public String toCSVLine(){
        return String.join(",", getStudentID(), getStudentName(), getStudentGender(), getStudentAge(), getBirthDate().format(BIRTHDATE_FORMAT), getCourse(), getSection(), getGradeYear());
    }
}
