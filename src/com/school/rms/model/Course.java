package com.school.rms.model;

public class Course {
    private String id;
    private String date;
    private String batch;
    private String subject;
    private String teacher;
    private String studentName;
    private String publicationStatus;

    public Course(String id, String date, String batch, String subject, String teacher, String studentName,
            String publicationStatus) {
        this.id = id;
        this.date = date;
        this.batch = batch;
        this.subject = subject;
        this.teacher = teacher;
        this.studentName = studentName;
        this.publicationStatus = publicationStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }
}
