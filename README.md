# 🎓 Student Management System

A full-stack web application built with **Spring Boot** to manage 
student records with secure login and database integration.

## ✨ Features
- ➕ Add / Edit / Delete student records
- 📋 View all students
- 🔐 Secure login with Spring Security
- 🗄️ MySQL database integration
- 🌐 Web interface with HTML & CSS

## 🛠️ Tech Stack
![Java](https://img.shields.io/badge/-Java-007396?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/-SpringBoot-6DB33F?style=flat&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/-MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
![HTML](https://img.shields.io/badge/-HTML-E34F26?style=flat&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/-CSS-1572B6?style=flat&logo=css3&logoColor=white)

## ⚙️ How to Run
```bash
# Clone the repository
git clone https://github.com/Rohit-Kalaskar/student-management-system

# Configure MySQL in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=your_username
spring.datasource.password=your_password

# Run the application
./mvnw spring-boot:run
```

## 📁 Project Structure
```
src/main/java/student_management/
├── controller/    # Web request handlers
├── model/         # Entity classes
├── repository/    # Database layer
├── security/      # Authentication & Authorization
└── service/       # Business logic
```
