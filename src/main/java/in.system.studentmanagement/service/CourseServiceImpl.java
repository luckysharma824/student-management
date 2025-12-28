package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Course;
import in.system.studentmanagement.domain.Teacher;
import in.system.studentmanagement.dto.CourseDTO;
import in.system.studentmanagement.exception.ServiceException;
import in.system.studentmanagement.repository.CourseRepository;
import in.system.studentmanagement.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseRepository.findByCode(courseDTO.getCode()).isPresent()) {
            throw new ServiceException("Course with code " + courseDTO.getCode() + " already exists");
        }

        Course course = modelMapper.map(courseDTO, Course.class);

        if (courseDTO.getCoordinatorTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(courseDTO.getCoordinatorTeacherId())
                    .orElseThrow(() -> new ServiceException("Teacher not found"));
            course.setCoordinatorTeacher(teacher);
        }

        Course savedCourse = courseRepository.save(course);
        return mapToCourseDTO(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Course not found with id " + id));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setDepartment(courseDTO.getDepartment());
        course.setCredits(courseDTO.getCredits());
        course.setTotalSemesters(courseDTO.getTotalSemesters());
        course.setIsActive(courseDTO.getIsActive());

        if (courseDTO.getCoordinatorTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(courseDTO.getCoordinatorTeacherId())
                    .orElseThrow(() -> new ServiceException("Teacher not found"));
            course.setCoordinatorTeacher(teacher);
        }

        Course updatedCourse = courseRepository.save(course);
        return mapToCourseDTO(updatedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Course not found with id " + id));
        return mapToCourseDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseByCode(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new ServiceException("Course not found with code: " + code));
        return mapToCourseDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getActiveCourses() {
        return courseRepository.findByIsActiveTrueOrderByName()
                .stream()
                .map(this::mapToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByDepartment(String department) {
        return courseRepository.findActiveCoursesByDepartment(department)
                .stream()
                .map(this::mapToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> searchCourseByName(String name) {
        return courseRepository.findByNameContaining(name)
                .stream()
                .map(this::mapToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Course not found with id " + id));
        courseRepository.delete(course);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Course not found with id " + id));
        course.setIsActive(course.getIsActive() != null && !course.getIsActive());
        courseRepository.save(course);
    }

    private CourseDTO mapToCourseDTO(Course course) {
        CourseDTO dto = modelMapper.map(course, CourseDTO.class);
        if (course != null && course.getCoordinatorTeacher() != null) {
            Teacher teacher = course.getCoordinatorTeacher();
            dto.setCoordinatorTeacherId(teacher.getId());
            if (teacher.getFirstName() != null && teacher.getLastName() != null) {
                dto.setCoordinatorTeacherName(teacher.getFirstName() + " " + teacher.getLastName());
            }
        }
        return dto;
    }
}
