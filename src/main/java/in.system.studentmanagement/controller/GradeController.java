package in.system.studentmanagement.controller;

import in.system.studentmanagement.dto.GradeDTO;
import in.system.studentmanagement.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<Object> createGrade(@Valid @RequestBody GradeDTO gradeDTO) {
        GradeDTO createdGrade = gradeService.createGrade(gradeDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Grade created successfully");
        response.put("data", createdGrade);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{gradeId}")
    public ResponseEntity<Object> updateGrade(@PathVariable Long gradeId,
            @Valid @RequestBody GradeDTO gradeDTO) {
        GradeDTO updatedGrade = gradeService.updateGrade(gradeId, gradeDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Grade updated successfully");
        response.put("data", updatedGrade);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{gradeId}")
    public ResponseEntity<Object> getGradeById(@PathVariable Long gradeId) {
        GradeDTO grade = gradeService.getGradeById(gradeId);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @GetMapping("/student/{studentCode}")
    public ResponseEntity<Object> getGradesByStudent(@PathVariable String studentCode) {
        List<GradeDTO> grades = gradeService.getGradesByStudentCode(studentCode);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("studentCode", studentCode);
        response.put("averageGrade", gradeService.getStudentAverageGradeByCode(studentCode));
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentCode}/semester/{semester}")
    public ResponseEntity<Object> getGradesByStudentAndSemester(@PathVariable String studentCode,
            @PathVariable Integer semester) {
        List<GradeDTO> grades = gradeService.getGradesByStudentCodeAndSemester(studentCode, semester);
        Double gpa = gradeService.getStudentSemesterGPAByCode(studentCode, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("studentCode", studentCode);
        response.put("semester", semester);
        response.put("semesterGPA", String.format("%.2f", gpa));
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course/{courseCode}")
    public ResponseEntity<Object> getGradesByCourse(@PathVariable String courseCode) {
        List<GradeDTO> grades = gradeService.getGradesByCourseCode(courseCode);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("courseCode", courseCode);
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course/{courseCode}/semester/{semester}")
    public ResponseEntity<Object> getGradesByCourseAndSemester(@PathVariable String courseCode,
            @PathVariable Integer semester) {
        List<GradeDTO> grades = gradeService.getGradesByCourseCodeAndSemester(courseCode, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("courseCode", courseCode);
        response.put("semester", semester);
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentCode}/average")
    public ResponseEntity<Object> getStudentAverageGrade(@PathVariable String studentCode) {
        Double average = gradeService.getStudentAverageGradeByCode(studentCode);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("studentCode", studentCode);
        response.put("averageMarks", String.format("%.2f", average));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentCode}/gpa")
    public ResponseEntity<Object> getStudentGPA(@PathVariable String studentCode,
            @RequestParam Integer semester) {
        Double gpa = gradeService.getStudentSemesterGPAByCode(studentCode, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("studentCode", studentCode);
        response.put("semester", semester);
        response.put("gpa", String.format("%.2f", gpa));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{gradeId}")
    public ResponseEntity<Object> deleteGrade(@PathVariable Long gradeId) {
        gradeService.deleteGrade(gradeId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Grade deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
