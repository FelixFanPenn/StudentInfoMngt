import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {

	@Test
	public void testStudent() {
		Student newStudent = new Student("Bernie", "Sanders");
		assertNotEquals(newStudent, null);
	}

	@Test
	public void testGetHWGrades() {
		Student newStudent = new Student("Bernie", "Sanders");
		assertEquals(newStudent.getHWGrades().size(), 0);
	}

	@Test
	public void testGetIDnumber() {
		Student newStudent = new Student("Bernie", "Sanders");
		assertEquals(newStudent.getIDnumber(), 2);
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertEquals(newStudent1.getIDnumber(), 3);
	}

	@Test
	public void testGetLastName() {
		Student newStudent = new Student("Bernie", "Sanders");
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertEquals(newStudent.getLastName(), "Sanders");
		assertEquals(newStudent1.getLastName(), "Clinton");
	}

	@Test
	public void testGetFirstName() {
		Student newStudent = new Student("Bernie", "Sanders");
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertEquals(newStudent.getFirstName(), "Bernie");
		assertEquals(newStudent1.getFirstName(), "Hillary");
	}

	@Test
	public void testGetLastNameFirst() {
		Student newStudent = new Student("Bernie", "Sanders");
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertEquals(newStudent.getLastNameFirst(), "Sanders,Bernie");
		assertEquals(newStudent1.getLastNameFirst(), "Clinton,Hillary");
	}

	@Test
	public void testGetGrade() {
		Student newStudent = new Student("Bernie", "Sanders");
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertEquals(newStudent.getGrade(), '-');
		assertEquals(newStudent1.getGrade(), '-');
	}

	@Test
	public void testToString() {
		Student newStudent = new Student("Bernie", "Sanders");
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertEquals(newStudent.toString(), "Bernie Sanders 4 -");
		assertEquals(newStudent1.toString(), "Hillary Clinton 5 -");
	}

	@Test
	public void testCompareTo() {
		Student newStudent = new Student("Bernie", "Sanders");
		Student newStudent1 = new Student("Hillary", "Clinton");
		assertTrue(newStudent.compareTo(newStudent1) > 0);
		assertTrue(newStudent1.compareTo(newStudent) < 0);
		assertTrue(newStudent.compareTo(newStudent) == 0);
	}

}
