package w7a3_School_JDBC.Models;

public class Teaching {
	private String course_name;
	private double minimum_gpa;     
	private String full_name;
	private String email;
	
	public Teaching() {
		this.course_name = " ";
		this.minimum_gpa = 0D;
		this.full_name = " ";
		this.email = " ";
	}
	
	public Teaching(String course_name, double minimum_gpa, String full_name, String email) {
		this.course_name = course_name;
		this.minimum_gpa = minimum_gpa;
		this.full_name = full_name;
		this.email = email;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public double getMinimum_gpa() {
		return minimum_gpa;
	}

	public void setMinimum_gpa(double minimum_gpa) {
		this.minimum_gpa = minimum_gpa;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
