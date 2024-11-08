package com.mycompany.sms;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class SMS {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            GUI gui = new GUI();
            gui.initialize(frame);

            frame.setVisible(true);
        });
    }
}
