import java.util.ArrayList;
import java.util.List;

class CourseManagement {
    private static List<Course> courses = new ArrayList<>();

    public static List<Course> getCourses() {
        return courses;
    }

    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courses.add(newCourse);
    }

    public static void enrollStudent(Student student, Course course) {
        if (courses.contains(course) && course.getEnrolledStudents().size() < course.getMaxCapacity()) {
            student.enrollInCourse(course);
            course.setEnrolledStudents(student);
        } else {
            System.out.println("Error: : It seems that course is full");
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
            List<Integer> grades = course.getGrades();
            if (!grades.isEmpty()) {
                totalGrades += grades.get(0);
            }
        }
        return numberOfCourses > 0 ? totalGrades / numberOfCourses : 0;
    }
}