package com.school.rms.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.school.rms.model.Student;
import com.school.rms.model.Subject;
import com.school.rms.util.Theme;

public class ManageSubjectsDialog extends JDialog {
    private Student student;
    private JTable subjectsTable;
    private DefaultTableModel tableModel;
    private boolean dataChanged = false;

    public ManageSubjectsDialog(JFrame parent, Student student) {
        super(parent, "Manage Subjects - " + student.getName(), true);
        this.student = student;
        initializeUI();
    }

    private void initializeUI() {
        setSize(800, 600);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(245, 245, 250));
        titlePanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Subject Marks Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Table panel
        JPanel tablePanel = createTablePanel();

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        JButton addButton = createStyledButton("+ Add Subject", Theme.PRIMARY_ACCENT);
        JButton deleteButton = createStyledButton("Delete Selected", new Color(220, 53, 69));
        JButton saveButton = createStyledButton("Save & Close", new Color(40, 167, 69));
        JButton cancelButton = createStyledButton("Cancel", Color.LIGHT_GRAY);

        addButton.addActionListener(e -> addSubject());
        deleteButton.addActionListener(e -> deleteSelectedSubject());
        saveButton.addActionListener(e -> {
            saveChanges();
            dataChanged = true;
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        add(titlePanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Create table
        String[] columnNames = { "Subject Name", "Marks", "Letter Grade", "CGPA", "Remarks" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column <= 1; // Only subject name and marks are editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return Double.class; // Marks column stores numbers
                }
                return String.class;
            }
        };

        // Populate with existing subjects
        for (Subject subject : student.getSubjects()) {
            Object[] row = {
                    subject.getSubjectName(),
                    subject.getMarks(), // Store as double, not formatted string
                    subject.getLetterGrade(),
                    String.format("%.2f", subject.getCgpa()),
                    subject.getRemarks()
            };
            tableModel.addRow(row);
        }

        subjectsTable = new JTable(tableModel);
        subjectsTable.setRowHeight(40);
        subjectsTable.setFont(Theme.FONT_REGULAR);
        subjectsTable.getTableHeader().setFont(Theme.FONT_BOLD);
        subjectsTable.getTableHeader().setBackground(new Color(240, 240, 245));
        subjectsTable.setShowVerticalLines(false);

        // Add tooltip for user guidance
        subjectsTable.setToolTipText("Double-click on Subject Name or Marks column to edit");

        // Make editing more responsive - single click to select, double click to edit
        subjectsTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        // Add listener to update grade info when marks change
        tableModel.addTableModelListener(e -> {
            if (e.getColumn() == 1) { // Marks column
                int row = e.getFirstRow();
                try {
                    Object marksObj = tableModel.getValueAt(row, 1);
                    double marks;

                    if (marksObj instanceof Double) {
                        marks = (Double) marksObj;
                    } else {
                        marks = Double.parseDouble(marksObj.toString());
                    }

                    // Validate marks range
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(this,
                                "Marks must be between 0 and 100",
                                "Invalid Marks",
                                JOptionPane.ERROR_MESSAGE);
                        // Reset to previous valid value or 0
                        tableModel.setValueAt(0.0, row, 1);
                        marks = 0;
                    }

                    Subject tempSubject = new Subject("temp", marks);
                    tableModel.setValueAt(tempSubject.getLetterGrade(), row, 2);
                    tableModel.setValueAt(String.format("%.2f", tempSubject.getCgpa()), row, 3);
                    tableModel.setValueAt(tempSubject.getRemarks(), row, 4);
                } catch (NumberFormatException ex) {
                    // Invalid number, show error
                    JOptionPane.showMessageDialog(this,
                            "Please enter a valid number for marks (0-100)",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    tableModel.setValueAt(0.0, row, 1);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(subjectsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(Theme.FONT_BOLD);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addSubject() {
        String subjectName = JOptionPane.showInputDialog(this, "Enter Subject Name:", "Add Subject",
                JOptionPane.PLAIN_MESSAGE);
        if (subjectName != null && !subjectName.trim().isEmpty()) {
            String marksStr = JOptionPane.showInputDialog(this, "Enter Marks (0-100):", "Add Subject",
                    JOptionPane.PLAIN_MESSAGE);
            if (marksStr != null) {
                try {
                    double marks = Double.parseDouble(marksStr);
                    if (marks >= 0 && marks <= 100) {
                        Subject subject = new Subject(subjectName.trim(), marks);
                        Object[] row = {
                                subject.getSubjectName(),
                                marks, // Store as double
                                subject.getLetterGrade(),
                                String.format("%.2f", subject.getCgpa()),
                                subject.getRemarks()
                        };
                        tableModel.addRow(row);
                    } else {
                        JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid marks value", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deleteSelectedSubject() {
        int selectedRow = subjectsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete this subject?", "Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a subject to delete", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveChanges() {
        student.clearSubjects();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {
                String subjectName = tableModel.getValueAt(i, 0).toString();
                double marks = Double.parseDouble(tableModel.getValueAt(i, 1).toString());

                if (marks >= 0 && marks <= 100) {
                    student.addSubject(new Subject(subjectName, marks));
                }
            } catch (NumberFormatException e) {
                // Skip invalid rows
            }
        }
    }

    public boolean isDataChanged() {
        return dataChanged;
    }
}
