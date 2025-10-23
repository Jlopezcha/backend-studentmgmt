package demo.local.studentmgnmtapp.mapper;

import demo.local.studentmgnmtapp.dto.StudentDto;
import demo.local.studentmgnmtapp.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDTO(Student student){
        return new StudentDto(
                student.getId(),
                student.getEmail()
        );
    }


}
