# Student Management System - Enhanced Version

## Overview

This is an enhanced version of the Student Management System built with **Spring Boot 3.5.6** and **Java 21**. The system provides comprehensive functionality for managing students, teachers, courses, grades, and attendance.

## New Features Added

### 1. **Teacher Management Module**

- Create, read, update, and delete teacher records
- Track teacher information (name, email, phone, department, qualification, specialization)
- Search teachers by name or department
- Activate/deactivate teachers
- API Endpoints:
  - `POST /api/teacher` - Create new teacher
  - `GET /api/teacher` - Get all teachers
  - `GET /api/teacher/{teacherId}` - Get teacher by ID
  - `GET /api/teacher/active/list` - Get active teachers
  - `GET /api/teacher/department/{department}` - Get teachers by department
  - `GET /api/teacher/search?name={name}` - Search teachers
  - `PUT /api/teacher/{teacherId}` - Update teacher
  - `PUT /api/teacher/{teacherId}/deactivate` - Deactivate teacher
  - `DELETE /api/teacher/{teacherId}` - Delete teacher

### 2. **Course Management Module**

- Manage courses with comprehensive details
- Assign course coordinators (teachers)
- Filter courses by department
- Search and activate/deactivate courses
- API Endpoints:
  - `POST /api/course` - Create new course
  - `GET /api/course` - Get all courses
  - `GET /api/course/{courseId}` - Get course by ID
  - `GET /api/course/active/list` - Get active courses
  - `GET /api/course/department/{department}` - Get courses by department
  - `GET /api/course/search?name={name}` - Search courses
  - `PUT /api/course/{courseId}` - Update course
  - `PUT /api/course/{courseId}/deactivate` - Deactivate course
  - `DELETE /api/course/{courseId}` - Delete course

### 3. **Attendance Tracking Module**

- Record student attendance with status (PRESENT, ABSENT, LEAVE)
- Track attendance by student, course, or date range
- Calculate attendance percentage
- Generate attendance reports
- API Endpoints:
  - `POST /api/attendance` - Record attendance
  - `GET /api/attendance/{attendanceId}` - Get attendance record
  - `GET /api/attendance/student/{studentId}` - Get student's attendance
  - `GET /api/attendance/student/{studentId}/semester/{semester}` - Get attendance by semester
  - `GET /api/attendance/student/{studentId}/percentage` - Get attendance percentage
  - `GET /api/attendance/course/{courseId}` - Get course attendance
  - `GET /api/attendance/semester/{semester}` - Get semester attendance
  - `GET /api/attendance/date-range` - Get attendance by date range
  - `PUT /api/attendance/{attendanceId}` - Update attendance
  - `DELETE /api/attendance/{attendanceId}` - Delete attendance

### 4. **Grade Management Module**

- Create and manage student grades
- Support for internal and external marks
- Automatic GPA calculation
- Grade point assignment (A, B, C, D, E, F)
- Track grades by student, course, or semester
- API Endpoints:
  - `POST /api/grade` - Create grade record
  - `GET /api/grade/{gradeId}` - Get grade by ID
  - `GET /api/grade/student/{studentId}` - Get student's grades
  - `GET /api/grade/student/{studentId}/semester/{semester}` - Get semester grades
  - `GET /api/grade/student/{studentId}/average` - Get average grade
  - `GET /api/grade/student/{studentId}/gpa` - Get semester GPA
  - `GET /api/grade/course/{courseId}` - Get course grades
  - `GET /api/grade/course/{courseId}/semester/{semester}` - Get course semester grades
  - `PUT /api/grade/{gradeId}` - Update grade
  - `DELETE /api/grade/{gradeId}` - Delete grade

### 5. **Enhanced Student Module**

- Extended student profile with address, date of birth, city, state, zip code
- Create, update, and delete students
- Filter students by semester or branch
- Activate/deactivate students
- Improved search functionality
- API Endpoints:
  - `POST /api/student` - Create student
  - `GET /api/student` - Get all students
  - `GET /api/student/{studentId}` - Get student details
  - `GET /api/student/code/{studentCode}` - Get student by code
  - `GET /api/student/semester/{semester}` - Get students by semester
  - `GET /api/student/branch/{branchCode}` - Get students by branch
  - `GET /api/student/active` - Get active students
  - `GET /api/student/search` - Search students
  - `GET /api/student/performance` - Get performance analytics
  - `PUT /api/student/{studentId}` - Update student
  - `PUT /api/student/{studentId}/deactivate` - Deactivate student
  - `DELETE /api/student/{studentId}` - Delete student

## Technical Enhancements

### 1. **Validation Framework**

- Input validation using `jakarta.validation` annotations
- Custom validation rules for email, phone, marks, and semester
- Comprehensive error messages

### 2. **Entity Relationships**

- Proper JPA relationships between entities
- Foreign key constraints
- Cascading operations where appropriate
- Lazy loading for performance

### 3. **Audit Logging**

- `createdAt` and `updatedAt` timestamps on all entities
- Automatic timestamp management with `@PrePersist` and `@PreUpdate`
- Track entity lifecycle

### 4. **Data Mapping**

- ModelMapper integration for DTO to Entity conversion
- Consistent mapping across all entities
- Separation of concerns between DTOs and domain objects

### 5. **Exception Handling**

- Global exception handler with `@ControllerAdvice`
- Custom `ServiceException` for business logic errors
- Validation error handling with field-level details
- Consistent API response format

### 6. **API Response Format**

- Standardized `ApiResponse` wrapper for all responses
- Includes success/failure status, message, data, timestamp, and status code
- Consistent JSON structure across all endpoints

### 7. **Database Optimization**

- Strategic use of `@Transactional` annotation
- Read-only transactions for queries
- Batch operations configuration
- Index optimization through query methods

### 8. **Security Considerations**

- Spring Security dependency included
- Foundation for authentication/authorization implementation
- Input validation to prevent SQL injection

## Database Schema

### Core Entities

- **Student** - Student information with contact details and academic progress
- **Teacher** - Faculty information with department and qualification details
- **Course** - Course details with coordinator assignment
- **Grade** - Student grades with internal/external marks and GPA calculation
- **Attendance** - Attendance records with date and status tracking
- **StudentSemester** - Semester-wise academic performance (existing)

## Dependencies Added

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.1.1</version>
</dependency>
```

## Configuration

### Application Properties

- Base context path: `/api`
- Server port: `8080`
- Database: MySQL
- JPA/Hibernate configuration for optimal performance

## Project Structure

```
src/main/java/in/system/studentmanagement/
├── config/
│   └── ModelMapperConfig.java
├── controller/
│   ├── StudentController.java (Enhanced)
│   ├── TeacherController.java
│   ├── CourseController.java
│   ├── GradeController.java
│   └── AttendanceController.java
├── domain/
│   ├── Student.java (Enhanced)
│   ├── Teacher.java (New)
│   ├── Course.java (New)
│   ├── Grade.java (New)
│   ├── Attendance.java (New)
│   └── StudentSemester.java
├── dto/
│   ├── StudentDTO.java (Enhanced)
│   ├── TeacherDTO.java (New)
│   ├── CourseDTO.java (New)
│   ├── GradeDTO.java (New)
│   ├── AttendanceDTO.java (New)
│   └── StudentSemesterDTO.java
├── exception/
│   ├── ExceptionHandler.java (Enhanced)
│   └── ServiceException.java
├── repository/
│   ├── StudentRepository.java (Enhanced)
│   ├── TeacherRepository.java (New)
│   ├── CourseRepository.java (New)
│   ├── GradeRepository.java (New)
│   ├── AttendanceRepository.java (New)
│   ├── StudentSemesterRepository.java
│   └── CustomStudentRepository.java
├── service/
│   ├── StudentService.java (Enhanced)
│   ├── StudentServiceImpl.java (Enhanced)
│   ├── TeacherService.java (New)
│   ├── TeacherServiceImpl.java (New)
│   ├── CourseService.java (New)
│   ├── CourseServiceImpl.java (New)
│   ├── GradeService.java (New)
│   ├── GradeServiceImpl.java (New)
│   ├── AttendanceService.java (New)
│   └── AttendanceServiceImpl.java (New)
├── util/
│   └── ApiResponse.java (New)
└── StudentManagementApplication.java
```

## Usage Examples

### Create a Teacher

```bash
curl -X POST http://localhost:8080/api/teacher \
  -H "Content-Type: application/json" \
  -d '{
    "code": "T001",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "9876543210",
    "department": "Computer Science",
    "qualification": "B.Tech",
    "specialization": "Data Science"
  }'
```

### Create a Course

```bash
curl -X POST http://localhost:8080/api/course \
  -H "Content-Type: application/json" \
  -d '{
    "code": "CS101",
    "name": "Introduction to Computer Science",
    "description": "Basic concepts of computer science",
    "department": "Computer Science",
    "credits": 4,
    "totalSemesters": 2
  }'
```

### Record Attendance

```bash
curl -X POST http://localhost:8080/api/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1,
    "attendanceDate": "2024-01-15",
    "status": "PRESENT",
    "semester": 1
  }'
```

### Record Grades

```bash
curl -X POST http://localhost:8080/api/grade \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1,
    "internalMarks": 30,
    "externalMarks": 55,
    "semester": 1
  }'
```

## Future Enhancements

1. **Authentication & Authorization**

   - JWT-based authentication
   - Role-based access control (Admin, Teacher, Student)
   - OAuth2 integration

2. **Advanced Reporting**

   - PDF report generation
   - Excel export functionality
   - Dashboard analytics

3. **Pagination & Filtering**

   - Implement Spring Data's Page and Pageable
   - Advanced filtering options
   - Sorting capabilities

4. **File Management**

   - Student document upload
   - Certificate generation
   - Resume management

5. **Notifications**

   - Email notifications for grades
   - SMS alerts for attendance
   - In-app notifications

6. **REST API Documentation**
   - Swagger/OpenAPI integration
   - API versioning
   - Comprehensive API documentation

## Building and Running

### Prerequisites

- Java 21 or later
- Maven 3.9.0 or later
- MySQL 8.0 or later

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

## Notes

- All timestamps are stored in UTC
- Attendance percentage is calculated as: (Present Classes / Total Classes) \* 100
- Grade points are calculated based on total marks (internal + external)
- All API endpoints require proper validation before processing
- The system maintains referential integrity through foreign keys

---

**Version**: 2.0 (Enhanced)  
**Last Updated**: December 2024
