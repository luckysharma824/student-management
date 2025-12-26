package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByCode(String code);

    Optional<Student> findByEmail(String email);

    List<Student> findByCurrentSemester(Integer semester);

    List<Student> findByBranchCode(String branchCode);

    List<Student> findByIsActiveTrue();
}
