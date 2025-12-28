package in.system.studentmanagement.service;

import in.system.studentmanagement.dto.AttendanceDTO;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceDTO recordAttendance(AttendanceDTO attendanceDTO);

    AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO);

    AttendanceDTO getAttendanceById(Long id);

    List<AttendanceDTO> getAttendanceByStudentId(Long studentId);

    List<AttendanceDTO> getAttendanceByStudentAndSemester(Long studentId, Integer semester);

    List<AttendanceDTO> getAttendanceByCourseId(Long courseId);

    List<AttendanceDTO> getAttendanceBySemester(Integer semester);

    Long getStudentPresentCount(Long studentId, Integer semester);

    Double getAttendancePercentage(Long studentId, Integer semester);

    List<AttendanceDTO> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate, Integer semester);

    void deleteAttendance(Long id);

    // Code-based methods
    List<AttendanceDTO> getAttendanceByStudentCode(String studentCode);

    List<AttendanceDTO> getAttendanceByStudentCodeAndSemester(String studentCode, Integer semester);

    List<AttendanceDTO> getAttendanceByCourseCode(String courseCode);

    Double getAttendancePercentageByCode(String studentCode, Integer semester);
}
