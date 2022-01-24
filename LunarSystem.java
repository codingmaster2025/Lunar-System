import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.*;
import java.io.*;

/*
 * @Author Jinwei Zhou 
 * 114177591 CSE214 
 * recitation04
 * 
 * LunarSystem will allow the user to interact with a database of Students. 
 * Provide the user with a menu-based interface that allows them to add, remove, and edit Students, as well as their courses. 
 * You will need to be able to serialize (save) the database at the end of the program and deserialize (load) the database if a file containing the database already exists. 
 * The database will have a Student’s netID as the key and the associated Student object as the value.
 * 
 */

public class LunarSystem {

	public static void main(String[] args) throws IOException, IllegalArgumentException {
		HashMap<String, Student> database = new HashMap();

		boolean loop1 = true;

		System.out.println(
				"Welcome to the Lunar System, a second place course registration system for second rate courses at a second class school.");
		System.out.println("No previous data found.");

		boolean load = false;

		do {
			// De-serializing
			if (load) {

				try {
					FileInputStream fis = new FileInputStream("database.ser");

					ObjectInputStream ois = new ObjectInputStream(fis);

					database = (HashMap) ois.readObject();

					ois.close();
					fis.close();
					System.out.println(
							"Welcome to the Lunar System, a second place course registration system for second rate courses at a second class school.");

					System.out.println("Previous data loaded.");
					load = false;

				} catch (IOException o1) {
					o1.printStackTrace();
					return;
				} catch (ClassNotFoundException o2) {
					System.out.println("No previous data found.");
					o2.printStackTrace();
					return;
				}

			}

			System.out.println("Menu:");
			System.out.println("\tL)Login");
			System.out.println("\tX)Save state and quit");
			System.out.println("\tQ)Quit without saving state.");

			Scanner in = new Scanner(System.in);

			System.out.println("Please select an option:");

			String s1 = in.next();

			boolean loop2 = true;
			boolean loop3 = true;

			switch (s1.toUpperCase()) {
			case "L":

				System.out.println("Please enter webid:");
				String webid = in.next();
				String webid_copy = webid;
				boolean found = false;

				for (Map.Entry<String, Student> set : database.entrySet()) {

					if (set.getKey().toUpperCase().equals(webid_copy.toUpperCase())) {
						found = true;
						webid = set.getKey();
						break;
					}

				}

				if (webid.toLowerCase().equals("registrar")) {

					do {
						System.out.println("Options:");
						System.out.println("\tR) Register a student");
						System.out.println("\tD) De-register a student");
						System.out.println("\tE) View course enrollment");
						System.out.println("\tL) Logout");

						System.out.println("Please select an option: ");
						String s2 = in.next();
						s2 = s2.toUpperCase();

						if (s2.equals("R")) { // Register a student
							System.out.println("Please enter a webid for the new student:");
							String ID = in.next();
							boolean reg = false;

							for (Map.Entry<String, Student> set : database.entrySet()) {

								if (set.getKey().toUpperCase().equals(ID.toUpperCase())) {

									System.out.println(ID + " is already registered.");
									reg = true;
									break;
								}
							}
							if (!reg) {

								List<Course> courses = new ArrayList<Course>();
								Student stu = new Student(ID, courses);
								database.put(ID, stu);

								System.out.println(ID + " registered.");
							}

						} else if (s2.equals("D")) { // De-register a student
							System.out.print("Please enter a webid for the student to be deregistered:");
							String drop = in.next();

							for (Map.Entry<String, Student> set : database.entrySet()) {

								if (set.getKey().toUpperCase().equals(drop.toUpperCase())) {
									database.remove(set.getKey());
									System.out.println(set.getKey() + " deregistered.");

									break;
								}

							}

						} else if (s2.equals("E")) {// View course enrollment
							System.out.println("Please enter course:");
							in.nextLine();
							String title = in.nextLine();

							String s[] = title.split(" ");
							String dpt = s[0].toUpperCase(); // department
							int num = Integer.parseInt(s[1]);

							System.out.println("Students Registered in " + title + ":");

							System.out.println("Student\tSemester");
							System.out.println("---------------------------\n");

							List courses = new ArrayList();

							for (Entry<String, Student> set : database.entrySet()) {
								courses = set.getValue().getCourses();

								for (int i = 0; i < courses.size(); i++) {
									Course c = (Course) courses.get(i);
									if (c.getDepartment().equals(dpt) && c.getNumber() == num) {
										System.out.println(set.getKey() + "\t" + c.getSemester());
									}

								}
							}

							System.out.println("");

						} else if (s2.equals("L")) { // log out Registrar
							System.out.println("Registrar logged out.");

							loop2 = false;
						} else {

						}

					} while (loop2);
				} else if (found) {
					System.out.println();

					System.out.println("Welcome " + webid);

					do {
						System.out.println("Options:");
						System.out.println("\tA)Add a class");

						System.out.println("\tD)Drop a class");
						System.out.println("\tC)View your classes sorted by course name/department");

						System.out.println("\tS)View your courses sorted by semester");

						System.out.println("Please select an option:");
						String s3 = in.next();

						switch (s3.toUpperCase()) {

						case "A": // Add a class

							List courses = new ArrayList();

							boolean line = true;

							boolean repeat = true;
							String dpt = "";
							int num = 0;

							while (repeat) {

								try {

									System.out.println("Please enter course name:");
									if (line) {
										in.nextLine();
										line = false;
									}
									String str1 = in.nextLine();

									String s[] = str1.split(" ");

									dpt = s[0].toUpperCase(); // department
									num = Integer.parseInt(s[1]);
									repeat = false;

								} catch (Exception e1) {
									System.out.println("invalid input");
								}
							}

							boolean invaildInput = true;

							String term = "";
							String sem = "";
							int year = 0;

							do {
								System.out.println("Please select a semester:");
								String semester = in.next();

								try {
									Character user_Char = semester.charAt(0);
									String user_Int = semester.substring(1);

									year = Integer.parseInt(user_Int);

									user_Char = Character.toUpperCase(user_Char);
									if ((user_Char.equals('F') || user_Char.equals('S'))
											&& (year <= 2500 && year >= 1900)) {
										invaildInput = false;
										sem = user_Char + user_Int;
										if (user_Char.equals('F')) {
											term = "Fall";
										} else {
											term = "Spring";
										}

									}

								} catch (Exception e) {
									System.out.println("invalid input");
								}

							} while (invaildInput);

							courses = database.get(webid).getCourses();

							courses.add(new Course(dpt, num, sem));

							database.get(webid).setCourses(courses);

							System.out.println(dpt + " " + num + " added in " + term + " " + year);

							break;

						case "D": // Drop a class

							System.out.println("Please enter course name: ");
							in.nextLine();
							String strD = in.nextLine();

							String sD[] = strD.split(" ");

							String dptD = sD[0].toUpperCase(); // department
							int numD = Integer.parseInt(sD[1]);

							courses = database.get(webid).getCourses();

							boolean drop = false;
							for (int i = 0; i < courses.size(); i++) {
								Course c = (Course) courses.get(i);
								if (c.getDepartment().equals(dptD) && c.getNumber() == numD) {
									String sem0 = c.getSemester();
									Character term0 = sem0.charAt(0);
									String user_Int = sem0.substring(1);

									courses.remove(i);

									if (term0.equals('F')) {
										sem0 = "Fall";
									} else {
										sem0 = "Spring";
									}

									drop = true;
									System.out.println(dptD + " " + numD + " dropped from " + sem0 + " " + user_Int);
								}

							}
							if (drop == false) {
								System.out.println("this course doesn't exist");
							}

							break;
						case "C": // View your classes sorted by course name/department

							courses = database.get(webid).getCourses();
							Collections.sort(courses, new CourseNameComparator());
							System.out.println("Dept. Course Semester");
							System.out.println("-----------------------");

							for (int i = 0; i < courses.size(); i++) {
								System.out.println(courses.get(i));
							}

							break;
						case "S": // View your courses sorted by semester
							courses = database.get(webid).getCourses();
							Collections.sort(courses, new SemesterComparator());

							System.out.println("Dept. Course Semester");
							System.out.println("-----------------------");

							for (int i = 0; i < courses.size(); i++) {
								System.out.println(courses.get(i));
							}

							break;
						case "L": // user logged out
							loop3 = false;
							System.out.println(webid + " logged out.");

							break;

						}

						System.out.println("");
						System.out.println("");
						System.out.println("");

					} while (loop3);

				} else {

				}

				break;

			case "X": // Save and quit (and subsequently load)

				// Serialize

				try {
					FileOutputStream fos = new FileOutputStream("database.ser");

					ObjectOutputStream oos = new ObjectOutputStream(fos);

					oos.writeObject(database);

					// closing FileOutputStream and
					// ObjectOutputStream
					oos.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("System state saved, system shut down for maintenance.");
				System.out.println("");

				load = true;

				break;
			case "Q": // Quit without saving state (and delete any save-file)
				loop1 = false;
				System.out.println("Good bye, please pick the right SUNY next time!");
				database.clear();
				break;
			}

		} while (loop1);
	}

}
