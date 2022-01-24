import java.util.Comparator;

/*
 * @Author Jinwei Zhou 
 * 114177591 CSE214 
 * recitation04
 * 
 */

public class SemesterComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Course e1 = (Course) o1;
        Course e2 = (Course) o2;
        String s1=e1.getSemester();   
        Character term1 = s1.charAt(0);
		int num1 = Integer.parseInt(s1.substring(1));
		
		
		 String s2=e2.getSemester();   
	     Character term2 = s2.charAt(0);
		 int num2 = Integer.parseInt(s2.substring(1));
		 
		 if(num1>num2) {
			 return 1;
		 }else if(num2>num1) {
			 return -1;
		 }else {
			 if(term1.equals('F')||(term1.equals('S')&&term2.equals('S')))
				 return 1;
			 else return -1;
		 }
		
}

}


