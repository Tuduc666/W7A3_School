package w7a3_School_JDBC.SystemsInterfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import w7a3_School_JDBC.Models.Instructor;

public interface InstructorDAOI {
	public List<Instructor> getAllInstructors() throws IOException, SQLException;
	public Instructor getInstructorByEmail(String email) throws SQLException, IOException;
}
