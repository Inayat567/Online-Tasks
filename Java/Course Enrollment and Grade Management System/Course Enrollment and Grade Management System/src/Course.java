import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int enrolledStudents;
    private List<Integer> grades;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0;
        this.grades = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void incrementEnrollment() {
        enrolledStudents++;
    }

    public static int getTotalEnrolledStudents() {
        int totalEnrolledStudents = 0;
        for (Course course : courses) {
            totalEnrolledStudents += course.getEnrolledStudents();
        }
        return totalEnrolledStudents;
    }

    public void assignGrade(Student student, int grade) {
        grades.add(grade);
    }
}
