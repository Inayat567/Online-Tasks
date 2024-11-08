package com.mycompany.sms;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CoursePanel extends JPanel {
    private JComboBox<String> courseComboBox;
    private JList<String> studentList;
    private JButton enrollButton;
    private List<Student> enrolledStudents;

    public CoursePanel(JFrame frame) {
        try {
            // Set the Nimbus Look and Feel
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Course selection dropdown
        courseComboBox = new JComboBox<>();
        courseComboBox.addItem("Programming Fundemental");
        courseComboBox.addItem("Artifitial Intelligence");
        courseComboBox.addItem("Object Oriented Programming");
        addField(courseComboBox, constraints, 0, 1);

        // Student list
        DefaultListModel<String> studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addField(new JScrollPane(studentList), constraints, 0, 2);

        // Enroll button
        enrollButton = new JButton("Enroll");
        addField(enrollButton, constraints, 0, 3);
        enrolledStudents = new ArrayList<>();
        Student std = new Student();

        for (Student student : std.getStudentList()) {
            studentListModel.addElement(Integer.toString(student.getId()) + " " + student.getName());
        }

        // Enroll button action
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedCourse = (String) courseComboBox.getSelectedItem();
                    System.out.println(selectedCourse);

                    int[] selectedIndices = studentList.getSelectedIndices();

                    for (int index : selectedIndices) {
                        String selectedStudentName = studentListModel.get(index);
                        String[] parts = selectedStudentName.split(" ");
                        if (parts.length > 0) {
                            int id = Integer.parseInt(parts[0]);
                            std.setEnrolledCourse(selectedCourse, id);
                            enrolledStudents.add(new Student());
                        }
                    }
                    if (enrolledStudents.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Please Select Student first");
                    } else {
                        JOptionPane.showMessageDialog(null, "Students Succesfully Enrolled");
                    }

                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Error! " + error.getMessage());
                }
            }
        });
    }

    private void addField(JComponent component, GridBagConstraints constraints, int gridx, int gridy) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(component, constraints);
    }
}
