package com.school.rms.controller;

import java.util.List;
import com.school.rms.model.Student;
import com.school.rms.model.StudentRepository;

public class DashboardController {
    private StudentRepository repository;

    public DashboardController() {
        this.repository = new StudentRepository();
    }

    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    public void addStudent(Student student) {
        repository.addStudent(student);
    }

    public void updateStudent(int index, Student student) {
        List<Student> students = repository.getAllStudents();
        if (index >= 0 && index < students.size()) {
            students.set(index, student);
        }
    }

    public void deleteStudent(int index) {
        List<Student> students = repository.getAllStudents();
        if (index >= 0 && index < students.size()) {
            repository.removeStudent(students.get(index));
        }
    }

    public Student getStudent(int index) {
        List<Student> students = repository.getAllStudents();
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        }
        return null;
    }

    public void deleteStudentByObject(Student student) {
        repository.removeStudent(student);
    }
}
