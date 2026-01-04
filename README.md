# 🎓 Student Result Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)

A robust and modern desktop application tailored for educational institutions to manage student records, courses, enrollments, and academic results efficiently. Built with Java Swing, it features a polished custom UI and seamless data persistence.

---

## 📸 Screenshots

*(Add your screenshots here. Example: `![Dashboard](screenshots/dashboard.png)`)*

> **Note**: This application uses a custom-built Swing UI library for a modern look and feel, departing from the standard "gray" Java interfaces.

---

## ✨ Key Features

- **🖥️ Dashboard Hub**: A central overview of key metrics (Total Students, Courses, recent activities).
- **👤 Student Management**: Full CRUD (Create, Read, Update, Delete) operations for student profiles.
- **📚 Course Administration**: Manage subjects and course details easily.
- **📝 Enrollment System**: Enroll students into courses and track their academic progress.
- **💾 Auto-Persistence**: all data is automatically saved to a local `students.dat` file, ensuring no data loss between sessions.
- **📊 Grade Calculation**: Automated grading logic based on input scores.
- **🎨 Modern Aesthetics**:
  - Custom flat-design buttons and badges.
  - Responsive table layouts with status indicators.
  - Clean, professional color palette.

---

## 🛠️ Tech Stack & Architecture

- **Language**: Java (JDK 8+)
- **GUI Framework**: Swing (javax.swing)
- **Architecture**: MVC (Model-View-Controller) pattern for clean code separation.
  - **Model**: `com.school.rms.model` (Data handling & Business logic)
  - **View**: `com.school.rms.view` (UI components)
  - **Controller/Util**: `com.school.rms.util` (Helper functions)

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher is required.
- **Git**: To clone the repository.

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/student-result-management-system.git
   cd student-result-management-system
   ```

2. **Compile the Source Code**
   Navigate to the `src` directory:
   ```bash
   cd src
   javac com/school/rms/Main.java
   ```

3. **Run the Application**
   ```bash
   java com.school.rms.Main
   ```

---

## 📂 Project Structure

```bash
com.school.rms
├── Main.java                # Application Entry Point
├── model/                   # Data Models & Repositories
│   ├── Student.java
│   ├── Course.java
│   └── ...
├── view/                    # UI Forms & Custom Components
│   ├── MainDashboard.java
│   ├── StudentForm.java
│   ├── components/          # Custom Buttons, Tables, Badges
│   └── ...
└── util/                    # Utilities
    ├── GradeCalculator.java
    └── Theme.java
```

---

