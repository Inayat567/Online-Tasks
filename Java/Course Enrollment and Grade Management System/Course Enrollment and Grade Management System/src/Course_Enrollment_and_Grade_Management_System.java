import java.util.Scanner;

public class AdministratorInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("1. Add a new course");
            System.out.println("2. Enroll students");
            System.out.println("3. Assign grades");
            System.out.println("4. Calculate overall course grades");
            System.out.println("0. Exit");

            // Get user input
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Get course information and call addCourse method
                    System.out.println("Enter course code:");
                    String courseCode = scanner.next();
                    System.out.println("Enter course name:");
                    String courseName = scanner.next();
                    System.out.println("Enter max capacity:");
                    int maxCapacity = scanner.nextInt();
                    CourseManagement.addCourse(courseCode, courseName, maxCapacity);
                    break;
                case 2:
                    // Get student and course information and call enrollStudent method
                    System.out.println("Enter student name:");
                    String studentName = scanner.next();
                    System.out.println("Enter student ID:");
                    int studentId = scanner.nextInt();
                    System.out.println("Enter course code to enroll in:");
                    String enrollCourseCode = scanner.next();
                    Student student = new Student(studentName, studentId);
                    Course courseToEnroll = findCourseByCode(enrollCourseCode);
                    if (courseToEnroll != null) {
                        CourseManagement.enrollStudent(student, courseToEnroll);
                    } else {
                        System.out.println("Error: Course not found.");
                    }
                    break;
                case 3:
                    // Get student, course, and grade information and call assignGrade method
                    System.out.println("Enter student ID:");
                    int assignStudentId = scanner.nextInt();
                    System.out.println("Enter course code:");
                    String assignCourseCode = scanner.next();
                    System.out.println("Enter grade:");
                    int grade = scanner.nextInt();
                    Student assignStudent = findStudentById(assignStudentId);
                    Course assignCourse = findCourseByCode(assignCourseCode);
                    if (assignStudent != null && assignCourse != null) {
                        CourseManagement.assignGrade(assignStudent, assignCourse, grade);
                    } else {
                        System.out.println("Error: Student or course not found.");
                    }
                    break;
                case 4:
                    // Get student information and call calculateOverallGrade method
                    System.out.println("Enter student ID:");
                    int overallStudentId = scanner.nextInt();
                    Student overallStudent = findStudentById(overallStudentId);
                    if (overallStudent != null) {
                        int overallGrade = CourseManagement.calculateOverallGrade(overallStudent);
                        System.out.println("Overall grade for student " + overallStudent.getName() + " is: " + overallGrade);
                    } else {
                        System.out.println("Error: Student not found.");
                    }
                    break;
                case 0:
                    // Exit the program
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : CourseManagement.getCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudentById(int studentId) {
        for (Course course : CourseManagement.getCourses()) {
            for (Student student : course.getEnrolledStudents()) {
                if (student.getId() == studentId) {
                    return student;
                }
            }
        }
        return null;
    }
}
