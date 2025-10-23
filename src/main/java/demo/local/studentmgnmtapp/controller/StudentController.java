package demo.local.studentmgnmtapp.controller;


import demo.local.studentmgnmtapp.dto.StudentDto;
import demo.local.studentmgnmtapp.entity.Student;
import demo.local.studentmgnmtapp.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;


    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody Student student){
        StudentDto newStudent = studentService.createStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId){
        StudentDto studentDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId,
                                                 @RequestBody Student changedStudent){
        Student newUpdatedStudent = studentService.updateStudent(studentId, changedStudent);
        return ResponseEntity.ok(newUpdatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student Id: " + studentId + " deleted successfully");
    }

}
