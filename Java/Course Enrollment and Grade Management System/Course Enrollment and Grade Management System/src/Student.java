import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int id;
    private List<Course> enrolledCourses;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.incrementEnrollment();
        } else {
            System.out.println("Error: Student is already enrolled in this course.");
        }
    }

    public void assignGrade(Course course, int grade) {
        if (enrolledCourses.contains(course)) {
            course.assignGrade(this, grade);
        } else {
            System.out.println("Error: Student is not enrolled in this course.");
        }
    }
}
