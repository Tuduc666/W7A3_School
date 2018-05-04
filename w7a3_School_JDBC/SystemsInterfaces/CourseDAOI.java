package w7a3_School_JDBC.SystemsInterfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import w7a3_School_JDBC.Models.Course;

public interface CourseDAOI {
	public List<Course> getAllCourses() throws IOException, SQLException;
	public List<Course> getCourseByInstructor(int instructor_id) throws IOException, SQLException;
}