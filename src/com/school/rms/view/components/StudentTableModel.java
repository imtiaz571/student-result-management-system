package com.school.rms.view.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.school.rms.model.Student;

public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;
    private String[] columnNames = { "Student Name", "Session", "Batch", "Department", "Overall CGPA", "Actions" };

    public StudentTableModel(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return student.getName();
            case 1:
                return student.getSession();
            case 2:
                return student.getBatch();
            case 3:
                return student.getDepartment();
            case 4:
                return String.format("%.2f", student.getOverallCGPA());
            case 5:
                return ""; // Actions column
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 5; // Only Actions column is editable
    }

    public Student getStudentAt(int row) {
        if (row >= 0 && row < students.size()) {
            return students.get(row);
        }
        return null;
    }
}
