package com.mycompany.sms;

import javax.swing.*;

public class GUI {
    private JPanel studentPanel;
    private JPanel coursePanel;
    private JPanel gradePanel;

    public void initialize(JFrame frame) {
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);

        studentPanel = new StudentPanel(frame);
        coursePanel = new CoursePanel(frame);
        gradePanel = new GradePanel(frame);

        tabbedPane.addTab("Student Records", studentPanel);
        tabbedPane.addTab("Course Enrollment", coursePanel);
        tabbedPane.addTab("Grades", gradePanel);
    }
}
