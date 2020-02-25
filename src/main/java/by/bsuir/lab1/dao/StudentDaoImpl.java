package by.bsuir.lab1.dao;


import by.bsuir.lab1.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.jws.WebService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentDao {

    private static final String SELECT_STUDENTS =
            "SELECT ID, FIRST_NAME, SECOND_NAME, COURSE, FACULTY, CARD_NUMBER FROM  STUDENT";

    private static final String DELETE_STUDENTS =
            "DELETE FROM STUDENT WHERE CARD_NUMBER=?";

    public static final String UPDATE_STUDENT =
            "UPDATE STUDENT SET FIRST_NAME=?, SECOND_NAME=?, COURSE=?, FACULTY=?" +
                    " WHERE CARD_NUMBER=?";

    private static final StudentMapper MAPPER = new StudentMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> getStudents() {
        return jdbcTemplate.query(SELECT_STUDENTS, MAPPER);
    }

    public int saveStudent(Student student) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("FIRST_NAME", student.getFirstName());
        parameterMap.put("SECOND_NAME", student.getSecondName());
        parameterMap.put("COURSE", student.getCourse());
        parameterMap.put("FACULTY", student.getFaculty());
        parameterMap.put("CARD_NUMBER", student.getCardNumber());
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("STUDENT")
                .usingGeneratedKeyColumns("ID")
                .execute(parameterMap);
    }

    @Override
    public int updateStudent(Student student) {
        return jdbcTemplate.update(UPDATE_STUDENT,
                student.getFirstName(),
                student.getSecondName(),
                student.getCourse(),
                student.getFaculty(),
                student.getCardNumber());
    }

    @Override
    public int deleteStudent(String cardNumber) {
        return jdbcTemplate.update(DELETE_STUDENTS, cardNumber);
    }

    private static class StudentMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student()
                    .setId(rs.getLong("ID"))
                    .setCourse(rs.getInt("COURSE"))
                    .setFaculty(rs.getString("FACULTY"))
                    .setFirstName(rs.getString("FIRST_NAME"))
                    .setSecondName(rs.getString("SECOND_NAME"))
                    .setCardNumber(rs.getString("CARD_NUMBER"));
        }
    }
}

