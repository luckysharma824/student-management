package in.system.studentmanagement.controller;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.StudentDTO;
import in.system.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student createdStudent = studentService.createStudent(studentDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Student created successfully");
        response.put("data", createdStudent);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long studentId,
            @Valid @RequestBody StudentDTO studentDTO) {
        Student updatedStudent = studentService.updateStudent(studentId, studentDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Student updated successfully");
        response.put("data", updatedStudent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Object> search(@RequestParam(required = false) Map<String, String> requestParams) {
        List<Student> studentList = studentService.search(requestParams);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", studentList.size());
        result.put("data", studentList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", students.size());
        result.put("data", students);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<Object> getStudentDetailById(@PathVariable("studentId") Long studentId) {
        StudentDTO student = studentService.getStudentDetail(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(value = "/code/{studentCode}")
    public ResponseEntity<Object> getStudentByCode(@PathVariable String studentCode) {
        Student student = studentService.getStudentByCode(studentCode);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(value = "/performance")
    public ResponseEntity<Object> studentPerformance(@RequestParam("semester") Integer semester,
            @RequestParam("branchCode") String branchCode) {
        return new ResponseEntity<>(studentService.performance(semester, branchCode), HttpStatus.OK);
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<Object> getStudentsBySemester(@PathVariable Integer semester) {
        List<Student> students = studentService.getStudentsBySemester(semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", students.size());
        response.put("semester", semester);
        response.put("data", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchCode}")
    public ResponseEntity<Object> getStudentsByBranch(@PathVariable String branchCode) {
        List<Student> students = studentService.getStudentsByBranch(branchCode);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", students.size());
        response.put("branchCode", branchCode);
        response.put("data", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<Object> getActiveStudents() {
        List<Student> students = studentService.getActiveStudents();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", students.size());
        response.put("data", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Student deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{studentId}/deactivate")
    public ResponseEntity<Object> deactivateStudent(@PathVariable Long studentId) {
        studentService.deactivateStudent(studentId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Student deactivated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
