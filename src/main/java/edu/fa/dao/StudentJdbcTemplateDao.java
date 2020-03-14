package edu.fa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import edu.fa.model.Student;

	@Component
	public class StudentJdbcTemplateDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
//		
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Student getStudentbyname(String name) {
		String query = " select * from student where name = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { name }, new NameMapper());
	}

	private static final class NameMapper implements RowMapper<Student> {

		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
		}

	}

	public Student getStudentByid(int id) {

		String query = "select * from student where id = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { id }, new StudentMapper());

	}

	public void InsertStudent(Student student) {
		String query = "insert into student values (" + student.getId() + ",'" + student.getName() + "','"
				+ student.getLocation() + "')";
		jdbcTemplate.execute(query);
	}

	public void deleteStudent(Student student) {
		String query = "delete from student ";
		jdbcTemplate.execute(query);
	}

	public String CountStudent(Student student) {
		String query = "select name from student ";
		return jdbcTemplate.queryForObject(query, String.class);
	}

	private static final class StudentMapper implements RowMapper<Student> {

		public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("location"));
		}

	}

	public List<Student> getAllstudent() {
		String query = " select * from student ";

		return jdbcTemplate.query(query, new StudentMapper());

	}

}
