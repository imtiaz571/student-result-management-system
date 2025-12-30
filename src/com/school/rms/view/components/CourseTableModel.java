package com.school.rms.view.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.school.rms.model.Course;

public class CourseTableModel extends AbstractTableModel {
    private List<Course> courses;
    private final String[] columnNames = { "Date", "Batch", "Subject", "Teacher", "Student Name",
            "Publication Status", "Actions" };

    public CourseTableModel(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public int getRowCount() {
        return courses.size();
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
        Course course = courses.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return course.getDate();
            case 1:
                return course.getBatch();
            case 2:
                return course.getSubject(); // Using Subject as Topic
            case 3:
                return course.getTeacher();
            case 4:
                return course.getStudentName();
            case 5:
                return course.getPublicationStatus();
            case 6:
                return ""; // Actions column
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 6; // Only actions are clickable usually, but we implement via editor/renderer.
        // For basic table, we might just use buttons.
    }
}
