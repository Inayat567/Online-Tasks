package com.mycompany.sms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable studentTable;

    public StudentPanel(JFrame frame) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        // Create the student table
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        JButton addButton = new JButton("Add Row");
        addButton.addActionListener(e -> {
            Student std = new Student();
            for (Student student : std.getStudentList()) {
                Object[] rowData = { student.getId(), student.getName(), student.getClass() };
                tableModel.addRow(rowData);
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Student Registration");

                addStudent studentPanel = new addStudent();
                frame.dispose();
                frame.add(studentPanel);

                // Set frame properties
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setVisible(true);

            }
        });
        buttonPanel.add(addStudentButton);

        JButton updateStudentButton = new JButton("Update Student");
        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStudent();
            }
        });
        buttonPanel.add(updateStudentButton);

        JButton displayStudentDetailsButton = new JButton("Display Student Details");
        displayStudentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student std = new Student();

                if (std.getStudentList().isEmpty()) {
                    JOptionPane.showMessageDialog(StudentPanel.this, "No student records to display.");
                    return;
                }
                JFrame detailsFrame = new JFrame("Student Details");
                detailsFrame.setSize(600, 400);
                String[] columnNames = { "Reg #", "Name", "Age" };
                DefaultTableModel detailsTableModel = new DefaultTableModel(columnNames, 0);
                JTable detailsTable = new JTable(detailsTableModel);
                for (Student student : std.getStudentList()) {
                    Object[] rowData = { student.getId(), student.getName(), student.getAge(), };
                    detailsTableModel.addRow(rowData);
                }
                JScrollPane tableScrollPane = new JScrollPane(detailsTable);
                detailsFrame.add(tableScrollPane);
                detailsFrame.setVisible(true);
            }
        });
        buttonPanel.add(displayStudentDetailsButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to update the student table with a list of students
    public void updateStudentTable(ArrayList<Student> students) {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // Add students to the table
        for (Student student : students) {
            Object[] rowData = { student.getClass(), student.getName() };
            tableModel.addRow(rowData);
        }
    }
}
