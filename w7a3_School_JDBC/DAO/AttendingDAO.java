package w7a3_School_JDBC.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import w7a3_School_JDBC.CustomExceptions.StudentRegistrationException;
import w7a3_School_JDBC.Models.Attending;
import w7a3_School_JDBC.Models.Course;
import w7a3_School_JDBC.Models.Student;
import w7a3_School_JDBC.SystemsInterfaces.AttendingDAOI;
import w7a3_School_JDBC.utils.OracleQueries;

public class AttendingDAO implements AttendingDAOI {

	@Override
	public int registerStudentToCourse(Student student, Course course) throws StudentRegistrationException, SQLException, ClassNotFoundException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String[] COL = {"attending_id"};   // use to get automatic sequence number for field "attending_id"   
		ResultSet result = null;           // this is needed to get the value above for the automatic sequence number
		Integer id = null;                 // use to store the automatic sequence number
		
		try {
		    if (student.getGpa() < course.getMinimum_gpa()) {
		       throw new StudentRegistrationException("g gpa");
		    } 
		    // allow student to register, create Attending record (attendingId(auto), courseId, studentId)
		    conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.INSERTATTENDING, COL);  // use COL to get value of generated key
			stmt.setInt(1, course.getCourse_id());
			stmt.setInt(2, student.getStudent_id());
			stmt.executeUpdate();
			// get the value of generated key
			result = stmt.getGeneratedKeys();
			if(result.next()) {
				id = result.getInt(1);
			}
			
		} catch (StudentRegistrationException g) {
		    throw new StudentRegistrationException("\nDid not meet the minimum GPA requirement\nRegistration Denied");
		}
		return id;
	}

	@Override
	public List<Attending> getStudentCourse(int student_id) throws IOException, SQLException {
		Attending attending = null;            // courseName, insName, insEmail
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Attending> l = new ArrayList<Attending>();
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETSTUDENTCOURSES);
			stmt.setInt(1, student_id);
			result = stmt.executeQuery();
			while(result.next()) {
				attending = new Attending();
				attending.setCourse_name(result.getString(1));
				attending.setFull_name(result.getString(2));
				attending.setEmail(result.getString(3));
				l.add(attending);
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
