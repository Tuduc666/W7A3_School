package w7a3_School_JDBC.SystemsInterfaces;

import java.io.IOException;
import java.sql.SQLException;

import w7a3_School_JDBC.Models.Student;

public interface StudentDAOI {
	public Student getStudentByEmail(String email) throws SQLException, IOException;
	
}
