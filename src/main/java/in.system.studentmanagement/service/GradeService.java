package in.system.studentmanagement.service;

import in.system.studentmanagement.dto.GradeDTO;
import java.util.List;

public interface GradeService {
    GradeDTO createGrade(GradeDTO gradeDTO);

    GradeDTO updateGrade(Long id, GradeDTO gradeDTO);

    GradeDTO getGradeById(Long id);

    List<GradeDTO> getGradesByStudentId(Long studentId);

    List<GradeDTO> getGradesByStudentAndSemester(Long studentId, Integer semester);

    List<GradeDTO> getGradesByCourseId(Long courseId);

    List<GradeDTO> getGradesByCourseAndSemester(Long courseId, Integer semester);

    Double getStudentAverageGrade(Long studentId);

    Double getStudentSemesterGPA(Long studentId, Integer semester);

    void deleteGrade(Long id);

    GradeDTO calculateAndAssignGrade(Long studentId, Long courseId, Integer semester);
}
