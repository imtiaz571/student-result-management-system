package com.school.rms.util;

public class GradeCalculator {

    public static class GradeInfo {
        private double gradePoint;
        private String letterGrade;
        private String remarks;

        public GradeInfo(double gradePoint, String letterGrade, String remarks) {
            this.gradePoint = gradePoint;
            this.letterGrade = letterGrade;
            this.remarks = remarks;
        }

        public double getGradePoint() {
            return gradePoint;
        }

        public String getLetterGrade() {
            return letterGrade;
        }

        public String getRemarks() {
            return remarks;
        }
    }

    
    public static GradeInfo calculateGrade(double marks) {
        if (marks >= 80) {
            return new GradeInfo(4.00, "A+", "Outstanding");
        } else if (marks >= 75) {
            return new GradeInfo(3.75, "A", "Excellent");
        } else if (marks >= 70) {
            return new GradeInfo(3.50, "A-", "Very Good");
        } else if (marks >= 65) {
            return new GradeInfo(3.25, "B+", "Good");
        } else if (marks >= 60) {
            return new GradeInfo(3.00, "B", "Satisfactory");
        } else if (marks >= 55) {
            return new GradeInfo(2.75, "B-", "Above Average");
        } else if (marks >= 50) {
            return new GradeInfo(2.50, "C+", "Average");
        } else if (marks >= 45) {
            return new GradeInfo(2.25, "C", "Below Average");
        } else if (marks >= 40) {
            return new GradeInfo(2.00, "D", "Pass");
        } else {
            return new GradeInfo(0.00, "F", "Retake");
        }
    }

    
    public static double calculateOverallCGPA(java.util.List<com.school.rms.model.Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return 0.00;
        }

        double totalGradePoints = 0;
        int count = 0;

        
        for (com.school.rms.model.Subject subject : subjects) {
            if (subject.getMarks() < 40) {
                return 0.00; 
            }
            totalGradePoints += subject.getCgpa();
            count++;
        }

        if (count == 0)
            return 0.00;

        return Math.round((totalGradePoints / count) * 100.0) / 100.0; 
    }
}
