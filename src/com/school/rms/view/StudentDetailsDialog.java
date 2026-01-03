package com.school.rms.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.school.rms.model.Student;
import com.school.rms.model.Subject;
import com.school.rms.util.Theme;

public class StudentDetailsDialog extends JDialog {
    private Student student;
    private JFrame parent;

    public StudentDetailsDialog(JFrame parent, Student student) {
        super(parent, "Student Details - " + student.getName(), true);
        this.parent = parent;
        this.student = student;
        initializeUI();
    }

    private void initializeUI() {
        setSize(900, 650);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        
        JPanel infoPanel = createStudentInfoPanel();

        
        JPanel tablePanel = createSubjectsTablePanel();

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton manageSubjectsButton = new JButton("Manage Subjects");
        manageSubjectsButton.setFont(Theme.FONT_BOLD);
        manageSubjectsButton.setBackground(Theme.PRIMARY_ACCENT);
        manageSubjectsButton.setForeground(Color.WHITE);
        manageSubjectsButton.setBorderPainted(false);
        manageSubjectsButton.setFocusPainted(false);
        manageSubjectsButton.setPreferredSize(new Dimension(150, 35));
        manageSubjectsButton.addActionListener(e -> openManageSubjects());

        JButton closeButton = new JButton("Close");
        closeButton.setFont(Theme.FONT_BOLD);
        closeButton.setBackground(Color.LIGHT_GRAY);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(manageSubjectsButton);
        buttonPanel.add(closeButton);

        add(infoPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createStudentInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel nameLabel = new JLabel("Name: " + student.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel sessionLabel = new JLabel("Session: " + student.getSession());
        sessionLabel.setFont(Theme.FONT_REGULAR);

        JLabel batchLabel = new JLabel("Batch: " + student.getBatch());
        batchLabel.setFont(Theme.FONT_REGULAR);

        JLabel deptLabel = new JLabel("Department: " + student.getDepartment());
        deptLabel.setFont(Theme.FONT_REGULAR);

        
        double cgpa = student.getOverallCGPA();
        String cgpaText = String.format("Overall CGPA: %.2f", cgpa);
        if (cgpa == 0.00) {
            cgpaText += " (FAILED - Must retake failed subjects)";
        }

        JLabel cgpaLabel = new JLabel(cgpaText);
        cgpaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cgpaLabel.setForeground(cgpa == 0.00 ? Color.RED : Theme.PRIMARY_ACCENT);

        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(sessionLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(batchLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(deptLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(cgpaLabel);

        return panel;
    }

    private JPanel createSubjectsTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 30, 20, 30));

        
        JLabel titleLabel = new JLabel("Subject Details");
        titleLabel.setFont(Theme.FONT_BOLD);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        
        String[] columnNames = { "Subject Name", "Marks", "Letter Grade", "CGPA", "Remarks" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Subject subject : student.getSubjects()) {
            Object[] row = {
                    subject.getSubjectName(),
                    String.format("%.1f", subject.getMarks()),
                    subject.getLetterGrade(),
                    String.format("%.2f", subject.getCgpa()),
                    subject.getRemarks()
            };
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(40);
        table.setFont(Theme.FONT_REGULAR);
        table.getTableHeader().setFont(Theme.FONT_BOLD);
        table.getTableHeader().setBackground(new Color(240, 240, 245));
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String remarks = (String) table.getValueAt(row, 4);
                if ("Retake".equals(remarks)) {
                    c.setForeground(Color.RED);
                    if (!isSelected) {
                        c.setBackground(new Color(255, 240, 240));
                    }
                } else {
                    c.setForeground(Color.BLACK);
                    if (!isSelected) {
                        c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void openManageSubjects() {
        ManageSubjectsDialog dialog = new ManageSubjectsDialog((JFrame) parent, student);
        dialog.setVisible(true);

        if (dialog.isDataChanged()) {
            
            dispose();
            new StudentDetailsDialog((JFrame) parent, student).setVisible(true);
        }
    }
}
