package in.system.studentmanagement.controller;

import in.system.studentmanagement.dto.CourseDTO;
import in.system.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Course created successfully");
        response.put("data", createdCourse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable Long courseId,
            @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(courseId, courseDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Course updated successfully");
        response.put("data", updatedCourse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long courseId) {
        CourseDTO course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", courses.size());
        response.put("data", courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Object> getActiveCourses() {
        List<CourseDTO> courses = courseService.getActiveCourses();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", courses.size());
        response.put("data", courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<Object> getCoursesByDepartment(@PathVariable String department) {
        List<CourseDTO> courses = courseService.getCoursesByDepartment(department);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", courses.size());
        response.put("department", department);
        response.put("data", courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchCourse(@RequestParam String name) {
        List<CourseDTO> courses = courseService.searchCourseByName(name);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", courses.size());
        response.put("searchQuery", name);
        response.put("data", courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Course deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{courseId}/deactivate")
    public ResponseEntity<Object> deactivateCourse(@PathVariable Long courseId) {
        courseService.deactivateCourse(courseId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Course deactivated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
