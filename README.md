# Student Management System - Getting Started

## Project Overview

A comprehensive **Spring Boot 3.5.6** application for managing a complete student education ecosystem including students, teachers, courses, grades, and attendance tracking.

## Quick Start

### Prerequisites

- Java 21 or later
- Maven 3.9.0+
- MySQL 8.0+
- IDE (VS Code, IntelliJ IDEA, Eclipse)

### Installation

1. **Clone/Open the Project**

   ```bash
   cd student-management
   ```

2. **Configure Database**
   Edit `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student_management
   spring.datasource.username=your_mysql_user
   spring.datasource.password=your_mysql_password
   ```

3. **Create Database**

   ```bash
   mysql -u root -p
   CREATE DATABASE student_management;
   ```

4. **Build Project**

   ```bash
   mvn clean install
   ```

5. **Run Application**

   ```bash
   mvn spring-boot:run
   ```

   Application will start on `http://localhost:8080`

### Verify Installation

```bash
curl http://localhost:8080/api/student
```

You should get a JSON response.

---

## Project Structure

```
student-management/
├── pom.xml                           # Maven configuration
├── README.md                         # This file
├── ENHANCEMENTS.md                   # Detailed feature documentation
├── ENHANCEMENT_SUMMARY.md            # Summary of all changes
├── API_TESTING_GUIDE.md             # API testing examples
│
├── src/main/
│   ├── java/in/system/studentmanagement/
│   │   ├── StudentManagementApplication.java  # Main application class
│   │   ├── controller/               # REST API controllers (4 new + 1 enhanced)
│   │   ├── service/                  # Business logic (8 new services)
│   │   ├── repository/               # Data access layer (4 new repositories)
│   │   ├── domain/                   # Entity classes (4 new entities)
│   │   ├── dto/                      # Data transfer objects (5 DTOs)
│   │   ├── exception/                # Exception handling
│   │   ├── config/                   # Spring configuration
│   │   └── util/                     # Utility classes
│   │
│   └── resources/
│       └── application.properties    # Application configuration
│
└── src/test/
    └── java/                         # Test files
```

---

## Key Features

### ✅ Complete CRUD Operations

- Students, Teachers, Courses, Grades, Attendance
- Soft delete with active/inactive status

### ✅ Advanced Querying

- Search by multiple criteria
- Filter by department, semester, branch
- Date range filtering
- Custom JPA queries

### ✅ Analytics

- Student performance analysis
- GPA and grade calculations
- Attendance percentage tracking
- Department-wise statistics

### ✅ Data Validation

- Input validation on all API endpoints
- Custom validation rules
- Field-level error messages
- Consistent error responses

### ✅ Database Design

- Proper relationships and constraints
- Audit timestamps
- Foreign key integrity
- Performance optimization

---

## Main Components

### Controllers (5)

1. **StudentController** - 12 endpoints for student management
2. **TeacherController** - 8 endpoints for teacher management
3. **CourseController** - 8 endpoints for course management
4. **GradeController** - 10 endpoints for grade management
5. **AttendanceController** - 10 endpoints for attendance tracking

### Services (8)

- StudentService, TeacherService, CourseService, GradeService, AttendanceService
- Each with full business logic and validation

### Repositories (8)

- Custom JPA queries for complex operations
- Optimized for performance

### Entities (5)

- Student, Teacher, Course, Grade, Attendance
- Proper relationships and constraints

---

## API Endpoints Overview

### Base URL: `http://localhost:8080/api`

| Module     | Endpoints |
| ---------- | --------- |
| Student    | 12        |
| Teacher    | 8         |
| Course     | 8         |
| Grade      | 10        |
| Attendance | 10        |
| **Total**  | **48**    |

See [API_TESTING_GUIDE.md](API_TESTING_GUIDE.md) for complete endpoint documentation.

---

## Example API Calls

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

### Get All Courses

```bash
curl http://localhost:8080/api/course
```

### Record Student Attendance

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

### Get Student Grades by Semester

```bash
curl http://localhost:8080/api/grade/student/1/semester/1
```

---

## Configuration Files

### application.properties

Located in `src/main/resources/`

Key configurations:

```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## Database Tables

### Entities Created

1. **students** - Student information
2. **teachers** - Teacher information
3. **courses** - Course details
4. **grades** - Student grades
5. **attendance** - Attendance records
6. **student_semester** - Semester-wise performance

All tables include:

- Primary keys
- Foreign keys
- Timestamps (created_at, updated_at)
- Status fields (is_active)

---

## Error Handling

All errors return standardized JSON response:

```json
{
  "success": false,
  "message": "Error description",
  "data": null,
  "statusCode": 400,
  "timestamp": "2024-01-15T10:30:00"
}
```

### Common Status Codes

- **201** - Created successfully
- **200** - OK
- **400** - Bad request / Validation error
- **404** - Not found
- **500** - Internal server error

---

## Validation Rules

### Student

- Email must be unique and valid format
- Mobile 10-12 digits
- Semester 1-8
- Admission year must be a valid year

### Teacher

- Code must be unique
- Email must be unique and valid
- Phone 10-12 digits

### Course

- Code must be unique
- Credits 1-6
- Department required

### Grade

- Internal marks 0-40
- External marks 0-60
- Semester 1-8

### Attendance

- Status: PRESENT, ABSENT, or LEAVE
- Semester 1-8

---

## Development Tips

### Enable Detailed Logging

Edit `application.properties`:

```properties
logging.level.in.system.studentmanagement=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

### View SQL Queries

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Use Postman for Testing

- Import the collection from [API_TESTING_GUIDE.md](API_TESTING_GUIDE.md)
- Set base URL: `http://localhost:8080/api`
- Save responses for documentation

---

## Testing

### Run Tests

```bash
mvn test
```

### Build Without Running Tests

```bash
mvn clean install -DskipTests
```

---

## Troubleshooting

### Port Already in Use

```bash
# Change port in application.properties
server.port=8081
```

### Database Connection Error

```bash
# Check MySQL service
sudo service mysql status
sudo service mysql start
```

### Build Fails

```bash
# Clean Maven cache
mvn clean
mvn install
```

---

## Next Steps

1. **Review Documentation**

   - Read [ENHANCEMENTS.md](ENHANCEMENTS.md) for detailed features
   - Check [API_TESTING_GUIDE.md](API_TESTING_GUIDE.md) for API examples

2. **Test the APIs**

   - Use Postman or curl
   - Start with simple GET requests
   - Then test POST/PUT/DELETE operations

3. **Explore the Code**

   - Start with StudentController
   - Understand the Service layer
   - Review entity relationships

4. **Plan Enhancements**
   - Authentication & Authorization
   - Report generation
   - Email notifications
   - UI/Dashboard

---

## File Documentation

| File                   | Purpose                           |
| ---------------------- | --------------------------------- |
| ENHANCEMENTS.md        | Complete feature documentation    |
| ENHANCEMENT_SUMMARY.md | Summary of all changes            |
| API_TESTING_GUIDE.md   | API examples and testing          |
| README.md              | This file - getting started guide |

---

## Support & Resources

### Spring Boot Documentation

- https://spring.io/projects/spring-boot

### Spring Data JPA

- https://spring.io/projects/spring-data-jpa

### MySQL Documentation

- https://dev.mysql.com/doc/

### Java 21 Features

- https://docs.oracle.com/en/java/javase/21/

---

## Statistics

- **Lines of Code**: 3000+
- **New Classes**: 26
- **Modified Classes**: 6
- **API Endpoints**: 48
- **Test Coverage**: Foundational

---

## License

This project is provided as-is for educational and development purposes.

---

## Version Info

- **Version**: 2.0 (Enhanced)
- **Java**: 21
- **Spring Boot**: 3.5.6
- **MySQL**: 8.0+
- **Maven**: 3.9.0+

---

**Last Updated**: December 2024

For detailed information, see the documentation files included in the project.
