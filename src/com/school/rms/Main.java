package com.school.rms;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.school.rms.view.MainDashboard;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                
                
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new MainDashboard().setVisible(true);
        });
    }
}
