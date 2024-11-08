import java.util.ArrayList;
import java.util.List;

class Student {
    private String stdName;
    private int stdID;
    private List<Course> enrolledCourses;

    public Student(String stdName, int stdID) {
        this.stdName = stdName;
        this.stdID = stdID;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getstdName() {
        return stdName;
    }

    public int getStdID() {
        return stdID;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        } else {
            System.out.println("Student already enrolled in this course.");
        }
    }

    public void assignGrade(Course course, int grade) {
        if (enrolledCourses.contains(course)) {
            course.assignGrade(this, grade);
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }
}