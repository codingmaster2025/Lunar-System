import java.io.Serializable;

/*
 * 
 * this class will be associated with the Students that are taking them
 * 
 */

public class Course implements Serializable{
	private String department;
	private int number;
	private String semester;
	
	
	
	public Course(String department, int number) {
		this.department = department;
		this.number = number;
	}
	

	public Course(String department, int number, String semester) {
		this.department = department;
		this.number = number;
		this.semester = semester;
	}

	public String getDepartment() {
		return department;
	}

	public int getNumber() {
		return number;
	}

	public String getSemester() {
		return semester;
	}
	
	public void setDepartment(String department) {
		this.department=department;
	}
	
	public void setNumber(int number) {
		this.number=number;
	}
	
	public void setSemester(String semester) {
		this.semester=semester;
	}
	
	
	public String toString() {
		return department+"\t"+number+"\t"+semester+"\n";
	}

}
