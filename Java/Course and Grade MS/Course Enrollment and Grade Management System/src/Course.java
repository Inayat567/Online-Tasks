import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private List<Student> enrolledStudents;
    private List<Integer> grades;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
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

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setEnrolledStudents(Student student) {
        this.enrolledStudents.add(student);
    }

    public void assignGrade(Student student, int grade) {
        grades.add(grade);
    }
}