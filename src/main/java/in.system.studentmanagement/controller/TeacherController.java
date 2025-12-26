package in.system.studentmanagement.controller;

import in.system.studentmanagement.dto.TeacherDTO;
import in.system.studentmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Teacher created successfully");
        response.put("data", createdTeacher);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Object> updateTeacher(@PathVariable Long teacherId,
            @Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(teacherId, teacherDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Teacher updated successfully");
        response.put("data", updatedTeacher);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Object> getTeacherById(@PathVariable Long teacherId) {
        TeacherDTO teacher = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", teachers.size());
        response.put("data", teachers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Object> getActiveTeachers() {
        List<TeacherDTO> teachers = teacherService.getActiveTeachers();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", teachers.size());
        response.put("data", teachers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<Object> getTeachersByDepartment(@PathVariable String department) {
        List<TeacherDTO> teachers = teacherService.getTeachersByDepartment(department);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", teachers.size());
        response.put("department", department);
        response.put("data", teachers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTeacher(@RequestParam String name) {
        List<TeacherDTO> teachers = teacherService.searchTeacherByName(name);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", teachers.size());
        response.put("searchQuery", name);
        response.put("data", teachers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Teacher deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{teacherId}/deactivate")
    public ResponseEntity<Object> deactivateTeacher(@PathVariable Long teacherId) {
        teacherService.deactivateTeacher(teacherId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Teacher deactivated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
