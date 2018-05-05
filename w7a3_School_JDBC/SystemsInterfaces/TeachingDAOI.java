package w7a3_School_JDBC.SystemsInterfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import w7a3_School_JDBC.Models.Teaching;

public interface TeachingDAOI {
	public int assignInstructorToCourse(int course_id, int instructor_id) throws IOException, SQLException;
	public List<Teaching> getIntructorsCourses() throws IOException, SQLException;
}