package w7a3_School_JDBC.utils;

public class OracleQueries {
	public final static String GETSTUDENTBYEMAIL = "select * from student "
			+ "where email = ?";
	public final static String GETALLINSTRUCTORS = "select * from instructor";
	public final static String GETINSTRUCTORBYEMAIL = "select * from instructor "
			+ "where email = ?";	
	public final static String GETALLCOURSES = "select * from course";
	public final static String GETCOURSESBYINSTRUCTOR = "select c.COURSE_ID,c.COURSE_NAME,c.MINIMUN_GPA from TEACHING " 
			+ "join COURSE c on t.COURSE_ID = c.COURSE_ID "  
			+ "where INSTRUCTOR_ID = ?";
	
	
	
	public final static String SAVEUSER = "insert into user_table "
			+ "(user_email, user_name, user_password) "
			+ "values(?, ?, ?)";
	public final static String UPDATEUSER = "update user_table "
			+ "set user_name = ?, user_password = ? "
			+ "where user_email = ?";
	
}
