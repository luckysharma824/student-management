package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByCode(String code);

    Optional<Teacher> findByEmail(String email);

    List<Teacher> findByDepartment(String department);

    List<Teacher> findByIsActiveTrueOrderByFirstName();

    @Query("SELECT t FROM Teacher t WHERE t.isActive = true AND LOWER(CONCAT(t.firstName, ' ', t.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Teacher> findByNameContaining(String name);
}
