
// initialize Student class
public class Student {

    // set all variable with private to efficiently use setter and getters
    private String studentID;
    private String studentName;
    private int age;
    private String birthDate;
    private String course;
    private String section;
    private String gradeYear;
    private boolean isEnrolled;

    // set-up of constructor
    public Student(String studentID, String studentName, int age, String birthDate, String course, String section, String gradeYear) {

        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.birthDate = birthDate;
        this.course = course;
        this.section = section;
        this.gradeYear = gradeYear;
        this.isEnrolled = false;
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

    public int getAge(){ // student age getter
        return age;
    }
    public void setAge(int age){ // student age setter
        this.age = age;
    }

    public String getBirthDate(){ // student birthdate getter
        return birthDate;
    }
    public void setBirthDate(String birthDate){ // student birhtdate setter
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

    public boolean isEnrolled(){ // student status initialization
        return isEnrolled;
    }
    public void setEnrolled(boolean enrolled){ // student status setter
        isEnrolled = enrolled;
    }
}
