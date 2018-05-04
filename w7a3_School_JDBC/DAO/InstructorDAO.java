package w7a3_School_JDBC.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import w7a3_School_JDBC.Models.Instructor;
import w7a3_School_JDBC.SystemsInterfaces.InstructorDAOI;
import w7a3_School_JDBC.utils.OracleQueries;

public class InstructorDAO implements InstructorDAOI {

	@Override
	public List<Instructor> getAllInstructors() throws IOException, SQLException {
		List<Instructor> l = new ArrayList<Instructor>();
		Instructor instructor = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(OracleQueries.GETALLINSTRUCTORS);
			while(result.next()) {
				instructor = new Instructor();
				instructor.setIntructor_id(result.getInt(1));
				instructor.setFull_name(result.getString(2));
				instructor.setEmail(result.getString(3));
				instructor.setSpeciality(result.getString(4));
				instructor.setInstructor_role(result.getInt(5));
				instructor.setPass(result.getString(6));
				l.add(instructor);
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
	public Instructor getInstructorByEmail(String email) throws SQLException, IOException {
		Instructor instructor = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETINSTRUCTORBYEMAIL);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			if(result.next()) {
				instructor = new Instructor();
				instructor.setIntructor_id(result.getInt(1));
				instructor.setFull_name(result.getString(2));
				instructor.setEmail(result.getString(3));
				instructor.setSpeciality(result.getString(4));
				instructor.setInstructor_role(result.getInt(5));
				instructor.setPass(result.getString(6));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			result.close();
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}	
		return instructor;
	}
}
