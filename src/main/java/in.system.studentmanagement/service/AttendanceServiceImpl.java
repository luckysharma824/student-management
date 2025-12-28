package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Attendance;
import in.system.studentmanagement.domain.Course;
import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.AttendanceDTO;
import in.system.studentmanagement.exception.ServiceException;
import in.system.studentmanagement.repository.AttendanceRepository;
import in.system.studentmanagement.repository.CourseRepository;
import in.system.studentmanagement.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AttendanceDTO recordAttendance(AttendanceDTO attendanceDTO) {
        Student student = studentRepository.findById(attendanceDTO.getStudentId())
                .orElseThrow(() -> new ServiceException("Student not found"));

        Course course = courseRepository.findById(attendanceDTO.getCourseId())
                .orElseThrow(() -> new ServiceException("Course not found"));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setAttendanceDate(attendanceDTO.getAttendanceDate());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setRemarks(attendanceDTO.getRemarks());
        attendance.setSemester(attendanceDTO.getSemester());

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return mapToAttendanceDTO(savedAttendance);
    }

    @Override
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Attendance record not found"));

        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setRemarks(attendanceDTO.getRemarks());

        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return mapToAttendanceDTO(updatedAttendance);
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceDTO getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Attendance record not found"));
        return mapToAttendanceDTO(attendance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToAttendanceDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByStudentAndSemester(Long studentId, Integer semester) {
        return attendanceRepository.findByStudentIdAndSemester(studentId, semester)
                .stream()
                .map(this::mapToAttendanceDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByCourseId(Long courseId) {
        return attendanceRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToAttendanceDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceBySemester(Integer semester) {
        return attendanceRepository.findBySemester(semester)
                .stream()
                .map(this::mapToAttendanceDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getStudentPresentCount(Long studentId, Integer semester) {
        return attendanceRepository.countPresentClassesByStudentAndSemester(studentId, semester);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAttendancePercentage(Long studentId, Integer semester) {
        List<Attendance> allClasses = attendanceRepository.findByStudentIdAndSemester(studentId, semester);
        if (allClasses.isEmpty()) {
            return 0.0;
        }
        Long presentCount = attendanceRepository.countPresentClassesByStudentAndSemester(studentId, semester);
        return (presentCount * 100.0) / allClasses.size();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate, Integer semester) {
        return attendanceRepository.findAttendanceByDateRangeAndSemester(startDate, endDate, semester)
                .stream()
                .map(this::mapToAttendanceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Attendance record not found"));
        attendanceRepository.delete(attendance);
    }

    private AttendanceDTO mapToAttendanceDTO(Attendance attendance) {
        AttendanceDTO dto = modelMapper.map(attendance, AttendanceDTO.class);
        dto.setStudentCode(attendance.getStudent().getCode());
        dto.setStudentName(attendance.getStudent().getFirstName() + " " + attendance.getStudent().getLastName());
        dto.setCourseCode(attendance.getCourse().getCode());
        dto.setCourseName(attendance.getCourse().getName());
        return dto;
    }

    // Code-based method implementations
    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByStudentCode(String studentCode) {
        Student student = studentRepository.findByCode(studentCode)
                .orElseThrow(() -> new ServiceException("Student not found with code: " + studentCode));
        return getAttendanceByStudentId(student.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByStudentCodeAndSemester(String studentCode, Integer semester) {
        Student student = studentRepository.findByCode(studentCode)
                .orElseThrow(() -> new ServiceException("Student not found with code: " + studentCode));
        return getAttendanceByStudentAndSemester(student.getId(), semester);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByCourseCode(String courseCode) {
        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ServiceException("Course not found with code: " + courseCode));
        return getAttendanceByCourseId(course.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAttendancePercentageByCode(String studentCode, Integer semester) {
        Student student = studentRepository.findByCode(studentCode)
                .orElseThrow(() -> new ServiceException("Student not found with code: " + studentCode));
        return getAttendancePercentage(student.getId(), semester);
    }
}
