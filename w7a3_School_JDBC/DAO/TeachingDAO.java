package w7a3_School_JDBC.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import w7a3_School_JDBC.Models.Teaching;
import w7a3_School_JDBC.SystemsInterfaces.TeachingDAOI;
import w7a3_School_JDBC.utils.OracleQueries;

public class TeachingDAO implements TeachingDAOI {

	@Override
	public int assignInstructorToCourse(int course_id, int instructor_id) throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int r = 0;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.INSERTTEACHING);
			stmt.setInt(1, course_id);
			stmt.setInt(2, instructor_id);
			r = stmt.executeUpdate();
			
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
		return r;
	}

	@Override
	public List<Teaching> getIntructorsCourses() throws IOException, SQLException {
		Teaching teaching = null;            // courseName, insName, insEmail
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		List<Teaching> l = new ArrayList<Teaching>();
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(OracleQueries.GETINSTRUCTORSCOURSES);
			while(result.next()) {
				teaching = new Teaching();
				teaching.setCourse_name(result.getString(1));
				teaching.setMinimum_gpa(result.getDouble(2));
				teaching.setFull_name(result.getString(3));
				teaching.setEmail(result.getString(4));
				l.add(teaching);
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
