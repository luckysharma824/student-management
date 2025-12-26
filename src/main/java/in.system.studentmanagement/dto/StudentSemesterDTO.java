package in.system.studentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("decoratedClass")
public interface StudentSemesterDTO {
    Integer getSemester();
    Integer getMarks();
    Float getPercentage();
}