
import org.junit.Test;

public class ManagerTest {

	@Test
	public void testAddStudent() {
		Manager manager = new Manager();
		manager.loadLogFile("exampleOutput.txt");
		manager.addStudent("Bernie", "Sanders");  
		manager.createLogFile("haha6.txt");
	}

	@Test
	public void testAddAssignment() {
		Manager manager = new Manager();
		manager.loadLogFile("exampleOutput.txt");
		manager.addAssignment(20000);
		manager.createLogFile("haha5.txt");
		
	}

	@Test
	public void testUpdateStudentGrade() {
		Manager manager = new Manager();
		manager.loadLogFile("exampleOutput.txt");
		manager.updateStudentGrade("Perfect", "Student", 0, 10);
		manager.updateStudentGrade("Bad", "Student", 0, 10);
		manager.updateStudentGrade("Perfect", "Student", 1, 10);
		manager.updateStudentGrade("Perfect", "Student", 2, 10);
		manager.updateStudentGrade("Perfect", "Student", 3, 10);
		manager.updateStudentGrade("Pct", "unt", 3, 10);
		manager.createLogFile("haha1.txt");
	}

	@Test
	public void testCreateLogFile() {
		Manager manager = new Manager();
		manager.loadLogFile("exampleOutput.txt");
		manager.createLogFile("haha2.txt");
	}

	@Test
	public void testLoadLogFile() {
		Manager manager = new Manager();
		manager.loadLogFile("exampleOutput.txt");
	}

	@Test
	public void testLoadAssignmentGrades() {
		Manager manager = new Manager();
		manager.loadLogFile("exampleOutput.txt");
		manager.loadAssignmentGrades("assignmentExample.txt");
		manager.createLogFile("haha3.txt");
	}
	
	/*
	@Test
	public void testGetFinalGrades() {
		Manager manager = new Manager();
		manager.loadLogFile("example.txt");
		System.out.println(manager.getFinalGrades());
		assertEquals(manager.getFinalGrades(), "Aaronson, Aaron - C\nStudent, Perfect - A\nStudent, Bad - F\n");
	}
	*/

}
