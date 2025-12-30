package com.school.rms.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import com.school.rms.controller.DashboardController;
import com.school.rms.model.Student;
import com.school.rms.view.components.*;
import com.school.rms.util.Theme;

public class MainDashboard extends JFrame {

    private DashboardController controller;
    private JTable studentTable;
    private StudentTableModel tableModel;
    private JTextField searchField;

    public MainDashboard() {
        controller = new DashboardController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Student Result Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND_COLOR);

        // Main Layout
        setLayout(new BorderLayout(20, 20));

        // Create Layout Sections
        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setBorder(new EmptyBorder(20, 30, 0, 30));

        // 1. Header (Search)
        JPanel headerInfo = new JPanel(new BorderLayout());
        headerInfo.setOpaque(false);

        // Search Bar (Left)
        searchField = new JTextField("Search...");
        searchField.setPreferredSize(new Dimension(300, 35));
        searchField.setForeground(Color.GRAY);

        // Add focus listener to clear placeholder
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        // Add document listener for real-time search
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterStudents();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterStudents();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterStudents();
            }
        });

        headerInfo.add(searchField, BorderLayout.WEST);

        // Filter Bar - Only Add Student button
        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        filterBar.setOpaque(false);
        filterBar.setBorder(new EmptyBorder(15, 0, 15, 0));

        CustomButton addStudentBtn = new CustomButton("+ Add Student", Theme.PRIMARY_ACCENT, Color.WHITE);

        addStudentBtn.addActionListener(e -> {
            showAddStudentDialog();
        });

        filterBar.add(addStudentBtn);

        // Combine
        container.add(headerInfo);
        container.add(Box.createVerticalStrut(10));
        container.add(filterBar);

        return container;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10, 30, 30, 30));

        tableModel = new StudentTableModel(controller.getAllStudents());
        studentTable = new JTable(tableModel);

        // Apply Renderers
        studentTable.setDefaultRenderer(Object.class, new StatusBadge());

        // Specific Column Renderers
        studentTable.getColumnModel().getColumn(0).setCellRenderer(new StatusBadge()); // Student Name
        studentTable.getColumnModel().getColumn(1).setCellRenderer(new StatusBadge()); // Batch
        studentTable.getColumnModel().getColumn(2).setCellRenderer(new StatusBadge()); // Department
        studentTable.getColumnModel().getColumn(3).setCellRenderer(new StatusBadge()); // CGPA

        // Action Column
        studentTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderer());
        TableActionCellEditor actionEditor = new TableActionCellEditor();
        actionEditor.setActionListener(new TableActionCellEditor.ActionListener() {
            @Override
            public void onEdit(int row) {
                showEditStudentDialog(row);
            }

            @Override
            public void onDelete(int row) {
                deleteStudent(row);
            }
        });
        studentTable.getColumnModel().getColumn(4).setCellEditor(actionEditor);

        // Add mouse listener to make student name clickable
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = studentTable.rowAtPoint(e.getPoint());
                int column = studentTable.columnAtPoint(e.getPoint());

                // If clicked on Student Name column (column 0)
                if (column == 0 && row >= 0) {
                    Student student = tableModel.getStudentAt(row);
                    if (student != null) {
                        showStudentDetails(student);
                    }
                }
            }
        });

        // Style the table
        studentTable.setRowHeight(50);
        studentTable.setShowVerticalLines(false);
        studentTable.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = studentTable.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setFont(Theme.FONT_BOLD);
        header.setForeground(Theme.TEXT_SECONDARY);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER_GRAY));

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void refreshTable() {
        tableModel = new StudentTableModel(controller.getAllStudents());
        studentTable.setModel(tableModel);

        // Reapply renderers and editor (now 6 columns: Name, Session, Batch, Dept,
        // CGPA, Actions)
        studentTable.getColumnModel().getColumn(0).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(1).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(2).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(3).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(4).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderer());

        TableActionCellEditor actionEditor = new TableActionCellEditor();
        actionEditor.setActionListener(new TableActionCellEditor.ActionListener() {
            @Override
            public void onEdit(int row) {
                showEditStudentDialog(row);
            }

            @Override
            public void onDelete(int row) {
                deleteStudent(row);
            }
        });
        studentTable.getColumnModel().getColumn(5).setCellEditor(actionEditor);
    }

    private void showStudentDetails(Student student) {
        StudentDetailsDialog dialog = new StudentDetailsDialog(this, student);
        dialog.setVisible(true);
        // Refresh table in case subjects were modified
        refreshTable();
    }

    private void showAddStudentDialog() {
        StudentDialog dialog = new StudentDialog(this, null);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Student newStudent = dialog.getStudent();
            controller.addStudent(newStudent);
            refreshTable();
        }
    }

    private void showEditStudentDialog(int row) {
        Student student = tableModel.getStudentAt(row);
        if (student != null) {
            StudentDialog dialog = new StudentDialog(this, student);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                // Student object is already updated in place
                refreshTable();
            }
        }
    }

    private void deleteStudent(int row) {
        Student student = tableModel.getStudentAt(row);
        if (student == null)
            return;

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteStudentByObject(student);
            refreshTable();
        }
    }

    private void filterStudents() {
        String searchText = searchField.getText().trim();

        // If search is placeholder text or empty, show all students
        if (searchText.equals("Search...") || searchText.isEmpty()) {
            refreshTable();
            return;
        }

        // Filter students by name
        java.util.List<Student> allStudents = controller.getAllStudents();
        java.util.List<Student> filteredStudents = new java.util.ArrayList<>();

        for (Student student : allStudents) {
            if (student.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredStudents.add(student);
            }
        }

        // Update table with filtered students
        tableModel = new StudentTableModel(filteredStudents);
        studentTable.setModel(tableModel);

        // Reapply renderers and editor (6 columns)
        studentTable.getColumnModel().getColumn(0).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(1).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(2).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(3).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(4).setCellRenderer(new StatusBadge());
        studentTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderer());

        TableActionCellEditor actionEditor = new TableActionCellEditor();
        actionEditor.setActionListener(new TableActionCellEditor.ActionListener() {
            @Override
            public void onEdit(int row) {
                showEditStudentDialog(row);
            }

            @Override
            public void onDelete(int row) {
                deleteStudent(row);
            }
        });
        studentTable.getColumnModel().getColumn(5).setCellEditor(actionEditor);
    }
}
