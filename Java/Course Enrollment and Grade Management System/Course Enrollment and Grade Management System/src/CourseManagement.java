import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();

    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courses.add(newCourse);
    }

    public static void enrollStudent(Student student, Course course) {
        if (courses.contains(course) && course.getEnrolledStudents() < course.getMaxCapacity()) {
            student.enrollInCourse(course);
        } else {
            System.out.println("Error: Course is either full or not found.");
        }
    }

    public static void assignGrade(Student student, Course course, int grade) {
        if (courses.contains(course) && student.getEnrolledCourses().contains(course)) {
            student.assignGrade(course, grade);
        } else {
            System.out.println("Error: Invalid course or student.");
        }
    }

    public static int calculateOverallGrade(Student student) {
        int totalGrades = 0;
        int numberOfCourses = student.getEnrolledCourses().size();

        for (Course course : student.getEnrolledCourses()) {
            // Assume each course has a grade; you may need to modify this based on your actual implementation
            // For simplicity, we assume grades are stored in the Course class
            List<Integer> grades = course.getGrades();
            if (!grades.isEmpty()) {
                totalGrades += grades.get(0); // Use appropriate logic to calculate the overall grade
            }
        }

        // Calculate the average grade
        return numberOfCourses > 0 ? totalGrades / numberOfCourses : 0;
    }
}
