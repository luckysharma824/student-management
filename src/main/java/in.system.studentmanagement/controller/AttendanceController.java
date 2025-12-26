package in.system.studentmanagement.controller;

import in.system.studentmanagement.dto.AttendanceDTO;
import in.system.studentmanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Object> recordAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO recordedAttendance = attendanceService.recordAttendance(attendanceDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Attendance recorded successfully");
        response.put("data", recordedAttendance);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<Object> updateAttendance(@PathVariable Long attendanceId,
            @Valid @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO updatedAttendance = attendanceService.updateAttendance(attendanceId, attendanceDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Attendance updated successfully");
        response.put("data", updatedAttendance);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<Object> getAttendanceById(@PathVariable Long attendanceId) {
        AttendanceDTO attendance = attendanceService.getAttendanceById(attendanceId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Object> getAttendanceByStudent(@PathVariable Long studentId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByStudentId(studentId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", attendances.size());
        response.put("studentId", studentId);
        response.put("data", attendances);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/semester/{semester}")
    public ResponseEntity<Object> getAttendanceByStudentAndSemester(@PathVariable Long studentId,
            @PathVariable Integer semester) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByStudentAndSemester(studentId, semester);
        Double percentage = attendanceService.getAttendancePercentage(studentId, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", attendances.size());
        response.put("studentId", studentId);
        response.put("semester", semester);
        response.put("attendancePercentage", String.format("%.2f%%", percentage));
        response.put("data", attendances);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Object> getAttendanceByCourse(@PathVariable Long courseId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseId(courseId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", attendances.size());
        response.put("courseId", courseId);
        response.put("data", attendances);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<Object> getAttendanceBySemester(@PathVariable Integer semester) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceBySemester(semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", attendances.size());
        response.put("semester", semester);
        response.put("data", attendances);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/percentage")
    public ResponseEntity<Object> getAttendancePercentage(@PathVariable Long studentId,
            @RequestParam Integer semester) {
        Double percentage = attendanceService.getAttendancePercentage(studentId, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("studentId", studentId);
        response.put("semester", semester);
        response.put("attendancePercentage", String.format("%.2f%%", percentage));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<Object> getAttendanceByDateRange(@RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam Integer semester) {
        List<AttendanceDTO> attendances = attendanceService.getAttendanceByDateRange(startDate, endDate, semester);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", attendances.size());
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("semester", semester);
        response.put("data", attendances);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Attendance record deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
