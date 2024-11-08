// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.table.DefaultTableModel;
// import java.util.ArrayList;

// public class StudentManagementSystem extends JFrame {
//     private ArrayList<Student> students;
//     private JTable studentTable;
//     private DefaultTableModel tableModel;

//     public StudentManagementSystem() {
//         students = new ArrayList<>();

//         setTitle("Student Management System");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JMenuBar menuBar = new JMenuBar();
//         JMenu studentMenu = new JMenu("Student");
//         JMenuItem addStudentMenuItem = new JMenuItem("Add Student");
//         JMenuItem updateStudentMenuItem = new JMenuItem("Update Student");
//         JMenuItem viewStudentDetailsMenuItem = new JMenuItem("View Student Details");
//         studentMenu.add(addStudentMenuItem);
//         studentMenu.add(updateStudentMenuItem);
//         studentMenu.add(viewStudentDetailsMenuItem);
//         menuBar.add(studentMenu);
//         setJMenuBar(menuBar);

//         String[] columnNames = {"ID", "Name", "Course", "Grade"};
//         tableModel = new DefaultTableModel(columnNames, 0);
//         studentTable = new JTable(tableModel);
//         JScrollPane tableScrollPane = new JScrollPane(studentTable);

//         getContentPane().setLayout(new BorderLayout());
//         getContentPane().add(tableScrollPane, BorderLayout.CENTER);

//         addStudentMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 // Display a form to add a new student
//                 String name = JOptionPane.showInputDialog("Enter student name:");
//                 String course = JOptionPane.showInputDialog("Enter student course:");
//                 String grade = JOptionPane.showInputDialog("Enter student grade");
//                 int id = students.size() + 1;
//                 Student newStudent = new Student(id, name, course, grade);
//                 students.add(newStudent);
//                 updateStudentTable();
//             }
//         });

//         updateStudentMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = studentTable.getSelectedRow();
//                 if (selectedRow >= 0) {
//                     Student selectedStudent = students.get(selectedRow);
//                     String name = JOptionPane.showInputDialog("Update student name:", selectedStudent.name);
//                     String course = JOptionPane.showInputDialog("Update student course:", selectedStudent.course);
//                     String grade = JOptionPane.showInputDialog("Update student grade:", selectedStudent.grade);
//                     selectedStudent.name = name;
//                     selectedStudent.course = course;
//                     selectedStudent.grade = grade;
//                     updateStudentTable();
//                 } else {
//                     JOptionPane.showMessageDialog(StudentManagementSystem.this, "Please select a student to update.");
//                 }
//             }
//         });

//         viewStudentDetailsMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                     if (students.isEmpty()) {
//                         JOptionPane.showMessageDialog(StudentManagementSystem.this, "No student records to display.");
//                         return;
//                     }
                
//                     JFrame detailsFrame = new JFrame("Student Details");
//                     detailsFrame.setSize(600, 400);
                
//                     String[] columnNames = {"ID", "Name", "Course", "Grade"};
//                     DefaultTableModel detailsTableModel = new DefaultTableModel(columnNames, 0);
//                     JTable detailsTable = new JTable(detailsTableModel);
                
//                     for (Student student : students) {
//                         Object[] rowData = {student.id, student.name, student.course, student.grade};
//                         detailsTableModel.addRow(rowData);
//                     }
                
//                     JScrollPane tableScrollPane = new JScrollPane(detailsTable);
//                     detailsFrame.add(tableScrollPane);
                
//                     detailsFrame.setVisible(true);
                
//             }
//         });
//     }

//     class Student {
//         private int id;
//         private String name;
//         private String course;
//         private String grade;

//         public Student(int id, String name, String course, String grade) {
//             this.id = id;
//             this.name = name;
//             this.course = course;
//             this.grade = grade;
//         }
//     }

//     private void updateStudentTable() {
//         // Clear the table and repopulate it with student data
//         tableModel.setRowCount(0);
//         for (Student student : students) {
//             Object[] rowData = {student.id, student.name, student.course, student.grade};
//             tableModel.addRow(rowData);
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new StudentManagementSystem().setVisible(true);
//             }
//         });
//     }
// }





















// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.table.DefaultTableModel;
// import java.util.ArrayList;

// public class StudentManagementSystem extends JFrame {
//     private ArrayList<Student> students;
//     private ArrayList<String> courses;
//     private JTable studentTable;
//     private DefaultTableModel tableModel;
//     private JComboBox<String> courseDropdown;
//     private JComboBox<Student> studentDropdown;
//     private JTextField gradeTextField;

//     public StudentManagementSystem() {
//         students = new ArrayList<>();
//         courses = new ArrayList<>();

//         setTitle("Student Management System");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JMenuBar menuBar = new JMenuBar();
//         JMenu studentMenu = new JMenu("Student");
//         JMenuItem addStudentMenuItem = new JMenuItem("Add Student");
//         JMenuItem enrollStudentMenuItem = new JMenuItem("Enroll Student");
//         JMenuItem gradeStudentMenuItem = new JMenuItem("Grade Student");
//         JMenuItem viewStudentDetailsMenuItem = new JMenuItem("View Student Details");
//         studentMenu.add(addStudentMenuItem);
//         studentMenu.add(enrollStudentMenuItem);
//         studentMenu.add(gradeStudentMenuItem);
//         studentMenu.add(viewStudentDetailsMenuItem);
//         menuBar.add(studentMenu);
//         setJMenuBar(menuBar);

//         String[] columnNames = {"ID", "Name", "Course", "Grade"};
//         tableModel = new DefaultTableModel(columnNames, 0);
//         studentTable = new JTable(tableModel);
//         JScrollPane tableScrollPane = new JScrollPane(studentTable);

//         courseDropdown = new JComboBox<>();
//         courseDropdown.addItem("Math");
//         courseDropdown.addItem("History");
//         courseDropdown.addItem("Science");
//         courseDropdown.addItem("English");

//         studentDropdown = new JComboBox<>();

//         gradeTextField = new JTextField(5);

//         JPanel gradePanel = new JPanel();
//         gradePanel.add(new JLabel("Select Student:"));
//         gradePanel.add(studentDropdown);
//         gradePanel.add(new JLabel("Select Course:"));
//         gradePanel.add(courseDropdown);
//         gradePanel.add(new JLabel("Grade:"));
//         gradePanel.add(gradeTextField);

//         getContentPane().setLayout(new BorderLayout());
//         getContentPane().add(tableScrollPane, BorderLayout.CENTER);
//         getContentPane().add(gradePanel, BorderLayout.NORTH);

//         addStudentMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 addStudent();
//             }
//         });

//         enrollStudentMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 enrollStudent();
//             }
//         });

//         gradeStudentMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 gradeStudent();
//             }
//         });

//         viewStudentDetailsMenuItem.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 viewStudentDetails();
//             }
//         });
//     }

//     class Student {
//         private int id;
//         private String name;
//         private String course;
//         private String grade;

//         public Student(int id, String name, String course, String grade) {
//             this.id = id;
//             this.name = name;
//             this.course = course;
//             this.grade = grade;
//         }

//         @Override
//         public String toString() {
//             return name;
//         }
//     }

//     private void addStudent() {
//         String name = JOptionPane.showInputDialog("Enter student name:");
//         String course = "";
//         String grade = "";
//         int id = students.size() + 1;
//         Student newStudent = new Student(id, name, course, grade);
//         students.add(newStudent);
//         updateStudentTable();
//         updateStudentDropdown();
//     }

//     private void enrollStudent() {
//         int selectedRow = studentTable.getSelectedRow();
//         if (selectedRow >= 0) {
//             int studentId = students.get(selectedRow).id;
//             String selectedCourse = courseDropdown.getSelectedItem().toString();
//             students.get(selectedRow).course = selectedCourse;
//             updateStudentTable();
//         } else {
//             JOptionPane.showMessageDialog(this, "Please select a student to enroll.");
//         }
//     }

//     private void gradeStudent() {
//         Student selectedStudent = (Student) studentDropdown.getSelectedItem();
//         String selectedCourse = courseDropdown.getSelectedItem().toString();
//         String grade = gradeTextField.getText();

//         if (selectedStudent != null && !grade.isEmpty()) {
//             for (Student student : students) {
//                 if (student == selectedStudent && student.course.equals(selectedCourse)) {
//                     student.grade = grade;
//                 }
//             }
//             updateStudentTable();
//         } else {
//             JOptionPane.showMessageDialog(this, "Please select a student, course, and enter a grade.");
//         }
//     }

//     private void viewStudentDetails() {
//         if (students.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "No student records to display.");
//             return;
//         }

//         JFrame detailsFrame = new JFrame("Student Details");
//         detailsFrame.setSize(600, 400);

//         String[] columnNames = {"ID", "Name", "Course", "Grade"};
//         DefaultTableModel detailsTableModel = new DefaultTableModel(columnNames, 0);
//         JTable detailsTable = new JTable(detailsTableModel);

//         for (Student student : students) {
//             Object[] rowData = {student.id, student.name, student.course, student.grade};
//             detailsTableModel.addRow(rowData);
//         }

//         JScrollPane tableScrollPane = new JScrollPane(detailsTable);
//         detailsFrame.add(tableScrollPane);

//         detailsFrame.setVisible(true);
//     }

//     private void updateStudentTable() {
//         tableModel.setRowCount(0);
//         for (Student student : students) {
//             Object[] rowData = {student.id, student.name, student.course, student.grade};
//             tableModel.addRow(rowData);
//         }
//     }

//     private void updateStudentDropdown() {
//         studentDropdown.removeAllItems();
//         for (Student student : students) {
//             studentDropdown.addItem(student);
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new StudentManagementSystem().setVisible(true);
//             }
//         });
//     }
// }






















// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class StudentManagementSystem {
//     public static void main(String[] args) {
//         // Create the main application frame
//         JFrame frame = new JFrame("Student Management System");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(800, 600);
        
//         // Create a menu bar
//         JMenuBar menuBar = new JMenuBar();
        
//         // Create menu items
//         JMenu studentMenu = new JMenu("Student Records");
//         JMenu courseMenu = new JMenu("Course Enrollment");
//         JMenu gradeMenu = new JMenu("Grades");
        
//         // Create menu items for student records
//         JMenuItem addStudentItem = new JMenuItem("Add Student");
//         JMenuItem viewStudentItem = new JMenuItem("View Students");
        
//         // Create menu items for course enrollment
//         JMenuItem enrollCourseItem = new JMenuItem("Enroll in Course");
//         JMenuItem viewEnrollmentsItem = new JMenuItem("View Enrollments");
        
//         // Create menu items for grades
//         JMenuItem enterGradesItem = new JMenuItem("Enter Grades");
//         JMenuItem viewGradesItem = new JMenuItem("View Grades");
        
//         // Add action listeners to menu items (you'll implement these)
//         addStudentItem.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Implement the action for adding a student
//             }
//         });
        
//         viewStudentItem.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Implement the action for viewing students
//             }
//         });
        
//         // Add menu items to the respective menus
//         studentMenu.add(addStudentItem);
//         studentMenu.add(viewStudentItem);
//         courseMenu.add(enrollCourseItem);
//         courseMenu.add(viewEnrollmentsItem);
//         gradeMenu.add(enterGradesItem);
//         gradeMenu.add(viewGradesItem);
        
//         // Add menus to the menu bar
//         menuBar.add(studentMenu);
//         menuBar.add(courseMenu);
//         menuBar.add(gradeMenu);
        
//         // Set the menu bar for the frame
//         frame.setJMenuBar(menuBar);
        
//         // Make the frame visible
//         frame.setVisible(true);
//     }
// }












import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class StudentManagementSystem {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);

        // Create panels for different functionalities
        JPanel studentPanel = createStudentPanel();
        JPanel coursePanel = createCoursePanel();
        JPanel gradePanel = createGradePanel();

        tabbedPane.addTab("Student Records", studentPanel);
        tabbedPane.addTab("Course Enrollment", coursePanel);
        tabbedPane.addTab("Grades", gradePanel);

        frame.setVisible(true);
    }

    private static JPanel createStudentPanel() {
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());

        // Create a table to display student records
        String[] columnNames = {"ID", "Name", "Age"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        studentPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a form to add a new student
        JPanel addStudentPanel = new JPanel(new GridLayout(4, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JButton addButton = new JButton("Add Student");
        addStudentPanel.add(new JLabel("ID:"));
        addStudentPanel.add(idField);
        addStudentPanel.add(new JLabel("Name:"));
        addStudentPanel.add(nameField);
        addStudentPanel.add(new JLabel("Age:"));
        addStudentPanel.add(ageField);
        addStudentPanel.add(new JLabel()); // Placeholder
        addStudentPanel.add(addButton);
        studentPanel.add(addStudentPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate input and add the new student
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    Student student = new Student(id, name, age);
                    students.add(student);
                    model.addRow(new Object[]{id, name, age});
                    // Clear the input fields
                    idField.setText("");
                    nameField.setText("");
                    ageField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
                }
            }
        });

        return studentPanel;
    }


private static JPanel createCoursePanel() {
    JPanel coursePanel = new JPanel();
    coursePanel.setLayout(new BorderLayout());

    // Create a dropdown for course selection
    String[] courses = {"Math", "Science", "History"};
    JComboBox<String> courseComboBox = new JComboBox<>(courses);
    coursePanel.add(courseComboBox, BorderLayout.NORTH);

    // Create a list of eligible students
    DefaultListModel<String> studentListModel = new DefaultListModel<>();
    JList<String> studentList = new JList<>(studentListModel);
    coursePanel.add(new JScrollPane(studentList), BorderLayout.CENTER);

    // Create an "Enroll" button
    JButton enrollButton = new JButton("Enroll");
    coursePanel.add(enrollButton, BorderLayout.SOUTH);

    // Add action listener to enroll students
    enrollButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStudent = studentList.getSelectedValue();
            if (selectedStudent != null) {
                // Implement enrollment logic
                // You can update the student's data to indicate they are enrolled in the selected course
            }
        }
    });

    return coursePanel;
}

private static JPanel createGradePanel() {
    JPanel gradePanel = new JPanel();
    gradePanel.setLayout(new BorderLayout());

    // Create dropdowns for student and course selection
    JComboBox<String> studentComboBox = new JComboBox<>();
    JComboBox<String> courseComboBox = new JComboBox<>();
    
    // Add code to populate these dropdowns with student and course data
    
    // Create an input field for grade
    JTextField gradeField = new JTextField();
    gradePanel.add(studentComboBox, BorderLayout.NORTH);
    gradePanel.add(courseComboBox, BorderLayout.CENTER);
    gradePanel.add(gradeField, BorderLayout.SOUTH);

    // Create a "Assign Grade" button
    JButton assignGradeButton = new JButton("Assign Grade");
    gradePanel.add(assignGradeButton, BorderLayout.SOUTH);

    // Add action listener to assign grades
    assignGradeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStudent = studentComboBox.getSelectedItem().toString();
            String selectedCourse = courseComboBox.getSelectedItem().toString();
            String grade = gradeField.getText();

            // Implement grade assignment logic
            // You can update the student's grade for the selected course
        }
    });

    return gradePanel;
}




    // Define the Student class
    static class Student {
        int id;
        String name;
        int age;

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }
    
}
