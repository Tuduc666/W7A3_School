package w7a3_School_JDBC.utils;

public class OracleQueries {
	public final static String GETSTUDENTBYEMAIL = "select * from student "
			+ "where email = ?";
	public final static String GETALLINSTRUCTORS = "select * from instructor";
	public final static String GETINSTRUCTORBYEMAIL = "select * from instructor "
			+ "where email = ?";	
	public final static String GETALLCOURSES = "select * from course";
	public final static String GETCOURSESBYINSTRUCTOR = "select c.COURSE_ID,c.COURSE_NAME,c.MINIMUN_GPA from TEACHING t " 
			+ "join COURSE c on t.COURSE_ID = c.COURSE_ID "  
			+ "where t.INSTRUCTOR_ID = ?";
	public final static String INSERTATTENDING = "insert into attending "
			+ "(course_id, student_id) "
			+ "values(?, ?)";	
	public final static String GETSTUDENTCOURSES = "select c.COURSE_NAME,"
			+ "i.FULL_NAME,i.EMAIL from ATTENDING a " 
			+ "join COURSE c   on a.COURSE_ID = c.COURSE_ID "  
			+ "join TEACHING t on a.COURSE_ID = t.COURSE_ID "  
			+ "join INSTRUCTOR i on t.INSTRUCTOR_ID = i.INSTRUCTOR_ID "   
			+ "where a.STUDENT_ID = ?";
	public final static String INSERTTEACHING = "insert into teaching "
			+ "(course_id, instructor_id) "
			+ "values(?, ?)";
	public final static String GETINSTRUCTORSCOURSES = "select c.COURSE_NAME,c.MINIMUN_GPA,"
			+ "i.FULL_NAME,i.EMAIL from TEACHING t " 
			+ "join COURSE c   on t.COURSE_ID = c.COURSE_ID "    
			+ "join INSTRUCTOR i on t.INSTRUCTOR_ID = i.INSTRUCTOR_ID ";  
	
	

//	public final static String UPDATEUSER = "update user_table "
//			+ "set user_name = ?, user_password = ? "
//			+ "where user_email = ?";
	
}
