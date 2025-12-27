package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Teacher;
import in.system.studentmanagement.dto.TeacherDTO;
import in.system.studentmanagement.exception.ServiceException;
import in.system.studentmanagement.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        if (teacherRepository.findByCode(teacherDTO.getCode()).isPresent()) {
            throw new ServiceException("Teacher with code " + teacherDTO.getCode() + " already exists");
        }
        if (teacherRepository.findByEmail(teacherDTO.getEmail()).isPresent()) {
            throw new ServiceException("Teacher with email " + teacherDTO.getEmail() + " already exists");
        }

        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return modelMapper.map(savedTeacher, TeacherDTO.class);
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found with id " + id));

        // Check if email is being changed and if it already exists
        if (!teacher.getEmail().equals(teacherDTO.getEmail()) &&
                teacherRepository.findByEmail(teacherDTO.getEmail()).isPresent()) {
            throw new ServiceException("Teacher with email " + teacherDTO.getEmail() + " already exists");
        }

        modelMapper.map(teacherDTO, teacher);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return modelMapper.map(updatedTeacher, TeacherDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found with id " + id));
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDTO> getActiveTeachers() {
        return teacherRepository.findByIsActiveTrueOrderByFirstName()
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDTO> getTeachersByDepartment(String department) {
        return teacherRepository.findByDepartment(department)
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDTO> searchTeacherByName(String name) {
        return teacherRepository.findByNameContaining(name)
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found with id " + id));
        teacherRepository.delete(teacher);
    }

    @Override
    public void deactivateTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found with id " + id));
        teacher.setIsActive(!teacher.getIsActive());
        teacherRepository.save(teacher);
    }
}
