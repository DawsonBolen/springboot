package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcStudentRepository {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT studentId, firstName, lastName, grade FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    public Student getStudentById(long studentId) {
        String sql = "SELECT studentId, firstName, lastName, grade FROM students WHERE studentId = ?";
        return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), studentId);
    }

    public void updateStudentGrade(long studentId, String newGrade) {
        String sql = "UPDATE students SET grade = ? WHERE studentId = ?";
        jdbcTemplate.update(sql, newGrade, studentId);
    }

    public void saveStudent(Student student) {
        String sql = "INSERT INTO students (studentId, firstName, lastName, grade) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGrade());
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET firstName = ?, lastName = ?, grade = ? WHERE studentId = ?";
        jdbcTemplate.update(sql,
                student.getFirstName(),
                student.getLastName(),
                student.getGrade(),
                student.getStudentId());
    }

    public void deleteStudentById(long studentId) {
        String sql = "DELETE FROM students WHERE studentId = ?";
        jdbcTemplate.update(sql, studentId);
    }

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setStudentId(rs.getLong("studentId"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));
            student.setGrade(rs.getString("grade"));
            return student;
        }
    }
}
