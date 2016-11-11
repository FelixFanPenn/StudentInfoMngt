import static org.junit.Assert.*;

import org.junit.Test;

public class HWGradesTest {

	@Test
	public void testHWGrades() {
		Assignment as = new Assignment(100);
		HWGrades hwGrades = new HWGrades(as, 0.95);
		assertTrue(hwGrades != null);
	}

	@Test
	public void testSetPC() {
		Assignment as = new Assignment(100);
		HWGrades hwGrades = new HWGrades(as, 0);
		hwGrades.setPC(0.95);
		assertTrue(hwGrades.getRawScore() == 95);
	}

	@Test
	public void testGetRawScore() {
		Assignment as = new Assignment(100);
		HWGrades hwGrades = new HWGrades(as, 0.95);
		assertTrue(hwGrades.getRawScore() == 95);
	}

	@Test
	public void testGetHomework() {
		Assignment as = new Assignment(100);
		HWGrades hwGrades = new HWGrades(as, 0.95);
		assertTrue(hwGrades.getHomework() != null);
	}

}
