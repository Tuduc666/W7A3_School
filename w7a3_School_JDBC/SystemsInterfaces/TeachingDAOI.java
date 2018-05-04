package w7a3_School_JDBC.SystemsInterfaces;

import java.util.List;
import w7a3_School_JDBC.Models.Teaching;

public interface TeachingDAOI {
	public int registerInstructorToCourse(int course_id, int instructor_id);
	public List<Teaching> getIntructorsCourses();
}