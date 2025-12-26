package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.StudentSemester;
import in.system.studentmanagement.dto.StudentSemesterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSemesterRepository extends JpaRepository<StudentSemester, Long> {

    @Query(value = "select e.semester as semester, e.totalMarksObtained as marks, (e.totalMarksObtained * 100)/1000 as percentage from StudentSemester e where e.studentId.id =?1")
    List<StudentSemesterDTO> findByStudentId(Long id);

    @Query(value = "select e.semester as semester, e.totalMarksObtained as marks, (e.totalMarksObtained * 100)/1000 as percentage from StudentSemester e where e.semester =?1 and e.studentId.branchCode =?2")
    List<StudentSemesterDTO> findBySemesterAndBranchCode(Integer semester, String branchCode);
}
