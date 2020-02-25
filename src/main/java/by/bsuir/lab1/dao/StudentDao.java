package by.bsuir.lab1.dao;

import by.bsuir.lab1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    List<Student> getStudents();

    int saveStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(String cardNumber);

}
