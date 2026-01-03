package com.school.rms.model;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
        private List<Course> courses;

        public CourseRepository() {
                this.courses = new ArrayList<>();
                initializeDummyData();
        }

        private void initializeDummyData() {
                
                courses.add(new Course("1", "22.11.2022", "Spring 2024", "Inequalities in Numbers", "Topic Explanation",
                                "Ahmed Hassan", "Published"));
                courses.add(
                                new Course("2", "22.11.2022", "Fall 2023", "Inequalities in Numbers", "AYT",
                                                "Fatima Rahman", "Published"));
                courses.add(new Course("3", "22.11.2022", "Spring 2024", "Inequalities in Numbers", "Topic Explanation",
                                "Omar Ali", "Published"));
                courses.add(new Course("4", "22.11.2022", "Summer 2023", "Inequalities in Numbers", "Question Solving",
                                "Aisha Khan", "Draft"));
                courses.add(new Course("5", "22.11.2022", "Fall 2023", "Inequalities in Numbers", "Question Solving",
                                "Yusuf Ibrahim", "Published"));
                courses.add(
                                new Course("6", "22.11.2022", "Spring 2024", "Inequalities in Numbers", "Test",
                                                "Sara Ahmed", "Published"));
                courses.add(new Course("7", "22.11.2022", "Summer 2023", "Inequalities in Numbers", "Topic Explanation",
                                "Zainab Malik", "Draft"));
                courses.add(new Course("8", "22.11.2022", "Fall 2024", "Inequalities in Numbers", "Topic Explanation",
                                "Hassan Mahmood", "Draft"));
                courses.add(new Course("9", "22.11.2022", "Spring 2024", "Inequalities in Numbers", "Topic Explanation",
                                "Layla Hussain", "Draft"));
        }

        public List<Course> getAllCourses() {
                return courses; 
        }

        public void addCourse(Course course) {
                courses.add(course);
        }

        public void removeCourse(Course course) {
                courses.remove(course);
        }
}
