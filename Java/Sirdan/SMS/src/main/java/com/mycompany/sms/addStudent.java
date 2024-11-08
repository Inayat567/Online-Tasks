package com.mycompany.sms;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addStudent extends JPanel {
    private JTextField regNoField;
    private JTextField nameField;
    private JTextField ageField;
    private JButton addButton;
    private JButton resetButton;

    public addStudent() {
        try {
            // Set the Nimbus Look and Feel
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Create a helper method to simplify adding components with constraints
        ActionListener resetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the input fields
                clearInputFields();
            }
        };

        Student std = new Student();

        addStyledLabel("Registration Number:", constraints, 0, 0);
        regNoField = createStyledTextField();
        regNoField.setText(String.valueOf(std.getStudentList().size() + 1));
        regNoField.setEditable(false);
        addField(regNoField, constraints, 1, 0);

        addStyledLabel("Name:", constraints, 0, 1);
        nameField = createStyledTextField();
        addField(nameField, constraints, 1, 1);

        addStyledLabel("Age:", constraints, 0, 2);
        ageField = createStyledTextField();
        addField(ageField, constraints, 1, 2);

        addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int regNo = Integer.parseInt(regNoField.getText());
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());

                    Student student = new Student(regNo, name, age);
                    student.getStudentList();
                    // student.studentList.add(student);
                    student.setStudentList(student);
                    System.out.println(student.getStudentList());
                    clearInputFields();
                    JOptionPane.showMessageDialog(null, "Added Sucessfully");
                    frame.dispose();
                    JFrame frame = new JFrame("Student Management System");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(800, 600);

                    GUI gui = new GUI();
                    gui.initialize(frame);

                    frame.setVisible(true);

                } catch (NumberFormatException ex) {
                    // Handle the case where parsing fails (e.g., invalid input)
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
                }
            }
        });
        addField(addButton, constraints, 0, 3);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(resetListener);
        addField(resetButton, constraints, 1, 3);
        frame.add(this);
    }

    // Helper method to create styled labels and add to panel with constraints
    private void addStyledLabel(String text, GridBagConstraints constraints, int gridx, int gridy) {
        JLabel label = createStyledLabel(text);
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = GridBagConstraints.WEST;
        add(label, constraints);
    }

    // Helper method to add components to panel with constraints
    private void addField(JComponent component, GridBagConstraints constraints, int gridx, int gridy) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(component, constraints);
    }

    // Helper method to create styled labels
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 102, 204));
        return label;
    }

    // Helper method to create styled text fields
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 30));
        return textField;
    }

    // Helper method to clear input fields
    private void clearInputFields() {
        nameField.setText("");
        ageField.setText("");
    }

}
