import java.util.ArrayList;
import java.util.List;
import java.io.*;
/*
 * 
 * 
 * this class will serve as the stored element of the Lunar System database
 * 
 */
public class Student implements Serializable{

	private String webID;
	private List<Course> courses=new ArrayList<Course>();
	
	
	
	
	public Student(String webID,List<Course> courses) {
		this.webID=webID;
		this.courses=courses;
	}
	
	
	
	public String getWebID() {
		return webID;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public void setWebID(String webID) {
		this.webID=webID;
	}
	public void setCourses(List<Course> courses) {
		this.courses=courses;
	}
	
	public String toString() {
		String result="";
		for (int i = 0; i < courses.size(); i++)
	           
            result+=courses.get(i) + " ";   
		
		return result;
	}
	
	
	
	
	
}
