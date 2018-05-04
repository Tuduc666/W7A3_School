package w7a3_School_JDBC.Models;

public class Instructor {
	private int intructor_id;
	private String full_name;
	private String email;
	private String speciality;
	private int instructor_role;
	private String pass;
	
	public Instructor() {
		this.intructor_id = 0;
		this.full_name = null;
		this.email = null;
		this.speciality = null;
		this.instructor_role = -9;
		this.pass = null;
	}
	
	public Instructor(int intructor_id, String full_name, String email, String speciality, int instructor_role,
			String pass) {
		this.intructor_id = intructor_id;
		this.full_name = full_name;
		this.email = email;
		this.speciality = speciality;
		this.instructor_role = instructor_role;
		this.pass = pass;
	}

	public int getIntructor_id() {
		return intructor_id;
	}

	public void setIntructor_id(int intructor_id) {
		this.intructor_id = intructor_id;
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getInstructor_role() {
		return instructor_role;
	}

	public void setInstructor_role(int instructor_role) {
		this.instructor_role = instructor_role;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}	
}
