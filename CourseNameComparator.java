import java.util.Comparator;

/*
 * @Author Jinwei Zhou 
 * 114177591 CSE214 
 * recitation04
 * 
 */

public class CourseNameComparator implements Comparator {
        public int compare(Object o1, Object o2) {
                Course e1 = (Course) o1;
                Course e2 = (Course) o2;
                return (e1.getDepartment().compareTo(e2.getDepartment()));
        }
        
    
        
}
