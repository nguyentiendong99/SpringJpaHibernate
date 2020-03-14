package edu.fa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import edu.fa.model.Student;

	@Component
	public class StudentJdbcDaoSupport extends JdbcDaoSupport {
		public Student getStudentByid(int id) {

		String query = "select * from student where id = ?";
		return this.getJdbcTemplate().queryForObject(query, new Object[] { id }, new StudentMapper());

	}

	public void InsertStudent(Student student) {
		String query = "insert into student values (" + student.getId() + ",'" + student.getName() + "','"
				+ student.getLocation() + "')";
		this.getJdbcTemplate().execute(query);
	}

	public void deleteStudent(Student student) {
		String query = "delete from student ";
		this.getJdbcTemplate().execute(query);
	}

	private static final class StudentMapper implements RowMapper<Student> {

		public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("location"));
		}

	}
}
