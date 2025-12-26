package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);

    List<Course> findByDepartment(String department);

    List<Course> findByIsActiveTrueOrderByName();

    @Query("SELECT c FROM Course c WHERE c.isActive = true AND LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Course> findByNameContaining(String name);

    @Query("SELECT c FROM Course c WHERE c.isActive = true AND c.department = :department")
    List<Course> findActiveCoursesByDepartment(String department);
}
