package w7a3_School_JDBC.SystemsInterfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import w7a3_School_JDBC.CustomExceptions.StudentRegistrationException;
import w7a3_School_JDBC.Models.Attending;
import w7a3_School_JDBC.Models.Course;
import w7a3_School_JDBC.Models.Student;

public interface AttendingDAOI {
	public int registerStudentToCourse(Student student, Course course) throws StudentRegistrationException, SQLException, ClassNotFoundException, IOException;
	public List<Attending> getStudentCourse(int student_id) throws IOException, SQLException;
}
