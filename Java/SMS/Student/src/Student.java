public class Student {
    private String StudentName;
    private int StudentID;
    private int StudentAge;
    private String StudentGrade;

    public Student(String StudentName, int StudentID, int StudentAge, String StudentGrade) {
        this.StudentName = StudentName;
        this.StudentID = StudentID;
        this.StudentAge = StudentAge;
        this.StudentGrade = StudentGrade;
    }

    // Getter and Setter methods for all instance variables
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public int getStudentID() {
        return StudentID;
    }

    public int getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(int StudentAge) {
        this.StudentAge = StudentAge;
    }

    public String getStudentGrade() {
        return StudentGrade;
    }

    public void setStudentGrade(String StudentGrade) {
        this.StudentGrade = StudentGrade;
    }
}
