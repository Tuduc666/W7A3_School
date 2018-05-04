package w7a3_School_JDBC.SystemsInterfaces;

import java.util.List;

import w7a3_School_JDBC.Models.Attending;
import w7a3_School_JDBC.Models.Course;
import w7a3_School_JDBC.Models.Student;

public interface AttendingDAOI {
	public int registerStudentToCourse(Student student, Course course);
	public List<Attending> getStudentCourse(int student_id);
}
