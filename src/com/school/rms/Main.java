package com.school.rms;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.school.rms.view.MainDashboard;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Try to set system look and feel for better integration,
                // though we will be custom painting most components.
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new MainDashboard().setVisible(true);
        });
    }
}
