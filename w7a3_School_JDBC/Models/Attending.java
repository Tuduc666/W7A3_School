package w7a3_School_JDBC.Models;

public class Attending {
	private int attending_id;
	private int course_id;
	private int student_id;
	
	public Attending() {
		this.attending_id = 0;
		this.course_id = 0;
		this.student_id = 0;
	}
	
	public Attending(int attending_id, int course_id, int student_id) {
		this.attending_id = attending_id;
		this.course_id = course_id;
		this.student_id = student_id;
	}

	public int getAttending_id() {
		return attending_id;
	}

	public void setAttending_id(int attending_id) {
		this.attending_id = attending_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
}
