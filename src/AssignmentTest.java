import static org.junit.Assert.*;
import org.junit.Test;

public class AssignmentTest {

	@Test
	public void testAssignment() {
		Assignment assignment = new Assignment(100);
		assertEquals(assignment.getAssignmentNumber(), 1);
		assertTrue(assignment != null);
	}

	@Test
	public void testGetAssignmentNumber() {
		Assignment assignment1 = new Assignment(50);
		Assignment assignment2 = new Assignment(100);
		assertEquals(assignment1.getAssignmentNumber(), 2);
		assertEquals(assignment2.getAssignmentNumber(), 3);
	}

	@Test
	public void testGetMaxScore() {
		Assignment assignment1 = new Assignment(50);
		Assignment assignment2 = new Assignment(100);
		assertTrue(assignment1.getMaxScore() == 50);
		assertTrue(assignment2.getMaxScore() == 100);
	}
	
	@Test
	public void testSetAssignNum() {
		Assignment assignment1 = new Assignment(50);
		assignment1.setAssignNum(20);
		assertEquals(20, assignment1.getAssignmentNumber());
	}

}
