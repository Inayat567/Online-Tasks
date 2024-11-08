package com.mycompany.sms;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class GradePanel extends JPanel {
    private JComboBox<String> studentComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> gradeComboBox;
    private JTable gradeTable;
    private JButton assignGradeButton;
    private JButton cancelButton;

    public GradePanel(JFrame frame) {
        try {

            // Set the Nimbus Look and Feel
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        setLayout(new GridBagLayout());

        // Create a container to hold the JComboBox components in one line
        JPanel comboBoxContainer = new JPanel();
        comboBoxContainer.setLayout(new FlowLayout(FlowLayout.CENTER)); // Adjust alignment as needed

        // Student selection dropdown
        studentComboBox = new JComboBox<>();
        comboBoxContainer.add(studentComboBox);

        studentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourseComboBox();
            }
        });

        courseComboBox = new JComboBox<>();
        comboBoxContainer.add(courseComboBox);

        // Grade selection dropdown
        gradeComboBox = new JComboBox<>();
        gradeComboBox.addItem("Select Grade");
        gradeComboBox.addItem("A");
        gradeComboBox.addItem("B");
        gradeComboBox.addItem("C");
        gradeComboBox.addItem("D");
        gradeComboBox.addItem("F");
        comboBoxContainer.add(gradeComboBox);

        addField(comboBoxContainer, constraints, 0, 0);

        // Create the grade table
        String[] columns = { "Student", "Course", "Grade" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        gradeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(gradeTable);
        addField(scrollPane, constraints, 0, 1);

        // Assign grade button
        assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected student and grade
                String selectedStudent = (String) studentComboBox.getSelectedItem();
                String selectedCourse = (String) courseComboBox.getSelectedItem();
                String selectedGrade = (String) gradeComboBox.getSelectedItem();

                if (selectedStudent != null && selectedGrade != null) {
                    model.addRow(new Object[] { selectedStudent, selectedCourse, selectedGrade });
                }
            }
        });
        addField(assignGradeButton, constraints, 0, 2);

        // Cancel button
        cancelButton = new JButton("Close");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame = new JFrame("Student Management System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                GUI gui = new GUI();
                gui.initialize(frame);

                frame.setVisible(true);
            }
        });
        addField(cancelButton, constraints, 1, 2);

        updateStudentComboBox();
    }

    private void addField(JComponent component, GridBagConstraints constraints, int gridx, int gridy) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(component, constraints);
    }

    private void updateStudentComboBox() {
        Student std = new Student();
        studentComboBox.addItem("Select Student");
        for (Student student : std.getStudentList()) {
            studentComboBox.addItem(Integer.toString(student.getId()) + " " + student.getName());
        }
    }

    private void updateCourseComboBox() {

        String selectedStudent = (String) studentComboBox.getSelectedItem();
        if (selectedStudent != null && !selectedStudent.equals("Select Student")) {
            String[] parts = selectedStudent.split(" ");
            int id = Integer.parseInt(parts[0]);

            Student std = new Student();
            for (Student student : std.getStudentList()) {
                if (student.getId() == id) {
                    for (String selectedSTD : student.getEnrolledCourse()) {
                        courseComboBox.addItem(selectedSTD);
                    }
                }
            }
        }
    }

}
