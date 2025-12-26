package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentDTO getStudentDetail(Long studentId);

    List<Student> search(Map<String, String> requestParam);

    List<Map<String, Object>> performance(Integer semester, String branchCode);

    Student createStudent(StudentDTO studentDTO);

    Student updateStudent(Long id, StudentDTO studentDTO);

    List<Student> getAllStudents();

    Student getStudentByCode(String code);

    List<Student> getStudentsBySemester(Integer semester);

    List<Student> getStudentsByBranch(String branchCode);

    List<Student> getActiveStudents();

    void deleteStudent(Long id);

    void deactivateStudent(Long id);
}
