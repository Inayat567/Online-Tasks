package com.mycompany.sms;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudent extends JPanel {
    private JTextField regNoField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> studentComboBox;
    private JButton updateButton;
    private JButton resetButton;
    Student std = new Student();

    public UpdateStudent() {
        try {
            // Set the Nimbus Look and Feel
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Create a helper method to simplify adding components with constraints
        ActionListener resetListener = new ActionListener() {
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
        };
        Student student = new Student();
        studentComboBox = new JComboBox<>();
        for (Student std : student.getStudentList()) {
            studentComboBox.addItem(String.valueOf(std.getId()));
        }

        studentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When a student is selected from the combo box, display their information
                int selectedIndex = studentComboBox.getSelectedIndex();

                if (selectedIndex >= 0) {
                    Student student = std.getStudentList().get(selectedIndex);
                    regNoField.setText(Integer.toString(student.getId()));
                    nameField.setText(student.getName());
                    ageField.setText(Integer.toString(student.getAge()));
                }
            }
        });

        // Add the combo box
        addStyledLabel("Select Student:", constraints, 0, 0);
        addField(studentComboBox, constraints, 1, 0);

        addStyledLabel("Registration Number:", constraints, 0, 2);
        regNoField = createStyledTextField();
        addField(regNoField, constraints, 1, 2);
        regNoField.setEditable(false);

        addStyledLabel("Name:", constraints, 0, 3);
        nameField = createStyledTextField();
        addField(nameField, constraints, 1, 3);

        addStyledLabel("Age:", constraints, 0, 4);
        ageField = createStyledTextField();
        addField(ageField, constraints, 1, 4);

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the selected student's information
                int selectedIndex = studentComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    try {
                        int regNo = Integer.parseInt(regNoField.getText());
                        String name = nameField.getText();
                        int age = Integer.parseInt(ageField.getText());
                        Student student = std.getStudentList().get(selectedIndex);
                        student.setId(regNo);
                        student.setName(name);
                        student.setAge(age);
                        updateStudentComboBox();
                        JOptionPane.showMessageDialog(null, "Update SucessFully.");
                        System.out.println("Check");
                        frame.dispose();
                        JFrame frame = new JFrame("Student Management System");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(800, 600);

                        GUI gui = new GUI();
                        gui.initialize(frame);

                        frame.setVisible(true);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
                    }
                }
            }
        });
        addField(updateButton, constraints, 0, 5);

        resetButton = new JButton("Cancel");
        resetButton.addActionListener(resetListener);
        addField(resetButton, constraints, 1, 5);

        frame.add(this);
        frame.setVisible(true);
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
        label.setForeground(new Color(0, 102, 204)); // Blue color
        return label;
    }

    // Helper method to create styled text fields
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 30));
        return textField;
    }

    // Helper method to update the student combo box
    private void updateStudentComboBox() {
        studentComboBox.removeAllItems();
        for (Student student : std.getStudentList()) {
            studentComboBox.addItem(student.getName());
        }
    }

}
