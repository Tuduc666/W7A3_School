package w7a3_School_JDBC.Models;

public class Teaching {
	private int teaching_id;
	private int course_id;
	private int instructor_id;
	
	public Teaching() {
		this.teaching_id = 0;
		this.course_id = 0;
		this.instructor_id = 0;
	}
	
	public Teaching(int teaching_id, int course_id, int instructor_id) {
		this.teaching_id = teaching_id;
		this.course_id = course_id;
		this.instructor_id = instructor_id;
	}

	public int getTeaching_id() {
		return teaching_id;
	}

	public void setTeaching_id(int teaching_id) {
		this.teaching_id = teaching_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
	}
}
