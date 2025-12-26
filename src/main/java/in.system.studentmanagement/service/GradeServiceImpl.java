package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Course;
import in.system.studentmanagement.domain.Grade;
import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.domain.Teacher;
import in.system.studentmanagement.dto.GradeDTO;
import in.system.studentmanagement.exception.ServiceException;
import in.system.studentmanagement.repository.CourseRepository;
import in.system.studentmanagement.repository.GradeRepository;
import in.system.studentmanagement.repository.StudentRepository;
import in.system.studentmanagement.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Student student = studentRepository.findById(gradeDTO.getStudentId())
                .orElseThrow(() -> new ServiceException("Student not found"));

        Course course = courseRepository.findById(gradeDTO.getCourseId())
                .orElseThrow(() -> new ServiceException("Course not found"));

        // Check if grade already exists for this combination
        if (gradeRepository.findByStudentIdAndCourseIdAndSemester(
                gradeDTO.getStudentId(), gradeDTO.getCourseId(), gradeDTO.getSemester()).isPresent()) {
            throw new ServiceException("Grade already exists for this student and course in this semester");
        }

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setSemester(gradeDTO.getSemester());
        grade.setInternalMarks(gradeDTO.getInternalMarks());
        grade.setExternalMarks(gradeDTO.getExternalMarks());
        grade.setRemarks(gradeDTO.getRemarks());

        if (gradeDTO.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(gradeDTO.getTeacherId())
                    .orElseThrow(() -> new ServiceException("Teacher not found"));
            grade.setAssignedBy(teacher);
        }

        // Calculate total and grade point
        Double totalMarks = gradeDTO.getInternalMarks() + gradeDTO.getExternalMarks();
        grade.setTotalMarks(totalMarks);
        grade.setGradePoint(calculateGradePoint(totalMarks));

        Grade savedGrade = gradeRepository.save(grade);
        return mapToGradeDTO(savedGrade);
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Grade not found"));

        grade.setInternalMarks(gradeDTO.getInternalMarks());
        grade.setExternalMarks(gradeDTO.getExternalMarks());
        grade.setRemarks(gradeDTO.getRemarks());

        // Recalculate total and grade point
        Double totalMarks = gradeDTO.getInternalMarks() + gradeDTO.getExternalMarks();
        grade.setTotalMarks(totalMarks);
        grade.setGradePoint(calculateGradePoint(totalMarks));

        Grade updatedGrade = gradeRepository.save(grade);
        return mapToGradeDTO(updatedGrade);
    }

    @Override
    @Transactional(readOnly = true)
    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Grade not found"));
        return mapToGradeDTO(grade);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDTO> getGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToGradeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDTO> getGradesByStudentAndSemester(Long studentId, Integer semester) {
        return gradeRepository.findByStudentIdAndSemester(studentId, semester)
                .stream()
                .map(this::mapToGradeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDTO> getGradesByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToGradeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeDTO> getGradesByCourseAndSemester(Long courseId, Integer semester) {
        return gradeRepository.getCourseGradesBySemester(courseId, semester)
                .stream()
                .map(this::mapToGradeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Double getStudentAverageGrade(Long studentId) {
        Double average = gradeRepository.getStudentAverageGrade(studentId);
        return average != null ? average : 0.0;
    }

    @Override
    @Transactional(readOnly = true)
    public Double getStudentSemesterGPA(Long studentId, Integer semester) {
        List<Grade> grades = gradeRepository.findByStudentIdAndSemester(studentId, semester);
        if (grades.isEmpty()) {
            return 0.0;
        }
        Double totalMarks = grades.stream().mapToDouble(Grade::getTotalMarks).sum();
        return totalMarks / grades.size();
    }

    @Override
    public void deleteGrade(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Grade not found"));
        gradeRepository.delete(grade);
    }

    @Override
    public GradeDTO calculateAndAssignGrade(Long studentId, Long courseId, Integer semester) {
        Grade grade = gradeRepository.findByStudentIdAndCourseIdAndSemester(studentId, courseId, semester)
                .orElseThrow(() -> new ServiceException("Grade record not found"));

        // Grade calculation logic is already done in createGrade and updateGrade
        return mapToGradeDTO(grade);
    }

    private String calculateGradePoint(Double marks) {
        if (marks >= 90)
            return "A";
        if (marks >= 80)
            return "B";
        if (marks >= 70)
            return "C";
        if (marks >= 60)
            return "D";
        if (marks >= 50)
            return "E";
        return "F";
    }

    private GradeDTO mapToGradeDTO(Grade grade) {
        GradeDTO dto = modelMapper.map(grade, GradeDTO.class);
        dto.setStudentName(grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName());
        dto.setCourseName(grade.getCourse().getName());
        if (grade.getAssignedBy() != null) {
            dto.setTeacherId(grade.getAssignedBy().getId());
            dto.setTeacherName(grade.getAssignedBy().getFirstName() + " " + grade.getAssignedBy().getLastName());
        }
        return dto;
    }
}
