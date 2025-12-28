package in.system.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private Long id;

    private Long studentId;
    @NotNull(message = "Student ID is required")
    private String studentCode;
    private String studentName;

    private Long courseId;
    @NotNull(message = "Course ID is required")
    private String courseCode;
    private String courseName;

    private Long teacherId;
    private String teacherCode;
    private String teacherName;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester cannot exceed 8")
    private Integer semester;

    @NotNull(message = "Internal marks is required")
    @Min(value = 0, message = "Internal marks cannot be negative")
    @Max(value = 40, message = "Internal marks cannot exceed 40")
    private Double internalMarks;

    @NotNull(message = "External marks is required")
    @Min(value = 0, message = "External marks cannot be negative")
    @Max(value = 60, message = "External marks cannot exceed 60")
    private Double externalMarks;

    private Double totalMarks;
    private String gradePoint;
    private String remarks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
