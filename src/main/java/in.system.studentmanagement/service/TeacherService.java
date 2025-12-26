package in.system.studentmanagement.service;

import in.system.studentmanagement.dto.TeacherDTO;
import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);

    TeacherDTO getTeacherById(Long id);

    List<TeacherDTO> getAllTeachers();

    List<TeacherDTO> getActiveTeachers();

    List<TeacherDTO> getTeachersByDepartment(String department);

    List<TeacherDTO> searchTeacherByName(String name);

    void deleteTeacher(Long id);

    void deactivateTeacher(Long id);
}
