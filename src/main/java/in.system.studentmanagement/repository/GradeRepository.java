package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByStudentIdAndSemester(Long studentId, Integer semester);

    List<Grade> findByCourseId(Long courseId);

    List<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.semester = :semester")
    List<Grade> getStudentGradesBySemester(Long studentId, Integer semester);

    @Query("SELECT AVG(g.totalMarks) FROM Grade g WHERE g.student.id = :studentId")
    Double getStudentAverageGrade(Long studentId);

    @Query("SELECT g FROM Grade g WHERE g.course.id = :courseId AND g.semester = :semester ORDER BY g.totalMarks DESC")
    List<Grade> getCourseGradesBySemester(Long courseId, Integer semester);

    Optional<Grade> findByStudentIdAndCourseIdAndSemester(Long studentId, Long courseId, Integer semester);
}
