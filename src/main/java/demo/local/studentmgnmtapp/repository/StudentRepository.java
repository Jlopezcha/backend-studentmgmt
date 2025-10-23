package demo.local.studentmgnmtapp.repository;

import demo.local.studentmgnmtapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByemail(String email);

}
