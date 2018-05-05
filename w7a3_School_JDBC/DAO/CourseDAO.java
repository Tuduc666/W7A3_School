package w7a3_School_JDBC.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import w7a3_School_JDBC.Models.Course;
import w7a3_School_JDBC.SystemsInterfaces.CourseDAOI;
import w7a3_School_JDBC.utils.OracleQueries;

public class CourseDAO implements CourseDAOI {

	@Override
	public List<Course> getAllCourses() throws IOException, SQLException {
		List<Course> l = new ArrayList<Course>();
		Course course = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(OracleQueries.GETALLCOURSES);
			while(result.next()) {
				course = new Course();
				course.setCourse_id(result.getInt(1));
				course.setCourse_name(result.getString(2));
				course.setMinimum_gpa(result.getDouble(3));
				l.add(course);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return l;
	}

	@Override
	public List<Course> getCourseByInstructor(int instructor_id) throws IOException, SQLException {
		List<Course> l = new ArrayList<Course>();
		Course course = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETCOURSESBYINSTRUCTOR);
			stmt.setInt(1, instructor_id);
			result = stmt.executeQuery();
			while(result.next()) {
				course = new Course();
				course.setCourse_id(result.getInt(1));
				course.setCourse_name(result.getString(2));
				course.setMinimum_gpa(result.getDouble(3));
				l.add(course);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(result != null) {
				result.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}	
		return l;
	}
}
