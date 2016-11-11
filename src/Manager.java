import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Manager implements ManagerInterface{
	
	private BST tree = new BST();
	
	/*
	 * @param: two strings
	 * call insertStudent method in BST class to add a student.
	 */
	@Override
	public void addStudent(String firstName, String lastName) {
		Student new_student = new Student(firstName, lastName);
		tree.insertStudent(new_student);
	}
	
	/*
	 * @param: an int
	 * add an assignment to every existing student
	 */
	@Override
	public void addAssignment(int maxScore) {
		Assignment as = new Assignment(maxScore);
		HWGrades hwGrades = new HWGrades(as, 0.0);
		int assignNum = tree.getRoot().value.getHWGrades().size();
		hwGrades.getHomework().setAssignNum(assignNum);
		addAssignmentHelper(tree.getRoot(), hwGrades);
	}
	
	private void addAssignmentHelper(BSTNode<Student> node, HWGrades hwGrades){
		if (node == null) return;
		node.value.getHWGrades().add(hwGrades);
		addAssignmentHelper(node.left, hwGrades);
		addAssignmentHelper(node.right, hwGrades);
	}
	
	/*
	 * @param: two strings, tow ints
	 * update a student's grade
	 * invoke a helper function in BST class
	 */
	@Override
	public void updateStudentGrade(String firstName, String lastname, int assignmentNo, int newGrade) {  
		if (assignmentNo < 0) return;
		tree.updateGrade(firstName, lastname, assignmentNo, newGrade);
	}

		
	/*
	 * @param: a string representing a file name
	 * print all info in this tree to a file
	 */
	@Override
	public void createLogFile(String outputFilename) {
		
		ArrayList<Student> studentArr = new ArrayList<>();
		studentArr = preOrderTraversal(tree.getRoot());

		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(outputFilename));
	        if (studentArr == null || studentArr.size() == 0){
	        	out.close();
	        	return;
	        } else {
	        	String maxScores = "";    // WRITE MAX SCORES FOR EACH HW
	        	for (int i = 0; i < studentArr.get(0).getHWGrades().size(); i++){
	        		maxScores = maxScores + studentArr.get(0).getHWGrades().get(i).getHomework().getMaxScore() + " ";
	        	}
	        	maxScores = maxScores.trim();
	        	out.write(maxScores);
	        	out.newLine();
	        	
	        	for (int i = 0; i < studentArr.size(); i++){
	        		out.write(studentArr.get(i).output());
	        		out.newLine();
	        	}
	        	out.close();
	        }
	    } catch (IOException e) {}
	}
	
	/*
	 * preorder Traversal helper function
	 */
	private ArrayList<Student> preOrderTraversal(BSTNode<Student> node) {
		ArrayList<Student> arrayList = new ArrayList<>();
		preHelper(node, arrayList);
		return arrayList;
	}
	
	private void preHelper(BSTNode<Student> node, ArrayList<Student> arrayList){
		if (node == null) return;
		arrayList.add(node.value);
		preHelper(node.left, arrayList);
		preHelper(node.right, arrayList);
	}
	
	/*
	 *  @param: a string representing a file name
	 * 	read all infor from the file and construct a tree
	 */
	@Override
	public void loadLogFile(String inputFilename) {
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(inputFilename));

			String str;
			if ((str = br.readLine()) == null){
				System.out.println("empty file!");
				br.close();
				return;
			}
			String[] tmp = str.split(" ");
			
			ArrayList<Assignment> assignments = new ArrayList<>();
			for (int i = 0; i < tmp.length; i++){
				assignments.add(new Assignment(Integer.parseInt(tmp[i])));
			}
			
			while ((str = br.readLine()) != null) {
				tmp = str.split(" ");
				Student newStu = new Student(tmp[0], tmp[1]);
				newStu.setId(Integer.parseInt(tmp[2]));
				for (int i = 3; i < tmp.length; i++){
					double percentage = Integer.parseInt(tmp[i]) * 1.0/(assignments.get(i-3).getMaxScore() * 1.0);
					HWGrades hwGrade = new HWGrades(assignments.get(i-3), percentage);
					newStu.getHWGrades().add(hwGrade);
				}
				tree.insertStudent(newStu);
			}

		} catch (IOException e) {}	
		
	}
	
	/*
	 *	@param: a string representing a file name
	 * 	read all infor from the file and update grades for students
	 */
	@Override
	public void loadAssignmentGrades(String inputFilename) {
		BufferedReader br = null;
		Map<String, Integer> map = new HashMap<>();
		int assignNum = 0;
		int maxScore = 0;
		try {
			br = new BufferedReader(new FileReader(inputFilename));
			assignNum = Integer.parseInt(br.readLine());
			if (tree.getRoot().value.getHWGrades().size() < assignNum){
				br.close();
				return;
			}
			maxScore = Integer.parseInt(br.readLine());
			String str;
			
			while ((str = br.readLine()) != null) {
				String[] tmp = str.split(" ");
				int actualScore = Integer.parseInt(tmp[2]);
				map.put(tmp[1] + "," + tmp[0], actualScore);
			}		
			
		} catch (IOException e) {}	
		
		tree.loadGrades(map, maxScore, assignNum);
	}
	
	/*
	@Override
	public String getFinalGrades() {                        // I keep this because in the original version of ManagerInterface we have getFinalGrades() method.
		// TODO Auto-generated method stub
		String string = "";
		if (tree.getRoot() == null) return string;
		ArrayList<Student> studentArr = inOrderTraversal(tree.getRoot());
		//System.out.println("the size of studentARR is" + studentArr.size());
		for (int i = 0; i < studentArr.size(); i++){
			String grade = String.valueOf(studentArr.get(i).getGrade());
			String tmp = studentArr.get(i).getLastName() + ", " + studentArr.get(i).getFirstName() + " - " + grade + "\n";
			string += tmp;
		}
		//System.out.println(string);
		return string;
	}
	*/
	
	/*														// I keep these two methods because they are helper methods for getFinalGrades
	private ArrayList<Student> inOrderTraversal(BSTNode<Student> node) {
		ArrayList<Student> arrayList = new ArrayList<>();
		inHelper(node, arrayList);
		return arrayList;
	}
	
	private void inHelper(BSTNode<Student> node, ArrayList<Student> arrayList){
		if (node == null) return;
		preHelper(node.left, arrayList);
		arrayList.add(node.value);
		preHelper(node.right, arrayList);
	}
	*/

}
