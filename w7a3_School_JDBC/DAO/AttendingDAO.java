package w7a3_School_JDBC.DAO;

import java.util.List;

import w7a3_School_JDBC.Models.Attending;
import w7a3_School_JDBC.Models.Course;
import w7a3_School_JDBC.Models.Student;
import w7a3_School_JDBC.SystemsInterfaces.AttendingDAOI;

public class AttendingDAO implements AttendingDAOI {

	@Override
	public int registerStudentToCourse(Student student, Course course) {
		// HERE!!!!!!!!!!!!!!!!!!!!!!!!!!
		return 0;
	}

	@Override
	public List<Attending> getStudentCourse(int student_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
