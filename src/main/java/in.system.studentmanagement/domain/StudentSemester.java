package in.system.studentmanagement.domain;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class StudentSemester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;
    @Column(length = 1)
    private Integer semester;
    @Column(length = 4)
    private Integer totalMarksObtained;

}
