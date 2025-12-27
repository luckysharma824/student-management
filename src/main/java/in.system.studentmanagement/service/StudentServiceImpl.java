package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.StudentDTO;
import in.system.studentmanagement.dto.StudentSemesterDTO;
import in.system.studentmanagement.exception.ServiceException;
import in.system.studentmanagement.repository.CustomStudentRepository;
import in.system.studentmanagement.repository.StudentRepository;
import in.system.studentmanagement.repository.StudentSemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentSemesterRepository semesterRepository;
    @Autowired
    private CustomStudentRepository customStudentRepository;

    @Override
    public StudentDTO getStudentDetail(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            List<StudentSemesterDTO> marks = semesterRepository.findByStudentId(student.getId());
            StudentDTO studentDTO = prepareResponse(student);
            studentDTO.setMarks(marks);
            return studentDTO;
        }
        return null;
    }

    private StudentDTO prepareResponse(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setCode(student.getCode());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setMobile(student.getMobile());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAdmissionYear(student.getAdmissionYear());
        studentDTO.setBranchCode(student.getBranchCode());
        studentDTO.setCourse(student.getCourse());
        studentDTO.setCurrentSemester(student.getCurrentSemester());
        return studentDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> search(Map<String, String> requestParam) {
        return customStudentRepository.search(requestParam);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> performance(Integer semester, String branchCode) {
        List<StudentSemesterDTO> studentSemester = semesterRepository.findBySemesterAndBranchCode(semester, branchCode);
        long count75_100 = 0;
        long count50_75 = 0;
        long count25_50 = 0;
        long count0_25 = 0;
        for (StudentSemesterDTO e : studentSemester) {
            if (75 <= e.getPercentage() && 100 >= e.getPercentage()) {
                count75_100++;
            } else if (50 <= e.getPercentage() && 75 > e.getPercentage()) {
                count50_75++;
            } else if (25 <= e.getPercentage() && 50 > e.getPercentage()) {
                count25_50++;
            } else if (0 <= e.getPercentage() && 25 > e.getPercentage()) {
                count0_25++;
            }
        }
        Map<String, Object> result1 = new LinkedHashMap<>();
        result1.put("range", "75-100");
        result1.put("total", count75_100);
        Map<String, Object> result2 = new LinkedHashMap<>();
        result2.put("range", "50-75");
        result2.put("total", count50_75);
        Map<String, Object> result3 = new LinkedHashMap<>();
        result3.put("range", "25-50");
        result3.put("total", count25_50);
        Map<String, Object> result4 = new LinkedHashMap<>();
        result4.put("range", "0-25");
        result4.put("total", count0_25);
        return Arrays.asList(result1, result2, result3, result4);
    }

    @Override
    public Student createStudent(StudentDTO studentDTO) {
        if (studentRepository.findByCode(studentDTO.getCode()).isPresent()) {
            throw new ServiceException("Student with code " + studentDTO.getCode() + " already exists");
        }
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new ServiceException("Student with email " + studentDTO.getEmail() + " already exists");
        }

        Student student = new Student();
        student.setCode(studentDTO.getCode());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setMobile(studentDTO.getMobile());
        student.setAdmissionYear(studentDTO.getAdmissionYear());
        student.setBranchCode(studentDTO.getBranchCode());
        student.setCourse(studentDTO.getCourse());
        student.setCurrentSemester(studentDTO.getCurrentSemester());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());
        student.setCity(studentDTO.getCity());
        student.setState(studentDTO.getState());
        student.setZipCode(studentDTO.getZipCode());
        student.setIsActive(true);

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found with id " + id));

        // Check if email is being changed and if it already exists
        if (!student.getEmail().equals(studentDTO.getEmail()) &&
                studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new ServiceException("Student with email " + studentDTO.getEmail() + " already exists");
        }

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setMobile(studentDTO.getMobile());
        student.setAdmissionYear(studentDTO.getAdmissionYear());
        student.setBranchCode(studentDTO.getBranchCode());
        student.setCourse(studentDTO.getCourse());
        student.setCurrentSemester(studentDTO.getCurrentSemester());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());
        student.setCity(studentDTO.getCity());
        student.setState(studentDTO.getState());
        student.setZipCode(studentDTO.getZipCode());

        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentByCode(String code) {
        return studentRepository.findByCode(code)
                .orElseThrow(() -> new ServiceException("Student not found with code " + code));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsBySemester(Integer semester) {
        return studentRepository.findByCurrentSemester(semester);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByBranch(String branchCode) {
        return studentRepository.findByBranchCode(branchCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getActiveStudents() {
        return studentRepository.findByIsActiveTrue();
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found with id " + id));
        studentRepository.delete(student);
    }

    @Override
    public void deactivateStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found with id " + id));
        student.setIsActive(!student.getIsActive());
        student.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student);
    }
}
