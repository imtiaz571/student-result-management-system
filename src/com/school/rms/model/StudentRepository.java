package com.school.rms.model;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        initializeDummyData();
    }

    private void initializeDummyData() {
        // Sample students with session and batch number
        Student student1 = new Student("1", "Ahmed Hassan", "Spring", "1", "Computer Science");
        student1.addSubject(new Subject("Data Structures", 85));
        student1.addSubject(new Subject("Algorithms", 90));
        student1.addSubject(new Subject("Database Systems", 82));
        student1.addSubject(new Subject("Web Development", 88));
        students.add(student1);

        Student student2 = new Student("2", "Fatima Rahman", "Autumn", "2", "Electrical Engineering");
        student2.addSubject(new Subject("Circuit Analysis", 92));
        student2.addSubject(new Subject("Digital Electronics", 89));
        student2.addSubject(new Subject("Signal Processing", 91));
        student2.addSubject(new Subject("Power Systems", 87));
        students.add(student2);

        Student student3 = new Student("3", "Omar Ali", "Spring", "1", "Computer Science");
        student3.addSubject(new Subject("Operating Systems", 80));
        student3.addSubject(new Subject("Computer Networks", 84));
        student3.addSubject(new Subject("Software Engineering", 86));
        student3.addSubject(new Subject("Machine Learning", 82));
        students.add(student3);

        Student student4 = new Student("4", "Aisha Khan", "Summer", "3", "Business Administration");
        student4.addSubject(new Subject("Marketing", 75));
        student4.addSubject(new Subject("Finance", 78));
        student4.addSubject(new Subject("Management", 76));
        student4.addSubject(new Subject("Accounting", 80));
        students.add(student4);

        Student student5 = new Student("5", "Yusuf Ibrahim", "Autumn", "2", "Mechanical Engineering");
        student5.addSubject(new Subject("Thermodynamics", 79));
        student5.addSubject(new Subject("Fluid Mechanics", 81));
        student5.addSubject(new Subject("Manufacturing", 77));
        student5.addSubject(new Subject("Machine Design", 83));
        students.add(student5);

        Student student6 = new Student("6", "Sara Ahmed", "Spring", "1", "Civil Engineering");
        student6.addSubject(new Subject("Structural Analysis", 88));
        student6.addSubject(new Subject("Concrete Design", 90));
        student6.addSubject(new Subject("Transportation", 85));
        student6.addSubject(new Subject("Surveying", 87));
        students.add(student6);

        Student student7 = new Student("7", "Zainab Malik", "Summer", "3", "Computer Science");
        student7.addSubject(new Subject("Programming", 72));
        student7.addSubject(new Subject("Data Structures", 74));
        student7.addSubject(new Subject("Algorithms", 76));
        student7.addSubject(new Subject("Database", 75));
        students.add(student7);

        Student student8 = new Student("8", "Hassan Mahmood", "Autumn", "4", "Electrical Engineering");
        student8.addSubject(new Subject("Circuit Theory", 70));
        student8.addSubject(new Subject("Electronics", 73));
        student8.addSubject(new Subject("Electromagnetics", 71));
        student8.addSubject(new Subject("Control Systems", 75));
        students.add(student8);

        Student student9 = new Student("9", "Layla Hussain", "Spring", "1", "Architecture");
        student9.addSubject(new Subject("Design Studio", 84));
        student9.addSubject(new Subject("Building Materials", 82));
        student9.addSubject(new Subject("History of Architecture", 86));
        student9.addSubject(new Subject("Urban Planning", 85));
        students.add(student9);

        // Example of a failed student
        Student student10 = new Student("10", "Ali Rahman", "Spring", "2", "Computer Science");
        student10.addSubject(new Subject("Programming", 35)); // Failed
        student10.addSubject(new Subject("Mathematics", 65));
        student10.addSubject(new Subject("Physics", 55));
        students.add(student10);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
