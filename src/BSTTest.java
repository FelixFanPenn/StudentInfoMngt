import static org.junit.Assert.*;
import org.junit.Test;

public class BSTTest {

	@Test
	public void testBST() {
		BST bst = new BST();
		assertEquals(bst.getRoot(), null);
	}

	@Test
	public void testBSTStudent() {
		Student newStu = new Student("Donald", "Trump");
		BST bst = new BST(newStu);
		assertNotEquals(bst, null);
	}

	@Test
	public void testGetRoot() {
		Student newStu = new Student("Donald", "Trump");
		BST bst = new BST(newStu);
		assertTrue(bst.getRoot() != null);
	}

	@Test
	public void testInsertStudent() {
		Student newStu1 = new Student("Donald", "Trump");
		BST bst = new BST(newStu1);
		Student newStu2 = new Student("John", "Trump");
		Student newStu3 = new Student("Ann", "Trump");
		bst.insertStudent(newStu2);
		bst.insertStudent(newStu3);
		assertTrue(bst.size() == 3);
	}
	
	
	public void testIsBalanced() {
		Student newStu1 = new Student("A", "Trump");
		BST bst = new BST(newStu1);
		Student newStu2 = new Student("B", "Trump");
		Student newStu3 = new Student("C", "Trump");
		bst.insertStudent(newStu2);
		bst.insertStudent(newStu3);
		BSTNode<Student> root = bst.getRoot();
		assertFalse(bst.isBalanced(root));
		assertTrue(bst.isBalanced(root.right));
	}

	@Test
	public void testFindStudent() {
		Student newStu1 = new Student("Donald", "Trump");
		BST bst = new BST(newStu1);
		Student newStu2 = new Student("John", "Trump");
		Student newStu3 = new Student("Ann", "Trump");
		bst.insertStudent(newStu2);
		bst.insertStudent(newStu3);
		
		Student s = new Student("Ann", "Trump");
		assertTrue(bst.findStudent(s));
		
		Student s1 = new Student("An", "Trump");
		assertFalse(bst.findStudent(s1));
	}

	@Test
	public void testSize() {
		Student newStu1 = new Student("Donald", "Trump");
		BST bst = new BST(newStu1);
		assertTrue(bst.size() == 1);
		Student newStu2 = new Student("John", "Trump");
		Student newStu3 = new Student("Ann", "Trump");
		bst.insertStudent(newStu2);
		bst.insertStudent(newStu3);
		assertTrue(bst.size() == 3);
		
		Student newStu4 = new Student("An", "Trump");
		bst.insertStudent(newStu4);
		assertTrue(bst.size() == 4);
		Student newStu5 = new Student("An2", "Trump");
		bst.insertStudent(newStu5);
		assertTrue(bst.size() == 5);
		
	}

	@Test
	public void testMaxDepth() {
		Student newStu1 = new Student("A", "Trump");
		BST bst = new BST(newStu1);
		assertTrue(bst.maxDepth() == 1);
		Student newStu2 = new Student("B", "Trump");
		Student newStu3 = new Student("C", "Trump");
		bst.insertStudent(newStu2);
		bst.insertStudent(newStu3);
		assertTrue(bst.maxDepth() == 2);
		
		Student newStu4 = new Student("D", "Trump");
		bst.insertStudent(newStu4);
		assertTrue(bst.maxDepth() == 3);
		Student newStu5 = new Student("E", "Trump");
		bst.insertStudent(newStu5);
		assertTrue(bst.maxDepth() == 3);
		Student newStu6 = new Student("F", "Trump");
		bst.insertStudent(newStu6);
		assertTrue(bst.maxDepth() == 3);
		Student newStu7 = new Student("G", "Trump");
		bst.insertStudent(newStu7);
		assertTrue(bst.maxDepth() == 3);
		Student newStu8 = new Student("H", "Trump");
		bst.insertStudent(newStu8);
		assertTrue(bst.maxDepth() == 4);
	}

}
