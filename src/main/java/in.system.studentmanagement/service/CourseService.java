package in.system.studentmanagement.service;

import in.system.studentmanagement.dto.CourseDTO;
import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    CourseDTO getCourseById(Long id);

    CourseDTO getCourseByCode(String code);

    List<CourseDTO> getAllCourses();

    List<CourseDTO> getActiveCourses();

    List<CourseDTO> getCoursesByDepartment(String department);

    List<CourseDTO> searchCourseByName(String name);

    void deleteCourse(Long id);

    void deactivateCourse(Long id);
}
