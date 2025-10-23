package demo.local.studentmgnmtapp.service;

import demo.local.studentmgnmtapp.dto.StudentDto;
import demo.local.studentmgnmtapp.entity.Student;
import demo.local.studentmgnmtapp.exception.ResourceNotFoundException;
import demo.local.studentmgnmtapp.mapper.StudentMapper;
import demo.local.studentmgnmtapp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepo;

    public StudentDto createStudent(Student student){

        if(studentRepo.findByemail(student.getEmail()).isPresent()) throw new RuntimeException("Student with this email already exists.");

        studentRepo.save(student);

        return StudentMapper.mapToStudentDTO(student);

    }

    public StudentDto getStudentById(Long studentId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Id: " + studentId + " does not exist"));

        return StudentMapper.mapToStudentDTO(student);

    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student updateStudent(Long studentId, Student updatedStudent){
        Student student = studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student Id: " + studentId + " does not exist")
        );

        if(studentRepo.findByemail(updatedStudent.getEmail()).isPresent() & !updatedStudent.getEmail().equals(student.getEmail())) throw new RuntimeException("Student with this email already exists.");


        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setGPA(updatedStudent.getGPA());

        studentRepo.save(student);
        return student;

    }

    public void deleteStudent(Long studentId){
        if(studentRepo.findById(studentId).isPresent()){
            studentRepo.deleteById(studentId);
        } else{
            throw new ResourceNotFoundException("Student Id: " + studentId + " does not exist");
        }

    }


}
