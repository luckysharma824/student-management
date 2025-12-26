package in.system.studentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {
     private Long id;

     @NotBlank(message = "Student code is required")
     private String code;

     @NotBlank(message = "First name is required")
     @Size(max = 50)
     private String firstName;

     @NotBlank(message = "Last name is required")
     @Size(max = 50)
     private String lastName;

     @Email(message = "Invalid email format")
     private String email;

     @Pattern(regexp = "^[0-9]{10}$|^[0-9]{12}$", message = "Mobile number must be 10 or 12 digits")
     private String mobile;

     @NotNull(message = "Admission year is required")
     private Integer admissionYear;

     @NotBlank(message = "Branch code is required")
     private String branchCode;

     @NotBlank(message = "Course is required")
     private String course;

     @NotNull(message = "Current semester is required")
     @Min(value = 1, message = "Semester must be at least 1")
     @Max(value = 8, message = "Semester cannot exceed 8")
     private Integer currentSemester;

     private LocalDate dateOfBirth;
     private String address;
     private String city;
     private String state;
     private String zipCode;
     private Boolean isActive;
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;

     private List<StudentSemesterDTO> marks;
}
