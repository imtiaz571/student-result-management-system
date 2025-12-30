package com.school.rms.model;

import com.school.rms.util.GradeCalculator;

public class Subject {
    private String subjectName;
    private double marks;
    private double cgpa;
    private String letterGrade;
    private String remarks;

    public Subject(String subjectName, double marks) {
        this.subjectName = subjectName;
        this.marks = marks;
        updateGradeInfo();
    }

    // Constructor for backward compatibility
    public Subject(String subjectName, double marks, double cgpa) {
        this.subjectName = subjectName;
        this.marks = marks;
        updateGradeInfo();
    }

    private void updateGradeInfo() {
        GradeCalculator.GradeInfo gradeInfo = GradeCalculator.calculateGrade(marks);
        this.cgpa = gradeInfo.getGradePoint();
        this.letterGrade = gradeInfo.getLetterGrade();
        this.remarks = gradeInfo.getRemarks();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        updateGradeInfo();
    }

    public double getCgpa() {
        return cgpa;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public String getRemarks() {
        return remarks;
    }
}
