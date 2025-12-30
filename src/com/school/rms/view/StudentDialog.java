package com.school.rms.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.school.rms.model.Student;
import com.school.rms.util.Theme;

public class StudentDialog extends JDialog {
    private JTextField nameField;
    private JComboBox<String> sessionCombo;
    private JTextField batchField;
    private JTextField departmentField;

    private Student student;
    private boolean confirmed = false;

    public StudentDialog(JFrame parent, Student student) {
        super(parent, student == null ? "Add Student" : "Edit Student", true);
        this.student = student;
        initializeUI();

        if (student != null) {
            populateFields();
        }
    }

    private void initializeUI() {
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.gridx = 0;
        gbc.weightx = 0.3;

        // Student Name
        gbc.gridy = 0;
        formPanel.add(createLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(200, 35));
        formPanel.add(nameField, gbc);

        // Session
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Session:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        String[] sessions = { "Spring", "Autumn", "Summer" };
        sessionCombo = new JComboBox<>(sessions);
        sessionCombo.setPreferredSize(new Dimension(200, 35));
        sessionCombo.setFont(Theme.FONT_REGULAR);
        formPanel.add(sessionCombo, gbc);

        // Batch
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Batch:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        batchField = new JTextField(20);
        batchField.setPreferredSize(new Dimension(200, 35));
        formPanel.add(batchField, gbc);

        // Department
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Department:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        departmentField = new JTextField(20);
        departmentField.setPreferredSize(new Dimension(200, 35));
        formPanel.add(departmentField, gbc);

        // Note about CGPA
        JLabel noteLabel = new JLabel("<html><i>Note: Overall CGPA will be calculated from subjects</i></html>");
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        noteLabel.setForeground(Color.GRAY);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(noteLabel, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        JButton saveButton = createStyledButton("Save", Theme.PRIMARY_ACCENT);
        JButton cancelButton = createStyledButton("Cancel", Color.LIGHT_GRAY);

        saveButton.addActionListener(e -> {
            if (validateFields()) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.FONT_BOLD);
        label.setForeground(Theme.TEXT_PRIMARY);
        return label;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(Theme.FONT_BOLD);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void populateFields() {
        nameField.setText(student.getName());
        sessionCombo.setSelectedItem(student.getSession());
        batchField.setText(student.getBatch());
        departmentField.setText(student.getDepartment());
    }

    private boolean validateFields() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Name is required", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (batchField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Batch is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (departmentField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Department is required", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Student getStudent() {
        // If editing existing student, update it directly
        if (student != null) {
            student.setName(nameField.getText().trim());
            student.setSession((String) sessionCombo.getSelectedItem());
            student.setBatch(batchField.getText().trim());
            student.setDepartment(departmentField.getText().trim());
            return student;
        }

        // If adding new student, create new object
        String id = String.valueOf(System.currentTimeMillis());
        Student newStudent = new Student(
                id,
                nameField.getText().trim(),
                (String) sessionCombo.getSelectedItem(),
                batchField.getText().trim(),
                departmentField.getText().trim());

        return newStudent;
    }
}
