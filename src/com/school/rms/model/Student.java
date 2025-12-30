package com.school.rms.model;

import java.util.ArrayList;
import java.util.List;
import com.school.rms.util.GradeCalculator;

public class Student {
    private String id;
    private String name;
    private String session; // Spring, Autumn, Summer
    private String batch; // 1, 2, 3, etc.
    private String department;
    private double overallCGPA;
    private List<Subject> subjects;

    public Student(String id, String name, String session, String batch, String department) {
        this.id = id;
        this.name = name;
        this.session = session;
        this.batch = batch;
        this.department = department;
        this.subjects = new ArrayList<>();
        this.overallCGPA = 0.00;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getOverallCGPA() {
        return overallCGPA;
    }

    /**
     * Recalculate overall CGPA based on subjects
     */
    public void calculateOverallCGPA() {
        this.overallCGPA = GradeCalculator.calculateOverallCGPA(subjects);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        calculateOverallCGPA();
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        calculateOverallCGPA();
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
        calculateOverallCGPA();
    }

    public void clearSubjects() {
        this.subjects.clear();
        calculateOverallCGPA();
    }
}
