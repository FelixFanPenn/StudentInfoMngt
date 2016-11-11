import java.util.ArrayList;

public class Student implements StudentInterface{   
	private String firstName;
	private String lastName;
	private int id;
	private static int idNumber = 0;
	private ArrayList<HWGrades> hwGrades;        //this arraylist contains all the grades for each assignment          
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = idNumber;
		idNumber++;
		hwGrades = new ArrayList<HWGrades>();     // assignment here is a collection of all assignments from this student
	}
	
	public  ArrayList<HWGrades> getHWGrades() {
		return hwGrades;
	}
	
	@Override
	public int getIDnumber() {
		return this.id;
	}
	
	public void setId(int newID){
		this.id = newID;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastNameFirst(){
		return getLastName() + "," + getFirstName();
	}
	
	public char getGrade(){   // calculate grade for students
		
		if (hwGrades == null || hwGrades.size() == 0){
			return '-';
		}
		
		int maxTotal = 0;
		int actualTotal = 0;
		
		for (int i = 0; i < hwGrades.size(); i++){
			maxTotal += hwGrades.get(i).getHomework().getMaxScore();
			actualTotal += hwGrades.get(i).getRawScore();
		}
		double percentage = actualTotal * 1.0/(maxTotal * 1.0);
		
		if (percentage >= 0.9){
			return 'A';
		} else if (percentage >= 0.8){
			return 'B';
		} else if (percentage >= 0.7){
			return 'C';
		} else if (percentage >= 0.6){
			return 'D';
		} else {
			return 'F';
		}
	}
	
	public String toString(){                        
		
		String string = getFirstName() + " " + getLastName() + " " + getIDnumber() + " " + getGrade();
		for (int i = 0; i < getHWGrades().size(); i++){
			string += " " + (int)getHWGrades().get(i).getRawScore();
		}	
		return string;
	}
	
	public String output() {
		String string = getFirstName() + " " + getLastName() + " " + getIDnumber();
		for (int i = 0; i < getHWGrades().size(); i++){
			string += " " + (int)getHWGrades().get(i).getRawScore();
		}
		
		return string;	
	}
	
	@Override
	public int compareTo(Student s) {             // compare students with their last name and then first name
		if (getLastName().compareTo(s.getLastName()) > 0){
			return 1;
		} else if (getLastName().compareTo(s.getLastName()) < 0){
			return -1;
		} else {
			if (getFirstName().compareTo(s.getFirstName()) > 0){
				return 1;
			} else if (getFirstName().compareTo(s.getFirstName()) < 0){
				return -1;
			} else {
				return 0;
			}
		}
	}

}
