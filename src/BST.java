
import java.util.ArrayList;
import java.util.Map;

public class BST implements BSTInterface{
	
	private BSTNode<Student> root;
	private int size;
	

	
	/*
	 * constructor for an empty tree
	 */
	public BST(){ 
		size = 0;
		root = null;
	}
	
	/*
	 * constructor for a root specified
	 */
	public BST(Student s){
		size = 1;
		root = new BSTNode<Student>(s);
	}
	
	/*
	 *  return the root of the tree
	 */
	public BSTNode<Student> getRoot() {
		return root;
	}
	
	/*
	 *  set the root to a node
	 */
	public void setRoot(BSTNode<Student> newRoot) {
		this.root = newRoot;
	}
	
	/*
	 * @param: a Student obj
	 */
	@Override
	public void insertStudent(Student s) {                       

		BSTNode<Student> newStudent = new BSTNode<Student>(s);
		BSTNode<Student> cur = root;
		
		if (root == null) {
			root = newStudent;
			size++;
			return;
		}
		
		
		//ArrayList<HWGrades> copy = new ArrayList<>(cur.value.getHWGrades());
		
		BSTNode<Student> pre = null;
		boolean addLeft = true;
		
		while(cur != null){
			if (cur.value.compareTo(s) == 0){           // if both last name and first name match, return without any exception.
				System.out.println("Error! CIT School does not allow students with same name!");
				return;
			} else if (cur.value.compareTo(s) == 1){
				pre = cur;
				addLeft = true;
				cur = cur.left;
			} else {
				addLeft = false;
				pre = cur;
				cur = cur.right;
			}
		}
		if (addLeft) {                 // test add to left sub tree or right sub tree
			pre.left = newStudent;
			newStudent.parent = pre;
		} else {
			pre.right = newStudent;
			newStudent.parent = pre;
		}
		size++;
		int i = 0;
		if (newStudent.value.getHWGrades().size() == 0){   // if this student has no homework yet, initialize some hws and their grades are zeros
			for (HWGrades hwGrades : getRoot().value.getHWGrades()){
				int maxScore = hwGrades.getHomework().getMaxScore();
				Assignment assignment = new Assignment(maxScore);
				assignment.setAssignNum(i++);
				HWGrades hwGrades2 = new HWGrades(assignment, 0);
				newStudent.value.getHWGrades().add(hwGrades2);
			}
			newStudent.value.setId(size-1);
		}
		
		checkBalanced(newStudent);   // invoke checkBalanced method and check the balance situation and rebalance if it is not balanced
		return;
		
		
	}
	
	
	/*
	 * @param: a BSTNode which is the node just added
	 */
	private void checkBalanced(BSTNode<Student> cur) {
		if (cur == null) return;
		if (isBalanced(cur)) {
			checkBalanced(cur.parent);   // if it is balanced, keep recursing
		} else {
			BSTNode<Student> pre = cur.parent;						// save the parent of current node(inbalanced node)

			boolean leftDeeper1; 									// leftDeeper1/2 indicate which sub tree is deeper
			leftDeeper1 = maxDepth(cur.left) > maxDepth(cur.right) ? true : false;
			
			boolean leftDeeper2;
			if (leftDeeper1){
				BSTNode<Student> child = cur.left;
				leftDeeper2 = maxDepth(child.left) > maxDepth(child.right) ? true : false;	
			}
			else {
				BSTNode<Student> child = cur.right;
				leftDeeper2 = maxDepth(child.left) > maxDepth(child.right) ? true : false;
			}
			
			BSTNode<Student> newNode;
			if (leftDeeper1 && leftDeeper2){       		//four cases use different rotations
				newNode = leftLeftRotation(cur);
			} else if (leftDeeper1 && !leftDeeper2){
				newNode = leftRightRotation(cur);
			} else if (!leftDeeper1 && leftDeeper2){
				newNode = rightLeftRotation(cur);
			} else {
				newNode = rightRightRotation(cur);
			}
			
			if (pre == null){  							// handle the parent node
				setRoot(newNode);
			} else {
				newNode.parent = pre;
				if (pre.value.compareTo(newNode.value) > 0){
					pre.left = newNode;
				} else {
					pre.right = newNode;
				}
			}
		}
		
	}
	
	
	/*
	 * @param: a BSTNode
	 * @return: the max depth of node cur
	 */
	public int maxDepth(BSTNode<Student> cur) {    		
		if (cur == null) return 0;
		return Math.max(maxDepth(cur.left), maxDepth(cur.right)) + 1;
	}
	
	
	/*
	 * @param: a BSTNode
	 * @return: true if it is balanced and false otherwise
	 */
	public boolean isBalanced(BSTNode<Student> cur) {  
		if (cur == null) return true;
		int left = maxDepth(cur.left);
		int right = maxDepth(cur.right);
		return Math.abs(left - right) <= 1;
	}
	
	/*
	 * @param: a BSTNode
	 * @return: new root of this sub tree after balancing
	 */
	private BSTNode<Student> leftLeftRotation(BSTNode<Student> cur) {
		BSTNode<Student> newNode;
		newNode = cur.left;
		
		if (newNode.right != null){
			newNode.right.parent = cur;
		}
		
		cur.left = newNode.right;
		newNode.right = cur;
		cur.parent = newNode;
		newNode.parent = null;
		return newNode;
	}
	
	/*
	 * @param: a BSTNode
	 * @return: new root of this sub tree after balancing
	 */
	private BSTNode<Student> rightRightRotation(BSTNode<Student> cur) {
		BSTNode<Student> newNode;
		newNode = cur.right;
		
		if (newNode.left != null){
			newNode.left.parent = cur;
		}
		
		cur.right = newNode.left;
		newNode.left = cur;
		cur.parent = newNode;
		newNode.parent = null;		
		return newNode;
	}
	
	
	/*
	 * @param: a BSTNode
	 * @return: new root of this sub tree after balancing
	 */
	private BSTNode<Student> leftRightRotation(BSTNode<Student> cur) {
		cur.left = rightRightRotation(cur.left);
		return leftLeftRotation(cur);
	}
	
	/*
	 * @param: a BSTNode
	 * @return: new root of this sub tree after balancing
	 */
	private BSTNode<Student> rightLeftRotation(BSTNode<Student> cur) {
		cur.right = leftLeftRotation(cur.right);
		return rightRightRotation(cur);
	}

	
	/*
	 * @param: a student obj
	 * @return: true if it is in the tree and false otherwise
	 */
	@Override
	public boolean findStudent(Student s) {
		BSTNode<Student> cur = getRoot();
		if (cur == null) return false;
		
		while(cur != null){
			if (cur.value.compareTo(s) == 0){
				return true;
			} else if (cur.value.compareTo(s) == 1){
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}		
		return false;
	}
	
	/*
	 * @param: a map, maxScore and assignNum
	 * this is a helper function for loadAssignmentGrades method in Manager class
	 * it loads grades for every one in the tree and 0 point for any one who is not in that file
	 */
	public void loadGrades(Map<String, Integer> map, int maxScore, int assignNum) {
		BSTNode<Student> cur = getRoot();
		if (cur == null) return;
		
		Assignment assignment = new Assignment(maxScore);
		assignment.setAssignNum(assignNum);
		ArrayList<Student> res = new ArrayList<>();
		preOrder(cur, res);
		
		for (Student student : res){
			if (map.containsKey(student.getLastNameFirst())){
				double percentage = map.get(student.getLastNameFirst()) * 1.0 / (1.0 * maxScore);
				if (student.getHWGrades().size() == assignNum){    // case where we should add an grades to a student.
					HWGrades hwGrades = new HWGrades(assignment, percentage);
					student.getHWGrades().add(hwGrades);
				}
			} else {											  // case where a student is not in that load assignment file.
				double percentage = 0;
				HWGrades hwGrades = new HWGrades(assignment, percentage);
				student.getHWGrades().add(hwGrades);
			}
		}
		return;
	}
	
	/*
	 * preorder traversal
	 * helper of loadGrades
	 */
	public void preOrder(BSTNode<Student> root, ArrayList<Student> res){
		if (root == null) return;
		res.add(root.value);
		preOrder(root.left, res);
		preOrder(root.right, res);
	}
	
	/*
	 * @param: two strings and two ints
	 * this is a helper function for updateStudentGrade method in Manager class
	 * it updates a student's homework grade
	 */
	public void updateGrade(String firstName, String lastName, int assignNum, int actualScore) {
		BSTNode<Student> cur = getRoot();
		if (cur == null) return;
		
		while(cur != null){
			for (int i = 0; i < cur.value.getHWGrades().size(); i++){
				cur.value.getHWGrades().get(i).getHomework().setAssignNum(i);
			}
			if (cur.value.getLastName().equals(lastName)){
				if (cur.value.getFirstName().equals(firstName)){
					int len = cur.value.getHWGrades().size();
					if (len - 1 < assignNum) return;
					double percentage = actualScore * 1.0 / (1.0 * cur.value.getHWGrades().get(assignNum).getHomework().getMaxScore());
					if (percentage > 1) return;
					for (HWGrades hwGrades : cur.value.getHWGrades()){
						if (hwGrades.getHomework().getAssignmentNumber() == assignNum){
							hwGrades.setPC(percentage);
							break;
						}
					}
					return;
				} else if (cur.value.getFirstName().compareTo(firstName) > 0){
					cur = cur.left;
				} else {
					cur = cur.right;
				}
			} else if (cur.value.getLastName().compareTo(lastName) > 0){
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}		
		return;
		
	}
	
	/*
	 * @return: the size of this tree
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * @return: the max depth of this tree
	 */
	@Override
	public int maxDepth() {
		BSTNode<Student> cur = getRoot();
		return maxDepth(cur);
	}

}
