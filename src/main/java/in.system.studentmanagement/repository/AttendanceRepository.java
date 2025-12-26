package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByStudentIdAndSemester(Long studentId, Integer semester);

    List<Attendance> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Attendance> findByCourseId(Long courseId);

    List<Attendance> findBySemester(Integer semester);

    @Query("SELECT a FROM Attendance a WHERE a.student.id = :studentId AND a.semester = :semester AND a.status = 'PRESENT'")
    List<Attendance> findPresentClassesByStudentAndSemester(Long studentId, Integer semester);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.student.id = :studentId AND a.semester = :semester AND a.status = 'PRESENT'")
    Long countPresentClassesByStudentAndSemester(Long studentId, Integer semester);

    @Query("SELECT a FROM Attendance a WHERE a.attendanceDate BETWEEN :startDate AND :endDate AND a.semester = :semester")
    List<Attendance> findAttendanceByDateRangeAndSemester(LocalDate startDate, LocalDate endDate, Integer semester);
}
