# API Testing Guide

## Base URL

```
http://localhost:8080/api
```

## Teacher Management APIs

### 1. Create Teacher

```bash
POST /api/teacher
Content-Type: application/json

{
  "code": "T001",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "department": "Computer Science",
  "qualification": "B.Tech",
  "specialization": "Data Science"
}

Response: 201 CREATED
```

### 2. Get All Teachers

```bash
GET /api/teacher
Response: 200 OK
```

### 3. Get Teacher by ID

```bash
GET /api/teacher/1
Response: 200 OK
```

### 4. Get Active Teachers

```bash
GET /api/teacher/active/list
Response: 200 OK
```

### 5. Get Teachers by Department

```bash
GET /api/teacher/department/Computer Science
Response: 200 OK
```

### 6. Search Teachers by Name

```bash
GET /api/teacher/search?name=John
Response: 200 OK
```

### 7. Update Teacher

```bash
PUT /api/teacher/1
Content-Type: application/json

{
  "code": "T001",
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phone": "9876543211",
  "department": "Information Technology",
  "qualification": "M.Tech",
  "specialization": "Cloud Computing"
}

Response: 200 OK
```

### 8. Deactivate Teacher

```bash
PUT /api/teacher/1/deactivate
Response: 200 OK
```

### 9. Delete Teacher

```bash
DELETE /api/teacher/1
Response: 200 OK
```

---

## Course Management APIs

### 1. Create Course

```bash
POST /api/course
Content-Type: application/json

{
  "code": "CS101",
  "name": "Introduction to Computer Science",
  "description": "Basic concepts and fundamentals",
  "department": "Computer Science",
  "credits": 4,
  "totalSemesters": 2,
  "coordinatorTeacherId": 1
}

Response: 201 CREATED
```

### 2. Get All Courses

```bash
GET /api/course
Response: 200 OK
```

### 3. Get Course by ID

```bash
GET /api/course/1
Response: 200 OK
```

### 4. Get Active Courses

```bash
GET /api/course/active/list
Response: 200 OK
```

### 5. Get Courses by Department

```bash
GET /api/course/department/Computer Science
Response: 200 OK
```

### 6. Search Courses by Name

```bash
GET /api/course/search?name=Introduction
Response: 200 OK
```

### 7. Update Course

```bash
PUT /api/course/1
Content-Type: application/json

{
  "code": "CS101",
  "name": "Advanced Computer Science",
  "description": "Advanced concepts",
  "department": "Computer Science",
  "credits": 4,
  "totalSemesters": 2,
  "coordinatorTeacherId": 1,
  "isActive": true
}

Response: 200 OK
```

### 8. Deactivate Course

```bash
PUT /api/course/1/deactivate
Response: 200 OK
```

### 9. Delete Course

```bash
DELETE /api/course/1
Response: 200 OK
```

---

## Student Management APIs

### 1. Create Student

```bash
POST /api/student
Content-Type: application/json

{
  "code": "STU001",
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice.smith@example.com",
  "mobile": "9876543210",
  "admissionYear": 2023,
  "branchCode": "CSE",
  "course": "B.Tech",
  "currentSemester": 1,
  "dateOfBirth": "2005-01-15",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001"
}

Response: 201 CREATED
```

### 2. Get All Students

```bash
GET /api/student
Response: 200 OK
```

### 3. Get Student by ID

```bash
GET /api/student/1
Response: 200 OK
```

### 4. Get Student by Code

```bash
GET /api/student/code/STU001
Response: 200 OK
```

### 5. Get Students by Semester

```bash
GET /api/student/semester/1
Response: 200 OK
```

### 6. Get Students by Branch

```bash
GET /api/student/branch/CSE
Response: 200 OK
```

### 7. Get Active Students

```bash
GET /api/student/active
Response: 200 OK
```

### 8. Search Students

```bash
GET /api/student/search?firstName=Alice
Response: 200 OK
```

### 9. Update Student

```bash
PUT /api/student/1
Content-Type: application/json

{
  "code": "STU001",
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice.johnson@example.com",
  "mobile": "9876543211",
  "admissionYear": 2023,
  "branchCode": "CSE",
  "course": "B.Tech",
  "currentSemester": 2,
  "dateOfBirth": "2005-01-15",
  "address": "456 Oak Ave",
  "city": "Boston",
  "state": "MA",
  "zipCode": "02101"
}

Response: 200 OK
```

### 10. Deactivate Student

```bash
PUT /api/student/1/deactivate
Response: 200 OK
```

### 11. Delete Student

```bash
DELETE /api/student/1
Response: 200 OK
```

---

## Attendance Management APIs

### 1. Record Attendance

```bash
POST /api/attendance
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "attendanceDate": "2024-01-15",
  "status": "PRESENT",
  "remarks": "On time",
  "semester": 1
}

Response: 201 CREATED
```

### 2. Get Attendance by ID

```bash
GET /api/attendance/1
Response: 200 OK
```

### 3. Get Student Attendance

```bash
GET /api/attendance/student/1
Response: 200 OK
```

### 4. Get Student Attendance by Semester

```bash
GET /api/attendance/student/1/semester/1
Response: 200 OK
```

### 5. Get Attendance Percentage

```bash
GET /api/attendance/student/1/percentage?semester=1
Response: 200 OK
```

### 6. Get Course Attendance

```bash
GET /api/attendance/course/1
Response: 200 OK
```

### 7. Get Semester Attendance

```bash
GET /api/attendance/semester/1
Response: 200 OK
```

### 8. Get Attendance by Date Range

```bash
GET /api/attendance/date-range?startDate=2024-01-01&endDate=2024-01-31&semester=1
Response: 200 OK
```

### 9. Update Attendance

```bash
PUT /api/attendance/1
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "status": "ABSENT",
  "remarks": "Medical leave",
  "semester": 1
}

Response: 200 OK
```

### 10. Delete Attendance

```bash
DELETE /api/attendance/1
Response: 200 OK
```

---

## Grade Management APIs

### 1. Create Grade

```bash
POST /api/grade
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "teacherId": 1,
  "semester": 1,
  "internalMarks": 30,
  "externalMarks": 55,
  "remarks": "Good performance"
}

Response: 201 CREATED
```

### 2. Get Grade by ID

```bash
GET /api/grade/1
Response: 200 OK
```

### 3. Get Student Grades

```bash
GET /api/grade/student/1
Response: 200 OK
```

### 4. Get Student Grades by Semester

```bash
GET /api/grade/student/1/semester/1
Response: 200 OK
```

### 5. Get Student Average Grade

```bash
GET /api/grade/student/1/average
Response: 200 OK
```

### 6. Get Student Semester GPA

```bash
GET /api/grade/student/1/gpa?semester=1
Response: 200 OK
```

### 7. Get Course Grades

```bash
GET /api/grade/course/1
Response: 200 OK
```

### 8. Get Course Grades by Semester

```bash
GET /api/grade/course/1/semester/1
Response: 200 OK
```

### 9. Update Grade

```bash
PUT /api/grade/1
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "internalMarks": 32,
  "externalMarks": 58,
  "remarks": "Very good performance"
}

Response: 200 OK
```

### 10. Delete Grade

```bash
DELETE /api/grade/1
Response: 200 OK
```

---

## Error Responses

### Validation Error (400)

```json
{
  "success": false,
  "message": "Validation failed",
  "data": {
    "email": "Invalid email format",
    "phone": "Mobile number must be 10 digits"
  },
  "statusCode": 400,
  "timestamp": "2024-01-15T10:30:00"
}
```

### Not Found Error (400)

```json
{
  "success": false,
  "message": "Student not found with id 999",
  "data": null,
  "statusCode": 400,
  "timestamp": "2024-01-15T10:30:00"
}
```

### Internal Server Error (500)

```json
{
  "success": false,
  "message": "An unexpected error occurred: Database connection failed",
  "data": null,
  "statusCode": 500,
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## Success Responses

### Creation Success (201)

```json
{
  "success": true,
  "message": "Student created successfully",
  "data": {
    "id": 1,
    "code": "STU001",
    "firstName": "Alice",
    "lastName": "Smith",
    "email": "alice.smith@example.com",
    ...
  },
  "statusCode": 201,
  "timestamp": "2024-01-15T10:30:00"
}
```

### Retrieval Success (200)

```json
{
  "success": true,
  "total": 5,
  "data": [
    { "id": 1, "firstName": "Alice", ... },
    { "id": 2, "firstName": "Bob", ... }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## Testing Steps

1. **Start the Application**

   ```bash
   mvn spring-boot:run
   ```

2. **Create a Teacher**

   - Use the Create Teacher API call above

3. **Create a Course**

   - Assign the created teacher as coordinator

4. **Create a Student**

   - Register a new student

5. **Record Attendance**

   - Mark attendance for the student in the course

6. **Add Grades**

   - Record marks and grades

7. **Run Analytics**
   - Get performance, attendance percentage, GPA

---

## Postman Collection

Import the following into Postman for easier testing:

```json
{
  "info": {
    "name": "Student Management API",
    "version": "2.0"
  },
  "item": [
    {
      "name": "Teachers",
      "item": [
        {
          "name": "Create Teacher",
          "request": { "method": "POST", "url": "{{baseUrl}}/teacher" }
        },
        {
          "name": "Get All Teachers",
          "request": { "method": "GET", "url": "{{baseUrl}}/teacher" }
        }
      ]
    },
    {
      "name": "Courses",
      "item": [
        {
          "name": "Create Course",
          "request": { "method": "POST", "url": "{{baseUrl}}/course" }
        },
        {
          "name": "Get All Courses",
          "request": { "method": "GET", "url": "{{baseUrl}}/course" }
        }
      ]
    }
  ]
}
```

Set `{{baseUrl}}` to `http://localhost:8080/api`

---

**Note**: All dates should be in `YYYY-MM-DD` format. Phone numbers should be exactly 10 or 12 digits.
