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

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Object> getGradesByStudent(@PathVariable Long studentId) {
        List<GradeDTO> grades = gradeService.getGradesByStudentId(studentId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("studentId", studentId);
        response.put("averageGrade", gradeService.getStudentAverageGrade(studentId));
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/semester/{semester}")
    public ResponseEntity<Object> getGradesByStudentAndSemester(@PathVariable Long studentId,
            @PathVariable Integer semester) {
        List<GradeDTO> grades = gradeService.getGradesByStudentAndSemester(studentId, semester);
        Double gpa = gradeService.getStudentSemesterGPA(studentId, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("studentId", studentId);
        response.put("semester", semester);
        response.put("semesterGPA", String.format("%.2f", gpa));
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Object> getGradesByCourse(@PathVariable Long courseId) {
        List<GradeDTO> grades = gradeService.getGradesByCourseId(courseId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("courseId", courseId);
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}/semester/{semester}")
    public ResponseEntity<Object> getGradesByCourseAndSemester(@PathVariable Long courseId,
            @PathVariable Integer semester) {
        List<GradeDTO> grades = gradeService.getGradesByCourseAndSemester(courseId, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", grades.size());
        response.put("courseId", courseId);
        response.put("semester", semester);
        response.put("data", grades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/average")
    public ResponseEntity<Object> getStudentAverageGrade(@PathVariable Long studentId) {
        Double average = gradeService.getStudentAverageGrade(studentId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("studentId", studentId);
        response.put("averageMarks", String.format("%.2f", average));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/gpa")
    public ResponseEntity<Object> getStudentGPA(@PathVariable Long studentId,
            @RequestParam Integer semester) {
        Double gpa = gradeService.getStudentSemesterGPA(studentId, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("studentId", studentId);
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
