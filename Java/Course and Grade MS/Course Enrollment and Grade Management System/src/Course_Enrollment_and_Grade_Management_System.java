import java.util.List;
import java.util.Scanner;

public class Course_Enrollment_and_Grade_Management_System {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    enrollStudent();
                    break;
                case 3:
                    assignGrade();
                    break;
                case 4:
                    calculateOverallGrade();
                    break;
                case 5:
                    System.out.println("\nExiting the program. Goodbye!\n");
                    System.exit(0);
                default:
                    System.out.println("Bad choice. Please try again.\n");
            }
        }
    }

    private static void displayMenu() {
        System.out.println(
                "\n\n\n##########********************    === Administrator Interface ===                   ********************##########\n");
        System.out.println(
                "##########********************    1. Add a new course                               ********************##########");
        System.out.println(
                "##########********************    2. Enroll a student                               ********************##########");
        System.out.println(
                "##########********************    3. Assign a grade                                 ********************##########");
        System.out.println(
                "##########********************    4. Calculate overall course grade for a student   ********************##########");
        System.out.println(
                "##########********************    5. Exit                                           ********************##########");
    }

    private static int getUserChoice() {
        System.out.print("##########********************    Enter your choice (1-5):        ");
        while (!scanner.hasNextInt()) {
            System.out.println(
                    "##########********************    Invalid! Please enter any number.      ********************##########");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    
    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.next();

        System.out.print("Enter course name: ");
        String courseName = scanner.next();

        System.out.print("Enter maximum capacity: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid! Please enter any number.");
            scanner.next();
        }
        int maxCapacity = scanner.nextInt();

        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
        System.out.println("Course added successfully!");
    }

    private static void enrollStudent() {
        System.out.print("Enter student name: ");
        String studentName = scanner.next();

        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();

        System.out.print("Enter course code to enroll in: ");
        String courseCode = scanner.next();

        Student student = new Student(studentName, studentID);
        Course course = findCourseByCode(courseCode);

        if (course != null) {
            CourseManagement.enrollStudent(student, course);
            System.out.println("Student registered successfully!");
        } else {
            System.out.println("Error: Course not found.");
        }
    }

    private static void assignGrade() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();

        System.out.print("Enter course code: ");
        String courseCode = scanner.next();

        System.out.print("Enter grade: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid! Please enter any number.");
            scanner.next();
        }
        int grade = scanner.nextInt();

        System.out.println("Available Courses:");
        for (Course c : CourseManagement.getCourses()) {
            System.out.println("Course Code: " + c.getCourseCode() + ", Course Name: " + c.getCourseName());
        }
    
        System.out.println("\nAvailable Students:");
        for (Course c : CourseManagement.getCourses()) {
            for (Student s : c.getEnrolledStudents()) {
                System.out.println("Student ID: " + s.getStdID() + ", Student Name: " + s.getstdName());
            }
        }

        Student student = findStudentById(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            CourseManagement.assignGrade(student, course, grade);
            System.out.println("Student grade updated successfully!");
        } else {
            System.out.println("Error: Student or course not found.");
        }
    }

    private static void calculateOverallGrade() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();

        Student student = findStudentById(studentID);

        if (student != null) {
            int overallGrade = CourseManagement.calculateOverallGrade(student);
            System.out.println("Overall grade for student " + student.getstdName() + " is: " + overallGrade);
        } else {
            System.out.println("Error: Student not found.");
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
            List<Student> enrolledStudents = course.getEnrolledStudents();
            if (enrolledStudents != null) {
                for (Student student : enrolledStudents) {
                    if (student.getStdID() == studentId) {
                        return student;
                    }
                }
            }
        }
        return null;
    }
}





