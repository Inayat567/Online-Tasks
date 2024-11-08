import java.util.ArrayList;
import java.util.List;

public class SMS {
    private static List<Student> studentList = new ArrayList<>();
    private static int TotalStudentNumber = 0;

    // Method to add a new student to the list
    public static void addStudent(String StudentName, int StudentID, int StudentAge, String StudentGrade) {
        Student std = new Student(StudentName, StudentID, StudentAge, StudentGrade);
        studentList.add(std);
        TotalStudentNumber++;
    }

    // Method to update student information
    public static void updateStudent(int StudentID, String StudentName, int StudentAge, String StudentGrade) {
        for (Student std : studentList) {
            if (std.getStudentID() == StudentID) {
                std.setStudentName(StudentName);
                std.setStudentAge(StudentAge);
                std.setStudentGrade(StudentGrade);
                return;
            }
        }
    }

    // Method to get student details by ID
    public static Student getStudentDetails(int StudentID) {
        for (Student std : studentList) {
            if (std.getStudentID() == StudentID) {
                return std;
            }
        }
        return null; // Return null if student not found
    }

    // Getter method for TotalStudentNumber
    public static int getTotalStudents() {
        return TotalStudentNumber;
    }
}
