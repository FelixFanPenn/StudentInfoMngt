import static org.junit.Assert.*;

import org.junit.Test;

public class BSTNodeTest {

	@Test
	public void testBSTNode() {
		Student newStu = new Student("Bernie", "Sanders");
		BSTNode<Student> bstNode = new BSTNode<>(newStu);
		assertTrue(bstNode != null);
		assertTrue(bstNode.left == null);
		assertTrue(bstNode.right == null);
		assertTrue(bstNode.parent == null);
		Student newStu1 = new Student("Hillary", "Trump");
		BSTNode<Student> bstNode1 = new BSTNode<>(newStu1);
		bstNode.left = bstNode1;
		assertTrue(bstNode.left != null);
	}
	


}
